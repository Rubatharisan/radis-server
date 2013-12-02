package components;

public class CO2_sim implements Runnable {

	private int co2 = 400; //unit = "ppm"
	private long sleeptime = 1000;
	private boolean bool;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(bool == true){
				co2 = co2+5;
				System.out.println("CO2 Level \t"+co2);
			}
			
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public int getCo2(){
		return co2;
	}
	public void setActive(boolean bool){
		this.bool = bool;
	}
	public boolean isActive(){
		return bool;
	}

}
