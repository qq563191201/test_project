package com.mysql.sql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import net.sf.json.JSONArray;

import com.mysql.common.ConnectionUtil;


public class ExcuteSql {
	
	public String url;
	public String user;
	public String password;
	
	Connection con;
	
	public ExcuteSql(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
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
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
//		new ExcuteSql().excute();
		new ExcuteSql().insert();
	}
	
	public void insert(){
		String json = "[{\"shopid\":43,\"goodname\":\"\u5c0f\u7c73\u624b\u73af\",\"goodspend\":\"180\u4e07\",\"goodsneed\":1800000,\"isdiscount\":\"2\",\"discountimg\":\"00\",\"sourcespend\":\"180\u4e07\",\"isNew\":\"1\",\"type\":2},{\"shopid\":44,\"goodname\":\"\u5357\u6781\u4eba\u6795\u5934\",\"goodspend\":\"90\u4e07\",\"goodsneed\":900000,\"isdiscount\":\"2\",\"discountimg\":\"00\",\"sourcespend\":\"90\u4e07\",\"isNew\":\"1\",\"type\":2},{\"shopid\":2,\"goodname\":\"\u8bdd\u8d3930\u5143\",\"goodspend\":\"60\u4e07\",\"goodsneed\":600000,\"isdiscount\":\"0\",\"discountimg\":\"85\",\"sourcespend\":\"420\u4e07\",\"isNew\":\"0\",\"type\":1},{\"shopid\":3,\"goodname\":\"\u8bdd\u8d3950\u5143\",\"goodspend\":\"100\u4e07\",\"goodsneed\":1000000,\"isdiscount\":\"0\",\"discountimg\":\"85\",\"sourcespend\":\"420\u4e07\",\"isNew\":\"0\",\"type\":1},{\"shopid\":4,\"goodname\":\"\u8bdd\u8d39100\u5143\",\"goodspend\":\"200\u4e07\",\"goodsneed\":2000000,\"isdiscount\":\"0\",\"discountimg\":\"85\",\"sourcespend\":\"420\u4e07\",\"isNew\":\"0\",\"type\":1},{\"shopid\":30,\"goodname\":\"5M\uff08\u7535\u4fe1\uff09\",\"goodspend\":\"3\u4e07\",\"goodsneed\":30000,\"isdiscount\":\"0\",\"discountimg\":\"85\",\"sourcespend\":\"3\u4e07\",\"isNew\":\"0\",\"type\":4},{\"shopid\":35,\"goodname\":\"50M\uff08\u8054\u901a\uff09\",\"goodspend\":\"10\u4e07\",\"goodsneed\":100000,\"isdiscount\":\"0\",\"discountimg\":\"85\",\"sourcespend\":\"10\u4e07\",\"isNew\":\"0\",\"type\":4},{\"shopid\":38,\"goodname\":\"30M\uff08\u79fb\u52a8\uff09\",\"goodspend\":\"10\u4e07\",\"goodsneed\":100000,\"isdiscount\":\"0\",\"discountimg\":\"85\",\"sourcespend\":\"10\u4e07\",\"isNew\":\"0\",\"type\":4}]";
		JSONArray array = JSONArray.fromObject(json);
		String sql = "";
		for(int i = 0; i < array.size(); i++){
			net.sf.json.JSONObject jsonObject = array.getJSONObject(i);
			String shopid = jsonObject.getString("shopid");
			String goodname = jsonObject.getString("goodname");
			String goodspend = jsonObject.getString("goodspend");
			String goodsneed = jsonObject.getString("goodsneed");
			String isdiscount = jsonObject.getString("isdiscount");
			String discountimg = jsonObject.getString("discountimg");
			String sourcespend = jsonObject.getString("sourcespend");
			String isNew = jsonObject.getString("isNew");
			String type = jsonObject.getString("type");
//			System.out.println(shopid+"/"+goodname+"/"+goodspend+"/"+goodsneed
//					+"/"+isdiscount+"/"+discountimg+"/"+sourcespend+"/"+isNew+"/"+type+"/");
			sql = "insert into shopgoodsconfig(id,shopname,shopspend,typeid,stock,surplusstock,inserttime,cost,showtype,disable,buycount,weight,discount,isnew)values("
					+shopid+",'"+goodname+"',"+goodsneed+",'"+type+"',20,20,now(),"+goodsneed+",1,0,0,"+i+","+isdiscount+","+isNew+");";
			System.out.println(sql);
		}
	}
	
	public void excute(){
		String sql = "select * from tb_apps_detail order by id asc";
		ResultSet result = null;
		PreparedStatement pre = null;
		ResultSet result1 = null;
		PreparedStatement pre1 = null;
		try{
			pre = this.con.prepareStatement(sql);
			result = pre.executeQuery();
			while(result.next()){
				String searchTag = result.getString("searchTag")!=null?result.getString("searchTag"):"";
				Long detailId = result.getLong("id");
				System.out.println(detailId);
				if(!searchTag.equals("")){
					String[] arr = searchTag.split(",");
					String inSql = "insert into tb_app_tag(name,state,num,create_time,update_time)";
					for(int j=0;j<arr.length;j++){
						String name = arr[j];
						try{
							pre1 = this.con.prepareStatement("select * from tb_app_tag where name=?");
							pre1.setString(1, name);
							result1 = pre1.executeQuery();
							if(result1.next()){
								Long tagId = result1.getLong("id");
								Long num = result1.getLong("num");
								num++;
								new ConnectionUtil().excute("update tb_app_tag set num="+num+" where id="+tagId);
							}else{
								inSql += " SELECT '"+name+"',1,1,now(),now() UNION ALL ";
							}
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							if(result1!=null){
								try{
									result1.close();
								}catch(SQLException e){
									e.printStackTrace();
								}
							}
							if(pre1!=null){
								try{
									pre1.close();
								}catch(SQLException e){
									e.printStackTrace();
								}
							}
						}
					}
					if(inSql.contains("UNION ALL")){
						inSql = inSql.substring(0, inSql.lastIndexOf(" UNION ALL"))+";";
						new ConnectionUtil().excute(inSql);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(result!=null){
				try{
					result.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pre!=null){
				try{
					pre.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

}
