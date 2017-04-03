package configureEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


/**
 * Custom class to configure  and create the webdriver.
 * @author estefafdez
 * @author ffgonzalez
 *
 */
public class CreateDriver {
    private static String browser, os;
    private static String logLevel;
    private static String properties = "test.properties";
    private static Properties prop = new Properties();
    private static InputStream in = WebDriverFactory.class.getResourceAsStream("/test.properties");    
    private static WebDriver driver; 
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(CreateDriver.class);

    /**
     * Get the Browser from the POM
     */
     public static WebDriver initConfig(){
        try {
        	log.info("***********************************************************************************************************");
        	log.info("[ POM Configuration ] - Read the basic properties configuration from: " + properties);
            prop.load(in);
            browser = prop.getProperty("browser");
            os = prop.getProperty("os");
            logLevel = prop.getProperty("logLevel");
            
        } catch (IOException e) {
        	log.error("initConfig Error", e);
        }
        
        /******** POM Information ********/
        log.info("[ POM Configuration ] - OS: " + os + " | Browser: " + browser + "|");
        log.info("[ POM Configuration ] - Logger Level: " + logLevel);
        log.info("***********************************************************************************************************");
        
        /****** Load the driver *******/
        driver = WebDriverFactory.CreateNewWebDriver(browser, os);
       
        
        /******** Clean Cookies, maxinize and declare Timeout on the Driver *******/
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        return driver;
    }

}
