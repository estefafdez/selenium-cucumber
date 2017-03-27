package stepDefintions;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to use the assert methods
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class AssertionSteps {
	WebDriver driver;
	WebDriverWait w = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
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
			boolean element = w.until(ExpectedConditions.textToBePresentInElementLocated(selector,  text));
			log.debug("The element with the text" + text +" should be present");
			Assert.assertTrue("The element by " + selector + "with the text " + text + "is not present", element);
		}
		else if(present.equals("should not")){
			boolean element = w.until(ExpectedConditions.textToBePresentInElementLocated(selector,  text));
			log.debug("The element with the text" + text +" should not be present");
			Assert.assertFalse("The element by " + selector + "with the text " + text + "is present", element);		
		}
	}	
		  	
	// step to check attribute value
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have attribute \"(.*?)\" with value \"(.*?)\"$") 
	public void checkElementAtribute(String type,String selector,String present,String attrb,String value) throws Exception
	{
	
	}
		 
	// step to check element enabled or not
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
	public void checkElementEnable(String type, String selector,String present,String state) throws Exception
	{
	}
		
	//step to check element present or not
	@Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void checkElementPresent(String type,By selector,String present) throws Exception
	{

	}
	
	//step to assert checkbox is checked or unchecked
	@Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
	public void checkCheckboxChecked(String type, String selector,String state) throws Exception
	{

	}
		  
	//steps to assert radio button checked or unchecked
	@Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$") 
	public void checkRadioButtonSelected(String type,String selector,String state) throws Exception
	{

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
