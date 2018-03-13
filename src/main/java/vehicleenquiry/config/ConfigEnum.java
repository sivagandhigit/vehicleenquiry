package vehicleenquiry.config;

import java.io.IOException;
import java.util.Properties;

public enum ConfigEnum {
	INSTANCE;

	String screenShotFolder;
	String testDataFolder;
	String homePageURL;
	long driverTimeOutInSeconds;
	String supportedFileTypes;

	private ConfigEnum() {
		Properties prop = new Properties();
		try {
			prop.load(ConfigEnum.class.getClassLoader().getResourceAsStream("config.properties"));
			screenShotFolder = prop.getProperty("ScreenShotFolder");
			testDataFolder = prop.getProperty("TestDataFolder");
			homePageURL = prop.getProperty("HomePageURL");
			supportedFileTypes = prop.getProperty("SupportedFileTypes");
			driverTimeOutInSeconds = Long.parseLong(prop.getProperty("DriverTimeOutInSeconds"));
		} catch (IOException e) {
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
