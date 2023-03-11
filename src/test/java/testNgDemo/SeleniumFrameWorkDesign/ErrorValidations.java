package testNgDemo.SeleniumFrameWorkDesign;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testNgDemo.SeleniumFrameWorkDesign.TestComponenets.BaseTest;
import testNgDemo.pageobjects.CartPage;
import testNgDemo.pageobjects.LandingPage;
import testNgDemo.pageobjects.PoductCataloge;

public class ErrorValidations extends BaseTest {
	
	@Test(groups = {"ErrorHandling"})
	public void errorMessageValidation() {
		
		landingPage.loginApplication("tokat19562@v2ssr.com", "Reptime11!");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessaage());
	}

	@Test
	public void itemsValidations() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		System.out.println(productName);
		PoductCataloge productCatalogs =  landingPage.loginApplication("tokat19562@v2ssr.com", "Reptime1!");

		List<WebElement> products = productCatalogs.getProductsList();
		productCatalogs.addProductToCard(productName);

		Thread.sleep(7000);
		CartPage cartPage = productCatalogs.gotoCartPage();

		Boolean match = cartPage.verifyProductDisaplya(productName);
		Assert.assertTrue(match);

		
	}
}
