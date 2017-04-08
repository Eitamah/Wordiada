package views.MenuItems;

import java.util.Scanner;

import engine.GameManager;

public class EndCurrentGameItem extends MenuItem {

	public EndCurrentGameItem(Scanner scanner) {
		super(scanner);
	}

	private final String MENU_STRING = "End current game";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		System.out.println("Execute End Current Game Item");
	}

}
