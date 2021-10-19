package com.oddsking.pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.oddskings.utilFiles.SeleniumUtils;

public class RegistrationPage {
	
	WebDriver driver;
	SeleniumUtils util;

	public RegistrationPage(WebDriver rdriver) {
		driver = rdriver;
		PageFactory.initElements(rdriver, this);
		 util=new SeleniumUtils(driver);
	}

	@FindBy(xpath="//a[@class='_znnu0ea']")
	WebElement joinButton;
	
	@FindBy(xpath="//*[@id='RegistrationPage.AccountSection.email']")
	WebElement emailTextBox;
	
	@FindBy(xpath="//*[@id='RegistrationPage.AccountSection.username']")
	WebElement usernameTextBox;
	
	@FindBy(xpath="//*[@id='RegistrationPage.AccountSection.password']")
	WebElement passwordTextBox;
	
	@FindBy(xpath="//body/div[@id='root']/div[@id='page-wrapper']/div[2]/div[1]/div[1]/div[1]/div[2]/button[1]")
	WebElement cookiesButton;
	
	//@FindBy(xpath="//*[@id='agree_terms-checkbox']/following-sibling::span")
	@FindBy(xpath="//*[@id='agree_terms-checkbox']/parent::div")
	WebElement agreeCheckBox;
	
	@FindBy(xpath="//a[contains(text(),'Terms & Conditions')]")
	WebElement termsAndConditionLink;
	
	@FindBy(xpath="//button[contains(text(),'Done')]")
	WebElement doneButton;
	
	@FindBy(xpath="//button[@class='_nljctal']")
	WebElement continueButton;
	
	@FindBy(xpath="//button[@name='Miss']")
	WebElement titleOption;
	
	@FindBy(xpath="//input[@name='firstName']")
	WebElement firstNameTextBox;
	
	@FindBy(xpath="//input[@name='lastName']")
	WebElement lastNameTextBox;
	
	@FindBy(xpath="//input[@id='RegistrationPage.DateOfBirthInput.day']")
	WebElement dayTextBox;
	
	@FindBy(xpath="(//input[@id='RegistrationPage.DateOfBirthInput.day']/following-sibling::input)[1]")
	WebElement monthTextBox;
	
	@FindBy(xpath="(//input[@id='RegistrationPage.DateOfBirthInput.day']/following-sibling::input)[2]")
	WebElement yearTextBox;
	
	@FindBy(xpath="//div[@class='_16qnp1m']/div/div[2]/form/div/div/input[@id='RegistrationPage.TelephoneNumberInput.telephone.desktop-telephone']")
	WebElement telephoneNumberTextBox;
	
	@FindBy(xpath="//div[@class='_16qnp1m']/div/div[2]/form/div/select")
	WebElement secQuestionDropDown;
	
	@FindBy(xpath="//div[@class='_16qnp1m']/div/div[2]/form/div[3]/div/input")
	WebElement secAnswerTextBox;
	
	@FindBy(xpath="(//div[@class='_16qnp1m']/div/div[2]/div/button)[2]")
	WebElement continueButton2;	
	
	@FindBy(xpath="//input[@id='search']")
	WebElement addressSearchBox;
	
	@FindBy(xpath="(//div[@data-actionable='RegistrationPage.PromotionsSelector.email'])[1]")
	WebElement emailCheckBox;
	
	@FindBy(xpath="(//div[@data-actionable='RegistrationPage.PromotionsSelector.text'])[2]")
	WebElement textCheckBox;
	
	@FindBy(xpath="//select[@id='RegistrationPage.Dropdown.currency']")
	WebElement currencyDropDown;
	
	@FindBy(xpath="//div[@data-actionable='Registration.SettingTile.DepositLimit']")
	WebElement setDepositLimitButton;
	
	@FindBy(xpath="//input[@id='Registration.DepositLimitModal.amount']")
	WebElement depositAmountTextBox;
	
	@FindBy(xpath="//button[@name='Daily']")
	WebElement depositAmountDuration;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement saveButton;
	
	@FindBy(xpath="//div[@data-actionable='Registration.SettingTile.RealityCheck']")
	WebElement setRealityCheckButton;
	
	@FindBy(xpath="//select[@data-actionable='Registration.RealityCheckSettingModal.Dropdown']")
	WebElement setRealityCheckDropDown;
	
	@FindBy(xpath="//button[contains(text(),'Register')]")
	WebElement registerButton;
	
	@FindBy(xpath="//button[@data-actionable='RegistrationPage.RegisterHeader.mobile.close']/following-sibling::h3")
	WebElement myAccountHeader;
	
	@FindBy(xpath="//button[@data-actionable='RegistrationPage.RegistrationSuccess.payment.desktop']")
	WebElement addPaymentButton;
	
	public void clickJoinButton()
	{
		util.clickElement(joinButton);
	}
	public void setEmailAddress(String emailAddrs)
	{
		util.enterElement(emailTextBox, emailAddrs );
	}
	public void setUserName(String userName) 
	{
		util.enterElement(usernameTextBox, userName );	
	}
	public void setPassword(String passWd) throws InterruptedException 
	{
		util.enterElement(passwordTextBox, passWd );		
		Thread.sleep(1000);
	}
	public void clickCookiesButton() 
	{
		util.clickElement(cookiesButton);		
	}
	public void agreePolicy() throws InterruptedException 
	{
	    util.clickElement(agreeCheckBox);
	}
	public void clickContinueButton() 
	{
    	JavascriptExecutor js2 = (JavascriptExecutor) driver;
    	js2.executeScript("arguments[0].scrollIntoView(true);", continueButton);
		util.clickElement(continueButton);		
	}
	public void setTitle(String title) 
	{
		util.enterElement(titleOption, title );		
	}
	public void setFirstName(String firstname) 
	{
		util.enterElement(firstNameTextBox, firstname );		
	}
	public void setLastName(String lastname) 
	{
		util.enterElement(lastNameTextBox, lastname );		
	}
	public void setDay(String day) 
	{
		util.enterElement(dayTextBox, day );		
	}
	public void setMonth(String month) 
	{
		util.enterElement(monthTextBox, month );		
	}
	public void setYear(String year) 
	{
		util.enterElement(yearTextBox, year );		
	}
	public void setTelephoneNumber(String number) 
	{
		util.enterElement(telephoneNumberTextBox, number );		
	}
	public void selectSecurityQuestion(String value)
	{
		util.selectDropDownElement(secQuestionDropDown, value);
	}
	public void setSecurityAnswer(String answer) 
	{
		util.enterElement(secAnswerTextBox, answer );		
	}
	public void clickContinueButton2() throws InterruptedException 
	{
		Thread.sleep(1000);
		util.clickElement(continueButton2);
	}
	public void setAddress(String address)
	{
		util.enterElementWithDelay(addressSearchBox, address );	
		util.clickEnter(addressSearchBox);
	}
	public void clickEmailCheckBox() 
	{
		util.clickElement(emailCheckBox);
	}
	public void clickTextCheckBox() 
	{
		util.clickElement(textCheckBox);
	}
	public void selectCurrencyDropdown(String value)
	{
		util.selectDropDownElement(currencyDropDown, value);
	}
	public void setDepositLimit(String value, String depositValue)
	{
		util.clickElement(setDepositLimitButton);
		util.enterElement(depositAmountTextBox, value);
		String text=util.getAttributeValue(depositAmountTextBox, value);
		util.deleteKeysInTextBox(depositAmountTextBox, text);	
		util.enterElement(depositAmountTextBox, depositValue);
		util.clickElement(depositAmountDuration);
		util.clickElement(saveButton);
	}	
	public void setRealityCheck(String realityValue)
	{
		util.clickElement(setRealityCheckButton);
		util.selectDropDownElement(setRealityCheckDropDown, realityValue);
		util.clickElement(saveButton);
	}
	public void registerUser()
	{
		util.clickElement(registerButton);
	}
	public boolean isTitleElementPresent()
	{
		Boolean headerValue=util.isElementDisplayed(titleOption);
		return headerValue;
	}
	public boolean isTelephoneElementPresent()
	{
		Boolean headerValue=util.isElementDisplayed(telephoneNumberTextBox);
		return headerValue;
	}
	public boolean isAddressElementPresent()
	{
		Boolean headerValue=util.isElementDisplayed(addressSearchBox);
		return headerValue;
	}
	public boolean isEmailCheckboxElementPresent()
	{
		Boolean headerValue=util.isElementDisplayed(emailCheckBox);
		return headerValue;
	}
	public boolean isaddPaymentButtonElementPresent()
	{
		Boolean headerValue=util.isElementDisplayed(addPaymentButton);
		return headerValue;
	}
}
