package com.qa.opencart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private InternetExplorerOptions io;

	public OptionsManager(Properties prop) {
		this.prop = prop;

	}

	public ChromeOptions getChromeOption() {
		co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("enableVNC", true);

		}

		if (Boolean.parseBoolean(prop.getProperty("headless")))
			co.setHeadless(true);
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");
		return co;
	}

	public FirefoxOptions getFirefoxOption() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			fo.setHeadless(true);
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			fo.addArguments("--incognito");
		return fo;
	}

//	public InternetExplorerOptions getInternetOption() {
//		io = new InternetExplorerOptions();
//		if (Boolean.parseBoolean(prop.getProperty("headless")))
//			io.
//		if (Boolean.parseBoolean(prop.getProperty("incognito")))
//			io.addArguments("--incognito");
//		return io;
//	}
}
