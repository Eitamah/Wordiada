package views.MenuItems;

import java.util.Scanner;
import engine.GameManager;

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
		System.out.println("Please enter xml path");
		scanner.nextLine();
		String input = scanner.nextLine();

		try {
			gameManager.loadGame(input);
		} catch (Exception e) {
			String message = "Problem with xml file:" + e.getMessage();
			System.out.println(message);
		}
		
	}

}
