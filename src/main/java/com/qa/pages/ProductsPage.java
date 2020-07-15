package com.qa.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage {
	@AndroidFindBy(xpath = "//*[@class=\"android.widget.TextView\" and @text =\"PRODUCTS\"]")
	private MobileElement productsPageTitleTxt;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private MobileElement productName;
	@AndroidFindBy(xpath = "(//android.widget.TextView[@content-desc=\"test-Price\"])[1]\n")
	private MobileElement productPrice;

	public String getProductsPageTitle() {
		return getAttribute(productsPageTitleTxt, "text");
	}

	public String getProductName() {
		return getAttribute(productName, "text");
	}

	public String getProductPrice() {
		return getAttribute(productPrice, "text");
	}
	
	public ProductDetailsPage clickOnProductName() {
		click(productName);
		return new ProductDetailsPage();
	}
}
