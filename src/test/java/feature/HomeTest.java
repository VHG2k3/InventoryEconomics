package feature;

import action.HomePage;
import action.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.HomePageUI;
import untils.Hook;

public class HomeTest extends Hook {
    HomePage homePage;
    LoginPage loginPage;
    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        //Dang nhap vao trang web
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickButton();
        homePage = new HomePage(driver);
    }
    @Test
    public void testSortNameAZ(){
        Assert.assertTrue(homePage.isHomeTitleDisplay(),"No Home Title displayed!");
        Assert.assertTrue(homePage.isCartIconDisplay(),"No Cart Icon displayed!");

        //Chon sap xep theo Name A-Z
        homePage.selectOption("Name (A to Z)");
        //Kiem tra thong tin
        Assert.assertTrue(homePage.isSortNameAZ(),"The list is not sorted in A-Z order by name");
        Assert.assertEquals(homePage.getAddToCartBtnCount(), 6, "Add to Cart' button is missing");
    }
    @Test
    public void testSortNameZA(){
        Assert.assertTrue(homePage.isHomeTitleDisplay(),"No Home Title displayed!");
        Assert.assertTrue(homePage.isCartIconDisplay(),"No Cart Icon displayed!");

        //Chon sap xep theo Name A-Z
        homePage.selectOption("Name (Z to A)");
        //Kiem tra thong tin
        Assert.assertTrue(homePage.isSortNameZA(),"The list is not sorted in Z-A order by name");
        Assert.assertEquals(homePage.getAddToCartBtnCount(), 6, "Add to Cart' button is missing");
    }
    @Test
    public void testSortPriceLowHigh(){
        Assert.assertTrue(homePage.isHomeTitleDisplay(),"No Home Title displayed!");
        Assert.assertTrue(homePage.isCartIconDisplay(),"No Cart Icon displayed!");

        //Chon sap xep theo Name A-Z
        homePage.selectOption("Price (low to high)");
        //Kiem tra thong tin
        Assert.assertTrue(homePage.isPriceSortedLowToHigh(),"The list is not sorted from low to high price");
        Assert.assertEquals(homePage.getAddToCartBtnCount(), 6, "Add to Cart' button is missing");
    }
    @Test
    public void testSortPriceHighLow(){
        Assert.assertTrue(homePage.isHomeTitleDisplay(),"No Home Title displayed!");
        Assert.assertTrue(homePage.isCartIconDisplay(),"No Cart Icon displayed!");

        //Chon sap xep theo Name A-Z
        homePage.selectOption("Price (high to low)");
        //Kiem tra thong tin
        Assert.assertTrue(homePage.isPriceSortedHighToLow(),"The list is not sorted from high to low price");
        Assert.assertEquals(homePage.getAddToCartBtnCount(), 6, "Add to Cart' button is missing");
    }

    @Test
    public void addToCart(){
        Assert.assertTrue(homePage.isHomeTitleDisplay(),"No Home Title displayed!");
        Assert.assertTrue(homePage.isCartIconDisplay(),"No Cart Icon displayed!");

        //Them san pham dau tien vao gio
        Assert.assertTrue(homePage.isProductDisplay("Sauce Labs Backpack","$29.99"),"No Product Sauce Labs Backpack Displayed");
        WebElement product1 = homePage.getProductAddToCart("Sauce Labs Backpack","$29.99");
        product1.findElement(HomePageUI.ADD_TO_CART_BTN).click();
        Assert.assertTrue(product1.findElement(HomePageUI.REMOVE_BTN).isDisplayed(),"Failed to add the product to the cart");
        Assert.assertEquals(driver.findElement(HomePageUI.BADGE_NUMBER).getText(),"1","Failed to add the product to the cart");
        //Them san pham thu 2 vào gio
        Assert.assertTrue(homePage.isProductDisplay("Sauce Labs Bike Light","$9.99"),"No Product Sauce Labs Backpack Displayed");
        WebElement product2 = homePage.getProductAddToCart("Sauce Labs Bike Light","$9.99");
        product2.findElement(HomePageUI.ADD_TO_CART_BTN).click();
        Assert.assertTrue(product2.findElement(HomePageUI.REMOVE_BTN).isDisplayed(),"Failed to add the product to the cart");
        Assert.assertEquals(driver.findElement(HomePageUI.BADGE_NUMBER).getText(),"2","Failed to add the product to the cart");
    }
    @Test
    public void removeCart(){
        Assert.assertTrue(homePage.isHomeTitleDisplay(),"No Home Title displayed!");
        Assert.assertTrue(homePage.isCartIconDisplay(),"No Cart Icon displayed!");

        //Them san pham vao gio
        WebElement product1 = homePage.getProductAddToCart("Sauce Labs Backpack","$29.99");
        product1.findElement(HomePageUI.ADD_TO_CART_BTN).click();
        //Xoa san pham khoi gio hang
        Assert.assertTrue(product1.findElement(HomePageUI.REMOVE_BTN).isDisplayed(),"The product has not been added to the cart.");
        product1.findElement(HomePageUI.REMOVE_BTN).click();
        Assert.assertTrue(driver.findElements(HomePageUI.BADGE_NUMBER).isEmpty(), "Failed to remove the product from the cart");
    }
}
