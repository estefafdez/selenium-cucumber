package WebElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import stepDefintions.Hooks;

public class HomePageElements {
	WebDriver driver;

	public HomePageElements(){
		 driver= Hooks.driver;
	}
	
	@FindBy(xpath = "//a[@href='indexE.html']/img")
    WebElement englishFlag;
	
}
