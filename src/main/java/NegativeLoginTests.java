import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class NegativeLoginTests {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);

        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait =
                new WebDriverWait(driver,
                        Duration.ofSeconds(10));

        String[][] cases = {
                {"", "secret_sauce",
                        "Epic sadface: Username is required"},

                {"standard_user", "",
                        "Epic sadface: Password is required"},

                {"standard_user", "wrong_pass",
                        "Epic sadface: Username and password do not match any user in this service"},

                {"locked_out_user", "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."}
        };

        for (String[] c : cases) {

            driver.get("https://www.saucedemo.com");

            driver.findElement(By.id("user-name"))
                    .sendKeys(c[0]);

            driver.findElement(By.id("password"))
                    .sendKeys(c[1]);

            driver.findElement(By.id("login-button"))
                    .click();

            String error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[data-test='error']")
                    )
            ).getText();

            check(
                    "Login Test",
                    c[2],
                    error
            );
        }

        driver.quit();
    }

    static void check(
            String testName,
            String expected,
            String actual
    ) {

        if (expected.equals(actual)) {
            System.out.println("PASS - " + testName);
        } else {
            System.out.println(
                    "FAIL - " + testName
                            + " Expected: " + expected
                            + " Actual: " + actual
            );
        }
    }
}