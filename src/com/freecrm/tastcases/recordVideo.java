package com.freecrm.tastcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class recordVideo {

	@Test
	public void recordVideo() throws ATUTestRecorderException {
		
		ATUTestRecorder recorde = new ATUTestRecorder("D:\\Projets Eclipse\\FreeCRM\\video", "recordVideo1", false);
		recorde.start();
		
		System.setProperty("webdriver.chrome.driver", "D:\\Projets Eclipse\\DriversNavigateursSelenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://freecrm.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		

		
		WebDriverWait wait = new WebDriverWait(driver,30000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='https://ui.freecrm.com']"))));
		WebElement login= driver.findElement(By.xpath("//a[@href='https://ui.freecrm.com']"));
		login.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("email"))));
		driver.findElement(By.name("email")).sendKeys("rajae-titrite8788@hotmail.com");
		driver.findElement(By.name("password")).sendKeys("bonjourfreeCRM");
		driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']")).click();
		driver.quit();
		recorde.stop();

	}
	
}
