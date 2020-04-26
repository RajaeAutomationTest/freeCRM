package com.freecrm.tastcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.freecrm.base.Testcase;
import com.freecrm.util.testUtils;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class ContactPageTest extends Testcase {
	
	public ContactPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@BeforeMethod
	public void setup() throws ATUTestRecorderException {
		initialisation();
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='https://ui.freecrm.com']"))));
		WebElement login= driver.findElement(By.xpath("//a[@href='https://ui.freecrm.com']"));
		login.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("email"))));
		driver.findElement(By.name("email")).sendKeys(prop.getProperty("UserName"));
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']")).click();		


	}
	
	@AfterMethod
	public void quit() throws ATUTestRecorderException {
		finishTest();
	}

	@Test(dataProvider = "testdata")	
	public void addContact(String fname, String lname, String mname, String comp, String desc) {
		
		WebDriverWait wait = new WebDriverWait(driver,30000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("main-nav"))));
		driver.findElement(By.id("main-nav")).findElement(By.xpath("//*[text() = 'Contacts']")).click();
		driver.findElement(By.xpath("//a[@href='/contacts/new']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[text()='Create New Contact']"))));
		WebElement first_name = driver.findElement(By.name("first_name"));
		WebElement last_name = driver.findElement(By.name("last_name"));
		WebElement middle_name = driver.findElement(By.name("middle_name"));
		WebElement company = driver.findElement(By.xpath("//div[@name='company']//input[@class='search']"));
		WebElement description = driver.findElement(By.name("description"));
		
		first_name.sendKeys(fname);
		last_name.sendKeys(lname);
		middle_name.sendKeys(mname);
		company.sendKeys(comp);
		description.sendKeys(desc);
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='custom-view-container']"))));
		String expected = "Youssef "+"Machkira";										
		String actual = driver.findElement(By.xpath("//div[@class='ui header item mb5 light-black']")).getText();
		assertEquals(actual, expected);
	}
	
	@DataProvider
	public Object[][] testdata() throws Exception {
		Object[][] data = testUtils.getData("contacts");
		return data;
	}
}
