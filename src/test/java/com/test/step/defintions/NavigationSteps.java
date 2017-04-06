package com.test.step.defintions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you navigate on the browser
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class NavigationSteps {
	WebDriver driver;
	
	public NavigationSteps(){
		 driver= Hooks.driver;
	}

	/** Navigate to a specified URL */
	@Then("^I navigate to \"([^\"]*)\"$")
	public void navigateTo(String url)
	{
	    driver.navigate().to(url);
	}

	/** Navigate forward */
	@Then("^I navigate forward")
	public void navigateForward()
	{
	    driver.navigate().forward();
	}
		
	/** Navigate backward */
	@Then("^I navigate back")
	public void navigateBack()
	{
	    driver.navigate().back();
	}
	
	/** Refresh current page */
	@Then("^I refresh page$")
	public void refreshPage()
	{
	    driver.navigate().refresh();
	}
	
	/** Switch to a new windows */
	@Then("^I switch to new window$")
	public void switchNewWindow()
	{
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
		 
	/** Switch to the previous windows */
	@Then("^I switch to previous window$")
	public void switchPreviousWindows()
	{
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window

		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}

		// Perform the actions on new window

		// Close the new window, if that window no more required
		driver.close();

		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);

		// Continue with original browser (first window)
		
	}
		
	//Switch to new window by window title
	@Then("^I switch to window having title \"(.*?)\"$")
	public void switchToNewWindowsByTitle(String windowTitle) throws Exception
	{
		
	}
	  
	//Close new window
	@Then("^I close new window$")
	public void closeNewWindows()
	{
		
	}
	
	// Switch between frame 
	
	// Step to switch to frame by web element
	@Then("^I switch to frame having (.+) \"(.*?)\"$") 
	public void switchFrameByElement(String method, String value)
	{
		
	}
		
	// step to switch to main content
	@Then("^I switch to main content$")
	public void switchToDefaultContent()
	{
		
	}

	// To interact with browser
	
	// step to resize browser
	@Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
	public void resizeBrowser(int width, int heigth)
	{
	
	}
	
	// step to maximize browser
	@Then("^I maximize browser window$")
	public void maximizeBrowser()
	{
	    driver.manage().window().maximize();
	}
		
// zoom in/out page
	
	// steps to zoom in page
	@Then("^I zoom in page$") 
	public void zoomIn()
	{
		
	}
	
	// steps to zoom out page
	@Then("^I zoom out page$")
	public void zoomOut()
	{
		
	}

// zoom out webpage till necessary element displays
	
	// steps to zoom out till element displays
	@Then("^I zoom out page till I see element having (.+) \"(.*?)\"$")
	public void zoomTillElementDisplay(String type, String accessName) throws Exception
	{
	
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
