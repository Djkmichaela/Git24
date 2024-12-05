package MichaelSeleniumAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MichaelSeleniumAutomation.pageobjects.CartPage;
import MichaelSeleniumAutomation.pageobjects.Orders;


public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	
	@FindBy(css="[routerlink*='myorder']")
	WebElement orderButtn;
	

	public void waitForElement(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOfElementLocated((findby)));

	}

	public void waitForElementByElement(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOf((findby)));

	}
	
	public void waitForElemenToDisappearByElement(WebElement findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.invisibilityOf((findby)));

	}
	public void waitForElementToDisapear(By findby,String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(findby,text));
	}
	

	public Orders clickOnOrders()
	{
		orderButtn.click();
		Orders ord = new Orders(driver);
		
		return ord;
	}
	
	
}
