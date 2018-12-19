package uk.ac.uos.assignment;

import uk.ac.uos.assignment.CustomException;

public class Calculate {

	// Here are my variables for the four things a task can contain.
	public String instruction;
	public String paramOne;
	public String paramTwo;
	public String response;

	/**
	 * Each task has an instruction and a list of parameters and a response URL. I
	 * am then setting my class variables from the inputs.
	 * 
	 * @param instruction
	 * @param paramOne
	 * @param paramTwo
	 * @param response
	 */
	public Calculate(String instruction, String paramOne, String paramTwo, String response) {
		this.instruction = instruction;
		this.paramOne = paramOne;
		this.paramTwo = paramTwo;
		this.response = response;
	}

	/**
	 * This is the method to call to get the calculated task answer.
	 * 
	 * @return String
	 * @throws JSONException
	 */
	public String getAnswer() throws CustomException {
		String result = "";
		// This is an if block to do different actions depending on
		// what instruction is being given.
		if (instruction.equals("add")) {
			int addSum = add(paramOne, paramTwo);
			result = Integer.toString(addSum);
		} else if (instruction.equals("multiply")) {
			int multiplySum = multiply(paramOne, paramTwo);
			result = Integer.toString(multiplySum);
		} else if (instruction.equals("concat")) {
			result = concatenate(paramOne, paramTwo);
		} else {
			// If the instruction given is not one of the three above
			// then something is wrong so I throw an exception here.
			throw new CustomException("Error! Invalid instruction: " + instruction);
		}
		return result;
	}

	/**
	 * Add only has to take two parameters because I've check the tasks and there
	 * are only ever two parameters.
	 * 
	 * @param numberOne
	 * @param numberTwo
	 * @return int
	 */
	public int add(String numberOne, String numberTwo) {
		int numOne = Integer.parseInt(numberOne);
		int numTwo = Integer.parseInt(numberTwo);
		return numOne + numTwo;
	}

	/**
	 * Multiply only has to take two parameters because I've check the tasks and
	 * there are only ever two parameters.
	 * 
	 * @param numberOne
	 * @param numberTwo
	 * @return int
	 */
	public int multiply(String numberOne, String numberTwo) {
		int numOne = Integer.parseInt(numberOne);
		int numTwo = Integer.parseInt(numberTwo);
		return numOne * numTwo;
	}

	/**
	 * Concatenate only has to take two parameters because I've check the tasks and
	 * there are only ever two parameters.
	 * 
	 * @param stringOne
	 * @param string2
	 * @return String
	 */
	public String concatenate(String stringOne, String stringTwo) {
		return stringOne + stringTwo;
	}
}