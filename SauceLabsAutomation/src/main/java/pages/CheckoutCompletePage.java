package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    WebDriver driver;

    @FindBy(className = "complete-header")
    WebElement successMessage;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isHeaderDisplayed (){
        return successMessage.isDisplayed();
    }

    public void clickOnBackHomeButton() {
        backHomeButton.click();
    }
}
