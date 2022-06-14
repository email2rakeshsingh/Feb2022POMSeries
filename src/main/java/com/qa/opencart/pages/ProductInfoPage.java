package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.constant;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By mainproductName = By.cssSelector("div#content h1");
	private By ProductImges = By.cssSelector("ul.thumbnails a");
	private By productPageParagraph = By.cssSelector("div#tab-description");
	private By Metadata = By.xpath("//div[@class='col-sm-4']//ul[1]/li");
	private By priceData = By.xpath("//div[@class='col-sm-4']//ul[2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	public String getMainProductName() {
		return eleUtil.doGetElementText(mainproductName);
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(ProductImges, constant.DEFAULT_ELEMENT_TIME_OUT).size();

	}

	public String getProductDescription() {
		return eleUtil.doGetElementText(productPageParagraph);
	}

	public Map<String, String> getMetadataInfo() {
		//Map<String, String> productInfoMap = new HashMap<String, String>();//Random Order
		//Map<String, String> productInfoMap = new LinkedHashMap<String, String>();// Order based
		Map<String, String> productInfoMap = new TreeMap<String, String>();// Sorted Order
		
		productInfoMap.put("Name", getMainProductName());

		// meta data
//		Brand: Apple
//		Product Code: Product 17
//		Reward Points: 700
//		Availability: In Stock

		List<WebElement> metaList = eleUtil.getElements(Metadata);
		for (WebElement e : metaList) {
			String metadata = e.getText();
			String metaKey = metadata.split(":")[0].trim();
			String metaVal = metadata.split(":")[1].trim();

			productInfoMap.put(metaKey, metaVal);

		}
		// MetaPrice
		List<WebElement> metaListPrice = eleUtil.getElements(priceData);
		String metaPrice = metaListPrice.get(0).getText().trim();
		String exTaxPrice = metaListPrice.get(1).getText().trim();
		productInfoMap.put("price", metaPrice);
		productInfoMap.put("exTaxPrice", exTaxPrice);

		return productInfoMap;

	}

}
//
