package com.freecrm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.freecrm.base.Testcase;

public class testUtils extends Testcase {

	public testUtils() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void takeScreenshop(String name) throws IOException {
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File("D:\\Projets Eclipse\\FreeCRM\\snapshots\\" + name + ".png"));		
	}
	
	public static Object[][] getData(String contactSheet) throws Exception{
		

		File file = new File("D:\\Projets Eclipse\\data.xlsx");
		FileInputStream fis= new FileInputStream(file);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheet(contactSheet); 

		int rows = sheet.getLastRowNum();
		int cells = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cells];
		
		for( int i = 0 ; i < rows ; i++) {
			for( int k = 0 ; k < cells ; k++) {
				data[i][k] = sheet.getRow(i).getCell(k).toString();
			}
		}
		workbook.close();
		return data; 
	}
	
	

}
