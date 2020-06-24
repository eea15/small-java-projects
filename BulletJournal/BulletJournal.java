/**
 * Emma Akbari
 * main program interacts with user to enter and view their "bullet journal"
 * allows user to make new entries and view old ones
 * saves player info in files via PlayerJournal.java
*/ 

import java.util.Scanner;
import java.io.*;

public class BulletJournal {

	public static void main(String[] args) {

		try {
			// set up variables
			Scanner inScan = new Scanner(System.in);
			File user = new File(args[0]); // user file
			boolean newPlayer = false;
			PlayerJournal journal; // read and write to journal

			// print welcome message based on whether player is new
			if(!user.exists()) {
				// new player
				System.out.println("Welcome to your bullet journal, " + args[0] + "!");
				newPlayer = true;
				//journal = new PlayerJournal(args[0], write);
			} else { 
				// returning player
				System.out.println("Welcome back to your bullet journal, " + args[0] + "!");
			}
			
			// instantiate journal based on whether player is new
			if(newPlayer) {
				PrintWriter write = new PrintWriter(user);
				journal = new PlayerJournal(args[0], write);
				write.close();
			} else {
				Scanner read = new Scanner(user);
				journal = new PlayerJournal(read);
				read.close();
			}

			// ask user what they want to do
			char action = ask_user(inScan);

			while(action != 'd') {
				// allow user to complete action
				if(action == 'a') {
					// user wants to view key
					show_key();
				} else if(action == 'b') {
					// user wants to create an entry
					create_entry(journal, inScan, user);
				} else if(action == 'c') {
					// user wants to view an entry
					view_entry(journal, inScan, user);
				} else {
					System.out.println("Invalid choice, try again.");
				}

				// ask again
				action = ask_user(inScan);
			}

			System.out.println("\nExiting...\n");

		} catch(IOException e) {
			System.out.println("An error occured.");
		}
	}

	// function to display key for bullets
	public static void show_key() {

		System.out.println("\n--------------------------");
		System.out.println("    Bullet Journal Key  ");
		System.out.println("--------------------------");
		System.out.println(". task\n> moved to following day\n- something to note\n"
			+ "x cancelled/irrelevent\n* important\n");
	}

	// function to prompt user whether they want to create or view an entry
	public static char ask_user(Scanner input) {
		char choice = ' ';

		do {
			System.out.println("\nDo you want to  [a, b, c, d]\na)view the key\nb)create an entry"
				+ "\nc)view an entry\nd)exit");
			choice = input.next().charAt(0);;
		} while(choice != 'a' && choice != 'b' && choice != 'c' && choice != 'd');

		return choice;
	}

	// function to add bullet journal entry
	public static void create_entry(PlayerJournal j, Scanner input, File file) {
		// prompt for date & check that is doesn't already exist
		System.out.println("Enter today's date for your entry [ex: 1/10/2000]");
		input.nextLine();
		String today = input.nextLine();
		
		while(j.date_exists(today, file)) {
			System.out.println("That date already has been entered, try again.");
			today = input.nextLine();
		}

		// write date to file
		j.add_date(today, file);

		// remind user of the key
		System.out.println("As a reminder, here is the bullet key (entries must be formatted as one" 
				+ " of the following):");
		show_key();

		// loop until done entering bullets
		boolean done = false;
		String task;
		String quit = "not done";

		while(!done) {
			// make sure the entry is of valid format
			do {
				System.out.println("Enter a bullet entry starting with an appropriate symbol [., >, -, x, *]:");
				task = input.nextLine();
			} while(!valid_bullet(task.charAt(0)));

			j.set_entry(task, file);

			System.out.println("Type 'q' to quit, otherwise enter to add another task.");
			quit = input.nextLine();

			if(quit.equals("q")) {
				done = true;
			}
		}
	}

	// function makes sure bullet is valid
	public static boolean valid_bullet(char b) {
		if(b == '.') {
			return true;
		} else if(b == '>') {
			return true;
		} else if(b == '-') {
			return true;
		} else if(b == 'x') {
			return true;
		} else if(b == '*') {
			return true;
		} else {
			return false;
		}
	}

	// function to view bullet journal entry
	public static void view_entry(PlayerJournal j, Scanner input, File file) {
		// prompt user for date of entry they want to view, check that it exists
		System.out.println("Enter the date of the entry you are looking for: ");
		input.nextLine();
		String toView = input.nextLine();

		boolean validDate = j.date_exists(toView, file); // true if date exists

		// if valid date, read in and print the entry
		if(validDate) {
			j.get_entry(toView, file); 
		} else {
			System.out.println("Invalid date.");
		}	
	}
}