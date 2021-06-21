package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import resources.WebWaits;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryDetailPage extends WebWaits {

    By deliveryFilter = By.xpath("//input[@placeholder='Filter Tree entries']");
    By deliverItem = By.xpath("//*[contains(@class,'treeCellInner')]//*[text()='TSHIP Delivery TAT']");
    By returnFileUpload = By.xpath("//*[contains(@class,'techouts_widgets_tshipdeliverytatwidget')]//input[@name]");
    By fileUploadSuccessMsg = By.xpath("//*[contains(@class,'label') and text()='File uploaded successfully']");
    By fileUploadOKButton = By.xpath("//button[text()='OK']");
    By ddOnSignIn = By.xpath("//*[contains(@class,'caption') and text()='Tata BUC']");

    WebDriver driver;

    public DeliveryDetailPage(WebDriver driver) {
        this.driver = driver;
        super.driver = driver;
    }

    public void selectTshipDelivery() {
        waitForElementToBeVisible(ddOnSignIn, 30);
        List<WebElement> filerElements = driver.findElements(deliveryFilter)
                .stream().filter(element -> element.isDisplayed())
                .collect(Collectors.toList());
        filerElements.get(0).sendKeys("TSHIP Delivery TAT");
        getElement(deliverItem).click();
        waitForSomeTime(10);

    }

    public Boolean uploadFileAndReturnResult(String filePath){
        //Handle exception case where user is logged out
        LoginPage loginPage = new LoginPage(driver);
        if(waitForElementToBeVisible(loginPage.loginButton, 1)){
            loginPage.loginAndSelectAuthorityGroup();
            selectTshipDelivery();
        }
        //Acknowledge failure pop up and try next file
        else if(waitForElementToBeVisible(fileUploadOKButton,1)){
            getElement(fileUploadOKButton).click();
        }
        driver.findElement(returnFileUpload).sendKeys(filePath);
        if(waitForElementToBeVisible(fileUploadSuccessMsg, 30)) {
            getElement(fileUploadOKButton).click();
            waitForSomeTime((Integer.parseInt(System.getProperty("waitBetweenUpload")) - 1)*60); //wait between every upload
            return true;
        }
        else{
            waitForSomeTime((Integer.parseInt(System.getProperty("waitBetweenUpload")) - 1)*60); //wait between every upload
            return false;
        }
    }


}
