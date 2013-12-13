package client;

import java.net.*;
import java.io.*;

import server.Server;

public class Client
{
   public static void main(String [] args)
   {
      String serverName = "0.0.0.0";
      int port = 7999;
      
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
         ObjectOutputStream objectOut = new ObjectOutputStream(outToServer);
         objectOut.writeObject(new Configuration(450, 200, 0, 21));
         
         out.writeUTF("Hello from "
                      + client.getLocalSocketAddress());
         
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
   
   
}

