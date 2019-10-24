package agents;

import java.util.Iterator;
import java.util.Vector;

import FIPA.stringsHelper;
import gui.Home1;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.FIPAProtocolNames;
import models.Appliance;
import models.ApplianceRecord;
import models.Home;
import models.Proposal;

public class HomeAgent extends Agent
{
	private Home home;
	private String[] retailerAgents;
	private String[] applianceAgents;
	private double predictConsume;
	private double predictGenerate;
	private int reportInterval = 2;
	private int applianceReportCount = 0;
	private int retailerReportCount = 0;
	private Home1 gui;
	private Proposal[] proposals;
	private double totalPredictUsage= 0;
	private double totalPredictGenerate = 0;
	private int predictReportCount =0;
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
			proposals = new Proposal[retailerAgents.length];
			for (int i = 0; i < applianceAgents.length; i++)
			{
				System.out.println("Added " + applianceAgents[i]);
			}
			gui = (Home1)args[3];
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
					int _hour = Math.round(applianceReportCount/applianceAgents.length + 1);
					System.out.println("-Hour " +  _hour+ ":");
					gui.SetHour(_hour);
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
			msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
			
			send(msg);
		}
	}
	
	private void requestApplicancePredictUsage()
	{
		for (String applianceName : applianceAgents)
		{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(applianceName, AID.ISLOCALNAME));
			msg.setContent("predict usage");
			msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
			
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
			msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
			
			send(msg);
		}
	}
	
	private void Sendquote()
	{
		double sincome = home.getIncome();
		for (String retailerName : retailerAgents)
		{
			ACLMessage a = new ACLMessage(ACLMessage.REQUEST);
            AID r= new AID(retailerName, AID.ISLOCALNAME);
            a.addReceiver(r);   
            a.setSender(new AID(getLocalName(), AID.ISLOCALNAME));  
            a.setContent("Send new request:" + (sincome + 1)); 
            a.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
            send(a);
        }
	}
	
	
	private Behaviour receiveBehaviour()
	{
		return new CyclicBehaviour()
		{
			@Override
			public void action()
			{
				MessageTemplate template1 = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
				ACLMessage appilanceMsg = receive(template1);
				if(appilanceMsg!=null)
				{
					if(appilanceMsg.getContent().contains("advice"))
					{
						ReadApplianceAdviceMessage(appilanceMsg.getContent(), appilanceMsg.getSender().getLocalName());
					}
					else if(appilanceMsg.getContent().contains("predict"))
					{
						ReadAppliancePredictMessage(appilanceMsg.getContent());
					}
					
					if(applianceReportCount ==reportInterval*applianceAgents.length)
					{
						PeriodSummary();
					}
				}
				
				MessageTemplate template2 = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_PROPOSE);
				ACLMessage retailerMsg = receive(template2);
				if(retailerMsg!=null)
				{
					if(retailerMsg.getContent().contains("proposal"))
					{
						ReadProposal(retailerMsg.getContent());
					}
				}
			}
		};
	}
	
	private void ReadApplianceAdviceMessage(String msg, String _name)
	{
		String[] dataStrings = msg.split(",");
		double consumeAmount = Double.parseDouble(dataStrings[1]);
		if(consumeAmount<0)
			home.consume(consumeAmount);
		else
			home.generate(consumeAmount);
		
		gui.UpdateApplianceValues(_name, consumeAmount);
		applianceReportCount++;
	}
	
	private void ReadProposal(String msg)
	{
		String[] dataStrings = msg.split(",");
		for (int i = 0; i < retailerAgents.length; i++)
		{
			if(dataStrings[1].equals(retailerAgents[i]))
			{
				String _retailerName = dataStrings[1];
				double _buy = Double.parseDouble(dataStrings[2]);
				double _sell = Double.parseDouble(dataStrings[3]);
				proposals[i] = new Proposal(_retailerName,  _buy, _sell);
				gui.UpdateRetailerValues(_retailerName, _buy, _sell);
				retailerReportCount++;
				break;
			}
		}
		
		if(retailerReportCount == retailerAgents.length)
		{
			ChooseProposal();
		}
	}
	
	private void ReadAppliancePredictMessage(String msg)
	{
		String[] dataStrings = msg.split(",");
		double consumeAmount = Double.parseDouble(dataStrings[1]);
		if(consumeAmount<0)
			totalPredictUsage += consumeAmount;
		else {
			totalPredictGenerate += consumeAmount;
		}
		predictReportCount++;
		if(predictReportCount== retailerAgents.length)
		{
			totalPredictUsage = totalPredictUsage*reportInterval;
			totalPredictGenerate = totalPredictGenerate*reportInterval;
			gui.SetPredictedUsage(totalPredictUsage, totalPredictGenerate);
			requestProposals();
			
			Sendquote();
			predictReportCount = 0;
		}
	}
	
	private void PeriodSummary()
	{
		double _totalConsume = home.getTotalConsume();
		double _totalGenerate = home.getTotalGenerate();
		double _expense = home.getExpense();
		double _income = home.getIncome();
		
		System.out.println("Total consume: " + _totalConsume + "kwh | Expense: $" + _expense);
		System.out.println("Total generate: " + _totalGenerate + "kwh | Income: $" + _income);
		gui.UpdateHomeAgent(_totalConsume, _totalGenerate, _expense, _income);
		applianceReportCount = 0;
		totalPredictGenerate = 0;
		totalPredictUsage = 0;
		requestApplicancePredictUsage();
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
		gui.UpdateContract(bestProposal.getRetailerName());
		double predictedExpense = totalPredictUsage*bestProposal.getSellPrice();
		double predictedIncome = totalPredictGenerate*bestProposal.getBuyPrice();
		gui.SetPredictedIncome(predictedIncome, predictedExpense);
		
		System.out.println("Choose proposal: " + bestProposal.getRetailerName());
		retailerReportCount = 0;
		home.reset();
		System.out.println("------------------------------------");
		
	}
	
}
