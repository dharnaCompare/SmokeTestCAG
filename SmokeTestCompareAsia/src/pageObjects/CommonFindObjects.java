package pageObjects;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.thoughtworks.selenium.webdriven.commands.IsAlertPresent;

import utility.Constant;

import utility.Log;
import utility.Utils;

public class CommonFindObjects extends BaseClass {
	private static WebElement element = null;
	private static String strlinkText = null;
	private static Exception NullPointerException;

	public CommonFindObjects(WebDriver driver) {
		super(driver);
	}

	/*---------------****byLinkTextMethod****---------------*/
	public static WebElement byLinkTextMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.linkText(varValue));
			if (element != null)
				strlinkText = element.getText();
			loggerMethodSucess(strlinkText);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****byIdMethod****---------------*/
	public static WebElement byIdMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.id(varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****byClassNameMethod****---------------*/
	public static WebElement byClassNameMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.className(varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****byXpathMethod****---------------*/
	public static WebElement byXpathMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.xpath("//" + varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure("Object is not found on Page", e);
			throw (e);
		}
		return element;
	}

	/*---------------****byCssPathMethod****---------------*/
	public static WebElement byCssPathMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.cssSelector(varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****byNameMethod****---------------*/
	public static WebElement byNameMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.name(varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****byPartialLinkTextMethod****---------------*/
	public static WebElement byPartialLinkTextMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.partialLinkText(varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****byTagNameMethod****---------------*/
	public static WebElement byTagNameMethod(String varValue) throws Exception {
		try {
			element = driver.findElement(By.tagName(varValue));
			loggerMethodSucess(varValue);
		} catch (Exception e) {
			loggerMethodFailure(varValue, e);
			throw (e);
		}
		return element;
	}

	/*---------------****closeLatestBrowser****---------------*/
	public static void closeLatestBrowser(String beforePopup) throws Exception {
		try {
			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
			driver.close(); // this will close new opened window
			Thread.sleep(2000);
			// switch back to main window using this code
			driver.switchTo().window(beforePopup);
			loggerMethodSucess(beforePopup);
		} catch (Exception e) {
			loggerMethodFailure(beforePopup, e);
			throw (e);
		}
	}

	/*---------------****closeLatestBrowser****---------------*/
	public static void switchToLatestBrowser(String beforePopup) throws Exception {
		try {
			// Switch to new window opened
			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			loggerMethodSucess(beforePopup);
		} catch (Exception e) {
			loggerMethodFailure(beforePopup, e);
			throw (e);
		}
	}

	/*---------------****closeLatestBrowser****---------------*/
	public static void switchToHomeBrowser(String beforePopup) throws Exception {
		try {
			driver.switchTo().window(beforePopup);
			loggerMethodSucess(beforePopup);
		} catch (Exception e) {
			loggerMethodFailure(beforePopup, e);
			throw (e);
		}
	}

	/*---------------****threadSleep****---------------*/
	public static void threadSleep(int timer) throws Exception {
		try {
			Thread.sleep(timer);
			loggerMethodSucess("threadSleep");
		} catch (Exception e) {
			loggerMethodFailure("threadSleep", e);
			throw (e);
		}
	}

	/*---------------****getAllElements****---------------*/
	public static List<WebElement> getAllElements(String varValueParent, String varValueChild) throws Exception {
		List<WebElement> descriptionEle = null;
		try {
			WebElement parentEle = CommonFindObjects.byXpathMethod(varValueParent);
			descriptionEle = parentEle.findElements(By.tagName(varValueChild));
			// System.out.println(descriptionEle.size());
			loggerMethodSucess(varValueParent + varValueChild);
		} catch (Exception e) {
			loggerMethodFailure(varValueParent + varValueChild, e);
			throw (e);
		}
		return descriptionEle;
	}

	/*---------------****getParentElements****---------------*/
	public static List<WebElement> getParentElements(String varValueParent) throws Exception {
		List<WebElement> parentEle = null;
		try {
			parentEle = driver.findElements(By.xpath("//" + varValueParent));
			// descriptionEle =
			// parentEle.findElements(By.tagName(varValueChild));
			// System.out.println(descriptionEle.size());
			loggerMethodSucess(varValueParent + varValueParent);
		} catch (Exception e) {
			loggerMethodFailure(varValueParent + varValueParent, e);
			throw (e);
		}
		return parentEle;
	}

	/*---------------****clickandVerifyFooter****---------------*/
	public static void clickandCheckChildObjects(String varValue, String beforePopup) throws Exception {
		List<WebElement> descriptionEle = null;
		Actions action = new Actions(driver);
		Exception excep = new Exception("Element not listed in switch Case.");
		int i = 0;
		int menuItem = 0;
		String URL = null;
		String strLink = null;
		String bodyText = null;
		String strActual = null;
		String strExpected = null;
		String alertText = null;
		int indexofString = 0;
		try {
			Boolean flag = false;
			String[] spropertyVal = varValue.split(";;");
			for (i = 0; i < Integer.parseInt(spropertyVal[2]); i++) {
				URL = "";
				strLink = "";
				bodyText = "";
				strActual = "";
				strExpected = "";
				alertText = "";
				descriptionEle = CommonFindObjects.getAllElements(spropertyVal[0], spropertyVal[1]);
				strLink = descriptionEle.get(i).getAttribute("href");
				String strClassElem = descriptionEle.get(i).getAttribute("class");
				String strText = descriptionEle.get(i).getText();
				System.out.println(strLink + strText + strClassElem + flag + strLink.length());
				if (spropertyVal.length == 4) {
					if (strLink.contains(Constant.URL) && spropertyVal[3].toUpperCase().equals("HOME")) {
						strText = "Home";
					}
					if (strLink.contains("/credit-card") && spropertyVal[3].toUpperCase().equals("HEADCC")) {
						menuItem = 1;
					}
					if (spropertyVal[3].toUpperCase().equals("HEADPL")) {
						menuItem = 2;
					}
					if (spropertyVal[3].toUpperCase().equals("HEADBLOG")) {
						menuItem = 3;
					}
					if (spropertyVal[3].toUpperCase().equals("HEADHELP")) {
						menuItem = 4;
					}
					if (strLink.contains("/credit-card") && spropertyVal[3].toUpperCase().equals("HEADCC") && i == 0) {
						strText = "Credit Cards";
					}
					if (strLink.contains("credit-card/best-deals") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADCC"))) {
						strText = "Credit Card Comparison";
					}
					if (strLink.contains("credit-card/guide") && (spropertyVal[3].toUpperCase().equals("HEADCC")
							|| spropertyVal[3].toUpperCase().equals("BODY"))) {
						strText = "Credit Card Guide";
					}
					if (strLink.contains("credit-card/faq") && spropertyVal[3].toUpperCase().equals("HEADCC")) {
						strText = "Credit Card FAQs";
					}
					if (strLink.contains("credit-card/glossary") && spropertyVal[3].toUpperCase().equals("HEADCC")) {
						strText = "Credit Card Glossary";
					}
					if (strLink.contains("credit-card/banks") && spropertyVal[3].toUpperCase().equals("HEADCC")) {
						strText = "Banks";
					}
					if (strLink.contains("/personal-loan") && spropertyVal[3].toUpperCase().equals("HEADPL")
							&& i == 0) {
						strText = "Personal Loans";
					}
					if (strLink.contains("personal-loan/instalment") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADPL"))) {
						strText = "Personal Loan Comparison";
					}
					if (strLink.contains("personal-loan/guide") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADPL"))) {
						strText = "Personal Loan Guide";
					}
					if (strLink.contains("personal-loan/faq") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADPL"))) {
						strText = "Personal Loan FAQs";
					}
					if (strLink.contains("personal-loan/glossary") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADPL"))) {
						strText = "Personal Loan Glossary";
					}
					if (strLink.contains("personal-loan/providers") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADPL"))) {
						strText = "Providers";
					}
					if (strLink.contains("/help") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADHELP"))) {
						strText = "Help";
					}
					if (strLink.contains("/contact-us") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADHELP"))) {
						strText = "Contact Us";
					}
					if (strLink.contains("/about-us") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADHELP"))) {
						strText = "About Us";
					}
					if (strLink.contains("/product-guide") && (spropertyVal[3].toUpperCase().equals("BODY")
							|| spropertyVal[3].toUpperCase().equals("HEADHELP"))) {
						strText = "Product Guide";
					}
					if (strLink.contains("blog") && (spropertyVal[3].toUpperCase().equals("BODY")||
							spropertyVal[3].toUpperCase().equals("BLOG")	||  spropertyVal[3].toUpperCase().equals("HEADBLOG"))) {
						strText = "Blog";
					}
					if (strLink.contains("blog/") && spropertyVal[3].toUpperCase().equals("BLOG")) {
						strText = "Blog Body";
					}
				}
				if (strClassElem.contains("socialmedia") || strClassElem.contains("fb") || strLink.contains("facebook")
						|| strLink.contains("google")) {
					descriptionEle.get(i).click();
					Thread.sleep(2000);
					CommonFindObjects.switchToLatestBrowser(beforePopup);
					URL = driver.getCurrentUrl();
					CommonFindObjects.switchToHomeBrowser(beforePopup);
					if (URL.contains("blog")) {
						Assert.assertEquals(URL, strLink + "/");
					} else {
						indexofString = strLink.indexOf("com/");
						strLink = strLink.substring(0, indexofString);
						Assert.assertEquals(URL.contains(strLink), true);
					}
					flag = true;
					CommonFindObjects.closeLatestBrowser(beforePopup);
				}
				if (spropertyVal[0].contains("flag")) {
					descriptionEle.get(i).click();
					Thread.sleep(2000);
					URL = driver.getCurrentUrl();
					Assert.assertEquals(URL, strLink);
					driver.get(Constant.URL);
					flag = true;
				}
				if (strLink != null && !strLink.equals("javascript:void(0);") && flag == false) {
					// to Expand menu list
					if (menuItem != 0) {
						action.moveToElement(
								driver.findElements(By.xpath("//a[@class='navi-block__url js-navi-block__url']"))
										.get(menuItem))
								.click().build().perform();
					}
					// Skip the click in Case of Blog as it's already done in
					// above if case.
					if (menuItem != 3) {
						descriptionEle.get(i).click();
					}
					Thread.sleep(3000);
					URL = driver.getCurrentUrl();
					bodyText = driver.findElement(By.tagName("body")).getText().toUpperCase();
					switch (strText.trim()) {
					case "Credit Cards":
						strExpected = "What kind of credit card do you need?".toUpperCase();
						break;
					case "Credit Card Comparison":
						strExpected = "The Best Credit Card Deals".toUpperCase();
						break;
					case "Credit Card Guide":
						strExpected = "Guide to Credit Cards in Singapore".toUpperCase();
						break;
					case "Credit Card FAQs":
						strExpected = "FAQs About Credit Cards in".toUpperCase();
						break;
					case "Banks":
						strExpected = "Our Credit Card Partners".toUpperCase();
						break;
					case "Credit Card Glossary":
						strExpected = "Credit Card Glossary of Terms".toUpperCase();
						break;
					case "Personal Loans":
						strExpected = "Need a personal loan right this minute?".toUpperCase();
						break;
					case "Personal Loan Comparison":
						strExpected = "Achieve Your Goals with the Help of a Personal Loan".toUpperCase();
						break;
					case "Personal Loan Guide":
						strExpected = "SingSaver.com.sg’s Guide to Personal Loans".toUpperCase();
						break;
					case "Personal Loan FAQs":
						strExpected = "FAQs About Personal Loans in Singapore".toUpperCase();
						break;
					case "Personal Loan Glossary":
						strExpected = "Personal Loans Glossary of Terms".toUpperCase();
						break;
					case "Providers":
						strExpected = "our personal loan providers".toUpperCase();
						break;
					case "Blog":
						Thread.sleep(1000);
						bodyText = driver.findElement(By.tagName("body")).getText().toUpperCase();
						strExpected = "Ways to Save".toUpperCase();
						strLink = strLink + "/";
						if (isAlertPresent())
							alertText = closeAlertAndGetItsText();
						break;
					case "Blog Body":
						Thread.sleep(1000);
						bodyText = driver.findElement(By.tagName("body")).getText().toUpperCase();
						strExpected = "Ways to Save".toUpperCase();
						if (isAlertPresent())
							alertText = closeAlertAndGetItsText();
						break;
					case "Help":
						strExpected = "Help Hub".toUpperCase();
						break;
					case "Contact Us":
						strExpected = "Do you have any questions? Send us a message!".toUpperCase();
						break;
					case "About Us":
						strExpected = "Our Mission".toUpperCase();
						break;
					case "Product Guide":
						strExpected = "How to find the credit card for you?".toUpperCase();
						break;
					case "Guide":
						strExpected = "comprehensive guides to financial products".toUpperCase();
						break;
					case "Affiliates":
						strExpected = "Affiliate Program".toUpperCase();
						break;
					case "Press":
						strExpected = "For any interview requests or questions".toUpperCase();
						break;
					case "Careers":
						strExpected = "Why join us?".toUpperCase();
						break;
					case "Privacy Policy":
						strExpected = "Information we collect".toUpperCase();
						break;
					case "Sitemap":
						strExpected = "Aloitus".toUpperCase();
						break;
					case "Terms and Conditions":
						strExpected = "terms and conditions stated here".toUpperCase();
						break;
					case "Terms of Use":
						strExpected = "terms and conditions stated here".toUpperCase();
						break;
					case "Home":
						strExpected = "Financial Comparison Site".toUpperCase();
						break;
					default:
						loggerMethodFailure("Deafult Case for Switch.Inner Text Not matching" + varValue, excep);
						flag = true;
						break;
					}
					indexofString = bodyText.indexOf(strExpected);
					strActual = bodyText.substring(indexofString, indexofString + strExpected.length());
					Assert.assertEquals(URL, strLink);
					Assert.assertEquals(strActual, strExpected);
					driver.get(Constant.URL);
					Thread.sleep(1000);
				}
				if (URL != "" || URL != null)
					loggerMethodSucess("clickandVerifyObjects || " + "\n" + "|||||||Actual: " + URL + "||" + strActual
							+ "\n" + "|||||||" + "Expected :" + strLink + "||" + strExpected);
				else
					loggerMethodSucess("clickandVerifyObjects || " + "This is not a valid link");
			}
		} catch (Exception e) {
			loggerMethodFailure("clickandVerifyFooter || " + "\n" + "|||||||Actual: " + URL + "||" + strActual + "\n"
					+ "|||||||" + "Expected :" + strLink + "||" + strExpected, e);
			throw (e);
		}
	}

	/*---------------****closeAlertAndGetItsText****---------------*/
	public static void getChildElementsandClickByIndex(String varValue) throws Exception {
		List<WebElement> descriptionEle = null;
		int iSize = 0;
		try {
			String[] spropertyVal = varValue.split(";;");
			String varValueParent = spropertyVal[0];
			// String varValueChild = spropertyVal[1];
			int elemIndex = Integer.parseInt(spropertyVal[1]);
			descriptionEle = CommonFindObjects.getParentElements(varValueParent);
			WebElement elem = descriptionEle.get(elemIndex);
			iSize = descriptionEle.size();
			elem.click();
			Thread.sleep(2000);
			loggerMethodSucess("getChildElementsandClickByIndex  || " + varValue + "Element is clicked.");
		} catch (Exception e) {
			loggerMethodFailure("getChildElementsandClickByIndex || " + iSize, e);
			throw (e);
		}
	}
	/*---------------****goToUrl****---------------*/
	public static void goToHomeUrl(String varValue) throws Exception {
		try {
			driver.get(Constant.URL);
			Thread.sleep(2000);
			loggerMethodSucess("goToHomeUrl || " + varValue + "Sucess.");
		} catch (Exception e) {
			loggerMethodFailure("goToHomeUrl || " + varValue, e);
			throw (e);
		}
	}

	/*---------------****checkValidLinks****---------------*/
	public static void checkValidLinks(String strParam2, String beforePopup) throws Exception {
		try {
			boolean isValid = false;
			List<WebElement> descriptionEle = driver.findElements(By.tagName("a"));
			// System.out.println(descriptionEle.size() +
			// descriptionEle.size());
			for (int i = 0; i < descriptionEle.size(); i++) {
				String strLinks = descriptionEle.get(i).getAttribute("href");
				if (descriptionEle != null) {
					isValid = CommonFindObjects.getResponseCode(strLinks);
					// Print message based on value of isValid which Is returned
					// by getResponseCode function.
					if (isValid) {
						System.out.println("Valid Link:" + strLinks);
						System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
						System.out.println();
					} else {
						System.out.println("Broken Link ------> " + strLinks);
						System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
						System.out.println();
					}
				} else {
					// If <a> tag do not contain href attribute and value
					// then print this message
					System.out.println("<a> tag do not contain href attribute and value");
					System.out.println("----------XXXX-----------XXXX----------XXXX-----------XXXX----------");
					System.out.println();
					continue;
				}
			}
		} catch (Exception e) {
			Log.error("Exception in threadSleep");
			throw (e);
		}
	}

	/*---------------****getResponseCode****---------------*/
	// Function to get response code of link URL.
	// Link URL Is valid If found response code = 200. //Link URL Is Invalid If
	// found response code = 404 or 505.
	public static boolean getResponseCode(String chkurl) {
		boolean validResponse = false;
		try {
			// Get response code of URL
			// chkurl="http://staging.moneyguru.co.th/en";
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(chkurl);
			HttpResponse urlresp = client.execute(new HttpGet(chkurl));
			// HttpResponse urlresp = new DefaultHttpClient().execute(new
			// HttpGet(chkurl));
			int resp_Code = urlresp.getStatusLine().getStatusCode();
			System.out.println("Response Code Is : " + resp_Code);
			if ((resp_Code == 404) || (resp_Code == 505)) {
				validResponse = false;
			} else {
				validResponse = true;
			}
		} catch (Exception e) {
		}
		return validResponse;
	}

	/*---------------****isAlertPresent****---------------*/
	public static boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	/*---------------****closeAlertAndGetItsText****---------------*/
	public static String closeAlertAndGetItsText() {
		boolean acceptNextAlert = true;
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	/*---------------****loggerMethodSucess****---------------*/
	public static void loggerMethodSucess(String varValue) throws Exception {
		Log.info("\n" + varValue + " is verified");
	}

	/*---------------****loggerMethodFailure****---------------*/
	public static void loggerMethodFailure(String varValue, Exception e) throws Exception {
		Log.error("\n" + varValue + " is not verified" + e.getMessage());
		// Utils.takeScreenshot(driver, "loggerMethodFailure");
	}
}
