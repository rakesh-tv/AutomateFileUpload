package uploadFiles;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DeliveryDetailPage;
import pages.LoginPage;
import resources.Base;
import resources.Util;

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
    public void uploadFileAndValidate( String file){
            if(new File(file).getAbsoluteFile().length() > 5242880)
                Assert.fail(String.format("Size of File : %s is greater than 5mb",file));
            else
                Assert.assertTrue(new DeliveryDetailPage(driver).uploadFileAndReturnResult(file),
                        "Upload failed. File - "+file);
    }

    @AfterClass
    public void tearDown(){
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
