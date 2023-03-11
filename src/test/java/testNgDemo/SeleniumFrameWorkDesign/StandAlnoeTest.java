package testNgDemo.SeleniumFrameWorkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.ExecuteException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testNgDemo.SeleniumFrameWorkDesign.TestComponenets.BaseTest;
import testNgDemo.SeleniumFrameWorkDesign.TestComponenets.Retry;
import testNgDemo.pageobjects.CartPage;
import testNgDemo.pageobjects.CheckOutPage;
import testNgDemo.pageobjects.ConfirmationPage;
import testNgDemo.pageobjects.LandingPage;
import testNgDemo.pageobjects.OrderPage;
import testNgDemo.pageobjects.PoductCataloge;

public class StandAlnoeTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = {"purchase"}, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> maps) throws IOException, InterruptedException {
		

		PoductCataloge productCatalogs = landingPage.loginApplication(maps.get("email"), maps.get("password"));

		List<WebElement> products = productCatalogs.getProductsList();
		productCatalogs.addProductToCard(maps.get("productName"));

		Thread.sleep(7000);
		CartPage cartPage = productCatalogs.gotoCartPage();

		Boolean match = cartPage.verifyProductDisaplya(maps.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.selectCountry("India");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");

		Thread.sleep(5000);
		ConfirmationPage confirmPage = checkOutPage.submitButton();

		String confirmMessage = confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = "submitOrder")
	public void orderHistoryTest() {

//		String productName = "ZARA COAT 3";

		PoductCataloge productCatalogs = landingPage.loginApplication("tokat19562@v2ssr.com", "Reptime1!");
		OrderPage orderpage = productCatalogs.GoTOOrderPAge();	
	
		Assert.assertTrue(orderpage.veryifyOrderDisaplyed(productName));	

	}
	
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * return new Object[][]
	 * {{"tokat19562@v2ssr.com","Reptime1!","ZARA COAT 3"},{"anshika@gmail.com",
	 * "Iamking@000","ADIDAS ORIGINAL"}}; }
	 */
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		/*
		 * Map<String, String> map = new HashMap<>(); map.put("email",
		 * "tokat19562@v2ssr.com"); map.put("password","Reptime1!");
		 * map.put("productName","ZARA COAT 3");
		 * 
		 * Map<String, String> map2 = new HashMap<>(); map.put("email",
		 * "anshika@gmail.com"); map.put("password","IamKing@000");
		 * map.put("productName","ADIDAS ORIGINAL");
		 * 
		 */
		
		List<HashMap<String, String>> data = getJsonDataToRead(System.getProperty("user.dir")+"\\src\\test\\java\\testNgDemo\\SeleniumFrameWorkDesign\\Data\\PurchaseOrder.json");
		
//		return new Object[][] {{map},{map2}};
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
