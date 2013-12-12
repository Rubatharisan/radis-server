package hmi;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import client.Configuration;

import components.CO2_sim;
import components.Temp_sim;
import data.Data;

public class HMIstartReadings extends JPanel {
	private JPanel center_panel;
	private JPanel footer_panel;
	private JLabel readings_footerTitle;
	private JLabel readings_footerProduct;
	private JPanel header_panel;
	private JLabel label;
	private Font header,footer,text,text_title;
	private JPanel center_left;
	private JPanel center_right;
	private JPanel CL_left;
	private JPanel CL_right;
	private JPanel center__leftKeeper;
	private JLabel lblNewLabel;
	private JPanel center_rightKeeper;
	private JLabel lblCurrentReadings;
	private JPanel CR_left;
	private JPanel CR_right;
	private JLabel recCo2Title;
	private JLabel lblFertilizing;
	private JLabel lblLightLevels;
	private JLabel lblRed;
	private JLabel lblBlue;
	private JLabel lblAirHumidity;
	private JLabel lblTemperature;
	private JLabel lblWater;
	private JLabel co2_recRead;
	private JLabel fertalize_recRead;
	private JLabel lightLevels_spacout;
	private JLabel red_recRead;
	private JLabel blue_recRead;
	private JLabel temperature_recRead;
	private JLabel water_recRead;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private JLabel label_7;
	private JLabel label_8;
	private JLabel Co2_current_read;
	private JLabel fertalize_current_read;
	private JLabel red_current_read;
	private JLabel blue_current_read;
	private JLabel air_current_read;
	private JLabel water_current_read;
	private JPanel center_centerpanel;
	private JPanel panel;
	private JLabel lblLastUpdated;
	private JTextField updaterLabel;
	private JLabel lblLastRead;
	private Temp_sim temp_sim;
	private double temp;
	private JSeparator separator;
	private JLabel temp_current_read;
	private JLabel label_9;
	private JLabel label_10;


	/**
	 * Create the panel.
	 */
	public HMIstartReadings(Configuration config) {
		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		header = new Font("courier",Font.BOLD,22);
		footer = new Font("courer",Font.BOLD,16);
		text = new Font("Tahoma",Font.PLAIN,18);
		text_title = new Font("Tahoma",Font.BOLD,18);
		
		this.center_panel = new JPanel();
		add(this.center_panel, BorderLayout.CENTER);
		this.center_panel.setLayout(new BorderLayout(0, 0));
		
		this.center__leftKeeper = new JPanel();
		this.center_panel.add(this.center__leftKeeper, BorderLayout.WEST);
		this.center__leftKeeper.setLayout(new BoxLayout(this.center__leftKeeper, BoxLayout.PAGE_AXIS));
		
		this.lblNewLabel = new JLabel("Recommended Readings");
		this.lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.lblNewLabel.setFont(text_title);
		this.center__leftKeeper.add(this.lblNewLabel);
		
		this.center_left = new JPanel();
		this.center__leftKeeper.add(this.center_left);
		this.center_left.setLayout(new BoxLayout(this.center_left, BoxLayout.X_AXIS));
		
		this.CL_left = new JPanel();
		this.center_left.add(this.CL_left);
		this.CL_left.setLayout(new BoxLayout(this.CL_left, BoxLayout.PAGE_AXIS));
		this.CL_left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.recCo2Title = new JLabel("Co2 level.");
		this.recCo2Title.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.CL_left.add(this.recCo2Title);
		
		this.lblFertilizing = new JLabel("Fertilizing");
		this.lblFertilizing.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.CL_left.add(this.lblFertilizing);
		
		this.lblLightLevels = new JLabel("Light Levels");
		this.lblLightLevels.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.CL_left.add(this.lblLightLevels);
		
		this.lblRed = new JLabel("Red");
		this.CL_left.add(this.lblRed);
		
		this.lblBlue = new JLabel("Blue");
		this.CL_left.add(this.lblBlue);
		
		this.lblAirHumidity = new JLabel("Air Humidity");
		this.lblAirHumidity.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.CL_left.add(this.lblAirHumidity);
		
		this.lblTemperature = new JLabel("Temperature");
		this.lblTemperature.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.CL_left.add(this.lblTemperature);
		
		this.lblWater = new JLabel("Water");
		this.lblWater.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.CL_left.add(this.lblWater);
		
		this.CL_right = new JPanel();
		this.center_left.add(this.CL_right);
		this.CL_right.setLayout(new BoxLayout(this.CL_right, BoxLayout.PAGE_AXIS));
		this.CL_right.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		this.co2_recRead = new JLabel("--");
		this.CL_right.add(this.co2_recRead);
		this.co2_recRead.setText(String.valueOf(config.getCo2_level()));
		
		this.fertalize_recRead = new JLabel("--");
		this.CL_right.add(this.fertalize_recRead);
		this.fertalize_recRead.setText("once in 30sec");
		
		this.lightLevels_spacout = new JLabel("-----");
		this.CL_right.add(this.lightLevels_spacout);
		
		this.red_recRead = new JLabel("--");
		this.CL_right.add(this.red_recRead);
		this.red_recRead.setText(String.valueOf(config.getRed_level()));
		
		this.blue_recRead = new JLabel("--");
		this.CL_right.add(this.blue_recRead);
		this.blue_recRead.setText(String.valueOf(config.getBlue_level()));
		
		this.label_10 = new JLabel("----");
		this.CL_right.add(this.label_10);
		
		this.temperature_recRead = new JLabel("--");
		this.CL_right.add(this.temperature_recRead);
		this.temperature_recRead.setText(String.valueOf(config.getTemp()));
		
		this.water_recRead = new JLabel("--");
		this.CL_right.add(this.water_recRead);
		this.water_recRead.setText("10%");
		
		this.center_rightKeeper = new JPanel();
		this.center_panel.add(this.center_rightKeeper, BorderLayout.EAST);
		this.center_rightKeeper.setLayout(new BoxLayout(this.center_rightKeeper, BoxLayout.PAGE_AXIS));
		
		this.lblCurrentReadings = new JLabel("Current Readings");
		this.lblCurrentReadings.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.center_rightKeeper.add(this.lblCurrentReadings);
		this.lblCurrentReadings.setFont(text_title);
		
		this.center_right = new JPanel();
		this.center_rightKeeper.add(this.center_right);
		this.center_right.setLayout(new BoxLayout(this.center_right, BoxLayout.X_AXIS));
		
		this.CR_left = new JPanel();
		this.center_right.add(this.CR_left);
		this.CR_left.setLayout(new BoxLayout(this.CR_left, BoxLayout.PAGE_AXIS));
		this.CR_left.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.label_1 = new JLabel("Co2 level.");
		this.label_1.setAlignmentX(0.5f);
		this.CR_left.add(this.label_1);
		
		this.label_2 = new JLabel("Fertilizing");
		this.label_2.setAlignmentX(0.5f);
		this.CR_left.add(this.label_2);
		
		this.label_3 = new JLabel("Light Levels");
		this.label_3.setAlignmentX(0.5f);
		this.CR_left.add(this.label_3);
		
		this.label_4 = new JLabel("Red");
		this.CR_left.add(this.label_4);
		
		this.label_5 = new JLabel("Blue");
		this.CR_left.add(this.label_5);
		
		this.label_6 = new JLabel("Air Humidity");
		this.label_6.setAlignmentX(0.5f);
		this.CR_left.add(this.label_6);
		
		this.label_7 = new JLabel("Temperature");
		this.label_7.setAlignmentX(0.5f);
		this.CR_left.add(this.label_7);
		
		this.label_8 = new JLabel("Water");
		this.label_8.setAlignmentX(0.5f);
		this.CR_left.add(this.label_8);
		
		this.CR_right = new JPanel();
		this.center_right.add(this.CR_right);
		this.CR_right.setLayout(new BoxLayout(this.CR_right, BoxLayout.PAGE_AXIS));
		this.CR_right.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		this.Co2_current_read = new JLabel("--");
		this.CR_right.add(this.Co2_current_read);
		
		this.fertalize_current_read = new JLabel("--");
		this.CR_right.add(this.fertalize_current_read);
		
		this.label_9 = new JLabel("--");
		this.CR_right.add(this.label_9);
		
//		this.separator = new JSeparator();
//		this.CR_right.add(this.separator);
		
		this.red_current_read = new JLabel("--");
		this.CR_right.add(this.red_current_read);
		
		this.blue_current_read = new JLabel("--");
		this.CR_right.add(this.blue_current_read);
		
		this.air_current_read = new JLabel("--");
		this.CR_right.add(this.air_current_read);
		
		this.temp_current_read = new JLabel("---");
		this.CR_right.add(this.temp_current_read);
		
		this.water_current_read = new JLabel("--");
		this.CR_right.add(this.water_current_read);
		
		this.panel = new JPanel();
		this.center_rightKeeper.add(this.panel);
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.PAGE_AXIS));
		
		this.lblLastRead = new JLabel("last read:");
		this.panel.add(this.lblLastRead);
		
		
		this.updaterLabel = new JTextField(14);
		this.panel.add(this.updaterLabel);
		this.updaterLabel.setEditable(false);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Calendar cal = Calendar.getInstance();
		this.updaterLabel.setText(dateFormat.format(cal.getTime()));
		
		this.center_centerpanel = new JPanel();
		this.center_panel.add(this.center_centerpanel, BorderLayout.CENTER);
		this.center_centerpanel.setLayout(new BorderLayout(0, 0));
		
		this.footer_panel = new JPanel();
		add(this.footer_panel, BorderLayout.SOUTH);
		
		this.readings_footerTitle = new JLabel("Product:");
		this.footer_panel.add(this.readings_footerTitle);
		
		
		this.readings_footerProduct = new JLabel("--");
		this.footer_panel.add(this.readings_footerProduct);
		this.readings_footerProduct.setFont(footer);
		this.readings_footerTitle.setFont(footer);
		
		this.header_panel = new JPanel();
		add(this.header_panel, BorderLayout.NORTH);
		
		this.label = new JLabel("Recommended Readings and Current Readings.");
		this.label.setFont(header);
		this.header_panel.add(this.label);
		setFonts();
		init();
		
		
		final Timer updater = new Timer(35*1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    	Calendar cal = Calendar.getInstance();
		    	updaterLabel.setText(dateFormat.format(cal.getTime()));
		    	System.out.println(dateFormat.format(cal.getTime()));
		    	init();
		    	
		    }
		});
		updater.start();

	}
	public void init(){
		Data getRead = new Data();
		String[] readings;
		try {
			readings = getRead.getReadings();
			
			
			Co2_current_read.setText(readings[0]);
			fertalize_current_read.setText(readings[1] + " : "+readings[2]);
			red_current_read.setText(readings[3]);
			blue_current_read.setText(readings[4]);
			temp_current_read.setText(readings[5]);
			water_current_read.setText(readings[6]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public void setFonts(){
		
		Component[] cl_left_components = CL_left.getComponents();
		for(int i = 0; i < cl_left_components.length;i++)
		{
			cl_left_components[i].setFont(text_title);
		}
		
		Component[] cl_right_components = CL_right.getComponents();
		for(int i = 0; i < cl_right_components.length;i++){
			cl_right_components[i].setFont(text);
		}
		
		Component[] cr_left_components = CR_left.getComponents();
		for(int i = 0; i < cr_left_components.length;i++){
			cr_left_components[i].setFont(text_title);
//			((JLabel) cr_left_components[i]).setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
		}
		
		Component [] cr_right_componetns = CR_right.getComponents();
		for(int i = 0; i < cr_right_componetns.length;i++){
			cr_right_componetns[i].setFont(text);

//			((JLabel) cr_right_componetns[i]).setBorder(BorderFactory.createEmptyBorder(04, 04, 04, 04));
		}
		
	}

}
