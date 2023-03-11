package testNgDemo.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testNgDemo.AbstarctPackage.AbstractCompponenct;

public class CartPage extends AbstractCompponenct{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css =".cartSection h3")
	private List<WebElement> productTitles;
	
	@FindBy(css = ".totalRow button")
	private WebElement checkoutEle;
	
	public boolean verifyProductDisaplya(String productName ) {
		Boolean match = productTitles.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));	
		return match;	
	}
	
	public CheckOutPage goToCheckOut() {
		checkoutEle.click();
	    CheckOutPage checkOutPage =	new CheckOutPage(driver);
		return checkOutPage;
	}
	
	
}
