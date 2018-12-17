package uk.ac.uos.assignment;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JSONProblemSolver {
	
	/**
	 * The main method of this program.
	 * Pass in the URL as the argument.
	 * @param args
	 * @return String
	 * @throws IOException
	 * @throws JSONException
	 */
	public String solve(String url) throws IOException, JSONException {
		//Creating instances of my finder and sender classes.
		TaskFinder taskFinder = new TaskFinder();
		AnswerSender answerSender = new AnswerSender();
		//Running a find for the tasks.
		taskFinder.runATaskFind(url);
		//Retrieving the tasks from the finder class.
		ArrayList<JSONTask> listOfTasks = taskFinder.listOfTasks;
		//Retrieving the base URL from the finder class.
		String baseUrl = taskFinder.baseUrl;
		//Send the answers back to the URLs.
		String finderReport = taskFinder.report;
		String answerReport = answerSender.runAnAnswerSend(baseUrl, listOfTasks);
		String report = "";
		report += finderReport;
		report += "\n\n";
		report += answerReport;
		//Saves a report
		writeReportToFile(report);
		//Returns the report so that this method can be tested.
		System.out.println(report);
		return report;
	}
	
	/**
	 * This method writes a report to a file.
	 * @param report
	 * @throws IOException 
	 */
	public void writeReportToFile(String report) throws IOException {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String stringDate = dateFormat.format(date);
		String filename = "JSON_Problem_Solver_Report_" + stringDate +".txt";
		File file = new File(filename);
		file.createNewFile();
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		String[] reportArray = report.split("\n");
		for (int i = 0; i < reportArray.length; i++) {
			writer.println(reportArray[i]);
		}
		writer.close();
	}
	
	

}
