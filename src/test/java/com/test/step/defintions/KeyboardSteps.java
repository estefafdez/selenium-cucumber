package com.test.step.defintions;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.configure.environment.PropertiesHandler;
import cucumber.api.java.en.When;

/**
 * This class contains methods to allow you to use keyboard methods
 * More steps examples here: https://github.com/selenium-cucumber/selenium-cucumber-java/blob/master/doc/canned_steps.md
 * @author estefafdez
 */
public class KeyboardSteps {
	static WebDriver driver;
	public static final int EXPLICIT_TIMEOUT = 5; 
	
	/******** Log Attribute ********/
    private static Logger log = Logger.getLogger(KeyboardSteps.class);
	
	public KeyboardSteps(){
		 driver= Hooks.getDriver();
	}
	
	/** Press Key */
	@When("^I press the key (.+)$")
    public static void pressKey(int key) {
		Robot r;
        try {
            r = new Robot();
            ProgressSteps.wait(EXPLICIT_TIMEOUT * 1000);
            r.keyPress(key);
            ProgressSteps.wait(EXPLICIT_TIMEOUT * 1000);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }
     }
	
	/** Press Key */
	@When("^I release the keyboard key (.+)$")
	 public static void releaseKey(int key, int sleepTime) {

        Robot r;
        try {
            r = new Robot();
            ProgressSteps.wait(EXPLICIT_TIMEOUT * 1000);
            r.keyRelease(key);
            ProgressSteps.wait(EXPLICIT_TIMEOUT * 1000);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }
    }
	
	/** Press Key */
	@When("^I press and release the keyboard key (.+)$")
	public static void pressReleaseKey(int key) {
        Robot r;
        try {
            r = new Robot();
            r.keyPress(key);
            ProgressSteps.wait(EXPLICIT_TIMEOUT * 500);
            r.keyRelease(key);
            ProgressSteps.wait(EXPLICIT_TIMEOUT * 500);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }
    }
	
	/** Moves the mouse over an element  */
	@When("^I move the mouse over an element having (.+) \"(.*?)\"$")
	public static void moveMouseOverElement(String type, String key) {
        Robot r;
        By element = PropertiesHandler.getCompleteElement(type, key);

        try {
            r = new Robot();
            Point point = getPositionToClick(driver, element);
            java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

            if (((int) location.getX()) != point.getX() || ((int) location.getY()) != point.getY()) {
                r.mouseMove(point.getX(), point.getY());
                ProgressSteps.wait(EXPLICIT_TIMEOUT);
            }
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }
    }
	
	/** Moves the mouse out of an element  */
	@When("^I move the mouse out of an element having (.+) \"(.*?)\"$")
	public static void moveMouseOutElement(String type, String key) {
        Robot r;
        int x = 0, y = 0;
        By element = PropertiesHandler.getCompleteElement(type, key);
        WebElement elementComplete = driver.findElement(element);
        

        try {
            r = new Robot();
            if (elementComplete.isDisplayed()) {                
                Point position = elementComplete.getLocation();
                x = position.getX() - 10;
                y = position.getY() - 10;
            }
            Point toMove = new Point(x, y);
            java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

            if (((int) location.getX()) != toMove.getX() && ((int) location.getY()) != toMove.getY()) {
                r.mouseMove(toMove.getX(), toMove.getY());
                ProgressSteps.wait(EXPLICIT_TIMEOUT);
            }
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/******************************************* Private Methods *****************************************************/
	
	private static Point getPositionToClick(WebDriver driver, By selector) {
    	Point toReturn = null;
        int x = 0, y = 0;
        WebElement element = driver.findElement(selector);

        if (element.isDisplayed()) {            
            Point position = element.getLocation();

            x = position.getX() + (element.getSize().getWidth() / 2);
            y = position.getY() + (element.getSize().getHeight() / 2);

            toReturn = new Point(x, y);
        }
        return toReturn;
    }
	
}
