package engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

public class GameSettings implements ValidationEventHandler {
	public enum eWinnerBy {
		WordCount
	}
	
	public enum eGameType {
		Basic
	}
	
	private String dictFilePath;
	private int deckSize;
	private Dictionary dictionary;
	private Scorer scorer;
	private int boardSize;
	private eGameType gameType;
	private boolean valid;
	private boolean xmlValid;
	private eWinnerBy winnerBy;
	private List<Letter> letters;
	private int numOfPlayers = 2;
	
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
		GameDescriptor gd;
		
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
			
			loadLetters(gd);
			valid = true;
		}
	}

	private void loadLetters(GameDescriptor gd) throws IllegalArgumentException {
		letters = new ArrayList<Letter>();
		List<Letter> list = gd.getStructure().getLetters().getLetter();
		int totalNum = 0;
		double totalFreq = 0;
		
		for (Letter let : list) {
			totalFreq += let.getFrequency();
		}
		
		for (Letter let : list) {
			let.setFrequency(Math.ceil((let.getFrequency() * 100) / totalFreq));
		}
		
		for (Letter let : list) {
			if (letters.contains(let)) {
				throw new IllegalArgumentException("the letter " + let.getSign() + " apears more than once");
			}
			int numOfLetter = (int)Math.ceil((let.getFrequency() / 100) * gd.getStructure().getLetters().getTargetDeckSize());
			totalNum += numOfLetter;
			
			for (int i = 0; i < numOfLetter; ++i) {
				letters.add(let);
			}
		}
		
		for (Letter let : letters) {
			System.out.println(let.getSign());
		}
			
		System.out.println("target = " + gd.getStructure().getLetters().getTargetDeckSize());
		System.out.println("Total freq = " + totalFreq);
		System.out.println("Total letters = " + letters.size());
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
	
	public Scorer getScorer() {
		return scorer;
	}

	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	@Override
	public boolean handleEvent(ValidationEvent event) {
		xmlValid = false;
		System.out.println("MESSAGE:  " + event.getMessage());
		System.out.println("LOCATOR");
		System.out.println("    LINE NUMBER:  " + event.getLocator().getLineNumber());
		System.out.println("    COLUMN NUMBER:  " + event.getLocator().getColumnNumber());
		return true;
	}
}
