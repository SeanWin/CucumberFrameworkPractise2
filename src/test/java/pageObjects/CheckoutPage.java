package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    By cartBag = By.cssSelector("img[alt='Cart']");
    By checkOutButton = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    By promoBtn = By.cssSelector("button[class='promoBtn']");
    By placeOrderBtn = By.xpath("//button[text()='Place Order']");

    public WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkoutItems() {
        driver.findElement(cartBag).click();
        driver.findElement(checkOutButton).click();
    }

    public boolean isPlaceOrderBtnPresent() {
        return driver.findElement(placeOrderBtn).isDisplayed();
    }

    public boolean isPromoBtnPresent() {
        return driver.findElement(promoBtn).isDisplayed();
    }

}
