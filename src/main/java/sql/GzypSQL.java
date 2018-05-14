package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import docfile.readname.PdfReader;

import util.AssistUtil;

public class GzypSQL {
	
//	public String url = "jdbc:mysql://115.28.143.46:34060/lesogo_sczdjcxt?useUnicode=true&characterEncoding=utf-8";
//	public String user = "lesogo_sczdjcxt";
//	public String password = "100200";
	
	public String url = "jdbc:mysql://localhost:3306/lesogo_ycdp_data?characterEncoding\\=UTF-8";
	public String user = "root";
	public String password = "";
	
//	public String url = "jdbc:mysql://58.17.246.47:3306/lesogo_dbry?characterEncoding\\=UTF-8";
//	public String user = "lesogo";
//	public String password = "123456";
	
	Connection con;
	Connection con1;
	private static Logger logger = Logger.getLogger(GzypSQL.class);
	public GzypSQL(){
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
	
	/**
	 * 
	 *@title
	 *@author Anleilei
	 *@date 下午03:42:51
	 *@version 1.0
	 *@Description  添加实况
	 *
	 *
	 *
	 */
	public void saveOrUpdate(){
		for (int i = 0; i < 24; i++) {
			PreparedStatement pre1;
			try {
				pre1 = this.con.prepareStatement(
						"insert into pub_tabtimedatas" +
						"(station_no,obstime,storetime,windvelocity,winddirect,precipitationamount," +
						"temp,relhumidity,stationpress) " +
						"select city_code,'2014-08-07 "+i+":00:00','2014-08-07 "+i+":00:00',"
						+(int)(Math.random()* 5+1)+","+(int)(Math.random()* 15+1)+","
						+round(Math.random()*50+0+"")+","+round(Math.random()*20+15+"")+","+round(Math.random()*10+10+"")+",6" +
						" from sys_city where city_code between 50000 and 60000");
				pre1.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("出错了。。。。");
			}
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕......");
	}
	
	/**
	 * 
	 *@title
	 *@author Anleilei
	 *@date 下午03:42:51
	 *@version 1.0
	 *@Description  添加实况
	 *
	 *
	 *
	 */
	public void saveOrUpdate11(){
		try {
			PreparedStatement pre1 = this.con.prepareStatement("select city_code from sys_city");
			ResultSet result1 = pre1.executeQuery();
			List<String> citycode = new ArrayList<String>();
			Map<String, String> map = new HashMap<String, String>();
			while(result1.next()){
				String station = result1.getString("city_code");
				if(station != null){
					map.put(station, station);
				}
			}
			String time = "2014-12-16 ";
			for(Map.Entry<String, String> m : map.entrySet()){
				String station = m.getKey();
				for (int i = 0; i < 24; i++) {
					PreparedStatement pre2;
					try {
						pre2 = this.con.prepareStatement(
								"insert into city_tme_aws_st" +
								"(stationnum,obstime,comptime," +
								"windvelocity,winddirect,precipitationamount," +
								"temp,relhumidity,stationpress) " +
								"values("+station+",'"+time+i+":00:00','"+time+i+":00:00',"
								+(int)(Math.random()* 5+1)+","+(int)(Math.random()* 15+1)+","+round(Math.random()*50+0+"")
								+","+round(Math.random()*7+34+"")+","+round(Math.random()*10+10+"")+",6)");
						pre2.executeUpdate();
						pre2.close();
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("出错了。。。。");
					}
				}
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕......");
	}
	
	/**
	 * 
	 *@title
	 *@author Anleilei
	 *@date 下午03:42:51
	 *@version 1.0
	 *@Description  添加实况
	 *
	 *
	 *
	 */
	public void saveOrUpdate_tabtime(){
		Long l1 = System.currentTimeMillis();
		for (int i = 0; i < 24; i++) {
			
			try {
				//执行分页查询插入
				PreparedStatement pre2 = this.con.prepareStatement("select * from pub_stations");
				ResultSet result2 = pre2.executeQuery();
//				String time = "2015-02-02 ";
//				Long l2 = System.currentTimeMillis();
//				int j = 0;
//				String sql = "insert into " +
//				"tabtime_data" +
//				"(data_city_code,data_obstime,data_comptime,data_windvelocity,data_winddirect,data_rain," +
//				"data_temp,data_relhumidity,data_stationpress) " +
//				"values(?,?,?,?,?,?,?,?,6)";
//				PreparedStatement pre1 = this.con.prepareStatement(sql);
//				while(result2.next()){
//					j++;
//					String rain = "0";
//					if(i==22){
//						rain = round(Math.random()*99+0+"");
//					}
//					String station = result2.getString("station_no");
////					sql = "insert into " +
////					"tabtime_data" +
////					"(data_city_code,data_obstime,data_comptime,data_windvelocity,data_winddirect,data_rain," +
////					"data_temp,data_relhumidity,data_stationpress) " +
////					"values('"+station
//////						"city_code"+
////					+"','"+time+i+":00:00','"+time+i+":00:00',"
////					+(int)(Math.random()* 5+1)+","+(int)(Math.random()* 15+1)+",'"
////					+rain+"',"+round(Math.random()*20+15+"")+","+round(Math.random()*10+10+"")+",6)";
//					if(i==22){
//						logger.info(sql);
//					}
//					pre1.setString(1, station);
//					pre1.setString(2, time+i+":00:00");
//					pre1.setString(3, time+i+":00:00");
//					pre1.setLong(4, (int)(Math.random()* 5+1));
//					pre1.setLong(5, (int)(Math.random()* 15+1));
//					pre1.setString(6, rain);
//					pre1.setString(7, round(Math.random()*20+15+""));
//					pre1.setString(8, round(Math.random()*10+10+""));
//					pre1.addBatch();
////					if(j%100==0){
////						pre1.executeBatch();
////					}
//					
////					pre1.executeUpdate();
////					pre1.close();
////					try{
////						Thread.sleep(200);
////					}catch(Exception e){
////						e.printStackTrace();
////					}
//				}
//				pre1.executeBatch(); // insert remaining records
//				pre1.clearBatch();
//				pre1.close();
				Long l4 = System.currentTimeMillis();
//				logger.info("单"+(l4-l2));
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("出错了。。。。");
			}
		}
		Long l3 = System.currentTimeMillis();
		logger.info("共"+(l3-l1));
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕......");
	}
	
	public void insertTimeData() throws SQLException{
		String time = "2015-04-28 ";
		for (int i = 0; i < 24; i++) {
			String rain = "0";
			String station = "A7439";
			String sql = "insert into " +
			"tabtime_data" +
			"(data_city_code,data_obstime,data_comptime,data_windvelocity,data_winddirect,data_rain," +
			"data_temp,data_relhumidity,data_stationpress) " +
			"values('"+station
//				"city_code"+
			+"','"+time+i+":00:00','"+time+i+":00:00',"
			+(int)(Math.random()* 5+1)+","+(int)(Math.random()* 15+1)+",'"
			+rain+"',"+round(Math.random()*20+15+"")+","+round(Math.random()*10+10+"")+",6)";
			PreparedStatement pre = this.con.prepareStatement(sql);
			pre.execute();
		}
	}
	
	/**
	 * 
	 *@title	添加预警
	 *@author Anleilei
	 *@date 上午10:39:55
	 *@version 1.0
	 *@Description
	 *
	 *
	 *
	 */
	public void saveOrUpdate1(){
		for (int i = 0; i < 24; i++) {
			PreparedStatement pre1;
			try {
				pre1 = this.con.prepareStatement(
						"insert into warn_infos(warn_supertype,warn_title,warn_content,warn_state,publish_state,warn_date,city_piece,relatively_url,local_file_name) SELECT " +
						"'13','ifyj13010101（寒潮蓝色预警信号）','','1','1','2014-03-26 "+i+":00:00',station_no,'qxzhyj/1357032848000.pdf','/var/lesogo/10_57_29_18/气象台/预报/预警信号发布/2013/预警信号发布/ifyj13010101（寒潮蓝色预警信号）.doc'" +
						" from pub_stations");
				pre1.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("出错了。。。。");
			}
		}
		System.out.println("执行完毕......");
	}
	/**
	 * 
	 *@title
	 *@author Anleilei
	 *@date 下午03:42:51
	 *@version 1.0
	 *@Description  实况图片
	 *
	 *
	 *
	 */
	public void saveOrUpdatereal_time_img(){
		for (int i = 0; i < 24; i++) {
			PreparedStatement pre1;
			try {
				pre1 = this.con.prepareStatement(
						"insert into real_time_img(img_city_code,img_abso_url,img_url,img_update) " +
						"select station_no,'/usr/tomcat6/lfImage/4.JPG','Hpo63jugsW1545.jpg','2014-05-26 "+i+":00:00' from pub_stations");
				pre1.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("出错了。。。。");
			}
		}
		System.out.println("执行完毕......");
	}
	
	/**
	 * 文档
	 * @throws IOException 
	 */
	public void saveOrUpdatedoc() throws IOException, SQLException{
		String name = "E:/Game/doc/";
		String outfile = "E:/Game/doc/";
		File file=new File(name);
		String test[];
		test=file.list();
//		for(int i=0;i<test.length;i++)
//		{
				PreparedStatement pre2 = this.con.prepareStatement("select * from document_info_types");
				ResultSet result2 = pre2.executeQuery();
				while(result2.next()){
					String id = result2.getString("type_id");
//					if(!id.equals("13") && !id.equals("19") && !id.equals("15")){
						String title = result2.getString("type_name");
						System.out.print(title);
						PreparedStatement pre1;
//						SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//						String date = formart.format(new Date());
//						if(test[i].contains(".pdf")){
////							String filename = System.currentTimeMillis()+".pdf";
//							String sql = "insert into document_info(info_title,info_content,info_type,info_url,info_status" +
//							",info_inputdate,info_updatedate,info_typePdf) " +
//							"values('测试-"+title+i+"','"+title+"',"+id+",'/doc/"+test[i]+"',1,'" +
//									date+"','"+date+"',2)";
//							System.out.println(sql);
////							System.out.println(outfile+filename);
////							FileUtils.copyFile(new File(name+test[i]), new File(outfile+filename));
//							pre1 = this.con.prepareStatement(sql);
//							pre1.executeUpdate();
//						}
//					}
				}
//		}
		System.out.println("执行完毕......");
	}
	
	/**
	 * 文档
	 * @throws IOException 
	 */
	public void saveOrUpdatedoc(String title, String filename, Long typeid, String station) throws IOException, SQLException{
//		PreparedStatement pre2 = this.con.prepareStatement("select * from document_info_types where type_id='"+typeid+"'");
//		ResultSet result2 = pre2.executeQuery();
//		int i = 0;
//		while(result2.next()){
//			String id = result2.getString("type_id");
			PreparedStatement pre1;
			SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = formart.format(new Date());
			String sql = "insert into document_info(info_title,info_content,info_type,info_url,info_status" +
			",info_inputdate,info_updatedate,info_typePdf, area_station) " +
			"values('"+title+"','"+title+"',1,'/doc/"+filename+"',1,'" +
					date+"','"+date+"',2,'"+station+"')";
			System.out.println(sql);
			pre1 = this.con.prepareStatement(sql);
			pre1.executeUpdate();
//			i++;
//		}
		System.out.println("执行完毕......");
	}
	
	/**
	 * 
	 * @title 根据pdf导入文档
	 * @description
	 * @param dir
	 * @param typeid
	 * @throws Exception
	 * @author Xuxiaobing
	 * @date 2015-3-2 下午01:41:12
	 */
//	@SuppressWarnings("unused")
//	private void showAllFiles(File dir, Long typeid) throws Exception{
//		File[] fs = dir.listFiles();
//		for(int i=0; i<fs.length; i++){
//			if(fs[i].toString().contains(".doc")){
//				String filename = System.currentTimeMillis()+".pdf";
//				String add_file = "E:\\Game\\"
//					+filename;
//				doctopdf.docToPdf(fs[i].getAbsolutePath(), add_file);
//				File file=new File(add_file);
//		        try{
//		            String cont=PdfReader.getText(file);
//		            String title = PdfReader.getSubString(cont,"\r");
//		            System.out.println(fs[i].getAbsolutePath()+">>>"+title+">>>>"+filename);
//		            GzypSQL insql = new GzypSQL();
//					insql.saveOrUpdatedoc(title, filename, typeid, "1005");
//		        }catch(Exception e){
//		            System.out.println("Strip failed.");
//		            System.out.println(fs[i].getAbsolutePath());
//		            e.printStackTrace();
//		        }
//				
//			}
//			if(fs[i].isDirectory()){
//				try{
//					showAllFiles(fs[i], typeid);
//				}catch(Exception e){}
//			}
//		}
//	}
	
	/**
	 * 卫星雷达图片
	 * @throws SQLException 
	 * @throws InterruptedException 
	 */
	public void saveOrUpdateurl_resources() throws SQLException, InterruptedException{
		//1-卫星云图2-雷达回波
		File file=new File("E:/Game/ldhb/");
		String test[];
		test=file.list();
//		PreparedStatement pre2 = this.con.prepareStatement("select * from url_resource_types");
//		ResultSet result2 = pre2.executeQuery();
//		while(result2.next()){
//			String id = result2.getString("id");
			for(int i=0;i<test.length;i++)
			{
				PreparedStatement pre1;
				try {
					SimpleDateFormat formart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = formart.format(new Date());
					String sql = "insert into url_resources(type,dest_url,src_url,station,publist_time)" +
					"values("+8+",'"+test[i]+"','"+test[i]+"','1006','"+date+"')";
					pre1 = this.con.prepareStatement(sql);
					System.out.println(sql);
					pre1.executeUpdate();
					Thread.sleep(1000);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("出错了。。。。");
				}
			}
//		}
		System.out.println("执行完毕......");
	}
	
	/**
	 * 
	 *@title
	 *@author Anleilei
	 *@date 下午03:41:22
	 *@version 1.0
	 *@Description	给天气预报表加数据
	 *
	 *
	 *
	 */
	public void saveOrUpdate2(){
		PreparedStatement pre2;
		try {
			for (int i = 20; i <= 31; i++) {
				pre2 = this.con.prepareStatement("insert into forecasts_city" +
						"(weather_city_code,weather_day,weather_one_code," +
						"weather_two_code,weather_low_temp,weather_high_temp," +
						"one_winddirect,one_windvelocity,two_winddirect,two_windvelocity)" +
						"select station_no ,'2014-10-"+i+"','1','2','3','4','5','6','8','9'" +
						"from pub_stations");
				pre2.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("出错拉。。。。");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕.......");
	}
	/**
	 * 
	 *@title
	 *@author Anleilei
	 *@date 下午03:41:22
	 *@version 1.0
	 *@Description	给天气预报表加数据
	 *
	 *
	 *
	 */
	public void saveOrUpdate21(){
		try {
//			for (int i = 20; i <= 30; i++) {
//			pre2 = this.con.prepareStatement("insert into forecasts_city" +
//					"(weather_city_code,weather_city,weather_day,weather_create,weather_update,weather_one_code," +
//					"weather_two_code,weather_low_temp,weather_high_temp," +
//					"one_winddirect,one_windvelocity,two_winddirect,two_windvelocity)" +
//					" select city_code,city_id,'2014-10-"+i+"','2014-10-"+i+"','2014-10-"+i+"',"+Math.random()+","+Math.random()+","+(int)(Math.random()* 5+10)+","+(int)(Math.random()* 15+15)+","+Math.random()+","+Math.random()+","+Math.random()+","+Math.random()+"" +
//			" from sys_city where city_code between 50000 and 60000");
//			pre2.executeUpdate();
//		}
			PreparedStatement pre1 = this.con.prepareStatement("select city_code from sys_city");
			ResultSet result1 = pre1.executeQuery();
			List<String> citycode = new ArrayList<String>();
			Map<String, String> map = new HashMap<String, String>();
			while(result1.next()){
				String station = result1.getString("city_code");
				if(station != null){
					map.put(station, station);
				}
			}
			String date = "2014-12-";
			for(Map.Entry<String, String> m : map.entrySet()){
				String station = m.getKey();
				for (int i = 1; i <= 30; i++) {
					PreparedStatement pre2 = this.con.prepareStatement("insert into forecasts_city" +
							"(weather_city_code,weather_day,weather_create,weather_update,weather_one_code," +
							"weather_two_code,weather_low_temp,weather_high_temp," +
							"one_winddirect,one_windvelocity,two_winddirect,two_windvelocity)" +
							"values("+station+",'"+date+i+"','"+date+i+"','"+date+i+"',"+Math.random()+","
							+Math.random()+","+(int)(Math.random()* 5+10)+","+(int)(Math.random()* 15+15)+","+Math.random()
							+","+Math.random()+","+Math.random()+","+Math.random()+")");
					pre2.executeUpdate();
					pre2.close();
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("出错拉。。。。");
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("执行完毕.......");
	}
		
		/**
		 * 保留一位小数
		 *@engineer wangrun
		 *@Description 
		 * time 2013-1-23 下午01:34:38
		 * @param arg
		 * @return
		 * @return String
		 */
		public static String round(String arg){
			if(arg==null)return "0.0";
			arg=arg.replaceAll("\r", "").replaceAll("\n", "").replaceAll(" ", "").trim();
			int position = arg.lastIndexOf(".");
			int lastPosition=arg.length()-1;
			if(lastPosition-position>1){
				arg=arg.substring(0, position+2);
			}
			return arg;
		}
		
	public void readwarn() throws SQLException, UnsupportedEncodingException{
		
		String sql = new String(("insert into push_log(push_type,push_original_id,push_state,push_title,push_create)" +
		"values('1','12','2','测试','2014-12-24')"));
		PreparedStatement pre = this.con.prepareStatement(sql);
//		pre.setString(1, new String(("测试").getBytes("gbk")));
		int i = pre.executeUpdate();
		pre.close();
		System.out.println(i);
	}
	public static void main(String[] args) throws Exception {
//		new sql_sum().saveOrUpdatereal_time_img();
//		new sql_sum().saveOrUpdate1();
////		new sql_sum().saveOrUpdate();
//		new GzypSQL().saveOrUpdate_tabtime();
		GzypSQL file = new GzypSQL();
		file.insertTimeData();
//		System.out.println("\u4E0A\u4F20\u6587\u4EF6\u6269\u5C55\u540D\u662F\u4E0D\u5141\u8BB8\u7684\u6269\u5C55\u540D\u3002\u53EA\u5141\u8BB8 =");
		
	}
}
