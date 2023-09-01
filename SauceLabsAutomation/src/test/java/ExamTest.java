import utilities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.YourCartPage;
import utilities.DriverManager;

import java.util.HashMap;

public class ExamTest extends BaseTest{
    @Test
    public void examTest() {
        //Iniciar sesion
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        //Agregar tres productos al carrito desde Home Page
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        HashMap<String, Product> products = homePage.getProducts();

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

        //Verificar que la informacion los items agregados es la correcta
        Assertions.assertTrue(yourCartPage.verifyProductsData(products));

        //Vericar que el numero del carrrito es el correcto
        Assertions.assertTrue(yourCartPage.isShoppingCartNumberCorrect());

        //Remover los items agregados
        yourCartPage.removeProduct("Sauce Labs Fleece Jacket");
        yourCartPage.removeProduct("Sauce Labs Bike Light");
        yourCartPage.removeProduct("Sauce Labs Backpack");

        //Verificar que los items fueron removidos
        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket")));
        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Bike Light")));
        Assertions.assertFalse((yourCartPage.isProductDisplayed("Sauce Labs Backpack")));

        //Verificar que el icono del carrito no tiene ningun numero
        Assertions.assertTrue(yourCartPage.isShoppingCartNumberCorrect());
    }
}
