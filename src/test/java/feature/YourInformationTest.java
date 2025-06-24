package feature;

import action.CartPage;
import action.HomePage;
import action.LoginPage;
import action.YourInformationPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.CartPageUI;
import ui.HomePageUI;
import ui.YourInformationPageUI;
import untils.Hook;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static untils.ExcelUntil.readExcelData;

public class YourInformationTest extends Hook {
    LoginPage loginPage;
    HomePage homePage;
    CartPage cartPage;
    YourInformationPage yourInformationPage;
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
        homePage.clickCartIcon();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("https://www.saucedemo.com/cart.html"));
        cartPage = new CartPage(driver);
        cartPage.clickCheckoutBtn();
        yourInformationPage = new YourInformationPage(driver);
    }
    @DataProvider(name = "yourInformationData")
    public Iterator<Object[]> yourInformationData() {
        List<Map<String, String>> data = readExcelData("data.xlsx", "your_information");
        List<Object[]> dataArray = new ArrayList<>();
        for (Map<String, String> row : data) {
            dataArray.add(new Object[]{
                    row.get("Firstname"),
                    row.get("LastName"),
                    row.get("Code"),
                    row.get("Expected Result"),
                    row.get("Expected Error Message")
            });
        }
        return dataArray.iterator();
    }

    @Test(dataProvider = "yourInformationData")
    public void testInformationFiledFunctionality(String firstname, String lastname,String code, String expectedResult, String expectedErrorMsg) {
        // Check title
        Assert.assertEquals(yourInformationPage.getPageTitle(), "Checkout: Your Information", "Title mismatch!");
        Assert.assertTrue(yourInformationPage.isCartIconDisplay(), "Cart Icon is not displayed");
        Assert.assertEquals(yourInformationPage.getBadgeNumber(),1, "Badge number mismatch!");
        Assert.assertTrue(yourInformationPage.isCancelButtonDisplay(), "Cancel button is not displayed");
        Assert.assertTrue(yourInformationPage.isContinueButtonDisplay(), "Continue button is not displayed");

        // Điền dữ liệu
        yourInformationPage.enterFirstname(firstname);
        yourInformationPage.enterLastname(lastname);
        yourInformationPage.enterCode(code);
        yourInformationPage.clickContinueButton();

        // Kiểm tra theo kỳ vọng
        if (expectedResult.equals("success")) {
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(actualURL, "https://www.saucedemo.com/checkout-step-two.html", "Invalid input!");

        } else {
            // Có lỗi, kiểm tra error message.
            Assert.assertTrue(yourInformationPage.isErrorDisplayed(), "Error message not displayed!");
            Assert.assertTrue(yourInformationPage.getErrorMessage().contains(expectedErrorMsg), "Unexpected error message text!");
        }
    }
}
