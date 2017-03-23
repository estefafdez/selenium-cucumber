package stepDefintions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you to take screenshots
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ScreenshotSteps {
	WebDriver driver;
	
	public ScreenshotSteps(){
		 driver= Hooks.driver;
	}

	//Screen shot methods
	
    @Then("^I take screenshot$")
    public void takeScreenshot() throws IOException
    {

    }

	

}
