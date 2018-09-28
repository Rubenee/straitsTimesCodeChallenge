package visaCodeChallengePageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AmazonProductPage {
	
	private WebDriver driver;
	HelperClass helper = new HelperClass();
	
	@FindBy(how = How.ID, using = "productTitle")
	public static WebElement productTitle;
	
	@FindBy(how = How.ID, using = "priceblock_ourprice")
	public static WebElement productPrice;
	
	@FindBy(how = How.ID,using="quantity")
	public static List<WebElement> quantityDropDown;
	
	@FindBy (how = How.ID, using="add-to-cart-button")
	public static WebElement addToCartBtn;
	
	@FindBy (how = How.XPATH, using="//h1[contains(text(),'Added to Cart')]")
	public static WebElement successMessage;
	
	@FindBy (how = How.ID, using="nav-cart")
	public static WebElement cartIcon;
	
	public AmazonProductPage(WebDriver driver){ 
        this.driver=driver;
        PageFactory.initElements(driver, this);
   }

	public boolean isProductPageLoaded() {
		//Verify the product Page is Loaded
		return productTitle.isDisplayed();
	}

	public String getProductName() {
		//Get the product Name
		return productTitle.getText();
	}

	public String getProductNamePrice() {
		//Get the product Name
		return productPrice.getText();
	}

	public void addProductToCart(String quantity) {
		//Add the product to cart
		if(!quantityDropDown.isEmpty()) {
			Select dropDown = new Select(quantityDropDown.get(0));
			dropDown.selectByValue(quantity);
		}
		Actions action = new Actions(driver);
		action.moveToElement(addToCartBtn);
		action.click().build().perform();
		helper.waitForVisibilityOfElements(successMessage, "PRODUCT NOT ADDED TO CART ON CLICKING ADD TO CART BUTTON", driver);
	}

	public boolean isProductAddedToCart() {
		//Verify the product is successfully added to the cart
		return successMessage.isDisplayed();
	}

	public AmazonShoppingCartPage navigateToCart() {
		//Click on cart Icon to navigate to cart page
		Actions action = new Actions(driver);
		action.moveToElement(cartIcon);
		action.click().build().perform();
		return new AmazonShoppingCartPage(driver);
	}

}
