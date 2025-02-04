package baseball;

import java.util.Queue;

import camp.nextstep.edu.missionutils.Console;

public class BaseballGame {

	public void startGame() {
		while (true) {
			proceedGame();
			if (repeatGame()) {
				continue;
			}
			break;
		}
	}

	public void proceedGame() {
		ThreeDigitNumber randomNumber = NumberManager.generateRandomNumber();
		while (true) {
			ThreeDigitNumber userGuessNumber = NumberManager.getUserGuessNumber();
			Score score = NumberManager.calculateScore(randomNumber, userGuessNumber);
			printHint(score);

			if (randomNumber.equals(userGuessNumber)) {
				break;
			}
		}

		System.out.println(Message.ALL_CORRECT_MESSAGE);
	}

	public boolean repeatGame() {
		System.out.println(Message.REPEAT_MESSAGE);
		String userInput = Console.readLine();
		Validator.validateIsRepeat(userInput);
		int isRepeat = Integer.parseInt(userInput);

		if (isRepeat == Number.IS_NOT_REPEAT.get()) {
			return false;
		}
		return true;
	}

	public void printHint(Score score) {
		StringBuilder hint = new StringBuilder();
		int strikeCount = score.getStrikeCount();
		int ballCount = score.getBallCount();

		if (ballCount != Number.ZERO.get()) {
			hint.append(ballCount).append(Message.BALL_MESSAGE);
		}

		if (strikeCount != Number.ZERO.get()) {
			hint.append(strikeCount).append(Message.STRIKE_MESSAGE);
		}

		if (ballCount == Number.ZERO.get() && strikeCount == Number.ZERO.get()) {
			hint.append(Message.NOTHING_MESSAGE);
		}

		System.out.println(hint);
	}
}
