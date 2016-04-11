package appModules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pageObjects.*;

import utility.Log;
import utility.Utils;

public class CommonFindMethods extends BaseClass {
	public static WebElement elem;
	public static boolean valOfAction;
	static int i = 0;
	static String beforePopup;
	
	public CommonFindMethods(WebDriver driver) {
		super(driver);
	}
	// iTestcaseRow is the row number of our Testcase name in the Test Data
	// sheet
	public static void executeMethod(String stepNo, String stepName, String strbyMethod, String strObjIdentifier,
			String strParam1, String strAction, String strDataId, String strFunction,String strArgs, String beforePopup) throws Exception {
		// Clicking on the My Account link on the Home Page
		// HomePage().
		try {
			loggerMethodStart(stepNo + "::" + stepName);
			boolean val = false;
			String attr = "";
			if(strbyMethod=="")
			{
				strbyMethod=strFunction;
			}

			switch (strbyMethod) {
			case "byLinkTextMethod":
				elem = CommonFindObjects.byLinkTextMethod(strObjIdentifier);
				val = true;
				break;
			/*
			 * case "byClassMethod": elem =
			 * CommonFindObjects.byClassMethod(strIdentifier); val = true;
			 * break;
			 */
			case "byClassNameMethod":
				elem = CommonFindObjects.byClassNameMethod(strObjIdentifier);
				val = true;
				break;
			case "byCssPathMethod":
				elem = CommonFindObjects.byCssPathMethod(strObjIdentifier);
				val = true;
				break;
			case "byIdMethod":
				elem = CommonFindObjects.byIdMethod(strObjIdentifier);
				val = true;
				break;
			case "byPartialLinkTextMethod":
				elem = CommonFindObjects.byPartialLinkTextMethod(strObjIdentifier);
				val = true;
				break;
			case "bytagNameMethod":
				elem = CommonFindObjects.byTagNameMethod(strObjIdentifier);
				val = true;
				break;
			case "byXpathMethod":
				elem = CommonFindObjects.byXpathMethod(strObjIdentifier);
				val = true;
				break;
			case "closeLatestBrowser":
				CommonFindObjects.closeLatestBrowser(beforePopup);
				val = true;
				break;
			case "threadSleep":
				CommonFindObjects.threadSleep(Integer.parseInt(strArgs.replace(".0", "")));
				val = true;
				break;
		case "clickandCheckChildObjects":
				CommonFindObjects.clickandCheckChildObjects(strArgs,beforePopup);
				val = true;
				break;
		case "getChildElementsandClickByIndex":
			CommonFindObjects.getChildElementsandClickByIndex(strArgs);
			val = true;
			break;
		case "goToHomeURL":
			CommonFindObjects.goToHomeUrl(strArgs);
			val = true;
			break;
			default:
				val = false;
				break;
			}
			if (strFunction.toUpperCase().contains("VERIFY")) {
				valOfAction = CommonVerifyActions.verifyElement(elem, strFunction, strDataId);
				loggerMethodSucess(elem, stepNo + "::" + stepName);
			} else{
				valOfAction = CommonVerifyActions.performActionOnElement(elem, strAction, strDataId);
				loggerMethodSucess(elem, stepNo + "::" + stepName);
			}
			loggerMethodEnd(stepNo + "::" + stepName);
		} catch (Exception e) {
			loggerMethodFailure(elem, stepNo + "::" + stepName);
			throw (e);
		}
	}

	public static void loggerMethodSucess(WebElement varValue, String strStep) throws Exception {
		Log.info("\n"+strStep + " " + " IS SET TO PASSED");
	}
	public static void loggerMethodStart(String strStep) throws Exception {
		Log.info("\n"+"***********************START OF STEP***********************  " + strStep);
	}
	public static void loggerMethodEnd(String strStep) throws Exception {
		Log.info("\n"+"***********************END OF STEP***********************    " + strStep);
	}
	public static void loggerMethodFailure(WebElement varValue, String strStep) throws Exception {
		Log.error("\n"+strStep + " " + " . IS SET TO FAILED");
		Utils.takeScreenshot(driver, strStep.replace("::", ""));
	}


	
}
