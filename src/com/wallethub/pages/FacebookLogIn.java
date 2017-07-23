package com.wallethub.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FacebookLogIn {
    
    WebDriver driver;
    Properties propertiesObject;
    WebDriverWait myWait;
 
    public FacebookLogIn(WebDriver driver,String URL,int waitTime) throws FileNotFoundException, IOException{
	System.out.println("**@Facebook LogIn Page Class - Constructor**");
	this.driver = driver;
	propertiesObject = new Properties();
	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\wallethub\\properties\\LogInPage.properties"));
	String userName = propertiesObject.getProperty("UserName");
	loadFacebookURL(URL,waitTime,userName,propertiesObject.getProperty("Key"));
	
    }
    
    public void loadFacebookURL(String URL,int waitTime,String userName,String passWord){
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get(URL);
	implicitWaitFunction(waitTime);
	System.out.println("Successfully Loaded>>"+driver.getCurrentUrl());
	getTitle();
	driver.findElement(By.id(propertiesObject.getProperty("userNameById"))).sendKeys(userName);;
	driver.findElement(By.id(propertiesObject.getProperty("keyById"))).sendKeys(passWord);;
	driver.findElement(By.id(propertiesObject.getProperty("logInButtonById"))).click();
	
    }
    
    public String getTitle(){
	String title = driver.getTitle();
	System.out.println("Page title is>>"+title);
	return title;
    }
    
    public void implicitWaitFunction(int waitInSec){
	driver.manage().timeouts().implicitlyWait(waitInSec, TimeUnit.SECONDS);
	System.out.println("Implicit wait of "+waitInSec+" is applied");

    }
    
    public void explicitWaitFunction(int waitInSec,String locator){
	myWait = new WebDriverWait(driver, waitInSec);
	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	
    }
    
 
    public void postUpdate(String textToBePosted){
	explicitWaitFunction(10,propertiesObject.getProperty("PostupdateByXpath"));
	System.out.println("**Located Post Update field**");
	driver.findElement(By.xpath(propertiesObject.getProperty("PostupdateByXpath"))).sendKeys(textToBePosted);
	explicitWaitFunction(10,propertiesObject.getProperty("PostButtonByXpath"));
	System.out.println("**Located Post Button**");
	driver.findElement(By.xpath(propertiesObject.getProperty("PostButtonByXpath"))).click();
    }
    
    public void logOutFunction(){
	System.out.println("**Going to Logout**");
	driver.findElement(By.id(propertiesObject.getProperty("usernavigationById"))).click();
	driver.findElement(By.partialLinkText("Log out")).click();
	driver.quit();
    }
    
}
