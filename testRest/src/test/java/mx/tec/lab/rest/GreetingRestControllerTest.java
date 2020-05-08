package mx.tec.lab.rest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class GreetingRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void givenARequest_WhenEmptyName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/greeting"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Hello, World!")));
	}
	
	@Test
	public void givenARequest_WhenProvidedName_thenHelloName() throws Exception {
		String name = "Jon";
		
		String response = String.format("Hello, %s!", name);
		
		this.mockMvc.perform(get(String.format("/greeting?name=%s", name)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo(response)));
	}
	
	@Test
	public void givenARequest_WhenNullName_thenHelloWorld() throws Exception {
		this.mockMvc.perform(get("/greeting?name="))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Hello, World!")));
	}
	
	@Test
	public void givenARequest_WhenWrongURL_thenError404() throws Exception {
		this.mockMvc.perform(get("/greting"))
			.andExpect(status().isNotFound());
	}
	
	
	//GOODBYE REQUEST TESTS
	
	@Test
	public void givenAGoodbyeRequest_WhenEmptyName_thenGoodbyeWorld() throws Exception {
		this.mockMvc.perform(get("/goodbye"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo("Goodbye, World!")));
	}
	
	@Test
	public void givenAGoodbyeRequest_WhenProvidedName_thenGoodbyeName() throws Exception {
		String name = "Jon";
		String response = String.format("Goodbye, %s!", name);
		
		this.mockMvc.perform(get(String.format("/goodbye?name=%s", name)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("content", equalTo(response)));
	}
}
