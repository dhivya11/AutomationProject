package com.oddskings.testScripts;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.oddskings.baseClassCode.BaseClass;
import com.oddskings.utilFiles.ExcelUtils;
import com.oddskings.utilFiles.FileMethodUtils;
import com.oddskings.utilFiles.JsonUtils;

public class CountryCountValidation extends BaseClass {
	JsonUtils su;
	private static XSSFSheet ExcelWSheetRead;
	int rowNum;
	FileMethodUtils fu;

	@BeforeTest
	public void setup() {
		logger.info("---------------------------Start of TestCase-----------------------------------");
		su = new JsonUtils();
		fu = new FileMethodUtils();
		String readExcelPath = System.getProperty("user.dir") + ReadProperty("readExcelPath");
		try {
			ExcelWSheetRead = ExcelUtils.setExcelFile(readExcelPath, ReadProperty("languageCodePageSheet"));
			rowNum = ExcelUtils.getLastrow(ExcelWSheetRead);
			ExcelUtils.setExcelFile(testResultFilePath, ReadProperty("CountryCountValidationTestResultSheet"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void applicationCode() throws Exception {
		for (int i = 1; i <= rowNum; i++) {
			String code = ExcelUtils.getCellDataInStringFormat(ExcelWSheetRead, i, 0);
			String countryAPCountryAppended = ReadProperty("countryAPIUrl") + code;
			logger.info("URL is " + countryAPCountryAppended);
			JSONObject json = JsonUtils.readJsonFromUrl(countryAPCountryAppended);

			List<String> list = new ArrayList<String>();
			JSONArray array = json.getJSONArray(ReadProperty("countryObject"));
			for (int j = 0; j < array.length(); j++) {
				list.add(array.getJSONObject(j).getString("name"));
			}
			logger.info("Final Country Count for Language " + code + " is " + list.size());

			int count = Integer.valueOf(ReadProperty("countriesCount"));
			if (list.size() == count) {
				logger.info("Countries count is Matching");
				ExcelUtils.setCellData(ReadProperty("passedStatus"), 1, 3, testResultFilePath);
			} else {
				ExcelUtils.setCellData(ReadProperty("failedStatus"), 1, 3, testResultFilePath);
				logger.error("Countries count is not Matching for country with code " + code);
				Assert.fail();
			}
		}
	}

	@AfterTest
	public void quit() {
		logger.info("---------------------------End of TestCase-----------------------------------");
	}

}
