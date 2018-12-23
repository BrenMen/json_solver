package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SendAnswerTest {
	
	SendAnswer sender;

	@Before
	public void setup() throws Exception {
		sender = new SendAnswer();
	}
	
	@Test
	public void correctCalculationForAdditionTaskTest() throws CustomException, IOException {
		Calculate task = new Calculate("add", "8054", "2119", "/answer/8473");
		ArrayList<Calculate> taskList = new ArrayList<Calculate>();
		taskList.add(task);
		String result = sender.sendAnswer("http://i2j.openode.io", taskList);
		
		assertEquals("Destination URL: http://i2j.openode.io/answer/8473\n"
				+ "Instruction: add\n"
				+ "Parameter One: 8054\n"
				+ "Parameter Two: 2119\n"
				+ "Answer: 10173\n\n", result);
	}
	
	@Test
	public void correctCalculationForMultiplicationTaskTest() throws CustomException, IOException {
		Calculate task = new Calculate("multiply", "4500", "5245", "/answer/5589");
		ArrayList<Calculate> taskList = new ArrayList<Calculate>();
		taskList.add(task);
		String result = sender.sendAnswer("http://i2j.openode.io", taskList);
		
		assertEquals("Destination URL: http://i2j.openode.io/answer/5589\n"
				+ "Instruction: multiply\n"
				+ "Parameter One: 4500\n"
				+ "Parameter Two: 5245\n"
				+ "Answer: 23602500\n\n", result);
	}
	
	@Test
	public void correctCalculationForConcatinationTaskTest() throws CustomException, IOException {
		Calculate task = new Calculate("concat", "1916", "6575", "/answer/2386");
		ArrayList<Calculate> taskList = new ArrayList<Calculate>();
		taskList.add(task);
		String result = sender.sendAnswer("http://i2j.openode.io", taskList);
		
		assertEquals("Destination URL: http://i2j.openode.io/answer/2386\n"
				+ "Instruction: concat\n"
				+ "Parameter One: 1916\n"
				+ "Parameter Two: 6575\n"
				+ "Answer: 19166575\n\n", result);
	}
	
	@Test
	public void incorrectCalculationForSubtractTaskTest() throws CustomException, IOException {
		String customMessage = "";
		Calculate task = new Calculate("subtract", "4918", "7706", "/answer/6160");
		ArrayList<Calculate> taskList = new ArrayList<Calculate>();
		taskList.add(task);
		try {
			sender.sendAnswer("http://i2j.openode.io", taskList);
		} catch (CustomException error) {
			customMessage = error.getMessage();
		}
		assertEquals("Error! Invalid instruction: subtract", customMessage);
	}
}
