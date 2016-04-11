package testCases;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class Test01HomePage_SG_Dev {
	public static WebDriver driver = null;
	public static XSSFSheet ExcelWSheet;
	public static WebDriver OpenBrowser(int iTestCaseRow) throws Exception {
		String sBrowserName;
		String sURL;
		String sActionSheet;
		try {
			ExcelWSheet = ExcelUtils.setExcelFile(Constant.File_TestData ,"Login");
			
			sBrowserName = ExcelUtils.getCellData(ExcelWSheet,iTestCaseRow, Constant.Col_Browser);
			sURL = ExcelUtils.getCellData(ExcelWSheet,iTestCaseRow, Constant.Col_param1);
			sURL = ExcelUtils.getCellData(ExcelWSheet,iTestCaseRow, Constant.Col_param1);
			Constant.URL=sURL;
			sActionSheet=ExcelUtils.getCellData(ExcelWSheet,iTestCaseRow, Constant.Col_param2);
			Constant.ActionSheet=sActionSheet;
			if (sBrowserName.equals("Mozilla")) {
				driver = new FirefoxDriver();
				Log.info("New driver instantiated");
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				Log.info("Implicit wait applied on the driver for 20 seconds");
				driver.get(sURL);
				Log.info("Web application launched successfully");
			
			}
			else if (sBrowserName.equals("Chrome")) {
				driver = new ChromeDriver();
				Log.info("New driver instantiated");
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				Log.info("Implicit wait applied on the driver for 20 seconds");
				driver.get(sURL);
				Log.info("Web application launched successfully");
			}
		} catch (Exception e) {
			Log.error("Class Utils | Method OpenBrowser | Exception desc : " + e.getMessage());
		}
		return driver;
	}

	public static String getTestCaseName(String sTestCase) throws Exception {
		String value = sTestCase;
		try {
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			Log.error("Class Utils | Method getTestCaseName | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static void mouseHoverAction(WebElement mainElement, String subElement) {
		Actions action = new Actions(driver);
		action.moveToElement(mainElement).perform();
		if (subElement.equals("Accessories")) {
			action.moveToElement(driver.findElement(By.linkText("Accessories")));
			Log.info("Accessories link is found under Product Category");
		}
		if (subElement.equals("iMacs")) {
			action.moveToElement(driver.findElement(By.linkText("iMacs")));
			Log.info("iMacs link is found under Product Category");
		}
		if (subElement.equals("iPads")) {
			action.moveToElement(driver.findElement(By.linkText("iPads")));
			Log.info("iPads link is found under Product Category");
		}
		if (subElement.equals("iPhones")) {
			action.moveToElement(driver.findElement(By.linkText("iPhones")));
			Log.info("iPhones link is found under Product Category");
		}
		action.click();
		action.perform();
		Log.info("Click action is performed on the selected Product Type");
	}

	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(Constant.UsrDir + Constant.Path_ScreenShot + sTestCaseName + ".jpg"));
		} catch (Exception e) {
			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "
					+ e.getMessage());
			throw new Exception();
		}
	}
}
