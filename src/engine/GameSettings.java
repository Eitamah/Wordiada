package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import gameSettings.GameDescriptor;
import gameSettings.Letter;

public class GameSettings implements ValidationEventHandler, Serializable {
	public enum eWinnerBy {
		WordCount
	}
	
	public enum eGameType {
		Basic,
		GoldFish
	}
	
	private String dictFilePath;
	private int deckSize;
	private Dictionary dictionary;
	private int boardSize;
	private eGameType gameType;
	private boolean valid;
	private boolean xmlValid;
	private eWinnerBy winnerBy;
	private List<Letter> letters;
	private int numOfPlayers = 2;
	private int numOfDiceFacets;
	private GameDescriptor gd;
	
	public GameSettings(String filePath) throws FileNotFoundException, IllegalArgumentException, JAXBException {
		valid = false;
		JAXBContext jaxbContext;
		Unmarshaller jaxbUnmarshaller;
		jaxbContext = JAXBContext.newInstance(GameDescriptor.class);
		jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		
		try {
			schema = sf.newSchema(new File("Wordiada.xsd"));
			jaxbUnmarshaller.setSchema(schema);
		} catch (SAXException e1) {
			System.out.println("??????????" + e1.getMessage());
		}

		jaxbUnmarshaller.setEventHandler(this);
		
		try {
			File XMLfile = new File(filePath);
			xmlValid = true;
			gd = (GameDescriptor) jaxbUnmarshaller.unmarshal(XMLfile);
		} catch (JAXBException e) {
			xmlValid = false;
			throw new FileNotFoundException("XML File not found");
		}
		
		// If the xml was invalid, the event would have set xmlValid to false
		if (xmlValid) {
			dictFilePath =  FilenameUtils.getFullPath(filePath) + "dictionary\\" + gd.getStructure().getDictionaryFileName();
			dictionary = new Dictionary(dictFilePath);
		
			deckSize = gd.getStructure().getLetters().getTargetDeckSize();
			boardSize = gd.getStructure().getBoardSize();
		
			if ((boardSize > Board.MAX_SIZE) || (boardSize < Board.MIN_SIZE)) {
				throw new IllegalArgumentException("Board size must be between " + Board.MAX_SIZE + " and "
						+ Board.MIN_SIZE);
			}
			
			if (deckSize < (boardSize * boardSize)) {
				throw new IllegalArgumentException("Deck size not enough for board");
			}
			
			if (!gd.getGameType().getValue().equals(new String("Basic"))) {
				throw new IllegalArgumentException("Game type must be basic");
			} else {
				gameType = eGameType.Basic;
			}
			
			if (!gd.getGameType().getWinnerAccordingTo().equals("WordCount")) {
				throw new IllegalArgumentException("Winner According To must be wordcount");
			} else {
				winnerBy = eWinnerBy.WordCount;
			}
			
			if (gd.getStructure().getCubeFacets() >= Dice.MIN_SIDES) {
				numOfDiceFacets = gd.getStructure().getCubeFacets();
			} else {
				throw new IllegalArgumentException("Dice facets must be at least " + Dice.MIN_SIDES);
			}
			
			loadLetters(gd);
			valid = true;
		}
	}

	private void loadLetters(GameDescriptor gd) throws IllegalArgumentException {
		letters = new ArrayList<Letter>();
		List<Letter> list = gd.getStructure().getLetters().getLetter();
		double totalFreq = 0;
		
		for (Letter let : list) {
			totalFreq += let.getFrequency();
		}
		
		for (Letter let : list) {
			let.setFrequency(Math.ceil( (let.getFrequency() * 100) / totalFreq) );
		}
		
		for (Letter let : list) {
			if (letters.contains(let)) {
				throw new IllegalArgumentException("the letter " + let.getSign() + " apears more than once");
			}
			
			int numOfLetter = (int)Math.ceil((let.getFrequency() / 100) * gd.getStructure().getLetters().getTargetDeckSize());
			
			let.getSign().get(0).toUpperCase();
			for (int i = 0; i < numOfLetter; ++i) {
				letters.add(let);
			}
		}
	}

	public boolean isValid() {
		return valid;
	}
	
	public eGameType getGameType() {
		return gameType;
	}
	
	public int getDeckSize() {
		return deckSize;
	}
	
	public Dictionary getDictionary() {
		return dictionary;
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	public int getBoardSize() {
		return boardSize;
	}

	public List<Letter> getLetters() {
		return letters;
	}
	@Override
	public boolean handleEvent(ValidationEvent event) {
		xmlValid = false;
		// TODO : Maybe get rid of getMessage()
		throw new IllegalArgumentException("Xml invalid " + event.getMessage() + " line " 
				+ event.getLocator().getLineNumber() + " column" + event.getLocator().getColumnNumber());
	}
	
	public int getDiceFacets() {
		return numOfDiceFacets;
	}
	
	public GameDescriptor getDescriptor() {
		return gd;
	}
}
