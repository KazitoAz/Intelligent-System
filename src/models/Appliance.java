package models;
import java.util.Random;
public class Appliance
{
	private String name;
	private float consumeRate;
	
	public Appliance(String _name, float _consumeRate)
	{
		name = _name;
		consumeRate = _consumeRate;
	}
	
	public String getName()
	{
		return name;
	}
	
	public float getConsumeRate()
	{
		Random rand = new Random();
		consumeRate = 10 - rand.nextInt(20);
		return consumeRate;
	}
	
}