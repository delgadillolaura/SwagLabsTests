package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class TwitterPage {
    WebDriver driver;

    public TwitterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isTwitterPageDisplayed(String expectedURL) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Esperar hasta que haya al menos dos ventanas (ventana actual y ventana de Twitter)
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Cambiar el enfoque a la ventana de Twitter
        String originalWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        windows.remove(originalWindow);
        driver.switchTo().window(windows.iterator().next());

        // Verificar la URL en la ventana de Twitter
        String actualURL = driver.getCurrentUrl();
        boolean isURLMatched = actualURL.equals(expectedURL);

        // Cerrar la ventana de Twitter y volver a la ventana original
        driver.close();
        driver.switchTo().window(originalWindow);

        return isURLMatched;
    }
}
