package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestHtml2ApplicationTests {
	private static WebDriver driver;
	
	@BeforeAll
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_81.0.4044.69\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
	
	@Test
	public void givenAClient_whenEnteringAutomationPractice_thenPageTitleIsCorrect() throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php");
		String title = driver.getTitle();
		
		//Then
		assertEquals("My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed() throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01566171@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("NoviHemi2;Latq");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		String title = driver.getTitle();
		
		//Then
		assertEquals("My account - My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed()
		throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01566153@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("WrongCredential");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		String title = driver.getTitle();
		
		//Then
		assertEquals("Login - My Store", title);
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed()
		throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01566153@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("WrongCredential");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		WebElement error = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
		
		//Then
		assertNotNull(error, "Oh-oh. You have an error.");
	}
	
	@Test
	public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed()
		throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01566153@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("Testing1");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		WebElement searchField = driver.findElement(By.id("search_query_top"));
		searchField.sendKeys("Cowboyw");
		WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default button-search']"));
		searchButton.click();

		WebElement warningError = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
		
		// Then
		assertNotNull(warningError, "Oh-oh. No results were found for your search.");
	}
	
	@Test
	public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed()
		throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01566153@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("Testing1");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		WebElement searchField = driver.findElement(By.id("search_query_top"));
		searchField.sendKeys("");
		WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default button-search']"));
		searchButton.click();

		WebElement warningError = driver.findElement(By.xpath("//p[@class='alert alert-warning']"));
		
		// Then
		assertEquals("Please enter a search keyword", warningError.getText().trim());
	}
	
	@Test
	public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed()
		throws Exception {
		//When
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement emailField = driver.findElement(By.id("email"));
		emailField.sendKeys("a01566153@itesm.mx");
		WebElement passwordField = driver.findElement(By.id("passwd"));
		passwordField.sendKeys("Testing1");
		WebElement submitButton = driver.findElement(By.id("SubmitLogin"));
		submitButton.click();
		
		WebElement logOut = driver.findElement(By.className("logout"));
		logOut.click();
		String title = driver.getTitle();

		assertEquals("Login - My Store", title);
	}

}