package stepDefintions;

import configureEnvironment.CreateDriver;
import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you navigate on the browser
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class NavigateSteps implements CreateDriver{
	
	//Step to navigate to a specified URL
			@Then("^I navigate to \"([^\"]*)\"$")
			public void navigateTo(String url)
			{
				driver.navigate().to(url);
			}
	//Step to navigate forward
			@Then("^I navigate forward")
			public void navigateForward(){
				driver.navigate().forward();
			}
		//Step to navigate back
			@Then("^I navigate back")
			public void navigateBack(){
				driver.navigate().back();
			}
		//Step to refresh the page
			@Then("^I refresh the page")
			public void refresh(){
				driver.navigate().refresh();
			}
		//Step to maximize the browser windows
			@Then("^I maximize the browser windows")
			public void maximize(){
				driver.manage().window().maximize();
			}
		//Step to close the browser
			@Then("^I close the browser")
			public void close(){
				driver.close();
				driver.quit();
			}
}
