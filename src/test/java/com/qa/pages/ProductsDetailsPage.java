package com.qa.pages;

import com.qa.commons.HeaderPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ProductsDetailsPage extends HeaderPage {

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
    private WebElement SLBName;
    @AndroidFindBy(accessibility = "product price") private WebElement SLBPrice;


    public String getSLBTitle(){
        try {
            return getAttribute(SLBName, "text");
        } catch (Exception e){
            e.printStackTrace();
            WebElement element = driver.findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=" +
                    "\"container header\"]/android.widget.TextView"));
            return getAttribute(element, "text");
        }
    }

    public String getSLBPrice(){
        return getAttribute(SLBPrice, "text");
    }



}
