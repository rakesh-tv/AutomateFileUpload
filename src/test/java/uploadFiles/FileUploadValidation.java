package uploadFiles;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeliveryDetailPage;
import pages.LoginPage;
import resources.Base;
import resources.Util;
import resources.WebWaits;

import java.io.File;
import java.util.ArrayList;

public class FileUploadValidation extends Base {
    public WebDriver driver;


    @BeforeSuite
    public void initializeAndNavigateToFileUploadPage() {
        driver = initializeDriver();
        new LoginPage(driver)
                .loginAndSelectAuthorityGroup()
                .selectTshipDelivery();
    }

    @Test(dataProvider = "getFilesToUpload")
    public void uploadFileAndValidate( String file) {

        test = extent.createTest("uploadFileAndValidate");

        if (new File(file).getAbsoluteFile().length() > 5242880) {
            test.log(Status.FAIL, "File size too big. File name : " + file);
            Assert.fail();}
        else if(!Util.checkReturnFileName(file)){
            test.log(Status.FAIL, "Incorrect file name. Return File Name Should Starts With ReturnLogisticsServiceability.\n File name : " + file);
            Assert.fail();}
        else if (new DeliveryDetailPage(driver).uploadFileAndReturnResult(file))
            test.log(Status.PASS, "File name : " + file);
        else{
            test.log(Status.FAIL, "Upload Failed. File name : " + file);
            Assert.fail();}

    }

    @AfterClass
    public void tearDown(){
        extent.flush();
        driver.quit();
    }

    @DataProvider(name = "getFilesToUpload")
    public Object[] getFilesToUpload(){
        ArrayList<String> filePaths = new Util().getFilesToUpload();
        Object[] files = new Object[filePaths.size()];
        for (int i = 0; i < filePaths.size(); i++){
            files[i] = filePaths.get(i);
        }
        return files;
    }


}
