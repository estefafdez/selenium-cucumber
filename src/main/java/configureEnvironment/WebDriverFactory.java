package configureEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.common.SauceOnDemandAuthentication;

import drivers.RemoteDriver;
/**
 * WebDriver Factory Class to select local driver or remote driver (SauceLabs).
 * @author Francisco Fernandez <ffgonzalez1989@gmail.com>
 * @author Estefanía Fdez Muñoz <estefafdez@gmail.com>
 * @author Rubén Nogueroles Bernal <rubennogueroles@gmail.com>
 */

public class WebDriverFactory {

    Logger log = Logger.getLogger(WebDriverFactory.class);
    FirefoxProfile firefoxProfile;
    ChromeOptions options;

    private String seleniumURI = SauceHelpers.buildSauceUri();

    public WebDriver getDriver(String driverType, String os, DesiredCapabilities capabilities, SauceOnDemandAuthentication authentication)
	    		throws MalformedURLException {
	
	    		/******** The driver selected is Local: Firefox  ********/
    	
	        if (driverType.equalsIgnoreCase("FIREFOX")) {
	        	 	System.setProperty("webdriver.gecko.driver", "resources/files/software/"+os+"/geckodriver");    
	            	return new FirefoxDriver();
	        }
	        
	        /******** The driver selected is Local: Chrome  ********/
	   
	        else if (driverType.equalsIgnoreCase("CHROME")) {
	            System.setProperty("webdriver.chrome.driver", "resources/files/software/"+os+"/chromedriver");            
	            return new ChromeDriver();
	        } 
	        
	        /******** The driver selected is Local: Internet Explorer ********/
	        
	        else if (driverType.equalsIgnoreCase("INTERNET EXPLORER")) {
	        		System.setProperty("webdriver.ie.driver", "resources/files/software/"+os+"/IEDriverServer.exe");
	        		return new InternetExplorerDriver();
	        	} 
	        
	        /******** The driver selected is Remore (SauceLabs)
	         * If the Remote Driver is selected, the connection into SauceLabs is mandatory  ********/
	        
	        else if (driverType.equalsIgnoreCase("REMOTE")) {
            return new RemoteDriver(new RemoteWebDriver(new URL( "http://" + authentication.getUsername() + ":"
            			+ authentication.getAccessKey() + seleniumURI + "/wd/hub"), capabilities));

        } else {
        		/******** The driver is not selected  ********/
        	
            log.error("The Driver is not selected properly");
            return null;
        }
    }
}