package uk.ac.uos.assignment;

import java.util.ArrayList;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;

public class TaskFinder {

	// Initialising my JSON Parser to be used.
	JSONParser jsonParser = new JSONParser();

	// Task Path URL's to locate the tasksList for completing individual tasks.
	ArrayList<String> taskPathURLs = new ArrayList<String>();
	ArrayList<Calculate> tasksList = new ArrayList<Calculate>();

	// The domain URL and student Number. Needed for accessing individual tasks.
	public String taskDomainURL;
	public String studentNumber;

	// Program output once tasks are completed.
	public String output = "";

	// Requesting the information to collect tasksLists and taskURL's.
	public void taskFinder(String taskInput) throws IOException, CustomException {
		
		// Converting Objects to Strings and creating taskList.
		String taskList = getTaskList(taskInput);
		ArrayList<String> stringTasks = new ArrayList<String>();
		ArrayList<Object> objectTasks = parseTaskList(taskList);
		for (int i = 0; i < objectTasks.size(); i++) {
			String thisTask = (String) objectTasks.get(i);
			stringTasks.add(thisTask);
		}
		this.taskPathURLs = stringTasks;
		output += "Tasks list:\n";
		
		// Parsing each of the taskPathURLs.
		for (int i = 0; i < taskPathURLs.size(); i++) {
			String taskURL = taskDomainURL + taskPathURLs.get(i);
			String taskInstructions = getSpecificTask(taskURL);
			taskErrorCheck(taskInstructions, taskURL);
			}
	}		
		
	// Finding all the individual task URL's to make the taskList.
	public String getTaskList(String taskInput) throws IOException, CustomException {
		String[] urlSplit = taskInput.split("id=");
		taskDomainURL = urlSplit[0].replace("/student?", "");
		studentNumber = urlSplit[1];

		// Making URL Connection for receiving tasks.
		String tasks = getRequest(taskInput);
		output += "Tasks received from the domain URL:\n";
		output += tasks + "\n";
		return tasks;
	}
	
	// Processing the taskList into readable Objects.
	public ArrayList<Object> parseTaskList(String taskList) throws IOException, CustomException {
		jsonParser.parse(taskList);
		JSONObject parseJSONObject = jsonParser.mainObject;
		JSONArray parseJSONArray = (JSONArray) parseJSONObject.thisObjectContents.get("tasks");
		String id = (String) parseJSONObject.thisObjectContents.get("id");
		boolean isCorrectList = id.equals(studentNumber) ? true : false;
		if (isCorrectList == false) {
			throw new CustomException("Error! These are incorrect tasks for this ID.");
		}
		return (ArrayList<Object>) parseJSONArray.thisArrayContents;

	}
	
	// Making an URL Connection for receiving tasks.
	public String getSpecificTask(String taskInput) throws IOException, CustomException {
		String tasks = getRequest(taskInput);
		output += tasks;
		output += "\n";
		return tasks;
	}
	
	// Processing the specific task to be accepted by the Calculator class. 
	public Calculate parseSpecificTask(String taskList) throws IOException, CustomException {
		jsonParser.parse(taskList);

		// Parsing all fields needed to answer request.
		JSONObject parseJSONObject = jsonParser.mainObject;
		JSONArray parseJSONArray = (JSONArray) parseJSONObject.thisObjectContents.get("parameters");
		String parseJSONInstruction = (String) parseJSONObject.thisObjectContents.get("instruction");
		String paramStringOne = "";
		String paramStringTwo = "";
		Object paramOne = parseJSONArray.thisArrayContents.get(0);
		if (paramOne instanceof String) {
			paramStringOne = (String) paramOne;
		} else if (paramOne instanceof Long) {
			paramStringOne = paramOne.toString();
		} else {
			throw new CustomException("Error! This is an invalid parameter: " + paramOne);
		}
		Object paramTwo = parseJSONArray.thisArrayContents.get(1);
		if (paramTwo instanceof String) {
			paramStringTwo = (String) paramTwo;
		} else if (paramTwo instanceof Long) {
			paramStringTwo = paramTwo.toString();
		} else {
			throw new CustomException("Error! This is an invalid parameter: " + paramTwo);
		}
		String senderURL = (String) parseJSONObject.thisObjectContents.get("response URL");

		// Calling Calculate class to process the given information.
		return new Calculate(parseJSONInstruction, paramStringOne, paramStringTwo, senderURL);
	}	
	
	// Requesting server connection to receive taskURL.
	public String getRequest(String taskInput) throws IOException, CustomException {
		URL obj = new URL(taskInput);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		if (con.getResponseCode() != 200) {
			throw new CustomException("Error! Unable to connect to task: " + taskInput);
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String thisTask = "";
		String taskList = "";
		while ((thisTask = reader.readLine()) != null) {
			taskList += thisTask;
		}
		reader.close();
		output += "task output:\n";
		output += taskList + "\n";
		return taskList;
	}
	
	// Checking taskList from the domainURL for errors.
	public int serverErrorCheck(String taskInput, String errorMessage) throws IOException, CustomException {
		URL obj = new URL(taskInput);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestMethod("POST");
		DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
		outputStream.writeBytes(errorMessage);
		outputStream.flush();
		outputStream.close();
		int responseCode = con.getResponseCode();
		if (responseCode != 200) {
			throw new CustomException("Error! Unable to connect to URL: " + obj);
		}
		return responseCode;
	}
	
	// Checking individual tasks for errors.
	public void taskErrorCheck(String taskInstructions, String taskURL) throws IOException, CustomException {
		boolean isParseFailure = false;
		Calculate thisTask = null;
		try {
			thisTask = parseSpecificTask(taskInstructions);
			thisTask.getAnswer();
		} catch (CustomException error) {
			int validTaskURL = serverErrorCheck(taskURL, error.getMessage());
			output += "Error message: " + error.getMessage() + "\n";
			output += "Invalid task: " + validTaskURL + "\n";
			output += "Error for task: " + taskURL + "\n";
			isParseFailure = true;
		}
		if (isParseFailure == false) {
			tasksList.add(thisTask);
		}
	}
}