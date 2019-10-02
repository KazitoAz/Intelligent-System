package agents;

import FIPA.stringsHelper;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import models.Appliance;

public class ApplianceAgent extends Agent
{
	private Appliance appliance;
	private String homeAgentName;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			appliance = (Appliance)args[0];
			homeAgentName = (String)args[1];
			addBehaviour(receiveBehaviour());
			System.out.println(appliance.getName() + " is up.");
		}
	}
	
	private Behaviour applianceTickBehaviour()
	{
		return new TickerBehaviour(this, 1000)
		{
			
			@Override
			protected void onTick()
			{
				informCusuming();
				
			}
		};
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
					if(msg.getContent().contains("request"))
					{
						informCusuming();
						
					}
				}
				
			}
		};
	}
	
	private void informCusuming()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgentName, AID.ISLOCALNAME));
		String type = "consume,";
		double consumeRate = appliance.getConsumeRate();
		if(consumeRate > 0)
		{
			type = "generate,";
		}
		msg.setContent(type + consumeRate );
		System.out.println(appliance.getName() +": " +consumeRate);
		send(msg);
	}
}
