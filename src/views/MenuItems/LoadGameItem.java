package views.MenuItems;

public class LoadGameItem implements MenuItem {

	private final String MENU_STRING = "Load Game";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute LoadGameItem");
	}

}
