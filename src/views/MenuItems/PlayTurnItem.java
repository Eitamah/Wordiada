package views.MenuItems;

import java.util.Scanner;

import engine.GameManager;

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
	}

}
