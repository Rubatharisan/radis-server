package tester;

import components.Temp_sim;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Temp_sim temp = new Temp_sim();
		Thread temperature_thread = new Thread(temp);
		temp.setTemperature(24);
		temperature_thread.start();

	}

}
