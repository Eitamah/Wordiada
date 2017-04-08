package engine;

import java.util.Random;

public class Dice {
	private final int MIN_VALUE = 2;
	private int sides;
	private Random random;
	
	public int getSides() {
		return sides;
	}
	
	public Dice(int numOfSides) {
		sides = numOfSides;
		random = new Random();
	}
	
	public int rollDice() {
		return random.nextInt(sides + MIN_VALUE);
	}
}
