package com.selenium.configure.environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;


/**
 * Custom class to handle the properties files. 
 * @author estefafdez
 *
 */
public class PropertiesHandler {
    private static String properties = "selector.properties";
    private static Properties selectorProp = new Properties();
    private static InputStream in = WebDriverFactory.class.getResourceAsStream("/selectors/selector.properties");    
    static String selector;
    
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(PropertiesHandler.class);
    
    private PropertiesHandler(){
    	
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
         String selector = getSelectorFromProperties(key);
         
         switch (type) {
             case "className":
                 result = By.className(selector);
                 break;
             case "cssSelector":
                 result = By.cssSelector(selector);
                 break;
             case "id":
                 result = By.id(selector);
                 break;
             case "linkText":
                 result = By.linkText(selector);
                 break;
             case "name":
                 result = By.name(selector);
                 break;
             case "partialLinkText":
                 result = By.partialLinkText(selector);
                 break;
             case "tagName":
                 result = By.tagName(selector);
                 break;
             case "xpath":
                 result = By.xpath(selector);
                 break;
             default:
                 throw new IllegalArgumentException("By type " + type + " is not found.");
         }
         return result;
     }

}