package components;

public class Temp_sim implements Runnable {

	private int temperature = 21;
	private int set_temp;
	private long sleeptime = 3000;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			
			if(temperature <= set_temp)
			{
				temperature = temperature +1;
				
			}
			else if(temperature > set_temp)
			{	
				new Runnable(){

					@Override
					public void run() {
					
						for(int i = 0; i < 3;i++)
						{
							temperature = temperature -1;
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

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int set_temp) {
		this.set_temp = set_temp;
	}
	

}
