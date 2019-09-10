package models;

public class Retailer
{
	private String name;
	private boolean isFixed;
	private float sellPricePerUnit;
	private float buyPricePerUnit;
	
	public Retailer(String _name,float _sellPrice, float _buyPrice, boolean fixedPrice)
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
	
	
}
