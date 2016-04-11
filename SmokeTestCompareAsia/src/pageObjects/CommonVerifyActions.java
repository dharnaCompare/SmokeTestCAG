package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utility.Log;
import utility.Utils;

public class CommonVerifyActions extends BaseClass {
	public CommonVerifyActions(WebDriver driver) {
		super(driver);
	}

	public static boolean performActionOnElement(WebElement elem, String action, String strData) throws Exception {
		try {
			boolean val = false;
			String attr = "";
			switch (action.toUpperCase()) {
			case "CLICK":
				elem.click();
				val = true;
				break;
			case "CLEAR":
				elem.clear();
				val = true;
				break;
			case "SUBMIT":
				elem.submit();
				val = true;
				break;
			case "EQUALS":
				Boolean i = elem.equals(strData);
				if (i!=null)
				val = true;
				break;
			case "GETATTRIBUTE":
				attr = elem.getAttribute(strData);
				if (attr!=null)
				val = true;
				break;
			case "GETCLASS":
				attr = elem.getClass().toString();
				if (attr!=null)
				val = true;
				break;
			case "GETTEXT":
				attr = elem.getText().toString();
				if (attr!=null)
				val = true;
				break;
			case "SENDKEYS":
				elem.sendKeys(strData);
				val = true;
				break;
			default:
				val = false;
				break;
			}
			if(val)
			loggerMethodSucess(elem, action);
			return val;
		} catch (Exception e) {
			loggerMethodFailure(elem, action,e);
			throw (e);
		}
	}

	public static Boolean verifyElement(WebElement elem, String action, String strtoCompare) throws Exception {
		boolean val = false;
		String attr = "";
		try {
			
			switch (action.toUpperCase()) {
			
			case "VERIFYGETTEXT":
				attr = elem.getText().toString();
				if (attr.toUpperCase().equals(strtoCompare.toUpperCase()))
				val = true;
				else
				val = false;
				break;		
			default:
				val = false;
				break;
			}
			if(val)
				loggerMethodSucess(elem, "Expected Value:"+attr+" EQUALS Actual Value:"+strtoCompare+"  VERIFYGETTEXT :: ");

			return val;
		} catch (Exception e) {
			loggerMethodFailure(elem, attr,e);
			throw (e);
		}
	}
	
	
	
	public static void loggerMethodSucess(WebElement elem, String action) throws Exception {
		Log.info("\n"+action + " is done on" + elem);
	}

	public static void loggerMethodFailure(WebElement elem, String action,Exception e) throws Exception {
		Log.error("\n"+action + " is not done on" + elem + ". Failed to Perform Action" + e.getMessage());
		Utils.takeScreenshot(driver,action + " is not done on"+". Failed to Perform Action");
	}
}
