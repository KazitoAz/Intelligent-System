package gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
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

public class Home1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtHomeEnergyTrading;
	private JTextField txtPricenow;
	private JTextField txtPredictPrice;
	private JTextField txtDuration;
	private JTextField txtAcceptablePriceRange;
	private JTextField textField;
	private JTextField txtAmount;
	private JTextField txtTotalAmount;
	private JTextField txtTotalUsage;
	private JTextField txtSolarPanels;
	private JTextField txtAirConditioner;
	private JTextField txtDishWasher;
	private JTextField txtWashingMachine;
	private JTextField textField_5;
	private JTextField textField_6;

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
		setBounds(100, 100, 1219, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHomeEnergyTrading = new JTextField();
		txtHomeEnergyTrading.setBounds(5, 5, 1193, 25);
		txtHomeEnergyTrading.setHorizontalAlignment(SwingConstants.CENTER);
		txtHomeEnergyTrading.setFont(new Font("Arial", Font.PLAIN, 16));
		txtHomeEnergyTrading.setText("\r\nSmart Home Dashboard");
		contentPane.add(txtHomeEnergyTrading);
		txtHomeEnergyTrading.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 37, 648, 392);
		contentPane.add(panel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(997, 479, 99, 43);
		contentPane.add(comboBox);
		
		txtPricenow = new JTextField();
		txtPricenow.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPricenow.setText("Price (Now) : ");
		txtPricenow.setBounds(10, 431, 305, 38);
		contentPane.add(txtPricenow);
		txtPricenow.setColumns(10);
		
		txtPredictPrice = new JTextField();
		txtPredictPrice.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPredictPrice.setText("Predict Price (Next Hour) : ");
		txtPredictPrice.setBounds(325, 431, 333, 38);
		contentPane.add(txtPredictPrice);
		txtPredictPrice.setColumns(10);
		
		txtDuration = new JTextField();
		txtDuration.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDuration.setText("Duration: ");
		txtDuration.setBounds(921, 479, 76, 44);
		contentPane.add(txtDuration);
		txtDuration.setColumns(10);
		
		txtAcceptablePriceRange = new JTextField();
		txtAcceptablePriceRange.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAcceptablePriceRange.setText("Acceptable Price Range: ");
		txtAcceptablePriceRange.setBounds(698, 420, 193, 49);
		contentPane.add(txtAcceptablePriceRange);
		txtAcceptablePriceRange.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(892, 420, 93, 49);
		contentPane.add(panel_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("--");
		textField.setBounds(987, 420, 35, 49);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1024, 420, 93, 49);
		contentPane.add(panel_2);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Arial", Font.PLAIN, 16));
		btnOrder.setBounds(858, 663, 156, 49);
		contentPane.add(btnOrder);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 16));
		btnCancel.setBounds(1022, 663, 156, 49);
		contentPane.add(btnCancel);
		
		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Arial", Font.PLAIN, 16));
		txtAmount.setBackground(Color.WHITE);
		txtAmount.setText("Amount: ");
		txtAmount.setBounds(698, 97, 148, 49);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(698, 182, 480, 25);
		contentPane.add(separator);
		
		txtTotalAmount = new JTextField();
		txtTotalAmount.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTotalAmount.setText("Total Amount: ");
		txtTotalAmount.setBounds(698, 190, 148, 43);
		contentPane.add(txtTotalAmount);
		txtTotalAmount.setColumns(10);
		
		txtTotalUsage = new JTextField();
		txtTotalUsage.setText("Total Usage");
		txtTotalUsage.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTotalUsage.setColumns(10);
		txtTotalUsage.setBackground(Color.WHITE);
		txtTotalUsage.setBounds(698, 40, 148, 49);
		contentPane.add(txtTotalUsage);
		
		txtSolarPanels = new JTextField();
		txtSolarPanels.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSolarPanels.setText("Solar Panels");
		txtSolarPanels.setBounds(10, 479, 305, 55);
		contentPane.add(txtSolarPanels);
		txtSolarPanels.setColumns(10);
		
		txtAirConditioner = new JTextField();
		txtAirConditioner.setFont(new Font("Arial", Font.PLAIN, 14));
		txtAirConditioner.setText("Air Conditioner");
		txtAirConditioner.setColumns(10);
		txtAirConditioner.setBounds(325, 479, 305, 55);
		contentPane.add(txtAirConditioner);
		
		txtDishWasher = new JTextField();
		txtDishWasher.setFont(new Font("Arial", Font.PLAIN, 14));
		txtDishWasher.setText("Dish Washer");
		txtDishWasher.setColumns(10);
		txtDishWasher.setBounds(10, 544, 305, 55);
		contentPane.add(txtDishWasher);
		
		txtWashingMachine = new JTextField();
		txtWashingMachine.setFont(new Font("Arial", Font.PLAIN, 14));
		txtWashingMachine.setText("Washing Machine");
		txtWashingMachine.setColumns(10);
		txtWashingMachine.setBounds(325, 544, 305, 55);
		contentPane.add(txtWashingMachine);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(10, 609, 305, 55);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(325, 609, 305, 55);
		contentPane.add(textField_6);
	}
}
