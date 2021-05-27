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

    WebDriver driver;

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

    public Boolean uploadFileAndReturnResult(String filePath){
        Boolean uploadSuccess = false;
        waitForSomeTime(5);
        driver.findElement(returnFileUpload).sendKeys(filePath);
        waitForSomeTime(30);
        uploadSuccess = getElement(fileUploadSuccessMsg).isDisplayed();
        if(driver.findElements(fileUploadSuccessOKButton).size() > 0) getElement(fileUploadSuccessOKButton).click();
        return uploadSuccess;
    }


}
