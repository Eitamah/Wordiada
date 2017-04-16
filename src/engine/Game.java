package engine;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBException;

import engine.GameSettings.eGameType;
import gameSettings.Player;
import gameSettings.Player.ePlayerType;
import gameSettings.Players;

public class Game implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum eGameState {
		UNINITIALIZED,
		LOADED,
		RUNNING,
		FINISHED
	}
	
	private eGameState state;
	private GameSettings settings;
	private Board board;
	private Dice dice;
	private int currentPlayer;
	private List<Turn> turns;
	long timeStarted;

	
	public Game() {
		state = eGameState.UNINITIALIZED;
		settings = null;
		board = null;
	}
	
	public void LoadSettings(String filePath) throws FileNotFoundException, IllegalArgumentException, 
												JAXBException, IllegalStateException {
		settings = new GameSettings(filePath);
		checkPlayers();
		initGame();
	}

	/*
	 * Check if there are players in the file (if not, default is 2 - player1 and player2
	 * if there are players in the file - check for duplicates ID or invalid types
	 */
	private void checkPlayers() {
		if ((settings.getDescriptor().getPlayers() == null) ||
			(settings.getDescriptor().getPlayers().getPlayer().size() < 2)) {
			// default is player1 and player2
			Player player1 = new Player();
			player1.setId((short)1234);
			player1.setType("Human");
			player1.getName().add("Player1");
			
			Player player2 = new Player();
			player2.setId((short)4321);
			player2.setType("Human");
			player2.getName().add("Player2");
			
			settings.getDescriptor().setPlayers(new Players());
			settings.getDescriptor().getPlayers().getPlayer().add(player1);
			settings.getDescriptor().getPlayers().getPlayer().add(player2);
		}
		
		for (Player player : settings.getDescriptor().getPlayers().getPlayer()) {
			if (Collections.frequency(settings.getDescriptor().getPlayers().getPlayer(), player) > 1) {
				throw new IllegalArgumentException("Player with id " + player.getId() + " included more"
						+ "than once in xml");
			}
			
			if (player.getType() == ePlayerType.INVALID) {
				throw new IllegalArgumentException("Player with id " + player.getId() + " has an "
						+ "invalid type");
			}
		}
	}

	public void startGame() {
		switch (state) {
			case UNINITIALIZED: {
				throw new IllegalStateException("Game hasn't been loaded yet");
	//			break;
			} case LOADED: {
				state = eGameState.RUNNING;
				break;
			} case RUNNING: {
				throw new IllegalStateException("Game already running");
	//			break;
			} case FINISHED: {
				throw new IllegalStateException("Game finished");
	//			break;
			}
			default: {
				break;
			}
		}		
		
		timeStarted = System.currentTimeMillis();
		playComputerTurns();
	}

	public void playComputerTurns() {
		// TODO Auto-generated method stub
		
	}

	private void initGame() {
		currentPlayer = 0;
		board = new Board(settings.getBoardSize(), settings.getLetters());
		dice = new Dice(settings.getDiceFacets());
		turns = new ArrayList<Turn>();
		state = eGameState.LOADED;
		
	}

	public eGameState getStatus() {
		return state;
	}
	
	public void endGame() {
		state = eGameState.FINISHED;		
	}
	
	public boolean isSettingsValid() {
		return settings.isValid();
	}
	
	public Player getCurrentPlayer() {
		return settings.getDescriptor().getPlayers().getPlayer().get(currentPlayer);
	}
	
	public Board getBoard() {
		return board;
	}
	
	/*
	 * Gets character for tile - if tile isn't visible, return -
	 */
	public Tile getTile(int row, int col) {
		return board.getBoard()[row][col];
	}
	
	public GameSettings getSettings() {
		return settings;
	}

	public Player getWinner() {
		currentPlayer = (currentPlayer + 1) % settings.getDescriptor().getPlayers().getPlayer().size();
		return settings.getDescriptor().getPlayers().getPlayer().get(currentPlayer);
	}
	
	public int getDiceRoll() {
		return dice.rollDice();
	}

	public List<Turn> getTurns() {
		return turns;
	}

	public long getTimeStarted() {
		return timeStarted;
	}

	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return settings.getDescriptor().getPlayers().getPlayer();
	}

	public void playTurn() {
		incrementCurrentPlayer();
		
		// update board, player, score, words, etc...
		
		endTurn();
	}

	private void endTurn() {
		if (settings.getGameType() == eGameType.GoldFish) {
			board.faceDownAllTiles();
		}
	}

	private void incrementCurrentPlayer() {
		currentPlayer = (currentPlayer + 1) % settings.getDescriptor().getPlayers().getPlayer().size();
	}
}