package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constant;

public class CommonsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By search = By.name("search");
	private By searchIcone = By.cssSelector("div#search button");

	public CommonsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	public SearchresultPage doSearch(String produtName) {
		WebElement searchele = eleUtil.waitForElementVisible(search, constant.DEFAULT_ELEMENT_TIME_OUT);
		searchele.clear();
		searchele.sendKeys(produtName);
		eleUtil.doClick(searchIcone);
		return new SearchresultPage(this.driver);

	}
}
