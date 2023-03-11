package testNgDemo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testNgDemo.AbstarctPackage.AbstractCompponenct;

public class LandingPage extends AbstractCompponenct {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "userPassword")
	private WebElement userPassword;

	@FindBy(css = "[class*='flyInOut']")
	private WebElement errorMessage;
	
	@FindBy(id = "login")
	private WebElement submit;

	public PoductCataloge loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		PoductCataloge productCatalog = new PoductCataloge(driver);
		return productCatalog;
	}

	public void goToLandingPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessaage() {
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
