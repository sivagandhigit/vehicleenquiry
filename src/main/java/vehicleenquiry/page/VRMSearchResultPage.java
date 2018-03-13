package vehicleenquiry.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VRMSearchResultPage  extends BasePage{
	By regNumber = By.cssSelector(".reg-mark");
	By make = By.cssSelector(".list-summary li:nth-of-type(2) strong");
	By colour = By.cssSelector(".list-summary li:nth-of-type(3) strong");
	
	public VRMSearchResultPage(WebDriver driver) {
		super(driver);
	}

	public String getMakeText() {
		return find(make).getText();
	}

	public String getColourText() {
		return find(colour).getText();
	}
	
	public String getRegNumberText() {
		return find(regNumber).getText();
	}
	
}
