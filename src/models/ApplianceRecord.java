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
	
	public double GetAverageUsage()
	{
		double _total = 0;
		
		for (Double d : usageRecords) 
		{
			_total+=d;
		}
		
		return _total/usageRecords.size();
	}
}
