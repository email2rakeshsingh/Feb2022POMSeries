package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constant;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	/**
	 * first we need to create by locators also this is private locators
	 */
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By ForgottenPassword = By.linkText("Forgotten Password");
	private By Loginbutton = By.xpath("//input[@value='Login']");
	private By searchbutton = By.cssSelector(".btn.btn-default.btn-lg");
	private By Register = By.linkText("Register");
	private By acclogoutmessage = By.cssSelector("div#content h1");
	
	private By testingLocaterGit= By.cssSelector("div#Rakesh.singh");

	/**
	 * @param driver
	 */

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	/**
	 * 
	 * @return title of this code
	 */
	@Step("Getting login page title...")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(constant.LOGIN_PAGE_TITLE, constant.DEFAULT_TIME_OUT);
		System.out.println("Login page Title is :" + title);
		return title;

	}

	@Step("Getting login page LoginPageURL...")
	public String getLoginPageURL() {
		String url = eleUtil.waitForUrlContains(constant.LOGIN_PAGE_URL, constant.DEFAULT_TIME_OUT);
		System.out.println("Login page URL IS:" + url);
		return url;

	}

	@Step("Getting login page PasswordLinkExit...")
	public boolean ForgottenPasswordLinkExit() {
		return eleUtil.waitForElementVisible(ForgottenPassword, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	@Step("Getting login page LoginbuttonLinkExit...")
	public boolean LoginbuttonLinkExit() {
		return eleUtil.waitForElementVisible(Loginbutton, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	@Step("Getting login page searchbuttonisEnable...")
	public boolean searchbuttonisEnable() {
		return eleUtil.waitForElementVisible(searchbutton, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}

	@Step("Getting login page RegisterLinkisExit...")
	public boolean RegisterLinkisExit() {
		return eleUtil.waitForElementVisible(Register, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	@Step("login with userName{0} and password{1}")
	
	public AccountPage doLogin(String username, String pwd) {
		System.out.println(username + ": " +pwd);
		eleUtil.waitForElementVisible(email, constant.DEFAULT_ELEMENT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(Loginbutton);

		return new AccountPage(driver);

	}

	
	public ProductInfoPage doLoginproductinfo(String username, String pwd) {
		eleUtil.waitForElementVisible(email, constant.DEFAULT_ELEMENT_TIME_OUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(Loginbutton);

		return new ProductInfoPage(driver);

	}

	@Step("getting logout page ")
	public String getlogoutmessage() {
		String logoutmesg = eleUtil.waitForElementVisible(acclogoutmessage, constant.DEFAULT_ELEMENT_TIME_OUT)
				.getText();
		System.out.println("Logout message ====:" + logoutmesg);
		return logoutmesg;
	}

	@Step("Navigate to RegisterPage")
	public RegisterPage NvigatetoRegisterPage() {
		eleUtil.doClick(Register);
		return new RegisterPage(driver);
	}
}
