package modules.login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import java.time.Duration;
import java.util.Map;

@DisplayName("Web tests for the Login module")
public class LoginTest {
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
    @DisplayName("When using valid credentials, Then login successfully")
    void loginWithValidCredentials() {
        String message = new LoginPage(driver)
                .fillUsername("julio.lima")
                .fillPassword("123456")
                .submitLoginFormWithValidData()
                .captureButtonText();

        Assertions.assertEquals("ADICIONAR PRODUTO", message);
    }

    @Test
    @DisplayName("When using invalid credentials, Then error message displays")
    void loginWithInvalidCredentials() {
        String errorMessage = new LoginPage(driver)
                .fillUsername("julio.lima")
                .fillPassword("654321")
                .submitLoginFormWithInvalidData()
                .captureErrorMessage();

        Assertions.assertEquals("Falha ao fazer o login", errorMessage);
    }
}
