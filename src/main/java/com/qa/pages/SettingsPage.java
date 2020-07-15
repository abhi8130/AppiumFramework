package com.qa.pages;

import com.qa.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BaseTest {
	@AndroidFindBy(accessibility = "test-LOGOUT")
	private MobileElement logoutTxt;
	
	public LoginPage clickOnLogoutOption() {
		click(logoutTxt);
		return new LoginPage();
	}
}
