import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class AutomatedLoginCart {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title"))).getText();
        System.out.println("Heading on page: " + heading);
        check("login worked", "Products", heading);

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        System.out.println("Items in cart: " + cartCount);
        check("one item in cart", "1", cartCount);

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        System.out.println("Items in cart: " + cartCount);
        check("two items in cart", "2", cartCount);

        driver.findElement(By.className("shopping_cart_link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        System.out.println("Items found in cart page: " + items.size());
        for (WebElement item : items) {
            System.out.println(" - " + item.getText());
        }
        check("cart page item count", "2", String.valueOf(items.size()));

        String firstName = items.get(0).getText();
        check("first item name", "Sauce Labs Backpack", firstName);

        String secondName = items.get(1).getText();
        check("second item name", "Sauce Labs Bike Light", secondName);

        driver.findElement(By.id("checkout")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Ankita");
        driver.findElement(By.id("last-name")).sendKeys("Rajput");
        driver.findElement(By.id("postal-code")).sendKeys("160062");
        driver.findElement(By.id("continue")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();

        String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header"))).getText();
        System.out.println("Message: " + message);
        check("order completed", "Thank you for your order!", message);

        driver.quit();
    }

    static void check(String testName, String expected, String actual) {
        if (expected.equals(actual)) {
            System.out.println("PASS - " + testName);
        } else {
            System.out.println("FAIL - " + testName + " | expected: " + expected + " | actual: " + actual);
        }
    }

    public static class CartPage {
        private WebDriver driver;
        private WebDriverWait wait;

        private By itemNames = By.className("inventory_item_name");
        private By checkoutButton = By.id("checkout");

        public CartPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public List<String> getItemNames() {
            wait.until(ExpectedConditions.visibilityOfElementLocated(itemNames));
            List<String> names = new ArrayList<>();
            for (WebElement element : driver.findElements(itemNames)) {
                names.add(element.getText());
            }
            return names;
        }

        public void checkout() {

            wait.until(
                    ExpectedConditions.elementToBeClickable(checkoutButton)
            ).click();

            System.out.println(
                    "After Checkout Click URL: "
                            + driver.getCurrentUrl()
            );
        }
    }
}