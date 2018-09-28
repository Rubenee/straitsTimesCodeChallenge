@AmazonTest
Feature: Amazon Add Product to Shopping cart

Background:
	Given I launch the the websit using he "AmazonURL"

  #====================================================================================
  #					Scenario 1 - Verify the presence of the home page header
  #====================================================================================
	@AmazonPresenceOfHomePageHeader
  Scenario: Verify the presence of the home page header
  Then I should be able to see the home page header displayed when user "Logged Out"
  
  #====================================================================================
  #			Scenario 2 - Verify error message for incorrect user Name
  #====================================================================================
	@AmazonErrorIncorrectUserName
  Scenario: Verify error message for incorrect user Name
  When I click on the sign in button
  And I enter the "User Name" as "IncorrectUserName"
  And I click on the "Continue" button 
  Then I should see the error message displayed as "ErrorMessageForIncorrectUserName"
  
  #====================================================================================
  #			Scenario 3 - Verify error message for incorrect password
  #====================================================================================
	@AmazonErrorIncorrectPassword
  Scenario: Verify error message for incorrect password
  When I click on the sign in button
  And I enter the "User Name" as "CorrectUserName"
  And I click on the "Continue" button
  And I enter the "Password" as "IncorrectPassword"
  And I click on the "Submit" button
  Then I should see the error message displayed as "ErrorMessageForIncorrectPassword"
  
  #====================================================================================
  #			Scenario 4 - Verify login to the site using valid credentials
  #====================================================================================
	@AmazonSuccessfulLogin
  Scenario: Verify login to the site using valid credentials
  When I click on the sign in button
  And I enter the "User Name" as "CorrectUserName"
  And I click on the "Continue" button
  And I enter the "Password" as "CorrectPassword"
  And I click on the "Submit" button
  And I wait for the user to be successfully logged in
  Then I should be able to see the home page header displayed when user "Logged In"
  
  #====================================================================================
  #			Scenario 5 - Verify adding product to cart
  #====================================================================================
	@AmazonAddProductToCart
  Scenario: Verify adding product to cart
  When I click on the sign in button
  And I login with "CorrectUserName" and "CorrectPassword"
  And I conduct a search for "ProductToBeSearched"
  And I click on the search results at "ProductPosition"
  Then I should be redirected to the product Page
  When I get the product name and price
  And I add the "Quantity" to the cart
  Then I should see the product is added to the cart in product page
  When I navigate to the cart page
  Then I should see the product "ProductToBeSearched" is added to the cart
  And I should see the name and the price displayed correctly
  When I logout out of the page
  And I login with "CorrectUserName" and "CorrectPassword"
  And I navigate to the cart page
  Then I should see the product "ProductToBeSearched" is added to the cart