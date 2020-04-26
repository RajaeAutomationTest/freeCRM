package com.freecrm.tastcases;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.freecrm.base.Testcase;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class homePage extends Testcase {

	public homePage() throws IOException {
		super();
	}
	
	
	
	@Parameters({"URL"})
	@BeforeMethod(groups = {"E2E", "sanity", "regression"})	
	public void setup() throws ATUTestRecorderException {
		initialisation();

	}

	@AfterMethod(groups = {"E2E", "sanity", "regression"})
	public void teardown() {
		//driver.quit();
	}
	/***************************************************************************/
	@Test(priority = 5, groups = {"E2E"})
	public void GoToContacts() {
		SoftAssert softAssert = new SoftAssert();
		WebElement leftbar = driver.findElement(By.id("main-nav"));
		WebElement contacts =  leftbar.findElement(By.xpath("//span[text() = 'Contacts']"));
		
		contacts.click();
		
		WebElement dashboard = driver.findElement(By.id("dashboard-toolbar"));
		WebElement  titleContacts = dashboard.findElement(By.xpath("//div[@class='ui header item mb5 light-black']"));
		boolean existTitle = titleContacts.isDisplayed();
		softAssert.assertTrue(existTitle,"the page contact title is not displayed");
		softAssert.assertEquals(driver.getCurrentUrl(), "https://ui.freecrm.com/contacts", "the URLs are not the same");
		softAssert.assertAll();
	}
}
