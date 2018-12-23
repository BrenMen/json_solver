package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import java.io.IOException;

public class OutputTaskTest {

	OutputTask output;

	@Before
	public void before() throws Exception {
		output = new OutputTask();
	}

	@Test
	public void correctOutputFormatTest() throws IOException, CustomException {
		String result = output.formatOutput("http://i2j.openode.io/student?id=s195215");
		String expected = "Tasks received from the domain URL:\n"
				+ "{\"id\":\"s195215\",\"tasks\":[\"/task/7518\",\"/task/5589\",\"/task/2386\",\"/task/3273\",\"/task/3383\",\"/task/7670\",\"/task/6661\",\"/task/5508\",\"/task/6160\",\"/task/4638\"]}\n"
				+ "\n" + "Tasks list:\n"
				+ "{\"instruction\":\"add\",\"parameters\":[7092,6937],\"response URL\":\"/answer/7518\"}\n"
				+ "{\"instruction\":\"multiply\",\"parameters\":[4500,5245],\"response URL\":\"/answer/5589\"}\n"
				+ "{\"instruction\":\"concat\",\"parameters\":[\"1916\",\"6575\"],\"response URL\":\"/answer/2386\"}\n"
				+ "{\"instruction\":\"add\",\"parameters\":[473,\"594\"],\"response URL\":\"/answer/3273\"}\n"
				+ "{\"instruction\":\"concat\",\"parameters\":[\"2207\",9785],\"response URL\":\"/answer/3383\"}\n"
				+ "{\"instruction\":\"multiply\",\"parameters\":[\"7682\",\"2325\"],\"response URL\":\"/answer/7670\"}\n"
				+ "{\"instruction\":\"concat\",\"parameters\":[8689,\"9255\"],\"response URL\":\"/answer/6661\"}\n"
				+ "{\"instruction\":\"concat\",\"parameters\":[\"7907\",4401],\"response URL\":\"/answer/5508\"}\n"
				+ "{\"instruction\":\"subtract\",\"parameters\":[4918,7706],\"response URL\":\"/answer/6160\"}\n"
				+ "Error! Invalid instruction: subtract\n" + "Error for task: http://i2j.openode.io/task/6160\n"
				+ "\"add up the two numbers 8182 and 6931\"\n" + "Error! This is an invalid Object.\n" 
				+ "Error for task: http://i2j.openode.io/task/4638\n" + "\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/7518\n" + "Instruction: add\n" + "Parameter One: 7092\n"
				+ "Parameter Two: 6937\n" + "Answer: 14029\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/5589\n" + "Instruction: multiply\n" + "Parameter One: 4500\n"
				+ "Parameter Two: 5245\n" + "Answer: 23602500\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/2386\n" + "Instruction: concat\n" + "Parameter One: 1916\n"
				+ "Parameter Two: 6575\n" + "Answer: 19166575\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/3273\n" + "Instruction: add\n" + "Parameter One: 473\n"
				+ "Parameter Two: 594\n" + "Answer: 1067\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/3383\n" + "Instruction: concat\n" + "Parameter One: 2207\n"
				+ "Parameter Two: 9785\n" + "Answer: 22079785\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/7670\n" + "Instruction: multiply\n" + "Parameter One: 7682\n"
				+ "Parameter Two: 2325\n" + "Answer: 17860650\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/6661\n" + "Instruction: concat\n" + "Parameter One: 8689\n"
				+ "Parameter Two: 9255\n" + "Answer: 86899255\n" + "\n"
				+ "Destination URL: http://i2j.openode.io/answer/5508\n" + "Instruction: concat\n" + "Parameter One: 7907\n"
				+ "Parameter Two: 4401\n" + "Answer: 79074401\n" + "\n";
		assertEquals(expected, result);
	}
}