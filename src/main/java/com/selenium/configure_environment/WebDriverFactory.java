package com.selenium.configure_environment;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;



/**
 * This class select and configure the Driver according to your browser selection on the POM.
 * @author estefafdez
 * @aurhor ffgonzalez
 *
 */
public class WebDriverFactory {
	private static WebDriver driver   = null;	
	static String resourceFolder="resources/files/software/";
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(WebDriverFactory.class);
	
		
	 public static WebDriver CreateNewWebDriver(String browser, String os){		
			
		 /******** The driver selected is Local: Firefox  ********/    	
		 if ("FIREFOX".equalsIgnoreCase(browser)) {
			 if("WINDOWS".equalsIgnoreCase(os)){
				 System.setProperty("webdriver.gecko.driver", resourceFolder+os+"/geckodriver.exe");    
			 }
			 else{
				 System.setProperty("webdriver.gecko.driver", resourceFolder+os+"/geckodriver");    
			 }
		     driver = new FirefoxDriver();	
		 }
					        
		 /******** The driver selected is Chrome  ********/
					   
	     else if ("CHROME".equalsIgnoreCase(browser)) {
	    	 if("WINDOWS".equalsIgnoreCase(os)){
	    		 System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver.exe");            
	    	 }
	    	 else{
	    		 System.setProperty("webdriver.chrome.driver", resourceFolder+os+"/chromedriver");        		 
	    	 }
	         driver = new ChromeDriver();
	     }  
					        
		 /******** The driver selected is Internet Explorer ********/        
	     else if ("INTERNET EXPLORER".equalsIgnoreCase(browser)) {
	    	 System.setProperty("webdriver.ie.driver", resourceFolder+os+"/IEDriverServer.exe");
			 driver = new InternetExplorerDriver();
	     } 
		 /******** The driver is not selected  ********/
	     else {
	    	 log.error("The Driver is not selected properly, invalid name: " + browser + ", " + os);
			 return null;
		 }
			    	    
	    return driver;
        }
	}
