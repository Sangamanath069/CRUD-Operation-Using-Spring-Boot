package com.crud.sts.insertinto.excel;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.crud.sts.controller.ProductController;
import com.crud.sts.entity.Products;

public class InsertIntoExcel {
	
	public static void insertDataToExcel(ArrayList<Products> a) {
		try {
	
	XSSFWorkbook workbook =new XSSFWorkbook();
	//creating sheet
	Sheet sh =workbook.createSheet("Products");
	
	//creating top row with column heading
	String[] columnHeading = {"prod_id", "prod_name", "prod_quantity", "prod_price","is_active"};
	
	
	//Create the header row
	Row headerRow = sh.createRow(0);
	
	//Iterate over the column headings to create columns
	for(int ii=0;ii<columnHeading.length;ii++) {
		Cell cell = headerRow.createCell(ii);
		cell.setCellValue(columnHeading[ii]);

	}
	//Freeze Header Row
	sh.createFreezePane(0, 1);
	
		int rownum =1;
	for(Products j : a) {
		//System.out.println("rownum-before"+(rownum));
		Row row = sh.createRow(rownum++);
		//System.out.println("rownum-after"+(rownum));
		System.out.println("j.getProdId()--> "+j.getProdId());
		System.out.println("a--> "+a);
		row.createCell(0).setCellValue(j.getProdId());
		row.createCell(1).setCellValue(j.getProdName());
		row.createCell(2).setCellValue(j.getProdQuantity());
		row.createCell(3).setCellValue(j.getProdPrice());
		row.createCell(4).setCellValue(j.getIsActive());
		System.out.println("j.getIsActive()--> "+j.getIsActive());
		
	}
	

	//Autosize columns
	for(int i=0;i<columnHeading.length;i++) {
		sh.autoSizeColumn(i);
	}
	
	//Write the output to file
	FileOutputStream fileOut = new FileOutputStream("E://DailyWork//StoringEmpData.xlsx");
	workbook.write(fileOut);
	fileOut.close();
	workbook.close();
	System.out.println("Completed");
}catch(Exception e) {
	e.printStackTrace();
}
}
	
	public static void insertAllDataToExcel(ArrayList<Products> a) {
		try {
	
	XSSFWorkbook workbook =new XSSFWorkbook();
	//creating sheet
	Sheet sh =workbook.createSheet("Products");
	
	//creating top row with column heading
	String[] columnHeading = {"prod_id", "prod_name", "prod_quantity", "prod_price","is_active"};
	
	
	//Create the header row
	Row headerRow = sh.createRow(0);
	
	//Iterate over the column headings to create columns
	for(int ii=0;ii<columnHeading.length;ii++) {
		Cell cell = headerRow.createCell(ii);
		cell.setCellValue(columnHeading[ii]);

	}
	//Freeze Header Row
	sh.createFreezePane(0, 1);
	
		int rownum =1;
	for(Products j : a) {
		//System.out.println("rownum-before"+(rownum));
		Row row = sh.createRow(rownum++);
		//System.out.println("rownum-after"+(rownum));
		System.out.println("j.getProdId()--> "+j.getProdId());
		System.out.println("a--> "+a);
		row.createCell(0).setCellValue(j.getProdId());
		row.createCell(1).setCellValue(j.getProdName());
		row.createCell(2).setCellValue(j.getProdQuantity());
		row.createCell(3).setCellValue(j.getProdPrice());
		
		row.createCell(4).setCellValue(String.valueOf(j.getIsActive()));
		System.out.println("j.getIsActive()--> "+j.getIsActive());
		
	}
	

	//Autosize columns
	for(int i=0;i<columnHeading.length;i++) {
		sh.autoSizeColumn(i);
	}
	
	//Write the output to file
	FileOutputStream fileOut = new FileOutputStream("E://DailyWork//StoringEmpData.xlsx");
	workbook.write(fileOut);
	fileOut.close();
	workbook.close();
	System.out.println("Completed");
}catch(Exception e) {
	e.printStackTrace();
}
}

	


}


