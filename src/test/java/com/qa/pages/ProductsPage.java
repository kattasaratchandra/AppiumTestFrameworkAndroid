package com.qa.pages;

import com.qa.commons.HeaderPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsPage extends HeaderPage {

    @AndroidFindBy(accessibility = "container header") private WebElement products;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sauce Labs Backpack\"]") private WebElement SLBName;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"$29.99\"]") private WebElement SLBPrice;





    public Boolean isProductsPresent(){
        return products.isDisplayed();
    }

    public String getSLBTitle(){
        return getAttribute(SLBName, "text");
    }

    public String getSLBPrice(){
        return getAttribute(SLBPrice, "text");
    }

    public ProductsDetailsPage clickSLB(){
        click(SLBName);
        return new ProductsDetailsPage();
    }



}
