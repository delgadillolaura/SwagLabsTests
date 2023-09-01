package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {
    WebDriver driver;

    @FindBy(className = "inventory_details_name")
    WebElement productName;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;

    public ProductPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductNameDisplayed(String product) {
        return productName.getText().equals(product);
    }

    public void addToCart() {
        String productNameLowerCase = productName.getText().toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;

        WebElement addToCartButton = driver.findElement(By.id(addToCartId));
        addToCartButton.click();
    }

    public int getCurrentCartNumber() {
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));

        if (elements.isEmpty()) {
            return 0;
        } else {
            String shoppingCartValue = shoppingCartBadge.getText();
            return Integer.parseInt(shoppingCartValue);
        }
    }

    public boolean verifyCartNumberIncreaseWhenProductAdded(int currentCartNumber) {
        String shoppingCartText = shoppingCartBadge.getText();
        int shoppingCartValue = Integer.parseInt(shoppingCartText);

        return shoppingCartValue == currentCartNumber + 1;
    }

    public void removeFromCart() {
        String productNameLowerCase = productName.getText().toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String removeFromCartId = "remove-";
        removeFromCartId = removeFromCartId + productNameLowerCase;

        WebElement addToCartButton = driver.findElement(By.id(removeFromCartId));
        addToCartButton.click();
    }

    public boolean verifyCartNumberDecreaseWhenProductRemoved(int currentCartNumber) {
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));

        if (elements.isEmpty()) {
            return currentCartNumber - 1 == 0;
        } else {
            String shoppingCartText = shoppingCartBadge.getText();
            int shoppingCartValue = Integer.parseInt(shoppingCartText);
            return shoppingCartValue == currentCartNumber - 1;
        }
    }
}
