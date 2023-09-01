import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.YourCartPage;
import utilities.DriverManager;

import java.util.HashMap;

public class ResetAppTest extends BaseTest{
    @Test
    public void resetAppStateTest() {
        //Iniciar sesion
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        //Agregar tres productos al carrito desde Home Page
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart("Sauce Labs Fleece Jacket");
        homePage.addProductToCart("Sauce Labs Bike Light");
        homePage.addProductToCart("Sauce Labs Backpack");

        //Hacer click en el boton de menu de Home Page
        homePage.clickOnBurgerButton();

        //Hacer click en el enlace Reset App
        homePage.clickOnResetAppStateLink();

        // Verificar que el carrito de compras esté vacío
        Assertions.assertTrue(homePage.isShoppingCartEmpty());

        // Verificar que los botones se encuentren en su estado original
        Assertions.assertTrue(homePage.verifyOriginalButtons());
    }
}
