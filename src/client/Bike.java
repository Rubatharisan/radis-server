package client;

import java.io.Serializable;

public class Bike implements Serializable {

	private static final long serialVersionUID = 521298086114341814L;
	private int height;
	private int width;
	
	
	public Bike(int height, int width){
		this.height = height;
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}
	
}
