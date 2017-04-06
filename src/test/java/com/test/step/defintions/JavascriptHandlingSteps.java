package com.test.step.defintions;


import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	/** Handle and accept a JavaScript alert */
	@Then("^I accept alert$")
	public void handleAlert()
	{
		try{
		   //Wait 10 seconds until the alert is present
		   WebDriverWait wait = new WebDriverWait(driver, 10);
		   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		  
		   //Accept the alert.
		   alert.accept();
		   System.out.println("The alert was accepted successfully.");
		}catch(Throwable e){
		   System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
		}

	}

	/** Handle and dismiss a JavaScript alert */
	@Then("^I dismiss alert$")
	public void dismissAlert()
	{
		try{
		   //Wait 10 seconds until the alert is present
		   WebDriverWait wait = new WebDriverWait(driver, 10);
		   Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		  
		   //Dismiss the alert.
		   alert.dismiss();		
		   System.out.println("The alert was dismissed successfully.");
		}catch(Throwable e){
		   System.err.println("Error came while waiting for the alert popup. "+e.getMessage());
		}

	}
		
		
}
