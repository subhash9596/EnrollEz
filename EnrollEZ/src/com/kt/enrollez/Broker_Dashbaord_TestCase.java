package com.kt.enrollez;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Broker_Dashbaord_TestCase {
	
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
	@Test(priority=1,groups="Regression")
	public void Verify_Login_WithValidInputs( ) throws Exception{
		driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys("TestBroker");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("12345678");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='Login']")).click();
		Thread.sleep(1000);
		String actTile=	driver.findElement(By.xpath("//p[contains(text(),'Test-Broker')]")).getText();
		//String actTile = driver.getTitle();
		String expected = "TEST-BROKER";
		Assert.assertEquals(actTile, expected);
	}	
	@Test(priority=2,groups="smoke")
	public void Verify_Details_link(){
		driver.findElement(By.xpath("//img[@class='detail-icon']")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Details";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=3,groups="smoke")
	public void Verify_Credentials_link(){
		driver.findElement(By.xpath("//img[@class='credentails-icon']")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Define Broker Credential";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=4,groups="smoke")
	public void Verify_BrokerRep_link(){
		driver.findElement(By.xpath("//img[@class='accinfo-icon']")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Broker Representative List";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=5,groups="smoke")
	public void Verify_AddEmployer_link(){
		driver.findElement(By.xpath("//img[@class='employer-add-icon']")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Add Employer";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=6,groups="smoke")
	public void Verify_EmployerList_link(){
		driver.findElement(By.xpath("//img[@class='employer-list-icon']")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Employer List";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=7,groups="smoke")
	public void Verify_UserList_link(){
		driver.findElement(By.xpath("//img[@class='user-icon']")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "User List (0)";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=8,groups="smoke")
	public void Verify_SearchEmployer_link(){
		driver.findElement(By.xpath("//div[7]//a[1]//img[1]")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Employer Search";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=9,groups="smoke")
	public void Verify_SearchEmployee_link(){
		driver.findElement(By.xpath(" //div[8]//a[1]//img[1]")).click();
		String actResult = driver.findElement(By.xpath("//h2[@class='Print pageHeadingContent']")).getText();
		driver.findElement(By.xpath("//img[@class='home']")).click();
		String expReslt = "Employee Search";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=10,groups="smoke")
	public void Employer_Tab(){
		driver.findElement(By.xpath("//a[contains(text(),'Employers')]")).click();
		String actResult = driver.findElement(By.xpath("//div[contains(text(),'Filter by:')]")).getText();
		String expReslt = "Filter by: A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z|All";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=11,groups="smoke")
	public void CountSummary_Tab(){
		driver.findElement(By.xpath("//a[contains(text(),'Count Summary')]")).click();
		String actResult = driver.findElement(By.xpath("//canvas[@class='jqplot-grid-canvas']")).getText();
		String expReslt = "Count Summary";
	    Assert.assertEquals(actResult, expReslt);
	}
	@Test(priority=12,groups="smoke")
	public void Activity_Tab(){
		driver.findElement(By.xpath("//a[contains(text(),'Activity')]")).click();
		String actResult = driver.findElement(By.xpath("//input[@id='btnSearch']")).getText();
		String expReslt = "Search";
	    Assert.assertEquals(actResult, expReslt);
	}
	@AfterSuite(groups = "Smoke")
	public void close_Browse() {
		driver.quit();
	}


}
