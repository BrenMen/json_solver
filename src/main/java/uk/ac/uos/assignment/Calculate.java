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

	public int multiplyValues(String paramOne, String paramTwo) {
		return Integer.parseInt(paramOne) * Integer.parseInt(paramTwo);
	}
	
	public int addValues(String paramOne, String paramTwo) {
		return Integer.parseInt(paramOne) + Integer.parseInt(paramTwo);
	}

	public String concatenateValues(String paramOne, String paramTwo) {
		return paramOne + paramTwo;
	}
}