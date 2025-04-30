package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OffersPage {

    By search = By.xpath("//input[@type='search']");
    By productName = By.cssSelector("tr > td:first-child");

    public WebDriver driver;

    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItem(String name) {
        driver.findElement(search).sendKeys(name);
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }
}
