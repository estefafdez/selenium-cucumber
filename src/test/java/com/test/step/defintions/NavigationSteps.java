package com.test.step.defintions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.configure.environment.PropertiesHandler;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

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
		 driver= Hooks.getDriver();
	}

	/** Navigate to a specified URL */
	@When("^I navigate to \"([^\"]*)\"$")
	public void navigateTo(String url)
	{
		log.info("Navigate to: "+url);
	    driver.navigate().to(url);
	}

	/** Navigate forward */
	@And("^I navigate forward")
	public void navigateForward()
	{
		log.info("Navigate forward");
	    driver.navigate().forward();
	}
		
	/** Navigate backward */
	@And("^I navigate back")
	public void navigateBack()
	{
		log.info("Navigate backward");
	    driver.navigate().back();
	}
	
	/** Refresh current page */
	@And("^I refresh page$")
	public void refreshPage()
	{
		log.info("Reflesh current page");
	    driver.navigate().refresh();
	}
	
	/** Switch to a new windows */
	@When("^I switch to new window$")
	public void switchNewWindow()
	{
		for(String winHandle : driver.getWindowHandles()){
			log.info("Switching to new windows");
		    driver.switchTo().window(winHandle);
		}
	}
		 
	/** Switch to the previous windows */
	@When("^I switch to previous window$")
	public void switchPreviousWindows()
	{
		log.info("Switching of previous windows");
		driver.switchTo().defaultContent();
		
	}
		
	/** Switch to a new windows by windows title */
	@When("^I switch to window having title \"(.*?)\"$")
	public void switchToNewWindowsByTitle(String windowTitle) throws Exception
	{
		log.info("Switching to the windows by title: " + windowTitle);
		driver.switchTo().window(windowTitle);
	}
	  
	/** Close a windows by title */
	@And("^I close window \"(.*?)\"$")
	public void closeNewWindows(String windowTitle)
	{
		log.info("Switching to the windows by title: " + windowTitle);
		driver.switchTo().window(windowTitle);
		log.info("Closing windows: "+ windowTitle);
		driver.close();
	}
	
	/** Resize browser */
	@And("^I resize browser window size to width (\\d+) and height (\\d+)$")
	public void resizeBrowser(int width, int heigth)
	{
		log.info("Reside browser to: " + width + "x" + heigth);
		driver.manage().window().setSize(new Dimension(width, heigth));
	}
	
	/** Maximize the browser */
	@And("^I maximize browser window$")
	public void maximizeBrowser()
	{
		log.info("Maximizing the windows");
	    driver.manage().window().maximize();
	}

	/** Zoom in the page */
	@And("^I zoom in the page$")
	public void zoomIn()
	{
		log.info("Zoom in");
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));		
	}
	
	/** Zoom out the page */
	@And("^I zoom out the page$")
	public void zoomOut()
	{
		log.info("Zoom out");
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));		 
	}
	
	/** Set the zoom of the page to the 100% */
	@And("^I set the zoom of the page to the 100%$")
	public void zoomReset()
	{
		log.info("Reset Zoom at 100%");
		driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, "0"));		 
	}	
	
	/** Zoom into an element of the page */
	@And("^I zoom in until I see the element having (.+) \"(.*?)\"$") 
	public void zoomInElement(String type, String key)
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement html = driver.findElement(element);
		html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));		
	}

	/** Zoom out until the element is displayed  */
	@And("^I zoom out page till I see element having (.+) \"(.*?)\"$")
	public void zoomTillElementDisplay(String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		WebElement html = driver.findElement(element);
		html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
	}

	/** Scroll to the (top/end) of the page. */
	@And("^I scroll to (top|end) of page$")
	public void scrollPage(String to) throws Exception
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		if(to.equals("top")){
			log.info("Scrolling to the top of the page");
			jse.executeScript("scroll(0, -250);");
				
		}
		else if(to.equals("end")){
			log.info("Scrolling to the end of the page");
			jse.executeScript("scroll(0, 250);");		
		}		
	} 

	/** Scroll to an element. */
	@And("^I scroll to element having (.+) \"(.*?)\"$")
	public void scrollToElement(String type, String key) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		log.info("Scrolling to element: " + element);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));		
		
	}
}
