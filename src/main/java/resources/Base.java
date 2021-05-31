package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

	public WebDriver driver;
	public static Properties prop;
	public static WebDriverWait wait;
	public static ExtentTest test;
	public static ExtentReports extent;


	public WebDriver initializeDriver() {
		initializeExtent();
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + File.separator+"data.properties");
			prop.load(fs);
		}catch (IOException e){
			Assert.fail("Cannot find properties file");
		}
		if(System.getProperty("os.name").startsWith("Mac"))
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"chromedriver");
		else
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ File.separator+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 30);
		return driver;

	}

	public void initializeExtent(){
		String path =System.getProperty("user.dir")+ File.separator +"reports"+File.separator+"report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		extent =new ExtentReports();
		extent.attachReporter(reporter);
	}

}
