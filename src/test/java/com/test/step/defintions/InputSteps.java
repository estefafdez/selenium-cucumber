package com.test.step.defintions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you input into a field. 
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class InputSteps {
	WebDriver driver;
	
	public InputSteps(){
		 driver= Hooks.driver;
	}

	
	// enter text into input field steps
	@Then("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void enterText(String text, String type,String accessName) throws Exception
	{
	
	}

	// clear input field steps
	@Then("^I clear input field having (.+) \"([^\"]*)\"$") 
	public void clearText(String type, String accessName) throws Exception
	{
	
	}

	// select option by text/value from dropdown
	@Then("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
	public void selectOptionDropdown(String option,String optionBy,String type,String accessName) throws Exception
	{
	
	}
	
	// select option by index from dropdown
	@Then("^I select (\\d+) option by index from dropdown having (.+) \"(.*?)\"$")
	public void selectOptionDropdownByIndex(String option, String type, String accessName) throws Exception
	{
	
	}
		
	// select option by text/value from multiselect
	@Then("^I select \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void selectOptionMultiDropdown(String option,String optionBy, String type,String accessName) throws Exception
	{
	}
	
	// select option by index from multiselect
	@Then("^I select (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void selectOptionMultiDropdownByIndex(String option, String type, String accessName) throws Exception
	{
		
	}
	
	// deselect option by text/value from multiselect
	@Then("^I deselect \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void unselectOptionMultiDropdown(String option,String optionBy, String type,String accessName) throws Exception
	{
	
	}
		
	// deselect option by index from multiselect
	@Then("^I deselect (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void unselectOptionMultiDropdownByIndex(String option, String type, String accessName) throws Exception
	{
	
	}

	// step to unselect option from mutliselect dropdown list
	@Then("^I deselect all options from multiselect dropdown having (.+) \"(.*?)\"$")
	public void unselectAllOptionsFromMultiDropdown(String type, String accessName) throws Exception
	{

	}

	//check checkbox steps
	@Then("^I check the checkbox having (.+) \"(.*?)\"$") 
	public void checkCheckbox(String type, String accessName) throws Exception
	{

	}

	//uncheck checkbox steps
	@Then("^I uncheck the checkbox having (.+) \"(.*?)\"$")
	public void uncheckCheckbox(String type,String accessName) throws Exception
	{
	}

	//steps to toggle checkbox
	@Then("^I toggle checkbox having (.+) \"(.*?)\"$")
	public void toggleCheckbox(String type,String accessName) throws Exception
	{
	}

	// step to select radio button
	@Then("^I select radio button having (.+) \"(.*?)\"$")
	public void selectRadioButton(String type, String accessName) throws Exception
	{

	}

	// steps to select option by text from radio button group
	@Then("^I select \"(.*?)\" option by (.+) from radio button group having (.+) \"(.*?)\"$")
	public void selectOptionFromRadioBtnButton(String option,String by, String type, String accessName) throws Exception
	{
	}


}
