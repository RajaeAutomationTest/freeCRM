package com.freecrm.tastcases;

import java.io.File;
import java.io.FileInputStream;


import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class alpoadXLSX {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		File file = new File("D:\\Projets Eclipse\\data.xlsx");
		FileInputStream fis= new FileInputStream(file);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet("contacts"); 

		int rows = sheet.getLastRowNum();
		int cells = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cells];
		
		for( int i = 0 ; i < rows ; i++) {
			for( int k = 0 ; k < cells ; k++) {
				data[i][k] = sheet.getRow(i+1).getCell(k+1);
			}
		}
		workbook.close();
	}

}
