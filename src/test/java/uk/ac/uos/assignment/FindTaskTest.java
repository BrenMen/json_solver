package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.io.IOException;

public class FindTaskTest {
	FindTask taskFinder;

	@Before
	public void setUp() throws Exception {
		taskFinder = new FindTask();
	}
	
	@Test
	public void correctTaskListFromURLTest() throws Exception {
		taskFinder.taskFinder("http://i2j.openode.io/student?id=s195215");
	}
	
	@Test
	public void correctRequestOfTaskListTest() throws Exception {
		String taskURL = taskFinder.getTaskList("http://i2j.openode.io/student?id=s195215");
		String result = "{\"id\":\"s195215\",\"tasks\":[\"/task/7518\",\"/task/5589\",\"/task/2386\",\"/task/3273\",\"/task/3383\",\"/task/7670\",\"/task/6661\",\"/task/5508\",\"/task/6160\",\"/task/4638\"]}";
		assertEquals(result, taskURL);
	}
	
	@Test
	public void correctIndividualTaskTest() throws IOException, CustomException {
		Calculate task = taskFinder.parseSpecificTask("{\"instruction\":\"multiply\",\"parameters\":[346,24],\"response URL\":\"/answer/8304\"}\n");
		String instruction = task.instruction;
		String paramOne = task.paramOne;
		String paramTwo = task.paramTwo;
		String responseURL = task.response;
		
		assertEquals("multiply", instruction);
		assertEquals("346", paramOne);
		assertEquals("24", paramTwo);
		assertEquals("/answer/8304", responseURL);
	}
	
	@Test
	public void correctTaskListTest() throws IOException, CustomException {
		taskFinder.studentNumber = "s195215";
		ArrayList<Object> listOfTasks = taskFinder.parseTaskList("{\"id\":\"s195215\",\"tasks\":[\"/task/3964\",\"/task/263\",\"/task/2393\",\"/task/3262\",\"/task/9768\",\"/task/279\",\"/task/2947\",\"/task/1968\",\"/task/9671\",\"/task/7241\"]}\n");
		String taskOne = (String) listOfTasks.get(0);
		String taskTwo = (String) listOfTasks.get(1);
		String taskThree = (String) listOfTasks.get(2);
		String taskFour = (String) listOfTasks.get(3);
		String taskFive = (String) listOfTasks.get(4);
		String taskSix = (String) listOfTasks.get(5);
		String taskSeven = (String) listOfTasks.get(6);
		String taskEight = (String) listOfTasks.get(7);
		String taskNine = (String) listOfTasks.get(8);
		String taskTen = (String) listOfTasks.get(9);
		
		assertEquals("/task/3964", taskOne);
		assertEquals("/task/263", taskTwo);
		assertEquals("/task/2393", taskThree);
		assertEquals("/task/3262", taskFour);
		assertEquals("/task/9768", taskFive);
		assertEquals("/task/279", taskSix);
		assertEquals("/task/2947", taskSeven);
		assertEquals("/task/1968", taskEight);
		assertEquals("/task/9671", taskNine);
		assertEquals("/task/7241", taskTen);
	}
	
	@Test
	public void correctTaskListCreationTest() throws Exception {
		taskFinder.taskFinder("http://i2j.openode.io/student?id=s195215");
		ArrayList<String> taskList = taskFinder.taskPathURLs;
		String taskOne = taskList.get(0);
		String taskTwo = taskList.get(1);
		String taskThree = taskList.get(2);
		String taskFour = taskList.get(3);
		String taskFive = taskList.get(4);
		String taskSix = taskList.get(5);
		String taskSeven = taskList.get(6);
		String taskEight = taskList.get(7);
		String taskNine = taskList.get(8);
		String taskTen = taskList.get(9);
		
		assertEquals("/task/7518", taskOne);
		assertEquals("/task/5589", taskTwo);
		assertEquals("/task/2386", taskThree);
		assertEquals("/task/3273", taskFour);
		assertEquals("/task/3383", taskFive);
		assertEquals("/task/7670", taskSix);
		assertEquals("/task/6661", taskSeven);
		assertEquals("/task/5508", taskEight);
		assertEquals("/task/6160", taskNine);
		assertEquals("/task/4638", taskTen);
	}
	
	@Test
	public void correctgetTaskTest() throws IOException, CustomException {
		String thisTask = taskFinder.getRequest("http://i2j.openode.io/task/7518");
		assertEquals("{\"instruction\":\"add\",\"parameters\":[7092,6937],\"response URL\":\"/answer/7518\"}", thisTask);
	}	

	@Test
	public void invalidTaskListTest() throws IOException {
		boolean exception = false;
		String customMessage = "";
		try {
			taskFinder.parseTaskList("{\"id\":\"s23\",\"tasks\":[\"/task/2863\",\"/task/5876\",\"/task/9335\",\"/task/289\",\"/task/3862\",\"/task/9261\",\"/task/9270\",\"/task/9276\",\"/task/3275\",\"/task/3223\"]}\n");
		} catch(CustomException error) {
			exception = true;
			customMessage = error.getMessage();
		}
		assertEquals(true, exception);
		assertEquals("Error! The tasks or ID are incorrect.", customMessage);
	}
	
	@Test
	public void invalidUserURLTest() {
		boolean exception = false;
		String customMessage = "";
		try {
			taskFinder.getTaskList("http://i2j.openode.io/student?id=s111111");
		} catch(Exception error) {
			exception = true;
			customMessage = error.getMessage();
		}
		assertEquals(true, exception);
		assertEquals("Error! Unable to connect to task: http://i2j.openode.io/student?id=s111111", customMessage);
	}
	
	@Test
	public void invalidTaskURLTest() throws IOException, CustomException {
		boolean exception = false;
		String customMessage = "";
		try {
			taskFinder.getRequest("http://i2j.openode.io/task/851359178341359");
		} catch(CustomException error) {
			exception = true;
			customMessage = error.getMessage();
		}
		assertEquals(true, exception);
		assertEquals("Error! Unable to connect to task: http://i2j.openode.io/task/851359178341359", customMessage);
	}
	
	@Test
	public void correctServerErrorCheckTest() throws IOException, CustomException {
		int result = taskFinder.serverErrorCheck("http://i2j.openode.io/task/6160", "Error! Invalid task");
		assertEquals(200, result);
	}
	
	@Test
	public void invalidServerErrorCheckTest() throws IOException, CustomException {
		boolean exception = false;
		String customMessage = "";
		try {
			taskFinder.serverErrorCheck("http://i2j.openode.io/task/9687235728", "Error! Invalid task");
		} catch(CustomException error) {
			exception = true;
			customMessage = error.getMessage();
		}
		assertEquals(true, exception);
		assertEquals("Error! Unable to connect to URL: http://i2j.openode.io/task/9687235728", customMessage);
	}
}