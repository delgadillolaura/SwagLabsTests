package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutOverviewPage {
    WebDriver driver;

    @FindBy(className = "title")
    WebElement titleLabel;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "summary_tax_label")
    WebElement taxPrice;

    @FindBy(className = "summary_total_label")
    WebElement totalPrice;

    @FindBy(id = "finish")
    WebElement finishButton;

    public CheckoutOverviewPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isTitleDisplayed (){
        return titleLabel.isDisplayed();
    }

    public boolean isProductDisplayed(String product){
        for (WebElement element: productNames) {
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public boolean isTotalPriceCorrect() {
        double expectedTotalPrice = 0;

        for (WebElement element: productPrices) {
            String priceText = element.getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            expectedTotalPrice += price;
        }

        String taxPriceText = taxPrice.getText();
        double taxPriceValue =  Double.parseDouble(taxPriceText.replace("Tax: $", ""));

        expectedTotalPrice += taxPriceValue;

        String totalPriceText = totalPrice.getText();
        double totalPriceValue =  Double.parseDouble(totalPriceText.replace("Total: $", ""));

        return expectedTotalPrice == totalPriceValue;
    }

    public void clickOnFinishButton (){
        finishButton.click();
    }
}
