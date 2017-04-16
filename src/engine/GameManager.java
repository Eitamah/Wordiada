package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import gameSettings.GameDescriptor;

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