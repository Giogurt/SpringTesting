package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@SpringBootTest
class TestHtmlApplicationTests {
	private WebClient webClient;
	
	@BeforeEach
	public void init() throws Exception {
		webClient = new WebClient();
	}
	
	@AfterEach
	public void close() throws Exception {
		webClient.close();
	}
	
	@Test
	public void givenACLient_whenEnteringAutomationPractice_thenPageTitleIsCorrect()
		throws Exception {
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			HtmlPage page = webClient.getPage("http://automationpractice.com/index.php");
			
			assertEquals("My Store", page.getTitleText());
	}
	
	@Test
	public void givenAClient_whenEnteringLoginCredentials_thenAccountPageIsDisplayed()
		throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("a01566171@itesm.mx");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("NoviHemi2;Latq");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		assertEquals("My account - My Store", resultPage.getTitleText());
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenAuthenticationPageIsDisplayed()
		throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("a01566171@itesm.mx");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("N");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		assertEquals("Login - My Store", resultPage.getTitleText());
	}
	
	@Test
	public void givenAClient_whenEnteringWrongLoginCredentials_thenErrorMessageIsDisplayed()
		throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("a01566171@itesm.mx");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("N");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		HtmlDivision div = (HtmlDivision) resultPage.getByXPath("//div[@class='alert alert-danger']").get(0);
		
		assertNotNull(div, "The error message is appearing");
	}
	
	@Test
    public void givenAClient_whenSearchingNotExistingProduct_thenNoResultsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=my-account");
		HtmlTextInput searchField = (HtmlTextInput) page.getElementById("search_query_top");
		searchField.setValueAttribute("hello hello");
		HtmlButton searchButton = (HtmlButton) page.getByXPath("//button[@class='btn btn-default button-search']").get(0);
		HtmlPage resultPage = searchButton.click();
		
		HtmlParagraph warning = (HtmlParagraph) resultPage.getByXPath("//p[@class='alert alert-warning']").get(0);
		
		assertNotNull(warning, "The warning message is appearing");
    }

    @Test
    public void givenAClient_whenSearchingEmptyString_thenPleaseEnterDisplayed() throws Exception {
    	webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=my-account");
		HtmlTextInput searchField = (HtmlTextInput) page.getElementById("search_query_top");
		searchField.setValueAttribute("");
		HtmlButton searchButton = (HtmlButton) page.getByXPath("//button[@class='btn btn-default button-search']").get(0);
		HtmlPage resultPage = searchButton.click();
		
		HtmlParagraph warning = (HtmlParagraph) resultPage.getByXPath("//p[@class='alert alert-warning']").get(0);
		
		assertEquals("Please enter a search keyword", warning.getTextContent().trim());
    }

    @Test 
    public void givenAClient_whenSigningOut_thenAuthenticationPageIsDisplayed() throws Exception {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		HtmlPage page = webClient.getPage("http://automationpractice.com/index.php?controller=my-account");
		HtmlTextInput emailField = (HtmlTextInput) page.getElementById("email");
		emailField.setValueAttribute("a01566171@itesm.mx");
		HtmlPasswordInput passwordField = (HtmlPasswordInput) page.getElementById("passwd");
		passwordField.setValueAttribute("NoviHemi2;Latq");
		HtmlButton submitButton = (HtmlButton) page.getElementById("SubmitLogin");
		HtmlPage resultPage = submitButton.click();
		
		HtmlAnchor signOut = (HtmlAnchor) resultPage.getAnchorByHref("http://automationpractice.com/index.php?mylogout=");
		resultPage = signOut.click();
		
		assertEquals("Login - My Store", resultPage.getTitleText());
    }
}
