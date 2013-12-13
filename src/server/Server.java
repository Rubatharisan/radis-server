package server;

import hmi.HMIstart;

import java.net.*;
import java.io.*;
import client.Configuration;
import client.Recipe;
import domain.Order;

public class Server extends Thread
{
	
   private ServerSocket serverSocket;
   public static int port = 7999;
   
   public Server() throws IOException
   {
      serverSocket = new ServerSocket(port);
      
   }

   public void run()
   {
      while(true)
      {
         try
         {
        	System.out.println("Server name: "+serverSocket.getInetAddress().getHostName());
            System.out.println("Waiting for client on port " +
            serverSocket.getLocalPort() + "...");
            Socket server = serverSocket.accept();
            System.out.println("Just connected to "
                  + server.getRemoteSocketAddress());
        
            
            ObjectInputStream objectIn = new ObjectInputStream(server.getInputStream());
            
            try {
            	 Recipe rec = (Recipe) objectIn.readObject();
            	 startHMI(rec, rec.getProductName());

//				System.out.println("Object Received: width: "+rec.getWidth()+" height: "+rec.getHeight());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            DataOutputStream out =
                 new DataOutputStream(server.getOutputStream());
            out.writeUTF("Thank you for connecting to "
              + server.getLocalSocketAddress() + "\nGoodbye!");
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("Socket timed out!");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }

   private void startHMI(Recipe rec, String prod_name){
	   
  	 HMIstart hmiStart = new HMIstart(rec,prod_name);
  	 hmiStart.setVisible(true);
  	 
   }
}

