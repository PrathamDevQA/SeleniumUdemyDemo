package testNgDemo.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testNgDemo.AbstarctPackage.AbstractCompponenct;

public class OrderPage extends AbstractCompponenct{
	
	WebDriver driver;
	
	@FindBy(css = "")
	private List <WebElement> productsName;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean veryifyOrderDisaplyed(String productName) {
		Boolean match = productsName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return true;
	}
	

}
