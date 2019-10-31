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
import models.Ulti;

public class HomeAgent extends Agent
{
	private Home home;
	private String[] retailerAgents;
	private String[] applianceAgents;
	private boolean[] retailersAcceptProposal;
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
	private boolean responsed = true;
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
			retailersAcceptProposal = new boolean[retailerAgents.length];
			ResetRetailerQuoteAccpetStatus();
			for (int i = 0; i < applianceAgents.length; i++)
			{
				System.out.println("Added " + applianceAgents[i]);
			}
			gui = (Home1)args[3];
			System.out.println("Home agent is up.");
			addBehaviour(receiveBehaviour());
			requestApplicancePredictUsage(0);
		}
	}
	
	private void requestAppliancesUsage(int idx)
	{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(applianceAgents[idx], AID.ISLOCALNAME));
			msg.setContent("request usage");
			msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
			gui.SendMessage("Request Usage", getLocalName(), applianceAgents[idx], "from " + applianceAgents[idx], "", this);
			send(msg);
	}
	
	private void requestApplicancePredictUsage(int idx)
	{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(applianceAgents[idx], AID.ISLOCALNAME));
			msg.setContent("predict usage");
			msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
			
			gui.SendMessage("Request Prediction", getLocalName(), applianceAgents[idx], "from " + applianceAgents[idx], "", this);
			send(msg);
	}
	
	private void requestProposals(int idx)
	{
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			
			msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
			msg.addReceiver(new AID(retailerAgents[idx], AID.ISLOCALNAME));
			msg.setContent("request proposal");
			msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
			gui.SendMessage("Request proposal", getLocalName(), retailerAgents[idx], "from " + retailerAgents[idx], "", this);
			send(msg);
	}
	
	int getIndexOf(Object[] array, Object item)
	{
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(item))
				return i;
		}
		return 0;
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
				}
				
				MessageTemplate template2 = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_PROPOSE);
				ACLMessage retailerMsg = receive(template2);
				if(retailerMsg!=null)
				{
					if(retailerMsg.getContent().contains("proposal"))
					{
						ReadProposal(retailerMsg.getContent());
					}
					else if(retailerMsg.getContent().contains("accept"))
					{
						SetRetailerAcceptStatus(retailerMsg.getSender().getLocalName(), true);
						responsed = true;
						System.out.println("  --Accepted");
						
					}
					else if(retailerMsg.getContent().contains("reject"))
					{
						SetRetailerAcceptStatus(retailerMsg.getSender().getLocalName(), false);
						responsed = true;
						System.out.println("  --Rejected");
					}
				}
			}
		};
	}
	
	private void SetRetailerAcceptStatus(String name, Boolean accept)
	{
		for(int i =0; i < retailerAgents.length; i++)
		{
			if(retailerAgents[i].equals(name))
			{
				//System.out.println(accept);
				retailersAcceptProposal[i] = accept;
				break;
			}
		}
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
		if(applianceReportCount < applianceAgents.length)
		{
			System.out.println(applianceReportCount);
			requestAppliancesUsage(applianceReportCount);
		}
		else {
			PeriodSummary();
		}
	}
	
	private void ReadProposal(String msg)
	{
		String[] dataStrings = msg.split(",");
		for (int i = 0; i < retailerAgents.length; i++)
		{
			if(dataStrings[1].equals(retailerAgents[i]))
			{
				String _retailerName = dataStrings[1];
				double _sell = Double.parseDouble(dataStrings[2]);
				double _buy = Double.parseDouble(dataStrings[3]);
				proposals[i] = new Proposal(_retailerName, _sell , _buy);

				gui.UpdateRetailerValues(_retailerName, _sell , _buy);
				retailerReportCount++;
				if(retailerReportCount == retailerAgents.length)
				{
					retailerReportCount = 0;
					ChooseProposal();
				}
				else {
					requestProposals(retailerReportCount);
				}
				break;
			}
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
			gui.SetPredictedUsage(totalPredictUsage, totalPredictGenerate);
			requestProposals(0);
			predictReportCount = 0;
		}
		else {
			requestApplicancePredictUsage(predictReportCount);
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
		
		double usageDiff = Math.abs(totalPredictUsage) - Math.abs(_totalConsume);
		usageDiff = Ulti.round(usageDiff);
		
		gui.SetExceededExpense(usageDiff,home.getProposal().getSellPrice());
		
		applianceReportCount = 0;
		totalPredictGenerate = 0;
		totalPredictUsage = 0;
		requestApplicancePredictUsage(0);
	}
	
	private void ResetRetailerQuoteAccpetStatus()
	{
		for(int i =0; i <retailersAcceptProposal.length; i++)
		{
			retailersAcceptProposal[i] = false;
		}
	}
	
	private void Negotiate(String _name, double _buyPrice, double _sellPrice)
	{
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		
		msg.setSender(new AID(getLocalName(), AID.ISLOCALNAME));
		msg.addReceiver(new AID(_name, AID.ISLOCALNAME));
		msg.setContent("negotiate," + _sellPrice + "," + _buyPrice);
		msg.setProtocol(FIPAProtocolNames.FIPA_REQUEST);
		
		send(msg);
	}
	
	private void ChooseProposal()
	{
		Proposal bestProposal = null;
		if(gui.isStrategyOne())
			bestProposal = StrategyOne();
		else 
			bestProposal = StrategyTwo();
		
		home.SetProposal(bestProposal);
		gui.UpdateContract(bestProposal.getRetailerName());
		double predictedExpense = totalPredictUsage*bestProposal.getSellPrice();
		double predictedIncome = totalPredictGenerate*bestProposal.getBuyPrice();
		gui.SetPredictedIncome(predictedIncome, predictedExpense);
		
		System.out.println("Choose proposal: " + bestProposal.getRetailerName());
		ResetRetailerQuoteAccpetStatus();
		home.reset();
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------------------");
		requestAppliancesUsage(0);
	}
	
	private Proposal StrategyOne()
	{
		Proposal[] finalProposals = new Proposal[retailerAgents.length];
		ResetRetailerQuoteAccpetStatus();
		for(int i =0; i < retailerAgents.length; i++)
		{
			double _sellPrice = proposals[i].getSellPrice();
			double _buyPrice = proposals[i].getBuyPrice();
			System.out.println("Neogatiating with "+ retailerAgents[i]);
			System.out.println("Sell price: $" + _sellPrice + "/kwh | Buy price: $" + _buyPrice + "/kwh");
			_sellPrice = _sellPrice*0.7f; //drop sell price for 30%
			while(!retailersAcceptProposal[i])
			{
				if(responsed)
				{
					_sellPrice = Ulti.round(_sellPrice * 1.1f);//increase buy price by 10% if proposal rejected
					
					System.out.print(" Negotiating with Sell price: $" + _sellPrice + "/kwh | Buy price: $" + _buyPrice + "/kwh");
					gui.SendMessage("Negotiation", this.getLocalName(), retailerAgents[i], "Sell price: $" + _sellPrice + "/kwh" , "", this);
					Negotiate(retailerAgents[i], _buyPrice, _sellPrice);
					responsed = false;
				}
				receiveBehaviour().action();
			}
			if(_sellPrice > proposals[i].getSellPrice())
				_sellPrice =  proposals[i].getSellPrice();
			retailersAcceptProposal[i] = false;
			_buyPrice = _buyPrice*1.3f; //increase buy price for 30%
			
			while(!retailersAcceptProposal[i])
			{
				if(responsed)
				{
					_buyPrice = Ulti.round(_buyPrice*0.9f); //decrease sell price by 10% if proposal rejected
					
					System.out.print(" Negotiating with Sell price: $" + _sellPrice + "/kwh | Buy price: $" + _buyPrice + "/kwh");
					gui.SendMessage("Negotiation", this.getLocalName(), retailerAgents[i], "Buy price: $" + _buyPrice + "/kwh" , "", this);
					Negotiate(retailerAgents[i], _buyPrice, _sellPrice);
					responsed = false;
				}
				receiveBehaviour().action();
			}
			if(_buyPrice < proposals[i].getBuyPrice())
				_buyPrice =  proposals[i].getBuyPrice();
			finalProposals[i] = new Proposal(retailerAgents[i], _sellPrice, _buyPrice);
			gui.UpdateRetailerValues(retailerAgents[i], finalProposals[i].getSellPrice(), finalProposals[i].getBuyPrice());
			System.out.println("Final proposal Sell price: $" + _sellPrice + "/kwh | Buy price: $" + _buyPrice + "/kwh");
			System.out.println("------------------------------------");
		}
		
		return GetBestProposal(finalProposals);
	}
	
	private Proposal StrategyTwo()
	{
		Proposal[] finalProposals = new Proposal[retailerAgents.length];
		ResetRetailerQuoteAccpetStatus();
		for(int i =0; i < retailerAgents.length; i++)
		{
			double _sellPrice = proposals[i].getSellPrice();
			double _buyPrice = proposals[i].getBuyPrice();
			
			if(predictConsume > predictGenerate)
			{
				_sellPrice =Ulti.round( _sellPrice *0.7f);
				while(!retailersAcceptProposal[i])
				{
					if(responsed)
					{
						if(_buyPrice > proposals[i].getBuyPrice()*0.7f)
						{
							_buyPrice = Ulti.round(_buyPrice*0.9f);
						}
						else 
						{
							_sellPrice = Ulti.round(_sellPrice*1.1f);
							_buyPrice = Ulti.round(proposals[i].getBuyPrice()*0.9f);
						}
						gui.SendMessage("Negotiation", this.getLocalName(), retailerAgents[i], "Sell price: $" + _sellPrice + "/kwh" , "Buy price: $" + _buyPrice + "/kwh", this);
						Negotiate(retailerAgents[i], _buyPrice, _sellPrice);
						responsed = false;
					}
					receiveBehaviour().action();
				}
			}
			else 
			{
				_buyPrice = Ulti.round(_buyPrice *1.3f);
				while(!retailersAcceptProposal[i])
				{
					if(responsed)
					{
						if(_sellPrice < proposals[i].getSellPrice()*1.3f)
						{
							_sellPrice = Ulti.round(_sellPrice*1.1f);
						}
						else 
						{
							_buyPrice = Ulti.round(_buyPrice*0.9f);
							_sellPrice = Ulti.round(proposals[i].getSellPrice()*1.1f);
						}
						gui.SendMessage("Negotiation", this.getLocalName(), retailerAgents[i], "Sell price: $" + _sellPrice + "/kwh" , "Buy price: $" + _buyPrice + "/kwh", this);
						Negotiate(retailerAgents[i], _buyPrice, _sellPrice);
						responsed = false;
					}
					receiveBehaviour().action();
				}
			}
			if(_sellPrice > proposals[i].getSellPrice())
				_sellPrice =  proposals[i].getSellPrice();
			if(_buyPrice < proposals[i].getBuyPrice())
				_buyPrice =  proposals[i].getBuyPrice();
			finalProposals[i] = new Proposal(retailerAgents[i], _sellPrice, _buyPrice);
			gui.UpdateRetailerValues(retailerAgents[i], finalProposals[i].getSellPrice(), finalProposals[i].getBuyPrice());
			System.out.println("Final proposal Sell price: $" + _sellPrice + "/kwh | Buy price: $" + _buyPrice + "/kwh");
			System.out.println("------------------------------------");
		}
		
		return GetBestProposal(finalProposals);
	}
	
	private Proposal GetBestProposal(Proposal[] finalProposals)
	{
		Proposal bestProposal = finalProposals[1];
		double profit = home.getTotalConsume()*finalProposals[1].getSellPrice() + home.getTotalGenerate()*finalProposals[1].getBuyPrice();
		for (Proposal proposal : finalProposals)
		{
			if(proposal == bestProposal)
				continue;
			double proposalProfit = home.getTotalConsume()*proposal.getSellPrice() + home.getTotalGenerate()*proposal.getBuyPrice();
			if(proposalProfit > profit)
			{
				profit = proposalProfit;
				bestProposal = proposal;
			}
		}
		return bestProposal;
	}
}
