package com.qa.opencart.Base;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.beust.jcommander.Parameter;
import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchresultPage;

public class BaseTest {

	DriverFactory df;
	public WebDriver driver;
	protected Properties prop;

	protected LoginPage login;
	protected AccountPage accPage;
	protected CommonsPage commPage;
	protected SearchresultPage searchResultsPage;
	protected ProductInfoPage productinfoPage;
	protected RegisterPage regPage;

	protected SoftAssert softAssert;

	@Parameters({ "browser", "browserversion" })
	@BeforeTest
	public void setup(String browser, String browserversion) {
		df = new DriverFactory();
		prop = df.init_prop();

		if (browser != null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserversion);

		}

		driver = df.init_Driver(prop);
		login = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
