package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager OptionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param browserName
	 * @return this method will return webDriver
	 */

	public WebDriver init_Driver(Properties prop) {

		String browserName = prop.getProperty("browser").trim();
		
		//String browserName = System.getProperty("browser");

		System.out.println("browser name is :" + browserName);

		OptionsManager = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase("chrome")) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// Remote execution
				init_remoteDriver("chrome");
			} else {
				// Local execution
				WebDriverManager.chromedriver().setup();
				// driver = new ChromeDriver(OptionsManager.getChromeOption());
				tlDriver.set(new ChromeDriver(OptionsManager.getChromeOption()));
			}

		} else if (browserName.equalsIgnoreCase("firefox")) {

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// Remote execution
				init_remoteDriver("firefox");
			} else {
				// Local execution
				WebDriverManager.chromedriver().setup();
				// driver = new FirefoxDriver(OptionsManager.getFirefoxOption());
				tlDriver.set(new FirefoxDriver(OptionsManager.getFirefoxOption()));

			}

		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.chromedriver().setup();
			driver = new InternetExplorerDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.chromedriver().setup();
			driver = new EdgeDriver();

		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	private void init_remoteDriver(String browserName) {
		System.out.println("Running tests on remote web server :" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), OptionsManager.getChromeOption()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else if (browserName.equalsIgnoreCase("firefox")) {
			try {
				tlDriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), OptionsManager.getFirefoxOption()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to initialize the properties from the respective env
	 * file.
	 * 
	 * @return this is return properties class object with all the config properties
	 */

	public Properties init_prop() {
		FileInputStream ip = null;
		prop = new Properties();

		// mvn command line argument
		// mvn clean install -Denv="qa";

		String envName = System.getProperty("env");
		System.out.println("Running testes on environment: " + envName);

		if (envName == null) {
			System.out.println("No env is given ...hence running in QA");
			try {
				ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		else {
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/main/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/main/resources/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/main/resources/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resources/config/config.properties");
					break;

				default:
					System.out.println("please pass the right environment values...." + envName);
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();

			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Take a screenshot method
	 * 
	 * @return
	 */
	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}

}
