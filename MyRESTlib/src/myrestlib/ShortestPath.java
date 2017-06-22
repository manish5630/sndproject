/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.jgrapht.EdgeFactory;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author M6000628
 */
public class ShortestPath {
    public static void main(String args[])
    {
        System.out.println("Enter the source and destination mac address\n");
        Scanner sc=new Scanner(System.in);
        String h1=sc.nextLine();
        String h2=sc.nextLine();
         shortestPathnetwork.createnetwork();
         String mh1="host:"+h1;
         String mh2="host:"+h2;
         System.out.println(mh1+"\n"+mh2);
         int s=0,d=0;
         int i;
         for(i=0;i<shortestPathnetwork.hosts.size();i++)
         {
             String temp=shortestPathnetwork.hosts.get(i);
             if(temp.contentEquals(mh1))
             {
                 s=1;
             }
             if(temp.contentEquals(mh2))
             {
                 d=1;
             }
         }
         if(s==0 && d==0)
         {
           System.out.println("Source and destination are not found in network\n");   
         }
         else if(s==0 && d==1)
         {
             System.out.println("Source is not found in network\n");   
         }
         else if(s==1 && d==0)
         {
            System.out.println("destination is not found in network\n");    
         }
         else
         {
        System.out.println("Shortest path from source to destination:");
        List path =
            DijkstraShortestPath.findPathBetween(shortestPathnetwork.mynetwork,mh1,mh2);
        System.out.println(path + "\n");
         }

       
      //  System.out.println("Shortest path from c to i:");
        //path = DijkstraShortestPath.findPathBetween(mynetwork, "c", "i");
        //System.out.println(path);

        
    }
    
}
