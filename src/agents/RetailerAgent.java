package agents;

import gui.Home1;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.FIPAProtocolNames;
import models.Proposal;
import models.Retailer;

public class RetailerAgent extends Agent
{
	protected Retailer retailer;
	private String homeAgent;
	private Home1 gui;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			retailer = (Retailer)args[0];
			homeAgent = (String)args[1];
			gui = (Home1)args[2];
			System.out.println(retailer.getName() + " is up.");
			addBehaviour(receiveBehaviour());
			//receivequote();
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
					else if(msg.getContent().contains("negotiate"))
					{
						readProposal(msg.getContent());
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
	
	private void readProposal(String msg)
	{
		String[] dataString = msg.split(",");
		double _buyPrice = Double.parseDouble(dataString[2]);
		double _sellPrice = Double.parseDouble(dataString[1]);
		if(retailer.proppoaslAccepetable(_sellPrice, _buyPrice))
		{
			acceptProposal();
		}
		else 
		{
			rejectProposal();
		}
	}
	
	void acceptProposal()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgent, AID.ISLOCALNAME));
		msg.setContent("accept");
		msg.setProtocol(FIPAProtocolNames.FIPA_PROPOSE);
		gui.SendMessage("Accept", getLocalName(), homeAgent, "","", this);
		send(msg);
	}
	
	void rejectProposal()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgent, AID.ISLOCALNAME));
		msg.setContent("reject");
		msg.setProtocol(FIPAProtocolNames.FIPA_PROPOSE);
		gui.SendMessage("Reject", getLocalName(), homeAgent, "","", this);
		send(msg);
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
        		//System.out.println("receiving");
        	}
        }
	}
	protected void informProposal()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgent, AID.ISLOCALNAME));
		
		Proposal proposal = retailer.getProposal();
		msg.setContent(proposal.toString());
		msg.setProtocol(FIPAProtocolNames.FIPA_PROPOSE);
		gui.SendMessage("Send proposal", getLocalName(), homeAgent, "Sell Price: $" + proposal.getSellPrice()+"/kwh","Buy Price: $" + proposal.getBuyPrice()+"/kwh", this);
		send(msg);
	}
}
