package com.oddskings.utilFiles;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	public WebDriver driver;
	public SeleniumUtils(WebDriver driver2) {
		driver=driver2;
	}
	public String clickElement(WebElement xpathValue)
	{
		String status ="Success";
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.elementToBeClickable(xpathValue));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("arguments[0].click()", element);
		status ="Success";
		}
		catch(Exception e)
		{
			status ="Failure";
			System.out.print("Unable to click the element "+e);
		}
		return status;
	}
	public String getText(String xpathValue)
	{
		String elementText = null;
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathValue)));
		elementText= element.getText();
		}
		catch(Exception e)
		{
			System.out.print("Unable to click the element "+e);
		}
		return elementText;
	}
	public void selectDropDownElement(WebElement dropDownPath, String value )
	{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.elementToBeClickable(dropDownPath));
		Select s = new Select(element);
		s.selectByValue(value);
	}
	public String enterElement(WebElement emailTextBox, String inputText)
	{
		String status ="Success";
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(emailTextBox));
		element.sendKeys(inputText);
		status ="Success";
		}
		catch(Exception e)
		{
			status ="Failure";
			System.out.print("Unable to enter the element "+e);
		}
		return status;
	}
	public String enterElementWithDelay(WebElement emailTextBox, String inputText)
	{
		String status ="Success";
		try
		{
		char[] arr=inputText.toCharArray();
		for(int i=0;i<arr.length;i++)
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(emailTextBox));
		String str=String.valueOf(arr[i]);
		element.sendKeys(str);
		Thread.sleep(1000);
		}
		status ="Success";
		}
		catch(Exception e)
		{
			status ="Failure";
			System.out.print("Unable to enter the element "+e);
		}
		return status;
	}
	public String clickEnter(WebElement xpathValue)
	{
		String status ="Success";
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(xpathValue));
		element.sendKeys(Keys.ENTER);
		status ="Success";
		}
		catch(Exception e)
		{
			status ="Failure";
			System.out.print("Unable to enter the element "+e);
		}
		return status;
	}
	public String getAttributeValue(WebElement xpathValue, String attributeName)
	{
		String status =null;
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(xpathValue));
		status=element.getAttribute(attributeName);		
		}
		catch(Exception e)
		{
			System.out.print("Unable to enter the element "+e);
		}
		return status;
	}
	public String getTextBoxValue(WebElement xpathValue)
	{
		String status =null;
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(xpathValue));
		status=element.getText();
		}
		catch(Exception e)
		{
			System.out.print("Unable to enter the element "+e);
		}
		return status;
	}
	public void deleteKeysInTextBox(WebElement element, String text)
	{
		if( text != null ) {
            for(int i=0; i<text.length();i++) {
            	element.sendKeys(Keys.BACK_SPACE);
            }
        }
	}
	public String getText(WebElement ele)
	{
		String elementText = null;
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(ele));
		elementText= element.getText();
		}
		catch(Exception e)
		{
			System.out.print("Unable to click the element "+e);
		}
		return elementText;
	}
	public Boolean isElementDisplayed(WebElement ele)
	{
		Boolean flag=false;
		try
		{
		WebElement element = (new WebDriverWait(driver, 10))
	               .until(ExpectedConditions.visibilityOf(ele));
		flag= element.isDisplayed();
		}
		catch(Exception e)
		{
			System.out.print("Unable to click the element "+e);
		}
		return flag;
	}
}
