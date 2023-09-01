package pages;

import utilities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class YourCartPage {
    WebDriver driver;
    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(className = "cart_item")
    List<WebElement> cartProductElements;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public YourCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isProductDisplayed(String product){
        for (WebElement element: productNames) {
            if(element.getText().equalsIgnoreCase(product)){
                return true;
            }
        }
        return false;
    }

    public void removeProduct(String product){
        //sauce-labs-bike-bight
        //remove-
        String productLowerCase = product.toLowerCase();
        productLowerCase = productLowerCase.replace(" ", "-");
        String removeProductId = "remove-"+productLowerCase;
        WebElement removeButton = driver.findElement(By.id(removeProductId));
        removeButton.click();
    }


    public boolean isShoppingCartNumberCorrect() {
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));

        if (elements.isEmpty()) {
            return productNames.isEmpty();
        } else {
            String shoppingCartValue = shoppingCartBadge.getText();
            int actualShoppingCartNumber = Integer.parseInt(shoppingCartValue);
            int expectedShoppingCartNumber = productNames.size();

            return actualShoppingCartNumber == expectedShoppingCartNumber;
        }
    }

    public boolean isShoppingCartEmpty() {
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));
        return elements.isEmpty();
    }

    public boolean verifyProductsData(HashMap<String, Product> expectedProducts) {

        for (WebElement cartProductElement : cartProductElements) {
            String productName = cartProductElement.findElement(By.className("inventory_item_name")).getText();
            String productDescription = cartProductElement.findElement(By.className("inventory_item_desc")).getText();
            String priceText = cartProductElement.findElement(By.className("inventory_item_price")).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));

            Product expectedProduct = expectedProducts.get(productName);
            if (!expectedProduct.getName().equals(productName) ||
                    !expectedProduct.getDescription().equals(productDescription) ||
                    expectedProduct.getPrice() != price) {
                return false;
            }
        }

        return true;
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }
}
