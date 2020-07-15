package com.qa.pages;

import com.qa.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
	@AndroidFindBy(accessibility = "test-Username")
	private MobileElement userNameTxtField;
	@AndroidFindBy(accessibility = "test-Password")
	private MobileElement passwordTxtField;
	@AndroidFindBy(accessibility = "test-LOGIN")
	private MobileElement loginBtn;
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private MobileElement errorTxt;

	// enter username
	public LoginPage enterUsername(String userName) {
		sendKeys(userNameTxtField, userName);
		return this;
	}

	// enter passsword
	public LoginPage enterPassword(String password) {
		sendKeys(passwordTxtField, password);
		return this;
	}

	//get error text 
	public String getErrorTxt() {
		return getAttribute(errorTxt, "text");
	}
	//click on loginButton
	public ProductsPage clickLoginBtn() {
		click(loginBtn);
		return new ProductsPage();
	}
	
	public ProductsPage login(String userName, String password ) {
		enterUsername(userName);
		enterPassword(password);
		return clickLoginBtn();
	}
}
