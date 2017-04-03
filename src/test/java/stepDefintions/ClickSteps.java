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

	/** Click on a WebElement */
	@Then("^I click on element having (.+) \"(.*?)\"$") 
	public void click(String type, String selector) throws Exception
	{
		//driver.findElement(WebElementsUI.ENGLISH_FLAG);
		
		if(type.equals("xpath")){
			 driver.findElement(By.xpath(selector)).click();
			 log.info("Clicking on element by xpath: " + selector);
		}
		else if(type.equals("cssSelector")){
			driver.findElement(By.cssSelector(selector)).click();
			log.info("Clicking on element by cssSelector: " + selector);
		}
	}
 
	/** Double click on a WebElement */
	@Then("^I double click on element having (.+) \"(.*?)\"$") 
	public void doubleClick(String type, By selector) throws Exception
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(selector)).doubleClick().perform();
		log.info("Double click on element by "+type+": " + selector);	
	}
		
	/** Click on a link having text */
	@Then("^I click on link having text \"(.*?)\"$")
	public void clickElementByLink(String text)
	{
		driver.findElement(By.linkText(text)).click();
		log.info("Click on link having text: " + text);
	}
		
	/** Click on a link having partial text */
	@Then("^I click on link having partial text \"(.*?)\"$")
	public void clickElementByPartialLink(String text)
	{
		driver.findElement(By.partialLinkText(text)).click();
		log.info("Click on link having partial text: " + text);
	}
	
	/** Click on link having ID */
	@Then("^I click on element having id \"(.*?)\"$")
	public void clickElementById(String id)
	{
		driver.findElement(By.id(id)).click();
		log.info("Click on element by id: " + id);
	}
	
	/** Click on link having className */
	@Then("^I click on element having className \"(.*?)\"$")
	public void clickElementByClassName(String className)
	{
		driver.findElement(By.className(className)).click();
		log.info("Click on element by className: " + className);
	}
	
	/** Click on link having Name */
	@Then("^I click on element having name \"(.*?)\"$")
	public void clickElementByName(String name)
	{
		driver.findElement(By.name(name)).click();
		log.info("Click on element by name: " + name);
	}

}
