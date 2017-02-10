package stepDefintions;

import configureEnvironment.CreateDriver;
import cucumber.api.java.en.Then;

/**
 * This class contains the assert steps methods.
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class AssertionSteps implements CreateDriver{
	
	//Step to navigate to a specified URL
			@Then("^I navigate to \"([^\"]*)\"$")
			public void navigateTo(String url)
			{
				driver.navigate().to(url);
			}
	/**
	 * Then I should see page title as "(.*?)"
		Then I should not see page title as "(.*?)"
		Then I should see page title having partial text as "(.*?)"

		Then element having id "([^\"]*)" should have text as "(.*?)"
		Then element having name "([^\"]*)" should have text as "(.*?)"
		Then element having class "([^\"]*)" should have text as "(.*?)"
		Then element having xpath "([^\"]*)" should have text as "(.*?)"
		Then element having css "([^\"]*)" should have text as "(.*?)"
		
		Then element having id "([^\"]*)" should have partial text as "(.*?)"
		Then element having name "([^\"]*)" should have partial text as "(.*?)"
		Then element having class "([^\"]*)" should have partial text as "(.*?)"
		Then element having xpath "([^\"]*)" should have partial text as "(.*?)"
		Then element having css "([^\"]*)" should have partial text as "(.*?)"
		
		Then element having id "([^\"]*)" should not have text as "(.*?)"
		Then element having name "([^\"]*)" should not have text as "(.*?)"
		Then element having class "([^\"]*)" should not have text as "(.*?)"
		Then element having xpath "([^\"]*)" should not have text as "(.*?)"
		Then element having css "([^\"]*)" should not have text as "(.*?)"
		
		Then element having id "([^\"]*)" should not have partial text as "(.*?)"
		Then element having name "([^\"]*)" should not have partial text as "(.*?)"
		Then element having class "([^\"]*)" should not have partial text as "(.*?)"
		Then element having xpath "([^\"]*)" should not have partial text as "(.*?)"
		Then element having css "([^\"]*)" should not have partial text as "(.*?)"
		
		Then element having id "([^\"]*)" should have attribute "(.*?)" with value "(.*?)"
		Then element having name "([^\"]*)" should have attribute "(.*?)" with value "(.*?)"
		Then element having class "([^\"]*)" should have attribute "(.*?)" with value "(.*?)"
		Then element having xpath "([^\"]*)" should have attribute "(.*?)" with value "(.*?)"
		Then element having css "([^\"]*)" should have attribute "(.*?)" with value "(.*?)"
		
		Then element having id "([^\"]*)" should not have attribute "(.*?)" with value "(.*?)"
		Then element having name "([^\"]*)" should not have attribute "(.*?)" with value "(.*?)"
		Then element having class "([^\"]*)" should not have attribute "(.*?)" with value "(.*?)"
		Then element having xpath "([^\"]*)" should not have attribute "(.*?)" with value "(.*?)"
		Then element having css "([^\"]*)" should not have attribute "(.*?)" with value "(.*?)"
		
		Then element having id "([^\"]*)" should be enabled
		Then element having name "([^\"]*)" should be enabled
		Then element having class "([^\"]*)" should be enabled
		Then element having xpath "([^\"]*)" should be enabled
		Then element having css "([^\"]*)" should be enabled
		
		Then element having id "([^\"]*)" should be disabled
		Then element having name "([^\"]*)" should be disabled
		Then element having class "([^\"]*)" should be disabled
		Then element having xpath "([^\"]*)" should be disabled
		Then element having css "([^\"]*)" should be disabled
		
		Then element having id "([^\"]*)" should be present
		Then element having name "([^\"]*)" should be present
		Then element having class "([^\"]*)" should be present
		Then element having xpath "([^\"]*)" should be present
		Then element having css "([^\"]*)" should be present
		
		Then element having id "([^\"]*)" should not be present
		Then element having name "([^\"]*)" should not be present
		Then element having class "([^\"]*)" should not be present
		Then element having xpath "([^\"]*)" should not be present
		Then element having css "([^\"]*)" should not be present
		
		Then checkbox having id "(.*?)" should be checked
		Then checkbox having name "(.*?)" should be checked
		Then checkbox having class "(.*?)" should be checked
		Then checkbox having xpath "(.*?)" should be checked
		Then checkbox having css "(.*?)" should be checked
		
		Then checkbox having id "(.*?)" should be unchecked
		Then checkbox having name "(.*?)" should be unchecked
		Then checkbox having class "(.*?)" should be unchecked
		Then checkbox having xpath "(.*?)" should be unchecked
		Then checkbox having css "(.*?)" should be unchecked
		
		Then option "(.*?)" by text from dropdown having id "(.*?)" should be selected
		Then option "(.*?)" by text from dropdown having name "(.*?)" should be selected
		Then option "(.*?)" by text from dropdown having class "(.*?)" should be selected
		Then option "(.*?)" by text from dropdown having xpath "(.*?)" should be selected
		Then option "(.*?)" by text from dropdown having css "(.*?)" should be selected
		
		Then option "(.*?)" by value from dropdown having id "(.*?)" should be selected
		Then option "(.*?)" by value from dropdown having name "(.*?)" should be selected
		Then option "(.*?)" by value from dropdown having class "(.*?)" should be selected
		Then option "(.*?)" by value from dropdown having xpath "(.*?)" should be selected
		Then option "(.*?)" by value from dropdown having css "(.*?)" should be selected
		
		Then option "(.*?)" by text from dropdown having id "(.*?)" should be unselected
		Then option "(.*?)" by text from dropdown having name "(.*?)" should be unselected
		Then option "(.*?)" by text from dropdown having class "(.*?)" should be unselected
		Then option "(.*?)" by text from dropdown having xpath "(.*?)" should be unselected
		Then option "(.*?)" by text from dropdown having css "(.*?)" should be unselected
		
		Then option "(.*?)" by value from dropdown having id "(.*?)" should be unselected
		Then option "(.*?)" by value from dropdown having name "(.*?)" should be unselected
		Then option "(.*?)" by value from dropdown having class "(.*?)" should be unselected
		Then option "(.*?)" by value from dropdown having xpath "(.*?)" should be unselected
		Then option "(.*?)" by value from dropdown having css "(.*?)" should be unselected    
		
		Then radio button having id "(.*?)" should be selected
		Then radio button having name "(.*?)" should be selected
		Then radio button having class "(.*?)" should be selected
		Then radio button having xpath "(.*?)" should be selected
		Then radio button having css "(.*?)" should be selected
		
		Then radio button having id "(.*?)" should be unselected
		Then radio button having name "(.*?)" should be unselected
		Then radio button having class "(.*?)" should be unselected
		Then radio button having xpath "(.*?)" should be unselected
		Then radio button having css "(.*?)" should be unselected
		
		Then option "(.*?)" by text from radio button group having id "(.*?)" should be selected
		Then option "(.*?)" by text from radio button group having name "(.*?)" should be selected
		Then option "(.*?)" by text from radio button group having class "(.*?)" should be selected
		Then option "(.*?)" by text from radio button group having xpath "(.*?)" should be selected
		Then option "(.*?)" by text from radio button group having css "(.*?)" should be selected
		
		Then option "(.*?)" by value from radio button group having id "(.*?)" should be selected
		Then option "(.*?)" by value from radio button group having name "(.*?)" should be selected
		Then option "(.*?)" by value from radio button group having class "(.*?)" should be selected
		Then option "(.*?)" by value from radio button group having xpath "(.*?)" should be selected
		Then option "(.*?)" by value from radio button group having css "(.*?)" should be selected
		
		Then option "(.*?)" by text from radio button group having id "(.*?)" should be unselected
		Then option "(.*?)" by text from radio button group having name "(.*?)" should be unselected
		Then option "(.*?)" by text from radio button group having class "(.*?)" should be unselected
		Then option "(.*?)" by text from radio button group having xpath "(.*?)" should be unselected
		Then option "(.*?)" by text from radio button group having css "(.*?)" should be unselected
		
		Then option "(.*?)" by value from radio button group having id "(.*?)" should be unselected
		Then option "(.*?)" by value from radio button group having name "(.*?)" should be unselected
		Then option "(.*?)" by value from radio button group having class "(.*?)" should be unselected
		Then option "(.*?)" by value from radio button group having xpath "(.*?)" should be unselected
		Then option "(.*?)" by value from radio button group having css "(.*?)" should be unselected
		
		Then link having text "(.*?)" should be present
		Then link having partial text "(.*?)" should be present
		
		Then link having text "(.*?)" should not be present
		Then link having partial text "(.*?)" should not be present
		Then I should see alert text as "(.*?)"
		
		Then actual image having id "(.*?)" and expected image having url "(.*?)" should be similar
		Then actual image having name "(.*?)" and expected image having url "(.*?)" should be similar
		Then actual image having class "(.*?)" and expected image having url "(.*?)" should be similar
		Then actual image having xpath "(.*?)" and expected image having url "(.*?)" should be similar
		Then actual image having css "(.*?)" and expected image having url "(.*?)" should be similar
		Then actual image having url "(.*?)" and expected image having url "(.*?)" should be similar
		
		Then actual image having id "(.*?)" and expected image having image_name "(.*?)" should be similar
		Then actual image having name "(.*?)" and expected image having image_name "(.*?)" should be similar
		Then actual image having class "(.*?)" and expected image having image_name "(.*?)" should be similar
		Then actual image having xpath "(.*?)" and expected image having image_name "(.*?)" should be similar
		Then actual image having css "(.*?)" and expected image having image_name "(.*?)" should be similar
		Then actual image having url "(.*?)" and expected image having image_name "(.*?)" should be similar
		
		Then actual image having id "(.*?)" and expected image having id "(.*?)" should be similar
		Then actual image having name "(.*?)" and expected image having name "(.*?)" should be similar
		Then actual image having class "(.*?)" and expected image having class "(.*?)" should be similar
		Then actual image having xpath "(.*?)" and expected image having xpath "(.*?)" should be similar
		Then actual image having css "(.*?)" and expected image having css "(.*?)" should be similar
		Then actual image having url "(.*?)" and expected image having url "(.*?)" should be similar
	 */
}
