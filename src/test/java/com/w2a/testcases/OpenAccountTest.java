package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String,String> data)
	{
		if(!TestUtil.isTestRunnable("OpenAccountTest", excel))
		{
			throw new SkipException("Skipping the test OpenAccountTest".toUpperCase()+" as the runmode is NO");
			
		}
		
		click("openaccount_CSS");
		select("customer_CSS",data.get("customer"));
		select("currency_CSS",data.get("currency"));
		click("Process_CSS");
		
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		
		alert.accept();
		
		
		
		
	}
	

}
