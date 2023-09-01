import utilities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.*;
import utilities.DriverManager;

import java.util.HashMap;

public class CheckoutTests extends BaseTest {
    @Test
    public void successfulCheckoutTest() {
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

        //Ir al carrito
        homePage.clickOnShoppingCartButton();

        //Verificar que los items agregados estan en el carrito
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(yourCartPage.isProductDisplayed("Sauce Labs Backpack"));

        //Presionar el boton de Checkout
        yourCartPage.clickOnCheckoutButton();

        //Verificar que el titulo de Checkout Information sea mostrado
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutInformationPage.isTitleDisplayed());

        //Ingresar datos validos
        checkoutInformationPage.setFirstNameTextBox("Laura");
        checkoutInformationPage.setLastNameTextBox("Delgadillo");
        checkoutInformationPage.setPostalCodeTextBox("34536");

        //Presionar el boton de Continue
        checkoutInformationPage.clickOnContinue();

        //Verificar que el titulo de Checkout Overview sea mostrado
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutOverviewPage.isTitleDisplayed());

        //Verificar que los productos sean mostrados
        Assertions.assertTrue(checkoutOverviewPage.isProductDisplayed("Sauce Labs Fleece Jacket"));
        Assertions.assertTrue(checkoutOverviewPage.isProductDisplayed("Sauce Labs Bike Light"));
        Assertions.assertTrue(checkoutOverviewPage.isProductDisplayed("Sauce Labs Backpack"));

        //Presionar el boton de Finish
        checkoutOverviewPage.clickOnFinishButton();

        //Verficiar que el header de Checkout Complete se muestre
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutCompletePage.isHeaderDisplayed());

        //Presionar el boton de Back Home
        checkoutCompletePage.clickOnBackHomeButton();

        //Verificar que el titulo de Home Page sea mostrado
        Assertions.assertTrue(homePage.isTitleDisplayed());
    }

    @Test
    public void correctTotalPriceTest() {
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

        //Ir al carrito
        homePage.clickOnShoppingCartButton();

        //Presionar el boton de Checkout
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        yourCartPage.clickOnCheckoutButton();

        //Ingresar datos validos
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);
        checkoutInformationPage.setFirstNameTextBox("Laura");
        checkoutInformationPage.setLastNameTextBox("Delgadillo");
        checkoutInformationPage.setPostalCodeTextBox("34536");

        //Presionar el boton de Continue
        checkoutInformationPage.clickOnContinue();

        //Verificar que el precio total sea correcto
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(checkoutOverviewPage.isTotalPriceCorrect());
    }

    @Test
    public void  checkoutValidationMessagesForEmptyFieldsTest() {
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

        //Ir al carrito
        homePage.clickOnShoppingCartButton();

        //Presionar el boton de Checkout
        YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
        yourCartPage.clickOnCheckoutButton();

        //Ingresar datos invalidos en Checkout Information
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(DriverManager.getDriver().driver);

        //Dejar el campo de nombre vacio
        checkoutInformationPage.setFirstNameTextBox("");
        checkoutInformationPage.clickOnContinue();
        Assertions.assertTrue(checkoutInformationPage.isErrorTextDisplayed("Error: First Name is required"));

        //Llenar el campo de nombre y dejar el campo de apellido vacio
        checkoutInformationPage.setFirstNameTextBox("Laura");
        checkoutInformationPage.setLastNameTextBox("");
        checkoutInformationPage.clickOnContinue();
        Assertions.assertTrue(checkoutInformationPage.isErrorTextDisplayed("Error: Last Name is required"));

        //Dejar el campo de codigo postal vacio
        checkoutInformationPage.setFirstNameTextBox("Laura");
        checkoutInformationPage.setLastNameTextBox("Delgadillo");
        checkoutInformationPage.setPostalCodeTextBox("");
        checkoutInformationPage.clickOnContinue();
        Assertions.assertTrue(checkoutInformationPage.isErrorTextDisplayed("Error: Postal Code is required"));
    }
}
