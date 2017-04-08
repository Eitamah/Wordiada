package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import engine.GameManager;
import views.MenuItems.EndCurrentGameItem;
import views.MenuItems.EndGameItem;
import views.MenuItems.GetStatsItem;
import views.MenuItems.LoadGameItem;
import views.MenuItems.MenuItem;
import views.MenuItems.PlayTurnItem;
import views.MenuItems.ShowGameStatusItem;
import views.MenuItems.StartGameItem;

public class ConsoleView {
	private List<MenuItem> menuItems;
    private static Scanner scanner = new Scanner(System.in);
    GameManager gameManager = new GameManager();
	
	public ConsoleView() {
		menuItems = new ArrayList<MenuItem>();
		menuItems.add(new LoadGameItem(scanner));
		menuItems.add(new StartGameItem(scanner));
		menuItems.add(new ShowGameStatusItem(scanner));
		menuItems.add(new PlayTurnItem(scanner));
		menuItems.add(new GetStatsItem(scanner));
		menuItems.add(new EndCurrentGameItem(scanner));
		menuItems.add(new EndGameItem(scanner));
	}
	
	public void Run() {
		Test();

		while (true) {
			int choice = getUserChoice();
			menuItems.get(choice).Execute(gameManager);
		}
	}

	private int getUserChoice() {
		printMenu();
		int num = 0;
        boolean validInput = false;
		
		while (!validInput) {
			try {
				num = scanner.nextInt();
				
				if ((num < 0) || (num > menuItems.size() - 1))
				{
					System.out.println("Invalid choice - please choose one of the options");
				}
				else
				{
					validInput = true;					
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid choice - please choose a number");
				scanner.nextLine();
				// TODO: handle exception
			}
		}
		
		return num;
		
	}

	private void printMenu() {
		int index = 0;
		for (MenuItem item : menuItems) {
		    System.out.println(index + " - " + item.getString());
		    ++index;
		}
	}
	
	private void Test() {
		try {
		} catch (Exception e) {
			// TODO: handle exception
		}
	/*	String file = "C:\\Users\\Eitam\\Downloads\\war and piece32.txt";
		try {
			Dictionary dict = new Dictionary(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Dictionary file doesn't exist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}		*/
	}
}
