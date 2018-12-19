package uk.ac.uos.assignment;

import uk.ac.uos.assignment.CustomException;

public class Calculate {
	
	//Here are my variables for the four things a task can contain.
	public String instruction;
	public String parameter1;
	public String parameter2;
	public String responseURL;
	

	/**
	 * 	Each task has an instruction and a list of parameters and a response URL.
	 *  I am then setting my class variables from the inputs.
	 * @param instruction
	 * @param parameter1
	 * @param parameter2
	 * @param responseURL
	 */
	public Calculate(String instruction, String parameter1, String parameter2, String responseURL) {
		this.instruction = instruction;
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.responseURL = responseURL;
	}
	
	/**
	 * This is the method to call to get the calculated task answer.
	 * @return String
	 * @throws JSONException
	 */
	public String getAnswer() throws CustomException {
		String result = "";
		//This is an if block to do different actions depending on
		//what instruction is being given.
		if ( instruction.equals("add") ) {
			int addResult = add(parameter1, parameter2);
			result = Integer.toString(addResult);
		} else if ( instruction.equals("multiply") ) {
			int multiplyResult = multiply(parameter1, parameter2);
			result = Integer.toString(multiplyResult);
		} else if ( instruction.equals("concat") ) {
			result = concatenate(parameter1, parameter2);
		} else {
			//If the instruction given is not one of the three above
			//then something is wrong so I throw an exception here.
			throw new CustomException("Boom! Error. Unexpected instruction " + instruction);
		}
		return result;
	}
	
	/**
	 * Add only has to take two parameters because I've check the tasks and there are only ever two parameters.
	 * @param number1
	 * @param number2
	 * @return int
	 */
	public int add(String number1, String number2) {
		int num1 = Integer.parseInt(number1);
		int num2 = Integer.parseInt(number2);
		return num1 + num2;
	}
	
	/**
	 * Multiply only has to take two parameters because I've check the tasks and there are only ever two parameters.
	 * @param number1
	 * @param number2
	 * @return int
	 */
	public int multiply(String number1, String number2) {
		int num1 = Integer.parseInt(number1);
		int num2 = Integer.parseInt(number2);
		return num1 * num2;
	}
	
	/**
	 * Concatenate only has to take two parameters because I've check the tasks and there are only ever two parameters.
	 * @param string1
	 * @param string2
	 * @return String
	 */
	public String concatenate(String string1, String string2) {
		return string1 + string2;
	}
	
}
