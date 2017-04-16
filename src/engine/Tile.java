package engine;

import java.awt.Point;
import java.io.Serializable;

import gameSettings.Letter;

public class Tile implements Serializable{
	public enum eTileState 	{
		FACE_UP,
		FACE_DOWN
	}
	
	private Point coord;
	private eTileState state;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coord == null) ? 0 : coord.hashCode());
		result = prime * result + ((letter == null) ? 0 : letter.hashCode());
		result = prime * result + score;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tile other = (Tile) obj;
		if (coord == null) {
			if (other.coord != null)
				return false;
		} else if (!coord.equals(other.coord))
			return false;
		if (letter == null) {
			if (other.letter != null)
				return false;
		} else if (!letter.equals(other.letter))
			return false;
		if (score != other.score)
			return false;
		if (state != other.state)
			return false;
		return true;
	}

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
	
	public Tile(Letter newLetter, int x, int y)	{
		state = eTileState.FACE_DOWN;
		letter = newLetter;
		coord = new Point(x, y);
	}
}