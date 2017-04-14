package engine;

import gameSettings.Letter;

public class Tile {
	public enum eTileState	{
		FACE_UP,
		FACE_DOWN
	}
	
	private eTileState state;
	private int score;
	private Letter letter;
	
	public int getScore() {
		return letter.getScore();
	}
	
	public eTileState getState() { 
		return state;
	}
	
	public char getSign() {
		return letter.getSign().get(0).charAt(0);
	}
	
	public void setLetter(Letter newLetter) {
		letter = newLetter;
	}
	
	public void setState(eTileState newState) {
		state = newState;
	}
	
	public Tile(Letter newLetter)	{
		state = eTileState.FACE_DOWN;
		letter = newLetter;
	}
}