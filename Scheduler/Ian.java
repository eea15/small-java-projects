/** 
 * Emma Akbari
 * to run: java Ian
 * main Scheduler class interacts with user
 * inputs: homework, outputs: optimized schedule to complete hw
 * named after my brother :)
 */ 

import java.util.Scanner;

public class Ian {
	
	public static void main(String[] args)
	{
		// set up variables
		Scanner inScan = new Scanner(System.in);
		String sub = "";
		int length = 0;
		ScheduleGenerator sched = new ScheduleGenerator();
		
		System.out.println("Welcome to your schedule generator, Ian! How many subjects do you have homework in?");
		int num = inScan.nextInt(); 
		// create array of homework assignments
		Homework[] assigs = new Homework[num];
		
		// set up of homework assignments
		System.out.println("Note: enter assignments in order of decreasing precedence.");
		for(int i = 1; i <= num; i++)
		{
			System.out.println("Enter the name of subject number " + i);
			inScan.nextLine();
			sub = inScan.nextLine();
			// Ian usually finishes shorter homework assignments during resource
			do
			{
				System.out.println("Approximately will it take to complete in minutes? [enter 30 or 60]");
				length = inScan.nextInt();
			}while(length != 30 && length != 60);
			
			// create new instance of Homework and store in array
			Homework temp = new Homework(sub, length, i);
			assigs[i-1] = temp;
		}
		
		// setup of unavailable times
		System.out.println("During which of the following time slots are you unavailable/busy?");
		System.out.println("Enter in the form of ints separated by spaces [ex: 0 2 4]");
		sched.displayWithNumbers(); // display options
		inScan.nextLine();
		String busy = inScan.nextLine();
		sched.markBusy(busy); // mark user entered times as busy
		
		// make the actual schedule
		sched.makeSchedule(assigs);
		// display final schedule
		System.out.println("\nHere is your final schedule");
		System.out.println("---------------------------");
		sched.displayFinal();
		System.out.println("\nHere is your quote of the day, Ian!");
		sched.randQuote();
	}
}
