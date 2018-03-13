package vehicleenquiry.stepdef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import vehicleenquiry.config.ConfigEnum;
import vehicleenquiry.page.BasePage;
import vehicleenquiry.page.HomePage;
import vehicleenquiry.page.VRMSearchResultPage;


public class VRMSearchStepDefinitions {
	Logger logger = LogManager.getLogger(VRMSearchStepDefinitions.class);	
	private WebDriver driver;
	private HomePage home;	
	private VRMSearchResultPage searchResult;
	
	@Before
	public void setUp() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--enable-automation");
        options.addArguments("--start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);		
		driver = new ChromeDriver(capabilities);
	}
	
    @After
    public void tearDown() {
        driver.quit();
    }
    
	@Given("I landed in VRM Search home page")
	public void lanchHomePage() throws InterruptedException {
		home = new HomePage(driver);
		home.visit(ConfigEnum.INSTANCE.getHomePageURL());
	}
	
	@When("^I search for each (.*)$")
	public String searchVRN(String vrn) {
		BasePage page = home.searchVRN(vrn);
		assertTrue("Error in VRN Number Search", page instanceof VRMSearchResultPage);
		searchResult =  (VRMSearchResultPage)page ;
		return null;
	}
	
	@Then("^I can view each vehicle (.*) and (.*)$")
	public void verifyMakeAndModel(String make, String colour) {
		assertEquals("Make is Not Matching",make,searchResult.getMakeText());
		assertEquals("Colour is Not Matching",colour,searchResult.getColourText());
		logger.info("Search Result VRM NumberL{} Model:{} colour:{}"
				,()->searchResult.getRegNumberText(),()->searchResult.getMakeText(),()->searchResult.getColourText());
	}
}
