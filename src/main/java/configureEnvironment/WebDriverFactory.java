package configureEnvironment;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


/**
 * This class select and configure the Driver according to your browser selection on the POM.
 * @author estefafdez
 *
 */
public class WebDriverFactory 
{
	static WebDriver driver = null;
	static Properties prop = new Properties();
	static String browser=null;

	/**
	 * Get the Browser from the POM
	 */
	public static String getBrowser()
	{
	    browser = System.getProperty("browser");
	    System.out.println(browser);
		if(browser == null)
			browser = "Firefox";
		return browser;
	}
	   
			
	 public  static WebDriver CreateNewWebDriver(String browser){		
				/******** The driver selected is  Firefox  ********/  	
			    if (browser.equalsIgnoreCase("FIREFOX")) {
			    		driver = new FirefoxDriver();
			    }
					        
			    /******** The driver selected is Chrome  ********/
					   
			    else if (browser.equalsIgnoreCase("CHROME")) {
			    		System.setProperty("webdriver.chrome.driver", "resources/files/software/chromedriver");         
			    		driver = new ChromeDriver();
			    } 
					        
			    /******** The driver selected is Internet Explorer ********/        
			    else if (browser.equalsIgnoreCase("INTERNET EXPLORER")) {
			    		System.setProperty("webdriver.ie.driver", "resources/files/software/IEDriverServer.exe");
			    		driver = new InternetExplorerDriver();
				 } 
			    else {
				        	
				/******** The driver is not selected  ********/
			    		System.out.println("The Driver is not selected properly, invalid name: " + browser);
				        return null;
				   }
	    
			    /******** Clean Cookies, maxinize and declare Timeout on the Driver *******/
			    driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    	    
	    return driver;
        }
	}
