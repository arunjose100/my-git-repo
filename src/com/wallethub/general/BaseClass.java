package com.wallethub.general;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass {
    
    private static WebDriver driver;
    static DesiredCapabilities caps;
    
    public static WebDriver browserInitialization(String browserType){
	if(browserType.equalsIgnoreCase("Firefox")){
	    driver= new FirefoxDriver();
	    System.out.println("Initialized Firefox Driver");
	}else if(browserType.equalsIgnoreCase("IE")){
	    System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\drivers\\IEDriverServer.exe");
	    caps = DesiredCapabilities.internetExplorer();
	    caps.setCapability("EnableNativeEvents", false);
	    caps.setCapability("ignoreZoomSetting", true);
	    caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	    driver= new InternetExplorerDriver(caps);
	    System.out.println("Initialized Internet Explorer Driver");
	}else if(browserType.equalsIgnoreCase("Chrome")){
	    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
	    caps = DesiredCapabilities.chrome();
	    caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	    driver= new ChromeDriver(caps);
	    System.out.println("Initialized Chrome Driver");
	}
	
	return driver;
	
    }

}
