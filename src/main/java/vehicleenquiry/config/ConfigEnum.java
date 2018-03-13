package vehicleenquiry.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Config Singleton enum to load the proprites file.
 * Based on this properties supported files type,screenshot folder ,home UrLdetermined
 *
 */
public enum ConfigEnum {
	INSTANCE;

	String screenShotFolder;
	String testDataFolder;
	String homePageURL;
	long driverTimeOutInSeconds;
	String supportedFileTypes;
	Logger logger = LogManager.getLogger(ConfigEnum.class);

	private ConfigEnum() {
		Properties prop = new Properties();
		try {
			prop.load(ConfigEnum.class.getClassLoader().getResourceAsStream("config.properties"));
			screenShotFolder = prop.getProperty("ScreenShotFolder");
			logger.debug(() -> screenShotFolder);
			testDataFolder = prop.getProperty("TestDataFolder");
			logger.debug(() -> testDataFolder);
			homePageURL = prop.getProperty("HomePageURL");
			logger.debug(() -> homePageURL);
			supportedFileTypes = prop.getProperty("SupportedFileTypes");
			logger.debug(() -> supportedFileTypes);
			driverTimeOutInSeconds = Long.parseLong(prop.getProperty("DriverTimeOutInSeconds"));
			logger.debug(() -> driverTimeOutInSeconds);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	public String getScreenShotFolder() {
		return screenShotFolder;
	}

	public String getTestDataFolder() {
		return testDataFolder;
	}

	public String getHomePageURL() {
		return homePageURL;
	}

	public long getDriverTimeOutInSeconds() {
		return driverTimeOutInSeconds;
	}

	public String getSupportedFileTypes() {
		return supportedFileTypes;
	}

}
