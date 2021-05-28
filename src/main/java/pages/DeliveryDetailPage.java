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
    By fileUploadSuccessOKButton = By.xpath("//button[text()='OK']");
    By uploadFileErrorMessage = By.xpath("//*[contains(@class,'error_class') and contains(text(),'Unable to Upload File Please try again')]");
    By uploadFileIncorrectNameErrorMessage = By.xpath("//*[contains(@class,'error_class') and contains(text(),'Return File Name Should Starts With ReturnLogisticsServiceability')]");

    WebDriver driver;

    public enum UploadStatus{
        SUCCESSS,
        INCORRECT_FILE_NAME,
        OTHER_ERROR
    }

    public DeliveryDetailPage(WebDriver driver) {
        this.driver = driver;
        super.driver = driver;
    }

    public void selectTshipDelivery() {
        waitForSomeTime(45);
        List<WebElement> filerElements = driver.findElements(deliveryFilter)
                                        .stream().filter(element -> element.isDisplayed())
                                        .collect(Collectors.toList());
        filerElements.get(0).sendKeys("TSHIP Delivery TAT");
        getElement(deliverItem).click();
    }

    public UploadStatus uploadFileAndReturnResult(String filePath){
        waitForSomeTime(5);
        driver.findElement(returnFileUpload).sendKeys(filePath);
        if(waitForElementToBeVisible(fileUploadSuccessMsg, 30)) {
            getElement(fileUploadSuccessOKButton).click();
            return UploadStatus.SUCCESSS;
        }
        else if(waitForElementToBeVisible(uploadFileIncorrectNameErrorMessage, 30))
            return UploadStatus.INCORRECT_FILE_NAME;
        else
            return UploadStatus.OTHER_ERROR;

    }


}
