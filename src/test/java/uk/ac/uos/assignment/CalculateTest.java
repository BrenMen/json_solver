package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculateTest {

	Calculate jt;

	@Test
	public void adding1And1Gives2() {
		jt = new Calculate("add", "1", "1", "/answer/d3ae45");
		int result = jt.add("1", "1");
		assertEquals(2, result);
	}
	
	@Test
	public void multiplying6And2Gives12() {
		jt = new Calculate("multiply", "6", "2", "/answer/d3ae45");
		int result = jt.multiply("6", "2");
		assertEquals(12, result);
	}
	
	@Test
	public void concatenatingHelloAndWorldGivesHelloWorld() {
		jt = new Calculate("concatenate", "Hello", "World", "/answer/d3ae45");
		String result = jt.concatenate("Hello", "World");
		assertEquals("HelloWorld", result);
	}
	
	@Test
	public void gettingAnswerForAddCase() throws CustomException {
		jt = new Calculate("add", "176", "2", "/answer/d3ae45");
		String result = jt.getAnswer();
		assertEquals("178", result);
	}
	
	@Test
	public void gettingAnswerForMultiplyCase() throws CustomException {
		jt = new Calculate("multiply", "10", "2", "/answer/d3ae45");
		String result = jt.getAnswer();
		assertEquals("20", result);
	}
	
	@Test
	public void gettingAnswerForConcatenateCase() throws CustomException {
		jt = new Calculate("concat", "Uni", "Time", "/answer/d3ae45");
		String result = jt.getAnswer();
		assertEquals("UniTime", result);
	}
	
	@Test
	public void invalidInstructionThrowsJSONException() {
		boolean thrown = false;
		String message = "";
		jt = new Calculate("divide", "4", "3", "/answer/d3ae45");
		
		try {
			jt.getAnswer();
		} catch (CustomException e) {
			thrown = true;
			message = e.getMessage();
		}
		
		assertEquals(true, thrown);
		assertEquals("Boom! Error. Unexpected instruction divide", message);
	}
}