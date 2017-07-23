package com.wallethub.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.wallethub.general.BaseClass;
import com.wallethub.pages.FacebookLogIn;

public class FaceBookLogInTest extends BaseClass{
    
    static WebDriver driver;
    static Properties propertiesObject;
    static FacebookLogIn faceBookLoginObject;
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
	System.out.println("**@Facebook LogIn Test Class**");
	propertiesObject = new Properties();
	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\wallethub\\properties\\LogInPage.properties"));
	driver = browserInitialization(propertiesObject.getProperty("BrowserType"));
	int waitTime = Integer.parseInt(propertiesObject.getProperty("WaitTimeForPageLoad"));
	String userName = propertiesObject.getProperty("UserName");
	faceBookLoginObject = new FacebookLogIn(driver,
		propertiesObject.getProperty("URL"),waitTime);
	System.out.println(userName+"<<Logged In Successfully>>");
	System.out.println(userName+"<<Going to update post>>");
	faceBookLoginObject.postUpdate(propertiesObject.getProperty("Postupdate"));
	System.out.println(propertiesObject.getProperty("Postupdate")+" has been updated on "+userName.split("@")[0]);
	System.out.println(userName+"<<Post updated on Wall Successfully>>");
	faceBookLoginObject.logOutFunction();
	System.out.println("LoggedOut Successfully>>Test Pass");
    }

}
