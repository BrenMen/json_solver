
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
		this.domainUrl = domainUrl;

		// Converting taskList into a list of answers and destination URL's.
		for (int i = 0; i < taskList.size(); i++) {
			Calculate jsonTask = taskList.get(i);
			String jsonURL = jsonTask.response;
			destinationUrls.add(jsonURL);
			String jsonAnswer = jsonTask.getSum();
			myAnswers.add(jsonAnswer);
		}
		
		for (int i = 0; i < taskList.size(); i++) {
			String thisURL = destinationUrls.get(i);
			String thisAnswer = myAnswers.get(i);

			String destination = "Destination Url: " + domainUrl + thisURL + "\n";
			String instruction = "Instruction: " + taskList.get(i).instruction + "\n";
			String parameterOne = "Parameter One: " + taskList.get(i).paramOne + "\n";
			String parameterTwo = "Parameter Two: " + taskList.get(i).paramTwo + "\n";
			String answer = "Answer: " + thisAnswer + "\n";
			output += destination + instruction + parameterOne + parameterTwo + answer;
			try {
				responseCode = sendAnswerToDestinationUrl(thisAnswer, thisURL);
			} catch(CustomException error) {
				output += error.getMessage() + "\n";
			}
			output += "Response code = " + responseCode + "\n\n";
		}
		return output;
	}
	
	// Connecting to server and writing answer to output.
	public int sendAnswerToDestinationUrl(String answer, String destinationUrl) throws IOException, CustomException {
		URL obj = new URL(domainUrl + destinationUrl); 
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestMethod("POST");
		DataOutputStream outputStream = new DataOutputStream(con.getOutputStream());
		outputStream.flush();
		outputStream.writeBytes(answer);
		outputStream.close();
		int responseCode = con.getResponseCode();
		if ( responseCode != 200 ) {
			throw new CustomException("Error! incorrect input or answer: " + answer);
		}
		return responseCode;
	}

}

//package uk.ac.uos.assignment;
//

//
//public class SendAnswer {


//
//	public String sendAnswer(String domainUrl, ArrayList<Calculate> taskList) throws CustomException, IOException {		
//		
//		// Converting taskList into a list of answers and destination URL's.
//		for (int i = 0; i < taskList.size(); i++) {
//			Calculate thisTask = taskList.get(i);
//			String thisAnswer = thisTask.getSum();
//			myAnswers.add(thisAnswer);
//			String thisURL = thisTask.response;
//			destinationUrls.add(thisURL);
//		}
//
//		// Sending every answer to the correct destination URL.
//		String output = "";
//		for (int i = 0; i < taskList.size(); i++) {
//			String destinationUrl = destinationUrls.get(i);
//			String currentAnswer = myAnswers.get(i);
//
//			// Filling my output file.
//			destination += domainUrl + destinationUrl + "\n";
//			instruction += taskList.get(i).instruction + "\n";
//			parameterOne += taskList.get(i).paramOne + "\n";
//			parameterTwo += taskList.get(i).paramTwo + "\n\n";
//			answer += currentAnswer + "\n";
//			int responseCode = 0;
//			try {
//				responseCode = sendAnswerToDestinationUrl(currentAnswer, destinationUrl);
//			} catch (CustomException error) {
//				output += error.getMessage() + "\n";
//			}
//			output += "Response code = " + responseCode + "\n\n";
//		}
//		return output;
//	}
//}