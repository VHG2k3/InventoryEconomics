package ui;

import org.openqa.selenium.By;

public class YourInformationPageUI {
    public static final By PAGE_TITLE = By.className("title");
    public static final By CART_ICON = By.className("shopping_cart_container");
    public static final By BADGE_NUMBER = By.className("shopping_cart_badge");
    public static final By FIRSTNAME_FILED = By.id("first-name");
    public static final By LASTNAME_FILED = By.id("last-name");
    public static final By CODE_FILED = By.id("postal-code");
    public static final By ERROR_MESSAGE = By.cssSelector(("[data-test='error']"));
    public static final By CANCEL_BTN = By.id("cancel");
    public static final By CONTINUE_BTN = By.id("continue");
}
