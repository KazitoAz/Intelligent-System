package agents;

import jade.core.Agent;
import models.Appliance;
import models.Home;

public class HomeAgent extends Agent
{
	private Home home;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			home = (Home)args[0];
		}
	}
}
