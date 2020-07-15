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
import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

public class LoginTest extends BaseTest {
	LoginPage loginPage;
	ProductsPage productsPage;
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
	public void invalidPassword() {
		loginPage.enterUsername(loginUsers.getJSONObject("invalidUserName").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidUserName").getString("password"));
		loginPage.clickLoginBtn();
		String actualErrorTxt = loginPage.getErrorTxt();
		String expectedErrorTxt = strings.get("err_invalid_username_or_password");
		Assert.assertEquals(actualErrorTxt, expectedErrorTxt);

	}

	@Test(priority = 2)
	public void invalidUsername() {
		loginPage.enterUsername(loginUsers.getJSONObject("invalidPassword").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		loginPage.clickLoginBtn();
		String actualErrorTxt = loginPage.getErrorTxt();
		String expectedErrorTxt = strings.get("err_invalid_username_or_password");
		Assert.assertEquals(actualErrorTxt, expectedErrorTxt);
	}

	@Test(priority = 3)
	public void successfulLogin() {
		loginPage.enterUsername(loginUsers.getJSONObject("validUserDetails").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUserDetails").getString("password"));
		productsPage = loginPage.clickLoginBtn();
		String actualErrorTxt = productsPage.getProductsPageTitle();
		String expectedErrorTxt = strings.get("product_title");
		Assert.assertEquals(actualErrorTxt, expectedErrorTxt);
	}

}
