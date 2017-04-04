package com.test.step.defintions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to generate methods to configure.
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ConfigurationSteps {
	WebDriver driver;
	
	public ConfigurationSteps(){
		 driver= Hooks.driver;
	}

	
  	// step to print configuration
  	@Then("^I print configuration$") 
  	public void printConfiguration()
  	{

  	}
	

}
