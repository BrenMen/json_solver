package uk.ac.uos.assignment;

import java.util.ArrayList;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SendAnswerTest {
	
	SendAnswer sender;

	@Before
	public void before() throws Exception {
		sender = new SendAnswer();
	}
	
	@Test
	public void correctCalculationForAdditionTaskTest() throws CustomException, IOException {
		SumTask task = new SumTask("add", "8054", "2119", "/answer/8473");
		ArrayList<SumTask> taskList = new ArrayList<SumTask>();
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
		SumTask task = new SumTask("multiply", "4500", "5245", "/answer/5589");
		ArrayList<SumTask> taskList = new ArrayList<SumTask>();
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
		SumTask task = new SumTask("concat", "1916", "6575", "/answer/2386");
		ArrayList<SumTask> taskList = new ArrayList<SumTask>();
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
		SumTask task = new SumTask("subtract", "4918", "7706", "/answer/6160");
		ArrayList<SumTask> taskList = new ArrayList<SumTask>();
		taskList.add(task);
		try {
			sender.sendAnswer("http://i2j.openode.io", taskList);
		} catch (CustomException error) {
			customMessage = error.getMessage();
		}
		assertEquals("Error! Invalid instruction: subtract", customMessage);
	}
}
