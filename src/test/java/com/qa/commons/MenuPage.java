package com.qa.commons;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class MenuPage extends BaseTest {

    @AndroidFindBy(accessibility = "menu item log in") private WebElement login;
    @AndroidFindBy(accessibility = "menu item log out") private WebElement logout;

    public LoginPage clickLogin(){
        click(login);
        return new LoginPage();
    }

    public LoginPage clickLogout(){
        click(logout);
        return new LoginPage();
    }


}
