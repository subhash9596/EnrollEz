package com.kt.enrollez;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Broker_Representaive_TestCase {
	
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
		driver.findElement(By.xpath("//img[@class='detail-icon']")).click();
		driver.findElement(By.xpath("//div[@class='quickLinkFont']//a[contains(text(),'Add')]")).click();
	}	
	    @Test(priority=2,groups="Regression",dataProvider="Cancel")
		public void Verify_Cancel_Broker(String tcId,String tcDes,String repFname,String repLname,String stree1,String street2,
				String city,String country,String state,String repZip,String repContact,String email,String expMessage) throws Exception{
			Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeFirstName']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeFirstName']")).sendKeys(repFname);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeLastName']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeLastName']")).sendKeys(repLname);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeStreet1']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeStreet1']")).sendKeys(stree1);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeStreet2']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeStreet2']")).sendKeys(street2);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeCity']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeCity']")).sendKeys(city);
		    Thread.sleep(1000);
		    //Select dropdown value using select 
		    Select selectcoun = new Select(driver.findElement(By.xpath("//select[@id='RepresentativeCountryId']")));
		    selectcoun.selectByVisibleText(country);
		    Select selectsta = new Select(driver.findElement(By.xpath("//select[@id='RepresentativeStateId']")));
		    selectsta.selectByVisibleText(state);
		   
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeZip']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeZip']")).sendKeys(repZip);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeContactNumber']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeContactNumber']")).sendKeys(repContact);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//input[@id='RepresentativeEmail']")).clear();
		    driver.findElement(By.xpath("//input[@id='RepresentativeEmail']")).sendKeys(email);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@id='btnCancel']")).click();
		    String actResult =driver.findElement(By.xpath("/html/body/center/div/table/tbody/tr/td[2]/table[2]/tbody/tr/td[2]/table/tbody/tr/td[1]/div/h2")).getText();
		 	Assert.assertEquals(actResult, expMessage);	
			Thread.sleep(1000);
			
		    driver.findElement(By.xpath("//div[@class='quickLinkFont']//a[contains(text(),'Add')]")).click();
	    }
	    
		@DataProvider
		  public Object[][] Cancel() {
		    return new Object[][] {
		    	
	             new Object[] { "TC01", "VerifyCancelFunctionality","Sunil","Kumar","Street-01","Street-02","pune","India","Goa","652152","9999999999","test4@gmail.com","Broker Representative List"},
		     
		    };
		}
		    
	        @Test(priority=3,groups="Regression",dataProvider="AddRep")
			public void Add_BrokerRep(String tcId,String tcDes,String repFname,String repLname,String stree1,String street2,
					String city,String country,String state,String repZip,String repContact,String email,String expMessage) throws Exception{
  
				Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeFirstName']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeFirstName']")).sendKeys(repFname);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeLastName']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeLastName']")).sendKeys(repLname);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeStreet1']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeStreet1']")).sendKeys(stree1);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeStreet2']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeStreet2']")).sendKeys(street2);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeCity']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeCity']")).sendKeys(city);
			    Thread.sleep(1000);
			    
			    //Select dropdown value using select 
			    Select selectcoun = new Select(driver.findElement(By.xpath("//select[@id='RepresentativeCountryId']")));
			    selectcoun.selectByVisibleText(country);
			    Select selectsta = new Select(driver.findElement(By.xpath("//select[@id='RepresentativeStateId']")));
			    selectsta.selectByVisibleText(state);
			   
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeZip']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeZip']")).sendKeys(repZip);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeContactNumber']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeContactNumber']")).sendKeys(repContact);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='RepresentativeEmail']")).clear();
			    driver.findElement(By.xpath("//input[@id='RepresentativeEmail']")).sendKeys(email);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='btnCreate']")).click();
			    Thread.sleep(1000);
			    
//		    	driver.findElement(By.xpath("//img[@class='close-icon']")).click();
			    if (tcDes.equals("BlankInfo")){
			    	 Thread.sleep(1000);
			    	 String actResult =driver.findElement(By.xpath("//tr[9]//td[2]//label[1]")).getText();
			 	     Assert.assertEquals(actResult, expMessage);	
		       }else if(tcDes.equals("InvalidInfo")){
		    	     Thread.sleep(1000);
		    	     String actInvalid =  driver.findElement(By.xpath("//label[contains(text(),'Please enter a valid email address.')]")).getText();
		  	         Assert.assertEquals(actInvalid, expMessage);
		       }else if(tcDes.equals("ValidInfo")) {
		        		Thread.sleep(1000);
		        	    String actValid =  driver.findElement(By.xpath("//div[@class='success']")).getText();;
		    	  		Assert.assertEquals(actValid, expMessage);
		        	}
	        }
	             
		    @DataProvider
			  public Object[][] AddRep() {
			    return new Object[][] {
			    	 new Object[] { "KTBTC01", "BlankInfo"," "," "," "," "," ",">> Select",">> Select"," "," "," ","required."},
			    	 new Object[] { "KTBTC02", "InvalidInfo","Sunil","Kumar","Street-01","Street-02","pune","India","Goa","652152","9999","test4gmail.com","Please enter a valid email address."},
		             new Object[] { "KTBTC03", "ValidInfo","Sunil","Kumar","Street-01","Street-02","pune","India","Goa","652152","9999999999","test4@gmail.com","Broker Representative information has been added successfully. "},
			     
			    };			    
	           }
		    
		    @Test(priority=4)
		    public void VerifyClick() throws Exception{
		    	driver.findElement(By.xpath("//a[contains(text(),'Sunil')]")).click();
		    	Thread.sleep(1000);
	    	   driver.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();

		    }
		    
		    @Test(priority=5,groups="Regression",dataProvider="UpdateRep")
			public void Update_BrokerRep(String tcId,String tcDes,String ContactName,String stree1,String street2,
					String city,String country,String state,String repZip,String repContact,String email,String expMessage) throws Exception{
//		    	
//		    	Thread.sleep(1000);
//		    	driver.findElement(By.xpath("//a[contains(text(),'Sunil')]")).click();
//		    	Thread.sleep(1000);
//		    	driver.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();
		    	
				Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='ContactName']")).clear();
			    driver.findElement(By.xpath("//input[@id='ContactName']")).sendKeys(ContactName);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='Street1']")).clear();
			    driver.findElement(By.xpath("//input[@id='Street1']")).sendKeys(stree1);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='Street2']")).clear();
			    driver.findElement(By.xpath("//input[@id='Street2']")).sendKeys(street2);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='City']")).clear();
			    driver.findElement(By.xpath("//input[@id='City']")).sendKeys(city);
			    Thread.sleep(1000);
			    //Select dropdown value using select 
			    Select selectcoun = new Select(driver.findElement(By.xpath("//select[@id='CountryId']")));
			    selectcoun.selectByVisibleText(country);
			   
			    
			    Select selectsta = new Select(driver.findElement(By.xpath("//select[@id='StateId']")));
			    selectsta.selectByVisibleText(state);
			   
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='Zip']")).clear();
			    driver.findElement(By.xpath("//input[@id='Zip']")).sendKeys(repZip);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='ContactNumber']")).clear();
			    driver.findElement(By.xpath("//input[@id='ContactNumber']")).sendKeys(repContact);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='ContactEmail']")).clear();
			    driver.findElement(By.xpath("//input[@id='ContactEmail']")).sendKeys(email);
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("//input[@id='btnCreate']")).click();
			   
			    if (tcDes.equals("BlankdData")){
			    	 String actResult =driver.findElement(By.xpath("//label[contains(text(),'required.')]")).getText();
			 	     Assert.assertEquals(actResult, expMessage);	
		       }else if(tcDes.equals("InvalidInfo")){
		    	     Thread.sleep(1000);
		    	     String actInvalid =  driver.findElement(By.xpath("//label[contains(text(),'Please enter a valid email address.')]")).getText();
		  	         Assert.assertEquals(actInvalid, expMessage);
		       }else if(tcDes.equals("InvalidInfo")){
		    	   Thread.sleep(1000);
	        	    String actValid =  driver.findElement(By.xpath("//div[@class='success']")).getText();;
	    	  		Assert.assertEquals(actValid, expMessage);
		       } 
		       }

	    
		    @DataProvider
			  public Object[][] UpdateRep() {
			    return new Object[][] {
			    	 new Object[] { "TC01", "BlankdData"," "," "," "," ",">> Select",">> Select"," "," "," ","required."},
			    	 new Object[] { "TC02", "InvalidInfo","Sunil","Street-01","Street-02","pune","India","Goa","652152","9999","test4gmail.com","Please enter a valid email address."},
		             new Object[] { "TC03", "ValidInfo","Sunil","Street-01","Street-02","pune","India","Goa","652152","9999999999","test4@gmail.com","Broker Representative information has been updated successfully. "},
			     
			    };
		    }
		    @Test(priority=6)
		    public void printTable(){
		    String text = driver.findElement(By.xpath("//tr[3]//td[3]")).getText();
		    	System.out.println(text);
		    	String text2 = driver.findElement(By.xpath("//tr[3]//td[2]//a[1]")).getText();
		    	System.out.println(text2);
		    	for (int tr=1;tr<5;tr++){
			    for(int td=1;td<=5;td++){
			    		WebElement listTable= driver.findElement(By.xpath("//tr["+tr+"]//td["+td+"]"));
			    		System.out.println("Tabel Print >>"+listTable.getText());
			    	}
			    	System.out.println();
			    }	
		    }
		    @Test(priority=7)
		    public void Delete_Rep(){
		    	
		    	driver.findElement(By.xpath("//tr[3]//td[1]//a[1]//img[1]")).click();
		    	driver.switchTo().alert().accept();
		    	String actMsg = driver.findElement(By.xpath("//div[@class='success']")).getText();
		    	String exMessage= "Broker Representative information has been updated successfully. ";
		    	Assert.assertEquals(actMsg, exMessage);;	
		    }
		    @AfterSuite(groups = "Smoke")
			public void close_Browse() {
				driver.quit();
			}	    
}
