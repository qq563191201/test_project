package sql;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

public class LeiDa_Data {
	
	public String url = "jdbc:mysql://192.168.1.100:3306/lesogo_xnry?characterEncoding\\=UTF-8";
	public String user = "lesogo";
	public String password = "123456";
	Connection con;
	Connection con1;
	public LeiDa_Data(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			con1 = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		LeiDa_Data LL = new LeiDa_Data();
		LL.wx_ld_data();
	}
	
	public void wx_ld_data(){
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement("select * from url_resource_types");
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				String typeid = result.getString("id");
				PreparedStatement pre1 = this.con.prepareStatement("select * from url_resources where type='"+typeid+"' order by publist_time desc limit 0,6");
				ResultSet result1 = pre1.executeQuery();
				while(result1.next()) {
					String t_id = result1.getString("type");
					String d_url = result1.getString("dest_url");
					String s_url = result1.getString("src_url");
					String time = result1.getString("publist_time");
					String station = result1.getString("station")!=null?result1.getString("station"):"";
					String sql = "insert into url_resources(type,dest_url,src_url,publist_time,station)values('" +
					t_id+"','"+d_url+"','"+s_url+"','"+time+"','"+station+"');";
					System.out.println(sql);
				}
			}
			System.out.println("完毕！！");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void get_image() throws SQLException{
		PreparedStatement pre = this.con.prepareStatement("select * from url_resources_1");
		ResultSet result = pre.executeQuery();
		while(result.next()) {
			String d_url = result.getString("dest_url");
			String typeid = result.getString("type");
			String parent_id = "";
			PreparedStatement pre1 = this.con.prepareStatement("select * from url_resource_types where id='"+typeid+"'");
			ResultSet result1 = pre1.executeQuery();
			while(result1.next()) {
				parent_id = result1.getString("parent_id");
			}
			String wxld = "";
			if(parent_id.equals("48") || parent_id.equals("49"))
			{
				wxld = "wxyt";
			}else{
				wxld = "ldhb";
			}
			String url = "http://118.112.180.201:10131/xnryDocument/"+wxld+d_url;
			String src = "";
			int i = 0;
			if(!(d_url!=null?d_url:"").equals("") && d_url.length()>20){
				i = d_url.indexOf("SEVP");
				src = d_url.substring(0, i);
				String dir = "/home/lesogo_mobile/tomcat6/webapps/xnryDocument/"+wxld+"/"+src;
				File file = new File(dir);
				file.mkdirs();
				downloadFromUrl(url, dir);
			}
		}
		System.out.println("完毕！！");
	}
	
	public static String downloadFromUrl(String url,String dir) {  
		  
        try {  
            URL httpurl = new URL(url);  
            String fileName = getFileNameFromUrl(url);  
            System.out.println(fileName);  
            File f = new File(dir + fileName);  
            FileUtils.copyURLToFile(httpurl, f);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "Fault!";  
        }   
        return "Successful!";  
    }  
	
	public static String getFileNameFromUrl(String url){  
        String name = new Long(System.currentTimeMillis()).toString() + ".X";  
        int index = url.lastIndexOf("/");  
        if(index > 0){  
            name = url.substring(index + 1);  
            if(name.trim().length()>0){  
                return name;  
            }  
        }  
        return name;  
    }  
}
