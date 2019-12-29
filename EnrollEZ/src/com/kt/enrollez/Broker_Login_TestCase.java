package com.kt.enrollez;

import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.css.StyleSheetList;

import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Broker_Login_TestCase {
	
	static WebDriver driver = null;
	
	@BeforeSuite(groups="smoke")
	public static WebDriver setup_Browser(){
		System.out.println("Starting Browser =======>>");
		driver = new FirefoxDriver();
		driver.manage().window().maximize(); // Maximize the window
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://qa.ensoulinc.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		return driver;	
	}
	@Test(priority=1,groups="smoke")
	public void Verify_Application_Title(){
		System.out.println("----This test case validate to check application of Title on login screen");
		String actTitle = driver.getTitle();
		System.out.println("Actual Tile of Application"+actTitle);
		String expTitle = "OBE - Account | Login";
		System.out.println("Expected Tile of Application"+expTitle);
		AssertJUnit.assertEquals(actTitle, expTitle);
		System.out.println("");
	}
	
	@Test(priority=2,groups="smoke")
	public void Verify_Logo() {
		System.out.println("----This test case validate to check log0 of Employer");
		 WebElement empLogo =  driver.findElement(By.xpath("//img[@class='img-responsive center-block']"));
		 Boolean empImgPresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", empLogo);
	        if (!empImgPresent)
	        {
	             System.out.println("Logo not displayed.");
	        }
	        else
	        {
	            System.out.println("Logo displayed.");
	        }  
	        System.out.println("");
	}
	@Test(priority=3,groups="smoke")
	public void Verify_Application_Url(){
		System.out.println("----This test case validate to check URL of application");
		String actUrl = driver.getCurrentUrl();
		System.out.println("Actual URL of EnSoul Application >>"+actUrl);
		String expUrl = "https://qa.ensoulinc.com/";
		System.out.println("Expexted URL of EnSoul Application >>"+expUrl);
		AssertJUnit.assertEquals(actUrl, expUrl);
		System.out.println("");
	}
	@Test(priority=4,groups="smoke")
	public void Verify_Placeholder_ofAllfields(){
		System.out.println("---This test case validate to check placeholder of all login page fields");
		String actResult = driver.findElement(By.xpath("//input[@id='UserName']")).getAttribute("placeholder");
		System.out.println("Actual placeholder of UN >>"+actResult);
		String expResult = "User Name";
		System.out.println("Expected placeholder of UN >>"+expResult );
		AssertJUnit.assertEquals(actResult, expResult);
		//------------Password Text Box Placeholder
		String actpwdResult = driver.findElement(By.xpath("//input[@id='Password']")).getAttribute("placeholder");
		System.out.println("Actual placeholder of pwd >>"+actpwdResult);
		String exppwdResult = "Password";
		System.out.println("Expected placeholder of pwd>>"+exppwdResult );
		AssertJUnit.assertEquals(actpwdResult, exppwdResult);
		System.out.println("");
		
	}
	
	// I will work after windows handler chapter 
	/*
	@Test(priority=5,groups="smoke")
	public void Verify_AppStorelink(){
		System.out.println("----This test case validate to check the link of AppStore");
		driver.findElement(By.xpath("//center//img[1]")).click();
		String actText = driver.findElement(By.xpath("//span[contains(text(),'Scan or click to Apple store')]")).getText();
		System.out.println(actText);
		String expText = "Scan or click to Apple store";
		System.out.println(expText);
		Assert.assertEquals(actText, expText);
		System.out.println("");
	}
	@Test(priority=6,groups="smoke")
	public void Verify_GooglePlaylink() throws Exception{
		System.out.println("----This test case validate to check the link of Google Store");
		driver.findElement(By.xpath("//div[@id='myModal']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//img[2]")).click();
		String actText = driver.findElement(By.xpath("//span[contains(text(),'Scan or click to Google store')]")).getText();
		System.out.println(actText);
		String expText = "Scan or click to Google store";
		System.out.println(expText);
		Assert.assertEquals(actText, expText);
		System.out.println("");
	}
	*/
	@Test(priority=5,groups="smoke",dataProvider="LoginData")
	public void Verify_Login(String tcId,String tcDescription,String Username,String Passwrod,String expResult ) throws Exception{
		
		System.out.println(tcId);
		System.out.println(tcDescription);
		
		driver.findElement(By.xpath("//input[@id='UserName']")).clear();
		driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys(Username);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='Password']")).clear();
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(Passwrod);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='Login']")).click();
		
		if (tcDescription.equals("DatawithBlank")){
			Thread.sleep(1000);
			String actResult1 = driver.findElement(By.xpath("//div[@id='loginError']")).getText();
			Thread.sleep(1000);
	        Assert.assertEquals(actResult1, expResult);
		}else if (tcDescription.equals("invalidData")){
			Thread.sleep(1000);
			String actResult2 = driver.findElement(By.xpath("//div[@id='loginError']")).getText();
	        Assert.assertEquals(actResult2, expResult);
		}else if(tcDescription.equals("ValidData")){
			Thread.sleep(1000);
			 String actResult3= driver.findElement(By.xpath("//li[contains(text(),'Welcome')]")).getText();
			 Thread.sleep(1000);
			 Assert.assertEquals(actResult3, expResult);
		}
	}
	 @DataProvider
	  public Object[][] LoginData() {
	    return new Object[][] {
	      new Object[] {"KTB01","DatawithBlank","","","Login was unsuccessful. Please check your login information and retry."},
	      new Object[] {"KTB02","invalidData","abc", "1234","Login was unsuccessful. Please check your login information and retry."},
	      new Object[] {"KTB03","ValidData","TestBroker","12345678","Welcome TestBroker!"},
	    };
	 }
	    
	

	@AfterSuite(groups = "Smoke")
	public void close_Browse() {
		driver.quit();
	}

		
	}
	
