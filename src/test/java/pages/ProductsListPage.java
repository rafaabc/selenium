package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsListPage {
    private WebDriver driver;

    public ProductsListPage(WebDriver driver) {
        this.driver = driver;
    }

    public String captureButtonText() {
        return driver.findElement(By.linkText("ADICIONAR PRODUTO")).getText();

    }

    public ProductRegistrationPage clickRegisterProduct() {
        driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        return new ProductRegistrationPage(driver);
    }

    public String captureMessage() {
        return driver.findElement(By.cssSelector(".rounded")).getText();

    }

}
