package com.qa.pages;

import com.qa.base.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MenuPage extends BaseTest{

	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	private MobileElement menuBtn;
	
	public SettingsPage clickOnMenuBtn() {
		click(menuBtn);
		return new SettingsPage();
	}
}
