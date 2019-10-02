package models;
import java.util.Random;
public class Appliance
{
	private String name;
	private double consumeRate;
	private boolean fixed;
	public Appliance(String _name, double _consumeRate, boolean _fixed)
	{
		name = _name;
		consumeRate = _consumeRate;
		fixed = _fixed;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getConsumeRate()
	{
		double newConsumeRate = consumeRate;
		if(!fixed)
		{
			Random rand = new Random();
			newConsumeRate = (rand.nextDouble()*(1.0f-0.7f)+0.7f)*consumeRate;
		}
		return Ulti.round(newConsumeRate);
	}
	
}