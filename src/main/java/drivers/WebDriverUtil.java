package drivers;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Estefanía Fdez Muñoz <estefafdez@gmail.com>
 */
public class WebDriverUtil {

    private static Logger log = Logger.getLogger(WebDriverUtil.class);

    /******************************************************************************************************************************************************************
     *                 								BASIC OPERATION METHODS SECTION               																			   *
     *****************************************************************************************************************************************************************/
	
    /**
     * Checks if an element exists in the DOM
     * @param driver WebDriver element
     * @param selector By element
     * @return True if the element exists in the DOM and false in the opposite case
     */
    public static boolean existsElement(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start existsElement method");

        boolean exists = false;

        try {
        	log.info("[[Check element]]: " + selector.toString() + " exists.");
            exists = (driver.findElement(selector) != null);
        } catch (Exception ex) {
            exists = false;
        }

        log.debug("[log-Utils] WebDriverUtil - End existsElement method");

        return exists;
    }

    /**
     * Checks if an element exists in the DOM and is displayed
     * @param driver WebDriver element
     * @param selector By element
     * @return True if the element exists in the DOM and is displayed and false in the opposite case
     */
    public static boolean isElementDisplayed(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start isElementDisplayed method");

        boolean isDisplayed = false;

        try {
            WebElement element = driver.findElement(selector);
            isDisplayed = (element != null && element.isDisplayed());
            log.info("The element: " + selector.toString() + " is displayed");
        } catch (Exception ex) {
            isDisplayed = false;
        }

        log.debug("[log-Utils] WebDriverUtil - End isElementDisplayed method");

        return isDisplayed;
    }

    /**
     * Clicks on an element after wait and if it is displayed
     * @param driver WebDriver element
     * @param selector By element
     */
    public static void clickIfExists(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start clickIfExists method");

        wait(driver, selector, 20);

        if (isElementDisplayed(driver, selector)) {
        	log.info("[[Click on]]: " + selector.toString());
            driver.findElement(selector).click();
        } else {
            log.error("The element " + selector.toString() + " is not displayed.");
        }

        log.debug("[log-Utils] WebDriverUtil - End clickIfExists method");
    }

    /******************************************************************************************************************************************************************
     *                 										         JAVASCRIPT  METHODS SECTION                  																	   *
     *****************************************************************************************************************************************************************/
    /**
     * Executes JavaScript in the context of the currently window
     * @param driver WebDriver element
     * @param script The JavaScript to execute
     * @return Boolean, Long, String, List, WebElement Or null
     */
    public static Object executeJavaScript(WebDriver driver, String script) {
        log.debug("[log-Utils] WebDriverUtil - Start executeJavaScript method");
        log.debug("[log-Utils] WebDriverUtil - End executeJavaScript method");

        return ((JavascriptExecutor) driver).executeScript(script);
    }

    /**
     * Puts the focus on an element through its id
     * @param driver WebDriver element
     * @param id string with the id of an element
     */
    public static void focus(WebDriver driver, String id) {
        log.debug("[log-Utils] WebDriverUtil - Start focus method");
        
        log.info("[[Focus on]]: " + id );
        WebDriverUtil.executeJavaScript(driver, "document.getElementById('" + id + "').focus();");

        log.debug("[log-Utils] WebDriverUtil - End focus method");
    }

    /******************************************************************************************************************************************************************
     *                 										           SCREENSHOT METHODS SECTION                  																   *
     *****************************************************************************************************************************************************************/
    /**
     * Saves a screenshot in a path with a timestamp
     * @param driver WebDriver element
     * @param folderPath to save the screenshot
     * @param baseFileName file name
     */
    public static void saveScreenshotPath(WebDriver driver, String folderPath, String baseFileName) {
        log.debug("[log-Utils] WebDriverUtil - Start saveScreenshotPath method");

        String timeStamp = getTimeStamp();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
        	log.info("[[File]]:  "+ baseFileName + "_" + timeStamp + ".png");
            FileUtils.copyFile(scrFile,
                    new File(folderPath + File.separator + baseFileName + "_" + timeStamp + ".png"));
        } catch (IOException e) {
            log.error("Error creating screenshot", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End saveScreenshotPath method");
    }

    // **** Keyboard events methods section ****//
    /**
     * Presses a keyboard key
     * @param key to press
     * @param sleepTime time to wait before and after to press the key
     */
    public static void pressKey(int key, int sleepTime) {
        log.debug("[log-Utils] WebDriverUtil - Start pressKey method");

        Robot r;

        try {
            r = new Robot();

            Thread.sleep(sleepTime * 1000);
            log.info("[[Press Key]]: " + key);
            r.keyPress(key);
            Thread.sleep(sleepTime * 1000);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End pressKey method");
    }

    /**
     * Releases a keyboard key
     * @param key to release
     * @param sleepTime time to wait before and after to release the key
     */
    public static void releaseKey(int key, int sleepTime) {
        log.debug("[log-Utils] WebDriverUtil - Start releaseKey method");

        Robot r;

        try {
            r = new Robot();

            Thread.sleep(sleepTime * 1000);
            r.keyRelease(key);
            log.info("[[Release Key]]: " + key);
            Thread.sleep(sleepTime * 1000);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End releaseKey method");
    }

    /**
     * Presses and releases a keyboard key
     * @param key to press and to release
     */
    public static void pressReleaseKey(int key) {
        log.debug("[log-Utils] WebDriverUtil - Start pressReleaseKey method");

        Robot r;

        try {
            r = new Robot();
            log.info("[[Press Key]]: " + key);
            r.keyPress(key);
            Thread.sleep(500);
            log.info("[[Release Key]]: " + key);
            r.keyRelease(key);
            Thread.sleep(500);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        } catch (InterruptedException e) {
            log.error("A thread has interrupted the current thread", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End pressReleaseKey method");
    }

    /******************************************************************************************************************************************************************
     *                 										           MOUSE EVENTS  METHODS SECTION                  															   *
     *****************************************************************************************************************************************************************/
    /**
     * Moves the mouse over an element
     * @param driver WebDriver element
     * @param selector By element
     */
    public static void moveMouseOverElement(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start moveMouseOverElement method");

        Robot r;

        try {
            r = new Robot();
            Point point = getPositionToClick(driver, selector);
            java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

            if (((int) location.getX()) != point.getX() || ((int) location.getY()) != point.getY()) {
            	log.info("[[Move mouse to]]: " +point.getX() + " and " + point.getY() );
                r.mouseMove(point.getX(), point.getY());
               WebDriverUtil.sleep(2);
            }
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End moveMouseOverElement method");
    }

    /**
     * Moves the mouse out of an element
     * @param driver WebDriver element
     * @param selector By element
     */
    public static void moveMouseOutElement(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start moveMouseOutElement method");

        Robot r;
        int x = 0, y = 0;

        try {
            r = new Robot();

            if (existsElement(driver, selector)) {
                WebElement element = driver.findElement(selector);
                Point position = element.getLocation();

                x = position.getX() - 10;
                y = position.getY() - 10;
            }

            Point toMove = new Point(x, y);
            java.awt.Point location = MouseInfo.getPointerInfo().getLocation();

            if (((int) location.getX()) != toMove.getX() && ((int) location.getY()) != toMove.getY()) {
            	log.info("[[Move mouse to]]: " +toMove.getX() + " and " + toMove.getY() );
                r.mouseMove(toMove.getX(), toMove.getY());
                WebDriverUtil.sleep(2);
            }
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End moveMouseOutElement method");
    }

    /**
     * Clicks with the mouse on an element
     * @param driver WebDriver element
     * @param selector By element
     */
    public static void clickOnWithMouse(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start clickOnWithMouse method");

        Robot r;

        try {
            r = new Robot();

            moveMouseOverElement(driver, selector);

            r.mousePress(InputEvent.BUTTON1_MASK);
            WebDriverUtil.sleep(2);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            WebDriverUtil.sleep(2);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End clickOnWithMouse method");
    }

    /**
     * Clicks with the mouse out of an element
     * @param driver WebDriver element
     * @param selector By element
     */
    public static void clickOutWithMouse(WebDriver driver, By selector) {
        WebDriverUtil.moveMouseOutElement(driver, selector);
        log.debug("[log-Utils] WebDriverUtil - Start clickOutWithMouse method");

        try {
            Robot r = new Robot();

            moveMouseOutElement(driver, selector);

            r.mousePress(InputEvent.BUTTON1_MASK);
            WebDriverUtil.sleep(1);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            WebDriverUtil.sleep(1);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End clickOutWithMouse method");
    }

    /**
     * Double clicks with the mouse on an element
     * @param driver WebDriver element
     * @param selector By element
     */
    public static void doubleClickOnWithMouse(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start doubleClickOnWithMouse method");

        Robot r;

        try {
            r = new Robot();

            moveMouseOverElement(driver, selector);

            r.mousePress(InputEvent.BUTTON1_MASK);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            r.mousePress(InputEvent.BUTTON1_MASK);
            r.mouseRelease(InputEvent.BUTTON1_MASK);
            WebDriverUtil.sleep(2);
        } catch (AWTException e) {
            log.error("The platform configuration does not allow low-level input control", e);
        }

        log.debug("[log-Utils] WebDriverUtil - End doubleClickOnWithMouse method");
    }

    /******************************************************************************************************************************************************************
     *                 										           WAIT METHODS SECTION                  																				   *
     *****************************************************************************************************************************************************************/
    /**
     * It sleeps the driver for X seconds. If the element exist in the DOM, the execution continue without waiting X seconds
     * @param driver WebDriver element for sleep
     * @param selector By element for wait
     * @param seconds to be slept
     * @return true if the element exist in the DOM and false in the opposite case
     */
    public static boolean wait(WebDriver driver, By selector, long seconds) {
        log.debug("[log-Utils] WebDriverUtil - Start wait method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
        	log.info("[[Wait element]]: " + selector.toString());
            w.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString() + " is missing in the DOM after waiting: " + seconds
                    + " seconds");
        }

        log.debug("[log-Utils] WebDriverUtil - End wait method");

        return retVal;
    }

    /**
     * It sleeps the driver for X seconds. If the element is visible in the page, the execution continue without waiting X seconds
     * @param driver WebDriver element for sleep
     * @param selector By element for wait
     * @param seconds to be slept
     * @return true if the element is visible in the page and false in the opposite case
     */
    public static boolean waitUntilVisible(WebDriver driver, By selector, long seconds) {
        log.debug("[log-Utils] WebDriverUtil - Start waitUntilVisible method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
        	log.info("[[Wait element]]: " + selector.toString() + "to be visible");
            w.until(ExpectedConditions.visibilityOfElementLocated(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The element: " + selector.toString() + " is not visible in the page after: " + seconds
                    + " seconds");
        }

        log.debug("[log-Utils] WebDriverUtil - End waitUntilVisible method");

        return retVal;
    }

    /**
     * It sleeps the driver for X seconds. If the element is clickable in the page, the execution continue without waiting X seconds
     * @param driver WebDriver element for sleep
     * @param selector By element for wait
     * @param seconds to be slept
     * @return true if the element is clickable in the page and false in the opposite case
     */
    public static boolean waitUntilElementClickable(WebDriver driver, By selector, long seconds) {
        log.debug("[log-Utils] WebDriverUtil - Start waitUntilElementClickable method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
        	log.info("[[Wait element]]: " + selector.toString() + "to be clickable");
            w.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (TimeoutException e) {
            retVal = false;

            log.error(
                    "The element: " + selector.toString() + " is not clickable after waiting: " + seconds + " seconds");
        }

        log.debug("[log-Utils] WebDriverUtil - End waitUntilElementClickable method");

        return retVal;
    }

    /**
     * It sleeps the driver for X seconds. If the text is present in element, the execution continue without waiting X seconds
     * @param driver WebDriver element for sleep
     * @param selector By element for wait
     * @param seconds to be slept
     * @param text to be find
     * @return true If the text is present in element, and false in the opposite case
     */
    public static boolean waitUntilTextPresent(WebDriver driver, By selector, long seconds, String text) {
        log.debug("[log-Utils] WebDriverUtil - Start waitUntilTextPresent method");

        WebDriverWait w = new WebDriverWait(driver, seconds);
        boolean retVal = true;

        try {
        	log.info("[[Wait element]]: " + selector.toString() + "to be present");
            w.until(ExpectedConditions.textToBePresentInElementLocated(selector, text));
        } catch (TimeoutException e) {
            retVal = false;

            log.error("The text: " + text + " in the element: " + selector.toString()
                    + " is missing in the DOM after waiting: " + seconds + " seconds");
        }

        log.debug("[log-Utils] WebDriverUtil - End waitUntilTextPresent method");

        return retVal;
    }

    /******************************************************************************************************************************************************************
     *                 										           PRIVATE  METHODS SECTION                  																		   *
     *****************************************************************************************************************************************************************/
    /**
     * Generates a timestamp
     * @return string with a timestamp
     */
    private static String getTimeStamp() {
        log.debug("[log-Utils] WebDriverUtil - Start getTimeStamp method");

        String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());

        log.debug("[log-Utils] WebDriverUtil - End getTimeStamp method");

        return timeStamp;

    }

    /**
     * Retrieves the center of an element to click it.
     * @param driver WebDriver element
     * @param selector By element
     * @return Point the position of the center of the element
     */
    private static Point getPositionToClick(WebDriver driver, By selector) {
        log.debug("[log-Utils] WebDriverUtil - Start getPositionToClick method");

        Point toReturn = null;
        int x = 0, y = 0;

        if (existsElement(driver, selector)) {
            WebElement element = driver.findElement(selector);
            Point position = element.getLocation();

            x = position.getX() + (element.getSize().getWidth() / 2);
            y = position.getY() + (element.getSize().getHeight() / 2);

            toReturn = new Point(x, y);
        }

        log.debug("[log-Utils] WebDriverUtil - End getPositionToClick method");

        return toReturn;
    }
    
    /******************************************************************************************************************************************************************
     *                 										           SLEEP METHODS                  																							   *
     *****************************************************************************************************************************************************************/
    /**
     * Stops the execution during some seconds
     * @param seconds time to stop the execution
     */
    public static void sleep(int seconds) {
    	try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			 log.error("A thread has interrupted the current thread", e);
		}
    }
}
