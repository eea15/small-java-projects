// Emma Akbari
// basic Homework class includes subject, time to complete, and significance

public class Homework {

	String subject;
	int approxTimeInMin;
	int signif;
	
	// default constructor method
	public Homework()
	{
		subject = "";
		approxTimeInMin = 0;
		signif = -1;
	}
	
	public Homework(String s, int time, int sig)
	{
		subject = s;
		approxTimeInMin = time;
		signif = sig;
	}
	
	//  the following are accessor methods to obtain Homework properties
	
	public String getSubject()
	{
		return subject; // returns homework subject
	}
	
	public int getTime()
	{
		return approxTimeInMin; // returns estimated time it will take to complete homework
	}
	
	public int getSignif()
	{
		return signif; // returns significance ranking
	}
	
	public String toString()
	{
		return("Subject: " + subject + " Time: " + approxTimeInMin + " Ranking: " + signif);
	}
}
