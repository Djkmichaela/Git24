package MichealSeleniumAutomation;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;


import MichaelSeleniumAutomation.TestComponents.BaseTest;
import MichaelSeleniumAutomation.pageobjects.CartPage;
import MichaelSeleniumAutomation.pageobjects.ProductCatalogue;

public class ErrorValidations extends BaseTest {

	@Test
	public void testFailedLogin() {
		
		page.LoginApplication("michad@gmail.com", "padkdkfd");
		ProductCatalogue ab = new ProductCatalogue(driver);
		
		ab.waitForToastToDisappear("Logout Successfully");
		ab.waitForToast();
		AssertJUnit.assertEquals(page.failedLogin(), "Incorrect email or password.");
		
	}
	
	
	@Test
	public void SubmitIncorrectOrder() throws IOException, InterruptedException {
		// TODO Auto-generated method stub



		

		ProductCatalogue abs = page.LoginApplication("dmichaeldjamba@gmail.com", "Grouth#44");

		System.out.println(abs.toastText());

		AssertJUnit.assertEquals(abs.toastText(), "Login Successfully");
		abs.getItemsList();

		abs.addItemToAcart("IPHONE 13 PRO");

		abs.waitForToastToDisappear("Login Successfully");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//div[@id='toast-container']"),
				"Login Successfully"));
		abs.waitForElemenToDisappearByElement(driver.findElement(By.cssSelector(".ng-animating")));
		abs.waitForToast();
		System.out.println(driver.findElement(By.xpath("//div[@id='toast-container']")).getText());
		AssertJUnit.assertEquals(abs.toastText(), "Product Added To Cart");

		CartPage cc = abs.viewCart();

		cc.ItemInCart("IPHONE 13 PRO");

		AssertJUnit.assertFalse(cc.ItemInCart("IPHONE 13 PRO3"));
		
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[4]")).click();
		abs.waitForToastToDisappear("Logout Successfully");
		abs.waitForElementByElement(driver.findElement(By.cssSelector("[id='userEmail']")));
		
	}
	

}
