
package uk.ac.uos.assignment;

import uk.ac.uos.assignment.Calculate;
import java.io.IOException;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendAnswer {
	
	// The domain part of the URL which is needed for all tasks.
	public String domainUrl;
	
	// A list of my answers ready to be sent off.
	ArrayList<String> myAnswers = new ArrayList<String>();

	// Destination of URL's my answers need to be sent to.
	ArrayList<String> destinationUrls = new ArrayList<String>();

	public String sendAnswer(String domainUrl, ArrayList<Calculate> taskList) throws CustomException, IOException {
		String output = "";
		int responseCode = 0;
		
		// Converting taskList into a list of answers and destination URL's.
		for (int i = 0; i < taskList.size(); i++) {
			Calculate jsonTask = taskList.get(i);
			destinationUrls.add(jsonTask.response);
			myAnswers.add(jsonTask.getSum());
		}
		for (int i = 0; i < taskList.size(); i++) {
			String thisUrl = destinationUrls.get(i);
			String thisAnswer = myAnswers.get(i);
			
			// Connecting to server and writing answer to output.
			try {
				URL obj = new URL(domainUrl + thisUrl); 
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
				con.setDoOutput(true);
				con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				con.setRequestMethod("POST");
				DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
				outputStream.flush();
				outputStream.writeBytes(thisAnswer);
				outputStream.close();
				responseCode = con.getResponseCode();
				if ( responseCode != 200 ) {
					throw new CustomException("Error! incorrect input or answer: " + thisAnswer);
				}
			} catch(CustomException error) {
				output += error.getMessage() + "\n";
			}
			String destination = "Destination URL: " + domainUrl + thisUrl + "\n";
			String instruction = "Instruction: " + taskList.get(i).instruction + "\n";
			String parameterOne = "Parameter One: " + taskList.get(i).paramOne + "\n";
			String parameterTwo = "Parameter Two: " + taskList.get(i).paramTwo + "\n";
			String answer = "Answer: " + thisAnswer + "\n\n";
			output += destination + instruction + parameterOne + parameterTwo + answer;
		}
		return output;
	}
}