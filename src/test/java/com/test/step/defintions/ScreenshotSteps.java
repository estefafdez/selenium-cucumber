package com.test.step.defintions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.And;

/**
 * This class contains methods to allow you to take screenshots
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class ScreenshotSteps {
	WebDriver driver;
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(AssertionSteps.class);
	
	public ScreenshotSteps(){
		 driver= Hooks.driver;
	}

	/** Take a screenshot */
    @And("^I take screenshot$")
    public void takeScreenshot() throws IOException
    {
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmm");
		    	String screenShotName = "resources/screenshot/" + dateFormat.format(GregorianCalendar.getInstance().getTime());		
		    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    	log.info("Screenshot saved as:" + screenShotName);
		    	FileUtils.copyFile(scrFile, new File(String.format("%s.png", screenShotName)));    	
    }

	

}
