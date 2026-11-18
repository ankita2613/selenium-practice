import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void addingOneItemShowsCountOfOne() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);
        products.addToCart("sauce-labs-backpack");

        Assert.assertEquals(products.getCartCount(), "1");
    }

    @Test(groups = {"regression"})
    public void cartPageListsTheAddedItem() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);
        products.addToCart("sauce-labs-backpack");
        products.openCart();

        Assert.assertEquals(new CartPage(driver).getItemNames(),
                List.of("Sauce Labs Backpack"));
    }
}