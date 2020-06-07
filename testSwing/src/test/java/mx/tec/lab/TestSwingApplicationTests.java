package mx.tec.lab;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestswingApplicationTests {

	TestSwingApplication application = new TestSwingApplication();

	@Test
	void givenTwoIntegers_WhenSum_ThenCorrectResult() {
		// Given 5 and 7
		application.numberOneTextField.setText("5");
		application.numberTwoTextField.setText("7");
		String expectedResult = "12";

		// When operation is sum
		application.operationButton.doClick();

		// Then result is 12
		String actualResult = application.resultTextField.getText();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	void givenAnInvalidInput_whenSum_thenInputIsClearedAndNoResult() {
		// Given
		application.numberOneTextField.setText("NOOOOO");
		application.numberTwoTextField.setText("3");
		String expectedResult = "";

		// When
		application.operationButton.doClick();

		// Then
		String actualResult = application.resultTextField.getText();
		assertEquals(expectedResult, actualResult);
		
		actualResult = application.numberOneTextField.getText();
		assertEquals(expectedResult, actualResult);
	}

}