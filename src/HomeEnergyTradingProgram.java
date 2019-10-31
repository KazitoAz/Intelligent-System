import java.util.Vector;

import gui.Home1;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import models.Appliance;
import models.Home;
import models.Retailer;

public class HomeEnergyTradingProgram
{

	public static void main(String[] args)
	{
		Runtime rt = Runtime.instance();
		
		//Create main container
		Profile p = new ProfileImpl();
		p.setParameter(Profile.MAIN_HOST, "localhost");
		p.setParameter(Profile.GUI, "true");
		jade.wrapper.AgentContainer mainContainer = rt.createMainContainer(p);
		
		//Create appliance agent container
		Profile applianceProfile = new ProfileImpl();
		applianceProfile.setParameter(Profile.CONTAINER_NAME, "ApplianceContainer");
		jade.wrapper.AgentContainer applianceContainer = rt.createAgentContainer(applianceProfile);
		
		//Create home agent container
		Profile homeProfile = new ProfileImpl();
		homeProfile.setParameter(Profile.CONTAINER_NAME, "HomeContainer");
		jade.wrapper.AgentContainer homeContainer = rt.createAgentContainer(homeProfile);
		
		//Create retailer agent container
		Profile retailerProfile = new ProfileImpl();
		retailerProfile.setParameter(Profile.CONTAINER_NAME, "RetailerContainer");
		jade.wrapper.AgentContainer retailerContainer = rt.createAgentContainer(retailerProfile);
		
		Home1 gui = new Home1();
		Appliance appliances[] = {
				new Appliance("SolarPanel", 5, false),
				new Appliance("AirConditioner", -2f,false),
				new Appliance("Refrigerator", -0.8f,true)
		};
		
		Retailer retailers[] = {
				new Retailer("AGL", 0.3f,0.25f,false),
				new Retailer("Origin",0.31f,0.24f,false),
				new Retailer("EnergyAustralia",0.32f,0.23f,true)
		};
		
		Object[][] applianceArgs = new Object[3][3];
		Object[][] retailerArgs = new Object[3][3];
		Home home = new Home();
		Object[] homeArg = new Object[4];
		homeArg[0] = home;
		for (int i = 0; i < 3; i++)
		{
			applianceArgs[i][0] = appliances[i];
			applianceArgs[i][1] = "HomeAgent";
			applianceArgs[i][2] = gui;
			retailerArgs[i][0] = retailers[i];
			retailerArgs[i][1] = "HomeAgent";
			retailerArgs[i][2] = gui;
		}
		
		//add retailers name into home arguments
		String[] retailersList = new String[retailers.length];
		for(int i =0; i< retailers.length;i++)
		{
			retailersList[i] = retailers[i].getName();
		}
		homeArg[1] = retailersList;
		
		//add appliance name into home arguments
		String[] appliancesList = new String[appliances.length];
		for(int i =0; i < appliances.length; i++)
		{
			appliancesList[i] = appliances[i].getName();
		}
		homeArg[2] = appliancesList;
		
		//setup JADE agents and containers
		AgentController rc;
		AgentController ac;
		AgentController hc;
		
		//initialize GUI and pass to home agent
		
		homeArg[3] = gui;
		gui.setVisible(true);
		try
		{
			for(int i =0; i <3; i++)
			{
				ac = applianceContainer.createNewAgent(appliances[i].getName(), "agents.ApplianceAgent", applianceArgs[i]);
				rc = retailerContainer.createNewAgent(retailers[i].getName(), "agents.RetailerAgent", retailerArgs[i]);
				ac.start();
				rc.start();
			}
			hc = homeContainer.createNewAgent("HomeAgent", "agents.HomeAgent", homeArg);
			hc.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
