package models;

public class Retailer
{
	private boolean isFixed;
	private float sellPricePerUnit;
	private float buyPricePerUnit;
	
	public Retailer(float _sellPrice, float _buyPrice, boolean fixedPrice)
	{
		sellPricePerUnit = _sellPrice;
		buyPricePerUnit = _buyPrice;
		isFixed = fixedPrice;
	}
}
