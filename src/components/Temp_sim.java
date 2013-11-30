package components;

public class Temp_sim implements Runnable {

	private double temperature = 21.0;
	private double set_temp;
	private long sleeptime = 100;
	
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
							System.out.println(temperature);
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
			System.out.println(temperature);
		
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

	public void setTemperature(int set_temp) {
		this.set_temp = set_temp +1;
	}
	

}
