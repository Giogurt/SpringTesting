package mx.tec.lab;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PalindromeVerifierTest {

	@Test
	public void givenASingleCharacter_WhenVerify_ThenTrue() {
		// Given
		String input = "a";
		boolean expected = true;

		// When
		boolean actual = PalindromeVerifier.verify(input);

		// Then
		assertEquals(expected, actual);
	}

	@Test
	public void givenTwoDifferentCharacters_WhenVerify_ThenFalse() {
		// Given
		String input = "ab";
		boolean expected = false;

		// When
		boolean actual = PalindromeVerifier.verify(input);

		// Then
		assertEquals(expected, actual);
	}

	@Test
	public void givenThreeLetterWordPalindrome_WhenVerify_thenTrue() {
		// Given
		String input = "aba";
		boolean expected = true;

		// When
		boolean actual = PalindromeVerifier.verify(input);

		// Then
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenFourLetterWordPalindrome_WhenVerify_thenTrue() {
		// Given
		String input = "abba";
		boolean expected = true;

		// When
		boolean actual = PalindromeVerifier.verify(input);

		// Then
		assertEquals(expected, actual);
	}
	
	@Test
	public void givenAPhrasePalindrome_WhenVerify_thenTrue() {
		// Given
		String input = "a man a plan a canal panama";
		boolean expected = true;

		// When
		boolean actual = PalindromeVerifier.verify(input);

		// Then
		assertEquals(expected, actual);
	}
}
