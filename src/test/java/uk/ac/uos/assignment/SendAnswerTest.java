package uk.ac.uos.assignment;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SendAnswerTest {
	
	SendAnswer as;

	@Before
	public void setUp() throws Exception {
		as = new SendAnswer();
	}
	
	@Test
	public void testingAnOverallAnswerSendProcess() throws CustomException, IOException {
		Calculate task1 = new Calculate("add", "8054", "2119", "/answer/8473");
		ArrayList<Calculate> listOfTasks = new ArrayList<Calculate>();
		listOfTasks.add(task1);
		as.sendAnswer("http://i2j.openode.io", listOfTasks);
	}

}
