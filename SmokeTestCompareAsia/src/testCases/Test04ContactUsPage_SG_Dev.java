package testCases;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import appModules.CommonFindMethods;

import pageObjects.BaseClass;
import pageObjects.CommonVerifyActions;
import pageObjects.CommonFindObjects;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class Test04ContactUsPage_SG_Dev {
	public WebDriver driver;
	private static String sTestCaseName;
	private static int iTestCaseRow;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private static String beforePopup;

	// prerequisites
	@BeforeTest
	public void beforeTestPage() throws Exception {
		// Configuring Log4j logs
		DOMConfigurator.configure("log4j.xml");
		// Getting the Test Case name
		sTestCaseName = this.toString();
		sTestCaseName = Utils.getTestCaseName(this.toString());
		// Start printing the logs and printing the Test Case name
		Log.startTestCase(sTestCaseName);
		// Setting up the Test Data Excel file using Constants variables
		ExcelWSheet = ExcelUtils.setExcelFile(Constant.File_TestData, "Login");
		// Fetching the Test Case row number from the Test Data Sheet
		iTestCaseRow = ExcelUtils.getRowContains(ExcelWSheet, sTestCaseName, Constant.Col_TestCaseName);
		// Launching the browser, this will take the Browser Type from Test Data
		// Sheet
		driver = Utils.OpenBrowser(iTestCaseRow);
		// Initializing the Base Class for Selenium driver
		new BaseClass(driver);
	}

	@DataProvider(name = "DP")
	public static Object[][] createData() throws Exception {
		ExcelWSheet = ExcelUtils.setExcelFile(Constant.File_TestData, sTestCaseName);
		ExcelUtils.setExcelSheet(ExcelWBook, sTestCaseName);
		Object[][] retObjArr = ExcelUtils.getRows(ExcelWSheet, sTestCaseName);
		System.out.println(retObjArr);
		return (retObjArr);
	}

	// This is the starting of the Main Test Case
	@Test(dataProvider = "DP")
	public void testHomePage(String StepNo, String StepName, String strExecuted, String strbyMethod,
			String strObjIdentifier, String strParam1, String strAction, String strDataId, String strFunction,
			String strArgs, String strParam2) throws Exception {
		try {
			beforePopup = driver.getWindowHandle();
			// Here we are calling the SignIN Action and passing argument
			if (!strExecuted.toUpperCase().equals("N")) {
				CommonFindMethods.executeMethod(StepNo, StepName, strbyMethod, strObjIdentifier, strParam1, strAction,
						strDataId, strFunction, strArgs, beforePopup);
				iTestCaseRow = ExcelUtils.getUniqueRow(ExcelWSheet, StepNo, StepName);
			}
			if (BaseClass.bResult == true) {
				// If the value of boolean variable is True, then your test is
				// complete pass and do this
				if (!strExecuted.toUpperCase().equals("N"))
					ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
			} else {
				// If the value of boolean variable is False, then your test is
				// fail, and you like to report it accordingly
				// This is to throw exception in case of fail test, this
				// exception will be caught by catch block below
				throw new Exception("Test Case Failed because of Verification");
			}
			// Below are the steps you may like to perform in case of failed
			// test or any exception faced before ending your test
		} catch (Exception e) {
			// If in case you got any exception during the test, it will mark
			// your test as Fail in the test result sheet
			ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
			// If the exception is in between the test, bcoz of any element not
			// found or anything, this will take a screen shot
			Utils.takeScreenshot(driver, sTestCaseName);
			// This will print the error log message
			Log.error(e.getMessage());
			// Again throwing the exception to fail the test completely in the
			// TestNG results
			throw (e);
		}
	}

	// close the finish the test case
	@AfterTest
	public void afterTestPage() {
		// Print logs to end the test case
		Log.endTestCase(sTestCaseName);
		// Closing the opened driver
		driver.close();
	}
}
