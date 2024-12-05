package MichaelSeleniumAutomation.pageobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MichaelSeleniumAutomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> itemsInCart;
	
	@FindBy(css=".ta-results")
	List<WebElement> countries;
	
	@FindBy(css="li[class='totalRow'] button")
	WebElement checkoutBut;
	
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement findCountry;
	
	@FindBy(xpath="//button[@class='ta-item list-group-item ng-star-inserted'][2]")
	WebElement selectedCountry;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeTheOrder;
	
	public boolean ItemInCart(String checkItem) {
		
		
		// checking for a string element in a list
		boolean itemsFound =itemsInCart.stream().anyMatch(c->c.getText().equalsIgnoreCase(checkItem));
		
		return itemsFound;
	}
	
	
	public void checkOut() {
		
		checkoutBut.click();
	}
	
	
	public void selectCountry(String text) {
		
		findCountry.sendKeys(text);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(countries));

		selectedCountry.click();
	}
	
	
	public void placeOrder() {
		
		placeTheOrder.click();
		
	}
	
}
