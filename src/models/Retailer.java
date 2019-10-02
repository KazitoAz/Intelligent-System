package models;

import java.util.Random;

public class Retailer
{
	private String name;
	private boolean isFixed;
	private double sellPricePerUnit;
	private double buyPricePerUnit;
	
	public Retailer(String _name,double _sellPrice, double _buyPrice, boolean fixedPrice)
	{
		name = _name;
		sellPricePerUnit = _sellPrice;
		buyPricePerUnit = _buyPrice;
		isFixed = fixedPrice;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Proposal getRandomProposal()
	{
		Random random = new Random();
		double newSellPrice = random.nextDouble()*(1.0f - 0.7f) * sellPricePerUnit;
		double newBuyPrice = random.nextDouble()*(1.0f - 0.7f) * buyPricePerUnit;
		
		return new Proposal(name, newSellPrice, newBuyPrice);
	}
}
