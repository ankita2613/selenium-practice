package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By title = By.className("title");
    private By cartBadge = By.className("shopping_cart_badge");
    private By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(title)
        ).getText();
    }

    public void addToCart(String productId) {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-" + productId)
                )
        ).click();
    }

    public String getCartCount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}