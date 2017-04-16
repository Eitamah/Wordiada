package views.console.MenuItems;

import java.awt.Point;
import java.util.Scanner;

import engine.Game.eGameState;
import engine.GameManager;
import engine.Tile.eTileState;
import gameSettings.Player.ePlayerType;
import javafx.util.Pair;

public class PlayTurnItem extends MenuItem {

	public PlayTurnItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "Play turn";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		if (gameManager.getCurrentGame().getStatus() == eGameState.RUNNING) {
			playHumanTurn(gameManager);
			playComputerTurn(gameManager);
			
			

		} else {
			System.out.println("Game isn't running");
		}
		
		
	}

	private void playComputerTurn(GameManager gameManager) {
		gameManager.getCurrentGame().playComputerTurns();
	}

	private void playHumanTurn(GameManager gameManager) {
		int diceRoll = gameManager.getCurrentGame().getDiceRoll();
		int tilesToUncover = diceRoll;
		System.out.println("Current player - " + gameManager.getCurrentGame().getCurrentPlayer().getName());
		System.out.println("Dice roll - " + diceRoll);

		while ((tilesToUncover > 0) && 
				(gameManager.getCurrentGame().getBoard().getFaceDownTiles().size() > 0)) {
			Point coord = getCoordinatesFromUser(gameManager);
			
			if (gameManager.getCurrentGame().getBoard().getBoard()[coord.x][coord.y].getState() 
					== eTileState.FACE_UP) {
				System.out.println("Already face up - choose a face down tile");
			} else {
				gameManager.getCurrentGame().getBoard().flipTile(coord.x, coord.y, eTileState.FACE_UP);
				tilesToUncover--;
			}
		}
		
		Helpers.printBoard(gameManager);
		
		System.out.println("Enter word [text]");
		String input = scanner.nextLine();
		
		gameManager.getCurrentGame().playTurn();
	}
	
	private Point getCoordinatesFromUser(GameManager gameManager) {
		boolean isValid = false;
		Point returnPoint = new Point();
		
		while (!isValid) {
			System.out.println("Please choose coordinates for next tile [row] [col]");
			try {
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				scanner.nextLine();
				
				if ((((row - 1) < 0) || ((row - 1)> gameManager.getCurrentGame().getBoard().getSize())) ||
					(((col - 1) < 0) || ((col - 1)> gameManager.getCurrentGame().getBoard().getSize()))) {
					throw new Exception();
				}
				
				isValid = true;
				returnPoint.x = row - 1;
				returnPoint.y = col - 1;
			} catch (Exception e) {
				System.out.println("Invalid coordinates. Enter two numbers between 1 and " + 
							gameManager.getCurrentGame().getBoard().getSize() + 1);
				scanner.nextLine();
			}
		}
		
		return returnPoint;
	}
}
