import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.TwitterPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.DriverManager;

public class FooterTest extends BaseTest{
    @Test
    public void testTwitterFooterLink() {
        //Iniciar sesion
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox("standard_user");
        loginPage.setPasswordTextBox("secret_sauce");
        loginPage.clickOnLoginButton();

        //Ingresar al link de Twitter
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnTwitterLink();

        //Verificar que ingrese al link de Twitter
        TwitterPage twitterPage = new TwitterPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(twitterPage.isTwitterPageDisplayed("https://twitter.com/saucelabs"));
    }
}
