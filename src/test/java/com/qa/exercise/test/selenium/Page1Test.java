package com.qa.exercise.test.selenium;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentLoggerReporter;


@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Page1Test {
	
	@LocalServerPort
	private int port;
	
	private static ExtentReports report;
	
	private WebDriver driver;
	
	private ExtentTest test;
	
	@Autowired
	private Environment env;
	
	@Value("${extent-reports.location}")
	private static String reportLocation;
	
	@Rule
	public TestWatcher watch = new TestWatcher() {
		protected void fail(Throwable e, Description description) {
			test.fail(e);
			fail(e, description);
		}
		
		protected void success(Description description) {
			test.pass("successful");
			success(description);
		}
	};
	
	static {
		report = new ExtentReports();
		report.attachReporter(new ExtentHtmlReporter(reportLocation + File.separator + "extentReport.html"),
				new ExtentLoggerReporter(reportLocation + File.separator + "extentReport.log"));
	}
	
	@After
	public void teardown() {
		driver.quit();
	}
	
	@AfterClass
	public static void afterClass() {
		report.flush();
	}
	
	@Before
	public void init() {
		ChromeOptions opts = new ChromeOptions();
		opts.setHeadless(true);
		driver = new ChromeDriver(opts);
	}
	
	@Test
	public void testCreate() {
		test = report.createTest("Create Ex Test");
		driver.manage().window().maximize();
		driver.get("http://localhost:" + port);
		Page1 page = PageFactory.initElements(driver, Page1.class);
		
		
		page.createEx("crunches", "an image", "strength", null);
		
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.textToBePresentInElement(page.getAddName(), "name"));
		assertFalse("no output", page.getAddName().getText().isEmpty());
	}

	
}
