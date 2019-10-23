package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.FIPAProtocolNames;
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
			receivequote();
		}
	}
	
	protected Behaviour receiveBehaviour()
	{
		return new CyclicBehaviour()
		{
			
			@Override
			public void action()
			{
				MessageTemplate template = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
				ACLMessage msg = receive(template);
				if(msg!=null)
				{
					if(msg.getContent().contains("request") )
					{
						informProposal();
						
					}
					if(msg.getContent().contains("Send") )
					{
			        	String title = msg.getContent();
			            //ACLMessage reply = acl1.createReply();
			            String str = title.substring(17);
			            //int price = Integer.parseInt(str);
			        	if(str!=null)
			        	{
			        		System.out.println("receiving");
			        		System.out.println(str);
			        		
			        	}
					}
				}
				
			}
			
		};
	}
	
	private void receivequote()
	{
		ACLMessage acl1=receive();
		
        if(acl1!=null) {
        	//System.out.println("receiving");
        	String title = acl1.getContent();
            //ACLMessage reply = acl1.createReply();
            String str = title.substring(17);
            int price = Integer.parseInt(str);
        	if(price > 0)
        	{
        		System.out.println("receiving");
        	}
        }
	}
	protected void informProposal()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgent, AID.ISLOCALNAME));
		msg.setContent(retailer.getProposal().toString());
		msg.setProtocol(FIPAProtocolNames.FIPA_QUERY);
		send(msg);
	}
}
