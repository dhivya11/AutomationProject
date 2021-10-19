
package com.oddskings.testScripts;

import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.oddskings.utilFiles.*;
import com.oddsking.pageObjects.RegistrationPage;
import com.oddskings.baseClassCode.*;

public class RegistrationPageValidation extends BaseClass {
	WebDriver driver;
	SeleniumUtils su;
	ExcelUtils eu;
	FileMethodUtils fu;
	RegistrationPage r;
	ScreenshotUtil sc;
	private static XSSFSheet ExcelWSheetRead;
	String scrPath;

	@BeforeClass
	public void setup() {
		logger.info("---------------------------Start of TestCase-----------------------------------");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		su = new SeleniumUtils(driver);
		eu = new ExcelUtils();
		fu = new FileMethodUtils();
		r = new RegistrationPage(driver);

		String readExcelPath = System.getProperty("user.dir") + ReadProperty("readExcelPath");
		try {
			ExcelWSheetRead = ExcelUtils.setExcelFile(readExcelPath, ReadProperty("registrationPageSheet"));
			System.out.println("testResultFilePath" + testResultFilePath);
			ExcelUtils.setExcelFile(testResultFilePath, ReadProperty("registrationPageTestResultSheet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(ReadProperty("applicationUrl"));
		scrPath = System.getProperty("user.dir") + ReadProperty("screenshotPath");
	}

	@Test(priority = 1)
	public void launchUrl() throws Exception {
		try {
			if (driver.getTitle().contentEquals(ReadObjectProperty("expectedTitle"))) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 1, 3, testResultFilePath);
				logger.info("Launching URL - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 1, 3, testResultFilePath);
				logger.error("Launching URL - Passed");
				ScreenshotUtil.takeSnapShot(driver, scrPath + "launchURL.png");
				Assert.fail();
			}
			r.clickJoinButton();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 2)
	public void enterAccountPageDetails() throws Exception {
		try {
			r.setEmailAddress(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 0));
			r.setUserName(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 1));
			r.setPassword(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 2));
			r.clickCookiesButton();
			r.agreePolicy();
			r.clickContinueButton();

			if (r.isTitleElementPresent()) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 2, 3, testResultFilePath);
				logger.info("Enter Account details page - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 2, 3, testResultFilePath);
				logger.error("Enter Account details page - Failed");
				ScreenshotUtil.takeSnapShot(driver, scrPath + "AccountPage.png");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 3)
	public void enterPersonalPageDetails() throws Exception {
		try {
			r.setTitle(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 3));
			r.setFirstName(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 4));
			r.setLastName(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 5));
			r.setDay(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 6));
			r.setMonth(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 7));
			r.setYear(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 8));
			r.clickContinueButton();

			if (r.isTelephoneElementPresent()) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 3, 3, testResultFilePath);
				logger.info("Enter Personal details page - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 3, 3, testResultFilePath);
				logger.error("Enter Personal details page - Failed");
				ScreenshotUtil.takeSnapShot(driver, scrPath + "PersonalDetails.png");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 4)
	public void enterContactPageDetails() throws Exception {
		try {
			r.setTelephoneNumber(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 9));
			r.selectSecurityQuestion(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 10));
			r.setSecurityAnswer(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 11));
			r.clickContinueButton2();

			if (r.isAddressElementPresent()) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 4, 3, testResultFilePath);
				logger.info("Enter Contact details page - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 4, 3, testResultFilePath);
				logger.error("Enter Contact details page - Failed");
				ScreenshotUtil.takeSnapShot(driver, scrPath + "ContactDetails.png");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 5)
	public void enterAddressPageDetails() throws Exception {
		try {
			r.setAddress(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 12));
			r.clickContinueButton();
			if (r.isEmailCheckboxElementPresent()) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 5, 3, testResultFilePath);
				logger.info("Enter Address details page - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 5, 3, testResultFilePath);
				logger.error("Enter Address details page - Failed");
				ScreenshotUtil.takeSnapShot(driver, scrPath + "AddressDetails.png");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 6)
	public void enterSettingsPageDetails() throws Exception {
		try {
			r.clickEmailCheckBox();
			r.clickTextCheckBox();
			r.selectCurrencyDropdown(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 13));
			r.setDepositLimit(ReadObjectProperty("depositLimitValue"),
					ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 14));
			r.setRealityCheck(ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, 1, 15));
			r.registerUser();
			if (r.isaddPaymentButtonElementPresent()) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 6, 3, testResultFilePath);
				logger.info("User Registration - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 6, 3, testResultFilePath);
				logger.error("User Registration - Failed");
				ScreenshotUtil.takeSnapShot(driver, scrPath + "SettingsPage.png");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@AfterClass
	public void quit() {
		driver.close();
		logger.info("---------------------------End of TestCase-----------------------------------");
	}

}
