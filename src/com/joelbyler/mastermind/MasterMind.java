package com.joelbyler.mastermind;

import java.util.Random;

public class MasterMind {

	private int[] numbers;
	private int numberOfGuesses = 0;
	
	public MasterMind(int maxGuesses) {
		numbers = new int[4];
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = generateRandomNumberBetween0and9();
		}
	}
	
	public int[] getNumber() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}
	
	private int generateRandomNumberBetween0and9() {
		return new Random().nextInt(10);
	}

	public GuessResult guess(int[] guess) {
		numberOfGuesses++;
		
		GuessResult guessResult = new GuessResult();

		// Ok, we'll let them keep guessing, but make it harder for them
		//if(numberOfGuesses > maxNumberOfGuesses) return guessResult;
		
		guessResult.setCorrectNumbers(determineCorrectNumbers(guess));

		int correctPositions = 0;
		for (int i = 0; i < guess.length; i++) {
			if (numbers[i] == guess[i]) correctPositions++;
		}
		guessResult.setCorrectPositions(correctPositions);
		
		guessResult.setCorrect(determineCorrectNumbers(guess) == 4 && correctPositions == 4);
		
		return guessResult;
	}

	public int getNumberOfGuesses() {
		return numberOfGuesses;
	}

	private int determineCorrectNumbers(int[] guess) {
		int correctNumbers = 0;
		int[] tempNumbers = numbers.clone();
		int[] tempGuess = guess.clone();
		for (int j = 0; j < tempGuess.length; j++) {
			int currentGuess = tempGuess[j];
			boolean foundMatch = false;
			for (int i = 0; i < tempNumbers.length; i++) {
				int currentNumber = tempNumbers[i];
				if (currentNumber == currentGuess && !foundMatch) {
					correctNumbers++;
					tempNumbers[i] = -1;
					foundMatch = true;
				}
			}
		}
		
		
		
		
		
		return correctNumbers;
	}

}
