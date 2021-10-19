package com.oddskings.baseClassCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.oddskings.utilFiles.FileMethodUtils;

public class BaseClass {
	protected static Properties p;
	protected static Properties p1;
	protected static Logger logger;
	protected static String testResultFilePath;
	static FileMethodUtils fu;
	
	@BeforeSuite(alwaysRun = true)
	public static void createTestResultFile() {
		try {
			initialiseLog();
			initialiseConfig();
			initialiseObject();
			fu = new FileMethodUtils();
			String readReportExcelPath= System.getProperty("user.dir") + ReadProperty("testReportReadExcelPath");
			String writeReportExcelPath= System.getProperty("user.dir") + ReadProperty("testReportWriteExcelPath");
			String testResultFileName = fu.filecopy(readReportExcelPath,writeReportExcelPath);
			testResultFilePath=System.getProperty("user.dir") + ReadProperty("testReportDirectory")+testResultFileName+".xlsx";
		} 
		catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void initialiseLog()
	{
		logger=Logger.getLogger("serviceKeyValidation");
		PropertyConfigurator.configure("log4j.properties");
	}

	public static void initialiseConfig() {
		File file = new File(System.getProperty("user.dir") + "\\Configuration\\config.properties");
		try {
			FileInputStream fip = new FileInputStream(file);
			p = new Properties();
			p.load(fip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException caught in initialconfig" + e.getStackTrace());
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void initialiseObject() {
		File file = new File(System.getProperty("user.dir") + "\\Configuration\\object.properties");

		try {
			FileInputStream fip = new FileInputStream(file);
			p1 = new Properties();
			p1.load(fip);
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException caught in initialconfig" + e.getStackTrace());
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

//Utility method to read property from properties file
	public static String ReadProperty(String Key) {
		return p.getProperty(Key);
	}

	public String ReadObjectProperty(String Key) {
		return p1.getProperty(Key);
	}
	@AfterSuite
	public void doNothing()
	{
		
	}
}
