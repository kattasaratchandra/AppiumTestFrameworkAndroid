package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.commons.HeaderPage;
import com.qa.commons.MenuPage;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.utils.TestUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

public class ProductsTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;
    HeaderPage headerPage;
    ProductsDetailsPage productsDetailsPage;
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

        loginPage = new LoginPage();
        System.out.println("\n" + "***********" + " starting test: " + m.getName());
    }


    @Test(priority = 1)
    public void validateProductFromProductsPage() throws InterruptedException {

        SoftAssert softAssert = new SoftAssert();
        productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password"));
        String actualProductName = productsPage.getSLBTitle();
        String expectedProductName = TestUtils.SLBTitle;

        String actualProductPrice = productsPage.getSLBPrice();
        String expectedProductPrice = TestUtils.SLBPrice;
        loginPage.logOut();
        softAssert.assertEquals(actualProductName, expectedProductName);

        softAssert.assertEquals(actualProductPrice, expectedProductPrice);

        softAssert.assertAll();

    }

    @Test(priority = 2)
    public void validateProductFromProductDetailsPage() {

        SoftAssert softAssert = new SoftAssert();
        productsPage = loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password"));
        productsDetailsPage = productsPage.clickSLB();
        String actualProductName = productsDetailsPage.getSLBTitle();
        String expectedProductName = TestUtils.SLBTitle;

        String actualProductPrice = productsDetailsPage.getSLBPrice();
        String expectedProductPrice = TestUtils.SLBPrice;
        loginPage.logOut();
        softAssert.assertEquals(actualProductName, expectedProductName);

        softAssert.assertEquals(actualProductPrice, expectedProductPrice);

        softAssert.assertAll();

    }



}
