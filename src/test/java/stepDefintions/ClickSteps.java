package stepDefintions;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to click on an element
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ClickSteps {
	WebDriver driver;
    /******** Log Attribute ********/
    private static Logger log = Logger.getLogger(ClickSteps.class);
	
	public ClickSteps(){
		 driver= Hooks.driver;
	}

	// click on web element
	@Then("^I click on element having (.+) \"(.*?)\"$") 
	public void click(String type,String selector) throws Exception
	{
		if(type.equals("xpath")){
			driver.findElement(By.xpath(selector)).click();
			log.info("Clicking on element by xpath: " + selector);
		}
		if(type.equals("cssSelector")){
			driver.findElement(By.cssSelector(selector)).click();
			log.info("Clicking on element by cssSelector: " + selector);
		}
	}
 
	// double click on web element
	@Then("^I double click on element having (.+) \"(.*?)\"$") 
	public void doubleClick(String type, String selector) throws Exception
	{
		Actions action = new Actions(driver);
		if(type.equals("xpath")){
			action.moveToElement(driver.findElement(By.xpath(selector))).doubleClick().perform();
			log.info("Double click on element by xpath: " + selector);	
		}
		if(type.equals("cssSelector")){
			action.moveToElement(driver.findElement(By.cssSelector(selector))).doubleClick().perform();
			log.info("Double click on element by cssSelector: " + selector);
		}
	}
		
	// steps to click on link
	@Then("^I click on link having text \"(.*?)\"$")
	public void clickLink(String text)
	{
		driver.findElement(By.linkText(text)).click();
		log.info("Click on link having text: " + text);
	}
		
	//Step to click on partial link
	@Then("^I click on link having partial text \"(.*?)\"$")
	public void clickPartialLink(String text)
	{
		driver.findElement(By.partialLinkText(text)).click();
		log.info("Click on link having partial text: " + text);
	}
	
	// steps to click on link
		@Then("^I click on element having id \"(.*?)\"$")
		public void clickElementById(String id)
		{
			driver.findElement(By.id(id)).click();
			log.info("Click on element by id: " + id);
		}

}
