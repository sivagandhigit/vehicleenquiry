package vehicleenquiry.page;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import vehicleenquiry.config.ConfigEnum;

import java.io.File;
import java.io.IOException;


public class BasePage {

    protected WebDriver driver;
    Logger logger = LogManager.getLogger(BasePage.class);
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement find(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,ConfigEnum.INSTANCE.getDriverTimeOutInSeconds());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void submit(By locator) {
        find(locator).submit();
    }

    public void type(String inputText, By locator) {
        find(locator).sendKeys(inputText);
    }

    public void clear(By locator) {
        find(locator).clear();
    }

    public void click(By locator) {
        find(locator).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void visit(String url) {
        driver.get(url);
    }

    /**
     * returns the text from the provided DOM locator using Selenium's getText method
     * @param locator
     * @return String representing the inner HTML of the DOM element (MW: To check it is actually inner-text
     */
    public String getText(By locator) {
        return find(locator).getText();
    }

    /**
    *  searches for the provided text in the current page Url
    *  @param searchString partial text to locate within the page url
    *  @exception IllegalStateException
    */
    public void validatePage(By locator) {
        if (!find(locator).isDisplayed()) {
        	logger.error("This is not the expecetd Page or element not displayed {}",()->locator.toString());
            throw new IllegalStateException("This is not the expecetd Page or element not displayed "+locator.toString());
        }
    }
    public Boolean isExpectedPage(By locator) {
        if (find(locator).isDisplayed()) {
            return true;
        }
        return false;
    }
    protected void takeScreenshot(RuntimeException e, String fileName) {
    	takeScreenshot(fileName,null);
        throw e;
    }
    
    protected void takeScreenshot(String fileName,String subFolder) {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
        	StringBuilder fileDirAndName   = new StringBuilder(ConfigEnum.INSTANCE.getScreenShotFolder());
        	if(subFolder!=null) {
        		fileDirAndName.append(subFolder);
        	}
        	fileDirAndName.append(fileName).append(".png");
            FileUtils.copyFile(screenShot, new File( fileDirAndName.toString()));
        } catch (IOException ioe) {
        	logger.error("while  takeScreenshot {}",() -> ioe.getMessage());
            throw new RuntimeException(ioe.getMessage(), ioe);
        }
    } 
    
}