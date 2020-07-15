package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.InteractsWithApps;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {
	protected static AppiumDriver driver;
	protected HashMap<String, String> strings = new HashMap<String, String>();
	protected Properties prop;
	InputStream inputStream;
	InputStream stringsis;
	TestUtils testUtils;

	// initalize the page factory objects
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	// create an instance of the driver
	@Parameters({ "udid", "platformName", "deviceName" })
	@BeforeTest
	public void createDriverInstance(String udid, String platformName, String deviceName) throws Exception {
		prop = new Properties();
		String propFileName = "config.properties";
		String xmlFileName = "strings/strings.xml";

		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(inputStream);

		stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
		testUtils = new TestUtils();
		strings = testUtils.parseStringXML(stringsis);

		// specify the desired capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.UDID, "164cd41d9805");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi Note 4");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 15);
		String appUrl = getClass().getResource(prop.getProperty("androidAppLocation")).getFile();
		capabilities.setCapability(MobileCapabilityType.APP, appUrl);
		capabilities.setCapability("appPackage", prop.getProperty("androidAppPackage"));
		capabilities.setCapability("appActivity", prop.getProperty("androidAppActivity"));
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("skipUnlock", true);
		try {
			driver = new AndroidDriver(new URL(prop.getProperty("appiumUrl")), capabilities);
			String sessionId = driver.getSessionId().toString();
			System.out.println("SessionId: " + sessionId);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null)
				inputStream.close();
			if (stringsis != null)
				stringsis.close();
		}
	}
	
	
	public void closeApp() {
		((InteractsWithApps)driver).closeApp();
	}
	
	public void launchApp() {
		((InteractsWithApps)driver).launchApp();
	}

	// driver commands
	// waitForVisibilty of an element
	public void waitForVisibility(MobileElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.durationInSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	// click on an element
	public void click(MobileElement e) {
		waitForVisibility(e);
		e.click();
	}

	// type content in editable field
	public void sendKeys(MobileElement e, String text) {
		waitForVisibility(e);
		e.sendKeys(text);
	}

	// return current value of attribute
	public String getAttribute(MobileElement e, String text) {
		waitForVisibility(e);
		return e.getAttribute(text);
	}

	@AfterTest
	public void tearDown() {
		driver.quit(); // quit the driver after executing the test
	}

}