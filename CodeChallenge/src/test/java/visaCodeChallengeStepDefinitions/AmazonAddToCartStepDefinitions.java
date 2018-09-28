package visaCodeChallengeStepDefinitions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import visaCodeChallengePageObjects.AmazonHomePage;
import visaCodeChallengePageObjects.AmazonProductPage;
import visaCodeChallengePageObjects.AmazonShoppingCartPage;

public class AmazonAddToCartStepDefinitions extends AmazonBaseTestClass {
	
	AmazonHomePage homePage;
	AmazonProductPage productPage;
	AmazonShoppingCartPage cartPage;
	String productName;
	String price;
	private static Log log = LogFactory.getLog(AmazonAddToCartStepDefinitions.class);
	
	@Given("I launch the the websit using he {string}")
	public void i_launch_the_the_websit_using_he(String string) {
		homePage.launchWebsite(readPropertyValues(string));
	}

	@Then("I should be able to see the home page header displayed when user {string}")
	public void i_should_be_able_to_see_the_home_page_header_displayed_when_user(String string) {
	    Assert.assertTrue("THE HEADER IS NOT DISPLAYED IN THE HOME PAGE",homePage.isHeaderPresent());
	    Assert.assertTrue("HEADER DISPLAYED IS NOT FOR THE "+string.toUpperCase()+" USER",homePage.isCorrectHeaderDisplayed(string));
	}
	
	@When("I click on the sign in button")
	public void i_click_on_the_sign_in_button() {
		homePage.clickSignIn();
	}

	@When("I enter the {string} as {string}")
	public void i_enter_the_as(String string, String string2) {
	    homePage.enterValueInField(string, readPropertyValues(string2));
	}

	@When("I click on the {string} button")
	public void i_click_on_the_button(String string) {
	   homePage.clickButton(string);
	}

	@Then("I should see the error message displayed as {string}")
	public void i_should_see_the_error_message_displayed_as(String string) {
		Assert.assertTrue("ERROR MESSAGE NOT DISPLAYED WHEN ENTERING INCORRECT VALUES TO FIELD", homePage.isErrorMessageDisplayed());
	     Assert.assertEquals("THE ERROR MESSAGE DISPLAYED FOR INCORRECT "+string.substring(15)+" IS INCORRECT", 
	    											readPropertyValues(string),homePage.getErrorMessage(string));
	}
	
	@When("I wait for the user to be successfully logged in")
	public void i_wait_for_the_user_to_be_successfully_logged_in() {
	    homePage.userLoggedIn();
	}
	
	@When("I login with {string} and {string}")
	public void i_login_with_and(String string, String string2) {
	    homePage.loginToPage(readPropertyValues(string),readPropertyValues(string2));
	}

	@When("I conduct a search for {string}")
	public void i_conduct_a_search_for(String string) {
	    homePage.searchForProduct(readPropertyValues(string));
	}

	@When("I click on the search results at {string}")
	public void i_click_on_the_search_results_at(String string) {
		productPage = homePage.clickOnResultsAtGivePosition(readPropertyValues(string));
	}

	@Then("I should be redirected to the product Page")
	public void i_should_be_redirected_to_the_product_Page() {
	    Assert.assertTrue("PRODUCT PAGE IS NOT LOADED ON CLICKING ON THE PRODUCT IN RESULTS PAGE", productPage.isProductPageLoaded());
	    log.info("PRODUCT PAGE LOADED SUCCESSFULLY");
	}

	@When("I get the product name and price")
	public void i_get_the_product_name_and_price() {
	    productName = productPage.getProductName();
	    price = productPage.getProductNamePrice();
	}

	@When("I add the {string} to the cart")
	public void i_add_the_to_the_cart(String string) {
	    productPage.addProductToCart(readPropertyValues(string));
	}

	@Then("I should see the product is added to the cart in product page")
	public void i_should_see_the_product_is_added_to_the_cart_in_product_page() {
	    Assert.assertTrue("PRODUCT IS NOT ADDED TO THE CART IN PRODUCT PAGE", productPage.isProductAddedToCart());
	}

	@When("I navigate to the cart page")
	public void i_navigate_to_the_cart_page() {
		cartPage = productPage.navigateToCart();
	}

	@Then("I should see the product {string} is added to the cart")
	public void i_should_see_the_product_is_added_to_the_cart(String string) {
		Assert.assertTrue("PRODUCT ADDED IS NOT DISPLAYED IN THE CART PAGE",cartPage.
																	isProductDisplayedInCartPage(readPropertyValues(string)));
	}

	@Then("I should see the name and the price displayed correctly")
	public void i_should_see_the_name_and_the_price_displayed_correctly() {
	    Assert.assertEquals("THE PRODUCT NAME DISPLAYED IN THE CART IS INCORRECT", productName, cartPage.getSelectedProductName());
	    Assert.assertEquals("THE PRODUCT PRICE DISPLAYED IN THE CART IS INCORRECT", price, cartPage.getSelectedProductPrice());
	}

	@When("I logout out of the page")
	public void i_logout_out_of_the_page() {
	    homePage.logOut();
	}
	
	@Before
	public void beforeMethod() {
		initializeDriver();
		homePage = new AmazonHomePage(AmazonBaseTestClass.driver);
	}

	@After
	public void afterMethod(Scenario scenario) {
		sessionCleanup(scenario);
	}
}
