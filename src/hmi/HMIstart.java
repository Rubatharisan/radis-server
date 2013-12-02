package hmi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class HMIstart extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JButton co2_btn;
	private JButton fertalize_btn;
	private JButton light_btn;
	private JButton moist_btn;
	private JButton temperature_btn;
	private JButton water_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HMIstart frame = new HMIstart();
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
	public HMIstart() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 383);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		
		this.co2_btn = new JButton("Co2 Levels");
		this.co2_btn.setPreferredSize(new Dimension(105,105));
		this.panel.add(this.co2_btn);
		
		this.fertalize_btn = new JButton("Fertalizing");
		this.fertalize_btn.setPreferredSize(new Dimension(105,105));
		this.panel.add(this.fertalize_btn);
		
		this.light_btn = new JButton("Light Levels");
		this.light_btn.setPreferredSize(new Dimension(105,105));
		this.panel.add(this.light_btn);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new BoxLayout(this.panel_1, BoxLayout.PAGE_AXIS));
		
		this.moist_btn = new JButton("Air Humidity");
		this.moist_btn.setPreferredSize(new Dimension(105,105));
		this.panel_1.add(this.moist_btn);
		
		this.temperature_btn = new JButton("Temperature");
		this.temperature_btn.setPreferredSize(new Dimension(105,105));
		this.panel_1.add(this.temperature_btn);
		
		this.water_btn = new JButton("Water Level");
		this.water_btn.setPreferredSize(new Dimension(105, 105));
		this.panel_1.add(this.water_btn);
	
	}

}
