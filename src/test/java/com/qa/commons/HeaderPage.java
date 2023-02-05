package com.qa.commons;

import com.qa.BaseTest;
import com.qa.pages.ProductsPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class HeaderPage extends BaseTest {

    @AndroidFindBy(accessibility = "cart badge") private WebElement cart;
    @AndroidFindBy(accessibility = "open menu") private WebElement menu;

    public MenuPage clickOpenMenu(){
        try {
            click(menu);
        }
        catch (Exception e) {
            WebElement element = driver.findElement(AppiumBy.accessibilityId("open menu"));
            click(element);
        }
        return new MenuPage();
    }



}
