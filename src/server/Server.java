package server;

import hmi.HMIstart;

import java.net.*;
import java.io.*;

import client.Bike;
import client.Configuration;

public class Server extends Thread
{
	
   private ServerSocket serverSocket;
   public static int port = 7999;
   
   public Server(int port) throws IOException
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
            	 Configuration rec = (Configuration) objectIn.readObject();
            	 HMIstart hmiStart = new HMIstart(rec);
            	 hmiStart.setVisible(true);
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
   public static void main(String [] args)
   {
      
      try
      {
         Thread t = new Server(Server.port);
         t.start();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}

