package com.joelbyler.mastermind;

public class GuessResult {

	private boolean correct = false;
	private int correctPositions = 0;
	private int correctNumbers = 0;
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public void setCorrectPositions(int correctPositions) {
		this.correctPositions = correctPositions;
	}

	public void setCorrectNumbers(int correctNumbers) {
		this.correctNumbers = correctNumbers;
	}

	public boolean isCorrect() {
		return correct;
	}

	public int getCorrectPositions() {
		return correctPositions;
	}

	public int getCorrectNumbers() {
		return correctNumbers;
	}
}
