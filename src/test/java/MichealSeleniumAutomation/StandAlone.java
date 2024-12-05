package MichealSeleniumAutomation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//		System.setProperty("webdriver.chrome.driver", "/Users/michaeldjamba/Downloads/chromedriver");
//
//        WebDriver driver = new ChromeDriver();

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.cssSelector("[id='userEmail']")).sendKeys("mikekulio@gmail.com");
		driver.findElement(By.cssSelector("[id='userPassword']")).sendKeys("Grouth#44");
		driver.findElement(By.cssSelector("[id='login']")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[@id='toast-container']")).getText());
	
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='toast-container']")).getText(), "Login Successfully");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    List<WebElement> items =	driver.findElements(By.cssSelector(".mb-3"));
	
	WebElement item =items.stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);

	item.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//div[@id='toast-container']"),"Login Successfully"));
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//div[@id='toast-container']"))));
	System.out.println(driver.findElement(By.xpath("//div[@id='toast-container']")).getText());
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id='toast-container']")).getText(), "Product Added To Cart");
	
	
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	
List<WebElement> cart =	driver.findElements(By.cssSelector(".cartSection h3"));
// checking for a string element in a list
boolean re =cart.stream().anyMatch(c->c.getText().equalsIgnoreCase("IPHONE 13 PRO"));
Assert.assertTrue(re);

driver.findElement(By.cssSelector("li[class='totalRow'] button")).click();

driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");

//List<WebElement> countries =  driver.findElements(By.cssSelector("[class*='list-group '] button span i"));.ta-results
List<WebElement> countries =  driver.findElements(By.cssSelector(".ta-results"));
wait.until(ExpectedConditions.visibilityOfAllElements(countries));

driver.findElement(By.xpath("//button[@class='ta-item list-group-item ng-star-inserted'][2]")).click();

driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();

//Assert.assertEquals(driver.findElement(By.cssSelector(".hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
//countries.stream().filter(c->c.getText().equalsIgnoreCase("india")).forEach(c->System.out.println(c));
//
//
//for(WebElement con :countries) {
//
//System.out.println(	con.getText());
//		
	
	
	
//}
	}

}
