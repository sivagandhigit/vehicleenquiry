package vehicleenquiry.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
	
	By vrmField = By.cssSelector("input[name='Vrm']");
	By continueButton = By.cssSelector("button[name='Continue']");
	By errorMessage = By.cssSelector(".error-summary-list a");
	By anyResultItem = By.cssSelector(".list-summary-item");
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
    public BasePage searchVRN(String vrmNumber) {
        try {
            type(vrmNumber, vrmField);
            submit(continueButton);
            if(isExpectedPage(anyResultItem)) {
            	takeScreenshot("SerachResult"+vrmNumber,null);
            	return new VRMSearchResultPage(driver);
            }	
        }
        catch (RuntimeException e) {
            takeScreenshot(e, "SearchError"+vrmNumber);
        }
        return this;
    }	
    
    public String getFirstErrorMessage() {
    	return find(errorMessage).getText();
    }

}
