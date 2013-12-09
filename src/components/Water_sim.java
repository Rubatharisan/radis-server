package components;

public class Water_sim implements Runnable{

	private double level = 10.0;
	private boolean bool = false;
	private boolean operator_Bool = false;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true){
			if(operator_Bool == true)
			{
				level = level +1;
			}
			else{
			if(bool == true){
				level = level + 1;
				if(level > 11){
					bool = false;
				}
			}
			else
			{
				level = level - 0.1;
				if(level < 10.3)
				{
					bool = true;
				}
			}
			}
			try {
				Thread.sleep((long)3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	public double getWaterLevel(){
		return level;
	}
	public void addWater(boolean bool){
		this.bool = bool;
	}
	public boolean water_isActive(){
		return bool;
	}
	public void setOperator(boolean operator_Bool)
	{
		this.operator_Bool = operator_Bool;
	}
	public boolean getOperator(){
		return operator_Bool;
	}

}
