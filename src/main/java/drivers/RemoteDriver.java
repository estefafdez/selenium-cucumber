package drivers;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Custom driver for Sauce Labs
 * @author Estefanía Fdez Muñoz <estefafdez@gmail.com>
 */
public class RemoteDriver implements  WebDriver {

    RemoteWebDriver remoteDriver;

    /**
     * Constructor
     */
    public RemoteDriver(RemoteWebDriver driver) {
        remoteDriver = driver;
    }

    /**
     * Get the Custom Job Session ID
     * @return session id
     */
    public String getCustomWebDriveSessionID() {
        return remoteDriver.getSessionId().toString();
    }

    /**
     * Get the Job Session ID
     * @return session id
     */
    public String getRemoteDriverSessionID(RemoteWebDriver driver) {
        return driver.getSessionId().toString();
    }

    public void get(String url) {
        remoteDriver.get(url);
    }

    public String getCurrentUrl() {
        return remoteDriver.getCurrentUrl();
    }

    public String getTitle() {
        return remoteDriver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        return remoteDriver.findElements(by);
    }

    public WebElement findElement(By by) {
        return remoteDriver.findElement(by);
    }

    public String getPageSource() {
        return remoteDriver.getPageSource();
    }

    public void close() {
        remoteDriver.close();
    }

    public void quit() {
        remoteDriver.quit();
    }

    public Set<String> getWindowHandles() {
        return remoteDriver.getWindowHandles();
    }

    public String getWindowHandle() {
        return remoteDriver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return remoteDriver.switchTo();
    }

    public Navigation navigate() {
        return remoteDriver.navigate();
    }

    public Options manage() {
        return remoteDriver.manage();
    }
    
    /**
     * Stops the execution during some seconds
     * @param seconds time to stop the execution
     */
    public void sleep(int seconds) {
        WebDriverUtil.sleep(seconds);
    }


}
