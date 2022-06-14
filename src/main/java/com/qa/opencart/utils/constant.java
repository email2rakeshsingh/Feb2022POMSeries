package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class constant {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL = "route=account/login";
	public static final String ACCOUNT_PAGE_HEADER = "naveenopencart";
	public static final String ACCOUNT_PAGE_URL = "?route=account/account";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final List<String> ACCOUNT_PAGE_HEADER_LISTapp = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

	public static final List<String> ACCOUNT_PAGE_FOOTER_LIST = Arrays.asList("Information", "Customer Service",
			"Extras", "My Account");

	public static final String USER_logout_message = "Account Logout";

	public static final int DEFAULT_ELEMENT_TIME_OUT = 20;
	public static final int DEFAULT_TIME_OUT = 5;
	public static final String REGISTER_SUCCESSFUL_MESSAGE = "Your Account Has Been Created!";
	
	//******************** SHEET NAME******************************//
	public static final String REGISTER_SHEET_NAME = "Register";

}
