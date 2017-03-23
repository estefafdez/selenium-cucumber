package stepDefintions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to wait for a certain event. 
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ProgressSteps {
	WebDriver driver;
	
	public ProgressSteps(){
		 driver= Hooks.driver;
	}

	// wait for specific period of time
	@Then("^I wait for (\\d+) seconds$")
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	
	//wait for specific element to display for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to display$")
	public void waitForElement(String duration, String type, String accessName) throws Exception
	{

	}
  
	// wait for specific element to enable for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be enabled$")
	public void waitForClick(String duration, String type, String accessName) throws Exception
	{

	}
	
}
