package com.ritm.wrapper;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ReadingFillo {
	static Fillo objFillo;
	static Recordset objRS = null;

	public static String Testdatavalue(String SheetName, String TC_id, String Coloumnname) throws FilloException {

		String Testdatavalue = null;

		try {
			objFillo = new Fillo();
			Connection objCon = objFillo.getConnection(System.getProperty("user.dir") + "\\TestData\\DataSheet.xlsx");
			String strQuery = "select * from " + SheetName + " where TC_ID='" + TC_id + "'";
			objRS = objCon.executeQuery(strQuery);
			objRS.next();
			Testdatavalue = objRS.getField(Coloumnname);
		} catch (Exception IOE) {
			System.out.println("Error Occurred is: " + IOE.getMessage());
		}
		return Testdatavalue;
	}

	public static String[] ArrayTestdatavalue(String SheetName, String TC_id, String Coloumnname)
			throws FilloException {

		String Testdatavalue[] = null;
		try {
			objFillo = new Fillo();
			Connection objCon = objFillo.getConnection(System.getProperty("user.dir") + "\\TestData\\DataSheet.xlsx");
			String strQuery = "select * from " + SheetName + " where TC_ID='" + TC_id + "'";
			objRS = objCon.executeQuery(strQuery);
			objRS.next();
			Testdatavalue = objRS.getField(Coloumnname).split(";");
		} catch (Exception IOE) {
			System.out.println("Error Occurred is: " + IOE.getMessage());
		}
		return Testdatavalue;
	}
	public static String[][] TwoDimArrayTestdatavalue(String SheetName, String TC_id, String Coloumnname)
			throws FilloException {

		String Testdatavalue[] = null;
		String[][] result = null;
		try {
			objFillo = new Fillo();
			Connection objCon = objFillo.getConnection(System.getProperty("user.dir") + "\\TestData\\DataSheet.xlsx");
			String strQuery = "select * from " + SheetName + " where TC_ID='" + TC_id + "'";
			objRS = objCon.executeQuery(strQuery);
			objRS.next();
			Testdatavalue = objRS.getField(Coloumnname).split(";");

			result = new String[Testdatavalue.length][];

			for (int i = 0; i < Testdatavalue.length; i++) {
				String[] row = Testdatavalue[i].split(",");
				result[i] = new String[row.length];

				for (int j = 0; j < row.length; j++) {
					result[i][j] = row[j].trim();
				}
			}
		} catch (Exception IOE) {
			System.out.println("Error Occurred is: " + IOE.getMessage());
		}
		return result;
	}

	public static void writingdata(String strModuleSheetName, String TC_id, String value, String Column_Name)
			throws FilloException {
		objFillo = new Fillo();
		Connection objCon = objFillo.getConnection(System.getProperty("user.dir") + "\\TestData\\DataSheet.xlsx");
		String strQuery = "Update" + " " + strModuleSheetName + " Set " + Column_Name + "= '" + value
				+ "' where TC_ID='" + TC_id + "'";
		objCon.executeUpdate(strQuery);
		objCon.close();
	}

	public static void createWB() {
		wb = new XSSFWorkbook();
		sheet1 = wb.createSheet();
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style.setFillPattern(FillPatternType.FINE_DOTS);
		Row row = sheet1.createRow(rowNumber);
		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("FieldName");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("Value");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("HMDAFieldName");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("HMDAValue");
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("HMDAValidation");

		rowNumber++;
		rowNumber2 = rowNumber;
	}

	static XSSFWorkbook wb;
	static XSSFSheet sheet1;
	static int rowNumber = 0;
	static int rowNumber2 = 0;

	public static void create_WB() {

		wb = new XSSFWorkbook();
		sheet1 = wb.createSheet("MI");
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setFont(font);
		style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style.setFillPattern(FillPatternType.FINE_DOTS);
		Row row = sheet1.createRow(rowNumber);

		Cell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("FieldName");
		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("Value");
		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("XML Value");
		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("Customised Value");
		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("XML Status");
		rowNumber++;
	}

	public static String[] ArrayTestdatavalueByDelimeter(String SheetName, String TC_id, String Coloumnname,
			String delimeter) throws FilloException {

		String Testdatavalue[] = null;
		try {
			objFillo = new Fillo();
			Connection objCon = objFillo.getConnection(System.getProperty("user.dir") + "\\TestData\\DataSheet.xlsx");
			String strQuery = "select * from " + SheetName + " where TC_ID='" + TC_id + "'";
			objRS = objCon.executeQuery(strQuery);
			objRS.next();
			Testdatavalue = objRS.getField(Coloumnname).split(delimeter);
		} catch (Exception IOE) {
			System.out.println("Error Occurred is: " + IOE.getMessage());
		}
		return Testdatavalue;
	}

	public static void print_WB(String data, String FieldName) throws IOException {
		Row row = sheet1.createRow(rowNumber);
		Cell cell = row.createCell(0);
		cell.setCellValue(FieldName);
		cell = row.createCell(1);
		cell.setCellValue(data);
		rowNumber++;
	}

	public static void printWB(String data, String FieldName) throws IOException {
		Row row1;
		if (sheet1.getRow(rowNumber) != null)
			row1 = sheet1.getRow(rowNumber);
		else
			row1 = sheet1.createRow(rowNumber);
		;
		Cell cell = row1.createCell(0);
		cell.setCellValue(FieldName);

		cell = row1.createCell(1);
		cell.setCellValue(data);
		System.out.println("Cell Print====================");
		rowNumber++;
	}

	public static void printWB_HMDA(String HMDAdata, String HMDAFieldName) throws IOException {
		Row row1;
		if (sheet1.getRow(rowNumber2) != null)
			row1 = sheet1.getRow(rowNumber2);
		else
			row1 = sheet1.createRow(rowNumber2);
		;
		Cell cell = row1.createCell(2);
		cell.setCellValue(HMDAFieldName);
		cell = row1.createCell(3);
		cell.setCellValue(HMDAdata);
		System.out.println("Cell Print====================");
		rowNumber2++;
	}

	public static void Compare_HMDA() throws IOException {
		int rows = sheet1.getLastRowNum();
		System.out.println("Rows" + rows);
		for (int i = 1; i <= rows; i++) {
			int r = i + 1;
			Row row = sheet1.getRow(i);
			Cell cell1 = row.createCell(4);

			String Formula = "IF(ISNUMBER(SEARCH(B" + r + ", D" + r + ")),\"Pass\",\"Fail\")";
			cell1.setCellFormula(Formula);
			System.out.println(Formula);
		}
	}

	public static void closeWB(String Tc_Id, String name) throws IOException {

		FileOutputStream fout = new FileOutputStream(
				System.getProperty("user.dir") + "\\Excel\\" + Tc_Id + "_HMDAReport_" + name + "_Output.xlsx");
		sheet1.autoSizeColumn(0);

		sheet1.autoSizeColumn(1);
		wb.write(fout);
		fout.close();
		rowNumber = 0;
		System.out.println("Created");
	}

}
