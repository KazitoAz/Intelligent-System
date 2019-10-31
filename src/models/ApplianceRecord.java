package models;

import java.util.Vector;

public class ApplianceRecord 
{
	private Vector<Double> usageRecords;
	
	public ApplianceRecord()
	{
		usageRecords = new Vector<Double>();
	}
	
	public void AddToRecord(double num)
	{
		usageRecords.add(num);
	}
	
	public double PredictUsage()
	{
		double sumx = 0;
		double sumy = 0;
		for (int i = 0; i < usageRecords.size(); i++) 
		{
			sumx += i;
			sumy += usageRecords.get(i);
		}
		double xbar = sumx / usageRecords.size();
        double ybar = sumy / usageRecords.size();
        
		double xxbar =0;
		double xybar = 0;
		for(int i=0; i < usageRecords.size();i++)
		{
			xxbar += (usageRecords.get(i) - xbar) * (usageRecords.get(i)  - xbar);
            xybar += (usageRecords.get(i)  - xbar) * (i - ybar);
		}
		double a = xybar / xxbar;
		double b = ybar - a * xbar;
		return b;
	}
}
