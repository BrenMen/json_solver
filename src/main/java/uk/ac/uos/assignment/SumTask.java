package uk.ac.uos.assignment;

public class SumTask {
	public String instruction;
	public String paramOne;
	public String paramTwo;
	public String response;

	/**
	 * Main method to call, returning the calculated sumAnswer.
	 * @return sumAnswer
	 * @throws CustomException
	 */
	public String calculate() throws CustomException {
		String sumAnswer = "";
		if (instruction.equals("multiply")) {
			sumAnswer = Integer.toString(multiplyValues(paramOne, paramTwo));
		} else if (instruction.equals("add")) {
			sumAnswer = Integer.toString(addValues(paramOne, paramTwo));
		} else if (instruction.equals("concat")) {
			sumAnswer = paramOne + paramTwo;
		} else {
			throw new CustomException("Error! Invalid instruction: " + instruction);
		}
		return sumAnswer;
	}
	
	public SumTask(String instruction, String paramOne, String paramTwo, String response) {
		this.instruction = instruction;
		this.paramOne = paramOne;
		this.paramTwo = paramTwo;
		this.response = response;
	}
	
	public int multiplyValues(String paramOne, String paramTwo) {
		return Integer.parseInt(paramOne) * Integer.parseInt(paramTwo);
	}
	
	public int addValues(String paramOne, String paramTwo) {
		return Integer.parseInt(paramOne) + Integer.parseInt(paramTwo);
	}
}