package com.oddsking.pageObjects;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.oddskings.utilFiles.SeleniumUtils;

public class FilterResultsPage {

	WebDriver driver;
	SeleniumUtils util;

	public FilterResultsPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		util = new SeleniumUtils(driver);
	}

	@FindBy(xpath = "//a[@data-actionable='Lotto.SelectLottoBanner.Results']")
	WebElement resultsButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//button[contains(text(),'View Filtered Results')]")
	WebElement viewFilteredResultsButton;

	@FindBy(xpath = "//button[@class='react-calendar__navigation__arrow react-calendar__navigation__next-button']")
	WebElement navigationButton;

	@FindBy(xpath = "//div[@class='_5ubxge']")
	List<WebElement> dateLabels;

	@FindBy(xpath = "//*[@id='page-inner-content']/div/div/div[2]/div[3]/div[2]/div[2]")
	WebElement fromCalenderButton;

	public void clickResultButton() {
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("arguments[0].scrollIntoView(true);", resultsButton);
		util.clickElement(resultsButton);
	}

	public void clickfromCalenderButton() {
		JavascriptExecutor js3 = (JavascriptExecutor) driver;
		js3.executeScript("arguments[0].scrollIntoView(true);", fromCalenderButton);
		util.clickElement(fromCalenderButton);
	}

	public void clickNavigationButton() {
		util.clickElement(navigationButton);
	}

	public void selectDate(int date7DaysBeforeIs) {
		driver.findElement(By.xpath("//button/abbr[contains(text()," + date7DaysBeforeIs + ")]")).click();
	}

	public void clickDoneButton() {
		util.clickElement(doneButton);
	}

	public void clickviewFilteredResultsButton() {
		util.clickElement(viewFilteredResultsButton);
	}

	public ArrayList<String> dateElementList() {
		ArrayList<String> webList = new ArrayList<String>();
		List<WebElement> ele;
		ele=driver.findElements(By.xpath("//div[@class='_5ubxge']"));
		for(WebElement webele:ele )
        {
			String text=util.getText(webele);
        	webList.add(text.substring(0, 6));
        }
		return webList;
	}
	public boolean isCalenderElementPresent()
	{
		Boolean headerValue=util.isElementDisplayed(fromCalenderButton);
		return headerValue;
	}
}
