package views.MenuItems;

public class PlayTurnItem implements MenuItem {

	private final String MENU_STRING = "Play turn";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute Playturn item");
	}

}
