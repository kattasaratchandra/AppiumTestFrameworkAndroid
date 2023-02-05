package com.qa.pages;

import com.qa.BaseTest;
import com.qa.commons.HeaderPage;
import com.qa.commons.MenuPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends HeaderPage {

    @AndroidFindBy(accessibility = "container header") private WebElement products;



    public Boolean isProductsPresent(){
        return products.isDisplayed();
    }



}
