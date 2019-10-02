package models;

import java.util.Random;

public class Home
{
	private double totalConsume;
	private double totalGenerate;
	
	private Proposal currentProposal;
	
	public Home()
	{
		totalConsume = 0;
		totalGenerate = 0;
	}
	
	public double getTotalConsume()
	{;
		return Ulti.round(totalConsume);
	}
	
	public double getTotalGenerate()
	{
		return Ulti.round(totalGenerate);
	}
	
	public void SetProposal(Proposal proposal)
	{
		currentProposal = proposal;
	}
	
	public Proposal getProposal()
	{
		return currentProposal;
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
		if(currentProposal == null)
		{
			return 0;
		}
		return Ulti.round(totalGenerate * currentProposal.getBuyPrice());
	}
	
	public double getExpense()
	{
		if(currentProposal == null)
		{
			return 0;
		}
		return Ulti.round(totalConsume * currentProposal.getSellPrice());
	}
}
