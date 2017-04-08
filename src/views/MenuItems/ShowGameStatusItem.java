package views.MenuItems;

import java.util.Scanner;

import engine.GameManager;

public class ShowGameStatusItem extends MenuItem {
	
	public ShowGameStatusItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "Show game status";

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		System.out.println("Execute show game status item");
	}

}
