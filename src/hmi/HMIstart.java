package hmi;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import client.Configuration;

import java.awt.Component;

public class HMIstart extends JFrame {

	/**
	 * 
	 */
	private Light_GUI light_gui;
	private Fertalizer_GUI fertalizer_gui;
	private static final long serialVersionUID = 2074814726152549528L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JButton co2_btn;
	private JButton fertalize_btn;
	private JButton light_btn;
	private JButton moist_btn;
	private JButton temperature_btn;
	private JButton water_btn;
	private JPanel panel_2;
	private JPanel panel_3;
	private Temperature_GUI temp_gui;
	private Co2_GUI co2_gui;
	private Water_GUI water_gui;
	private JPanel panel_4;
	private JLabel lblPlantNr;
	private Configuration configuration;
	



	/**
	 * Create the frame.
	 */
	
	public HMIstart(Configuration config) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 722);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		
		Font font = new Font("Tahoma",Font.PLAIN,25);
		this.contentPane.setLayout(new BoxLayout(this.contentPane, BoxLayout.PAGE_AXIS));
		
		this.lblPlantNr = new JLabel("Plant nr. 1");
		this.lblPlantNr.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.contentPane.add(this.lblPlantNr);
		
		this.panel_4 = new JPanel();
		this.contentPane.add(this.panel_4);
		
		this.panel = new JPanel();
		this.panel_4.add(this.panel);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		
		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);
		
		this.co2_btn = new JButton("Co2 Levels");
		this.co2_btn.addActionListener(new Co2_btnActionListener());
		this.co2_btn.setFont(font);
		this.panel_2.add(this.co2_btn);
		this.co2_btn.setPreferredSize(new Dimension(155,105));
		
		this.fertalize_btn = new JButton("Fertalizing");
		this.fertalize_btn.addActionListener(new Fertalize_btnActionListener());
		this.panel_2.add(this.fertalize_btn);
		this.fertalize_btn.setPreferredSize(new Dimension(155,105));
		this.fertalize_btn.setFont(font);
		
		this.light_btn = new JButton("Light Levels");
		this.light_btn.addActionListener(new Light_btnActionListener());
		this.panel_2.add(this.light_btn);
		this.light_btn.setPreferredSize(new Dimension(155,105));
		this.light_btn.setFont(font);
		
		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		this.panel_1.setLayout(new BoxLayout(this.panel_1, BoxLayout.PAGE_AXIS));
		
		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);
		
		this.moist_btn = new JButton("Air Humidity");
		this.panel_3.add(this.moist_btn);
		this.moist_btn.setPreferredSize(new Dimension(155,105));
		this.moist_btn.setFont(font);
		
		this.temperature_btn = new JButton("Temperature");
		this.temperature_btn.addActionListener(new Temperature_btnActionListener());
		this.panel_3.add(this.temperature_btn);
		this.temperature_btn.setPreferredSize(new Dimension(155,105));
		this.temperature_btn.setFont(font);
		
		
		this.water_btn = new JButton("Water Level");
		this.water_btn.addActionListener(new Water_btnActionListener());
		this.panel_3.add(this.water_btn);
		this.water_btn.setPreferredSize(new Dimension(155, 105));
		this.water_btn.setFont(font);
		
		JPanel readings = new HMIstartReadings(config);
		contentPane.add(readings);

		UnitInit();
	}

	public void UnitInit(){
		temp_gui = new Temperature_GUI();
		temp_gui.setVisible(false);
		
		co2_gui = new Co2_GUI();
		co2_gui.setVisible(false);
		
		water_gui = new Water_GUI();
		water_gui.setVisible(false);
		
		fertalizer_gui = new Fertalizer_GUI();
		fertalizer_gui.setVisible(false);
		
		light_gui = new Light_GUI();
		light_gui.setVisible(false);
		
		
	}

	private class Temperature_btnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			temp_gui.setVisible(true);
			
			
		}
	}
	private class Co2_btnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			co2_gui.setVisible(true);
		}
	}
	private class Water_btnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			water_gui.setVisible(true);
		}
	}
	private class Fertalize_btnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fertalizer_gui.setVisible(true);
		}
	}
	private class Light_btnActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			light_gui.setVisible(true);
		}
		
	}
	
}
