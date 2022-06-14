package com.qa.opencart.Test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.pages.CommonsPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.ProductDescriptionConstant;

public class ProductInfoTest extends BaseTest {

	@BeforeClass
	public void ProductInfosetup() {
		productinfoPage = login.doLoginproductinfo(prop.getProperty("username").trim(),
				prop.getProperty("password").trim());
		commPage = new CommonsPage(driver);
		productinfoPage = new ProductInfoPage(driver);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "Macbook", "MacBook Pro", 4 }, { "iMac", "iMac", 3 },
				{ "Apple", "Apple Cinema 30\"", 6 }

		};
	}

	@Test(dataProvider = "getProductData")
	public void productImagesCount(String searchKey, String ProductName, int imagesCount) {
		searchResultsPage = commPage.doSearch(searchKey);
		productinfoPage = searchResultsPage.selectProductName(ProductName);
		Assert.assertEquals(productinfoPage.getProductImagesCount(), imagesCount);

	}

	@Test
	public void productDescriptionTest() {
		searchResultsPage = commPage.doSearch("MacBook");
		productinfoPage = searchResultsPage.selectProductName("MacBook Air");
		String productInfo = productinfoPage.getProductDescription();
		System.out.println("product desc :" + productInfo);

//		Assert.assertTrue(productInfo!= null);
//		Assert.assertFalse(productInfo.isEmpty());
//		Assert.assertTrue(productInfo.contains("MacBook Air"));
//		Assert.assertTrue(productInfo.contains("MacBook Air"));
//		Assert.assertTrue(productInfo.contains(ProductDescriptionConstant.MACBOOK_PRO_DESCRIPTION));

		softAssert.assertTrue(productInfo != null);
		softAssert.assertFalse(productInfo.isEmpty());
		softAssert.assertTrue(productInfo.contains("MacBook Air"));
		softAssert.assertTrue(productInfo.contains("MacBook Air"));
		softAssert.assertTrue(productInfo.contains(ProductDescriptionConstant.MACBOOK_PRO_DESCRIPTION));
		softAssert.assertAll();

	}
//	Brand:Apple
//	Availability:In Stock
//	price:$1,202.00
//	Product Code:Product 17
//	Reward Points:700
//	exTaxPrice:Ex Tax: $1,000.00
//	Name:MacBook Air
//	PASSED: productTestData

	@Test
	public void productTestData() {
		searchResultsPage = commPage.doSearch("MacBook");
		productinfoPage = searchResultsPage.selectProductName("MacBook Air");
		Map<String, String> actProductInfoMap = productinfoPage.getMetadataInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$1,202.00");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 17");
		softAssert.assertAll();
	}
}
//HashMap: Random Order 
//Brand:Apple
//Availability:In Stock
//price:$1,202.00
//Product Code:Product 17
//Reward Points:700
//exTaxPrice:Ex Tax: $1,000.00
//Name:MacBook Air
//PASSED: productTestData

// LinkedHashMap: Order based
//Name:MacBook Air
//Brand:Apple
//Product Code:Product 17
//Reward Points:700
//Availability:In Stock
//price:$1,202.00
//exTaxPrice:Ex Tax: $1,000.00
//PASSED: productTestData

//TreeMap : Sorted Order
//Availability:In Stock
//Brand:Apple
//Name:MacBook Air
//Product Code:Product 17
//Reward Points:700
//exTaxPrice:Ex Tax: $1,000.00
//price:$1,202.00
//PASSED: productTestData


