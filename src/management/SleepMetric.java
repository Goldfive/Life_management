package management;

import java.util.ArrayList;

public class SleepMetric extends Metric implements Monitor, Statistics {

	SleepMetric(int id, String type, int value, String time, String extra)
	{
		loadData(id,type, value, time, extra);
		logs("");
	}
	
	@Override
	void updateData() {
		// TODO Auto-generated method stub
		
	}
	
	void logs(String args)
	{
		if (args.equals(""))
		{
			System.out.println("id - waarde - tijd - extra");
			for (Metric m : metricList)
			{
				if (m.type == "sleep")
				{
					System.out.println(m.id+" "+m.value+" "+m.time+": "+m.extra);
				}
			}
		}
	}

	@Override
	void menu() {
		System.out.println("----- Sleep Metric ------"
				+ "\n Dit onderdeel van je programma houd je slaap bij. Wanneer je gaat slapen of op staat, "
				+ "log het in dit programma en dan kan je er allemaal fancy shit over zien."
				+ "Commando's: !sleep\n"
				+ "!wakeup\n"
				+ "!logs - zie alle slaap data, gefilterd per dag (of vul x aantal dagen in)\n"
				+ "!statistics - zie de statistieken over je slaap. Bijv: gemiddelde nacht lengte, gemiddelde slaap tijd etc.\n"
				+ "!monitor - zet de alert opties. Zolang het programma blijft draaien, krijg je alerts om de ideale bedtijd aan te houden.");
		
	}
	
}
