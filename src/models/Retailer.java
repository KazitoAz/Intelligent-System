package models;

import java.util.Random;

public class Retailer
{
	private String name;
	private boolean fixed;
	private double sellPricePerUnit;
	private double buyPricePerUnit;
	
	public Retailer(String _name,double _sellPrice, double _buyPrice, boolean fixedPrice)
	{
		name = _name;
		sellPricePerUnit = _sellPrice;
		buyPricePerUnit = _buyPrice;
		fixed = fixedPrice;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Proposal getProposal()
	{
		double newSellPrice = sellPricePerUnit;
		double newBuyPrice = buyPricePerUnit;
		if(!fixed)
		{
			Random random = new Random();
			newSellPrice = (random.nextDouble()*(1.0f - 0.7f)+0.7f) * sellPricePerUnit;
			
			newBuyPrice = (random.nextDouble()*(1.0f - 0.7f)+0.7f) * buyPricePerUnit;
		}
		return new Proposal(name, Ulti.round(newSellPrice), Ulti.round(newBuyPrice));
	}
	
	
}
