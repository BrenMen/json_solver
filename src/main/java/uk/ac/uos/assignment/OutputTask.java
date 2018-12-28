package uk.ac.uos.assignment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

public class OutputTask {

	/**
	 * Outputs a CSV file of all gathered information from the Solver.
	 * @param taskUrl
	 * @return output
	 * @throws CustomException
	 * @throws IOException
	 */
	public String formatOutput(String taskUrl) throws CustomException, IOException {
		String output = "";
		SendAnswer sendAnswer = new SendAnswer();
		FindTask findTask = new FindTask();
		findTask.taskFinder(taskUrl);
		// Constructing the URL for each task to output.
		String domainUrl = findTask.taskDomainURL;
		String findTaskOutput = findTask.output;
		String findSendOutput = sendAnswer.sendAnswer(domainUrl, findTask.tasksList);
		output += findTaskOutput + "\n\n" + findSendOutput;
		// Exporting the output in a text file.
		String date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		String[] outputLines = output.split("\n");
		String filename = "Solver_Report_" + date +".txt";
		new File(filename).createNewFile();
		PrintWriter printToFile = new PrintWriter(filename, "UTF-8");
		for (int i = 0; i < outputLines.length; i++) {
			printToFile.println(outputLines[i]);
		}
		printToFile.close();
		System.out.println(output);
		return output;
	}
}