package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LandingPage {

    By search = By.xpath("//input[@type='search']");
    By productName = By.cssSelector("h4.product-name");
    By topDeals = By.linkText("Top Deals");
    By increment = By.cssSelector("a.increment");
    By addToCart = By.cssSelector(".product-action button");

    public WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItem(String name) {
        driver.findElement(search).sendKeys(name);
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public void selectTopDealsPage() {
        driver.findElement(topDeals).click();
    }

    public String getTitleLandingPage() {
        return driver.getTitle();
    }

    public void incrementQuantity(int quantity) {
        for(int i=0;i<quantity;i++) {
            driver.findElement(increment).click();
        }
    }

    public void addToCart() {
        driver.findElement(addToCart).click();
    }
}
