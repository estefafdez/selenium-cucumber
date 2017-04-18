package com.test.step.defintions;


import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;

/**
 * This class contains methods to allow you to handle Javascript 
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class JavascriptHandlingSteps {
	WebDriver driver;
	
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(JavascriptHandlingSteps.class);
	
	public JavascriptHandlingSteps(){
		 driver= Hooks.driver;
	}

	/** Handle and accept a JavaScript alert */
	@And("^I accept alert$")
	public void handleAlert()
	{
		try{
		   WebDriverWait wait = new WebDriverWait(driver, 10);
		   Alert alert = wait.until(ExpectedConditions.alertIsPresent());		  
		   alert.accept();
		   log.info("The alert was accepted successfully.");
		}catch(Throwable e){
		   log.error("Error came while waiting for the alert popup. "+e.getMessage());
		}
	}

	/** Handle and dismiss a JavaScript alert */
	@And("^I dismiss alert$")
	public void dismissAlert()
	{
		try{
		   WebDriverWait wait = new WebDriverWait(driver, 10);
		   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		   alert.dismiss();		
		   log.info("The alert was dismissed successfully.");
		}catch(Throwable e){
		   log.error("Error came while waiting for the alert popup. "+e.getMessage());
		}
	}
		
}
