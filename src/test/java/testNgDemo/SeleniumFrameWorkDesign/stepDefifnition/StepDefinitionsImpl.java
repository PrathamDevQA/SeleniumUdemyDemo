package testNgDemo.SeleniumFrameWorkDesign.stepDefifnition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testNgDemo.SeleniumFrameWorkDesign.TestComponenets.BaseTest;
import testNgDemo.pageobjects.CartPage;
import testNgDemo.pageobjects.CheckOutPage;
import testNgDemo.pageobjects.ConfirmationPage;
import testNgDemo.pageobjects.LandingPage;
import testNgDemo.pageobjects.PoductCataloge;

public class StepDefinitionsImpl extends BaseTest {
	public LandingPage landingPage;
	public PoductCataloge productCatalogs;
	public ConfirmationPage confirmPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = lunchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productCatalogs = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogs.getProductsList();
		productCatalogs.addProductToCard(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		Thread.sleep(7000);
		CartPage cartPage = productCatalogs.gotoCartPage();

		Boolean match = cartPage.verifyProductDisaplya(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();

		checkOutPage.selectCountry("India");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");

		Thread.sleep(5000);
		ConfirmationPage confirmPage = checkOutPage.submitButton();

	}

	@Then("{string} message is disaplyed on ConfirmationPage")
	public void message_is_disaplyed_on_ConfirmationPage(String string) {
		String confirmMessage = confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));

	}
	
	@Then("^\"([^\"]*)\" message is displayed.$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessaage());
		driver.close();
    }

	
	
	
}
