package com.test.step_defintions;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to use the assert methods
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class AssertionSteps {
	WebDriver driver;
	//WebDriverWait w = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
	public static final int EXPLICIT_TIMEOUT = 15; 
	
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(AssertionSteps.class);
	
	public AssertionSteps(){
		 driver= Hooks.driver;
	}

	/** Check if the page title (is/is not) the same */
	@Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
	public void checkPageTitle(String present,String title)
	{
		String pageTitle = driver.getTitle();
		if(present.equals("see")){
			log.debug("The title should be present");
			Assert.assertTrue("The title is not present", pageTitle.equals(title));			
		}
		else if(present.equals("not see")){
			log.debug("The title should not be present");
			Assert.assertFalse("The title is present", pageTitle.equals(title));		
		}
	}
	
	/** Check if the page title (have/not have) a partial text */
	@Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
	public void checkPartialText(String present, String partialTextTitle)
	{
		String pageTitle = driver.getTitle();
		if(present.equals("see")){
			log.debug("The partial text on the title should be present");
			Assert.assertTrue("The partial title is not present", pageTitle.contains(partialTextTitle));			
		}
		else if(present.equals("not see")){
			log.debug("The partial text on the title should not be present");
			Assert.assertFalse("The partial title is present", pageTitle.contains(partialTextTitle));		
		}
	}
		
	/** Check if an element (should/should not) a certain text */
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have text as \"(.*?)\"$")
	public void checkElementText(String type, By selector,String present,String text) throws Exception 
	{
		if(present.equals("should")){
			//boolean element = w.until(ExpectedConditions.textToBePresentInElementLocated(selector,  text));
			log.debug("The element with the text" + text +" should be present");
			//Assert.assertTrue("The element by " + selector + "with the text " + text + "is not present", element);
		}
		else if(present.equals("should not")){
			//boolean element = w.until(ExpectedConditions.textToBePresentInElementLocated(selector,  text));
			log.debug("The element with the text" + text +" should not be present");
			//Assert.assertFalse("The element by " + selector + "with the text " + text + "is present", element);		
		}
	}	
		  			 
	/** Check if an element (should/should not) be (enabled/disabled) */
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
	public void checkElementEnable(String type, By selector,String present,String state) throws Exception
	{
		boolean enable = driver.findElement(selector).isEnabled();
		
		if(present.equals("should")){			
			log.debug("The element" + selector +" should be present");
			if(state.equals("enabled")){
				Assert.assertTrue("The element by " + selector + "is disabled", enable);
			}
			else if(state.equals("disabled")){
				Assert.assertFalse("The element by " + selector + "is enabled", enable);
			}
		}
		else if(present.equals("should not")){
			log.debug("The element" + selector +" should not be present");
			if(state.equals("enabled")){
				Assert.assertTrue("The element by " + selector + "is disabled", enable);
			}
			else if(state.equals("disabled")){
				Assert.assertFalse("The element by " + selector + "is enabled", enable);
			}
		}
	}
		
	/** Check if an element (should/should not) be present */
	@Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkElementPresent(String type,By selector,String present) throws Exception
	{
		boolean isPresent = driver.findElements(selector).isEmpty();
		
		if(present.equals("should")){
			log.debug("The element: " + selector +" should be present");
			Assert.assertTrue("The element by " + selector + "is not present", isPresent);
		}
		else if(present.equals("should not")){
			log.debug("The element: " + selector +" should not be present");
			Assert.assertFalse("The element by " + selector + "is present", isPresent);		
		}
	}
	
	/** Check if an element should be (checked/unchecked) */
	@Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
	public void checkCheckboxChecked(String type, By selector,String state) throws Exception
	{
		boolean selected = driver.findElement(selector).isSelected();
		
		if(state.equals("checked")){
			log.debug("The element: " + selector +" should be checked");
			Assert.assertTrue("The element by " + selector + "is not checked", selected);
		}
		else if(state.equals("unchecked")){
			log.debug("The element: " + selector +" should be unchecked");
			Assert.assertFalse("The element by " + selector + "is checked", selected);		
		}
	}
		  
	/** Check if an element should be (selected/unselected) */
	@Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$") 
	public void checkRadioButtonSelected(String type,By selector,String state) throws Exception
	{
		boolean selected = driver.findElement(selector).isSelected();
		
		if(state.equals("selected")){
			log.debug("The element: " + selector +" should be selected");
			Assert.assertTrue("The element by " + selector + "is not selected", selected);
		}
		else if(state.equals("unselected")){
			log.debug("The element: " + selector +" should be unselected");
			Assert.assertFalse("The element by " + selector + "is selected", selected);		
		}
	}
		 
	//steps to assert option by text from radio button group selected/unselected
	@Then("^option \"(.*?)\" by (.+) from radio button group having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void checkOptionFromRadioButtonSelected(String option,String attrb,String type,String selector,String state) throws Exception
	{

	}
		
	//steps to check link presence
	@Then("^link having text \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkElementPresent(String selector,String present) 
	{
	
	}
		  
	//steps to check partial link presence
	@Then("^link having partial text \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkPartialElementPresent(String selector,String present) 
	{
	}
		
	//step to assert javascript pop-up alert text
	@Then("^I should see alert text as \"(.*?)\"$") 
	public void checkJavascriptAlertText(String actualValue) 
	{
	}
		
	// step to select dropdown list
	@Then("^option \"(.*?)\" by (.+) from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void checkDropdownOptionSelected(String option,String by,String type,String selector,String state) throws Exception
	{
	
	}

	//Step to close the browser
	@Then("^I close browser$")
	public void close()
	{
		driver.close();
	}
	

}
