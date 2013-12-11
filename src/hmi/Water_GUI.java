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
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import components.Water_sim;
import data.Data;

import javax.swing.BoxLayout;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Water_GUI extends JFrame {

	private JPanel contentPane;
	private TimeSeries series;
	private Timer timer = new Timer(250, new tempListener());
	private Water_sim simulator = new Water_sim();
	private Thread thread = new Thread(simulator);
	private JPanel panel;
	private JPanel panel_1;
	private JToggleButton toggleButton;
	private JPanel panel_2;
	private JLabel lblValveIs;
	private JLabel ValveStat;
	private JLabel lblCurrentWaterLevel;
	private JTextField current_read;
	private Font font_on;
	private JButton btnReturn;
	private Timer dataLog = new Timer(30*1000,new dataLogger());
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Water_GUI frame = new Water_GUI();
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
	public Water_GUI() {
		font_on = new Font("Tahoma",Font.BOLD,24);
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
		
		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1, BorderLayout.NORTH);
		this.panel_1.setLayout(new BoxLayout(this.panel_1, BoxLayout.PAGE_AXIS));
		
		this.toggleButton = new JToggleButton("Water Valve");
		this.toggleButton.addActionListener(new ToggleButtonActionListener());
		this.panel_1.add(this.toggleButton);
		this.toggleButton.setPreferredSize(new Dimension(150, 105));
		
		this.panel_2 = new JPanel();
		this.panel_1.add(this.panel_2);
		
		this.lblValveIs = new JLabel("Valve is: ");
		this.panel_2.add(this.lblValveIs);
		
		this.ValveStat = new JLabel("--");
		this.panel_2.add(this.ValveStat);
		ValveStat.setText("OFF");
		ValveStat.setFont(font_on);
		ValveStat.setForeground(Color.RED);
		
		this.lblCurrentWaterLevel = new JLabel("Current Water Level");
		this.panel_1.add(this.lblCurrentWaterLevel);
		
		Timer onoffupdater = new Timer(500, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(simulator.getOperator() == true){
					ValveStat.setText("ON");
					ValveStat.setFont(font_on);
					ValveStat.setForeground(Color.GREEN);
					toggleButton.setSelected(true);
				}
				else if(simulator.getOperator() == false){
					if(simulator.water_isActive() == true){
						ValveStat.setText("ON");
						ValveStat.setFont(font_on);
						ValveStat.setForeground(Color.GREEN);
						toggleButton.setSelected(true);
					}else{
						ValveStat.setText("OFF");
						ValveStat.setFont(font_on);
						ValveStat.setForeground(Color.RED);
						toggleButton.setSelected(false);
					}
				}
			}
			
		});
	
		this.current_read = new JTextField();
		this.panel_1.add(this.current_read);
		this.current_read.setColumns(10);
		final Timer readUpdater = new Timer(1, new ActionListener(){
			DecimalFormat df = new DecimalFormat("##.00");
	    	String getWater = df.format(simulator.getWaterLevel());
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				current_read.setText(/**getWater**/df.format(simulator.getWaterLevel()));
			}
			
		});
		readUpdater.start();
		current_read.setEditable(false);
		
		this.btnReturn = new JButton("Return");
		this.btnReturn.addActionListener(new BtnReturnActionListener());
		this.panel.add(this.btnReturn, BorderLayout.SOUTH);
		btnReturn.setPreferredSize(new Dimension(150, 105));
		
		this.series = new TimeSeries("Water Level", Millisecond.class);
		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
	    final JFreeChart chart = createChart(dataset);
	    timer.setInitialDelay(1000);
	    chart.setBackgroundPaint(Color.LIGHT_GRAY);
	    
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		this.contentPane.add(chartPanel, BorderLayout.CENTER);
		onoffupdater.start();
		timer.start();
		dataLog.start();
	}
	
	private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Water Level",
            "Time",
            "Water level %",
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
        yaxis.setRange(0.0, 50.0);
       
        return result;
    }
	
	private class tempListener implements ActionListener{
		double lastValue;
		 public void actionPerformed(ActionEvent e) {
		      
		       this.lastValue = simulator.getWaterLevel();
		        final Millisecond now = new Millisecond();
		     //   this.series.add(new Millisecond(), this.lastValue);
		        
		        series.addOrUpdate(now, lastValue);
		        
		    }
	}
	private class ToggleButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if(toggleButton.isSelected() == true)
			{
					simulator.addWater(false);
					simulator.setOperator(true);
					simulator.addWater(toggleButton.isSelected());
					ValveStat.setText("ON");
					ValveStat.setFont(font_on);
					ValveStat.setForeground(Color.GREEN);
			}
			else if(toggleButton.isSelected() == false){
				
				simulator.addWater(true);
				simulator.setOperator(false);
				ValveStat.setText("OFF");
				ValveStat.setFont(font_on);
				ValveStat.setForeground(Color.RED);
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
			DecimalFormat df = new DecimalFormat("00.00");
	    	String getDouble = df.format(simulator.getWaterLevel());
	    	double logTemp = Double.valueOf(getDouble.subSequence(0, 2) +"."+getDouble.substring(3, 5));
			sender.insertWater(logTemp);
			System.out.println();
		}
		
	}
	

}
