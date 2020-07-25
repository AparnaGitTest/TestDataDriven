package com.w2a.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.w2a.base.TestBase;

public class TestUtil extends TestBase {
	public static String screenshotPath;
	public static String screenshotName;
	public  static void captureScreenshot() throws IOException
	{
		Date d=new Date();
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshotName=d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		//TakesScreenshot is the interface
		//type cast using driver refrence((TakeScreenshot)driver)
		FileUtils.copyFile(scrFile, new File("C:\\Users\\Aparna\\eclipse-workspace\\LiveProjects\\DDFFourthAttempt\\target\\surefire-reports\\html\\"+screenshotName));
		
	}
	
	@DataProvider(name ="dp")
	public Object[][] getData(Method m)
	{
		String sheetName=m.getName();
		int rows=excel.getRowCount(sheetName);
		int cols=excel.getColumnCount(sheetName);
		
		Object[][] data=new Object[rows-1][1];
		Hashtable<String,String> table=null;
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			table=new Hashtable<String,String>();
			for(int colNum=0;colNum<cols;colNum++)
			{
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0]=table;
			}
		}
		
		return data;
	}
	
	public static boolean isTestRunnable(String testname,ExcelReader excel)
	{
		String sheetName="testsuite";
		int rows= excel.getRowCount(sheetName);
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
		String testcase=excel.getCellData(sheetName, "TCID", rowNum);
		if(testcase.equalsIgnoreCase(testname))
		{
			String runMode=excel.getCellData(sheetName, "Runmode", rowNum);
			if(runMode.equalsIgnoreCase("Y"))
			
				return true;
			else
				return false;
			
				
		}
		}
		return false;
	}

}
