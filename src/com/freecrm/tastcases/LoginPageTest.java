package com.freecrm.tastcases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.freecrm.base.Testcase;
import com.freecrm.util.testUtils;

import atu.testrecorder.exceptions.ATUTestRecorderException;

public class LoginPageTest extends Testcase {
	
	public LoginPageTest() throws IOException {
		super();
	}
	
//	@BeforeMethod(groups = {"E2E", "sanity", "regression"})
//	public void setup() {
//		System.setProperty("webdriver.chrome.driver", "D:\\Projets Eclipse\\DriversNavigateursSelenium\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://freecrm.com");
//	}
//	
//	@AfterMethod(groups = {"E2E", "sanity", "regression"})
//	public void quit() {
//		driver.quit();
//	}
	@Parameters({"URL"})
	@BeforeClass(groups = {"E2E", "sanity", "regression"} )
	public void setup() throws ATUTestRecorderException {
		initialisation();
	}
	
	@AfterClass(groups = {"E2E", "sanity", "regression"} )
	public void quit() {
		driver.quit();
	}
	/***************************************************************************/
	
	@Test(priority = 1, groups = {"sanity"})
	public void titleTest() {

		SoftAssert softAssert = new SoftAssert();
		String expectedResult="Free CRM #1 cloud software for any business large or small";
		String actualResult = driver.getTitle();
		System.out.println(actualResult);
		
		//2 type d'assert : Hard assert & soft assert
		//Hard assert : l'execution s'arrete à la detection de defaillance
		Assert.assertEquals(actualResult, expectedResult, "the expected result and the actual result are not the same !!");
		
		//Soft assert : l'execution continue méme à la detection d'une defaillance
		softAssert.assertEquals(actualResult, expectedResult, "the expected result and the actual result are not the same !!");
		softAssert.assertAll();
	}
	
	@Test(priority = 2, groups = {"E2E"})
	public void urlCheck() {

		String expectedResult="https://freecrm.com/";
		String actualResult = driver.getCurrentUrl();
		System.out.println(actualResult);
		Assert.assertEquals(actualResult, expectedResult, "the URL is not correct");
	}
	
	
	@Test(priority = 3, groups = {"regression"}, enabled = false)
	public void logoTest() {
		
		WebElement logo = driver.findElement(By.xpath("//image[@src ='/images/cogtiny1.jpg']"));
		
		boolean actualResult = logo.isDisplayed();
		System.out.println(actualResult);
		Assert.assertTrue(actualResult, "the pogo is not displayed");;
	}	
	//@Parameters({"Email", "Password"})
	@Test(priority = 4, groups = { "login"}, dataProvider = "mydata")
	public void loginIn(String Email,String Password, Method method) throws IOException {
		
		WebDriverWait wait = new WebDriverWait(driver,30000);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='https://ui.freecrm.com']"))));
		WebElement login= driver.findElement(By.xpath("//a[@href='https://ui.freecrm.com']"));
		login.click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("email"))));
		driver.findElement(By.name("email")).sendKeys(Email);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']")).click();
		testUtils.takeScreenshop(method.getName());
	}
	
	@DataProvider
	public Object[][] mydata() {
		Object[][] data = new Object[4][2];
		
		data[0][0] = "rajae-titrite8788@hotmail.com";
		data[0][1] = "bonjourfreeCRM";
		data[1][0] = "rajafgdfdf8788@hotmail.com";
		data[1][1] = "bonjourfreeCRM";
		data[2][0] = "rajae-titrite8788@hotmail.com";
		data[2][1] = "xcgdfg";
		data[3][0] = "qdfghhkk";
		data[3][1] = "yuiyu";
		
		return data;
	}
}
