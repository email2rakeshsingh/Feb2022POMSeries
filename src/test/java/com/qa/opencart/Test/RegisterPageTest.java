package com.qa.opencart.Test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.constant;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = login.NvigatetoRegisterPage();

	}
	
	public String getRandomEmailId() {
		Random random= new Random();
		String email= "email2Feb2022"+random.nextInt(3000)+"@gmail.com";
		return email;
		
	}

	@DataProvider
	public Object[][] getRegisterTestData() {
		Object regData[][] = ExcelUtil.getTestData(constant.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegisterTestData")

	public void userRegistionPageTest(String FirstName, String LastName,  String Telephone,
			String Password, String Subscribe)

	{
		Assert.assertTrue(regPage.userRegister(FirstName, LastName, getRandomEmailId(), Telephone, Password, Subscribe));

	}
}
