package visaCodeChallengePageObjects;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage {
	 
	private WebDriver driver;
	private static Log log = LogFactory.getLog(AmazonHomePage.class);
    HelperClass helper = new HelperClass();
    
	@FindBy(how = How.ID, using = "navbar")
	public static WebElement header;
	
	@FindBy(how= How.XPATH, using = "//a[@id='nav-link-accountList']/span")
	public static WebElement headerText;
	
	@FindBy(how = How.ID, using = "nav-link-accountList")
	public static WebElement signInLink;
	
	@FindBy(how = How.NAME, using = "email")
	public static WebElement userNameField;
	
	@FindBy(how = How.NAME, using = "password")
	public static WebElement passwordField;
	
	@FindBy(how = How.ID, using = "continue")
	public static WebElement continueBtn;
	
	@FindBy(how = How.ID, using = "signInSubmit")
	public static WebElement submitBtn;
	
	@FindBy(how = How.XPATH, using = "//div[@id='auth-error-message-box']//span[@class='a-list-item']")
	public static WebElement errorMessage;
	
	@FindBy(how = How.ID, using = "nav-recently-viewed")
	public static WebElement browseHistory;
	
	@FindBy(how = How.XPATH, using = "//form[@class='nav-searchbar']//input[@id='twotabsearchtextbox']")
	public static WebElement searchTextBox;
	
	@FindBy(how = How.CLASS_NAME, using = "nav-input")
	public static WebElement searchTextBoxIcon;
	
	@FindBy(how= How.XPATH, using="//a[contains(@class,'s-access-detail-page')]")
	public static List<WebElement> searchResults;
	
	@FindBy(how = How.ID, using = "nav-item-signout-sa")
	public static WebElement signOutLink;
	
	public AmazonHomePage(WebDriver driver){ 
        this.driver=driver;
        PageFactory.initElements(driver, this);
   }
	
	public void launchWebsite(String URL) {
		//Launch the website using the given URL
		driver.get(URL);
		log.info("WAITING FOR AMAZON HOME PAGE TO LOAD.....");
		helper.waitForVisibilityOfElements(header, "AMAZON HOME PAGE NOT LOADED ON ENTERING THE URL",driver);
	}

	public boolean isHeaderPresent() {
		//Verify the header is present when the home page is loaded
		return header.isDisplayed();
	}

	public boolean isCorrectHeaderDisplayed(String userState) {
		//Verify the header dispalyed is correct for the user state
		boolean isHeaderCorrect = false;
		String textDisplayed = headerText.getText();
		if(userState.contains("Out")) {
			isHeaderCorrect = textDisplayed.contains("Sign in");
		}else {
			isHeaderCorrect = !textDisplayed.contains("Sign in");
		}
		return isHeaderCorrect;
		
	}
	
	public void clickSignIn() {
		//Click on the sign in button
		Actions action = new Actions(driver);
		action.moveToElement(signInLink);
		action.click().build().perform();
		log.info("WAITING FOR SIGN IN PAGE TO LOAD.....");
		helper.waitForVisibilityOfElements(userNameField, "PAGE TO ENTER USER NAME NOT LOADED ON CLICKING SIGN IN",driver);
		log.info("SIGN IN PAGE LOADED SUCCESSFULLY");
	}

	public void enterValueInField(String fieldName, String valueToEnter) {
		//Enter the value into the given Field
		Actions action = new Actions(driver);
		if(fieldName.equals("User Name")) {
			action.moveToElement(userNameField);
		}else {
			action.moveToElement(passwordField);
		}
		action.sendKeys(valueToEnter).build().perform();
	}

	public void clickButton(String buttonName) {
		//click on the given button
		Actions action = new Actions(driver);
		if(buttonName.equals("Continue")) {
			action.moveToElement(continueBtn);
		}else {
			action.moveToElement(submitBtn);
		}
		action.click().build().perform();
	}

	public String getErrorMessage(String string) {
		//Get the text of error message displayed
		return errorMessage.getText();
	}

	public boolean isErrorMessageDisplayed() {
		//Verify error message is displayed
		return errorMessage.isDisplayed();
	}

	public void userLoggedIn() {
		log.info("WAITING FOR THE LOGGED IN USER'S HOME PAGE TO BE LOADED......");
		helper.waitForVisibilityOfElements(browseHistory, "USER NOT LOGGED IN ON CLICKING SUBMIT BUTTON",driver);
		log.info("HOME PAGE FOR LOGGED IN USER LOADED SUCCESSFULLY");
	}

	public void loginToPage(String userName, String passWord) {
		//Login steps in a single method
		enterValueInField("User Name", userName);
		clickButton("Continue");
		enterValueInField("Password", passWord);
		clickButton("Sign In");
		userLoggedIn();
	}

	public void searchForProduct(String searchText) {
		//Search for the product
		Actions action = new Actions(driver);
		action.moveToElement(searchTextBox);
		action.click();
		action.sendKeys(searchText).build().perform();
		action.moveToElement(searchTextBoxIcon);
		action.click().build().perform();
		log.info("WAITING FOR SEARCH RESULTS TO LOAD........");
		helper.waitForVisibilityOfElements(searchResults.get(0), "SEARCH RESULTS NOT LOADED ON CLICKING SEARCH", driver);
		log.info("SEARCH RESULTS LOADED FOR THE SEARCH TEXT");
	}

	public AmazonProductPage clickOnResultsAtGivePosition(String position) {
	     int resultPosition = Integer.parseInt(position) - 1;
	     Actions action = new Actions(driver);
	     action.moveToElement(searchResults.get(resultPosition));
	     action.click().build().perform();
	     log.info("WAITING FOR THE PRODUCT PAGE TO LAUNCH.....");
	     return new AmazonProductPage(driver);
	}

	public void logOut() {
		//Logout of the page
		Actions action = new Actions(driver);
		action.moveToElement(signInLink).build().perform();
		action.moveToElement(signOutLink);
		action.click().build().perform();
		helper.waitForVisibilityOfElements(userNameField, "USER NOT LOGGED OUT SUCCESSFULLY", driver);
	}
	
	
}
