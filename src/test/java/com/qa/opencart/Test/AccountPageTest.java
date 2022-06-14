package com.qa.opencart.Test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.constant;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountPageTest extends BaseTest {
	@Description("Account setup for AccountPageTes ")
	@Severity(SeverityLevel.CRITICAL)
	@BeforeClass
	public void accSetup() {
		accPage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());

	}
	@Description("Account page title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void pagetitleTest() {

		String title = accPage.AccountHeaderPageTitle();
		System.out.println("account page title Test :" + title);
		Assert.assertEquals(title, constant.ACCOUNT_PAGE_TITLE);
	}
	@Description("Account page urls")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void getPageURLTest() {
		Assert.assertTrue(accPage.AccountpageURLExist().contains(constant.ACCOUNT_PAGE_URL));
	}
	@Description("Account page logo header ")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void logoHeaderTest() {
		Assert.assertTrue(accPage.accountpageHeaderExist(), constant.ACCOUNT_PAGE_HEADER);
	}
	@Description("Account page header page")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 4)
	public void accountPageHeaderPageTest() {
		boolean pageheader = accPage.accountpageHeaderExist();
		System.out.println("Account page header :" + pageheader);
		Assert.assertTrue(pageheader, constant.ACCOUNT_PAGE_HEADER);
	}
	@Description("Account page List item")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 5)
	public void accountPageHeaderListTest() {
		List<String> actualacessList = accPage.accountpageheaderLinksExist();
		System.out.println("Actual acc page list :" + actualacessList);
		Assert.assertEquals(actualacessList, constant.ACCOUNT_PAGE_HEADER_LISTapp);

	}
	@Description("Account page footer list")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 6)
	public void accountPageFooterListTest() {
		List<String> actualacessList = accPage.footerLinksExist();
		System.out.println("Actual acc page list :" + actualacessList);
		Assert.assertEquals(actualacessList, constant.ACCOUNT_PAGE_FOOTER_LIST);

	}

	@Test(priority = 7)
	public void isUserloggedoutTest() {
		accPage.clickOnLogout();
		Assert.assertEquals(login.getlogoutmessage(), constant.USER_logout_message);

	}

//*********************************SearchPage_Test*****************************
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { 
			{ "Macbook" }, 
			{ "iMac" }, 
			{ "Samsang" }, 
			{ "Apple" }

		};

	}
	@Description("Account page dataprovider test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 8, dataProvider = "getProductName")
	public void searchTest(String paroductName) {
		commPage = new CommonsPage(driver);
		searchResultsPage = commPage.doSearch(paroductName);
		String resultpageHeader = searchResultsPage.getResultPageHeader();
		Assert.assertTrue(resultpageHeader.contains(paroductName));

	}
//***********************product_Info*******************************************
	@DataProvider
	public Object[][] getProductData() {
//		return new Object[][] { 
//			{ "Macbook", "MacBook Pro" }, 
//			{ "iMac", "iMac" }, 
//			{ "Apple", "Apple Cinema 30\"" }
//
//		};
		
		return ExcelUtil.getTestData("products");
	}
	@Description("Account page dataprovider by sheet")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 9, dataProvider = "getProductData")
	public void searchTest(String paroductName, String mainProductName) {
		commPage = new CommonsPage(driver);
		searchResultsPage = commPage.doSearch(paroductName);
		String resultpageHeader = searchResultsPage.getResultPageHeader();
		productinfoPage = searchResultsPage.selectProductName(mainProductName);
		String MainproductNameValue = productinfoPage.getMainProductName();
		System.out.println(MainproductNameValue);
		Assert.assertEquals(MainproductNameValue, mainProductName);

	}
}
