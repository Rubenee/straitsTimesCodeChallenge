package visaCodeChallengeStepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import cucumber.api.Scenario;

public class AmazonBaseTestClass{
	
	protected static WebDriver driver;
	
	public void initializeDriver() {
		System.setProperty("webdriver.chrome.driver","\\VisaCodingChallenge\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public void sessionCleanup(Scenario scenario) {
		if(scenario.isFailed()) {
			//Take screenshot if the scenario fails
			Path path = FileSystems.getDefault().getPath(".").toAbsolutePath();
			String fileName = path + "/target/"+scenario.getName()+".jpeg";
			takeScreenShot(fileName);
		}
		AmazonBaseTestClass.driver.quit();
	}
	
	public void takeScreenShot(String fileName) {
		//Take screen shot and save it to the specified location
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
			FileHandler.copy(scrFile, new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	protected String readPropertyValues(String propertyKey) {
		String propertyKeyValue = null;
		InputStream inputstream= null;
		//Read the values of the given key from property file
		try{
			Properties prop 	= new Properties();
			inputstream = new FileInputStream("src/test/resources/Configurations/Test Data.properties");
			prop.load(inputstream);
			propertyKeyValue = prop.getProperty(propertyKey);
			
		} catch(Exception e){
			System.err.println("Exception" + e);
		}finally {
			try {
				inputstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return propertyKeyValue;
	}
}
