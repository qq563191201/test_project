package docfile.readname.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class readprop {
	private static String param1;   
    private static String param2;   
	public static void main(String[] args){
		Properties prop = new Properties();   
        InputStream in = Object.class.getResourceAsStream("/config.properties");   
        try {   
	        prop.load(in);   
	        param1 = prop.getProperty("pushUrl").trim(); 
	            System.out.println(param1);
	        } catch (IOException e) {   
	            e.printStackTrace();   
	        }   
	}
}
