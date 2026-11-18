import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage {
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
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }
}