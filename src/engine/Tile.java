package engine;

public class Tile {
	public enum eTileState	{
		FACE_UP,
		FACE_DOWN
	}
	
	private eTileState state;
	private int score;
	private char sign;
	
	public int getScore() {
		return score;
	}
	
	public eTileState getState() { 
		return state;
	}
	
	public char getSign() {
		return sign;
	}
	
	public void setSign(char newSign, int newScore) {
		sign = newSign;
		score = newScore;
	}
	
	public void setState(eTileState newState) {
		state = newState;
	}
	
	public Tile(char sign, int points)	{
		state = eTileState.FACE_DOWN;
		score = points;
	}
}