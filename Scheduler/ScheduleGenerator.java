/**
 * Emma Akbari
 * class to generate a schedule
 * makes a schedule based on input precedence and length in time
 * also includes quotes
 */
import java.util.ArrayList;

public class ScheduleGenerator {

	String[] schedule = new String[10];
	String[] homeworkSched = new String[10];
	ArrayList<Homework> halfHour = new ArrayList<Homework>();
	ArrayList<Homework> hour = new ArrayList<Homework>();
	String[] quotes = new String[5];
	
	// default constructor method
	public ScheduleGenerator()
	{
		// set up schedule array
		schedule[0] = "3:00 - 3:30";
		schedule[1] = "3:30 - 4:00";
		schedule[2] = "4:00 - 4:30";
		schedule[3] = "4:30 - 5:00";
		schedule[4] = "5:00 - 5:30";
		schedule[5] = "5:30 - 6:00";
		schedule[6] = "6:00 - 6:30";
		schedule[7] = "6:30 - 7:00";
		schedule[8] = "7:00 - 7:30";
		schedule[9] = "7:30 - 8:00";
		
		// avoid NullPointerException with homeworkSched
		for(int i = 0; i < homeworkSched.length; i++)
		{
			homeworkSched[i] = "brain break!";
		}
		
		// fill quote array
		quotes[0] = "\"Try and fail, but don't fail to try.\" --Stephen Kaggwa";
		quotes[1] = "\"Great things are done by a series of small things brought together.\" --Vincent Van Gogh";
		quotes[2] = "\"The brain is like a muscle. When it is in use we feel very good. Understanding is joyous.\" --Carl Sagan";
		quotes[3] = "\"Failure is just practice for success.\" --Unknown";
		quotes[4] = "\"Do something today that your future self will thank you for.\" --Unknown";
	}
	
	// mark busy time slots
	public void markBusy(String x)
	{
		for(int i = 0; i < schedule.length; i++)
		{
			if(x.contains(String.valueOf(i)))
			{
				homeworkSched[i] = "BUSY";
			}
		}
	}
	
	// make the schedule
	public void makeSchedule(Homework[] hw)
	{
		// fill ArrayLists based on how long it will take to complete hw
		for(int i = 0; i < hw.length; i++)
		{
			if(hw[i].getTime() == 30) // half hour hw
			{
				halfHour.add(hw[i]);
			}
			else if(hw[i].getTime() == 60) // hour hw
			{
				hour.add(hw[i]);
			}
		}
		
		// fill schedule
		for(int i = 0; i < schedule.length; i++)
		{
			if(i < 9 && !homeworkSched[i].equals("BUSY") && !homeworkSched[i+1].equals("BUSY") && hour.size() != 0) //next hour available
			{
				homeworkSched[i] = hour.get(0).getSubject();
				homeworkSched[i+1] = hour.get(0).getSubject();
				i++; // increment extra bc two slots are filled
				hour.remove(0);
			}
			else if(i < 9 && !homeworkSched[i].equals("BUSY") && homeworkSched[i+1].equals("BUSY") && halfHour.size() != 0) //next half hour available
			{
				homeworkSched[i] = halfHour.get(0).getSubject();
				halfHour.remove(0);
			}
			else if(!homeworkSched[i].equals("BUSY") && halfHour.size() != 0) //last slot
			{
				homeworkSched[i] = halfHour.get(0).getSubject();
				halfHour.remove(0);
			}
			// else, nothing happens	
		}
	}
	
	// method to display schedule options, with array numbers
	public void displayWithNumbers()
	{
		for(int i = 0; i < schedule.length; i++)
		{
			System.out.println(i + ") " + schedule[i]);
		}
	}
	
	// method to display combination final schedule
	// which combines schedule and homeworkSched
	public void displayFinal()
	{
		for(int i = 0; i < schedule.length; i++)
		{
			System.out.println(schedule[i] + ": " + homeworkSched[i]);
		}	
	}
	
	// method to randomly choose quote
	public void randQuote()
	{
		int x = (int)(Math.random()*4);
		System.out.println(quotes[x]);
	}
}
