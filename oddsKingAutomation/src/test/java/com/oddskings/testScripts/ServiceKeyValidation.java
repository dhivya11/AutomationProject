package com.oddskings.testScripts;

import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.oddskings.baseClassCode.BaseClass;
import com.oddskings.utilFiles.ExcelUtils;
import com.oddskings.utilFiles.FileMethodUtils;
import com.oddskings.utilFiles.JsonUtils;

public class ServiceKeyValidation extends BaseClass {
	JsonUtils su;
	ExcelUtils eu;
	FileMethodUtils fu;

	@BeforeClass
	public void setup() {
		logger.info("---------------------------Start of TestCase-----------------------------------");
		su = new JsonUtils();
		eu = new ExcelUtils();
		fu = new FileMethodUtils();
		try {
			ExcelUtils.setExcelFile(testResultFilePath, ReadProperty("serviceResponseValidationTestResultSheet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void applicationCode() throws Exception {
		try {
			JSONObject json = JsonUtils.readJsonFromUrl(ReadProperty("serviceAPIUrl"));
			System.out.println(json.toString());
			System.out.println(json.get(ReadProperty("serviceObject")));
			if (json.get(ReadProperty("serviceObject")).equals(ReadProperty("okResponse"))) {
				System.out.println("Service Response is OK");
				logger.info("Service Response is OK");
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 1, 3, testResultFilePath);
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 1, 3, testResultFilePath);
				logger.error("Service Response is not OK");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void quit() {
		logger.info("---------------------------End of TestCase-----------------------------------");
	}
}
