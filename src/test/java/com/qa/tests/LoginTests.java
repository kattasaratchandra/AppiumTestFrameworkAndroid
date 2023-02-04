package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {

    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass
    public void beforeClass(){

    }

    @AfterClass
    public void afterClass(){
        productsPage.logOut();
    }

    @BeforeMethod
    public void beforeMethod(Method m){

        productsPage = new ProductsPage();
        System.out.println("\n" + "***********" + " starting test: " + m.getName());
    }

    @AfterMethod
    public void afterMethod(){

    }

    @Test(priority = 1)
    public void invalidUsername() throws InterruptedException {

        productsPage.clickOpenMenu();
        loginPage = productsPage.clickLogin();
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("10203040");
        loginPage.clickSubmit();
        String expectedText = loginPage.getErrorText();
        String actualText = "Provided credentials do not match any user in this service.";
        Assert.assertEquals(actualText, expectedText);

    }

    @Test(priority = 2)
    public void invalidPassword() {
        productsPage.clickOpenMenu();
        loginPage = productsPage.clickLogin();
        loginPage.enterUsername("invalidUser");
        loginPage.enterPassword("10203040n");
        loginPage.clickSubmit();
        String expectedText = loginPage.getErrorText();
        String actualText = "Provided credentials do not match any user in this service.";
        Assert.assertEquals(actualText, expectedText);

    }

    @Test(priority = 3)
    public void validUsernameAndPassword() {

        loginPage.enterUsername("bob@example.com");
        loginPage.enterPassword("10203040");
        productsPage = loginPage.clickSubmit();
        productsPage.isProductsPresent();
        productsPage.clickOpenMenu();
        loginPage = productsPage.clickLogin();
        Assert.assertTrue(loginPage.isShoppingButtonPresent());

    }



}
