package com.wallethub.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WalletHubLightLogin{
    
    static WebDriver driver;
    static Properties propertiesObject;
    Actions actionObject;
    WebDriverWait myWait;
    
    public WalletHubLightLogin(WebDriver driver,String URL) throws FileNotFoundException, IOException, InterruptedException{
	System.out.println("**@Wallethub Light LogIn Page Class - Constructor**");
	this.driver = driver;
	actionObject = new Actions(driver);
	propertiesObject = new Properties();
	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\wallethub\\properties\\LogInPage.properties"));
	String userName = propertiesObject.getProperty("Email");
	loadWalletHubURL(URL,userName,propertiesObject.getProperty("Password"));
	
    }
    
    public void loadWalletHubURL(String URL,String userName,String passWord) throws InterruptedException{
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get(URL);
	implicitWaitFunction(10);
	System.out.println("Successfully Loaded>>"+driver.getCurrentUrl());
	String pageTitle = driver.getTitle();
	System.out.println("Logged Into>>"+pageTitle);
	driver.findElement(By.name(propertiesObject.getProperty("EmailByName"))).sendKeys(userName);
	driver.findElement(By.name(propertiesObject.getProperty("PassByName"))).sendKeys(passWord);
	driver.findElement(By.name(propertiesObject.getProperty("ConfirmPassByNam"))).sendKeys(passWord);
	WebElement checkBox = driver.findElement(By.xpath(propertiesObject.getProperty("CheckBox")));
	if(!checkBox.isSelected()){
	    checkBox.click();
	    System.out.println("**Unchecked Checkbox**");
	}else{
	    System.out.println("**Checkbox Already Unchecked**");
	}
	driver.findElement(By.xpath(propertiesObject.getProperty("JoinButtonByXpath"))).click();
	WebElement text = driver.findElement(By.xpath("//*[@id='join-login']/form/h2[3]"));
	if(text.isDisplayed()){
	    System.out.println(text.getText()+"**Hence Login using proper Cred!!");
	    //driver.findElement(By.name(propertiesObject.getProperty("EmailByName"))).sendKeys(userName);
	    driver.findElement(By.name("pw")).sendKeys(passWord);
	    driver.findElement(By.xpath(propertiesObject.getProperty("loginButtonXpath"))).click();
	    driver.get("https://wallethub.com/profile/test_insurance_company/");
	}else{
	    //driver.get("https://wallethub.com/profile/test_insurance_company/");
	}
	
	hoveringAndRating();
	
    }
    
    
    public void hoveringAndRating() throws InterruptedException{
	System.out.println("**Going To Hover**");
	implicitWaitFunction(5);
	driver.findElement(By.xpath("//a/span[text()='Write a Review']")).click();
	/*WebElement rating = driver.findElement(By.className("wh-rating-choices-holder"));
	actionObject.moveToElement(rating).click().build().perform();
	implicitWaitFunction(10);
	WebElement fifthStar = driver.findElement(By.xpath(propertiesObject.getProperty("fifthStarByXpath")));
	if(!fifthStar.isSelected()){
	    actionObject.moveToElement(fifthStar).click().build().perform();
	    System.out.println("**Selected star 5**");
	    implicitWaitFunction(10);
	    //policyDropDownSelection();
	}else{
	    System.out.println("**Already Selected 5**");
	}*/
	
	policyDropDownSelection();
	
    }
    
    
    public void implicitWaitFunction(int waitInSec){
	driver.manage().timeouts().implicitlyWait(waitInSec, TimeUnit.SECONDS);
	System.out.println("Implicit wait of "+waitInSec+" is applied");

    }
    
    public void explicitWaitFunction(int waitInSec,String locator){
	myWait = new WebDriverWait(driver, waitInSec);
	myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	
    }
    
    public void policyDropDownSelection() throws InterruptedException{
	driver.findElement(By.xpath("//*[@id='reviewform']/div[1]/div/div/span[2]/i")).click();
	driver.findElement(By.xpath(".//*[@id='reviewform']/div[1]/div/ul/li[2]/a")).click();
	System.out.println("Selected health option from drop down");
	Thread.sleep(500);
	
	explicitWaitFunction(5,"//*[@id='overallRating']/a[5]");	
	WebElement fiveStar = driver.findElement(By.xpath("//span[@id='overallRating']/a[5]"));
	//Action clickOnStar = actionObject.moveToElement(fiveStar).click().build();
	fiveStar.click();
	
	WebElement textBoxArea = driver.findElement(By.xpath(propertiesObject.getProperty("textAreaByXpath")));
	String concatenated = propertiesObject.getProperty("text1")+
		propertiesObject.getProperty("text2")+
		propertiesObject.getProperty("text3");
	textBoxArea.sendKeys(concatenated);
	Thread.sleep(500);
	
	driver.findElement(By.xpath(propertiesObject.getProperty("submitButtonByXpath"))).click();
	System.out.println("**Review  has been submitted**");
    }
    
    public void reviewhasUpdated(String userName){
	driver.get("https://wallethub.com/profile/arunjose100/reviews/");
	List<WebElement> texts = driver.findElements(By.xpath("//div[contains(@id,review)]/p"));
	if(texts.size()<=0){
	    System.out.println(userName+" has not updated any reviews");
	}else{
	    System.out.println(userName+" has updated reviews and updated comments are as follows");
	    for(WebElement text:texts){
		System.out.println("*********");
		System.out.println(text.getText());
	    }
	}
    }
    
    public void logOutFunction(){
	explicitWaitFunction(10,"//a[@class='user' and @data-menu='m-user']");
	driver.findElement(By.xpath("//a[@class='user' and @data-menu='m-user']")).click();
	driver.findElement(By.id("logout-link")).click();
	driver.quit();
    }
    

}
