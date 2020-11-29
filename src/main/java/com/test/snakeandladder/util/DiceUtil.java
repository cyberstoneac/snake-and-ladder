package com.test.snakeandladder.util;

import java.util.Random;

import com.test.snakeandladder.constants.DiceType;

public class DiceUtil {
	public static int roll(DiceType type) {
		if (DiceType.NORMAL.equals(type)) {
			return new Random().nextInt(6) + 1;
		}
		return returnCrookedValue();
	}

	private static int returnCrookedValue() {
		Random random = new Random();
		int number = random.nextInt(6) + 1;
		while (!evenNumber(number)) {
			number = random.nextInt(6) + 1;
		}
		return number;
	}

	private static boolean evenNumber(int number) {
		return (number % 2) == 0;
	}
}
