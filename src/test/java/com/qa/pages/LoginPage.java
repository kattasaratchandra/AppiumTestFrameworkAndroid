package com.qa.pages;

import com.qa.BaseTest;
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


}
