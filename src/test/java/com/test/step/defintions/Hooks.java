package com.test.step.defintions;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.selenium.configure.environment.CreateDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	
		public static WebDriver driver;
		Logger log = Logger.getLogger(Hooks.class);
		Scenario scenario = null;
		
		@Before
		public void before(Scenario scenario) {
			this.scenario = scenario;
		}

		@Before
	    /**
	     * Delete all cookies at the start of each scenario to avoid
	     * shared state between tests
	     */
	    public void initDriver() throws MalformedURLException {
			log.info("***********************************************************************************************************");
			log.info("[ Configuration ] - Initializing driver configuration");
			log.info("***********************************************************************************************************");
	    	driver = CreateDriver.initConfig();	    
	    	
	    	log.info("***********************************************************************************************************");
			log.info("[ Scenario ] - "+scenario.getName());
			log.info("***********************************************************************************************************");
	    }	 
	     
	 	@After
	 	/**
	     * Embed a screenshot in test report if test is marked as failed
	     */
	    public void embedScreenshot(Scenario scenario) {
	       
	        if(scenario.isFailed()) {
		        try {
		        	scenario.write("The scenario failed.");
		        	scenario.write("Current Page URL is " + driver.getCurrentUrl());
		            byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		            scenario.embed(screenshot, "resources/screenshot");
		        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
		            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		        }	        
	        }
	        
			log.info("***********************************************************************************************************");
			log.info("[ Driver Status ] - Clean and close the intance of the driver");
			log.info("***********************************************************************************************************");
	        driver.quit();
	        
	    }
}
