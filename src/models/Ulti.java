package models;

public abstract class Ulti
{
	public static double round(double number)
	{
		number = Math.round(number*100);
		number = number/100;
		return number;
	}
}
