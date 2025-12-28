package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage fillUsername(String username) {
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys(username);

        return this;
    }

    public LoginPage fillPassword(String password) {
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys(password);

        return this;
    }

    public ProductsListPage submitLoginFormWithValidData() {
        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        return new ProductsListPage(driver);
    }

    public LoginPage submitLoginFormWithInvalidData() {
        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        return this;
    }

    public String captureErrorMessage() {
        return driver.findElement(By.cssSelector(".rounded")).getText();
    }


}
