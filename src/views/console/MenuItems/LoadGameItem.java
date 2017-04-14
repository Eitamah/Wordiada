package views.console.MenuItems;

import java.util.Scanner;
import engine.GameManager;
import engine.Game.eGameState;

public class LoadGameItem extends MenuItem {

	public LoadGameItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "Load Game";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		if ((gameManager.getCurrentGame() == null) ||
			(gameManager.getCurrentGame().getStatus() != eGameState.RUNNING)) {
			System.out.println("Please enter xml path");
			scanner.nextLine();
			String input = scanner.nextLine();
	
			try {
				// TODO: Remove this!!!!
				input = "C:\\temp\\basic_2.xml";
				gameManager.loadGame(input);
			} catch (IllegalStateException e) {
				System.out.println("Can't load settings " + e.getMessage());
			} catch (Exception e) {
				String message = "Problem with xml file:" + e.getMessage();
				System.out.println(message);
			}
			
			Helpers.printGameStatus(gameManager);
		} else {
			System.out.println("Can't load a new game while current game is running");
		}
	}
}
