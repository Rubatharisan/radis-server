package client;

import java.io.Serializable;

public class Configuration implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1609385722150252215L;
	private double co2_level;
	private int red_level;
	private int blue_level;
	private double temp;
	
	

	public Configuration(double co2_level, int red_level, int blue_level, double temp){
		this.co2_level = co2_level;
		this.red_level = red_level;
		this.blue_level = blue_level;
		this.temp = temp;
	}



	public double getCo2_level() {
		return co2_level;
	}



	public int getRed_level() {
		return red_level;
	}



	public int getBlue_level() {
		return blue_level;
	}



	public double getTemp() {
		return temp;
	}
	
	
}
