package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {
    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstNameTextBox;

    @FindBy(id = "last-name")
    WebElement lastNameTextBox;

    @FindBy(id = "postal-code")
    WebElement postalCodeTextBox;

    @FindBy(id="continue")
    WebElement continueButton;

    @FindBy(className = "title")
    WebElement titleLabel;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMessage;

    public CheckoutInformationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setFirstNameTextBox(String firstName){
        firstNameTextBox.sendKeys(firstName);
    }

    public void setLastNameTextBox(String lastName){
        lastNameTextBox.sendKeys(lastName);
    }

    public void setPostalCodeTextBox(String postalCode){
        postalCodeTextBox.sendKeys(postalCode);
    }

    public void clickOnContinue(){
        continueButton.click();
    }

    public boolean isTitleDisplayed (){
        return titleLabel.isDisplayed();
    }

    public boolean isErrorTextDisplayed(String error){
        String actualErrorMessage = errorMessage.getText();
        return error.equalsIgnoreCase(actualErrorMessage);
    }
}
