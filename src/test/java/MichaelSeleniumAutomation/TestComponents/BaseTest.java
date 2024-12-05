package MichaelSeleniumAutomation.TestComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import MichaelSeleniumAutomation.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage page;

	public void initializeDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//MichaelSeleniumAutomation//resources//GlobalData.properties");
		prop.load(fis);
		// checks if you sent a browser property from maven if not it will get it form the file in GlobalData
	String browserName =	System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser"); 
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
				
			}
			
			driver =new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));

			

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// code here for firefox
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}


		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	// lesson 174
	public List<HashMap<String, String>> getJsonDatToMap(String filePath) throws IOException {

		// Read Json to string
		String jsonContentInString = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap you need a dependency called Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContentInString,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	// taking a screenshot

	public String takeScreenShot(String testCaseName, WebDriver driver) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png"));
		return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
	}

	@BeforeTest(alwaysRun = true)
	public LandingPage launchApplication() throws IOException  {

		initializeDriver();
System.out.println("for the commt");
		page = new LandingPage(driver);
		page.goTo();

	return page;

	}

	@AfterTest(alwaysRun = true)
	public void closeTests() {
		driver.close();
	}

}
