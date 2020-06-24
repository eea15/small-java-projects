// Emma Akbari
// read/write to player journal file

import java.util.*;
import java.io.*;

public class PlayerJournal {

	// set up vars
	private String player;

	// default constructor method
	public PlayerJournal() {
		player = "nameless";
	}

	// constructor method for returning player
	public PlayerJournal(Scanner fileRead) {
		// intialize player name
		player = fileRead.nextLine();
	}

	// constructor method for new player
	public PlayerJournal(String p, PrintWriter fileWrite) {
		// initialize player name and write it to file
		player = p;
		fileWrite.print(p);
	}

	// write date to bullet journal
	public void add_date(String d, File f) {
		try {
			PrintWriter write = new PrintWriter(new FileWriter(f, true));
			write.append("\n" + d + ",");
			write.close();
		} catch(IOException e) {
			System.out.println("Error writing to file.");
		}
	}
	
	// write to bullet journal
	public void set_entry(String d, File f) {
		try {
			PrintWriter write = new PrintWriter(new FileWriter(f, true));
			write.append(d + ",");
			write.close();
		} catch(IOException e) {
			System.out.println("Error writing to file.");
		}
	}
	
	// read from bullet journal and print entry
	public void get_entry(String d, File f) {
		try {
			// set up variables
			Scanner read = new Scanner(f);
			String next;
			String[] tokens;

			// read in lines of file, look for date
			while(read.hasNextLine()) {
				next = read.nextLine();
				tokens = next.split(",");
				// parse String if date exists
				if(tokens[0].equals(d)) {
					System.out.println("-----------------------");
					System.out.println("     " + tokens[0]);
					System.out.println("-----------------------");

					// loop until last token
					for(int i = 1; i < tokens.length; i++) {
						  System.out.println(tokens[i]);
					}
					System.out.println();
					break;
				}
			}
			read.close();
		} catch(IOException e) {
			System.out.println("Error reading from file.");
		}
	}
	
	// check if date exists
	public boolean date_exists(String d, File f) {
		boolean found = false;

		try {
			// set up variables
			Scanner read = new Scanner(f);
			String next;
			String[] tokens;

			// read in lines of file, look for date
			while(read.hasNextLine()) {
				next = read.nextLine();
				tokens = next.split(",");
				// set found to true if date exists
				if(tokens[0].equals(d)) {
					found = true;
					break;
				}
			}
			read.close();
		} catch(IOException e) {
			System.out.println("Error reading from file.");
		}

		return found;
	}
}