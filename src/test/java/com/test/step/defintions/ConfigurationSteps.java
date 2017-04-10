package com.test.step.defintions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.And;
/**
 * This class contains methods to allow you to generate methods to configure.
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ConfigurationSteps {
	WebDriver driver;
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(ConfigurationSteps.class);
    
	public ConfigurationSteps(){
		 driver= Hooks.driver;
	}

  	
  	/** Close the driver instance */
	@And("^I close browser$")
	public void close()
	{
		log.info("Closing browsers");
		driver.close();
	}
	

}
