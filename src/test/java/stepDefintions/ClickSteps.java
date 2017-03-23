package stepDefintions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to click on an element
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ClickSteps {
	WebDriver driver;
	
	public ClickSteps(){
		 driver= Hooks.driver;
	}

	
	// click on web element
	@Then("^I click on element having (.+) \"(.*?)\"$") 
	public void click(String type,String accessName) throws Exception
	{
	}
		  
	//Forcefully click on element
	@Then("^I forcefully click on element having (.+) \"(.*?)\"$")
	public void clickForcefully(String type,String accessName) throws Exception
	{

	}
		  
	// double click on web element
	@Then("^I double click on element having (.+) \"(.*?)\"$") 
	public void doubleClick(String type, String accessValue) throws Exception
	{

	}
		
	// steps to click on link
	@Then("^I click on link having text \"(.*?)\"$")
	public void clickLink(String accessName)
	{

	}
		
	//Step to click on partial link
	@Then("^I click on link having partial text \"(.*?)\"$")
	public void clickPartialLink(String accessName)
	{

	}
	

}
