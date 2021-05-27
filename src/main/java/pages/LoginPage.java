package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import resources.Base;
import resources.WebWaits;

public class LoginPage extends WebWaits {


    By userName = By.name("j_username");
    By password = By.name("j_password");
    By loginButton = By.cssSelector(".login_btn");
    By tataBUCRole = By.xpath("//*[contains(text(),'Tata BUC Admin Role')]");
    By proceedButton = By.xpath("//button[text()='PROCEED']");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        super.driver = driver;
    }

    public DeliveryDetailPage loginAndSelectAuthorityGroup(){
        driver.get(Base.prop.get("url").toString());
        getElement(userName).sendKeys(Base.prop.get("username").toString());
        getElement(password).sendKeys(Base.prop.get("password").toString());
        getElement(loginButton).click();
        getElement(tataBUCRole).click();
        getElement(proceedButton).click();
        return new DeliveryDetailPage(driver);
    }
}
