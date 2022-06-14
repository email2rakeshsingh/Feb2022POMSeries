package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constant;

public class SearchresultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By resultpageHeader = By.cssSelector("div#content h1");

	public SearchresultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	public String getResultPageHeader() {
		return eleUtil.doGetElementText(resultpageHeader);
	}

	public ProductInfoPage selectProductName(String mainProductName) {

		WebElement mainProductEle = eleUtil.waitForElementVisible(By.linkText(mainProductName),
				constant.DEFAULT_ELEMENT_TIME_OUT);
		mainProductEle.click();
		return new ProductInfoPage(this.driver);

	}

}
