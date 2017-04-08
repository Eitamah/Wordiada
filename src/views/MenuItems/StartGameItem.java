package views.MenuItems;

import java.util.Scanner;

import engine.GameManager;

public class StartGameItem extends MenuItem {

	public StartGameItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "Start game";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		System.out.println("Execute start game status item");
	}

}
