package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Ulti;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("Price", 25, 84);
		g.drawString("Hour", 643, 320);
		
		g.drawLine(25, 90, 25, 320);
		g.drawLine(25, 320, 640, 320);
		
		//repaint();
	}

	/**
	 * Create the frame.
	 */
	public Home1() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				ReadConfig();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				SaveConfig();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 790);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(5, 41, 675, 255);
		contentPane.add(panel);
		contentPane.setVisible(true);

		JLabel lblApplianceAgents = new JLabel("Appliance Agents");
		lblApplianceAgents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApplianceAgents.setBounds(10, 342, 116, 25);
		contentPane.add(lblApplianceAgents);

		JLabel lblAirconditioner = new JLabel("Air Conditioner");
		lblAirconditioner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAirconditioner.setBounds(25, 378, 116, 25);
		contentPane.add(lblAirconditioner);

		lblSolarPanel = new JLabel("Solar Panel");
		lblSolarPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSolarPanel.setBounds(25, 414, 116, 25);
		contentPane.add(lblSolarPanel);

		lblRefrigerator = new JLabel("Refrigerator");
		lblRefrigerator.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRefrigerator.setBounds(25, 449, 116, 25);
		contentPane.add(lblRefrigerator);

		airConValue = new JLabel("");
		airConValue.setForeground(Color.RED);
		airConValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		airConValue.setBounds(151, 378, 116, 25);
		contentPane.add(airConValue);

		solarPanelValue = new JLabel("");
		solarPanelValue.setForeground(new Color(60, 179, 113));
		solarPanelValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		solarPanelValue.setBounds(151, 414, 116, 25);
		contentPane.add(solarPanelValue);

		refrigeratorValue = new JLabel("");
		refrigeratorValue.setForeground(Color.RED);
		refrigeratorValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		refrigeratorValue.setBounds(151, 449, 116, 25);
		contentPane.add(refrigeratorValue);

		lblRetailAgents = new JLabel("Retail Agents");
		lblRetailAgents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRetailAgents.setBounds(330, 342, 116, 25);
		contentPane.add(lblRetailAgents);

		lblAgl = new JLabel("AGL");
		lblAgl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAgl.setBounds(340, 374, 116, 25);
		contentPane.add(lblAgl);

		lblOrigin = new JLabel("Origin");
		lblOrigin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOrigin.setBounds(340, 410, 116, 25);
		contentPane.add(lblOrigin);

		lblEnergyAustralia = new JLabel("Energy Australia");
		lblEnergyAustralia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnergyAustralia.setBounds(340, 444, 116, 25);
		contentPane.add(lblEnergyAustralia);

		lblSellPrices = new JLabel("Sell Prices");
		lblSellPrices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSellPrices.setBounds(445, 342, 116, 25);
		contentPane.add(lblSellPrices);

		lblBuyPrices = new JLabel("Buy Prices");
		lblBuyPrices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuyPrices.setBounds(582, 342, 116, 25);
		contentPane.add(lblBuyPrices);

		AGL_sellPrice = new JLabel("");
		AGL_sellPrice.setForeground(Color.RED);
		AGL_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AGL_sellPrice.setBounds(445, 374, 116, 25);
		contentPane.add(AGL_sellPrice);

		origin_sellPrice = new JLabel("");
		origin_sellPrice.setForeground(Color.RED);
		origin_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		origin_sellPrice.setBounds(445, 410, 116, 25);
		contentPane.add(origin_sellPrice);

		enAus_sellPrice = new JLabel("");
		enAus_sellPrice.setForeground(Color.RED);
		enAus_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enAus_sellPrice.setBounds(445, 444, 116, 25);
		contentPane.add(enAus_sellPrice);

		AGL_buyPrice = new JLabel("");
		AGL_buyPrice.setForeground(new Color(60, 179, 113));
		AGL_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AGL_buyPrice.setBounds(582, 374, 116, 25);
		contentPane.add(AGL_buyPrice);

		origin_buyPrice = new JLabel("");
		origin_buyPrice.setForeground(new Color(60, 179, 113));
		origin_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		origin_buyPrice.setBounds(582, 410, 116, 25);
		contentPane.add(origin_buyPrice);

		enAus_buyPrice = new JLabel("");
		enAus_buyPrice.setForeground(new Color(60, 179, 113));
		enAus_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enAus_buyPrice.setBounds(582, 444, 116, 25);
		contentPane.add(enAus_buyPrice);

		lblHomeAgent = new JLabel("Home Agent");
		lblHomeAgent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHomeAgent.setBounds(10, 506, 116, 25);
		contentPane.add(lblHomeAgent);

		lblTotalConsume = new JLabel("Actual Consume");
		lblTotalConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalConsume.setBounds(25, 566, 116, 25);
		contentPane.add(lblTotalConsume);

		lblTotalGenerate = new JLabel("Actual Generate");
		lblTotalGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalGenerate.setBounds(25, 602, 116, 25);
		contentPane.add(lblTotalGenerate);

		total_consume = new JLabel("");
		total_consume.setBackground(SystemColor.activeCaption);
		total_consume.setForeground(Color.RED);
		total_consume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		total_consume.setBounds(151, 566, 116, 25);
		contentPane.add(total_consume);

		total_generate = new JLabel("");
		total_generate.setBackground(SystemColor.activeCaption);
		total_generate.setForeground(new Color(60, 179, 113));
		total_generate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		total_generate.setBounds(151, 602, 116, 25);
		contentPane.add(total_generate);

		lblExpense = new JLabel("Expense");
		lblExpense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpense.setBounds(25, 638, 116, 25);
		contentPane.add(lblExpense);

		lblIncome = new JLabel("Income");
		lblIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIncome.setBounds(25, 674, 116, 25);
		contentPane.add(lblIncome);

		expense = new JLabel("");
		expense.setForeground(Color.RED);
		expense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expense.setBounds(151, 638, 50, 25);
		contentPane.add(expense);

		income = new JLabel("");
		income.setForeground(new Color(60, 179, 113));
		income.setFont(new Font("Tahoma", Font.PLAIN, 14));
		income.setBounds(151, 674, 80, 25);
		contentPane.add(income);

		lblContractWith = new JLabel("Contract With");
		lblContractWith.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContractWith.setBounds(25, 710, 116, 25);
		contentPane.add(lblContractWith);

		contract_company = new JLabel("");
		contract_company.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contract_company.setBounds(151, 710, 116, 25);
		contentPane.add(contract_company);

		lblHour = new JLabel("Hour");
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHour.setBounds(10, 306, 57, 25);
		contentPane.add(lblHour);

		current_hour = new JLabel("");
		current_hour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		current_hour.setBounds(50, 306, 116, 25);
		contentPane.add(current_hour);
		
		JLabel lblSmartHomeDashboard = new JLabel("Smart Home Dashboard");
		lblSmartHomeDashboard.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSmartHomeDashboard.setHorizontalAlignment(SwingConstants.CENTER);
		lblSmartHomeDashboard.setBounds(5, 0, 675, 43);
		contentPane.add(lblSmartHomeDashboard);
		
		lblSellPriceAccept = new JLabel("Sell Price Accept Range");
		lblSellPriceAccept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSellPriceAccept.setBounds(25, 530, 151, 25);
		contentPane.add(lblSellPriceAccept);
		
		lblBuyPriceAccept = new JLabel("Buy Price Accept Range");
		lblBuyPriceAccept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuyPriceAccept.setBounds(329, 530, 151, 25);
		contentPane.add(lblBuyPriceAccept);
		
		sellPriceAcceptRangeMin = new JTextField();
		sellPriceAcceptRangeMin.setBackground(SystemColor.control);
		sellPriceAcceptRangeMin.setBounds(168, 534, 57, 20);
		contentPane.add(sellPriceAcceptRangeMin);
		sellPriceAcceptRangeMin.setColumns(10);
		
		buyPriceAcceptRangeMin = new JTextField();
		buyPriceAcceptRangeMin.setColumns(10);
		buyPriceAcceptRangeMin.setBackground(SystemColor.menu);
		buyPriceAcceptRangeMin.setBounds(476, 534, 57, 20);
		contentPane.add(buyPriceAcceptRangeMin);
		
		sellPriceAcceptRangeMax = new JTextField();
		sellPriceAcceptRangeMax.setColumns(10);
		sellPriceAcceptRangeMax.setBackground(SystemColor.menu);
		sellPriceAcceptRangeMax.setBounds(235, 534, 57, 20);
		contentPane.add(sellPriceAcceptRangeMax);
		
		buyPriceAcceptRangeMax = new JTextField();
		buyPriceAcceptRangeMax.setColumns(10);
		buyPriceAcceptRangeMax.setBackground(SystemColor.menu);
		buyPriceAcceptRangeMax.setBounds(548, 534, 57, 20);
		contentPane.add(buyPriceAcceptRangeMax);
		
		label = new JLabel("-");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(225, 530, 10, 25);
		contentPane.add(label);
		
		label_1 = new JLabel("-");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(534, 530, 10, 25);
		contentPane.add(label_1);
		
		lblPredictedGenerate = new JLabel("Predicted  Generate");
		lblPredictedGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictedGenerate.setBounds(330, 602, 126, 25);
		contentPane.add(lblPredictedGenerate);
		
		lblPredictedConsume = new JLabel("Predicted Consume");
		lblPredictedConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictedConsume.setBounds(330, 566, 130, 25);
		contentPane.add(lblPredictedConsume);
		
		predictedConsume = new JLabel("");
		predictedConsume.setForeground(Color.RED);
		predictedConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedConsume.setBackground(SystemColor.activeCaption);
		predictedConsume.setBounds(476, 566, 116, 25);
		contentPane.add(predictedConsume);
		
		predictedGenerate = new JLabel("");
		predictedGenerate.setForeground(new Color(60, 179, 113));
		predictedGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedGenerate.setBackground(SystemColor.activeCaption);
		predictedGenerate.setBounds(476, 602, 116, 25);
		contentPane.add(predictedGenerate);
		
		lblPaidExtra = new JLabel("Predicted Expense");
		lblPaidExtra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPaidExtra.setBounds(330, 638, 116, 25);
		contentPane.add(lblPaidExtra);
		
		predictedExpense = new JLabel("");
		predictedExpense.setForeground(Color.RED);
		predictedExpense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedExpense.setBounds(476, 638, 50, 25);
		contentPane.add(predictedExpense);
		
		lblPredictedIncome = new JLabel("Predicted Income");
		lblPredictedIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPredictedIncome.setBounds(330, 674, 116, 25);
		contentPane.add(lblPredictedIncome);
		
		predictedIncome = new JLabel("");
		predictedIncome.setForeground(new Color(60, 179, 113));
		predictedIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedIncome.setBounds(476, 674, 68, 25);
		contentPane.add(predictedIncome);
		
		JLabel lblExceededEnergyPrice = new JLabel("Exceeded energy price");
		lblExceededEnergyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExceededEnergyPrice.setBounds(330, 306, 143, 25);
		contentPane.add(lblExceededEnergyPrice);
		
		exceededPrice = new JTextField();
		exceededPrice.setColumns(10);
		exceededPrice.setBackground(SystemColor.menu);
		exceededPrice.setBounds(476, 310, 57, 20);
		contentPane.add(exceededPrice);
		
		expenseExceed = new JLabel("");
		expenseExceed.setForeground(Color.RED);
		expenseExceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expenseExceed.setBounds(198, 638, 57, 25);
		contentPane.add(expenseExceed);
		
		predictedExpenseExceed = new JLabel("");
		predictedExpenseExceed.setForeground(Color.RED);
		predictedExpenseExceed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		predictedExpenseExceed.setBounds(525, 638, 57, 25);
		contentPane.add(predictedExpenseExceed);
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

	public void UpdateRetailerValues(String _name, Double _buy, Double _sell) {
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
	
	public void SetExceededExpense(Double amount, Double contractSellPrice)
	{
		
		if(amount < 0)
		{
			amount = amount * Double.parseDouble(exceededPrice.getText());
			amount = Ulti.round(amount);
			expenseExceed.setText("+$" + amount.toString());
			predictedExpenseExceed.setText("");
		}
		else 
		{
			amount = Math.abs(amount);
			amount*= contractSellPrice;
			amount = Ulti.round(amount);
			predictedExpenseExceed.setText("+$" + amount.toString());
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
}
