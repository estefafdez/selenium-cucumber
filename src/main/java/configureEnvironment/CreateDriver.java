package configureEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

/**
 * Custom interface for configure  and create the webdriver.
 * @author estefafdez
 * @author ffgonzalez
 *
 */
public class CreateDriver {
    private static String browser = null;
    private static String os = null;
    private static Properties prop = new Properties();
    private static InputStream in = WebDriverFactory.class.getResourceAsStream("/test.properties");    
    private static WebDriver driver;

    /**
     * Get the Browser from the POM
     */
     public static WebDriver initConfig(){
        try {
            prop.load(in);
            browser = prop.getProperty("browser");
            os = prop.getProperty("os");
            System.out.println(browser);
            System.out.println(os);
//            if(browser == null)
//                browser = "Firefox";
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /****** Load the driver *******/
        driver = WebDriverFactory.CreateNewWebDriver(browser, os);
        
        /******** Clean Cookies, maxinize and declare Timeout on the Driver *******/
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        
        return driver;
    }
}
