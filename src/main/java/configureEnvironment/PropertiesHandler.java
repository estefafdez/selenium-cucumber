package configureEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefintions.Hooks;


/**
 * Custom class to handle the properties files. 
 * @author estefafdez
 *
 */
public class PropertiesHandler {
	static WebDriver driver;
    private static String properties = "selector.properties";
    private static Properties selectorProp = new Properties();
    private static InputStream in = WebDriverFactory.class.getResourceAsStream("/selectors/selector.properties");    
    static String selector;
    
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(PropertiesHandler.class);
    
    public PropertiesHandler(){
		 driver= Hooks.driver;
	}

    /**
     * Get the selector of a properties file with its key.
     */
     public static String getSelectorFromProperties(String key){
        try {
        	log.info("***********************************************************************************************************");
        	log.info("[ Properties Configuration ] - Read the selector properties from: " + properties);
        	selectorProp.load(in);
        	selector = selectorProp.getProperty(key);
            
        } catch (IOException e) {
        	log.error("getSelectorFromProperties Error", e);
        }
        
        return selector;
    }
 

     /**
      * Get the complete element with a selected type and key.
      */
     public static By getCompleteElement(String type, String key) {
         By result;
         int cases = 8;
         String selector = PropertiesHandler.getSelectorFromProperties(key);
         
         switch (cases) {
             case 1: type = "className";
                 result = By.className(selector);
                 break;
             case 2: type = "cssSelector";
                 result = By.cssSelector(selector);
                 break;
             case 3: type = "id";
                 result = By.id(selector);
                 break;
             case 4: type = "linkText";
                 result = By.linkText(selector);
                 break;
             case 5: type = "name";
                 result = By.name(selector);
                 break;
             case 6: type = "partialLinkText";
                 result = By.partialLinkText(selector);
                 break;
             case 7: type = "tagName";
                 result = By.tagName(selector);
                 break;
             case 8: type = "xpath";
                 result = By.xpath(selector);
                 break;
             default:
                 throw new IllegalArgumentException("By type " + type + " is not found.");
         }
         return result;
     }

}
