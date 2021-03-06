package components;

import data.Data;
import hmi.HMIstartReadings;

public class Temp_sim implements Runnable {

	private double temperature;
	private double set_temp;
	private long sleeptime = 100;
//	private double send_temp;
	

	

	public Temp_sim(double de_temp) {
		// TODO Auto-generated constructor stub
		this.temperature = de_temp;
		this.set_temp = de_temp;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			
			if(temperature <= set_temp)
			{
				temperature = temperature +0.1;
		
				
			}
			else if(temperature > set_temp)
			{	
				new Runnable(){
					
					@Override
					public void run() {
					
						for(double i = 0.0; i < 2.0;i = i+0.05)
						{
							temperature = temperature -0.05;
						
						//	System.out.println("Temperature is: \t"+temperature);
							try {
								Thread.sleep(sleeptime);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					} 
					
				}.run();
				
				
			}
		//	System.out.println("Temperature is: \t"+temperature);
		
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double current_temp) {
		this.set_temp = current_temp +1;
	}
	public double getSetTemp(){
		return set_temp;
	}

	

}
