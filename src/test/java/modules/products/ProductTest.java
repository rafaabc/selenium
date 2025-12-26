package modules.products;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Web tests for the products module")
public class ProductTest {
    private WebDriver driver;

    @BeforeEach
    void beforeEach() {
        //open the browser
        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        this.driver = new ChromeDriver(options);
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        this.driver.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @AfterEach
    void afterEach() {
        driver.quit();
    }

    @Test
    @DisplayName("When submit a product form with valid data, Then the product is registered")
    void submitProductWithValidData() {
        //fill username
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys("julio.lima");

        //fill password
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("123456");

        //click enter button
        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        //click add product button
        driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //fill name
        driver.findElement(By.id("produtonome")).click();
        driver.findElement(By.id("produtonome")).sendKeys("Playstation 5");

        //fill value
        driver.findElement(By.id("produtovalor")).click();
        driver.findElement(By.id("produtovalor")).sendKeys("001");

        //fill color
        driver.findElement(By.id("produtocores")).click();
        driver.findElement(By.id("produtocores")).sendKeys("silver");

        //click salvar button
        driver.findElement(By.id("btn-salvar")).click();

    }

    @Test
    @DisplayName("When submit a product form with invalid data, Then error message displays")
    void submitProductWithValueAsZero() {
        //fill username
        driver.findElement(By.cssSelector("label[for='usuario']")).click();
        driver.findElement(By.id("usuario")).sendKeys("julio.lima");

        //fill password
        driver.findElement(By.cssSelector("label[for='senha']")).click();
        driver.findElement(By.id("senha")).sendKeys("123456");

        //click enter button
        driver.findElement(By.cssSelector(".btn.waves-effect.waves-light")).click();

        //click add product button
        driver.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //fill name
        driver.findElement(By.id("produtonome")).click();
        driver.findElement(By.id("produtonome")).sendKeys("Playstation 5");

        //fill value
        driver.findElement(By.id("produtovalor")).click();
        driver.findElement(By.id("produtovalor")).sendKeys("000");

        //fill color
        driver.findElement(By.id("produtocores")).click();
        driver.findElement(By.id("produtocores")).sendKeys("silver");

        //click salvar button
        driver.findElement(By.id("btn-salvar")).click();

        //assert error message
        String errorMessage = driver.findElement(By.cssSelector(".rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", errorMessage);

    }
}
