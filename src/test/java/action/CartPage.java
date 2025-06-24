package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.CartPageUI;
import ui.HomePageUI;

import java.util.List;

public class CartPage {

    WebDriver driver;
    public CartPage(WebDriver driver){this.driver = driver;}
    public String getCartTitle(){
        return driver.findElement(CartPageUI.CART_TITLE).getText();
    }
    public boolean isCartIconDisplay(){
        return driver.findElement(CartPageUI.CART_ICON).isDisplayed();
    }
    public int getBadgeNumber(){return Integer.parseInt(driver.findElement(CartPageUI.BADGE_NUMBER).getText());}

    public boolean isShoppingContinueBtn(){
        return driver.findElement(CartPageUI.SHOPPING_CONTINUE_BTN).isDisplayed();
    }
    public boolean isCheckoutBtn(){
        return driver.findElement(CartPageUI.CHECKOUT_BTN).isDisplayed();
    }
    public WebElement getProductAddToCart(String productName, String productPrice){
        List<WebElement> cartItems = driver.findElements(CartPageUI.CART_ITEM);
        WebElement temp = null;
        for(WebElement cartItem : cartItems){
            if(cartItem.findElement(CartPageUI.INVENTORY_ITEM_NAME).getText().equals(productName) &&
                    cartItem.findElement(CartPageUI.INVENTORY_ITEM_PRICE).getText().equals(productPrice) &&
                        cartItem.findElement(CartPageUI.REMOVE_BTN).isDisplayed()){
                temp =  cartItem;
                break;
            }
        }
        return temp;
    }
    public boolean isProductDisplay(String productName, String productPrice){
        List<WebElement> cartItems = driver.findElements(CartPageUI.CART_ITEM);
        boolean temp = false;
        for(WebElement cartItem : cartItems){
            if(cartItem.findElement(CartPageUI.INVENTORY_ITEM_NAME).getText().equals(productName) &&
                    cartItem.findElement(CartPageUI.INVENTORY_ITEM_PRICE).getText().equals(productPrice) &&
                    cartItem.findElement(CartPageUI.REMOVE_BTN).isDisplayed()){
                temp =  true;
                break;
            }
        }
        return temp;
    }
}
