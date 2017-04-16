package views.console.MenuItems;

import java.util.Scanner;

import engine.GameManager;

public class LoadFileItem extends MenuItem {

	private final String MENU_STRING = "Load saved game";
	
	public LoadFileItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		// TODO Auto-generated method stub

	}

}
