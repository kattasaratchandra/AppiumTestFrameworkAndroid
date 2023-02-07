package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.commons.HeaderPage;
import com.qa.commons.MenuPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.TestUtils;

import org.json.JSONObject;
import org.json.JSONTokener;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    HeaderPage headerPage;
    MenuPage menuPage;
    InputStream inputStream;
    JSONObject loginUsers;


    @BeforeClass
    public void beforeClass() throws IOException {
    try {
        String fileName = "data/loginUsers.json";
        inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        JSONTokener jsonTokener = new JSONTokener(inputStream);
        loginUsers = new JSONObject(jsonTokener);
    }catch(Exception e){
        e.printStackTrace();
        throw e;
    }
    finally {
        if(inputStream != null){
            inputStream.close();
        }
    }
    }

    @BeforeMethod
    public void beforeMethod(Method m){

        productsPage = new ProductsPage();
        headerPage = new HeaderPage();
        System.out.println("\n" + "***********" + " starting test: " + m.getName());
    }


    @Test(priority = 1)
    public void invalidUsername() throws InterruptedException {

        menuPage = headerPage.clickOpenMenu();
        loginPage = menuPage.clickLogin();
        loginPage.enterUsername(loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.clickSubmit();
        String expectedText = loginPage.getErrorText();
        String actualText = TestUtils.ERROR_TEXT;
        Assert.assertEquals(actualText, expectedText);

    }

    @Test(priority = 2)
    public void invalidPassword() {
        loginPage.enterUsername(loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.clickSubmit();
        String expectedText = loginPage.getErrorText();
        String actualText = TestUtils.ERROR_TEXT;
        Assert.assertEquals(actualText, expectedText);

    }

    @Test(priority = 3)
    public void validUsernameAndPassword() {

        loginPage.enterUsername(loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
        productsPage = loginPage.clickSubmit();
        productsPage.isProductsPresent();
        productsPage.clickOpenMenu();
        loginPage = menuPage.clickLogin();
        Assert.assertTrue(loginPage.isShoppingButtonPresent());
        loginPage.logOut();
    }



}
