package configureEnvironment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Custom interface for configure  and create the webdriver.
 * @author estefafdez
 *
 */
public interface CreateDriver 
{
	public static WebDriver driver = WebDriverFactory.CreateNewWebDriver(WebDriverFactory.getBrowser());
	public static WebDriverWait wait = new WebDriverWait(driver, 30);
}
