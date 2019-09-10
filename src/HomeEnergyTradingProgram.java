import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
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
		
		
		Appliance appliances[] = {
				new Appliance("SolarPanel", -10),
				new Appliance("AirContinioner", 2.5f),
				new Appliance("Refrigerator", 1.2f)
		};
		
		Retailer retailers[] = {
				new Retailer("AGL", 0.2f,0.3f,true),
				new Retailer("Origin",0.21f,0.31f,true),
				new Retailer("EnergyAustralia",0.22f,0.32f,true)
		};
		
		Object[][] applianceArgs = new Object[3][1];
		Object[][] retailerArgs = new Object[3][1];
		
		for (int i = 0; i < 3; i++)
		{
			applianceArgs[i][0] = appliances[i];
			retailerArgs[i][0] = retailers[i];
		}
		
		Home home = new Home();
		Object[] homeArg = {home};
		
		AgentController rc;
		AgentController ac;
		AgentController hc;
		try
		{
			for(int i =0; i <3; i++)
			{
				ac = applianceContainer.createNewAgent(appliances[i].getName(), "agents.ApplianceAgent", applianceArgs[i]);
				rc = retailerContainer.createNewAgent(retailers[i].getName(), "agents.RetailerAgent", retailerArgs[i]);
				ac.start();
				rc.start();
			}
			hc = homeContainer.createNewAgent("HomeAgent1", "agents.HomeAgent", homeArg);
			
			hc.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
