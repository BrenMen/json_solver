package uk.ac.uos.assignment;

public class Calculate {
	public String instruction;
	public String paramOne;
	public String paramTwo;
	public String response;

	public Calculate(String instruction, String paramOne, String paramTwo, String response) {
		this.instruction = instruction;
		this.paramOne = paramOne;
		this.paramTwo = paramTwo;
		this.response = response;
	}

	// Main method to call, returning the calculated sumAnswer.
	public String getSum() throws CustomException {
		String sumAnswer = "";
		if (instruction.equals("add")) {
			int additionSum = addValues(paramOne, paramTwo);
			sumAnswer = Integer.toString(additionSum);
		} else if (instruction.equals("multiply")) {
			int multiplicationSum = multiplyValues(paramOne, paramTwo);
			sumAnswer = Integer.toString(multiplicationSum);
		} else if (instruction.equals("concat")) {
			sumAnswer = concatenateValues(paramOne, paramTwo);
		} else {
			throw new CustomException("Error! Invalid instruction: " + instruction);
		}
		return sumAnswer;
	}

	public int multiplyValues(String numOne, String numTwo) {
		int resultOne = Integer.parseInt(numOne);
		int resultTwo = Integer.parseInt(numTwo);
		return resultOne * resultTwo;
	}
	
	public int addValues(String numOne, String numTwo) {
		int resultOne = Integer.parseInt(numOne);
		int resultTwo = Integer.parseInt(numTwo);
		return resultOne + resultTwo;
	}

	public String concatenateValues(String stringOne, String stringTwo) {
		String result = stringOne + stringTwo;
		return result;
	}
}