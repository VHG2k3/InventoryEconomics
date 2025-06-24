package ui;

import org.openqa.selenium.By;

public class CheckoutPageUI {
    public static final By CHECKOUT_TITLE = By.className("title");
    public static final By CART_ICON = By.className("shopping_cart_container");
    public static final By BADGE_NUMBER = By.className("shopping_cart_badge");
    public static final By CART_ITEM = By.className("cart_item");
    public static final By INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    public static final By INVENTORY_ITEM_PRICE = By.className("inventory_item_price");
    public static final By PAYMENT_INFO_VALUE = By.cssSelector(("[data-test='payment-info-value']"));
    public static final By SHIPPING_INFO_VALUE = By.cssSelector(("[data-test='shipping-info-value']"));
    public static final By ITEM_TOTAL = By.className("summary_subtotal_label");
    public static final By TAX = By.className("summary_tax_label");
    public static final By PRICE_TOTAL = By.className("summary_total_label");
    public static final By CANCEL_BTN = By.xpath("//button[contains(text(),'Cancel')]");
    public static final By FINISH_BTN = By.id("finish");
}
