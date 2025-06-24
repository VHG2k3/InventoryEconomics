package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.CartPageUI;
import ui.CheckoutPageUI;

import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    public CheckoutPage(WebDriver driver){this.driver = driver;}
    public String getCheckoutTitle(){
        return driver.findElement(CheckoutPageUI.CHECKOUT_TITLE).getText();
    }
    public boolean isCartIconDisplay(){
        return driver.findElement(CheckoutPageUI.CART_ICON).isDisplayed();
    }
    public int getBadgeNumber(){return Integer.parseInt(driver.findElement(CheckoutPageUI.BADGE_NUMBER).getText());}

    public String getPaymentInfoValue(){
        return driver.findElement(CheckoutPageUI.PAYMENT_INFO_VALUE).getText();
    }
    public String getShippingInfoValue(){
        return driver.findElement(CheckoutPageUI.SHIPPING_INFO_VALUE).getText();
    }
    public boolean isCancelBtnDisplay(){
        return driver.findElement(CheckoutPageUI.CANCEL_BTN).isDisplayed();
    }
    public boolean isFinishBtnDisplay(){
        return driver.findElement(CheckoutPageUI.FINISH_BTN).isDisplayed();
    }
    public void clickCancelBtn(){
        driver.findElement(CheckoutPageUI.CANCEL_BTN).click();
    }
    public void clickFinishBtn(){
        driver.findElement(CheckoutPageUI.FINISH_BTN).click();
    }
    public boolean isProductDisplay(String productName, String productPrice){
        List<WebElement> cartItems = driver.findElements(CartPageUI.CART_ITEM);
        boolean temp = false;
        for(WebElement cartItem : cartItems){
            if(cartItem.findElement(CartPageUI.INVENTORY_ITEM_NAME).getText().equals(productName) &&
                    cartItem.findElement(CartPageUI.INVENTORY_ITEM_PRICE).getText().equals(productPrice)){
                temp =  true;
                break;
            }
        }
        return temp;
    }
    public Double TotalItemPrice(){
        List<WebElement> cartItems = driver.findElements(CartPageUI.CART_ITEM);
        double sum = 0.0;
        for(WebElement cartItem : cartItems){
            sum += Double.parseDouble(cartItem.findElement(CheckoutPageUI.INVENTORY_ITEM_PRICE).getText().replace("$", "").trim());
        }
        return sum;
    }
    public boolean checkTotalItemPrice(){
        Double sum = TotalItemPrice();
        if(sum == Double.parseDouble(driver.findElement(CheckoutPageUI.ITEM_TOTAL).getText().replace("Item total: $","").trim())){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkTotalPrice(){
        Double totalPrice = TotalItemPrice() + Double.parseDouble(driver.findElement(CheckoutPageUI.TAX).getText().replace("Tax: $", "").trim());
        Double totalDisplay =  Double.parseDouble(driver.findElement(CheckoutPageUI.PRICE_TOTAL).getText().replace("Total: $", "").trim());
        if(totalPrice.equals(totalDisplay)){
            return true;
        }
        else {
            return false;
        }
    }

}
