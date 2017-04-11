package com.selenium.configure.environment;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * This class contains methods to manage the latest release of the Geckodriver, ChromeDriver and IEDriver.
 * @author ffgonzalez
 */
public class Main {
	 /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(Main.class);
    private static Main instance = null;
    
    private Main() {    	
    }
    
    /**
     * Singleton pattern
     * @return a single instance
     */
    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }    

    public static void main(String[] args) {

        // http://chromedriver.storage.googleapis.com/index.html    --> Chrome
        // http://selenium-release.storage.googleapis.com           --> IE
        // https://github.com/mozilla/geckodriver/releases          --> FireFox
        
        String repoURL = "http://selenium-release.storage.googleapis.com";
        String driverVersion = "/3.3/";
        String driverName = "IEDriverServer_x64_3.3.0.zip";
        String resourcePath = "src\\main\\resources\\";

        try {
            HandlerRepo.downloadFile(repoURL + driverVersion + driverName, resourcePath);
            HandlerRepo.unZipIt(resourcePath + driverName, resourcePath);
            HandlerRepo.deleteZip(resourcePath + driverName);
        } catch (IOException e) {
        	log.error("manageDriver Error", e);
        }

    }

}
