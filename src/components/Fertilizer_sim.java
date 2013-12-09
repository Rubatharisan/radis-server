package components;

public class Fertilizer_sim implements Runnable {

	private int fertalize = 0;
	private long sleeptime = 30000;
	private boolean bool;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			fertalize = fertalize +1;
			
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public boolean Fertalizer_isActive(){
		return bool;
	}
	public void Fertalizer_setActive(boolean bool){
		this.bool = bool;
	}
	public int Fertalizer_getCount(){
		return fertalize;
	}
	public void Fertalizer_addFertalizer(){
		fertalize = fertalize + 1;
	}

}
