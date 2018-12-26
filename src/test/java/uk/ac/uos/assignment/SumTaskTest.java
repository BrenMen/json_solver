package uk.ac.uos.assignment;

import org.junit.Test;
import static org.junit.Assert.*;

public class SumTaskTest {

	SumTask sumTask;
	
	@Test
	public void correctMultiplygetAnswer5Times3Test() throws CustomException {
		sumTask = new SumTask("multiply", "5", "3", "/answer/e94gj3");
		String result = sumTask.calculate();
		assertEquals("15", result);
	}
	
	@Test
	public void corectAdd7And2Test() {
		sumTask = new SumTask("add", "7", "2", "/answer/e94gj3");
		int result = sumTask.addValues("7", "2");
		assertEquals(9, result);
	}

	@Test
	public void correctConcatGetAnswerForLemonAndSaltTest() throws CustomException {
		sumTask = new SumTask("concat", "Lemon", "Salt", "/answer/e94gj3");
		String result = sumTask.calculate();
		assertEquals("LemonSalt", result);
	}
	
	@Test
	public void correctMultiply9Times4Test() {
		sumTask = new SumTask("multiply", "9", "4", "/answer/e94gj3");
		int result = sumTask.multiplyValues("9", "4");
		assertEquals(36, result);
	}
	
	@Test
	public void correctAddGetAnswerfor253And73Test() throws CustomException {
		sumTask = new SumTask("add", "253", "73", "/answer/e94gj3");
		String result = sumTask.calculate();
		assertEquals("326", result);
	}
	
	@Test
	public void invalidInstructionThrowsCorrectException() {
		boolean exception = false;
		String customMessage = "";
		sumTask = new SumTask("remainder", "6.4", "2", "/answer/e94gj3");
		try {
			sumTask.calculate();
		} catch (CustomException error) {
			exception = true;
			customMessage = error.getMessage();
		}
		assertEquals(true, exception);
		assertEquals("Error! Invalid instruction: remainder", customMessage);
	}
}