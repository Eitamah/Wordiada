package views.MenuItems;

import java.util.Scanner;

import engine.GameManager;

public class EndGameItem extends MenuItem {

	public EndGameItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "End game";
	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		System.out.println("Execute EndGameItem");
	}

}
