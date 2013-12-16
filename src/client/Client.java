package client;

import java.net.*;
import java.io.*;

import server.Server;

public class Client{
	String serverName = "10.126.25.104";
	int port = 7998;
	
	public void sendStatus(boolean bool){
	
	      try
	      {
	    	  
	         System.out.println("Connecting to " + serverName
	                             + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         
	         System.out.println("Just connected to "
	                      + client.getRemoteSocketAddress());
	         
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out =
	                       new DataOutputStream(outToServer);
	         out.writeBoolean(bool);
	         
	         out.writeUTF("Hello from "
	                      + client.getLocalSocketAddress());
	         
	         client.close();
	         
	      }catch(IOException e)
	      {
	         e.printStackTrace();
	      }


	}
	public boolean getAlive(){
		boolean alive = false;
		try {
			Socket client = new Socket(serverName,port);
			alive = true;
			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alive;
	}
}

