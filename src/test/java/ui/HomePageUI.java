package ui;

import org.openqa.selenium.By;

public class HomePageUI {
    public static final By HOME_TITLE = By.className("app_logo");
    public static final By CART_ICON = By.className("shopping_cart_link");
    public static final By PRODUCT_SORT = By.className("product_sort_container");
    public static final By INVENTORY_ITEM = By.className("inventory_item");
    public static final By INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    public static final By INVENTORY_ITEM_PRICE = By.className("inventory_item_price");
    public static final By ADD_TO_CART_BTN = By.xpath("//button[contains(text(),'Add to cart')]");
    public static final By REMOVE_BTN = By.xpath("//button[contains(text(),'Remove')]");
    public static final By BADGE_NUMBER = By.className("shopping_cart_badge");
    public static final By ACTIVE_OPTION = By.className("active_option");

}
