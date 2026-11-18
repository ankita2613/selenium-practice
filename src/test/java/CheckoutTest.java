import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void userCanCompleteAnOrder() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);
        products.addToCart("sauce-labs-backpack");
        products.addToCart("sauce-labs-bike-light");
        Assert.assertEquals(products.getCartCount(), "2");
        products.openCart();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getItemNames(),
                List.of("Sauce Labs Backpack", "Sauce Labs Bike Light"));
        cart.checkout();

        CheckoutPage checkout = new CheckoutPage(driver);
        checkout.fillDetails("Ankita", "Rajput", "160062");
        checkout.finish();
        Assert.assertEquals(checkout.getConfirmation(), "Thank you for your order!");
    }
}