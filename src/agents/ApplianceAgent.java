package agents;

import FIPA.stringsHelper;
import gui.Home1;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.FIPAProtocolNames;
import models.Appliance;
import models.ApplianceRecord;

public class ApplianceAgent extends Agent
{
	private Appliance appliance;
	private String homeAgentName;
	private ApplianceRecord records = new ApplianceRecord();
	private Home1 gui;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			appliance = (Appliance)args[0];
			homeAgentName = (String)args[1];
			gui = (Home1)args[2];
			addBehaviour(receiveBehaviour());
			records.AddToRecord(appliance.getConsumeRate());
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
				informConsuming();
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
				MessageTemplate template = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
				ACLMessage msg = receive(template);
				if(msg!=null)
				{
					if(msg.getContent().contains("request"))
					{
						informConsuming();
					}
					if(msg.getContent().contains("predict"))
					{
						informPredictConsuming();
					}
				}
			}
		};
	}
	
	private void informConsuming()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgentName, AID.ISLOCALNAME));
		msg.setProtocol(FIPAProtocolNames.FIPA_QUERY);
		
		Double consumeRate = appliance.getConsumeRate();
		records.AddToRecord(consumeRate);
		msg.setContent("advice," + consumeRate );
		System.out.println(appliance.getName() +": " +consumeRate +"kwh");
		String s = "Generated ";
		if(consumeRate < 0)
			s = "Consumed";
		consumeRate = Math.abs(consumeRate);
		gui.SendMessage("Sending consuming", getLocalName(), homeAgentName, s + consumeRate.toString() + "kwh", "", this);
		send(msg);
	}
	private void informPredictConsuming()
	{
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(homeAgentName, AID.ISLOCALNAME));
		msg.setProtocol(FIPAProtocolNames.FIPA_QUERY);
		
		Double predictedConsume = records.PredictUsage();
		msg.setContent("predict," + predictedConsume);
		String s = "Generated ";
		if(predictedConsume < 0)
			s = "Consumed ";
		predictedConsume = Math.abs(predictedConsume);
		gui.SendMessage("Sending prediction", getLocalName(), homeAgentName, s + predictedConsume.toString() +"kwh", "", this);
		send(msg);
	}
}
