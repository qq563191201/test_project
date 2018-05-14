package com.mysql.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.sql.ExcuteSql;

public class ConnectionUtil {
	
	public String url;
	public String user;
	public String password;
	
	Connection con;
	
	public ConnectionUtil(){
		try {
			// 构造Properties对象  
	        Properties properties = new Properties();  
	        InputStream is = null;  
			try {
				// 获取配置文件输入流  
		         is = ExcuteSql.class.getResourceAsStream("/application.properties");  
		         // 加载配置文件  
		         properties.load(is);
		         // 读取配置文件  
		         url = (String) properties.get("url");
		         user = (String) properties.get("username");
		         password = (String) properties.get("password");
			}catch (IOException e) {  
		    	 e.printStackTrace();  
			}finally {  
				// 判断输入流是否为空  
		    	 if (null != is) {  
		    		 try {  
		    			 // 关闭输入流  
		    			 is.close();  
		    		 } catch (IOException e) {  
		    			 e.printStackTrace();  
		    		 }
	            }
		     }
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: 执行sql语句
	 * @Description:
	 * @param sql
	 * @author Xuxiaobing
	 * @date 2015-9-23 下午4:29:20
	 * @version V1.0
	 */
	public void excute(String sql){
		PreparedStatement pre = null;
		try{
			pre = this.con.prepareStatement(sql);
			pre.execute();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pre != null){
				try {
					pre.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
