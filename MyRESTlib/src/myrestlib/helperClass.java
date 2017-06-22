/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myrestlib;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

/**
 *
 * @author edasdn
 */
public class helperClass {
    public static void main(String args[])
    {
        MyRESTlib mylib=new MyRESTlib();
        String myurl="http://172.16.52.140:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:1/table/0/flow/11";
        // String res=mylib.myGetRequest(myurl);
        //JsonFactory factory = new JsonFactory();
        // ObjectMapper mapper = new ObjectMapper(factory);
        //JsonNode rootNode = mapper.readTree(res);
        
        //parsejson(rootNode);
        int res=mylib.myPutRequest(myurl);
        System.out.println(res);
        // myurl="http://localhost:8181/restconf/config/opendaylight-inventory:nodes/node/openflow:1/table/0";
//mylib.myPutRequest(myurl);
//mylib.myDeleteRequest(myurl);
//mylib.myPostRequest(myurl);
    
}
    public static void parsejson(final JsonNode js)
    {
         Iterator<Map.Entry<String,JsonNode>> fieldsIterator = js.getFields();
            while (fieldsIterator.hasNext()) {
                
                Map.Entry<String,JsonNode> field = fieldsIterator.next();
                System.out.println("Key: " + field.getKey());
                final JsonNode value=field.getValue();
                 System.out.println("check:  "+value.isContainerNode());
                  Iterator<Map.Entry<String,JsonNode>> fieldsIterator1=value.getFields();
                System.out.println(fieldsIterator1.hasNext());
                  while(fieldsIterator1.hasNext())
                  {
                      Map.Entry<String,JsonNode> field1=fieldsIterator1.next();
                       System.out.println("Key: " + field1.getKey());
                  }
                if(value.isContainerNode())
                {
                    parsejson(value);
                }
                else
                {
                    System.out.println("value :   "+value);
                }
               
                
            }
    }
}
