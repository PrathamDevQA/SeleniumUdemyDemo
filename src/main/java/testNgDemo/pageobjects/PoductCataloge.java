package testNgDemo.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testNgDemo.AbstarctPackage.AbstractCompponenct;

public class PoductCataloge extends AbstractCompponenct {

	WebDriver driver;

	public PoductCataloge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	private List<WebElement> products;

	@FindBy(css = ".ng-animating")
	private WebElement spinner;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		WebElement prodact = getProductsList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prodact;
	}

	public void addProductToCard(String productName) {

		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToDisappea(spinner);
		waitForElementToAppear(toastMessage);

	}
}
