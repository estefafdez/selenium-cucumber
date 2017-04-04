package com.test.step_defintions;


import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to handle Javascript 
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class JavascriptHandlingSteps {
	WebDriver driver;
	
	public JavascriptHandlingSteps(){
		 driver= Hooks.driver;
	}

		//Step to handle java script
		@Then("^I accept alert$")
		public void handleAlert()
		{

		}

		//Steps to dismiss java script
		@Then("^I dismiss alert$")
		public void dismissAlert()
		{

		}
		
		
}
