package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.io.IOException;


public class TaskFinderTest {
	TaskFinder taskFinder;

	@Before
	public void setUp() throws Exception {
		taskFinder = new TaskFinder();
	}
	
	@Test
	public void taskFromJSONTest() throws IOException, CustomException {
		Calculate task = taskFinder.parseSpecificTask("{\"instruction\":\"multiply\",\"parameters\":[346,24],\"response URL\":\"/answer/8304\"}\n");
		
		String instruction = task.instruction;
		String parameter1 = task.param1;
		String parameter2 = task.parameter2;
		String responseURL = task.responseURL;
		
		assertEquals("add", instruction);
		assertEquals("8054", parameter1);
		assertEquals("2119", parameter2);
		assertEquals("/answer/8473", responseURL);
	}
	
	@Test
	public void accuratelyParsingATaskJSONPage() throws IOException, CustomException {
		taskFinder.studentNumber = "s188216";
		ArrayList<Object> listOfTasks = taskFinder.parseTaskList("{\"id\":\"s188216\",\"tasks\":[\"/task/8473\",\"/task/7225\",\"/task/983\",\"/task/4963\",\"/task/8413\",\"/task/2915\",\"/task/9946\",\"/task/1621\",\"/task/6432\",\"/task/1090\"]}\n");
		String task1 = (String) listOfTasks.get(0);
		String task2 = (String) listOfTasks.get(1);
		String task3 = (String) listOfTasks.get(2);
		String task4 = (String) listOfTasks.get(3);
		String task5 = (String) listOfTasks.get(4);
		String task6 = (String) listOfTasks.get(5);
		String task7 = (String) listOfTasks.get(6);
		String task8 = (String) listOfTasks.get(7);
		String task9 = (String) listOfTasks.get(8);
		String task10 = (String) listOfTasks.get(9);
		
		assertEquals("/task/8473", task1);
		assertEquals("/task/7225", task2);
		assertEquals("/task/983", task3);
		assertEquals("/task/4963", task4);
		assertEquals("/task/8413", task5);
		assertEquals("/task/2915", task6);
		assertEquals("/task/9946", task7);
		assertEquals("/task/1621", task8);
		assertEquals("/task/6432", task9);
		assertEquals("/task/1090", task10);
	}
	
	@Test
	public void parsingTaskJSONWithIncorrectID() throws IOException {
		boolean thrown = false;
		String message = "";
		
		try {
			taskFinder.parseTaskList("{\"id\":\"s9\",\"tasks\":[\"/task/8473\",\"/task/7225\",\"/task/983\",\"/task/4963\",\"/task/8413\",\"/task/2915\",\"/task/9946\",\"/task/1621\",\"/task/6432\",\"/task/1090\"]}\n");
		} catch(CustomException e) {
			thrown = true;
			message = e.getMessage();
		}
		
		assertEquals(true, thrown);
		assertEquals("Error! These are incorrect tasks for this ID.", message);
	}
	
	@Test
	public void gettingMyTaskJSONCorrectly() throws Exception {
		String taskJSON = taskFinder.getTaskList("http://i2j.openode.io/student?id=s188216");
		String expected = "{\"id\":\"s188216\",\"tasks\":[\"/task/8473\",\"/task/7225\",\"/task/983\",\"/task/4963\",\"/task/8413\",\"/task/2915\",\"/task/9946\",\"/task/1621\",\"/task/6432\",\"/task/1090\"]}";
		assertEquals(expected, taskJSON);
	}
	
	@Test
	public void connectingToWrongURL() {
		boolean thrown = false;
		String message = "";
		try {
			taskFinder.getTaskList("http://i2j.openode.io/student?id=s123456");
		} catch(Exception e) {
			thrown = true;
			message = e.getMessage();
		}
		assertEquals(true, thrown);
		assertEquals("Error! Unable to connect to task: http://i2j.openode.io/student?id=s123456", message);
	}
	
	@Test
	public void generatingTheListOfTasks() throws Exception {
		taskFinder.taskFinder("http://i2j.openode.io/student?id=s188216");
		ArrayList<String> listOfTasks = taskFinder.taskPathURLs;
		String task1 = listOfTasks.get(0);
		String task2 = listOfTasks.get(1);
		String task3 = listOfTasks.get(2);
		String task4 = listOfTasks.get(3);
		String task5 = listOfTasks.get(4);
		String task6 = listOfTasks.get(5);
		String task7 = listOfTasks.get(6);
		String task8 = listOfTasks.get(7);
		String task9 = listOfTasks.get(8);
		String task10 = listOfTasks.get(9);
		
		assertEquals("/task/8473", task1);
		assertEquals("/task/7225", task2);
		assertEquals("/task/983", task3);
		assertEquals("/task/4963", task4);
		assertEquals("/task/8413", task5);
		assertEquals("/task/2915", task6);
		assertEquals("/task/9946", task7);
		assertEquals("/task/1621", task8);
		assertEquals("/task/6432", task9);
		assertEquals("/task/1090", task10);
	}
	
	@Test
	public void gettingTheJSONForATask() throws IOException, CustomException {
		String taskJSON = taskFinder.getSpecificTask("http://i2j.openode.io/task/8473");
		assertEquals("{\"instruction\":\"add\",\"parameters\":[8054,2119],\"response URL\":\"/answer/8473\"}", taskJSON);
	}
	
	@Test
	public void gettingTheJSONForATaskWithIncorrectURL() throws IOException, CustomException {
		boolean thrown = false;
		String message = "";
		try {
			taskFinder.getSpecificTask("http://i2j.openode.io/task/847399999999");
		} catch(CustomException e) {
			thrown = true;
			message = e.getMessage();
		}
		assertEquals(true, thrown);
		assertEquals("Error! Unable to connect to task: http://i2j.openode.io/task/847399999999", message);
	}
	
	@Test
	public void returningErrorMessageToCorrectURL() throws IOException, CustomException {
		int result = taskFinder.serverErrorCheck("http://i2j.openode.io/task/6432", "Boom! Error. Invalid JSON.");
		assertEquals(200, result);
	}
	
	@Test
	public void returningErrorMessageToIncorrectURLThrowsException() throws IOException, CustomException {
		boolean thrown = false;
		String message = "";
		try {
			taskFinder.serverErrorCheck("http://i2j.openode.io/task/84731111", "Boom! Error. Invalid JSON.");
		} catch(CustomException e) {
			thrown = true;
			message = e.getMessage();
		}
		assertEquals(true, thrown);
		assertEquals("Error! Unable to connect to URL: http://i2j.openode.io/task/84731111", message);
	}
	
	@Test
	public void buildingASetOfTasksFromURL() throws Exception {
		taskFinder.taskFinder("http://i2j.openode.io/student?id=s188216");
	}

}
