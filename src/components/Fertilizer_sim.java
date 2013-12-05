package components;

public class Fertilizer_sim implements Runnable {

	private int fertalize;
	private long sleeptime = 1000;
	private boolean bool;
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	public boolean isActive(){
		return bool;
	}
	public void setActive(boolean bool){
		this.bool = bool;
	}

}
