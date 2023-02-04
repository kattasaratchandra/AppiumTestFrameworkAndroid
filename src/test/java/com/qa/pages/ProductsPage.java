package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends BaseTest {

    @AndroidFindBy(accessibility = "open menu") private WebElement menu;
    @AndroidFindBy(accessibility = "menu item log in") private WebElement login;
    @AndroidFindBy(accessibility = "container header") private WebElement products;
    @AndroidFindBy(accessibility = "menu item log out") private WebElement logout;
    @AndroidFindBy(id = "android:id/button1") private WebElement okNotification;
    @AndroidFindBy(xpath = "//*[@text='You are successfully logged out.']") private WebElement logoutTitle ;

    public ProductsPage clickOpenMenu(){
        click(menu);
        return this;
    }

    public LoginPage clickLogin(){
        click(login);
        return new LoginPage();
    }

    public ProductsPage clickLogout(){
        click(logout);
        return this;
    }

    public ProductsPage clickOk(){
        click(okNotification);
        return this;
    }

    public Boolean isLogoutSuccessfullyTitlePresent(){
        return logoutTitle.isDisplayed();
    }

    public Boolean isProductsPresent(){
        return products.isDisplayed();
    }

    public void logOut() {
        click(menu);
        clickLogout();
        clickOk();
        isElementPresent(logoutTitle);
        clickOk();
    }

}
