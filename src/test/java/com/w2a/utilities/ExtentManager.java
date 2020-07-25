package com.w2a.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance()
	// ExtentReports is return type
	{
		if (extent == null) {
			// the path where report should generate
			extent = new ExtentReports(
					"C:\\Users\\Aparna\\eclipse-workspace\\LiveProjects\\DDFFourthAttempt\\target\\surefire-reports\\html\\extent.html",
					true, DisplayOrder.OLDEST_FIRST);
			
			extent.loadConfig(new File(
					"C:\\Users\\Aparna\\eclipse-workspace\\LiveProjects\\DDFFourthAttempt\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		}

		return extent;
	}

}
