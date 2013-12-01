package hmi;

import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import components.Temp_sim;

public class tester {
    static TimeSeries ts = new TimeSeries("Current Temperature", Millisecond.class);

    public static void main(String[] args) throws InterruptedException {
        gen myGen = new gen();
        new Thread(myGen).start();

        TimeSeriesCollection dataset = new TimeSeriesCollection(ts);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Temperature Component",
            "Time",
            "Temperature",
            dataset,
            true,
            true,
            false
        );
        
        final XYPlot plot = chart.getXYPlot();

        ValueAxis xaxis = plot.getRangeAxis();
 
        xaxis.setAutoRange(true);
       
        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
    
     
        
        NumberAxis yaxis = (NumberAxis) plot.getRangeAxis();
        yaxis.setRange(0, 50);
        
        
        

        JFrame frame = new JFrame("GraphTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ChartPanel label = new ChartPanel(chart);
        frame.getContentPane().add(label);
        //Suppose I add combo boxes and buttons here later

        frame.pack();
        frame.setVisible(true);
    }

    static class gen implements Runnable {

        public void run() {
        	Temp_sim temp = new Temp_sim();
      		Thread temperature_thread = new Thread(temp);
      		temp.setTemperature(30);
      		temperature_thread.start();
            while(true) {
    
                ts.addOrUpdate(new Millisecond(), temp.getTemperature());
                /** try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                } **/
            }
        }
    }

}
