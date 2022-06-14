package com.qa.opencart.Test;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.utils.constant;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC -100: Design Login test function types ")
@Story("INDIA-101: Design user id and password")

public class LoginPageTest extends BaseTest {

	@Description("Login test done by offshore")
	@Severity(SeverityLevel.CRITICAL)

	@Test(priority = 1)
	public void getLoginPageTitleTest() {
		String title = login.getLoginPageTitle();
		Assert.assertEquals(title, constant.LOGIN_PAGE_TITLE);
	}
//    @Description ("Verify page title ")
//    @Severity (SeverityLevel.NORMAL)
//	@Test(priority = 8)
//	public void getLoginPageTitleAllureFail() {
//		String title = "Account Login";
//		Assert.assertEquals(title, "Account Login");
//	}
    @Description("verify testing URLs ")
    @Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void getLoginPageURLTest() {
		String url = login.getLoginPageURL();
		Assert.assertTrue(url.contains(constant.LOGIN_PAGE_URL));

	}
    @Description("verify PasswordLinkExit ")
    @Severity(SeverityLevel.MINOR)
	@Test(priority = 3)
	public void ForgottenPasswordLinkExit() {
		Assert.assertTrue(login.ForgottenPasswordLinkExit());

	}

	@Test(priority = 4)
	public void LoginbuttonLinkExit() {
		Assert.assertTrue(login.LoginbuttonLinkExit());

	}

	@Test(priority = 5)
	public void searchbuttonisEnable() {
		Assert.assertTrue(login.searchbuttonisEnable());
	}

	@Test(priority = 6)
	public void RegisterLinkisExit() {
		Assert.assertTrue(login.RegisterLinkisExit());
	}

	@Test(priority = 7)
	public void DoLogin() {
		AccountPage accpage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		String accountpagetitle = accpage.AccountHeaderPageTitle();
		Assert.assertEquals(accountpagetitle, constant.ACCOUNT_PAGE_TITLE);
		Assert.assertTrue(accpage.LogoutLinkExist());

	}

}
