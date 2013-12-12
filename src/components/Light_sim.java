package components;

public class Light_sim implements Runnable {

	private int red_level;
	private int blue_level;
	private long sleeptime = 1; 	//timer*60(min)*60(sec)*1000(ms); men 
									//lige nu, 5 sek for funktionalitet
	private boolean red_status;
	private boolean blue_status ;
	
	public Light_sim(int red_level, int blue_level){
		this.red_level = red_level;
		this.blue_level = blue_level;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
		if(red_status == true){
			blue_level = 0;
			
		}
		else if(blue_status == true){
			red_level = 0;
			
		}
		try {
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	}
	
	public int getRed_level() {
		return red_level;
	}

	public void setRed_level(int red_level) {
		this.red_level = red_level;
	}

	public int getBlue_level() {
		return blue_level;
	}

	public void setBlue_level(int blue_level) {
		this.blue_level = blue_level;
	}

	public boolean isRed_status() {
		return red_status;
	}

	public void setRed_status(boolean red_status) {
		this.red_status = red_status;
	}

	public boolean isBlue_status() {
		return blue_status;
	}

	public void setBlue_status(boolean blue_status) {
		this.blue_status = blue_status;
	}


}
