package engine;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import engine.Player;

public class Game {
	
	public enum eGameState {
		LOADED,
		RUNNING,
		FINISHED
	}
	
	private List<Player> players;
	private GameSettings settings;
	
	
	public Game() {
		settings = null;
	}
	
	public void LoadSettings(String filePath) throws FileNotFoundException, IllegalArgumentException, JAXBException {
		settings = new GameSettings(filePath);		
	}

	public void startGame() {
		
	}
	
	
}
