package com.wallethub.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.wallethub.general.BaseClass;
import com.wallethub.pages.FacebookLogIn;
import com.wallethub.pages.WalletHubLightLogin;

public class WalletHubLightLoginTest extends BaseClass{
    
    static WebDriver driver;
    static Properties propertiesObject;
    static WalletHubLightLogin walletHubLoginObject;
    

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
	System.out.println("**@Wallethub LogIn Test Class**");
	propertiesObject = new Properties();
	propertiesObject.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\com\\wallethub\\properties\\LogInPage.properties"));
	driver = browserInitialization(propertiesObject.getProperty("BrowserType"));
	
	String userName = propertiesObject.getProperty("Email");
	
	walletHubLoginObject = new WalletHubLightLogin(driver,
	propertiesObject.getProperty("URLWallet"));
	System.out.println(userName+"<<Logged In Successfully>>");
	
	walletHubLoginObject.reviewhasUpdated(userName);
	walletHubLoginObject.logOutFunction();
	System.out.println("LoggedOut Successfully");


    }

}
