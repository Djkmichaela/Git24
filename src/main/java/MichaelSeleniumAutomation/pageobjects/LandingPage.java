package MichaelSeleniumAutomation.pageobjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MichaelSeleniumAutomation.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // this line initializes the driver on object below

	}

	@FindBy(css = "[id='userEmail']") // this gets assigned to the next or following webelemnt
	WebElement userMail;

	@FindBy(css = "[id='userPassword']")
	WebElement passwordEle;

	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	@FindBy(css = "[id='login']")
	WebElement submit;

	By errorToast = By.cssSelector("[class*='flyInOut']");

	public ProductCatalogue LoginApplication(String email, String passwords) {
	
		userMail.sendKeys(email);
		passwordEle.sendKeys(passwords);
		submit.click();
		ProductCatalogue abs = new ProductCatalogue(driver);
		return abs;
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");
	}

	public String failedLogin() {
		waitForElement(errorToast);
		String message = errorMessage.getText();
		return message;
	}

}
