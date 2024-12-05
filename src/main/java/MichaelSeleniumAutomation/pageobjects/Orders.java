package MichaelSeleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MichaelSeleniumAutomation.AbstractComponents.AbstractComponents;


public class Orders extends AbstractComponents {
	
	WebDriver driver;
	public Orders(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tbody/tr/td[2]")
	List<WebElement> OrderList;
	
	
	
	public boolean verifyOrder(String item) {
		
	boolean truth =	OrderList.stream().anyMatch(o->o.getText().equalsIgnoreCase(item));
	 return truth;
	}
	
}
