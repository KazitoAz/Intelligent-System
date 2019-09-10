package agents;

import jade.core.Agent;
import models.Retailer;

public class RetailerAgent extends Agent
{
	protected Retailer retailer;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			retailer = (Retailer)args[0];
		}
	}
}
