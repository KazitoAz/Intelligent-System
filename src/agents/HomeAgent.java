package agents;

import java.util.Vector;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import models.Appliance;
import models.Home;

public class HomeAgent extends Agent
{
	private Home home;
	private Vector<String> retailerAgents;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			home = (Home)args[0];
			System.out.println("Home agent is up.");
			addBehaviour(receiveBehaviour());
		}
	}
	
	
	private Behaviour receiveBehaviour()
	{
		return new CyclicBehaviour()
		{
			
			@Override
			public void action()
			{
				ACLMessage msg = receive();
				if(msg!=null)
				{
					System.out.println("Home agent: Message recived from "+ msg.getSender().getName() + " :" + msg.getContent());
				}
				
			}
		};
	}
}
