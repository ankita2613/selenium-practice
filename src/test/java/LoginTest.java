import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void createPages() {
        loginPage = new LoginPage(driver);
    }

    @Test(groups = {"smoke", "regression"})
    public void validLoginShowsProductsPage() {
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(groups = {"regression"})
    public void wrongPasswordShowsError() {
        loginPage.login("standard_user", "wrong_pass");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service");
    }

    @DataProvider(name = "badLogins")
    public Object[][] badLogins() {
        String generic = "Epic sadface: Username and password do not match any user in this service";
        return new Object[][] {
                {"",                "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user",   "",             "Epic sadface: Password is required"},
                {"standard_user",   "wrong_pass",   generic},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"#$%^ahv145",      "secret_sauce", generic},
                {"abc123",          "secret_sauce", generic},
                {"STANDARD_USER",   "secret_sauce", generic}
        };
    }

    @Test(dataProvider = "badLogins", groups = {"regression"})
    public void badLoginShowsError(String user, String pass, String expectedError) {
        loginPage.login(user, pass);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
    }
}