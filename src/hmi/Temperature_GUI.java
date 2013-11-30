package hmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import components.Temp_sim;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Temperature_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private TimeSeries series;
	private Timer timer = new Timer(250, new tempListener());
	Temp_sim temp = new Temp_sim();
    Thread thread = new Thread(temp);
    private JTextField textField_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temperature_GUI frame = new Temperature_GUI();
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
	@SuppressWarnings("deprecation")
	public Temperature_GUI() {
		
	    thread.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel Button_Panel = new JPanel();
		contentPane.add(Button_Panel, BorderLayout.EAST);
		Button_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		Button_Panel.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new BtnUpActionListener());
		btnUp.setPreferredSize(new Dimension(105,105));
		panel.add(btnUp);
		
		textField = new JTextField();
		textField.setText(String.valueOf(temp.getSetTemp())+" \u2103");
		textField.setPreferredSize(new Dimension(95,95));
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new BtnDownActionListener());
		btnDown.setPreferredSize(new Dimension(105,105));
		panel.add(btnDown);
		
		JLabel lblCurrentTemperature = new JLabel("current temperature:");
		panel.add(lblCurrentTemperature);
		
		this.textField_1 = new JTextField();
		panel.add(this.textField_1);
		this.textField_1.setColumns(10);
		final Timer updater = new Timer(1000, new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	textField_1.setText(String.valueOf(temp.getTemperature())+" \u2103");
		    }
		});
		updater.start();
		JPanel center = new JPanel();
		contentPane.add(center, BorderLayout.CENTER);
		
		
		this.series = new TimeSeries("Temperature Data", Millisecond.class);
		final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
	    final JFreeChart chart = createChart(dataset);
	    timer.setInitialDelay(1000);
	    chart.setBackgroundPaint(Color.LIGHT_GRAY);
	    
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
	    center.add(chartPanel);
	        timer.start();
	}
	
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Temperature Component",
            "Time",
            "Temperature"+" ("+"\u2103"+")",
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
		      
		       this.lastValue = temp.getTemperature();
		        final Millisecond now = new Millisecond();
		     //   this.series.add(new Millisecond(), this.lastValue);
		        
		        series.addOrUpdate(now, lastValue);
		        
		    }
	}
	private class BtnUpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			double current_temp = Double.valueOf(textField.getText().substring(0, 3)) +1.0;
			temp.setTemperature(current_temp);
			textField.setText(String.valueOf(current_temp)+" \u2103");
			
		}
	}
	private class BtnDownActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			double current_temp = Double.valueOf(textField.getText().substring(0, 3)) -1.0;
			temp.setTemperature(current_temp);
			textField.setText(String.valueOf(current_temp)+" \u2103");
			
		}
	}

}