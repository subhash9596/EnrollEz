package com.kt.enrollez;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Broker_AddEmployer_TestCase {
	
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
		Thread.sleep(1000);
	    driver.findElement(By.xpath("//img[@class='employer-add-icon']")).click();
		
	}	
	@Test(priority=2,groups="Regression",dataProvider="AddEmployer")
	public void AddEmployer_CompanyInformation(String tcId,String tcDes,String EmployerName,String taxId,String Lane,String Street2,String city,String country,
			String state,String zip,String desc,String stardDate,String endDate,String amount,String expResult) throws Exception{
		driver.findElement(By.xpath("//input[@id='Name']")).clear();
		driver.findElement(By.xpath("//input[@id='Name']")).sendKeys(EmployerName);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='TaxId']")).clear();
		driver.findElement(By.xpath("//input[@id='TaxId']")).sendKeys(taxId);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='Lane']")).click();
		driver.findElement(By.xpath("//input[@id='Lane']")).sendKeys(Lane);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='Street']")).clear();
		driver.findElement(By.xpath("//input[@id='Street']")).sendKeys(Street2);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='City']")).clear();
		driver.findElement(By.xpath("//input[@id='City']")).sendKeys(city);
		Thread.sleep(500);
		Select selCountry = new Select(driver.findElement(By.xpath("//select[@id='CountryId']")));
		selCountry.selectByVisibleText(country);
		Thread.sleep(500);
		Select selState = new Select(driver.findElement(By.xpath("//select[@id='ddlEmployerStateId']")));
		selState.selectByVisibleText(state);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='Zip']")).clear();
		driver.findElement(By.xpath("//input[@id='Zip']")).sendKeys(zip);
		Thread.sleep(500);
		driver.findElement(By.xpath("//textarea[@id='Description']")).clear();
		driver.findElement(By.xpath("//textarea[@id='Description']")).sendKeys(desc);
		//Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='chkUSA']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='ContractStartDate']")).clear();
		driver.findElement(By.xpath("//input[@id='ContractStartDate']")).sendKeys(stardDate);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='ContractEndDate']")).clear();
		driver.findElement(By.xpath("//input[@id='ContractEndDate']")).sendKeys(endDate);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='ContractAmount']")).clear();
		driver.findElement(By.xpath("//input[@id='ContractAmount']")).sendKeys(amount);
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='btnCreate']")).click();
		Thread.sleep(500);
		
		if(tcDes.equals("BlankInfo"))
		{  System.out.println(1111);
			String actResult = driver.findElement(By.xpath("//label[@class='error']//label[contains(text(),'Employer Name')]")).getText();
			System.out.println(2);
			Assert.assertEquals(actResult, expResult);
			System.out.println(3);
		} 
		else if(tcDes.equals("InValid")){
			System.out.println(4);
			String actResult1 = driver.findElement(By.xpath("//label[@id='msgLocation']")).getText();
			System.out.println(5);
			Assert.assertEquals(actResult1, expResult);
			System.out.println(6);
		}
		
		else if(tcDes.equals("Valid")) {
			System.out.println(7);
			String actResult1 = driver.findElement(By.xpath("//div[@class='success']")).getText();
			System.out.println(8);
			Assert.assertEquals(actResult1, expResult);
			System.out.println(9);
		}
	
	}
	 @DataProvider
	  public Object[][] AddEmployer() {
	    return new Object[][] {
//	    	 new Object[] { "TC01", "BlankInfo"," "," "," "," "," ",">> Select",">> Select"," "," "," ",""," ","Employer Name"},
//	    	 new Object[] { "TC02", "InValid","KT16 ","57488555","Street -01","Street -01","pune ","India","Goa","60012","TestDescripton","12/01/2019","12/31/2019","1000","Please select any Country Location ."},
	    	 new Object[] { "TC03", "Valid","KT19","5865847584","Street -01","Street -01","pune ","India","Goa","60012","TestDescripton","12/01/2019","12/31/2019","1000","You have successfully added employer basic information. Please select applications for authorization. "},
	    };			    
      }
	 @Test(priority=3,groups="Regression")
	 public void Verify_Applications() throws Exception{
		 //Select OnBoarding Link 
		 Thread.sleep(1000);
		 System.out.println(1);
		 driver.findElement(By.id("Checkbox1")).click();
		 System.out.println(2);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='application3']")).click();
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//tr[3]//td[2]//input[1]")).click();
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//tr[4]//td[2]//input[1]")).click();
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//tr[6]//td[2]//input[1]")).click();
		 Thread.sleep(500);
		 
		 driver.findElement(By.xpath("//input[@id='btnCreate']")).click();
		 
		String actMessage=  driver.findElement(By.xpath("//td[contains(text(),'Application(s) authorized successfully. Please add')]")).getText();
		String expMessage = "Application(s) authorized successfully. Please add one or more representative(s). ";
		Assert.assertEquals(actMessage, expMessage); 
	 }
	 @Test(priority=4,groups="Regression",dataProvider="CR")
	 public void Client_Representative(String tcId,String tcDesc,String Designation,String FName,String LName,String Street1 ,String Street2,String City,String State ,String Zip,String Phone,
		       String Extension,String Cell ,String Email,String expMessge) throws Exception{
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@id='RepresentativeDesignation']")).sendKeys(Designation);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeFirstName']")).sendKeys(FName);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeLastName']")).sendKeys(LName);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeLane']")).sendKeys(Street1);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeStreet']")).sendKeys(Street2);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeCity']")).sendKeys(City);
		 Thread.sleep(500);
		 
		 Select selstate = new Select(driver.findElement(By.xpath("//select[@id='RepresentativeStateId']")));
		 selstate.selectByVisibleText(State);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeZip']")).sendKeys(Zip);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativePhone']")).sendKeys(Phone);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeExtension']")).sendKeys(Extension);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeCell']")).sendKeys(Cell);
		 Thread.sleep(500);
		 driver.findElement(By.xpath("//input[@id='RepresentativeEmail']")).sendKeys(Email);
		 Thread.sleep(500);
		 
		 driver.findElement(By.xpath("//input[@id='btnCreate']")).click();
		 String actMessage= driver.findElement(By.xpath("//div[@class='content']//tr[1]//td[2]//label[1]")).getText();
		 String expMessage  = "required.";
		 Assert.assertEquals(actMessage, expMessage);
		 
	 }
	 @DataProvider
	  public Object[][] CR() {
	    return new Object[][] {
	    	 new Object[] { "KTBTC01", "BlankInfo"," "," "," "," "," "," ",">> Select"," "," "," "," "," ","required."},
	    	 new Object[] { "KTBTC01", "BlankInfo","HR Manager ","John ","Doe ","Street-01 ","Street-02 "," ",">> Select"," "," "," "," "," ","required."},
	    	// new Object[] { "KTBTC02", "InvalidInfo","Sunil","Kumar","Street-01","Street-02","pune","India","Goa","652152","9999","test4gmail.com","Please enter a valid email address."},
            // new Object[]  { "KTBTC03", "ValidInfo","Sunil","Kumar","Street-01","Street-02","pune","India","Goa","652152","9999999999","test4@gmail.com","Broker Representative information has been added successfully. "},
	     
	    };			    
      }
	 
	 
	@AfterSuite(groups = "Smoke")
	public void close_Browse() {
		driver.quit();
	}

}
