package modules.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Web tests for the Login module")
public class LoginTest {
    private WebDriver driver;
    @Test
    @DisplayName("When using valid credentials, Then login successfully")
    void loginWithValidCredentials() {
        //open the browser
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.driver.get("http://165.227.93.41/lojinha-web/v2/");

        //fill username
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys("julio.lima");

        //fill password
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("123456");

        //click enter button
        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        //assert the user is logged in
        String message = driver.findElement(By.linkText("Boas vindas, Julio de Lima!")).getText();
        Assertions.assertEquals("Boas vindas, Julio de Lima!", message);


    }

    @Test
    @DisplayName("When using invalid credentials, Then error message displays")
    void loginWithInvalidCredentials() {
        //open the browser
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.driver.get("http://165.227.93.41/lojinha-web/v2/");

        //fill username
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys("julio.lima");

        //fill password
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("654321");

        //click enter button
        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        String errorMessage = driver.findElement(By.cssSelector(".rounded")).getText();
        Assertions.assertEquals("Falha ao fazer o login", errorMessage);

    }
}
