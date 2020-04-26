package com.freecrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.freecrm.util.webListener;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class Testcase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static webListener webListener;
	public static WebDriverWait wait ;
	public static ATUTestRecorder recorde;
	public Testcase() throws IOException{
		
		prop = new Properties();
		
		FileInputStream file = new FileInputStream("D:\\Projets Eclipse\\FreeCRM\\src\\com\\freecrm\\config\\config.properties");
		prop.load(file);
		
	}
	
	public void initialisation () throws ATUTestRecorderException {
		System.setProperty("webdriver.chrome.driver", "D:\\Projets Eclipse\\DriversNavigateursSelenium\\chromedriver.exe");
		driver = new ChromeDriver();
		e_driver = new EventFiringWebDriver(driver);
		//webListener =  new webListener();
		//e_driver.register(webListener);
		driver = e_driver;
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,30000);
		recorde = new ATUTestRecorder("D:\\Projets Eclipse\\FreeCRM\\video", "recordVideo1", false);
		recorde.start();
	}
	
	public void finishTest() throws ATUTestRecorderException {
		driver.quit();
		recorde.stop();
	}
	
	
}
