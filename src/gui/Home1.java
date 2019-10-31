package gui;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import models.Ulti;
import models.Vector2;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.border.EtchedBorder;

import jade.core.Agent;
import jade.wrapper.AgentController;

public class Home1 extends JFrame {

	private JPanel contentPane;
	private JLabel lblSolarPanel;
	private JLabel lblRefrigerator;
	private JLabel airConValue;
	private JLabel solarPanelValue;
	private JLabel refrigeratorValue;
	private JLabel lblRetailAgents;
	private JLabel lblAgl;
	private JLabel lblOrigin;
	private JLabel lblEnergyAustralia;
	private JLabel lblSellPrices;
	private JLabel lblBuyPrices;
	private JLabel AGL_sellPrice;
	private JLabel origin_sellPrice;
	private JLabel enAus_sellPrice;
	private JLabel AGL_buyPrice;
	private JLabel origin_buyPrice;
	private JLabel enAus_buyPrice;
	private JLabel lblHomeAgent;
	private JLabel lblTotalConsume;
	private JLabel lblTotalGenerate;
	private JLabel total_consume;
	private JLabel total_generate;
	private JLabel lblExpense;
	private JLabel lblIncome;
	private JLabel expense;
	private JLabel income;
	private JLabel lblContractWith;
	private JLabel contract_company;
	private JLabel lblHour;
	private JLabel current_hour;
	private JLabel lblSellPriceAccept;
	private JLabel lblBuyPriceAccept;
	private JTextField sellPriceAcceptRangeMin;
	private JTextField buyPriceAcceptRangeMin;
	private JTextField sellPriceAcceptRangeMax;
	private JTextField buyPriceAcceptRangeMax;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblPredictedGenerate;
	private JLabel lblPredictedConsume;
	private JLabel predictedConsume;
	private JLabel predictedGenerate;
	private JLabel lblPaidExtra;
	private JLabel predictedExpense;
	private JLabel lblPredictedIncome;
	private JLabel predictedIncome;
	private JTextField exceededPrice;
	private JLabel expenseExceed;
	private JLabel predictedExpenseExceed;
	private JLabel airconIcon;
	private JLabel solarIcon;
	private JLabel homeIcon;
	private JLabel aglIcon;
	private JLabel originIcon;
	private JLabel enAuIcon;
	private JLabel line2;
	private JPanel mailPanel;
	private JLabel messageType;
	private JLabel line1;
	private JLabel refrigeratorIcon;
	private boolean playingAnimation = false;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home1 frame = new Home1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home1() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ReadConfig();
				mailPanel.setVisible(false);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				SaveConfig();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 789);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);

		JLabel lblApplianceAgents = new JLabel("Appliance Agents");
		lblApplianceAgents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApplianceAgents.setBounds(45, 334, 116, 25);
		contentPane.add(lblApplianceAgents);

		JLabel lblAirconditioner = new JLabel("Air Conditioner");
		lblAirconditioner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAirconditioner.setBounds(60, 370, 116, 25);
		contentPane.add(lblAirconditioner);

		lblSolarPanel = new JLabel("Solar Panel");
		lblSolarPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSolarPanel.setBounds(60, 406, 116, 25);
		contentPane.add(lblSolarPanel);

		lblRefrigerator = new JLabel("Refrigerator");
		lblRefrigerator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRefrigerator.setBounds(60, 441, 116, 25);
		contentPane.add(lblRefrigerator);

		airConValue = new JLabel("");
		airConValue.setForeground(Color.RED);
		airConValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		airConValue.setBounds(186, 370, 116, 25);
		contentPane.add(airConValue);

		solarPanelValue = new JLabel("");
		solarPanelValue.setForeground(new Color(60, 179, 113));
		solarPanelValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		solarPanelValue.setBounds(186, 406, 116, 25);
		contentPane.add(solarPanelValue);

		refrigeratorValue = new JLabel("");
		refrigeratorValue.setForeground(Color.RED);
		refrigeratorValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		refrigeratorValue.setBounds(186, 441, 116, 25);
		contentPane.add(refrigeratorValue);

		lblRetailAgents = new JLabel("Retail Agents");
		lblRetailAgents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRetailAgents.setBounds(365, 334, 116, 25);
		contentPane.add(lblRetailAgents);

		lblAgl = new JLabel("AGL");
		lblAgl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAgl.setBounds(375, 366, 116, 25);
		contentPane.add(lblAgl);

		lblOrigin = new JLabel("Origin");
		lblOrigin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrigin.setBounds(375, 402, 116, 25);
		contentPane.add(lblOrigin);

		lblEnergyAustralia = new JLabel("Energy Australia");
		lblEnergyAustralia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergyAustralia.setBounds(375, 436, 116, 25);
		contentPane.add(lblEnergyAustralia);

		lblSellPrices = new JLabel("Sell Prices");
		lblSellPrices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSellPrices.setBounds(480, 334, 116, 25);
		contentPane.add(lblSellPrices);

		lblBuyPrices = new JLabel("Buy Prices");
		lblBuyPrices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuyPrices.setBounds(617, 334, 116, 25);
		contentPane.add(lblBuyPrices);

		AGL_sellPrice = new JLabel("");
		AGL_sellPrice.setForeground(Color.RED);
		AGL_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AGL_sellPrice.setBounds(480, 366, 116, 25);
		contentPane.add(AGL_sellPrice);

		origin_sellPrice = new JLabel("");
		origin_sellPrice.setForeground(Color.RED);
		origin_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		origin_sellPrice.setBounds(480, 402, 116, 25);
		contentPane.add(origin_sellPrice);

		enAus_sellPrice = new JLabel("");
		enAus_sellPrice.setForeground(Color.RED);
		enAus_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enAus_sellPrice.setBounds(480, 436, 116, 25);
		contentPane.add(enAus_sellPrice);

		AGL_buyPrice = new JLabel("");
		AGL_buyPrice.setForeground(new Color(60, 179, 113));
		AGL_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AGL_buyPrice.setBounds(617, 366, 116, 25);
		contentPane.add(AGL_buyPrice);

		origin_buyPrice = new JLabel("");
		origin_buyPrice.setForeground(new Color(60, 179, 113));
		origin_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		origin_buyPrice.setBounds(617, 402, 116, 25);
		contentPane.add(origin_buyPrice);

		enAus_buyPrice = new JLabel("");
		enAus_buyPrice.setForeground(new Color(60, 179, 113));
		enAus_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enAus_buyPrice.setBounds(617, 436, 116, 25);
		contentPane.add(enAus_buyPrice);

		lblHomeAgent = new JLabel("Home Agent");
		lblHomeAgent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHomeAgent.setBounds(45, 498, 116, 25);
		contentPane.add(lblHomeAgent);

		lblTotalConsume = new JLabel("Actual Consume");
		lblTotalConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalConsume.setBounds(60, 558, 116, 25);
		contentPane.add(lblTotalConsume);

		lblTotalGenerate = new JLabel("Actual Generate");
		lblTotalGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalGenerate.setBounds(60, 594, 116, 25);
		contentPane.add(lblTotalGenerate);

		total_consume = new JLabel("");
		total_consume.setBackground(SystemColor.activeCaption);
		total_consume.setForeground(Color.RED);
		total_consume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		total_consume.setBounds(186, 558, 116, 25);
		contentPane.add(total_consume);

		total_generate = new JLabel("");
		total_generate.setBackground(SystemColor.activeCaption);
		total_generate.setForeground(new Color(60, 179, 113));
		total_generate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		total_generate.setBounds(186, 594, 116, 25);
		contentPane.add(total_generate);

		lblExpense = new JLabel("Expense");
		lblExpense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpense.setBounds(60, 630, 116, 25);
		contentPane.add(lblExpense);

		lblIncome = new JLabel("Income");
		lblIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIncome.setBounds(60, 666, 116, 25);
		contentPane.add(lblIncome);

		expense = new JLabel("");
		expense.setForeground(Color.RED);
		expense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expense.setBounds(186, 630, 50, 25);
		contentPane.add(expense);

		income = new JLabel("");
		income.setForeground(new Color(60, 179, 113));
		income.setFont(new Font("Tahoma", Font.PLAIN, 14));
		income.setBounds(186, 666, 80, 25);
		contentPane.add(income);

		lblContractWith = new JLabel("Contract With");
		lblContractWith.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContractWith.setBounds(60, 702, 116, 25);
		contentPane.add(lblContractWith);

		contract_company = new JLabel("");
		contract_company.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contract_company.setBounds(186, 702, 116, 25);
		contentPane.add(contract_company);

		lblHour = new JLabel("Hour");
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHour.setBounds(45, 298, 57, 25);
		contentPane.add(lblHour);

		current_hour = new JLabel("");
		current_hour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		current_hour.setBounds(85, 298, 116, 25);
		contentPane.add(current_hour);
		
		JLabel lblSmartHomeDashboard = new JLabel("Smart Home Dashboard");
		lblSmartHomeDashboard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSmartHomeDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmartHomeDashboard.setBounds(5, -1, 675, 43);
		contentPane.add(lblSmartHomeDashboard);
		
		lblSellPriceAccept = new JLabel("Sell Price Accept Range");
		lblSellPriceAccept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSellPriceAccept.setBounds(60, 522, 151, 25);
		contentPane.add(lblSellPriceAccept);
		
		lblBuyPriceAccept = new JLabel("Buy Price Accept Range");
		lblBuyPriceAccept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuyPriceAccept.setBounds(364, 522, 151, 25);
		contentPane.add(lblBuyPriceAccept);
		
		sellPriceAcceptRangeMin = new JTextField();
		sellPriceAcceptRangeMin.setBackground(SystemColor.control);
		sellPriceAcceptRangeMin.setBounds(203, 526, 57, 20);
		contentPane.add(sellPriceAcceptRangeMin);
		sellPriceAcceptRangeMin.setColumns(10);
		
		buyPriceAcceptRangeMin = new JTextField();
		buyPriceAcceptRangeMin.setColumns(10);
		buyPriceAcceptRangeMin.setBackground(SystemColor.menu);
		buyPriceAcceptRangeMin.setBounds(511, 526, 57, 20);
		contentPane.add(buyPriceAcceptRangeMin);
		
		sellPriceAcceptRangeMax = new JTextField();
		sellPriceAcceptRangeMax.setColumns(10);
		sellPriceAcceptRangeMax.setBackground(SystemColor.menu);
		sellPriceAcceptRangeMax.setBounds(270, 526, 57, 20);
		contentPane.add(sellPriceAcceptRangeMax);
		
		buyPriceAcceptRangeMax = new JTextField();
		buyPriceAcceptRangeMax.setColumns(10);
		buyPriceAcceptRangeMax.setBackground(SystemColor.menu);
		buyPriceAcceptRangeMax.setBounds(583, 526, 57, 20);
		contentPane.add(buyPriceAcceptRangeMax);
		
		label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(260, 522, 10, 25);
		contentPane.add(label);
		
		label_1 = new JLabel("-");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(569, 522, 10, 25);
		contentPane.add(label_1);
		
		lblPredictedGenerate = new JLabel("Predicted  Generate");
		lblPredictedGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictedGenerate.setBounds(365, 594, 126, 25);
		contentPane.add(lblPredictedGenerate);
		
		lblPredictedConsume = new JLabel("Predicted Consume");
		lblPredictedConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictedConsume.setBounds(365, 558, 130, 25);
		contentPane.add(lblPredictedConsume);
		
		predictedConsume = new JLabel("");
		predictedConsume.setForeground(Color.RED);
		predictedConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedConsume.setBackground(SystemColor.activeCaption);
		predictedConsume.setBounds(511, 558, 116, 25);
		contentPane.add(predictedConsume);
		
		predictedGenerate = new JLabel("");
		predictedGenerate.setForeground(new Color(60, 179, 113));
		predictedGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedGenerate.setBackground(SystemColor.activeCaption);
		predictedGenerate.setBounds(511, 594, 116, 25);
		contentPane.add(predictedGenerate);
		
		lblPaidExtra = new JLabel("Predicted Expense");
		lblPaidExtra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaidExtra.setBounds(365, 630, 116, 25);
		contentPane.add(lblPaidExtra);
		
		predictedExpense = new JLabel("");
		predictedExpense.setForeground(Color.RED);
		predictedExpense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedExpense.setBounds(511, 630, 50, 25);
		contentPane.add(predictedExpense);
		
		lblPredictedIncome = new JLabel("Predicted Income");
		lblPredictedIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictedIncome.setBounds(365, 666, 116, 25);
		contentPane.add(lblPredictedIncome);
		
		predictedIncome = new JLabel("");
		predictedIncome.setForeground(new Color(60, 179, 113));
		predictedIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedIncome.setBounds(511, 666, 68, 25);
		contentPane.add(predictedIncome);
		
		JLabel lblExceededEnergyPrice = new JLabel("Exceeded energy price");
		lblExceededEnergyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExceededEnergyPrice.setBounds(365, 298, 143, 25);
		contentPane.add(lblExceededEnergyPrice);
		
		exceededPrice = new JTextField();
		exceededPrice.setColumns(10);
		exceededPrice.setBackground(SystemColor.menu);
		exceededPrice.setBounds(511, 302, 57, 20);
		contentPane.add(exceededPrice);
		
		expenseExceed = new JLabel("");
		expenseExceed.setForeground(Color.RED);
		expenseExceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expenseExceed.setBounds(233, 630, 122, 25);
		contentPane.add(expenseExceed);
		
		predictedExpenseExceed = new JLabel("");
		predictedExpenseExceed.setForeground(Color.RED);
		predictedExpenseExceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedExpenseExceed.setBounds(560, 630, 109, 25);
		contentPane.add(predictedExpenseExceed);
		
		refrigeratorIcon = new JLabel("");
		refrigeratorIcon.setBounds(35, 213, 74, 74);
		contentPane.add(refrigeratorIcon);
		Image refrigeratorImg =  new ImageIcon(this.getClass().getResource("/refrigerator.png")).getImage(); 
		refrigeratorIcon.setIcon(new ImageIcon(refrigeratorImg));
		
		airconIcon = new JLabel("");
		airconIcon.setBounds(35, 53, 74, 74);
		contentPane.add(airconIcon);
		Image airconImg =  new ImageIcon(this.getClass().getResource("/aircon.png")).getImage(); 
		airconIcon.setIcon(new ImageIcon(airconImg));
		
		solarIcon = new JLabel("");
		solarIcon.setBounds(35, 133, 74, 74);
		contentPane.add(solarIcon);
		Image solarPanelImg =  new ImageIcon(this.getClass().getResource("/solar.png")).getImage(); 
		solarIcon.setIcon(new ImageIcon(solarPanelImg));
		
		homeIcon = new JLabel("");
		homeIcon.setBounds(327, 133, 74, 74);
		contentPane.add(homeIcon);
		Image homeImg =  new ImageIcon(this.getClass().getResource("/home.png")).getImage(); 
		homeIcon.setIcon(new ImageIcon(homeImg));
		
		aglIcon = new JLabel("");
		aglIcon.setBounds(606, 53, 74, 74);
		contentPane.add(aglIcon);
		Image aglImg =  new ImageIcon(this.getClass().getResource("/agl.png")).getImage(); 
		aglIcon.setIcon(new ImageIcon(aglImg));
		
		originIcon = new JLabel("");
		originIcon.setBounds(606, 133, 74, 74);
		contentPane.add(originIcon);
		Image originImg =  new ImageIcon(this.getClass().getResource("/origin.png")).getImage(); 
		originIcon.setIcon(new ImageIcon(originImg));
		
		enAuIcon = new JLabel("");
		enAuIcon.setBounds(606, 213, 74, 74);
		contentPane.add(enAuIcon);
		Image enAuImg =  new ImageIcon(this.getClass().getResource("/enAu.png")).getImage(); 
		enAuIcon.setIcon(new ImageIcon(enAuImg));
		
		mailPanel = new JPanel();
		mailPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mailPanel.setBounds(186, 237, 180, 49);
		contentPane.add(mailPanel);
		
		messageType = new JLabel("Negotiate");
		messageType.setBounds(2, 2, 168, 14);
		
		line1 = new JLabel("Buy Price: $0.35/kwh");
		line1.setBounds(2, 16, 168, 14);
		line1.setVerticalAlignment(SwingConstants.TOP);
		
		line2 = new JLabel("Buy Price: $0.35/kwh");
		line2.setBounds(2, 30, 168, 14);
		line2.setVerticalAlignment(SwingConstants.TOP);
		mailPanel.setLayout(null);
		mailPanel.add(line1);
		mailPanel.add(messageType);
		mailPanel.add(line2);
	}
	
	void SaveConfig()
	{
		try (OutputStream output = new FileOutputStream("resources/config.properties")) {

            Properties prop = new Properties();
            prop.setProperty("buyAcceptRangeMin", buyPriceAcceptRangeMin.getText());
            prop.setProperty("buyAcceptRangeMax", buyPriceAcceptRangeMax.getText());
            prop.setProperty("sellAcceptRangeMin", sellPriceAcceptRangeMin.getText());
            prop.setProperty("sellAcceptRangeMax", sellPriceAcceptRangeMax.getText());
            prop.setProperty("exceededPrice", exceededPrice.getText());
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

	}
	
	void ReadConfig()
	{
		System.out.println("Reading");
		try (InputStream input = new FileInputStream("resources/config.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            buyPriceAcceptRangeMin.setText(prop.getProperty("buyAcceptRangeMin"));
            buyPriceAcceptRangeMax.setText(prop.getProperty("buyAcceptRangeMax"));
            sellPriceAcceptRangeMin.setText(prop.getProperty("sellAcceptRangeMin"));
            sellPriceAcceptRangeMax.setText(prop.getProperty("sellAcceptRangeMax"));
            exceededPrice.setText(prop.getProperty("exceededPrice"));

        } catch (IOException ex) {
        	 buyPriceAcceptRangeMin.setText("0");
             buyPriceAcceptRangeMax.setText("0.32");
             sellPriceAcceptRangeMin.setText("0.25");
             sellPriceAcceptRangeMax.setText("0.4");
             exceededPrice.setText("0.65");
            ex.printStackTrace();
        }
	}
	public void UpdateApplianceValues(String _name, Double value) {
		switch (_name) {
		case "SolarPanel":
			solarPanelValue.setText(value.toString() + "kwh");
			break;
		case "AirConditioner":
			airConValue.setText(value.toString() + "kwh");
			break;
		case "Refrigerator":
			refrigeratorValue.setText(value.toString() + "kwh");
			break;
		default:
			break;
		}
	}

	public void UpdateRetailerValues(String _name, Double _sell, Double _buy) {
		switch (_name) {
		case "AGL":
			AGL_buyPrice.setText("$" + _buy.toString() + "/kwh");
			AGL_sellPrice.setText("$" + _sell.toString() + "/kwh");
			break;
		case "Origin":
			origin_buyPrice.setText("$" + _buy.toString() + "/kwh");
			origin_sellPrice.setText("$" + _sell.toString() + "/kwh");
			break;
		case "EnergyAustralia":
			enAus_buyPrice.setText("$" + _buy.toString() + "/kwh");
			enAus_sellPrice.setText("$" + _sell.toString() + "/kwh");
			break;
		default:
			break;
		}
	}
	
	public void UpdateHomeAgent(Double _totalConsume,Double _totalGenerate,Double _expense,Double _income)
	{
		total_consume.setText(_totalConsume.toString() + "kwh");
		total_generate.setText(_totalGenerate.toString() + "kwh");
		expense.setText("$" + _expense.toString());
		income.setText("$" +_income.toString());
	}
	
	public void UpdateContract(String _contract)
	{
		contract_company.setText(_contract);
	}

	public void SetHour(Integer _hourValue) {
		current_hour.setText(_hourValue.toString());
	}
	
	public void SetPredictedUsage(Double consume, Double generate)
	{
		consume = Ulti.round(consume);
		generate = Ulti.round(generate);
		predictedConsume.setText(consume.toString()+ "kwh");
		predictedGenerate.setText(generate.toString()+ "kwh");
	}
	
	public boolean isPlayingAnimation()
	{
		return playingAnimation;
	}
	
	public void SetExceededExpense(Double amount, Double contractSellPrice)
	{
		
		if(amount < 0)
		{
			amount = amount * Double.parseDouble(exceededPrice.getText());
			amount = Ulti.round(amount);
			expenseExceed.setText("+$" + amount.toString()+ "(Exceed fee)");
			predictedExpenseExceed.setText("");
		}
		else 
		{
			amount = Math.abs(amount);
			amount*= contractSellPrice;
			amount = Ulti.round(amount);
			predictedExpenseExceed.setText("+$" + amount.toString() + "(Money paid extra)");
			expenseExceed.setText("");
		}
	}
	
	public void SetPredictedIncome(Double income, Double expense)
	{
		income = Ulti.round(income);
		expense = Ulti.round(expense);
		predictedExpense.setText("$" + expense.toString());
		predictedIncome.setText("$" + income.toString());
	}
	
	public double getMaxSellPrice()
	{
		return Double.parseDouble(sellPriceAcceptRangeMax.getText());
	}
	
	public double getMinBuyPrice()
	{
		return Double.parseDouble(buyPriceAcceptRangeMin.getText());
	}
	
	public void SendMessage(String title, String from, String to, String content1, String content2,  Agent a)
	{
		long startTime = System.currentTimeMillis();
		long estimatedTime = 0;
		Vector2 startPoint = getContentPosition(from);
		Vector2 endPoint = getContentPosition(to);
		messageType.setText(title);
		line1.setText(content1);
		line2.setText(content2);
		mailPanel.setVisible(true);
		double distance = Vector2.Distance(startPoint, endPoint);
		Vector2 direction = Vector2.getDirection(startPoint, endPoint).normailized();
		mailPanel.setBounds((int)startPoint.x, (int)startPoint.y, mailPanel.getBounds().width, mailPanel.getBounds().height);
		playingAnimation = true;
		while(Vector2.Distance(mailPanel.bounds().getCenterX(), mailPanel.bounds().getCenterY(), endPoint.x, endPoint.y) > 1 && estimatedTime < 2000)
		{
			estimatedTime = System.currentTimeMillis() - startTime;
			double nextDis = (estimatedTime/2000f) * distance;
			Vector2 nextPos = Vector2.multiply(direction, nextDis);
			nextPos.add(startPoint);
			mailPanel.setBounds((int)nextPos.x, (int)nextPos.y, mailPanel.getBounds().width, mailPanel.getBounds().height);
		}
		playingAnimation = false;
		mailPanel.setVisible(false);
	}
	
	Vector2 getContentPosition(String name) // get item position in GUI
	{
		switch (name) {
		case "SolarPanel":
			return new Vector2(solarIcon.bounds().getCenterX(), solarIcon.bounds().getCenterY());
		case "AirConditioner":
			return new Vector2(airconIcon.bounds().getCenterX(), airconIcon.bounds().getCenterY());
		case "Refrigerator":
			return new Vector2(refrigeratorIcon.bounds().getCenterX(), refrigeratorIcon.bounds().getCenterY());
		case "AGL":
			return new Vector2(aglIcon.bounds().getCenterX(), aglIcon.bounds().getCenterY());
		case "Origin":
			return new Vector2(originIcon.bounds().getCenterX(), originIcon.bounds().getCenterY());
		case "EnergyAustralia":
			return new Vector2(enAuIcon.bounds().getCenterX(), enAuIcon.bounds().getCenterY());
		case "HomeAgent":
			return new Vector2(homeIcon.bounds().getCenterX(), homeIcon.bounds().getCenterY());
		default:
			return null;
		}
	}
}


