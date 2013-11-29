package hmi;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import components.Temp_sim;

public class Temp_GUI extends JFrame {

	private JPanel contentPane;
	private XYSeries series;
	private ChartPanel chartpanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temp_GUI frame = new Temp_GUI();
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
	public Temp_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		series = new XYSeries("graph");
		
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		JFreeChart chart = ChartFactory.createXYLineChart("motherfucking graph", "X", "y", dataset, PlotOrientation.VERTICAL,true,true,false);
		chart.addChangeListener(new changelistener());
		
		chartpanel = new ChartPanel(chart);
		contentPane.add(chartpanel);
		
		Temp_sim sim = new Temp_sim();
		Thread thread = new Thread(sim);
		sim.setTemperature(100);
		thread.start();
		series.add(sim.getTemperature(),1);
		
		new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				if(chartpanel.isVisible()){
				while(true){
					chartpanel.repaint();
					try {
						Thread.sleep((long)1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
			}
			
		}.run();
		
	}
	private class changelistener implements ChartChangeListener{

		
		
		@Override
		public void chartChanged(ChartChangeEvent arg0) {
			// TODO Auto-generated method stub

			
		
			
		}

		
		
	}

}
