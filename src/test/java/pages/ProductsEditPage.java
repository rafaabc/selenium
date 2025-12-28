package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsEditPage {
    private WebDriver driver;

    public ProductsEditPage(WebDriver driver) {
        this.driver = driver;
    }

    public String captureMessage() {
        return driver.findElement(By.cssSelector(".rounded")).getText();

    }
}
