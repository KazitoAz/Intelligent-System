package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import models.Retailer;

public class RetailerAgent extends Agent
{
	protected Retailer retailer;
	private String homeAgent;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			retailer = (Retailer)args[0];
			homeAgent = (String)args[1];
			System.out.println(retailer.getName() + " is up.");
			addBehaviour(receiveBehaviour());
		}
	}
	
	protected Behaviour receiveBehaviour()
	{
		return new CyclicBehaviour()
		{
			
			@Override
			public void action()
			{
				ACLMessage msg = receive();
				if(msg!=null)
				{
					if(msg.getContent().contains("request") )
					{
						informProposal();
					}
				}
				
			}
		};
	}
	
	protected void informProposal()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgent, AID.ISLOCALNAME));
		msg.setContent(retailer.getProposal().toString());
		
		send(msg);
	}
}
