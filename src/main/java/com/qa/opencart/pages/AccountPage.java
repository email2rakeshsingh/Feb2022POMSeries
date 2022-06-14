package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constant;

import io.qameta.allure.Step;

public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleuUtil;
	
	

	private By AccHeader = By.cssSelector("div#logo a ");
	private By searhBox = By.xpath("//input [@name='search']");
	private By pageHeaderh2Links = By.cssSelector("div#content h2");
	private By footerLinks = By.cssSelector("div.row h5");
	private By LogoutLink = By.linkText("Logout");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleuUtil = new ElementUtil(this.driver);
		
	}
@Step ("AccountHeaderPageTitle")
	public String AccountHeaderPageTitle() {
		String title = eleuUtil.waitForTitleIs(constant.ACCOUNT_PAGE_TITLE, constant.DEFAULT_TIME_OUT);
		System.out.println("Account page title :" + title);
		return title;

	}
@Step ("AccountpageURLExist")
	public String AccountpageURLExist() {
		String Accountpageurl = eleuUtil.waitForUrlContains(constant.ACCOUNT_PAGE_URL,
				constant.DEFAULT_ELEMENT_TIME_OUT);
		System.out.println(Accountpageurl);
		return Accountpageurl;
	}
@Step ("accountpageHeaderExist")
	public boolean accountpageHeaderExist() {
		return eleuUtil.waitForElementVisible(AccHeader, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();

	}
@Step ("searchBoxExist")
	public boolean searchBoxExist() {
		return eleuUtil.waitForElementVisible(searhBox, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
@Step ("accountpageheaderLinksExist")
	public List<String> accountpageheaderLinksExist() {
		List<WebElement> lists = eleuUtil.waitForElementsVisible(pageHeaderh2Links, constant.DEFAULT_ELEMENT_TIME_OUT);
		List<String> values = new ArrayList<>();
		for (WebElement e : lists) {
			String val = e.getText();
			values.add(val);
		}
		return values;
	}
@Step ("footerLinksExist")
	public List<String> footerLinksExist() {
		List<WebElement> footerlinks = eleuUtil.waitForElementsVisible(footerLinks, constant.DEFAULT_ELEMENT_TIME_OUT);
		List<String> val = new ArrayList<>();
		for (WebElement e : footerlinks) {
			String text = e.getText();
			val.add(text);

		}
		return val;
	}

	public boolean LogoutLinkExist() {
		return eleuUtil.waitForElementVisible(LogoutLink, constant.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public LoginPage clickOnLogout() {
		if (LogoutLinkExist()) {
			eleuUtil.doClick(LogoutLink);
			return new LoginPage(driver);
		}
		return null;

	}
}
