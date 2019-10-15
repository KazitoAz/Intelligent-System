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
import models.ApplianceRecord;
import models.Home;
import models.Proposal;

public class HomeAgent extends Agent
{
	private Home home;
	private String[] retailerAgents;
	private String[] applianceAgents;
	private ApplianceRecord[] applianceRecords;
	private double predictConsume;
	private double predictGenerate;
	private int reportInterval = 2;
	private int applianceReportCount = 0;
	private int retailerReportCount = 0;
	
	private Proposal[] proposals;
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		if(args!=null)
		{
			home = (Home)args[0];
			retailerAgents = (String[]) args[1];
			
			applianceAgents = (String[]) args[2];
			applianceRecords = new ApplianceRecord[applianceAgents.length];
			
			for (int i = 0; i < retailerAgents.length; i++)
			{
				System.out.println("Added " + retailerAgents[i]);
			}
			proposals = new Proposal[retailerAgents.length];
			for (int i = 0; i < applianceAgents.length; i++)
			{
				applianceRecords[i] = new ApplianceRecord(applianceAgents[i]);
				System.out.println("Added " + applianceAgents[i]);
			}
			requestProposals();
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
				if((applianceReportCount+applianceAgents.length) % applianceAgents.length == 0)
				{
					System.out.println("-Hour " + Math.round(applianceReportCount/applianceAgents.length + 1) + ":");
				}
				requestAppliancesUsage();
				
			}
		};
	}
	
	private void requestAppliancesUsage()
	{
		for (String applianceName : applianceAgents)
		{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(applianceName, AID.ISLOCALNAME));
			msg.setContent("request usage");
			
			send(msg);
		}
	}
	
	private void requestProposals()
	{
		for (String retailerName : retailerAgents)
		{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(retailerName, AID.ISLOCALNAME));
			msg.setContent("request proposal");
			
			send(msg);
		}
	}
	/*
	private void Sendquote()
	{
		for (String retailerName : retailerAgents)
		{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(retailerName, AID.ISLOCALNAME));
			msg.setContent(home.getTotalConsume()*proposals[1].getSellPrice());
			send(msg);
	}
	*/
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
						double consumeAmount = Double.parseDouble(dataStrings[1]);
						home.consume(consumeAmount);
						for (ApplianceRecord aRecord : applianceRecords) 
						{
							if(aRecord.getName().equals(msg.getSender().getLocalName()))
							{
								aRecord.AddToRecord(consumeAmount);
							}
						}
						applianceReportCount++;
					}
					else if(msg.getContent().contains("generate"))
					{
						String[] dataStrings = msg.getContent().split(",");
						double generateAmount = Double.parseDouble(dataStrings[1]);
						home.generate(generateAmount);
						for (ApplianceRecord aRecord : applianceRecords) 
						{
							if(aRecord.getName().equals(msg.getSender().getLocalName()))
							{
								aRecord.AddToRecord(generateAmount);
							}
						}
						applianceReportCount++;
					}
					else if(msg.getContent().contains("proposal"))
					{
						String[] dataStrings = msg.getContent().split(",");
						for (int i = 0; i < retailerAgents.length; i++)
						{
							if(dataStrings[1].equals(retailerAgents[i]))
							{
								proposals[i] = new Proposal(dataStrings[1], Double.parseDouble(dataStrings[2]) , Double.parseDouble(dataStrings[3]));
								retailerReportCount++;
								break;
							}
						}
					}
					
					if(retailerReportCount == retailerAgents.length)
					{
						ChooseProposal();
					}
					
					if(applianceReportCount ==reportInterval*applianceAgents.length)
					{
						System.out.println("Total consume: " + home.getTotalConsume() + "kwh | Expense: $" + home.getExpense());
						System.out.println("Total generate: " + home.getTotalGenerate() + "kwh | Income: $" + home.getIncome());
						applianceReportCount = 0;
						requestProposals();
					}
				}
				
			}
		};
	}
	
	private double PredictUsage()
	{
		double totalUsage = 0;
		
		for (ApplianceRecord aRecord : applianceRecords) 
		{
			totalUsage+=aRecord.GetAverageUsage();
		}
		
		return totalUsage;
	}
	
	
	private void ChooseProposal()
	{
		Proposal bestProposal = proposals[1];
		double profit = home.getTotalConsume()*proposals[1].getSellPrice() + home.getTotalGenerate()*proposals[1].getBuyPrice();
		
		for (Proposal proposal : proposals)
		{
			System.out.println(proposal.toPrintMessage());
			if(proposal == bestProposal)
				continue;
			double proposalProfit = home.getTotalConsume()*proposal.getSellPrice() + home.getTotalGenerate()*proposal.getBuyPrice();
			if(proposalProfit > profit)
			{
				profit = proposalProfit;
				bestProposal = proposal;
			}
		}
		home.SetProposal(bestProposal);
		System.out.println("Choose proposal: " + bestProposal.getRetailerName());
		retailerReportCount = 0;
		home.reset();
		System.out.println("------------------------------------");
		
	}
	
}
