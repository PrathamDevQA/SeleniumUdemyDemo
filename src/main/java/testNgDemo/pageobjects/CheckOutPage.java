package testNgDemo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testNgDemo.AbstarctPackage.AbstractCompponenct;

public class CheckOutPage extends AbstractCompponenct {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath = "(//button/span[@class='ng-star-inserted'])[2]")
	private WebElement selectCountry;
	
	@FindBy(css = ".action__submit")
	private WebElement submit;
	
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(By.cssSelector(".ta-results"));
		
		
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitButton() {
		
		submit.click();
		
		return new ConfirmationPage(driver);
		
	}
	

}
