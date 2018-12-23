package uk.ac.uos.assignment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OutputTask {

	public String formatOutput(String taskUrl) throws CustomException, IOException {
		String output = "";
		FindTask findTask = new FindTask();
		ArrayList<Calculate> taskList = findTask.tasksList;
		SendAnswer sendAnswer = new SendAnswer();
		findTask.taskFinder(taskUrl);
		
		// Constructing the URL for each task to output.
		String domainUrl = findTask.taskDomainURL;
		String findTaskOutput = findTask.output;
		String findSendOutput = sendAnswer.sendAnswer(domainUrl, taskList);
		output += findTaskOutput + "\n\n" + findSendOutput;
		saveToFile(output);
		System.out.println(output);
		return output;
	}
	
	public void saveToFile(String output) throws IOException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = dateFormat.format(date);
		String filename = "JSON_Problem_Solver_Report_" + stringDate +".txt";
		File file = new File(filename);
		file.createNewFile();
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		String[] reportArray = output.split("\n");
		for (int i = 0; i < reportArray.length; i++) {
			writer.println(reportArray[i]);
		}
		writer.close();
	}
}