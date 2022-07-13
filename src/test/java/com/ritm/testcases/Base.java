package com.ritm.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.ritm.configFiles.ReadPropertyFile;
import com.ritm.wrapper.ExtentManager;
import com.ritm.wrapper.ReadingFillo;
import com.ritm.wrapper.Report;

public class Base {
	public static WebDriver driver;
	public static WebDriverWait wait;
	private static ExtentReports extent;
	protected static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	protected static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	static String baseURL;

	protected static ExtentTest child;
	public static Boolean abortTest = false;
	public static String framework;
	public static String browser;
	public static String Protocol;
	public static String Environment;

	@BeforeSuite
	@Parameters({ "TC_id", "Staging" })
	public void startdriver(String TC_id, String staging) throws InterruptedException, IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		/****
		 * Fetch the browser which configured in XML to pass here as parameter
		 * using TestNG Concept
		 *****/

		browser = ReadPropertyFile.readPropertyFile("browser");
		framework = ReadPropertyFile.readPropertyFile("framework");
		Protocol = ReadPropertyFile.readPropertyFile("Protocol");
		Environment = ReadPropertyFile.readPropertyFile("Environment");

		switch (browser) {
		case "chrome":

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get("chrome://settings/clearBrowserData");
			driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
			break;

		case "IE":

			DesiredCapabilities ANYZOOM4X = DesiredCapabilities.internetExplorer();
			ANYZOOM4X.setBrowserName("internet explorer");
			ANYZOOM4X.setPlatform(Platform.WINDOWS);
			ANYZOOM4X.setCapability("ignoreZoomSetting", true);
			ANYZOOM4X.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();

			break;

		default:
		}

		wait = new WebDriverWait(driver, 10);
		// To maximize browser
		driver.manage().window().maximize();
		// Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		baseURL = Protocol + "://" + Environment + "/" + framework;
		System.out.println("Launched Environment for testing: " + baseURL);
		driver.get(baseURL);
		Pattern pattern = Pattern.compile("("+Environment+")");//(?<=-)([^.]*)
		Matcher matcher = pattern.matcher(Environment);
		matcher.find();
		System.out.println(matcher.matches());
		String s = matcher.group(1);

		extent = ExtentManager.createInstance("extent.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\Reports\\" + s.toUpperCase() + "_" + browser.toUpperCase() + "_"
						+ TC_id + "_" + staging + "_" + dateName + "extent.html");
		extent.attachReporter(htmlReporter);
		ReadingFillo.createWB();
	}

	@BeforeClass
	public synchronized void beforeClass(ITestContext context) throws IOException {
		System.out.println(getClass().getName());
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
		if (Base.getAbortTest()) {
			throw new SkipException("Skipping Test: " + context.getCurrentXmlTest().getName());
		}
	}

	@BeforeMethod
	public synchronized void beforeMethod(Method method, ITestContext context) throws IOException {
		System.out.println(method.getName());
		child = parentTest.get().createNode(method.getName());
		test.set(child);
		if (Base.getAbortTest()) {
			throw new SkipException("Skipping Test: " + context.getCurrentXmlTest().getName());
		}
	}

	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			child.log(Status.FAIL, "Test Case " + "'" + result.getName() + "'" + " Failed,Execution Interrupted");
			String Test = Report.captureScreenShot(driver, result.getName());
			child.log(Status.FAIL, "Screenshot from : " + Test).addScreenCaptureFromPath(Test);
		}

		
		else if (result.getStatus() == ITestResult.SKIP)
			test.get().skip(result.getThrowable());
		else {
			test.get().pass("Execution Completed");
			String Test = Report.captureScreenShot(driver, result.getName());
			child.log(Status.PASS, "Screenshot from : " + Test).addScreenCaptureFromPath(Test);
		}

		extent.flush();
	}
	// Removed Start and End Test
	@BeforeTest
	public synchronized void beforeTest(ITestContext context) throws IOException {
		if (Base.getAbortTest()) {
			throw new SkipException("Skipping Test: " + context.getCurrentXmlTest().getName());
		}
	}

	@AfterTest
	public void TeardownTest(ITestContext context) {
	}

	public static void setAbortTest(Boolean abortTest) {
		Base.abortTest = abortTest;
	}

	public static Boolean getAbortTest() throws IOException {
		return abortTest;
	}

	// Added for Refresh Browser.
	public static void refreshBrowser() {
		switch (browser) {
		case "chrome":
			driver.get("chrome://settings/clearBrowserData");
			driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
			break;

		case "IE":
			DesiredCapabilities ANYZOOM4X = DesiredCapabilities.internetExplorer();
			
			ANYZOOM4X.setBrowserName("internet explorer");
			ANYZOOM4X.setPlatform(Platform.WINDOWS);
			ANYZOOM4X.setCapability("ignoreZoomSetting", true);
			ANYZOOM4X.setCapability("nativeEvents", false);
			ANYZOOM4X.setCapability("unexpectedAlertBehaviour", "accept");
			ANYZOOM4X.setCapability("ignoreProtectedModeSettings", true);
			ANYZOOM4X.setCapability("disable-popup-blocking", true);
			ANYZOOM4X.setCapability("enablePersistentHover", true);
			ANYZOOM4X.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			driver.manage().deleteAllCookies();
			break;
			
			

		default:
		}
		baseURL = Protocol + "://" + Environment + "/" + framework;
		driver.get(baseURL);
	}

}
