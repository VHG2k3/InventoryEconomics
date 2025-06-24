package action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ui.HomePageUI;
import ui.LoginPageUI;
import untils.ExcelUntil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){this.driver = driver;}
    public boolean isHomeTitleDisplay(){
        return driver.findElement(HomePageUI.HOME_TITLE).isDisplayed();
    }
    public boolean isCartIconDisplay(){
        return driver.findElement(HomePageUI.CART_ICON).isDisplayed();
    }
    public void selectOption(String optionName){
        Select select = new Select( driver.findElement(HomePageUI.PRODUCT_SORT));
        select.selectByVisibleText(optionName);
    }
    public boolean isSortNameAZ(){
        List<WebElement> elements = driver.findElements(HomePageUI.INVENTORY_ITEM_NAME);
        List<String> listNameProduct = new ArrayList<>();
        for (WebElement element : elements) {
            listNameProduct.add(element.getText());
        }
        List<String> sortedList = new ArrayList<>(listNameProduct);
        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
        // So sánh với danh sách ban đầu
        return listNameProduct.equals(sortedList);
    }
    public boolean isSortNameZA(){
        List<WebElement> elements = driver.findElements(HomePageUI.INVENTORY_ITEM_NAME);
        List<String> listNameProduct = new ArrayList<>();
        for (WebElement element : elements) {
            listNameProduct.add(element.getText());
        }
        List<String> sortedList = new ArrayList<>(listNameProduct);
        sortedList.sort(String.CASE_INSENSITIVE_ORDER.reversed());
        // So sánh với danh sách ban đầu
        return listNameProduct.equals(sortedList);
    }
    public boolean isPriceSortedHighToLow() {
        List<WebElement> priceElements = driver.findElements(HomePageUI.INVENTORY_ITEM_PRICE);

        List<Double> priceList = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            priceList.add(Double.parseDouble(priceText));
        }
        List<Double> sortedList = new ArrayList<>(priceList);
        sortedList.sort(Collections.reverseOrder());
        // So sánh danh sách ban đầu với danh sách đã sắp xếp
        return priceList.equals(sortedList);
    }

    public boolean isPriceSortedLowToHigh() {
        List<WebElement> priceElements = driver.findElements(HomePageUI.INVENTORY_ITEM_PRICE);

        List<Double> priceList = new ArrayList<>();
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "").trim();
            priceList.add(Double.parseDouble(priceText));
        }

        List<Double> sortedList = new ArrayList<>(priceList);
        Collections.sort(sortedList);
        return priceList.equals(sortedList);
    }
    public int getAddToCartBtnCount() {
        return driver.findElements(HomePageUI.ADD_TO_CART_BTN).size();
    }
    public boolean isProductDisplay(String productName, String productPrice){
        List<WebElement> inventoryItems = driver.findElements(HomePageUI.INVENTORY_ITEM);
        boolean match = false;
        for(WebElement inventoryItem : inventoryItems){
            if(inventoryItem.findElement(HomePageUI.INVENTORY_ITEM_NAME).getText().equals(productName) &&
                inventoryItem.findElement(HomePageUI.INVENTORY_ITEM_PRICE).getText().equals(productPrice)){
                match= true;
            }
        }
        return match;
    }
    public WebElement getProductAddToCart(String productName, String productPrice){
        List<WebElement> inventoryItems = driver.findElements(HomePageUI.INVENTORY_ITEM);
        WebElement temp = null;
        for(WebElement inventoryItem : inventoryItems){
            if(inventoryItem.findElement(HomePageUI.INVENTORY_ITEM_NAME).getText().equals(productName) &&
                    inventoryItem.findElement(HomePageUI.INVENTORY_ITEM_PRICE).getText().equals(productPrice)){
                temp =  inventoryItem;
                break;
            }
        }
        return temp;
    }
    public void clickCartIcon(){
        driver.findElement(HomePageUI.CART_ICON).click();
    }
}
