package models;

import java.util.Random;

public class Home
{
	private double totalConsume;
	private double totalGenerate;
	private double income;
	private double expense;
	
	public Home()
	{
		totalConsume = 0;
		totalGenerate = 0;
		income = 0;
		expense =0;
	}
	
	public double getTotalConsume()
	{
		
		totalConsume = Math.round(totalConsume*10);
		totalConsume = totalConsume/10;
		return totalConsume;
	}
	
	public double getTotalGenerate()
	{
		totalGenerate = Math.round(totalGenerate*10);
		totalGenerate = totalGenerate/10;
		return totalGenerate;
	}
	
	public void consume(double amount)
	{
		totalConsume+=amount;
	}
	
	public void generate(double amount)
	{
		totalGenerate+=amount;
	}
	
	public void reset()
	{
		totalConsume = 0;
		totalGenerate = 0;
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
