package views.console.MenuItems;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import engine.GameManager;
import engine.Game.eGameState;
import gameSettings.Letter;
import gameSettings.Player;

public class GetStatsItem extends MenuItem {

	public GetStatsItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "Get game statistics";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		if (gameManager.getCurrentGame().getStatus() == eGameState.RUNNING) {
			
			System.out.println("================ Game Stats ===============");
			System.out.println("Number of turns played - " + gameManager.getCurrentGame().getTurns().size());
	
			long now = System.currentTimeMillis();
			long duration = now - gameManager.getCurrentGame().getTimeStarted();
			
			// change to seconds
			duration = duration / 1000;
			
			int minutes = (int) (duration / 60);
			int seconds = (int) (duration % 60);
			String time = String.format("%02d:%02d", minutes, seconds);
			System.out.println("Time since game started " + time);
			
			List<Letter> list = gameManager.getCurrentGame().getSettings().getDescriptor().
					getStructure().getLetters().getLetter();
			
			for (Letter letter : list) {
				int count = Collections.frequency(gameManager.getCurrentGame().getSettings().getLetters(), letter);
				System.out.println(letter.getSign().get(0).toUpperCase() + ": " + count + 
						"/" + gameManager.getCurrentGame().getSettings().getLetters().size());
			}
			
			for (Player player : gameManager.getCurrentGame().getPlayers()) {
				System.out.println(player.getName() + " Score = " + player.getScore());
			}
			
			for (Player player : gameManager.getCurrentGame().getPlayers()) {
				System.out.println(player.getName() + " words:");
				
				for (String word : player.getWordsPlayed()) {
					System.out.println(word + " : " + gameManager.getCurrentGame().getSettings().getDictionary()
							.getWordCount(word) + " / " + gameManager.getCurrentGame().getSettings().getDictionary()
							.getTotalWords());
				}
			}
		} else {
			System.out.println("Must have a game running");
		}
	}

}
