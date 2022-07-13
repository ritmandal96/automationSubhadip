package com.ritm.wrapper;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteInExcel {
	
	 public XSSFWorkbook DataExcel;
	 public XSSFSheet Sheet;
	 public XSSFRow Row;
	 public int rownumber;
	 private String[][] bs;
	 
	 public void createWorkBook(String SheetName,String ...Column_names) throws IOException
	 {
		 DataExcel=new XSSFWorkbook();
		 Sheet=DataExcel.createSheet(SheetName);
		 Row = Sheet.createRow(0);
		 XSSFFont font = DataExcel.createFont();
		    font.setFontHeightInPoints((short) 10);
		    font.setFontName("Arial");
		    font.setBold(true);
		    CellStyle style = DataExcel.createCellStyle();
		    style.setFont(font);
		    style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		    style.setFillPattern(FillPatternType.FINE_DOTS);
		 int count=0;
		 for(String c:Column_names){
			 Cell ce=Row.createCell(count);
			 ce.setCellStyle(style);
			 ce.setCellValue(c);
			 count++;
		 }
	 
	 }
	 
	 public void OpenExisting(String File,String SheetName) throws IOException, InvalidFormatException
	 {
	  FileInputStream Fin=new FileInputStream(File);
	  DataExcel=new XSSFWorkbook(Fin);
	  Sheet=DataExcel.getSheet(SheetName); 
	 }
	 
	 public  void EnterData(String Data,int row,int cell)
	 {
		 this.rownumber=row;
         if(Sheet.getRow(rownumber)==null)
         {
        	 Sheet.createRow(rownumber);
         }
		 Sheet.getRow(rownumber).createCell(cell).setCellValue(Data);
	 }
	 
	 public String[][] GetData()
	 {
		int row=Sheet.getPhysicalNumberOfRows();
		bs=new String[row][];
		for(int i=0;i<row;i++)
		{
			int cell=Sheet.getRow(i).getPhysicalNumberOfCells();
			bs[i]=new String[cell];			
			for(int j=0;j<cell;j++)
			{
				bs[i][j]=Sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}
		
		return bs;
	 }
	 
	 
	 public void CloseWorkBook(String filepath) throws IOException
	 {
		 FileOutputStream Fout=new FileOutputStream(filepath);
		 int noOfColumns = Sheet.getRow(0).getPhysicalNumberOfCells();
		 for(int i=0;i<noOfColumns;i++)
		 {
			 Sheet.autoSizeColumn(i); 
		 }
		
		 DataExcel.write(Fout);
		 DataExcel.close();	 
	 }
	 

}
