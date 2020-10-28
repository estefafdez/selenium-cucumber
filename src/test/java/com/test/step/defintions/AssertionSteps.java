package com.test.step.defintions;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.configure.environment.PropertiesHandler;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to use the assert methods
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class AssertionSteps {
	WebDriver driver;
	private WebDriverWait w;
	public static final int EXPLICIT_TIMEOUT = 15; 
	
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(AssertionSteps.class);
	
	public AssertionSteps(){
		 driver= Hooks.getDriver();
		 w = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
	}

	/** Check if the page title (is/is not) the same */
	@Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
	public void checkPageTitle(String present,String title)
	{
		String pageTitle = driver.getTitle();
		if(present.equals("see")){
			log.info("The title should be present");
			Assert.assertTrue("The title is not present", pageTitle.equals(title));			
		}
		else if(present.equals("not see")){
			log.info("The title should not be present");
			Assert.assertFalse("The title is present", pageTitle.equals(title));		
		}
	}
	
	/** Check if the page title (have/not have) a partial text */
	@Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
	public void checkTitlePartialText(String present, String partialTextTitle)
	{
		String pageTitle = driver.getTitle();
		if(present.equals("see")){
			log.info("The partial text on the title should be present");
			Assert.assertTrue("The partial title is not present", pageTitle.contains(partialTextTitle));			
		}
		else if(present.equals("not see")){
			log.info("The partial text on the title should not be present");
			Assert.assertFalse("The partial title is present", pageTitle.contains(partialTextTitle));		
		}
	}
		
	/** Check if an element (should/should not) a certain text */
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have text as \"(.*?)\"$")
	public void checkElementText(String type, String key,String present,String text) throws Exception 
	{
		By element = PropertiesHandler.getCompleteElement(type, key);

		boolean textElement = w.until(ExpectedConditions.textToBePresentInElementLocated(element, text)) !=null;
		
		if(present.equals("should")){			
			log.info("The element: "+ element +"with the text" + text +" should be present");
			Assert.assertTrue("The element by " + element + "with the text " + text + "is not present", textElement);
		}
		else if(present.equals("should not")){
			log.info("The element: "+ element +"with the text" + text +" should not be present");
			Assert.assertFalse("The element by " + element + "with the text " + text + "is present", textElement);
		}
	}	
		  			 
	/** Check if an element (should/should not) be (enabled/disabled) */
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
	public void checkElementEnable(String type, String key,String present,String state) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		boolean enable = driver.findElement(element).isEnabled();
		
		if(present.equals("should")){			
			log.info("The element" + element +" should be present");
			if(state.equals("enabled")){
				log.info("The element" + element +" should be present and enabled");
				Assert.assertTrue("The element by " + element + "is disabled", enable);
			}
			else if(state.equals("disabled")){
				log.info("The element" + element +" should be present and disabled");
				Assert.assertFalse("The element by " + element + "is enabled", enable);
			}
		}
		else if(present.equals("should not")){
			log.info("The element" + element +" should not be present");
			if(state.equals("enabled")){
				log.info("The element" + element +" should not be present and enabled");
				Assert.assertTrue("The element by " + element + "is disabled", enable);
			}
			else if(state.equals("disabled")){
				log.info("The element" + element +" should not be present and disable");
				Assert.assertFalse("The element by " + element + "is enabled", enable);
			}
		}
	}
		
	/** Check if an element (should/should not) be present */
	@Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkElementPresent(String type,String key,String present) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		boolean isPresent = driver.findElements(element).isEmpty();
		
		if(present.equals("should")){
			log.info("The element: " + element +" should be present");
			Assert.assertTrue("The element by " + element + "is not present", isPresent);
		}
		else if(present.equals("should not")){
			log.info("The element: " + element +" should not be present");
			Assert.assertFalse("The element by " + element + "is present", isPresent);		
		}
	}
	
	/** Check if an element should be (checked/unchecked) */
	@Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
	public void checkCheckboxChecked(String type, String key,String state) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		boolean selected = driver.findElement(element).isSelected();
		
		if(state.equals("checked")){
			log.info("The element: " + element +" should be checked");
			Assert.assertTrue("The element by " + element + "is not checked", selected);
		}
		else if(state.equals("unchecked")){
			log.info("The element: " + element +" should be unchecked");
			Assert.assertFalse("The element by " + element + "is checked", selected);		
		}
	}
		  
	/** Check if an element should be (selected/unselected) */
	@Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$") 
	public void checkRadioButtonSelected(String type,String key,String state) throws Exception
	{
		By element = PropertiesHandler.getCompleteElement(type, key);
		boolean selected = driver.findElement(element).isSelected();
		
		if(state.equals("selected")){
			log.info("The element: " + element +" should be selected");
			Assert.assertTrue("The element by " + element + "is not selected", selected);
		}
		else if(state.equals("unselected")){
			log.info("The element: " + element +" should be unselected");
			Assert.assertFalse("The element by " + element + "is selected", selected);		
		}
	}

	/** Check if an link with certain text (should/should not) be present*/
	@Then("^link having text \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkElementPresent(String text, String present) 
	{
		WebElement element = driver.findElement(By.linkText(text));
		boolean isPresent = w.until(ExpectedConditions.textToBePresentInElement(element,  text));
		
		if(present.equals("should")){
			log.info("The element: " + element +" should be present");
			Assert.assertTrue("The element by " + element + "is not present", isPresent);
		}
		else if(present.equals("should not")){
			log.info("The element: " + element +" should not be present");
			Assert.assertFalse("The element by " + element + "is present", isPresent);		
		}
	}
		  
	/** Check if an partial link with certain text (should/should not) be present*/
	@Then("^link having partial text \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkPartialElementPresent(String text,String present) 
	{
		WebElement element = driver.findElement(By.partialLinkText(text));
		boolean isPresent = w.until(ExpectedConditions.textToBePresentInElement(element,  text));
		
		if(present.equals("should")){
			log.info("The element: " + element +" should be present");
			Assert.assertTrue("The element by " + element + "is not present", isPresent);
		}
		else if(present.equals("should not")){
			log.info("The element: " + element +" should not be present");
			Assert.assertFalse("The element by " + element + "is present", isPresent);		
		}
	}
		
	/** Check if a text is displayed on a JavaScript pop-up alert*/
	@Then("^I should see alert text as \"(.*?)\"$") 
	public void checkJavascriptAlertText(String actualValue) 
	{
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();		
		Assert.assertTrue("The JavaScript pop-up alert does not contains the text", alertText.equals(actualValue));		
	}
		
	/** Check if an option with a certain value is (selected/unselected) on a drop-down*/
	@Then("^option with value \"(.*?)\" from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void checkDropdownOptionSelected(String value,String type,String type2,String key,String select) throws Exception
	{
		By optionToSelect = PropertiesHandler.getCompleteElement(type,value);
		By dropdown = PropertiesHandler.getCompleteElement(type2,key);
		driver.findElement(dropdown).click();		
		WebElement selectElement = driver.findElement(optionToSelect);
		
		if(select.equals("selected")){
			log.info("The option: " + optionToSelect +" is selected");			
			selectElement.click();
			Assert.assertTrue("The element" + optionToSelect + "is not selected", selectElement.isSelected());
		}
		else if(select.equals("unselected")){
			log.info("The option: " + optionToSelect +" is unselected");			
			if(selectElement.isSelected()){
				selectElement.click();
			}			
			Assert.assertTrue("The element" + optionToSelect + "is selected", selectElement.isSelected());
		}	
	}	
}
