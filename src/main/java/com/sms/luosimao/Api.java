package com.sms.luosimao;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Administrator
 */
public class Api {
    public static void main(String[] args) {
        Api api = new Api();
        String httpResponse =  api.testSend();
         try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
            Logger.getLogger(Api.class.getName()).log(Level.SEVERE, null, ex);
        }

//        httpResponse =  api.testStatus();
//        try {
//            JSONObject jsonObj = new JSONObject( httpResponse );
//            int error_code = jsonObj.getInt("error");
//            if( error_code == 0 ){
//                int deposit = jsonObj.getInt("deposit");
//                System.out.println("Fetch deposit success :"+deposit);
//            }else{
//                String error_msg = jsonObj.getString("msg");
//                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
//            }
//        } catch (JSONException ex) {
//            Logger.getLogger(Api.class.getName()).log(Level.SEVERE, null, ex);
//        }


    }

    private String testSend(){
        // just replace key here
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api","key-be9d90f90cbab437117516dc55f2d9f2"));
        WebResource webResource = client.resource(
            "http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", "15922545427");
        formData.add("message", "验证码："+generateVrifyCode()+"【文推科技】");
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
        post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
        return textEntity;
    }
    
    private String generateVrifyCode(){
        final int length = 6;
        String retStr = "";
        String strTable = "1234567890";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }

    private String testStatus(){
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api","key-d609b769db914a4d959bae3414ed1f7X"));
        WebResource webResource = client.resource( "http://sms-api.luosimao.com/v1/status.json" );
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        ClientResponse response =  webResource.get( ClientResponse.class );
        String textEntity = response.getEntity(String.class);
        int status = response.getStatus();
        //System.out.print(status);
        //System.out.print(textEntity);
        return textEntity;
    }
}