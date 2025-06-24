package action;

import org.openqa.selenium.WebDriver;
import ui.CartPageUI;
import ui.LoginPageUI;
import ui.YourInformationPageUI;

public class YourInformationPage {
    WebDriver driver;
    public YourInformationPage(WebDriver driver){this.driver = driver;}
    public String getPageTitle(){
        return driver.findElement(YourInformationPageUI.PAGE_TITLE).getText();
    }
    public boolean isCartIconDisplay(){
        return driver.findElement(YourInformationPageUI.CART_ICON).isDisplayed();
    }
    public int getBadgeNumber(){return Integer.parseInt(driver.findElement(YourInformationPageUI.BADGE_NUMBER).getText());}

    public void enterFirstname(String firstName){
        driver.findElement(YourInformationPageUI.FIRSTNAME_FILED).sendKeys(firstName);
    }
    public void enterLastname(String lastName){
        driver.findElement(YourInformationPageUI.LASTNAME_FILED).sendKeys(lastName);
    }
    public void enterCode(String code){
        driver.findElement(YourInformationPageUI.CODE_FILED).sendKeys(code);
    }
    public boolean isErrorDisplayed(){
        return driver.findElement(YourInformationPageUI.ERROR_MESSAGE).isDisplayed();
    }
    public String getErrorMessage(){
        return driver.findElement(YourInformationPageUI.ERROR_MESSAGE).getText();
    }
    public boolean isCancelButtonDisplay(){
        return driver.findElement(YourInformationPageUI.CANCEL_BTN).isDisplayed();
    }
    public boolean isContinueButtonDisplay(){
        return driver.findElement(YourInformationPageUI.CONTINUE_BTN).isDisplayed();
    }
    public void clickContinueButton(){
        driver.findElement(YourInformationPageUI.CONTINUE_BTN).click();
    }
    public void clickCancelButton(){
        driver.findElement(YourInformationPageUI.CANCEL_BTN).click();
    }
}
