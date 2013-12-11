package hmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import components.Light_sim;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

import data.Data;

public class Light_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel blueContainer;
	private JPanel redContainer;
	private JPanel red_center;
	private JPanel red_CL;
	private JPanel red_CR;
	private JLabel redStatus;
	private JTextField redWatt;
	private JSlider red_slider;
	private JPanel blueCenter;
	private JPanel blue_CL;
	private JPanel blue_CR;
	private JLabel blueStat;
	private JTextField blueWatt;
	private JSlider blue_Slider;
	private Light_sim simulator;
	private Thread thread;
	private Font watts,status;
	private JPanel navigator_panel;
	private JButton btnReturn;
	private JPanel panel;
	private JPanel toggleButtons_panel;
	private JToggleButton redToggle;
	private JToggleButton blueToggle;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Timer dataLog = new Timer(30*1000,new dataLogger());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Light_GUI frame = new Light_GUI();
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
	public Light_GUI() {
		simulator = new Light_sim();
		thread = new Thread(simulator);
		thread.start();
		simulator.setRed_status(true);
		watts = new Font("Courier",Font.PLAIN,30);
		status = new Font("Tahoma",Font.BOLD,30);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.redContainer = new JPanel();
		this.contentPane.add(this.redContainer);
		this.redContainer.setLayout(new BorderLayout(0, 0));
		
		this.red_center = new JPanel();
		this.redContainer.add(this.red_center, BorderLayout.CENTER);
		this.red_center.setLayout(new BoxLayout(this.red_center, BoxLayout.X_AXIS));
		
		this.red_CL = new JPanel();
		this.red_center.add(this.red_CL);
		
		this.redWatt = new JTextField(15);
		this.redWatt.setPreferredSize(new Dimension(95,95));
		this.redWatt.setFont(watts);
		this.red_CL.add(this.redWatt);
		this.redWatt.setText(String.valueOf(simulator.getRed_level()));
		
		this.red_CR = new JPanel();
		this.red_center.add(this.red_CR);
		
		this.red_slider = new JSlider();
		this.red_slider.setOrientation(SwingConstants.VERTICAL);
		this.red_slider.setPaintTicks(true);
		this.red_slider.setPaintLabels(true);
		this.red_slider.setSnapToTicks(true);
		this.red_slider.setPreferredSize(new Dimension(80,450));
		this.red_slider.setMinorTickSpacing(50);
		this.red_slider.setMaximum(800);
		this.red_slider.setMinimum(0);
		this.red_slider.setValue(simulator.getRed_level());
		this.red_slider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				simulator.setRed_level(red_slider.getValue());
				redWatt.setText(String.valueOf(simulator.getRed_level()));
			}
			
		});
		this.red_CR.add(this.red_slider);
		
		
		this.redStatus = new JLabel("ON");
		this.redStatus.setFont(status);
		this.redStatus.setForeground(Color.GREEN);
		this.redContainer.add(this.redStatus, BorderLayout.NORTH);
		
		this.blueContainer = new JPanel();
		this.contentPane.add(this.blueContainer);
		this.blueContainer.setLayout(new BorderLayout(0, 0));
		
		this.blueCenter = new JPanel();
		this.blueContainer.add(this.blueCenter, BorderLayout.CENTER);
		this.blueCenter.setLayout(new BoxLayout(this.blueCenter, BoxLayout.X_AXIS));
		
		this.blue_CL = new JPanel();
		this.blueCenter.add(this.blue_CL);
		
		this.blueWatt = new JTextField();
		this.blueWatt.setText(String.valueOf(simulator.getBlue_level()));
		this.blueWatt.setPreferredSize(new Dimension(95,95));
		this.blue_CL.add(this.blueWatt);
		this.blueWatt.setColumns(15);
		this.blueWatt.setFont(watts);
		
		this.blue_CR = new JPanel();
		this.blueCenter.add(this.blue_CR);
		
		this.blue_Slider = new JSlider();
		this.blue_Slider.setPreferredSize(new Dimension(80,450));
		this.blue_Slider.setMaximum(800);
		this.blue_Slider.setMinimum(0);
		this.blue_Slider.setSnapToTicks(true);
		this.blue_Slider.setPaintTicks(true);
		this.blue_Slider.setPaintLabels(true);
		this.blue_Slider.setOrientation(SwingConstants.VERTICAL);
		this.blue_Slider.setMinorTickSpacing(50);
		this.blue_Slider.setValue(simulator.getBlue_level());
		this.blue_Slider.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				simulator.setBlue_level(blue_Slider.getValue());
				blueWatt.setText(String.valueOf(simulator.getBlue_level()));
			}
			
		});
		this.blue_Slider.setEnabled(false);
		this.blue_CR.add(this.blue_Slider);
		
		this.blueStat = new JLabel("OFF");
		this.blueStat.setFont(status);
		this.blueStat.setForeground(Color.RED);
		this.blueContainer.add(this.blueStat, BorderLayout.NORTH);
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		
		this.navigator_panel = new JPanel();
		this.panel.add(this.navigator_panel);
		this.navigator_panel.setLayout(new BorderLayout(50, 115));
		
		this.btnReturn = new JButton("Return");
		this.btnReturn.addActionListener(new BtnReturnActionListener());
		this.btnReturn.setPreferredSize(new Dimension(150, 105));
		this.navigator_panel.add(this.btnReturn, BorderLayout.SOUTH);
		
		this.toggleButtons_panel = new JPanel();
		
		this.navigator_panel.add(this.toggleButtons_panel, BorderLayout.NORTH);
		this.toggleButtons_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.redToggle = new JToggleButton("Red Light ON/OFF");
		this.redToggle.setSelected(true);
		this.buttonGroup.add(this.redToggle);
		this.redToggle.addActionListener(new ToggleButtonActionListener());
		this.redToggle.setPreferredSize(new Dimension(150, 105));
		this.toggleButtons_panel.add(this.redToggle);
		
		this.blueToggle = new JToggleButton("Blue Light ON/OFF");
		this.buttonGroup.add(this.blueToggle);
		this.blueToggle.addActionListener(new BlueToggleActionListener());
		this.blueToggle.setPreferredSize(new Dimension(150, 105));
		this.toggleButtons_panel.add(this.blueToggle);
		
		Timer updater = new Timer(1, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(redToggle.isSelected() == true){
					simulator.setRed_status(true);
					redWatt.setText(String.valueOf(simulator.getRed_level()));
					red_slider.setValue(simulator.getRed_level());
					
					blue_Slider.setValue(simulator.getBlue_level());
					blueWatt.setText(String.valueOf(simulator.getBlue_level()));
				}
				else if(blueToggle.isSelected() == true){
					simulator.setBlue_status(true);
					blueWatt.setText(String.valueOf(simulator.getBlue_level()));
					blue_Slider.setValue(simulator.getBlue_level());
					
					red_slider.setValue(simulator.getRed_level());
					redWatt.setText(String.valueOf(simulator.getRed_level()));
				}
			}
			
		});
		updater.start();
		dataLog.start();
		
	}

	private class BtnReturnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	private class ToggleButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
				simulator.setRed_status(true);
				red_slider.setValue(200);
				
				red_slider.setEnabled(true);
				redStatus.setText("ON");
				redStatus.setForeground(Color.GREEN);
				
				blueToggle.setSelected(false);
				blueStat.setText("OFF");
				blueStat.setForeground(Color.RED);
				
				blue_Slider.setEnabled(false);
				
				

		}
	}
	private class BlueToggleActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
				simulator.setRed_status(false);
				blue_Slider.setValue(200);
				blue_Slider.setEnabled(true);
				blueStat.setText("ON");
				blueStat.setForeground(Color.GREEN);
				redToggle.setSelected(false);
				redStatus.setText("OFF");
				redStatus.setForeground(Color.RED);
				red_slider.setEnabled(false);	
		
			
			
		}
	}
	private class dataLogger implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Data sender = new Data();
			sender.insertLight(simulator.getRed_level(),simulator.getBlue_level());
			System.out.println("red: " + simulator.getRed_level() + " and blue: "+simulator.getBlue_level()+ 
					" was logged into database");
			
			
		}
		
	}
}

