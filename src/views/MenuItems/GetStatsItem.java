package views.MenuItems;

public class GetStatsItem implements MenuItem {

	private final String MENU_STRING = "Get game statistics";

	@Override
	public String getString() {
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute GetStatsItem");
	}

}
