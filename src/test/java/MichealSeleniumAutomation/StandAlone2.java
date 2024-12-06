package MichealSeleniumAutomation;

import org.testng.annotations.Test;

import com.google.common.io.Files;

import org.testng.AssertJUnit;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import MichaelSeleniumAutomation.AbstractComponents.AbstractComponents;
import MichaelSeleniumAutomation.TestComponents.BaseTest;
import MichaelSeleniumAutomation.TestComponents.MyRetry;
import MichaelSeleniumAutomation.pageobjects.CartPage;
import MichaelSeleniumAutomation.pageobjects.LandingPage;
import MichaelSeleniumAutomation.pageobjects.Orders;
import MichaelSeleniumAutomation.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone2 extends BaseTest {
	


	//String productName = "IPHONE 13 PRO";
	@Test(dataProvider="getData",groups={"Smoke"},retryAnalyzer=MyRetry.class)
	public void SubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub



		
  
		ProductCatalogue abs = page.LoginApplication(input.get("email"), input.get("password"));
		abs.waitForToast();
		System.out.println(abs.toastText());

		AssertJUnit.assertEquals(abs.toastText(), "Login Successfully");
		abs.getItemsList();

		abs.addItemToAcart(input.get("product"));

		abs.waitForToastToDisappear("Login Successfully");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//div[@id='toast-container']"),
				"Login Successfully"));

		abs.waitForToast();
		System.out.println(driver.findElement(By.xpath("//div[@id='toast-container']")).getText());
		abs.waitForToast();
		//AssertJUnit.assertEquals(abs.toastText(), "Product Added To Cart");

		CartPage cc = abs.viewCart();

		cc.ItemInCart(input.get("product"));

		AssertJUnit.assertTrue(cc.ItemInCart(input.get("product")));

		cc.checkOut();
		cc.selectCountry("ind");

		cc.placeOrder();
		
		//abs.waitForToastToDisappear("Product Added To Cart");
		abs.waitForToast();
		//AssertJUnit.assertEquals(abs.toastText(), "Order Placed Successfully");
		abs.waitForElemenToDisappearByElement(driver.findElement(By.cssSelector(".ng-animating")));
		
		
		 Orders orddr = new Orders(driver);
		 orddr.clickOnOrders();
			Assert.assertTrue(orddr.verifyOrder(input.get("product")));
			abs.waitForToastToDisappear("Order Placed Successfully");
			driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[4]")).click();
			
			System.out.println(abs.toastText()+" we in here toast");
			
			//Assert.assertEquals(abs.toastText(), "Logout Successfully");
			abs.waitForToastToDisappear("Logout Successfully");
			orddr.waitForElementByElement(driver.findElement(By.cssSelector("[id='userEmail']")));

	}
	
	@Test(dependsOnMethods="SubmitOrder",dataProvider="getData",enabled=false)
	public void checkOrders(HashMap<String,String> input) throws InterruptedException
	{ ProductCatalogue ted = new ProductCatalogue(driver);
		 Orders orddr = new Orders(driver);
		orddr.clickOnOrders();
		Assert.assertTrue(orddr.verifyOrder(input.get("product")));
		ted.waitForToastToDisappear("Order Placed Successfully");
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[4]")).click();
		
		System.out.println(ted.toastText()+" we in here toast");
		
		//Assert.assertEquals(ted.toastText(), "Logout Successfully");
		ted.waitForToastToDisappear("Logout Successfully");
		orddr.waitForElementByElement(driver.findElement(By.cssSelector("[id='userEmail']")));
		
	
		//ProductCatalogue abs = page.LoginApplication(input.get("email"), input.get("password")); testing

		
	}
	
	

	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String, String>> data =	getJsonDatToMap(System.getProperty("user.dir")+"//src//test//java//MichaelSeleniumAutomation//data//PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};	
		
	}	
//	@DataProvider 
//	public Object[][] getData() {
//		return new Object[][]  {{"mikekulio@gmail.com","Grouth#44","ADIDAS ORIGINAL"},{"dmichaeldjamba@gmail.com","Grouth#44","IPHONE 13 PRO"}};
//		
//	}

	
//	HashMap<String,String> map = new  HashMap<String,String>();
//	map.put("email", "dmichaeldjamba@gmail.com");
//	map.put("password", "Grouth#44");
//	map.put("product", "IPHONE 13 PRO");
//	
//	HashMap<String,String> map1 = new  HashMap<String,String>();
//	map1.put("email", "mikekulio@gmail.com");
//	map1.put("password", "Grouth#44");
//	map1.put("product", "ADIDAS ORIGINAL");
	//return new Object[][] {{map},{map1}};

}
