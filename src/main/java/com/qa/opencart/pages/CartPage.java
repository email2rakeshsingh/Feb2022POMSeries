package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class CartPage {
	private By cart = By.id("Page-Cart");
	private By item = By.id("Page-item");

	public void CartPage() {
		System.out.println("cart items");
		System.out.println("click on cart page :"+cart);
	}
}
