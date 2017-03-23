package stepDefintions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;

/**
 * This class contains methods to allow you navigate on the browser
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class StepDefinitions
{
		WebDriver driver = Hooks.driver;
	
		//Navigation Steps
	
		//Step to navigate to specified URL
		@Then("^I navigate to \"([^\"]*)\"$")
		public void navigateTo(String url)
		{
		    driver.navigate().to(url);
		}
			
		//Step to navigate forward
		@Then("^I navigate forward")
		public void navigateForward()
		{
		    driver.navigate().forward();
		}
			
		//Step to navigate backward
		@Then("^I navigate back")
		public void navigateBack()
		{
		    driver.navigate().back();
		}
		
		// steps to refresh page
		@Then("^I refresh page$")
		public void refreshPage()
		{
		    driver.navigate().refresh();
		}
	
	// Switch between windows
		
		//Switch to new window
		@Then("^I switch to new window$")
		public void switchNewWindow()
		{
			
		}
			 
		//Switch to old window
		@Then("^I switch to previous window$")
		public void switchPreviousWindows()
		{
			
		}
			
		//Switch to new window by window title
		@Then("^I switch to window having title \"(.*?)\"$")
		public void switch_to_window_by_title(String windowTitle) throws Exception
		{
			
		}
		  
		//Close new window
		@Then("^I close new window$")
		public void close_new_window()
		{
			
		}
		
	// Switch between frame 
		
		// Step to switch to frame by web element
		@Then("^I switch to frame having (.+) \"(.*?)\"$") 
		public void switch_frame_by_element(String method, String value)
		{
			
		}
			
		// step to switch to main content
		@Then("^I switch to main content$")
		public void switch_to_default_content()
		{
			
		}
	
	// To interact with browser
		
		// step to resize browser
		@Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
		public void resize_browser(int width, int heigth)
		{
		
		}
		
		// step to maximize browser
		@Then("^I maximize browser window$")
		public void maximize_browser()
		{
		    driver.manage().window().maximize();
		}
				
		//Step to close the browser
		@Then("^I close browser$")
		public void close_browser()
		{
		    driver.close();
		}
			
	// zoom in/out page
		
		// steps to zoom in page
		@Then("^I zoom in page$") 
		public void zoom_in()
		{
			
		}
		
		// steps to zoom out page
		@Then("^I zoom out page$")
		public void zoom_out()
		{
			
		}
	
	// zoom out webpage till necessary element displays
		
		// steps to zoom out till element displays
		@Then("^I zoom out page till I see element having (.+) \"(.*?)\"$")
		public void zoom_till_element_display(String type, String accessName) throws Exception
		{
		
		}
		
	// reset webpage view use
		
		@Then("^I reset page view$")
		public void reset_page_zoom()
		{
			
		}

	// scroll webpage

		@Then("^I scroll to (top|end) of page$")
		public void scroll_page(String to) throws Exception
		{
			
		} 

		
	// scroll webpage to specific element
		
		@Then("^I scroll to element having (.+) \"(.*?)\"$")
		public void scroll_to_element(String type, String accessName) throws Exception
		{
			
		}
	
	// hover over element
		
		// Note: Doesn't work on Windows firefox
		@Then("^I hover over element having (.+) \"(.*?)\"$")
		public void hover_over_element(String type, String accessName) throws Exception
		{
			
		}
		
	//Assertion steps

	/** page title checking
	 * @param present : 
	 * @param title :
	 */
	@Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
	public void check_title(String present,String title)
	{
		
	}
	
	// step to check element partial text
	@Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
	public void check_partial_text(String present, String partialTextTitle)
	{
	
	}
		
	// step to check element text
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have text as \"(.*?)\"$")
	public void check_element_text(String type, String accessName,String present,String value) throws Exception 
	{

	}	
		
	//step to check element partial text
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have partial text as \"(.*?)\"$")
	public void check_element_partial_text(String type,String accessName,String present,String value) throws Exception
	{
		
	}
		  	
	// step to check attribute value
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have attribute \"(.*?)\" with value \"(.*?)\"$") 
	public void check_element_attribute(String type,String accessName,String present,String attrb,String value) throws Exception
	{
	
	}
		 
	// step to check element enabled or not
	@Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
	public void check_element_enable(String type, String accessName,String present,String state) throws Exception
	{
	}
		
	//step to check element present or not
	@Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void check_element_presence(String type,String accessName,String present) throws Exception
	{

	}
	
	//step to assert checkbox is checked or unchecked
	@Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
	public void is_checkbox_checked(String type, String accessName,String state) throws Exception
	{

	}
		  
	//steps to assert radio button checked or unchecked
	@Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$") 
	public void is_radio_button_selected(String type,String accessName,String state) throws Exception
	{

	}
		 
	//steps to assert option by text from radio button group selected/unselected
	@Then("^option \"(.*?)\" by (.+) from radio button group having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void is_option_from_radio_button_group_selected(String option,String attrb,String type,String accessName,String state) throws Exception
	{

	}
		
	//steps to check link presence
	@Then("^link having text \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void check_element_presence(String accessName,String present) 
	{
	
	}
		  
	//steps to check partail link presence
	@Then("^link having partial text \"(.*?)\" should\\s*((?:not)?)\\s+be present$") 
	public void check_partial_element_presence(String accessName,String present) 
	{
	}
		
	//step to assert javascript pop-up alert text
	@Then("^I should see alert text as \"(.*?)\"$") 
	public void check_alert_text(String actualValue) 
	{
	}
		
	// step to select dropdown list
	@Then("^option \"(.*?)\" by (.+) from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
	public void is_option_from_dropdown_selected(String option,String by,String type,String accessName,String state) throws Exception
	{
	
	}
	
	//Input steps
	
	// enter text into input field steps
	@Then("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
	public void enter_text(String text, String type,String accessName) throws Exception
	{
	
	}

	// clear input field steps
	@Then("^I clear input field having (.+) \"([^\"]*)\"$") 
	public void clear_text(String type, String accessName) throws Exception
	{
	
	}

	// select option by text/value from dropdown
	@Then("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_dropdown(String option,String optionBy,String type,String accessName) throws Exception
	{
	
	}
	
	// select option by index from dropdown
	@Then("^I select (\\d+) option by index from dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_dropdown_by_index(String option, String type, String accessName) throws Exception
	{
	
	}
		
	// select option by text/value from multiselect
	@Then("^I select \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_multiselect_dropdown(String option,String optionBy, String type,String accessName) throws Exception
	{
	}
	
	// select option by index from multiselect
	@Then("^I select (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void select_option_from_multiselect_dropdown_by_index(String option, String type, String accessName) throws Exception
	{
		
	}
	
	// deselect option by text/value from multiselect
	@Then("^I deselect \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
	public void deselect_option_from_multiselect_dropdown(String option,String optionBy, String type,String accessName) throws Exception
	{
	
	}
		
	// deselect option by index from multiselect
	@Then("^I deselect (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
	public void deselect_option_from_multiselect_dropdown_by_index(String option, String type, String accessName) throws Exception
	{
	
	}

	// step to unselect option from mutliselect dropdown list
	@Then("^I deselect all options from multiselect dropdown having (.+) \"(.*?)\"$")
	public void unselect_all_option_from_multiselect_dropdown(String type, String accessName) throws Exception
	{

	}

	//check checkbox steps
	@Then("^I check the checkbox having (.+) \"(.*?)\"$") 
	public void check_checkbox(String type, String accessName) throws Exception
	{

	}

	//uncheck checkbox steps
	@Then("^I uncheck the checkbox having (.+) \"(.*?)\"$")
	public void uncheck_checkbox(String type,String accessName) throws Exception
	{
	}

	//steps to toggle checkbox
	@Then("^I toggle checkbox having (.+) \"(.*?)\"$")
	public void toggle_checkbox(String type,String accessName) throws Exception
	{
	}

	// step to select radio button
	@Then("^I select radio button having (.+) \"(.*?)\"$")
	public void select_radio_button(String type, String accessName) throws Exception
	{

	}

	// steps to select option by text from radio button group
	@Then("^I select \"(.*?)\" option by (.+) from radio button group having (.+) \"(.*?)\"$")
	public void select_option_from_radio_btn_group(String option,String by, String type, String accessName) throws Exception
	{
	}
	
	//Click element Steps 
	
		// click on web element
		@Then("^I click on element having (.+) \"(.*?)\"$") 
		public void click(String type,String accessName) throws Exception
		{
		}
			  
		//Forcefully click on element
		@Then("^I forcefully click on element having (.+) \"(.*?)\"$")
		public void click_forcefully(String type,String accessName) throws Exception
		{

		}
			  
		// double click on web element
		@Then("^I double click on element having (.+) \"(.*?)\"$") 
		public void double_click(String type, String accessValue) throws Exception
		{

		}
			
		// steps to click on link
		@Then("^I click on link having text \"(.*?)\"$")
		public void click_link(String accessName)
		{

		}
			
		//Step to click on partial link
		@Then("^I click on link having partial text \"(.*?)\"$")
		public void click_partial_link(String accessName)
		{

		}
		
	//Progress methods
	
	// wait for specific period of time
	@Then("^I wait for (\\d+) sec$")
	public void wait(String time)
	{
	}

	//wait for specific element to display for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to display$")
	public void wait_for_ele_to_display(String duration, String type, String accessName) throws Exception
	{

	}
  
	// wait for specific element to enable for specific period of time
	@Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be enabled$")
	public void wait_for_ele_to_click(String duration, String type, String accessName) throws Exception
	{

	}
	
	//JavaScript handling steps
	
	//Step to handle java script
	@Then("^I accept alert$")
	public void handle_alert()
	{

	}

	//Steps to dismiss java script
	@Then("^I dismiss alert$")
	public void dismiss_alert()
	{

	}
	
	//Screen shot methods
	
    @Then("^I take screenshot$")
    public void take_screenshot() throws IOException
    {

    }
    
  //Configuration steps
	
  	// step to print configuration
  	@Then("^I print configuration$") 
  	public void print_config()
  	{

  	}
}
