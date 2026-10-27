package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"})
    public void validLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );

        ProductsPage productsPage =
                new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getTitle(),
                "Products"
        );
    }
}