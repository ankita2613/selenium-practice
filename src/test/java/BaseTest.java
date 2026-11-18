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
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");

        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Path.of("screenshots"));
            Path target = Path.of("screenshots/" + result.getName() + "-" + System.currentTimeMillis() + ".png");
            Files.copy(shot.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved: " + target);
        }
        driver.quit();
    }
}