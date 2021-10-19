package com.oddskings.utilFiles;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row1;

//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static XSSFSheet setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream 
			ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);			
		} catch (Exception e) {
			throw (e);
		}
		return ExcelWSheet;
	}

	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num
	public static int getCellData(XSSFSheet sheet, int RowNum, int ColNum) throws Exception {
		try {
			Cell = sheet.getRow(RowNum).getCell(ColNum);
			double CellData = Cell.getNumericCellValue();
			int value= (int)CellData;
			System.out.println(value);
			return value;
		} catch (Exception e) {
			throw (e);
		}
	}
	
	// This method is to read the test data from the Excel cell, in this we are
		// passing parameters as Row num and Col num
		public static String getCellDataInStringFormat(XSSFSheet sheet, int RowNum, int ColNum) throws Exception {
			try {
				Cell = sheet.getRow(RowNum).getCell(ColNum);
//				String CellData = Cell.getStringCellValue();
				DataFormatter formatter = new DataFormatter();
				 String CellData = formatter.formatCellValue(Cell);
				return CellData;
			} catch (Exception e) {
				throw (e);
			}
		}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters

	public static void setCellData(String Result, int RowNum, int ColNum, String path) throws Exception {
		try {
			Row1 = ExcelWSheet.getRow(RowNum);
			Cell = Row1.getCell(ColNum, MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row1.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	public static int getLastrow(XSSFSheet sheet) throws Exception {
		try {
			int rowNum = sheet.getLastRowNum();
			return rowNum;
		} catch (Exception e) {
			throw (e);
		}
	}
	public void closeFile() throws Exception {
		try {
			ExcelWBook.close();
		} catch (Exception e) {
			throw (e);
		}
	}
}
