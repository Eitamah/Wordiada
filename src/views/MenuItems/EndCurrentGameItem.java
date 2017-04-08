package views.MenuItems;

public class EndCurrentGameItem implements MenuItem {

	private final String MENU_STRING = "End current game";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute End Current Game Item");
	}

}
