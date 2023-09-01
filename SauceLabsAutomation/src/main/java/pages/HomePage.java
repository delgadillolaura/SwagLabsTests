package pages;

import utilities.Product;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;

import java.time.Duration;
import java.util.*;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "app_logo")
    WebElement appLogo;

    @FindBy(className = "product_sort_container")
    WebElement sortComboBox;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerButton;

    @FindBy(className = "inventory_item")
    List<WebElement> productElements;

    @FindBy(className = "btn_inventory")
    List<WebElement> cartButtons;

    @FindBy(className = "title")
    WebElement titleLabel;

    @FindBy(className = "social_twitter")
    WebElement TwitterLink;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean pageTitleIsDisplayed(){
        return appLogo.isDisplayed();
    }

    public boolean isTitleDisplayed() {
        return titleLabel.isDisplayed();
    }

    public void selectSortComboBox(String option){
        Select selectObject = new Select(sortComboBox);
        selectObject.selectByVisibleText(option);
    }

    public boolean areProductsInDescendantOrderByName(){
        List<WebElement> products = driver.findElements(By.className("inventory_item_name"));
        List<String> actualProductNames = new ArrayList<>();

        for( WebElement element: products){
            actualProductNames.add(element.getText());
        }

        return Ordering.natural().reverse().isOrdered(actualProductNames);
    }

    public void addProductToCart(String productName){
        //sauce-labs-fleece-jacket
        // id button = add-to-cart-sauce-labs-fleece-jacket
        String productNameLowerCase = productName.toLowerCase();
        productNameLowerCase = productNameLowerCase.replace(" ","-");
        String addToCartId = "add-to-cart-";
        addToCartId = addToCartId + productNameLowerCase;

        WebElement addToCartButton = driver.findElement(By.id(addToCartId));
        addToCartButton.click();
    }

    public void clickOnShoppingCartButton(){
        shoppingCartButton.click();
    }

    public void clickOnBurgerButton(){
        burgerButton.click();
    }

    public void clickOnLogoutLink(){
        WebElement logoutLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
        logoutLink.click();
    }

    public void clickOnResetAppStateLink(){
        WebElement resetLink = new WebDriverWait(DriverManager.getDriver().driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));
        resetLink.click();
    }

    public boolean verifyOriginalButtons() {
        for (WebElement button: cartButtons) {
            String buttonText = button.getText();
            if(buttonText.equals("Remove")) {
                return false;
            }
        }

        return true;
    }

    public HashMap<String, Product> getProducts() {
        HashMap<String, Product> products = new HashMap<>();

        for (WebElement productElement : productElements) {
            String productName = productElement.findElement(By.className("inventory_item_name")).getText();
            String productDescription = productElement.findElement(By.className("inventory_item_desc")).getText();
            String priceText = productElement.findElement(By.className("inventory_item_price")).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));

            Product product = new Product(productName, productDescription, price);
            products.put(productName, product);
        }

        return products;
    }

    public boolean isShoppingCartEmpty() {
        List<WebElement> elements = driver.findElements(By.className("shopping_cart_badge"));
        return elements.isEmpty();
    }

    public void clickOnProductLink(String product) {
        WebElement productLink = driver.findElement(By.xpath("//div[@class='inventory_item_name'][text()='" + product + "']/ancestor::a"));
        productLink.click();
    }

    public void clickOnTwitterLink() {
        TwitterLink.click();
    }
}
