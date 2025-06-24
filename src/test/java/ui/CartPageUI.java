package ui;

import org.openqa.selenium.By;

public class CartPageUI {
    public static final By CART_TITLE = By.className("title");
    public static final By CART_ICON = By.className("shopping_cart_container");
    public static final By BADGE_NUMBER = By.className("shopping_cart_badge");
    public static final By CART_ITEM = By.className("cart_item");
    public static final By INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    public static final By INVENTORY_ITEM_PRICE = By.className("inventory_item_price");
    public static final By REMOVE_BTN = By.xpath("//button[contains(text(),'Remove')]");
    public static final By SHOPPING_CONTINUE_BTN = By.id("continue-shopping");
    public static final By CHECKOUT_BTN = By.id("checkout");

}
