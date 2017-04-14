package engine;

import java.awt.Point;
import java.util.List;


import gameSettings.Player;

public class Turn {
	private Player player;
	private boolean isValid;
	private String word = "";
	private List<Point> points;
	
	public Turn(Player p, boolean valid, String text, List<Point> ps) {
		player = p;
		isValid = valid;
		word = text;
		points = ps;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean getIsValid() {
		return isValid;
	}
	
	public String getWord() {
		return word;
	}
	
	public List<Point> getPoints() {
		return points;
	}
}
