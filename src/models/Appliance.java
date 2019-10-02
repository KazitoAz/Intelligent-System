package models;
import java.util.Random;
public class Appliance
{
	private String name;
	private double consumeRate;
	
	public Appliance(String _name, double _consumeRate)
	{
		name = _name;
		consumeRate = _consumeRate;
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getConsumeRate()
	{
		Random rand = new Random();
		double newConsumeRate = (rand.nextDouble()*(1.0f-0.7f)+0.7f)*consumeRate;
		newConsumeRate = Math.round(newConsumeRate*10);
		newConsumeRate = newConsumeRate/10;
		return newConsumeRate;
	}
	
}