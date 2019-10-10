package models;

import java.util.Vector;

public class ApplianceRecord 
{
	private String applianceName;
	
	private Vector<Double> usageRecords;
	
	public ApplianceRecord(String _name)
	{
		applianceName = _name;
	}
	
	public String getName()
	{
		return applianceName;
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