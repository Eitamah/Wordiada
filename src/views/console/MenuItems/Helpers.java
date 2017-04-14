package views.console.MenuItems;

import javax.xml.bind.helpers.NotIdentifiableEventImpl;

import engine.Board;
import engine.GameManager;
import engine.Tile.eTileState;

public class Helpers {
	
	public static final char FACE_DOWN_CHAR = '-';
	
	public static void printGameStatus(GameManager gameManager) {
		printBoard(gameManager);
		System.out.println("Tiles left :" + gameManager.getCurrentGame().getBoard().getTilesLeft());
		System.out.println("Next player : " + gameManager.getCurrentGame().getCurrentPlayer().getName().get(0));
	}
	
	public static void printBoard(GameManager gameManager) {
		Board board = gameManager.getCurrentGame().getBoard();

    	System.out.printf("\t");
		for (int col = 0; col < board.getSize(); col++) {
			System.out.printf("%4d", col + 1);
		}
		System.out.println();
		System.out.println();

	    for (int row = 0; row < board.getSize(); row++) {
	    	System.out.printf("%4d", row + 1);
	    	System.out.printf("\t");

	        for (int col = 0; col < board.getSize(); col++) {
	        	if (board.getBoard()[row][col].getState() == eTileState.FACE_UP) {	        		
	        		System.out.printf("%4c", board.getBoard()[row][col].getSign());
	        	} else {
	        		System.out.printf("%4c", FACE_DOWN_CHAR);
	        	}
	        }
	        
	        System.out.println();
	    }
	}

	public static void printGameStats(GameManager gameManager) {
		System.out.println("Printing game stats");
	}
}
