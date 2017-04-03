package WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import stepDefintions.Hooks;

public class WebElementsUI {
	WebDriver driver;

	public WebElementsUI(){
		 driver= Hooks.driver;
	}
	
	public static final By ENGLISH_FLAG = By.xpath("//a[@href='indexE.html']/img");


}
