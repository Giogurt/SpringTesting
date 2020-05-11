package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringCalculatorTest {

	StringCalculator calculator = new StringCalculator();
	
	@Test
	void testEmptyString() {
		//Given
		String input = "";
		int expected = 0;
		//When
		int result = calculator.add(input);
		//Then
		assertEquals(expected, result);
	}
	
	@Test
	void ifOneThenReturnOne() {
		//Given
		String input = "1";
		int expected = 1;
		//When
		int result = calculator.add(input);
		//Then
		assertEquals(expected, result);
	}
	
	@Test
	void ifOneAndTwo_thenReturnSum() {
		//Given
		String input = "1,2";
		int expected = 3;
		//When
		int result = calculator.add(input);
		//Then
		assertEquals(expected, result);
	}
	
	@Test
	void ifArrayBiggerThanTwo() {
		//Given
		String input = "3,5,8";
		int expected = 16;
		//When
		int result = calculator.add(input);
		//Then
		assertEquals(expected, result);
	}
	
	@Test
	void newLineSeparator() {
		//Given
		String input = "1\n2,3";
		int expected = 6;
		//When
		int result = calculator.add(input);
		//Then
		assertEquals(expected, result);
	}
	
	@Test
	void differentDelimeter() {
		//Given
		String input = "//;\n1;2";
		int expected = 3;
		//When
		int result = calculator.add(input);
		//Then
		assertEquals(expected, result);
	}
	
	@Test
	void ifNegativeNumber_thenIllegalArgumentException()  {
		//Given
		String input = "-3";
		String expectedMsg = "negatives not allowed -3";
		//When
		try {
			int result = calculator.add(input);
			fail("Esto fallo amigo");
		} catch(IllegalArgumentException e) {
			assertEquals(expectedMsg, e.getMessage());
		}
		
		//Then
	}
}
