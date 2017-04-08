package views.MenuItems;

public class StartGameItem implements MenuItem {

	private final String MENU_STRING = "Start game";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute start game status item");
	}

}
