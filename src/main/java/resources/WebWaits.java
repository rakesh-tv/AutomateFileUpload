package resources;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;

public class WebWaits extends Base{


    public WebElement getElement(By locator){
        waitForElementToBeVisible(locator);
        return driver.findElement(locator);
    }

    public void waitForElementToBeVisible(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException se) {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }
    }

    public Boolean waitForElementToBeVisible(By locator, long timeInSeconds) {
        Wait waitForElement = new WebDriverWait(driver, timeInSeconds);
        try {
            waitForElement.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException | NoSuchElementException se) {
            return false;
        }
        return true;
    }

    public void waitForSomeTime(int seconds){
        try{
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
        }
    }
}
