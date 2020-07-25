package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase {

	@Test
	public void bankManagerLoginTest() throws IOException
	{
		verifyEquals("abc","xyz");
		//Assert.assertEquals("abc", "xyz");
		log.debug("Inside bank manager Login !!");
		click("bmlBtn_CSS");
		//Reporter.log("Clicked on Business Manager Login Button !!");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS", "Login sucessfull"))));
		//Assert.fail("FAILED");
		log.debug("Login successfully executed !!");
		
	}
}
