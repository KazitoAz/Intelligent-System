package models;

public class Home
{
	private double totalConsumed;
	private double totalGenerated;
	private double income;
	private double expense;
	
	public Home()
	{
		totalConsumed = 0;
		totalGenerated = 0;
		income = 0;
		expense =0;
	}
	
	public double getTotalConsumede()
	{
		return totalConsumed;
	}
	
	public double getTotalGenerated()
	{
		return totalGenerated;
	}
	
	public void consume(double amount)
	{
		totalConsumed+=amount;
	}
	
	public void generate(double amount)
	{
		totalGenerated+=amount;
	}
	
	public void reset()
	{
		totalConsumed = 0;
		totalGenerated = 0;
	}
	
	public double getIncome()
	{
		return income;
	}
	
	public double getExpense()
	{
		return expense;
	}
}
