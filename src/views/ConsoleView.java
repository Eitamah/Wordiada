package views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import engine.Dictionary;
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
	
	public ConsoleView() {
		menuItems = new ArrayList<MenuItem>();
		menuItems.add(new LoadGameItem());
		menuItems.add(new StartGameItem());
		menuItems.add(new ShowGameStatusItem());
		menuItems.add(new PlayTurnItem());
		menuItems.add(new GetStatsItem());
		menuItems.add(new EndCurrentGameItem());
		menuItems.add(new EndGameItem());
	}
	
	public void Run() {
		Test();

		while (true) {
			int choice = getUserChoice();
			menuItems.get(choice).Execute();
		}
	}
	private void Test() {
		String file = "C:\\Users\\Eitam\\Downloads\\war and piece32.txt";
		try {
			Dictionary dict = new Dictionary(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Dictionary file doesn't exist");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
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
}
