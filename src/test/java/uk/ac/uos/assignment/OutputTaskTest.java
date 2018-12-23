package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class OutputTaskTest {

	OutputTask jps;

	@Before
	public void setUp() throws Exception {
		jps = new OutputTask();
	}

	@Test
	public void testingSaveToFile() throws IOException {
		jps.saveToFile("Test line");
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = dateFormat.format(date);
		String filename = "JSON_Problem_Solver_Report_" + stringDate +".txt";
		File file = new File(filename);
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String result = "";
		String currentLine;
		while ((currentLine = bufferedReader.readLine()) != null) {
			result += currentLine;
		}
		assertEquals("Test line", result);
		file.delete();
		bufferedReader.close();
	}
	
	@Test
	public void testingTheWholeProcess() throws IOException, CustomException {
		String answer = jps.formatOutput("http://i2j.openode.io/student?id=s195215");
//		System.out.println(answer);
		String expected = "JSON from main url:\n" + 
				"{\"id\":\"s195215\",\"tasks\":[\"/task/7518\",\"/task/5589\",\"/task/2386\",\"/task/3273\",\"/task/3383\",\"/task/7670\",\"/task/6661\",\"/task/5508\",\"/task/6160\",\"/task/4638\"]}\n" + 
				"\n" + 
				"JSON of Tasks:\n" + 
				"{\"instruction\":\"add\",\"parameters\":[7092,6937],\"response URL\":\"/answer/7518\"}\n" + 
				"{\"instruction\":\"multiply\",\"parameters\":[4500,5245],\"response URL\":\"/answer/5589\"}\n" + 
				"{\"instruction\":\"concat\",\"parameters\":[\"1916\",\"6575\"],\"response URL\":\"/answer/2386\"}\n" + 
				"{\"instruction\":\"add\",\"parameters\":[473,\"594\"],\"response URL\":\"/answer/3273\"}\n" + 
				"{\"instruction\":\"concat\",\"parameters\":[\"2207\",9785],\"response URL\":\"/answer/3383\"}\n" + 
				"{\"instruction\":\"multiply\",\"parameters\":[\"7682\",\"2325\"],\"response URL\":\"/answer/7670\"}\n" + 
				"{\"instruction\":\"concat\",\"parameters\":[8689,\"9255\"],\"response URL\":\"/answer/6661\"}\n" + 
				"{\"instruction\":\"concat\",\"parameters\":[\"7907\",4401],\"response URL\":\"/answer/5508\"}\n" + 
				"{\"instruction\":\"subtract\",\"parameters\":[4918,7706],\"response URL\":\"/answer/6160\"}\n" + 
				"Error for http://i2j.openode.io/task/6160\n" + 
				"Error Message = Error! Unexpected instruction subtract\n" + 
				"Return Code = 200\n" + 
				"\"add up the two numbers 8182 and 6931\"\n" + 
				"Error for http://i2j.openode.io/task/4638\n" + 
				"Error Message = Error! This is an invalid object.\n" + 
				"Return Code = 200\n" + 
				"\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/7518\n" + 
				"Instruction = add\n" + 
				"First parameter = 7092\n" + 
				"Second parameter = 6937\n" + 
				"Answer = 14029\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/7225\n" + 
				"Instruction = multiply\n" + 
				"First parameter = 7985\n" + 
				"Second parameter = 1357\n" + 
				"Answer = 10835645\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/983\n" + 
				"Instruction = concat\n" + 
				"First parameter = 7803\n" + 
				"Second parameter = 6137\n" + 
				"Answer = 78036137\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/4963\n" + 
				"Instruction = add\n" + 
				"First parameter = 6884\n" + 
				"Second parameter = 8905\n" + 
				"Answer = 15789\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/8413\n" + 
				"Instruction = add\n" + 
				"First parameter = 6251\n" + 
				"Second parameter = 5945\n" + 
				"Answer = 12196\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/2915\n" + 
				"Instruction = multiply\n" + 
				"First parameter = 4415\n" + 
				"Second parameter = 7506\n" + 
				"Answer = 33138990\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/9946\n" + 
				"Instruction = concat\n" + 
				"First parameter = 4786\n" + 
				"Second parameter = 3522\n" + 
				"Answer = 47863522\n" + 
				"Return code = 200\n" + 
				"\n" + 
				"URL = http://i2j.openode.io/answer/1621\n" + 
				"Instruction = concat\n" + 
				"First parameter = 8208\n" + 
				"Second parameter = 2089\n" + 
				"Answer = 82082089\n" + 
				"Return code = 200\n" + 
				"\n";
		assertEquals(expected, answer);
	}
}