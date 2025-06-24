package feature;

import action.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.HomePageUI;
import untils.Hook;

import java.time.Duration;

public class CheckoutTest extends Hook {
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    YourInformationPage yourInformationPage;
    CheckoutPage checkoutPage;
    @BeforeMethod
    public void setUpPage() {

        loginPage = new LoginPage(driver);
        //Dang nhap vao trang web
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickButton();
        // Them san pham vao gio hang
        homePage = new HomePage(driver);
        homePage.getProductAddToCart("Sauce Labs Backpack", "$29.99").findElement(HomePageUI.ADD_TO_CART_BTN).click();
        homePage.getProductAddToCart("Sauce Labs Bike Light", "$9.99").findElement(HomePageUI.ADD_TO_CART_BTN).click();
        homePage.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("https://www.saucedemo.com/cart.html"));
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutBtn();
        yourInformationPage = new YourInformationPage(driver);
        yourInformationPage.enterFirstname("Giang");
        yourInformationPage.enterLastname("Vu");
        yourInformationPage.enterCode("12345");
        yourInformationPage.clickContinueButton();
        checkoutPage = new CheckoutPage(driver);
    }
    @Test
    public void testCheckoutOverview(){
        // Kiem tra title page...
        Assert.assertEquals(checkoutPage.getCheckoutTitle(),"Checkout: Overview","Title mismatch!");
        Assert.assertTrue(checkoutPage.isCartIconDisplay(),"Cart Icon is not displayed");
        Assert.assertEquals(checkoutPage.getBadgeNumber(),2,"Badge number mismatch!");
        Assert.assertEquals(checkoutPage.getPaymentInfoValue(),"SauceCard #31337","Payment Info mismatch!");
        Assert.assertEquals(checkoutPage.getShippingInfoValue(),"Free Pony Express Delivery!","ShippingInfo mismatch!");
        Assert.assertTrue(checkoutPage.isCancelBtnDisplay(),"Cancel button is not displayed");
        Assert.assertTrue(checkoutPage.isFinishBtnDisplay(),"Finish button is not displayed");

        // Kiem tra danh sach san pham
        Assert.assertTrue(checkoutPage.isProductDisplay("Sauce Labs Backpack", "$29.99"),"Product is not displayed");
        Assert.assertTrue(checkoutPage.isProductDisplay("Sauce Labs Bike Light", "$9.99"),"Product is not displayed");

        //Kiem tra tong gia san pham
        Assert.assertTrue(checkoutPage.checkTotalItemPrice(),"Total Price mismatch!");
        //Kiem tra tong tien sau thue
        Assert.assertTrue(checkoutPage.checkTotalPrice(),"Total Price mismatch!");
    }
    @Test
    public void testCancelBtnActive(){
        Assert.assertTrue(checkoutPage.isCancelBtnDisplay(),"Cancel button is not displayed");
        checkoutPage.clickCancelBtn();
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, "https://www.saucedemo.com/inventory.html", "Cancel failed");
    }
    @Test
    public void testFinishBtnActive(){
        Assert.assertTrue(checkoutPage.isFinishBtnDisplay(),"Finish button is not displayed");
        checkoutPage.clickFinishBtn();
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, "https://www.saucedemo.com/checkout-complete.html", "Checkout failed");
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Thank you for your order!']")).isDisplayed(),"Checkout failed");
    }
}
