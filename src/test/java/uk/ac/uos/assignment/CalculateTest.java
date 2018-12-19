package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculateTest {

	Calculate instruction;
	
	@Test
	public void correctMultipl5Times3Test() throws CustomException {
		instruction = new Calculate("multiply", "5", "3", "/answer/e94gj3");
		String result = instruction.getAnswer();
		assertEquals("15", result);
	}
	
	@Test
	public void corectAdd7And2Test() {
		instruction = new Calculate("add", "7", "2", "/answer/e94gj3");
		int result = instruction.add("7", "2");
		assertEquals(9, result);
	}

	@Test
	public void correctConcatGetAnswerForLemonAndSaltTest() throws CustomException {
		instruction = new Calculate("concat", "Lemon", "Salt", "/answer/e94gj3");
		String result = instruction.getAnswer();
		assertEquals("LemonSalt", result);
	}
	
	@Test
	public void correctConcateOrangeAndPearTest() {
		instruction = new Calculate("concatenate", "Orange", "Pear", "/answer/e94gj3");
		String result = instruction.concatenate("Orange", "Pear");
		assertEquals("OrangePear", result);
	}
	
	@Test
	public void correctMultiply9And4Test() {
		instruction = new Calculate("multiply", "9", "4", "/answer/e94gj3");
		int result = instruction.multiply("9", "4");
		assertEquals(36, result);
	}
	
	@Test
	public void correctAddGetAnswerfor253And73Test() throws CustomException {
		instruction = new Calculate("add", "253", "73", "/answer/e94gj3");
		String result = instruction.getAnswer();
		assertEquals("326", result);
	}
	
	@Test
	public void invalidInstructionThrowsCorrectException() {
		boolean exception = false;
		String customMessage = "";
		instruction = new Calculate("remainder", "6.4", "2", "/answer/e94gj3");
		try {
			instruction.getAnswer();
		} catch (CustomException error) {
			exception = true;
			customMessage = error.getMessage();
		}
		assertEquals(true, exception);
		assertEquals("Error! Invalid instruction: remainder", customMessage);
	}
}