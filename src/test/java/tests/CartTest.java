package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void addProductToCart() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        ProductsPage products =
                new ProductsPage(driver);

        products.addToCart("sauce-labs-backpack");

        Assert.assertEquals(
                products.getCartCount(),
                "1"
        );
    }
}