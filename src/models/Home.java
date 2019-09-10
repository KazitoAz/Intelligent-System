package models;

public class Home
{
	private float totalConsumeRate;
	private float totalGenerateRate;
	private float income;
	private float expense;
	
	public Home()
	{
		totalConsumeRate = 0;
		totalGenerateRate = 0;
		income = 0;
		expense =0;
	}
	
	public float getTotalConsumeRate()
	{
		return totalConsumeRate;
	}
	
	public float getTotalGenerateRate()
	{
		return totalGenerateRate;
	}
	
	public float getIncome()
	{
		return income;
	}
	
	public float getExpense()
	{
		return expense;
	}
}
