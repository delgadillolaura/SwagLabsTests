import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.YourCartPage;
import utilities.DriverManager;
import utilities.Product;

import java.util.HashMap;

public class YourCartTests extends BaseTest {

    @Test
    public void verifyYourCartProductNameWhenAProductIsAdded() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Bike Light"));
    }

    @Test
    public void verifyProductIsRemovedFromYourCartPage() throws InterruptedException {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");

        homePage.clickOnShoppingCartButton();

        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        yourCartPage.removeProduct("Sauce Labs Fleece Jacket");

        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket")));

    }

    @Test
    public void verifyCorrectDataIsDisplayedTest() {
        //Iniciar sesion
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        //Obtener productos
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        HashMap<String, Product> products = homePage.getProducts();

        //Agregar tres productos al carrito desde Home Page
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Backpack");

        //Ir al carrito
        homePage.clickOnShoppingCartButton();

        //Verificar que los items agregados estan en el carrito
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Backpack"));

        //Verificar que la descripcion y el precio de los items agregados es la correcta
        Assertions.assertTrue(yourCartPage.verifyProductsData(products));
    }
}
