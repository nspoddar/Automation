package HereScenarios;
import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class hereTestScenarios {
	WebDriver driver;
	final String expectedTitle = "Documentation, Code Examples and API References - HERE Developer";
	
	@Test
	public void hereTestCase1(){
		/**
		 * This is the first test case scenario
		 * 1.Verify documentation page
		 * */
		//Provide browser driver name and its path below in the setProperties
		System.setProperty("webdriver.chrome.driver", "E:\\here workspace\\HereTestCases\\drivers\\chromedriver.exe");
		//Launching chrome driver
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.get("https://developer.here.com/documentation");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='heading']")));
		System.out.println("page successfully loaded...");
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("Page title verified!!\n Expected title: " + expectedTitle + "\nActual title from page: " + actualTitle);
		driver.close();
	}
	
	@Test
	public void hereTestCase2() {
		/**
		 * This is the second test case scenario
		 * 2.Verify status code of the documentation page
		 * */
		RestAssured.baseURI = "https://developer.here.com/documentation";
		RequestSpecification httpRequest = RestAssured.given();
		//Hitting the base URL
		Response response = httpRequest.get();
		int statusCode = response.getStatusCode();
		//Validating the status code for 200
		Assert.assertEquals(statusCode, 200);
		System.out.println("Voila! 200 status it is...");
	}
	
	@Test
	public void hereTestCase3() {
		/**
		 * This is the third test case scenario
		 * 3. Click on each link
		 * 4. Verify status code 200
		 **/
		List<String> links = new ArrayList<>();

		driver.findElements(By.xpath("//div//li//a")).forEach(e -> {
			links.add(e.getAttribute("href"));
		});

		links.forEach(hereTestScenarios::navigate);
		driver.close();
		
	}
	
	public static void navigate(String href) {

		RestAssured.baseURI = "https://developer.here.com/documentation";
		RequestSpecification httpRequest = RestAssured.given();
		//Hitting the child URLs
		Response response = httpRequest.get(href);
		int statusCode = response.getStatusCode();
		//Validating the status code for 200
		Assert.assertEquals(statusCode, 200);
		System.out.println("Test passed");
	}

}


