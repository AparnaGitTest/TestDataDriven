package com.w2a.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data)
	{
		
		if(!data.get("runmode").equals("Y"))
		{
			throw new SkipException("Skipping the test case as the runmode is NO");

		}
		click("addCustBtn_CSS");
		type("firstName_CSS",data.get("firstName"));
		type("lastName_CSS",data.get("LastName"));
		type("postCode_CSS",data.get("PostCode"));
		click("addCustomer_CSS");
	
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("Message")));
		alert.accept();
		//adding a commnet to update the file
		
	}
	
}
