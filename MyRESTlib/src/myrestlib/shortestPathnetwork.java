/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.util.ArrayList;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author M6000628
 */
public class shortestPathnetwork {
    public static UndirectedGraph<String, DefaultEdge> mynetwork;
//public static String hosts[];
public static ArrayList<String> hosts;
   public static void createnetwork()
   {
        mynetwork =
            new SimpleGraph<String, DefaultEdge>
            (DefaultEdge.class);
        hosts=new ArrayList<String>();
        MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.140:8181/restconf/operational/network-topology:network-topology/topology/flow:1/";
        String res=mylib.myGetRequest(myurl);
        JSONObject myobj=new JSONObject(res);
        JSONArray topology=myobj.getJSONArray("topology");
        //JSONObject mytopo=topology.getJSONObject(0);
        JSONArray node=(topology.getJSONObject(0)).getJSONArray("node");
        JSONArray link=(topology.getJSONObject(0)).getJSONArray("link");
        int i;
        for(i=0;i<node.length();i++)
        {
            JSONObject temp=node.getJSONObject(i);
            String nodeid=temp.getString("node-id");
            if(nodeid.charAt(0)=='h')
            {
                hosts.add(nodeid);
            }
            mynetwork.addVertex(nodeid);
            
        }
        for(i=0;i<link.length();i++)
        {
            JSONObject temp=link.getJSONObject(i);
             JSONObject dest=temp.getJSONObject("destination");
            String destid=dest.getString("dest-node");
            JSONObject source=temp.getJSONObject("source");
            String sourceid=source.getString("source-node");
            String sourcetp=source.getString("source-tp");
            String portstats=GetPortStatistics.getstats(sourcetp,sourceid);
            JSONObject portst=new JSONObject(portstats);
            String trans=portst.getString("transmitted");
            String recv=portst.getString("recieved");
            int trafffic=(Integer.parseInt(trans)+Integer.parseInt(recv))/2;
            
            
           
            
            mynetwork.addEdge(sourceid,destid);
            
        }
        
        
   }
    
    
}
