package com.qa.pages;

import com.qa.BaseTest;
import com.qa.commons.HeaderPage;
import com.qa.commons.MenuPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.Base64;

public class LoginPage extends BaseTest {

    @AndroidFindBy(accessibility = "Username input field") private WebElement usernameField;
    @AndroidFindBy(accessibility = "Password input field") private WebElement passwordField;
    @AndroidFindBy(accessibility = "Login button") private WebElement loginButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[" +
            "@content-desc=\"generic-error-message\"]/android.widget.TextView") private WebElement errorText ;
    @AndroidFindBy(accessibility = "Go Shopping button") private WebElement goShoppingButton;

    @AndroidFindBy(id = "android:id/button1") private WebElement okNotification;
    @AndroidFindBy(xpath = "//*[@text='You are successfully logged out.']") private WebElement logoutTitle ;



    public  LoginPage enterUsername(String username){
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password){
        sendKeys(passwordField, password);
        return this;
    }

    public ProductsPage clickSubmit(){
        click(loginButton);
        return new ProductsPage();
    }

    public String getErrorText(){
        return getAttribute(errorText, "text");
    }

    public Boolean isShoppingButtonPresent(){
        return isElementPresent(goShoppingButton);
    }

    public LoginPage clickOk(){
        click(okNotification);
        return this;
    }

    public Boolean isLogoutSuccessfullyTitlePresent(){
        return logoutTitle.isDisplayed();
    }

    public void logOut() {
        MenuPage menuPage = new HeaderPage().clickOpenMenu();
        menuPage.clickLogout();
        clickOk();
        isElementPresent(logoutTitle);
        clickOk();
    }


}
