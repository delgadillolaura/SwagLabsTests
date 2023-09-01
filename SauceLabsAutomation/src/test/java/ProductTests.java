import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import utilities.DriverManager;

public class ProductTests extends BaseTest {
    @Test
    public void verifyAddToCartAndRemoveFromCartButton() {
        //Iniciar sesion
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        //Ingresar al nombre de un producto
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnProductLink("Sauce Labs Backpack");

        //Verificar que el nombre del producto seleccionado sea mostrado
        ProductPage productPage = new ProductPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(productPage.isProductNameDisplayed("Sauce Labs Backpack"));

        //Obtener el numero actual del carrito
        int currentCartNumber = productPage.getCurrentCartNumber();

        //Agregar el producto al carrito
        productPage.addToCart();
        Assertions.assertTrue(productPage.verifyCartNumberIncreaseWhenProductAdded(currentCartNumber));

        //Obtener el numero actual del carrito
        currentCartNumber = productPage.getCurrentCartNumber();

        //Remover el producto del carrito
        productPage.removeFromCart();
        Assertions.assertTrue(productPage.verifyCartNumberDecreaseWhenProductRemoved(currentCartNumber));
    }
}
