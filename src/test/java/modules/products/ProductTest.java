package modules.products;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.time.Duration;
import java.util.Map;

@DisplayName("Web tests for the products module")
public class ProductTest {
    private WebDriver driver;

    @BeforeEach
    void beforeEach() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.setExperimentalOption("prefs", Map.of("profile.password_manager_leak_detection", false));
        this.driver = new ChromeDriver(options);
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
        //Login
        String toastMessage = new LoginPage(driver)
                .fillUsername("julio.lima")
                .fillPassword("123456")
                .submitLoginFormWithValidData()
                .clickRegisterProduct()
                .fillProductName("Playstation 5")
                .fillProductValue("001")
                .fillProductColor("silver")
                .clickSaveButtonWithSuccess()
                .captureMessage();

        Assertions.assertEquals("Produto adicionado com sucesso", toastMessage);

    }

    @Test
    @DisplayName("When submit a product form with invalid data, Then error message displays")
    void submitProductWithValueAsZero() {
        //Login
        String errorMessage = new LoginPage(driver)
                .fillUsername("julio.lima")
                .fillPassword("123456")
                .submitLoginFormWithValidData()
                .clickRegisterProduct()
                .fillProductName("Playstation 5")
                .fillProductValue("000")
                .fillProductColor("silver")
                .clickSaveButtonWithError()
                .captureMessage();

        //assert error message
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", errorMessage);

    }
}
