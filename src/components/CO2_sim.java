package components;

public class CO2_sim implements Runnable {

	private double co2; //unit = "ppm"
	private double set_co2;
	private long sleeptime = 1000;
	private boolean bool = false;
	private boolean operator_Bool = false;
	
	public CO2_sim(double set_co2){
		this.set_co2 = set_co2;
		this.co2 = set_co2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(operator_Bool == true){
				co2 = co2+5;
//				System.out.println("OPERATOR valve is on: Co2 level = "+co2);
			}
			else{
			if(bool == true && operator_Bool == false){
				co2 = co2+5;
//				System.out.println("CO2 Level \t"+co2);
				if(co2 > set_co2+10){
					bool = false;
				}
			}else if(bool == false && operator_Bool == false){
				co2 = co2-0.3;
				if(co2 < set_co2){
					bool = true;
				}
			}
			}
			
			try {
				Thread.sleep(sleeptime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	public double getCo2(){
		return co2;
	}
	public void co2_setActive(boolean bool){
		this.bool = bool;
	}
	public boolean co2_isActive(){
		return bool;
	}
	public void co2_setOperator(boolean operator_Bool){
		this.operator_Bool = operator_Bool;
	}
	public boolean co2_getOperator(){
		return operator_Bool;
	}

}
