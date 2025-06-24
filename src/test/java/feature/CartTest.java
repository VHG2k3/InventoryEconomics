package feature;

import action.CartPage;
import action.HomePage;
import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.CartPageUI;
import ui.HomePageUI;
import untils.Hook;

public class CartTest extends Hook {
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        //Dang nhap vao trang web
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickButton();
        // Them 3 san pham vao gio hang
        homePage = new HomePage(driver);
        homePage.getProductAddToCart("Sauce Labs Backpack", "$29.99").findElement(HomePageUI.ADD_TO_CART_BTN).click();
        homePage.getProductAddToCart("Sauce Labs Bike Light", "$9.99").findElement(HomePageUI.ADD_TO_CART_BTN).click();
        homePage.getProductAddToCart("Sauce Labs Bolt T-Shirt", "$15.99").findElement(HomePageUI.ADD_TO_CART_BTN).click();
        driver.findElement(HomePageUI.CART_ICON).click();
        cartPage = new CartPage(driver);
    }
    @Test
    public void testInforCartPage(){
        // Kiem tra hien thi cac thong tin chung nhu title, cart icon, button Checkout...
        Assert.assertEquals(cartPage.getCartTitle(),"Your Cart","Title mismatch");
        Assert.assertTrue(cartPage.isCartIconDisplay(),"No Cart Icon Displayed");
        Assert.assertTrue(cartPage.isShoppingContinueBtn(),"No Shopping Continue Button Displayed");
        Assert.assertTrue(cartPage.isCheckoutBtn(),"No Checkout Button Displayed");

        // Kiem tra so luong san pham hien thi tren icon gio hang
        Assert.assertEquals(cartPage.getBadgeNumber(),3, "Badge Number mismatch");
        // Kiem tra thong tin danh sach san pham them vao gio
        Assert.assertTrue(cartPage.isProductDisplay("Sauce Labs Backpack", "$29.99"),"No Product displayed");
        Assert.assertTrue(cartPage.isProductDisplay("Sauce Labs Bike Light", "$9.99"),"No Product displayed");
        Assert.assertTrue(cartPage.isProductDisplay("Sauce Labs Bolt T-Shirt", "$15.99"),"No Product displayed");
    }

    @Test
    public  void testRemoveProduct(){
        // Xoa san pham Sauce Labs Backpack trong gio hang
        cartPage.getProductAddToCart("Sauce Labs Backpack", "$29.99").findElement(CartPageUI.REMOVE_BTN).click();
        //Kiem tra ket qua
        Assert.assertEquals(cartPage.getBadgeNumber(),2, "Failed to remove the product from the cart");
        Assert.assertTrue(!cartPage.isProductDisplay("Sauce Labs Backpack", "$29.99"),"Failed to remove the product from the cart");

    }
    @Test
    public void testContinueShoppingBtn(){
        driver.findElement(CartPageUI.SHOPPING_CONTINUE_BTN).click();
        //Kiem tra
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
    }
    @Test
    public void testCheckoutBtn(){
        driver.findElement(CartPageUI.CHECKOUT_BTN).click();
        //Kiem tra
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", "URL mismatch!");
    }

}
