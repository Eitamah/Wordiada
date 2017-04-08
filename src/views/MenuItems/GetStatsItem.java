package views.MenuItems;

import java.util.Scanner;

import engine.GameManager;

public class GetStatsItem extends MenuItem {

	public GetStatsItem(Scanner scanner) {
		super(scanner);
		// TODO Auto-generated constructor stub
	}

	private final String MENU_STRING = "Get game statistics";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute(GameManager gameManager) {
		System.out.println("Execute GetStatsItem");
	}

}
