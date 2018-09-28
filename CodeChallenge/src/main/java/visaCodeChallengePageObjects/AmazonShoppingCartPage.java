package visaCodeChallengePageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AmazonShoppingCartPage {
	
	private WebDriver driver;
	HelperClass helper = new HelperClass();
	
	@FindBy(how = How.CLASS_NAME, using="sc-list-item-content")
	public static WebElement productInCart;
	
	@FindBy(how = How.XPATH, using="//div[@class='sc-list-item-content']//a[contains(@class,'sc-product-link')]/span")
	public static WebElement productInCartTitle;
	
	@FindBy(how = How.XPATH, using="//span[contains(@class,'sc-product-price')]")
	public static WebElement productInCartPrice;

	public AmazonShoppingCartPage(WebDriver driver){ 
        this.driver=driver;
        PageFactory.initElements(driver, this);
   }

	public boolean isProductDisplayedInCartPage(String searchText) {
		//Verify the added product is displayed in the cart
		boolean isProductDisplayed = false;
		String[] keywords = searchText.split("\\s");
		String productName = getSelectedProductName().toLowerCase();
		for(int x=0;x<keywords.length;x++) {
			isProductDisplayed = productName.contains(keywords[x].toLowerCase());
			if(isProductDisplayed==false) {
				break;
			}
		}
		return isProductDisplayed && productInCart.isDisplayed();	
	}

	public String getSelectedProductName() {
		//Get the product Name displayed in the cart page
		return productInCartTitle.getText();
	}
	
	public String getSelectedProductPrice() {
		//Get the product Name displayed in the cart page
		return productInCartPrice.getText();
	}

}
