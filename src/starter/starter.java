package starter;

import client.Configuration;
import hmi.HMIstart;

public class starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration config = new Configuration(400.0, 200, 0, 21.0);
		HMIstart starter = new HMIstart(config);
		starter.setVisible(true);
		

	}

}
