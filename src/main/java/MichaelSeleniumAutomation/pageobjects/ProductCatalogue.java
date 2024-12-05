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

public class ProductCatalogue extends AbstractComponents {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	By toast = By.xpath("//div[@id='toast-container']");
	By cart = By.cssSelector(".card-body button:last-of-type");
	
	@FindBy(css=".mb-3")
	List<WebElement> items;
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartview;
	
	@FindBy(css=".ng-animating")
	WebElement loadingElement;
	
	By itemss = By.cssSelector(".mb-3");
	public List<WebElement> getItemsList() {
		
		waitForElement(itemss);
		return items;
	}

	public String toastText() {

		String toastText = driver.findElement(toast).getText();
		return toastText;

	}
	public void waitForToastToDisappear(String text) {
		
	waitForElementToDisapear(toast,text);
	}
	
	public void waitForToast() {
		waitForElement(toast);
	}

	public void addItemToAcart(String product) {
	List<WebElement> items	=getItemsList();
		WebElement item =items.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(product)).findFirst().orElse(null);
		
		item.findElement(cart).click();

	}
	

	
	public CartPage viewCart() {
		
		//waitForElemenToDisappearByElement(loadingElement);
		cartview.click();
		
		CartPage cc = new CartPage(driver);
		return cc;
	}
	
	
}
