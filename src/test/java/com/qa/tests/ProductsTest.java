package com.qa.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;

public class ProductsTest extends BaseTest {
	LoginPage loginPage;
	ProductsPage productsPage;
	SettingsPage settingsPage;
	ProductDetailsPage productDetailsPage;
	InputStream inputStream;
	JSONObject loginUsers;

	@BeforeClass
	public void beforeClass() throws IOException {
		try {
			String dataFile = "data/loginUsers.json";
			inputStream = getClass().getClassLoader().getResourceAsStream(dataFile);
			JSONTokener jsonToken = new JSONTokener(inputStream);
			loginUsers = new JSONObject(jsonToken);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null)
				inputStream.close();
		}	
		closeApp();
		launchApp();
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		loginPage = new LoginPage();
		System.out.println("\n" + "Starting Test:  " + m.getName() + "\n");
	}

	@Test(priority = 1)
	public void validateProductNameAndPriceOnProductsPage() {
		productsPage = loginPage.login(loginUsers.getJSONObject("validUserDetails").getString("username"), loginUsers.getJSONObject("validUserDetails").getString("password"));
		SoftAssert softAssert = new SoftAssert();
		
		String productName = productsPage.getProductName();
		Assert.assertEquals(productName, strings.get("products_page_title"));
		
		String productPrice = productsPage.getProductPrice();
		Assert.assertEquals(productPrice, strings.get("products_page_price"));
		
		settingsPage = productsPage.clickOnMenuBtn();
		loginPage = settingsPage.clickOnLogoutOption();
		softAssert.assertAll();
	}
	
	
	@Test(priority = 2)
	public void validateProductNameAndDescritpionOnProductdetailsPage() {
		productsPage = loginPage.login(loginUsers.getJSONObject("validUserDetails").getString("username"), loginUsers.getJSONObject("validUserDetails").getString("password"));
		SoftAssert softAssert = new SoftAssert();
		
		productDetailsPage = productsPage.clickOnProductName();
		String productName = productDetailsPage.getProductName();
		softAssert.assertEquals(productName, strings.get("product_details_page_title"));
		
		String productDescription = productDetailsPage.getProductDescription();
		softAssert.assertEquals(productDescription, "product_details_page_desc");
		
		productsPage = productDetailsPage.clickOnBackToProductsBtn();
		
		settingsPage = productsPage.clickOnMenuBtn();
		loginPage = settingsPage.clickOnLogoutOption();
		softAssert.assertAll();
	}
}
