package com.test.step.defintions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.configure.environment.PropertiesHandler;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to wait for a certain event. 
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ProgressSteps {
	static WebDriver driver;
	
	public ProgressSteps(){
		 driver= Hooks.driver;
	}

	/** Wait for a specific period of time */
	@Then("^I wait for (\\d+) seconds$")
	public static void wait(int seconds) {
		new WebDriverWait(driver, seconds);
	}

	/** Wait for an element to be present for a specific period of time */
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be present$")
	public void waitForElementPresent(int seconds, String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.presenceOfElementLocated(element));
	}
	
	/** Wait for an element to be visible for a specific period of time */
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be visible$")
	public void waitForElementVisible(int seconds, String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
  
	/** Wait for an element to be enabled for a specific period of time */
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be enabled$")
	public void waitForEnable(int seconds, String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		boolean enabled = driver.findElement(element).isEnabled();
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.elementSelectionStateToBe(element, enabled));

	}
	
	/** Wait for an element to be clickable for a specific period of time */
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be clickable$")
	public void waitForClick(int seconds, String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebDriverWait w = new WebDriverWait(driver, seconds);
		w.until(ExpectedConditions.elementToBeClickable(element));

	}
	
}
