package com.selenium.configure.environment;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * This class select and configure the Driver according to your browser selection on the POM.
 * @author estefafdez
 * @aurhor ffgonzalez
 *
 */
public class WebDriverFactory {	
	static String resourceFolder="resources/files/software/";
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(WebDriverFactory.class);
    
	private static WebDriverFactory instance = null;
	    
    private WebDriverFactory() {    
    }
    
    /**
     * Singleton pattern
     * @return a single instance
     */
    public static WebDriverFactory getInstance() {
        if (instance == null) {
            instance = new WebDriverFactory();
        }
        return instance;
    }    
	
		
	 public static WebDriver createNewWebDriver(String browser, String os){	
		 WebDriver driver;
			
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
