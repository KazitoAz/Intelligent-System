package agents;

import java.util.Iterator;
import java.util.Vector;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import models.Appliance;
import models.Home;

public class HomeAgent extends Agent
{
	private Home home;
	private String[] retailerAgents;
	private String[] applianceAgents;
	
	private int reportInterval = 2;
	private int agentReportCount = 0;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			home = (Home)args[0];
			retailerAgents = (String[]) args[1];
			applianceAgents = (String[]) args[2];
			
			for (int i = 0; i < retailerAgents.length; i++)
			{
				System.out.println("Added " + retailerAgents[i]);
			}
			
			for (int i = 0; i < applianceAgents.length; i++)
			{
				System.out.println("Added " + applianceAgents[i]);
			}
			
			System.out.println("Home agent is up.");
			addBehaviour(homeAgentTickBehaviour());
			addBehaviour(receiveBehaviour());
		}
	}
	
	private Behaviour homeAgentTickBehaviour()
	{
		return new TickerBehaviour(this, 1000)
		{
			
			@Override
			protected void onTick()
			{
				if((agentReportCount+applianceAgents.length) % applianceAgents.length == 0)
				{
					System.out.println("-Hour " + Math.round(agentReportCount/applianceAgents.length + 1) + ":");
				}
				for (String applianceName : applianceAgents)
				{
					requestAppliancesUsage(applianceName);
				}
				
			}
		};
	}
	
	private void requestAppliancesUsage(String applianceName)
	{
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(applianceName, AID.ISLOCALNAME));
		msg.setContent("request usage");
		
		send(msg);
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
					
					
					if(msg.getContent().contains("consume"))
					{
						String[] dataStrings = msg.getContent().split(",");
						home.consume(Double.parseDouble(dataStrings[1]));
						agentReportCount++;
					}
					else if(msg.getContent().contains("generate"))
					{
						String[] dataStrings = msg.getContent().split(",");
						home.generate(Double.parseDouble(dataStrings[1]));
						agentReportCount++;
					}
					
					if(agentReportCount ==reportInterval*applianceAgents.length)
					{
						System.out.println("Total consume: " + home.getTotalConsume());
						System.out.println("Total generate: " + home.getTotalGenerate());
						home.reset();
						agentReportCount = 0;
						System.out.println("------------------------------------");
					}
				}
				
			}
		};
	}
}
