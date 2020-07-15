package com.qa.pages;

import com.qa.base.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends BaseTest{

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]\n")
	private MobileElement productName;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[2]\n")
	private MobileElement productDescription;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-BACK TO PRODUCTS\"]/android.widget.TextView")
	private MobileElement backToProductsBtn;
	
	
	public String getProductName() {
		return getAttribute(productName, "text");
	}
	
	public String getProductDescription() {
		return getAttribute(productDescription, "text");
	}
	
	public ProductsPage clickOnBackToProductsBtn() {
		click(backToProductsBtn);
		return new ProductsPage();
	}
}
