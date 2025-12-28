package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductRegistrationPage {
    private WebDriver driver;

    public ProductRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductRegistrationPage fillProductName(String product) {
        driver.findElement(By.id("produtonome")).sendKeys(product);

        return this;
    }

    public ProductRegistrationPage fillProductValue(String value) {
        driver.findElement(By.id("produtovalor")).sendKeys(value);

        return this;
    }

    public ProductRegistrationPage fillProductColor(String color) {
        driver.findElement(By.id("produtocores")).sendKeys(color);

        return this;
    }

    public ProductsListPage clickSaveButtonWithError() {
        driver.findElement(By.id("btn-salvar")).click();

        return new ProductsListPage(driver);
    }

    public ProductsEditPage clickSaveButtonWithSuccess() {
        driver.findElement(By.id("btn-salvar")).click();

        return new ProductsEditPage(driver);
    }

}
