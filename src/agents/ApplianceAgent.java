package agents;

import jade.core.Agent;
import models.Appliance;

public class ApplianceAgent extends Agent
{
	private Appliance appliance;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			appliance = (Appliance)args[0];
		}
	}
}
