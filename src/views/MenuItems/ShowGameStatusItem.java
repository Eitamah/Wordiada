package views.MenuItems;

public class ShowGameStatusItem implements MenuItem {
	
	private final String MENU_STRING = "Show game status";

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return MENU_STRING;
	}

	@Override
	public void Execute() {
		System.out.println("Execute show game status item");
	}

}
