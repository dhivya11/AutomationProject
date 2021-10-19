package com.oddskings.testScripts;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.oddskings.utilFiles.*;
import com.oddsking.pageObjects.FilterResultsPage;
import com.oddskings.baseClassCode.*;

public class FilterResultsValidation extends BaseClass {
	WebDriver driver1;
	SeleniumUtils su;
	ExcelUtils eu;
	FileMethodUtils fu;
	DateTimeUtils d;
	FilterResultsPage f;
	ArrayList<String> webList = new ArrayList<String>();
	ArrayList<String> list = new ArrayList<String>();
	String scrPath;

	@BeforeClass
	public void setup() {
		logger.info("---------------------------Start of TestCase-----------------------------------");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
		driver1 = new ChromeDriver();
		su = new SeleniumUtils(driver1);
		eu = new ExcelUtils();
		fu = new FileMethodUtils();
		f = new FilterResultsPage(driver1);
		d = new DateTimeUtils();

		String readExcelPath = System.getProperty("user.dir") + ReadProperty("readExcelPath");
		try {
			ExcelUtils.setExcelFile(readExcelPath, ReadProperty("registrationPageSheet"));
			ExcelUtils.setExcelFile(testResultFilePath, ReadProperty("FilterResultsTestResultSheet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver1.get(ReadProperty("filterValidationUrl"));
		scrPath = System.getProperty("user.dir") + ReadProperty("screenshotPath");
	}

	@Test(priority = 1)
	public void clickResult() throws Exception {
		try {
			f.clickResultButton();
			if (f.isCalenderElementPresent()) {
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 1, 3, testResultFilePath);
				logger.info("User has entered filter page - Passed");
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 1, 3, testResultFilePath);
				logger.error("User has entered filter page - Failed");
				ScreenshotUtil.takeSnapShot(driver1, scrPath + "FilterPage.png");
				Assert.fail();
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 2)
	public void filterResult() throws Exception {
		try {
			int dateTodayIs = d.dateToday();
			int date7DaysBeforeIs = d.dateLast7thday();
			list = d.listOfLast7Days();
			f.clickfromCalenderButton();
			if (dateTodayIs < 15) {
				f.clickNavigationButton();
				f.selectDate(date7DaysBeforeIs);
			} else {
				f.selectDate(date7DaysBeforeIs);
			}
			f.clickDoneButton();
			f.clickviewFilteredResultsButton();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@Test(priority = 3)
	public void validateResult() throws Exception {
		try {
			Thread.sleep(1000);
			webList = f.dateElementList();
			for (int i = 0; i < webList.size(); i++) {
				if (list.contains(webList.get(i))) {
					logger.info("Filter has happened correctly for element " + webList.get(i));
					ExcelUtils.setCellData(ReadProperty("passedStatus"), 2, 3, testResultFilePath);
				} else {
					logger.error("Filter has not happened correctly for element " + webList.get(i));
					ExcelUtils.setCellData(ReadProperty("failedStatus"), 2, 3, testResultFilePath);
					ScreenshotUtil.takeSnapShot(driver1, scrPath + "ValidateResults.png");
					Assert.fail();
				}
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	@AfterClass
	public void quit() {
		driver1.close();
		logger.info("---------------------------End of TestCase-----------------------------------");
	}
}
