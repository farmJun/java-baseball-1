package baseball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class NumberManager {
	public ThreeDigitNumber generateRandomNumber() {
		List<Integer> digitList = new ArrayList<>();

		while (digitList.size() < Number.NUMBER_OF_DIGITS.get()) {
			int randomDigit = Randoms.pickNumberInRange(Number.START_NUMBER_RANGE.get(), Number.END_NUMBER_RANGE.get());

			if (!digitList.contains(randomDigit)) {
				digitList.add(randomDigit);
			}
		}

		ThreeDigitNumber randomNumber = new ThreeDigitNumber(digitList);
		return randomNumber;
	}

	public ThreeDigitNumber getUserGuessNumber() {
		System.out.print(Message.GUESS_NUMBER_REQUEST_MESSAGE);
		String userInput = Console.readLine();
		validateUserInput(userInput);
		ThreeDigitNumber userGuessNumber = new ThreeDigitNumber(Integer.parseInt(userInput));
		return userGuessNumber;
	}

	public void validateUserInput(String userInput) {
		if (userInput.length() != Number.NUMBER_OF_DIGITS.get()) {
			throw new IllegalArgumentException();
		}

		if (userInput.contains(Integer.toString(Number.ZERO.get()))) {
			throw new IllegalArgumentException();
		}

		if (!userInput.matches(RegexPattern.IS_NUMBER.getRegexPattern())) {
			throw new IllegalArgumentException();
		}

		Set<Character> digitSet = new HashSet<>();
		for (char digit : userInput.toCharArray()) {
			if (!Character.isDigit(digit)) {
				throw new IllegalArgumentException();
			}
			digitSet.add(digit);
		}

		if (digitSet.size() != Number.NUMBER_OF_DIGITS.get()) {
			throw new IllegalArgumentException();
		}
	}

	public Score calculateStrikeBallCount(ThreeDigitNumber randomNumber, ThreeDigitNumber userGuessNumber) {
		int strikeCount = Number.INIT_STRIKE_COUNT.get();
		int ballCount = Number.INIT_BALL_COUNT.get();
		String randomNumberString = String.valueOf(randomNumber.getThreeDigitNumber());
		String userGuessNumberString = String.valueOf(userGuessNumber.getThreeDigitNumber());

		for (int numberIndex = Number.ZERO.get();
			 numberIndex < Number.NUMBER_OF_DIGITS.get(); numberIndex++) {
			if (randomNumberString.charAt(numberIndex) == userGuessNumberString.charAt(numberIndex)) {
				strikeCount++;
			}

			String guessDigit = String.valueOf(userGuessNumberString.charAt(numberIndex));

			if (randomNumberString.contains(guessDigit) && randomNumberString.indexOf(guessDigit) != numberIndex) {
				ballCount++;
			}
		}

		Score score = new Score(strikeCount, ballCount);
		return score;
	}
}
