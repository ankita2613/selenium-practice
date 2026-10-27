package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");

        Map<String, Object> prefs = new HashMap<>();

        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {

        if (driver != null && result.getStatus() == ITestResult.FAILURE) {

            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Files.createDirectories(Path.of("screenshots"));

            Path target = Path.of(
                    "screenshots",
                    result.getName() + "-"
                            + System.currentTimeMillis()
                            + ".png"
            );

            Files.copy(
                    screenshot.toPath(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("Screenshot saved: " + target);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}