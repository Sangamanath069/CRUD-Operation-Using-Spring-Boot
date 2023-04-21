package com.crud.sts.gettingdatafrom.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadDataFromExcelToDatabase {

	public static List<ArrayList<Object>> readDataFromExcel() throws IOException {
		FileInputStream file = null;
		
		List<ArrayList<Object>> all = null;
		int var = 0;
		try {
			file = new FileInputStream(new File("E://DailyWork//StoringEmpData.xlsx"));

			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			
			 all = new ArrayList<ArrayList<Object>>();
			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				if(var!=0) {
				ArrayList<Object> rowdata = new ArrayList();
				
					// For each row, iterate through all the columns
					Iterator<Cell> cellIterator = row.cellIterator();
					
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case NUMERIC:
							rowdata.add(cell.getNumericCellValue());
							break;
						case STRING:
							
							rowdata.add(cell.getStringCellValue());
							break;
						}
						
					
					}
      			all.add(rowdata);
				}
				var++;
			}
			
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			file.close();
		}
		
		System.out.println("all-->"+ all.toString());
		return all;
	}

}
