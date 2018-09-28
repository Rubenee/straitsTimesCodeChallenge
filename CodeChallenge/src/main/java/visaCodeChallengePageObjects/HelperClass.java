package visaCodeChallengePageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {
    
	public void waitForVisibilityOfElements(WebElement element, String message,WebDriver driver) {
		//Waits until the element specified is visible
		WebDriverWait wait = new WebDriverWait(driver,60);
		//Returns the given message if the element cannot be found after waiting for given no of seconds
		wait.withMessage(message);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
