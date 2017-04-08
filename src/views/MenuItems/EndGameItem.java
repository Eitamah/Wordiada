package views.MenuItems;

import java.io.Console;

public class EndGameItem implements MenuItem {

	private final String MENU_STRING = "End game";
	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute EndGameItem");
	}

}
