package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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

public class Home1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtHomeEnergyTrading;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 706, 766);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtHomeEnergyTrading = new JTextField();
		txtHomeEnergyTrading.setBounds(5, 5, 675, 25);
		txtHomeEnergyTrading.setHorizontalAlignment(SwingConstants.CENTER);
		txtHomeEnergyTrading.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHomeEnergyTrading.setText("\r\nSmart Home Dashboard");
		contentPane.add(txtHomeEnergyTrading);
		txtHomeEnergyTrading.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBounds(5, 41, 675, 241);
		contentPane.add(panel);

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
		solarPanelValue.setForeground(Color.GREEN);
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
		AGL_sellPrice.setBounds(456, 374, 116, 25);
		contentPane.add(AGL_sellPrice);

		origin_sellPrice = new JLabel("");
		origin_sellPrice.setForeground(Color.RED);
		origin_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		origin_sellPrice.setBounds(456, 410, 116, 25);
		contentPane.add(origin_sellPrice);

		enAus_sellPrice = new JLabel("");
		enAus_sellPrice.setForeground(Color.RED);
		enAus_sellPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enAus_sellPrice.setBounds(456, 444, 116, 25);
		contentPane.add(enAus_sellPrice);

		AGL_buyPrice = new JLabel("");
		AGL_buyPrice.setForeground(Color.GREEN);
		AGL_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		AGL_buyPrice.setBounds(591, 374, 116, 25);
		contentPane.add(AGL_buyPrice);

		origin_buyPrice = new JLabel("");
		origin_buyPrice.setForeground(Color.GREEN);
		origin_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		origin_buyPrice.setBounds(591, 410, 116, 25);
		contentPane.add(origin_buyPrice);

		enAus_buyPrice = new JLabel("");
		enAus_buyPrice.setForeground(Color.GREEN);
		enAus_buyPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		enAus_buyPrice.setBounds(591, 444, 116, 25);
		contentPane.add(enAus_buyPrice);

		lblHomeAgent = new JLabel("Home Agent");
		lblHomeAgent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHomeAgent.setBounds(10, 506, 116, 25);
		contentPane.add(lblHomeAgent);

		lblTotalConsume = new JLabel("Total Consume");
		lblTotalConsume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalConsume.setBounds(25, 542, 116, 25);
		contentPane.add(lblTotalConsume);

		lblTotalGenerate = new JLabel("Total Generate");
		lblTotalGenerate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalGenerate.setBounds(25, 578, 116, 25);
		contentPane.add(lblTotalGenerate);

		total_consume = new JLabel("");
		total_consume.setForeground(Color.RED);
		total_consume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		total_consume.setBounds(151, 542, 116, 25);
		contentPane.add(total_consume);

		total_generate = new JLabel("");
		total_generate.setForeground(Color.GREEN);
		total_generate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		total_generate.setBounds(151, 578, 116, 25);
		contentPane.add(total_generate);

		lblExpense = new JLabel("Expense");
		lblExpense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExpense.setBounds(330, 542, 116, 25);
		contentPane.add(lblExpense);

		lblIncome = new JLabel("Income");
		lblIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIncome.setBounds(330, 578, 116, 25);
		contentPane.add(lblIncome);

		expense = new JLabel("");
		expense.setForeground(Color.RED);
		expense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expense.setBounds(456, 542, 116, 25);
		contentPane.add(expense);

		income = new JLabel("");
		income.setForeground(Color.GREEN);
		income.setFont(new Font("Tahoma", Font.PLAIN, 14));
		income.setBounds(456, 578, 116, 25);
		contentPane.add(income);

		lblContractWith = new JLabel("Contract With");
		lblContractWith.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContractWith.setBounds(25, 614, 116, 25);
		contentPane.add(lblContractWith);

		contract_company = new JLabel("");
		contract_company.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contract_company.setBounds(151, 614, 116, 25);
		contentPane.add(contract_company);

		lblHour = new JLabel("Hour");
		lblHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHour.setBounds(10, 306, 57, 25);
		contentPane.add(lblHour);

		current_hour = new JLabel("");
		current_hour.setFont(new Font("Tahoma", Font.PLAIN, 14));
		current_hour.setBounds(50, 306, 116, 25);
		contentPane.add(current_hour);
	}

	}
}
