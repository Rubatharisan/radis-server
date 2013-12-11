package hmi;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import components.CO2_sim;
import data.Data;

public class Co2_GUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JLabel valveStat;
	private JPanel panel_1;
	private JTextField textField;
	private JPanel panel_2;
	private JToggleButton toggleButton;
	private JPanel panel_3;
	private TimeSeries series;
	private Timer timer = new Timer(250, new tempListener());
	private Timer sender = new Timer(30*1000, new dataLogger());
	private CO2_sim simulator;
	private JButton btnReturn;
	private Font font_on;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Co2_GUI frame = new Co2_GUI();
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
	public Co2_GUI() {
		font_on = new Font("Tahoma",Font.BOLD,24);
		simulator = new CO2_sim();
		Thread thread = new Thread(simulator);
		thread.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.EAST);
		this.panel.setLayout(new BorderLayout(0, 0));
		
		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2, BorderLayout.NORTH);
		this.panel_2.setLayout(new BoxLayout(this.panel_2, BoxLayout.PAGE_AXIS));
		
		this.toggleButton = new JToggleButton("Add co2");
		this.toggleButton.addActionListener(new TglbtnNewToggleButtonActionListener());
		this.toggleButton.setPreferredSize(new Dimension(150, 105));
		this.panel_2.add(this.toggleButton);

		
		this.panel_1 = new JPanel();
		this.panel_2.add(this.panel_1);
		
		this.lblNewLabel = new JLabel("Valve is: ");
		this.panel_1.add(this.lblNewLabel);
		
		this.valveStat = new JLabel(" ");
		
		this.panel_1.add(this.valveStat);
		valveStat.setText("OFF");
		valveStat.setFont(font_on);
		valveStat.setForeground(Color.RED);
		Timer onoffupdater = new Timer(500, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(simulator.co2_getOperator() == true){
					valveStat.setText("ON");
					valveStat.setFont(font_on);
					valveStat.setForeground(Color.GREEN);
					toggleButton.setSelected(true);
				}
				else if(simulator.co2_getOperator() == false){
					if(simulator.co2_isActive() == true){
						valveStat.setText("ON");
						valveStat.setFont(font_on);
						valveStat.setForeground(Color.GREEN);
						toggleButton.setSelected(true);
					}else{
						valveStat.setText("OFF");
						valveStat.setFont(font_on);
						valveStat.setForeground(Color.RED);
						toggleButton.setSelected(false);
					}
				}
			}
			
		});
	
		
		
		this.textField = new JTextField();
		this.panel_2.add(this.textField);
		this.textField.setColumns(10);
		final Timer updater = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	 DecimalFormat df = new DecimalFormat("0000.00");
		    	 String getTemp = df.format(simulator.getCo2());
		    	 textField.setText(getTemp+" PPM");
		    }
		});
		
		this.btnReturn = new JButton("Return");
		this.btnReturn.addActionListener(new BtnReturnActionListener());
		this.btnReturn.setPreferredSize(new Dimension(150, 105));
		this.panel.add(this.btnReturn, BorderLayout.SOUTH);
		
		this.panel_3 = new JPanel();
		this.contentPane.add(this.panel_3, BorderLayout.CENTER);
		
		this.series = new TimeSeries("Temperature", Millisecond.class);
		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
	    final JFreeChart chart = createChart(dataset);
	    timer.setInitialDelay(1000);
	    chart.setBackgroundPaint(Color.LIGHT_GRAY);
	    
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
	    this.panel_3.add(chartPanel);
	        timer.start();
	        updater.start();
	        onoffupdater.start();
	        sender.start();
	        
	        
		
	}
		private JFreeChart createChart(final XYDataset dataset) {
			final JFreeChart result = ChartFactory.createTimeSeriesChart(
					"Co2 Component",
					"Time",
					"Co2 Level",
					dataset,
					true,
					true,
					false
				);
       
        final XYPlot plot = result.getXYPlot();
       
        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
       
                
        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
       
        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
       
        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(200.0, 700.0);
       
        return result;
    }
		private class tempListener implements ActionListener{
			double lastValue;
			 public void actionPerformed(ActionEvent e) {
			      
			        this.lastValue = simulator.getCo2();
			        final Millisecond now = new Millisecond();
			     //   this.series.add(new Millisecond(), this.lastValue);
			        
			        series.addOrUpdate(now, lastValue);
			        
			    }
		}
		private class TglbtnNewToggleButtonActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				if(toggleButton.isSelected() == true)
				{
						simulator.co2_setActive(false);
						simulator.co2_setOperator(true);
						valveStat.setText("ON");
						valveStat.setFont(font_on);
						valveStat.setForeground(Color.GREEN);
				}
				else if(toggleButton.isSelected() == false){
					
					simulator.co2_setOperator(false);
					simulator.co2_setActive(true);
					valveStat.setText("OFF");
					valveStat.setFont(font_on);
					valveStat.setForeground(Color.RED);
				}
				}
				
			}
		private class BtnReturnActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		}
		private class dataLogger implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Data sender = new Data();
				 DecimalFormat df = new DecimalFormat("0000.00");
		    	 String getDouble = df.format(simulator.getCo2());
		    	 double logTemp = Double.valueOf(getDouble.subSequence(0, 4) +"."+getDouble.substring(5, 7));
				sender.insertCO2(logTemp);
				System.out.println(logTemp + " was logged into the co2_level database");
			}
			
		}
		
}
