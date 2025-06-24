package feature;

import action.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import untils.Hook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static untils.ExcelUntil.readExcelData;

public class LoginTest extends Hook {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
    }

    public void Login(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @DataProvider(name = "loginData")
    public Iterator<Object[]> loginData() {
        List<Map<String, String>> data = readExcelData("data.xlsx", "data_login");
        List<Object[]> dataArray = new ArrayList<>();
        for (Map<String, String> row : data) {
            dataArray.add(new Object[]{
                    row.get("Username"),
                    row.get("Password"),
                    row.get("Expected Result"),
                    row.get("Expected Error Message")
            });
        }
        return dataArray.iterator();
    }

    @Test(dataProvider = "loginData")
    public void testLoginFunctionality(String username, String password, String expectedResult, String expectedErrorMsg) {
        // Check title & URL đầu trang
        Assert.assertEquals(driver.getTitle(), "Swag Labs", "Title mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "URL mismatch!");

        // Điền dữ liệu
        Login(username,password);
        loginPage.clickButton();

        // Kiểm tra theo kỳ vọng
        if (expectedResult.equals("success")) {
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(actualURL, "https://www.saucedemo.com/inventory.html", "Login failed when expected to succeed!");

            // Check Logo & Item
            Assert.assertTrue(loginPage.isLogoDisplayed(), "Logo missing on inventory page!");

            Assert.assertTrue(loginPage.getInventoryItemCount() > 0, "No products displayed!");
        } else {
            // Có lỗi, kiểm tra error message.
            Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message not displayed!");
            Assert.assertTrue(loginPage.getErrorMessage().contains(expectedErrorMsg), "Unexpected error message text!");
        }
    }

}
