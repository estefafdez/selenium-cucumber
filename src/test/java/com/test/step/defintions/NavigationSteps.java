package com.test.step.defintions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.configure.environment.PropertiesHandler;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you navigate on the browser
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class NavigationSteps {
	WebDriver driver;
	
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(NavigationSteps.class);
		
	public NavigationSteps(){
		 driver= Hooks.driver;
	}

	/** Navigate to a specified URL */
	@Then("^I navigate to \"([^\"]*)\"$")
	public void navigateTo(String url)
	{
	    driver.navigate().to(url);
	    log.info("Navigate to: "+url);
	}

	/** Navigate forward */
	@Then("^I navigate forward")
	public void navigateForward()
	{
		log.info("Navigate forward");
	    driver.navigate().forward();
	}
		
	/** Navigate backward */
	@Then("^I navigate back")
	public void navigateBack()
	{
		log.info("Navigate backward");
	    driver.navigate().back();
	}
	
	/** Refresh current page */
	@Then("^I refresh page$")
	public void refreshPage()
	{
		log.info("Reflesh current page");
	    driver.navigate().refresh();
	}
	
	/** Switch to a new windows */
	@Then("^I switch to new window$")
	public void switchNewWindow()
	{
		for(String winHandle : driver.getWindowHandles()){
			log.info("Switching to new windows");
		    driver.switchTo().window(winHandle);
		}
	}
		 
	/** Switch to the previous windows */
	@Then("^I switch to previous window$")
	public void switchPreviousWindows()
	{
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
	
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles())
		{
		    driver.switchTo().window(winHandle);		
		}
		// Perform the actions on new window
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		// Continue with original browser (first window)
		
	}
		
	/** Switch to a new windows by windows title */
	@Then("^I switch to window having title \"(.*?)\"$")
	public void switchToNewWindowsByTitle(String windowTitle) throws Exception
	{
		log.info("Switching to the windows by title: " + windowTitle);
		driver.switchTo().window(windowTitle);
	}
	  
	/** Close a windows by title */
	@Then("^I close window \"(.*?)\"$")
	public void closeNewWindows(String windowTitle)
	{
		log.info("Switching to the windows by title: " + windowTitle);
		driver.switchTo().window(windowTitle);
		log.info("Closing windows: "+ windowTitle);
		driver.close();
	}
	
	/** Resize browser */
	@Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
	public void resizeBrowser(int width, int heigth)
	{
		log.info("Reside browser to: " + width + "x" + heigth);
		driver.manage().window().setSize(new Dimension(width, heigth));
	}
	
	/** Maximize the browser */
	@Then("^I maximize browser window$")
	public void maximizeBrowser()
	{
	    driver.manage().window().maximize();
	}


	/** Zoom in the page */
	@Then("^I zoom out the page$")
	public void zoomOIn()
	{

		
	}
	
	/** Zoom out the page */
	@Then("^I zoom out the page$")
	public void zoomOut()
	{
		
	}
	
	/** Zoom into an element of the page */
	@Then("^I zoom in until I see the element having (.+) \"(.*?)\"$") 
	public void zoomInElement(String type, String key)
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement html = driver.findElement(element);
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));		
	}

	/** Zoom out until the element is displayed  */
	@Then("^I zoom out page till I see element having (.+) \"(.*?)\"$")
	public void zoomTillElementDisplay(String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement html = driver.findElement(element);
		html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}
	
// reset webpage view use
	
	@Then("^I reset page view$")
	public void resetPageZoom()
	{
		
	}

// scroll webpage

	@Then("^I scroll to (top|end) of page$")
	public void scrollPage(String to) throws Exception
	{
		
	} 

	
// scroll webpage to specific element
	
	@Then("^I scroll to element having (.+) \"(.*?)\"$")
	public void scrollToElement(String type, String accessName) throws Exception
	{
		
	}

// hover over element
	
	// Note: Doesn't work on Windows firefox
	@Then("^I hover over element having (.+) \"(.*?)\"$")
	public void hoverOverElement(String type, String accessName) throws Exception
	{
		
	}

}
