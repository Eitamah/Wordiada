package engine;

import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.xml.bind.JAXBException;

public class GameManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	
	public GameManager() {
	}
	
	public void loadGame(String xmlPath) throws FileNotFoundException, JAXBException, 
											IllegalArgumentException, IllegalStateException {
		game = new Game();
		game.LoadSettings(xmlPath);		
	}
	
	public void startGame() { 
		game.startGame();
	}
	
	public Game getCurrentGame() {
		return game;
	}
}