package models;

public class Proposal
{
	private String retailerString;
	
	private double sellPricePerUnit;
	
	private double buyPricePerUnit;
	
	public Proposal(String _retailer, double _sellPrice, double _buyPrice)
	{
		retailerString = _retailer;
		sellPricePerUnit = _sellPrice;
		buyPricePerUnit = _buyPrice;
	}
	
	public double getSellPrice()
	{
		return sellPricePerUnit;
	}
	
	public double getBuyPrice()
	{
		return buyPricePerUnit;
	}
	
	public String toString()
	{
		String msg = "";
		
		msg = retailerString+",sellPrice="+sellPricePerUnit+",buyPrice="+buyPricePerUnit;
		
		return msg;
	}
}
