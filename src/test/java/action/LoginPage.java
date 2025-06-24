package action;

import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    public void enterUsername(String Username){
        driver.findElement(LoginPageUI.USERNAME_FILED).sendKeys(Username);
    }
    public void enterPassword(String Password){
        driver.findElement(LoginPageUI.PASSWORD_FILED).sendKeys(Password);
    }
    public void clickButton(){
        driver.findElement(LoginPageUI.LOGIN_BUTTON).click();
    }
    public String getErrorMessage(){
        return driver.findElement(LoginPageUI.ERROR_MESSAGE).getText();
    }
    public boolean isErrorDisplayed(){return driver.findElement(LoginPageUI.ERROR_MESSAGE).isDisplayed();}
    public boolean isLogoDisplayed() {
        return driver.findElement(LoginPageUI.APP_LOGO).isDisplayed();
    }

    public int getInventoryItemCount() {
        return driver.findElements(LoginPageUI.INVENTORY_ITEM).size();
    }
}
