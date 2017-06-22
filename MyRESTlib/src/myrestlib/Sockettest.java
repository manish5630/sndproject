/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class Sockettest {
    private static ServerSocket socket;

    private static Socket connection;
    private static String command       = new String();
    private static String responseStr   = "";

    private static int port = 1338;

    public static void main(String args[])  {
        System.out.println("Signal Server is running.");

        try  {
            socket = new ServerSocket(port);

            while (true)  {
                connection = socket.accept();

                InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
               // DataOutputStream response = new DataOutputStream(connection.getOutputStream());
                OutputStream out=connection.getOutputStream();
                //OutputStreamWriter outw=new OutputStreamWriter(out);
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                PrintStream ps=new PrintStream(out);
                BufferedReader input = new BufferedReader(inputStream);
                

                command = input.readLine();
                 System.out.println(command+"\n");
                JSONObject test=new JSONObject(command);
                String operation=test.getString("operation");
                if(operation.contentEquals("del"))
                {
                    responseStr=Deleteflow.deletemyflow(test);
                    responseStr=responseStr+"\r\n";
                    ps.write(responseStr.getBytes());
                    ps.flush();
                }
                if(operation.contentEquals("getstatsport"))
                {
                    responseStr=GetPortStatistics.getstats(test.getString("portid"),test.getString("swid"));
                    responseStr=responseStr+"\r\n";
                    ps.write(responseStr.getBytes());
                    ps.flush();
                }
                if(operation.contentEquals("addflow"))
                {
                    responseStr=Createflow.Addflow(test);
                     responseStr=responseStr+"\r\n";
                    ps.write(responseStr.getBytes());
                    ps.flush();
                    
                }
                if(operation.contentEquals("getflow"))
                {
                    responseStr=Getflow.getmyflow(test);
                     responseStr=responseStr+"\r\n";
                     System.out.println(responseStr);
                    ps.write(responseStr.getBytes());
                    ps.flush();
                }
                
                
//outw.write(responseStr);
//ps.write(responseStr.getBytes());
//ps.flush();


               // response.writeBytes(responseStr);
                //response.flush();
                //response.close();

               
            }
        } catch (IOException e)  {
            System.out.println("Fail!: " + e.toString());
        }

        System.out.println("Closing...");
    }
    
}
