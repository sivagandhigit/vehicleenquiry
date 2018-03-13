package vehicleenquiry.stepdef;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import vehicleenquiry.config.ConfigEnum;
import vehicleenquiry.domain.VehicleDetails;
import vehicleenquiry.domain.VehicleSearchResultDetails;
import vehicleenquiry.file.VehicleFileFilter;
import vehicleenquiry.file.VehicleFileMetaData;
import vehicleenquiry.page.BasePage;
import vehicleenquiry.page.HomePage;
import vehicleenquiry.page.VRMSearchResultPage;
import vehicleenquiry.parser.VehicleEnquiryFileParser;
import vehicleenquiry.parser.VehicleEnquiryParserRegistry;

public class VRNBulkFileSearchStepDefinitions {
	Logger logger = LogManager.getLogger(VRMSearchStepDefinitions.class);
	List<VehicleSearchResultDetails> resultList;

	public WebDriver createWebDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--enable-automation");
		options.addArguments("--start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(capabilities);

		return driver;
	}

	@Before
	public void setUp() {
		resultList = new ArrayList<VehicleSearchResultDetails>();
	}

	@Given("I retrieved supported vrn search files from configured test data folder")
	public void lanchHomePage() throws InterruptedException {

	}

	@When("I search and retrieve vehicle details for each vrn in search files")
	public void searchVRNsBulk() {
		VehicleFileFilter fileFilter = new VehicleFileFilter(ConfigEnum.INSTANCE.getTestDataFolder());
		List<VehicleFileMetaData> metaDataList = fileFilter.filter();
		metaDataList.forEach(metaData -> {
			VehicleEnquiryFileParser parser = VehicleEnquiryParserRegistry.getVehicleFileParser(metaData);
			if (parser != null) {
				List<VehicleDetails> vehicleDetaisList = parser.parse(metaData);
				if (vehicleDetaisList.size() > 0) {
					vehicleDetaisList.forEach(vehicleDetails -> {
						resultList.add(searchVRN(vehicleDetails));
					});
				}
			}
		});
	}

	public VehicleSearchResultDetails searchVRN(VehicleDetails vehicleDetails) {
		WebDriver driver = createWebDriver();
		VehicleSearchResultDetails result = createAndPopulateExpectedResultDetails(vehicleDetails);
		HomePage home = new HomePage(driver);

		home.visit(ConfigEnum.INSTANCE.getHomePageURL());
		BasePage page = home.searchVRN(vehicleDetails.getVrn());

		if (page instanceof VRMSearchResultPage) {
			VRMSearchResultPage searchResult = (VRMSearchResultPage) page;
			populateExpectedResultDetails(result, searchResult);
		} else {
			result.setMessage("Vehicle details not found");
		}
		driver.quit();
		return result;
	}

	@Then("Assert and write results to configured output folder")
	public void verifyVRNsMakeAndModelWriteResults() {
		resultList.forEach(result -> {
			Boolean assertRow = Boolean.valueOf(result.getAssertMake()) && Boolean.valueOf(result.getAssertColour());
			assertTrue("VRN Number " + result.getActualVRN() + " " + result.getActualMake() + " "
					+ result.getActualColour() + " " + result.getExpectedVRN() + " " + result.getExpectedMake() + " "
					+ result.getExpectedColour(), assertRow);
		});
	}

	private VehicleSearchResultDetails createAndPopulateExpectedResultDetails(VehicleDetails vehicleDetails) {
		VehicleSearchResultDetails result = new VehicleSearchResultDetails();
		result.setExpectedVRN(vehicleDetails.getVrn());
		result.setExpectedMake(vehicleDetails.getModel());
		result.setExpectedColour(vehicleDetails.getColour());
		return result;
	}

	private VehicleSearchResultDetails populateExpectedResultDetails(VehicleSearchResultDetails result,
			VRMSearchResultPage searchResult) {

		result.setActualVRN(searchResult.getRegNumberText());
		result.setActualMake(searchResult.getMakeText());
		result.setActualColour(searchResult.getColourText());
		Boolean assetMake = result.getExpectedMake().equalsIgnoreCase(searchResult.getMakeText());
		result.setAssertMake(assetMake.toString());
		Boolean assetColour = result.getExpectedColour().equalsIgnoreCase(searchResult.getColourText());
		result.setAssertColour(assetColour.toString());
		return result;
	}
}
