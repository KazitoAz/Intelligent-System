package models;

import java.util.Random;

public class Retailer
{
	private String name;
	private boolean fixed;
	private double sellPricePerUnit;
	private double buyPricePerUnit;
	
	private double lowestAcceptableSellPrice;
	private double highestAcceptableBuyPrice;
	
	
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
		Random random = new Random();
		if(!fixed)
		{
			
			newSellPrice = (random.nextDouble()*(1.0f - 0.7f)+0.7f) * sellPricePerUnit;
			newBuyPrice = (random.nextDouble()*(1.0f - 0.7f)+0.7f) * buyPricePerUnit;
			
			lowestAcceptableSellPrice = Ulti.round(newSellPrice * (random.nextDouble()*(1.0f - 0.8f)+0.8f));
			
			highestAcceptableBuyPrice = Ulti.round(newBuyPrice *(random.nextDouble()*(1.2f - 1f)+1f));
			
		}
		else {
			
			lowestAcceptableSellPrice = Ulti.round(sellPricePerUnit * (random.nextDouble()*(1.0f - 0.8f)+0.8f));
			
			highestAcceptableBuyPrice = Ulti.round(buyPricePerUnit *(random.nextDouble()*(1.2f - 1f)+1f));
		}
		return new Proposal(name, Ulti.round(newSellPrice), Ulti.round(newBuyPrice));
	}
	
	public Boolean proppoaslAccepetable(Double sellPrice, Double buyPirce)
	{
		if(sellPrice >= lowestAcceptableSellPrice && buyPirce<= highestAcceptableBuyPrice)
			return true;
		else 
			return false;
	}
}
