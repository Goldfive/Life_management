package management;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Management {
	
	Database db;
	ArrayList<Metric> metricList;
	
	Management()
	{
		connectDatabase(true);
	}
	
	public void runApplication()
	{
		showMainMenu();
		loadDatabase();
		
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			switch (sc.nextLine())
			{
//				case 1:
					
			}
		}
	}
	
	private void loadDatabase()
	{
		ResultSet query = db.select("SELECT * FROM metrics");
		
		try {
			while (query.next())
			{
				System.out.println(query.getString("type"));
				switch (query.getString("type"))
				{
					case "sleep": //int id, String type, int value, String time, String extra
						metricList.add(new SleepMetric(query.getInt("id"), query.getString("type"), query.getInt("value"),
								query.getString("time"), query.getString("extra")));
						break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Er zijn geen records aangetroffen om in te laden.");
		}
	}
	
	
	public void connectDatabase(boolean output)
	{
		String host, user, pass;
		try(BufferedReader br = new BufferedReader(new FileReader("api pass.txt"))) {
	
		    host = br.readLine();
		    
		    user = br.readLine();
		    pass = br.readLine();
		    System.out.println(user+pass+host);
		    try {
		    	db = new Database(user, pass, host);
		    } catch (BadLoginException e) {
		    	System.out.println("Foutieve inloggegevens. Stacktrace print:");
		    	
		    	StringWriter sw = new StringWriter();
		    	PrintWriter pw = new PrintWriter(sw);
		    	e.printStackTrace(pw);
		    	String sStackTrace = sw.toString();

		    	System.out.println(sStackTrace);
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Database inloggegevens niet gevonden.");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (output) 
			System.out.print("Succesvolle connectie aangegaan met de database.");

	}
	
	void showMainMenu()
	{
		System.out.println("Welkom bij het Life Management Monitoring System (LMMS). U kunt kiezen uit:"
				+ "\n\n1. Sleep Metric  - 2. Money Metric"
				+ "\n3. Sports Metric   - 3. Food Metric"
				+ "\n4. Sluit applicatie");
	}
	
	public static void main(String... args)
	{
		System.out.println("Check de database connectie...");
		Management m = new Management();
		m.runApplication();
	}
}

enum MetricNames {
	SLEEPMETRIC, SPORTSMETRIC, MONEYMETRIC, FOODMETRIC
}
