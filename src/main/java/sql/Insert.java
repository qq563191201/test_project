package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import util.AssistUtil;

public class Insert {
	public String url = "jdbc:mysql://localhost:3306/sichuan?characterEncoding\\=UTF-8";
	public String user = "root";
	public String password = "";
//	public String url = "jdbc:mysql://39.108.89.180:6231/majiang_houtai?characterEncoding\\=UTF-8";
//	public String user = "majiang";
//	public String password = "m32MajiangGts@138$1";
	
	Connection con;
	Connection con1;
	public Insert(){
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
	
	public static void characterSqlTable() {
		String tb = "act_egg_code, act_egg_info, act_egg_log, act_egg_seller, activity, activity_general, activity_user_group, admin, area_canton, area_canton_info, area_game_info, area_large_info, area_plays, channel_package, channel_package_contact, channel_package_version, competition_area, competition_award, competition_config, config_channel, config_horse_race, config_hour, config_notice, config_payment, config_type, dc_admin, dc_food, dc_user, dc_user_food_log, game_area, game_info, game_on_off, invitation_log, invitation_proxy_log, invitation_wx_friends, inviter_record, knowledge_base, lottery_config, lottery_goods, lottery_log, lottery_user, mail_log, mail_log_user, mail_model, mail_system, mail_system_user, match_change_rule, module, notice_channel, on_off_type, online_every_hours_log, operation_log, order_info, order_success, package_game_list, page_custom_info, page_custom_user, pca_area, pca_city, pca_province, permission, play_list, proxy, proxy_apply, proxy_balance, proxy_card_log, proxy_crash_log, proxy_general, proxy_general_total, proxy_goods, proxy_order, proxy_order_success, proxy_random_id, proxy_rebate, proxy_statistics, proxy_together, proxy_user_discount, proxy_wealth, proxy_wealth_log, race_package, role, role_permission, share_log, share_provide_log, shopping_mall, shopping_mall_channel, shopping_tab, statictics_proxy_user_play, statistics_channel_every_day, statistics_channel_every_month, statistics_coins_day, statistics_coins_day_info, statistics_day_info, statistics_every_hours, statistics_montch_info, statistics_new_user_every_day, statistics_play_distribution, statistics_proxy_coins_day, statistics_room_hours, statistics_sys_every_day, statistics_sys_month_day, statistics_total, user_card_log, user_feedback, user_first_login, user_goods, user_inform_num, user_init, user_other, user_reg, user_reg_copy, user_seal, user_seal_ip, user_spread_log, user_test, user_total, web_dynamic, web_dynamic_info, work_order, work_record";
		String[] arr = tb.split(",");
		StringBuffer buf = new StringBuffer();
		for(String l:arr) {
			buf.append("alter table "+l+" convert to character set utf8mb4 COLLATE utf8mb4_bin; ");
		}
		System.out.println(buf.toString());
	}
	
	public void updateInviterRecord() {
		PreparedStatement pre;
		try {
//			pre = this.con.prepareStatement("SELECT * FROM `invitation_log` WHERE be_invitation_id <> ''");
//			ResultSet result3 = pre.executeQuery();
//			while(result3.next()) {
//				int be_invitation_id = result3.getInt("be_invitation_id");
//				pre = this.con.prepareStatement("SELECT count(*) FROM mj_center.user_win_lose_log WHERE user_id ="+be_invitation_id+" and result != 2");
//				ResultSet result4 = pre.executeQuery();
//				result4.next();
//				int num = result4.getInt(1); 
//				String sql = "update invitation_log set play_num="+num+" where be_invitation_id="+be_invitation_id+";";
//				pre.executeUpdate(sql);
//				System.out.println(sql);
//			}
			pre = this.con.prepareStatement("SELECT * FROM `inviter_record` WHERE invitation_num < play_one_num");
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				int userId = result.getInt("user_id");
				String sql = "SELECT count(*) FROM `invitation_log` WHERE invitation_id = "+userId+" AND type = 1 AND be_invitation_id <> '' AND play_num >=1";
				pre = this.con.prepareStatement(sql);
				ResultSet result1 = pre.executeQuery();
				result1.next();
				int num1 = result1.getInt(1); 
				pre = this.con.prepareStatement("SELECT count(*) FROM `invitation_log` WHERE invitation_id = "+userId+" AND type = 1 AND be_invitation_id <> '' AND play_num >=8");
				ResultSet result2 = pre.executeQuery();
				result2.next();
				int num8 = result2.getInt(1); 
				sql = "update `inviter_record` set play_one_num="+num1+", play_eight_num="+num8+" where user_id="+userId+";";
				System.out.println(sql);
				pre.executeUpdate(sql);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updateUser() {
		PreparedStatement pre;
		try {
			long l1 = System.currentTimeMillis();
			System.out.println("start");
			pre = this.con.prepareStatement("select Id,general_id,inviter_id from user order by Id asc");
//			pre = this.con.prepareStatement("select Id,general_id from user where inviter_id<>0");
			ResultSet result = pre.executeQuery();
			Map<Integer, Integer> map = new HashMap<>();
			//获取父节点
			while(result.next()) {
				int id = result.getInt("Id");
				int generalId = result.getInt("general_id");
				int inviterId = result.getInt("inviter_id");
				if(inviterId != 0) {
					generalId = map.get(inviterId);
					String sql = "update user set general_id="+generalId+" where Id="+id;
					PreparedStatement pre2 = this.con.prepareStatement(sql);
					if(generalId != 0)
					System.out.println(sql+";");
//					pre2.executeUpdate(sql);
				}
				map.put(id, generalId);
			}
			long l4 = System.currentTimeMillis();
			System.out.println("end time:"+(l4-l1));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean updateGeneralId(int inviterId, int generalId) {
		try {
		//查询当前用户id的邀请用户
		PreparedStatement pre3 = this.con1.prepareStatement("select Id, general_id from user where Id="+inviterId);
		ResultSet result3 = pre3.executeQuery();
		if(result3.next()) {
			int id = result3.getInt("Id");
			int generalId1 = result3.getInt("general_id");
			String sql = "update user set general_id="+generalId+" where Id="+id;
			PreparedStatement pre2 = this.con.prepareStatement(sql);
			System.out.println("l2 start,id:"+id+",generalId:"+generalId);
			long l2 = System.currentTimeMillis();
			pre2.executeUpdate(sql);
			long l3 = System.currentTimeMillis();
			System.out.println("end time:"+(l3-l2));
			return updateGeneralId(id, generalId1);
		}
		}catch(Exception e) {
			
		}finally {
			try {
				this.con1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public void updateNotInviterIdUser() {
		PreparedStatement pre;
		try {
			long l1 = System.currentTimeMillis();
			System.out.println("start");
			pre = this.con.prepareStatement("select Id,inviter_id from user where inviter_id<>0");
			ResultSet result = pre.executeQuery();
			//获取父节点
			while(result.next()) {
				int id = result.getInt("Id");
				int inviterId = result.getInt("inviter_id");
				int generalId = result.getInt("general_id");
				long l2 = System.currentTimeMillis();
				System.out.println("l1 start,id:"+id+",generalId:"+generalId);
				updateGeneralId1(inviterId);
				long l3 = System.currentTimeMillis();
				System.out.println("end time:"+(l3-l2));
			}
			long l4 = System.currentTimeMillis();
			System.out.println("end time:"+(l4-l1));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean updateGeneralId1(int inviterId) throws SQLException {
		//查询当前用户id的邀请用户
		PreparedStatement pre3 = this.con.prepareStatement("select Id, general_id from user where Id="+inviterId);
		ResultSet result3 = pre3.executeQuery();
		if(result3.next()) {
			int id = result3.getInt("Id");
			int generalId1 = result3.getInt("general_id");
			String sql = "update user set general_id="+generalId1+" where Id="+id;
			PreparedStatement pre2 = this.con.prepareStatement(sql);
			System.out.println("l2 start,id:"+id+",generalId:"+generalId1);
			long l2 = System.currentTimeMillis();
			pre2.executeUpdate(sql);
			long l3 = System.currentTimeMillis();
			System.out.println("end time:"+(l3-l2));
		}
		return false;
	}
	
	/**
	 * 
	 * @title 获取父节点
	 * @description
	 * @throws SQLException
	 * @author Xuxiaobing
	 * @date 2015-2-26 下午06:02:01
	 */
	public void list() throws SQLException{
		Long l1 = System.currentTimeMillis();
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement("select * from pub_stations");
			ResultSet result = pre.executeQuery();
			int i = 0;
			//获取父节点
			while(result.next()) {
				String station = result.getString("station_no");
				String id = result.getString("station_id");
				String province = result.getString("province").replace("省", "").replace(" ", "");
				String city = result.getString("city").replace("市", "").replace(" ", "");
				String county = result.getString("county").replace(" ", "");
				if(county.equals("扎赉特旗")){
					county = "扎赉特";
				}
				if(county.equals("乌拉特前旗")){
					county = "乌前旗";
				}
				if(county.equals("乌拉特中旗")){
					county = "乌中旗";
				}
				if(county.equals("乌拉特后旗")){
					county = "乌后旗";
				}
				if(county.equals("孪井滩示范区")){
					county = "孪井滩";
				}
				if(county.equals("额济纳旗")){
					county = "额济纳";
				}
				if(county.equals("察哈尔右翼前旗")){
					county = "察右前旗";
				}
				if(county.equals("察哈尔右翼后旗")){
					county = "察右后旗";
				}
				if(county.equals("察哈尔右翼中旗")){
					county = "察右中旗";
				}
				if(county.equals("经济开发区")){
					county = "阿左旗";
				}
				if(county.equals("阿拉善右旗")){
					county = "阿右旗";
				}
				if(county.equals("阿拉善阿左旗")){
					county = "阿阿左旗";
				}
				if(county.equals("莫旗")){
					county = "莫力达瓦";
				}
				if(county.equals("扎赉特旗")){
					county = "扎赉特";
				}
				if(county.equals("二道")){
					county = "安图";
				}
				if(city.equals("锡林郭勒盟")){
					county = "锡林浩特";
				}
				if(city.equals("长白山管委会")){
					county = "白山";
				}
				if(city.equals("大兴安岭地区")){
					county = "大兴安岭";
				}
				if(!(county!=null?county:"").equals("")){
					county = county.replace("县", "").replace("市", "").replace("区", "");
				}
				Integer citycode = 0;
				Integer cityid = 0;
				if(!(city!=null?city:"").equals("")){
					PreparedStatement pre2 = this.con.prepareStatement("select * from sys_city where city_name='"+county+"' and city_prov_cn='"+province+"'");
					ResultSet result2 = pre2.executeQuery();
					int j = 0;
					while(result2.next()){
						if(result2.getInt("city_code") >50000){
							citycode = result2.getInt("city_code");
							cityid = result2.getInt("city_id");
							j++;
						}
					}
					pre2.close();
					result2.close();
					int y = 0;
					if(j==0){
						PreparedStatement pre3 = this.con.prepareStatement("select * from sys_city where city_name='"+city+"' and city_prov_cn='"+province+"'");
						ResultSet result3 = pre3.executeQuery();
						while(result3.next()){
							if(result3.getInt("city_code") >50000){
								citycode = result3.getInt("city_code");
								cityid = result3.getInt("city_id");
								y++;
							}
						}
						pre3.close();
						result3.close();
					}
					String sql = "update pub_stations set parent_no='"+citycode+"' where station_id='"+id+"'";
					PreparedStatement pre4 = this.con.prepareStatement(sql);
					pre4.executeUpdate(sql);//date -s "20150227 14:55:00"
					if(j==0&&y==0){
						System.out.println(j+"<<<"+station+"/"+citycode+"/"+cityid);
					}
					i++;
				}
//				}
			}
			Long l2 = System.currentTimeMillis();
			System.out.println(i+"<<<"+(l2-l1)+"ms");
//			} 
			
		}finally{
			con1.close();
			con.close();
		}
		
	}
	
	public void scenic(){
		PreparedStatement pre;
		try {
			Long l1 = System.currentTimeMillis();
			//查询四川下所有城市id
			pre = this.con.prepareStatement("select * from sys_city where city_parent=12187");
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				Long cityid = result.getLong("city_id");
				PreparedStatement pre2 = this.con.prepareStatement( "select * from scenic_area where scenic_city="+cityid);
				ResultSet result2 = pre2.executeQuery();
				while(result2.next()){
					String name = result2.getString("scenic_name");
					cityid = result2.getLong("scenic_city");
					System.out.println(cityid);
					PreparedStatement pre3 = this.con.prepareStatement("select * from sys_city where city_id="+cityid);
					ResultSet result3 = pre3.executeQuery();
					String citycode = "";
					while(result3.next()){
						citycode = result3.getString("city_code");
					}
					result3.close();
					pre3.close();
					String scenic_parent = result2.getString("scenic_parent");
					String scenic_latitude = result2.getString("scenic_latitude")!=null?result2.getString("scenic_latitude"):"";
					if(scenic_latitude.equals("")){
						scenic_latitude = "0";
					}
					String scenic_longitude = result2.getString("scenic_longitude")!=null?result2.getString("scenic_longitude"):"";
					
					if(scenic_longitude.equals("")){
						scenic_longitude = "0";
					}
					String scenic_content = result2.getString("scenic_content");
					String scenic_create = result2.getString("scenic_create");
					String scenic_update = result2.getString("scenic_create");
					String scenic_level = result2.getString("scenic_level")!=null?result2.getString("scenic_level"):"";
					if(scenic_level.equals("")){
						scenic_level = "0";
					}
					String scenic_areais = result2.getString("scenic_areais");
					String sql = "insert into scenic_info(scenic_name,scenic_city,scenic_area,parent_name,scenic_latitude," +
							"scenic_longitude,scenic_content,scenic_create,scenic_update,scenic_level,scenic_areais)values";
					sql += "('"+name+"','"+cityid+"','"+citycode+"','"+scenic_parent+"',"+scenic_latitude+",";
					sql += scenic_longitude+",'"+scenic_content+"','"+scenic_create+"','"+scenic_update+"','"+scenic_level+"','"+scenic_areais+"')";
					PreparedStatement pre4 = this.con.prepareStatement(sql);
					pre4.executeUpdate(sql);
					pre4.close();
				}
				result2.close();
				pre2.close();
			}
			result.close();
			pre.close();
			Long l2 = System.currentTimeMillis();
			System.out.println((l2-l1)+"ms");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void wx_ld_data(){
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement("select * from url_resource_types");
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				String typeid = result.getString("id");
				PreparedStatement pre1 = this.con.prepareStatement("select * from url_resources_1 where type='"+typeid+"' limit 0,6");
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
				String dir = "D:\\Tomcat6.0\\webapps\\xnryDocument\\"+wxld+"\\"+src;
				File file = new File(dir);
				file.mkdirs();
				downloadFromUrl(url, dir);
			}
		}
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
	
	/**
	 * 
	 * @title 西南卫星雷达关联部门
	 * @description
	 * @author Xuxiaobing
	 * @date 2015-1-14 下午04:40:43
	 */
	public void wxld(){
		PreparedStatement pre;
		try {
		pre = this.con.prepareStatement("select * from url_staterader_relation");
		ResultSet result = pre.executeQuery();
		int i = 0;
		while(result.next()) {
			i++;
			String name = result.getString("relation_name");
			String id = result.getString("relation_id");
			PreparedStatement pre1;
			pre1 = this.con.prepareStatement("select * from wm_department_info where department_name='"+name+"'");
			ResultSet result1 = pre1.executeQuery();
			while(result1.next()) {
				String code = result1.getString("department_code");
				String sql = "update url_staterader_relation set relation_citycode='"+code+"' where relation_id='"+id+"'";
				PreparedStatement pre4 = this.con.prepareStatement(sql);
				pre4.executeUpdate(sql);
				pre4.close();
				System.out.println(name+"<<<"+code+"<<<"+i);
			}
		}
		System.out.println("over");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @title 自动站分级
	 * @description
	 * @author Xuxiaobing
	 * @date 2015-1-20 下午04:28:05
	 */
	public void excute_City_Level(){
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement("select * from auto_stations");
			ResultSet result = pre.executeQuery();
			while(result.next()) {
				String id = result.getString("id");
				String citycode = result.getString("sid");
				String auto_name = result.getString("sname");
				if(!AssistUtil.isNumeric(citycode)){
					System.out.println(auto_name);
					String sql = "update auto_stations set lvl='3' where id='"+id+"'";
					PreparedStatement pre1 = this.con.prepareStatement(sql);
					pre1.executeUpdate();
					pre1.close();
				}
				String sql = "select * from sys_city where city_code='"+citycode+"'";
				PreparedStatement pre1 = this.con.prepareStatement(sql);
				ResultSet result1 = pre1.executeQuery();
				while(result1.next()){
					String name = result1.getString("city_name");
					String level = result1.getString("city_center")!=null?result1.getString("city_center"):"";
					String parent_no = result1.getString("city_parent")!=null?result1.getString("city_parent"):"";
					if(level.equals("2"))
					{
						if(parent_no.length()==5)
						{
							level = "2";
						}else if(parent_no.length()==4)
						{
							level = "1";
						}
					}
					sql = "update auto_stations set lvl='"+level+"' where id='"+id+"'";
					PreparedStatement pre2 = this.con.prepareStatement(sql);
					pre2.executeUpdate();
					pre2.close();
					System.out.println(name+"<<<"+level);
				}
				result1.close();
				pre1.close();
			}
			System.out.println("over");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static String readTxtFile(String filePath){
    	String s = "";
        try {
            String encoding="UTF-8";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                while((lineTxt = bufferedReader.readLine()) != null){
//                	String ks = new Insert().addLatide(lineTxt);
//                    s += lineTxt+"\n";
//                	ks = ks.replace("N", "");
//                	Insert ins = new Insert();
                	try {  
                		lineTxt = addLatide(String.valueOf(lineTxt));
                    } catch (Exception e) {  
                        System.err.println("非数据类型不能转换。");  
                        //e.printStackTrace();  
                    } 
                	System.out.println(lineTxt);
                }
                
                read.close();
	        }else{
	            System.out.println("找不到指定的文件");
	        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return s;
    }
    
    public void exit_station(String station){
    	 try {
    		 String sql = "select * from pub_stations where station_no='"+station+"'";
    		 PreparedStatement pre = this.con.prepareStatement(sql);
    		 ResultSet result = pre.executeQuery();
    		 boolean isexit = false;
    		 while(result.next()) {
    			 isexit = true;
    		 }
    		 //select * from pub_stations where station_no='﻿S7684'
			 //select * from pub_stations where station_no='S7685'
    		 pre.close();
    		 result.close();
    		 if(isexit==false){
    			 System.out.println("////"+station+"//");
    		 }
    	 } catch (Exception e) {
             System.out.println("读取文件内容出错");
             e.printStackTrace();
         }
    }
    
    public static void main(String args[]) throws SQLException{
//    	Insert ll = new Insert();
//    	readTxtFile("E:\\Game\\1.txt");
//    	List<String> latides = addLatide("1060030", "311701");
//    	System.out.println(addLatide("311701"));
//    	System.out.println(latides.get(0));
//    	System.out.println(latides.get(1));
    	//118°30 35°20
//    	ll.list();
//    	Insert nn = new Insert();
//    	nn.updateInviterRecord();
//    	nn.updateNotInviterIdUser();
    	characterSqlTable();
    }
    
    /**
     * 
     * @title 经纬度转换
     * @description
     * @param lo
     * @return
     * @author Xuxiaobing
     * @date 2015-2-26 下午05:58:08
     */
    public static String addLatide(String lo) {
    	double dou = Math.floor(Double.parseDouble(lo));
    	
		int log = (int)dou;
		int d = log/10000;
		int temp = log%10000;
		int f = temp/100;
		int m = temp%100;
		String logDfm = d +"°"+f +"'"+m;
		f = temp/100;
		m = temp%100;
		String fm = d +"°"+f +"'"+m;
		String latD = dfmTojwd(logDfm);
//		String latDfm = dfmTojwd(fm);
//		System.out.println("longitude= "+latD);
//		System.out.println("latitude= "+latDfm);
//		List<String> list = new ArrayList<String>();
//		list.add(latD);
//		list.add(latDfm);
		return latD;
    }
    public static List<String> addLatide(String lo, String la) {
		int log = Integer.valueOf(lo);
		int lat = Integer.valueOf(la);
		int d = log/10000;
		int temp = log%10000;
		int f = temp/100;
		int m = temp%100;
		String logDfm = d +"°"+f +"'"+m;
		d = lat/10000;
		temp = lat%10000;
		f = temp/100;
		m = temp%100;
		String fm = d +"°"+f +"'"+m;
		String latD = dfmTojwd(logDfm);
		String latDfm = dfmTojwd(fm);
//		System.out.println("longitude= "+latD);
//		System.out.println("latitude= "+latDfm);
		List<String> list = new ArrayList<String>();
		list.add(latD);
		list.add(latDfm);
		return list;
    }
    /**
	 * 度分秒转换换
	 * 经纬度格式分为三种：度、度-分、度-份-秒
	 *	1.） ddd.ddddd °【度 . 度 格式】的十进制小数部分（5位）
	 *	2.） ddd°mm.mmm’ 【度 . 分 . 分 格式】的十进制小数部分（3位）
	 *	3.)   ddd°mm’ss’’ 【度 . 分 . 秒 格式】
	 *	Google 使用的是第三种格式  度。分’秒’’
	 * @param objValue 57°55'56.6
	 * @return
	 */
   protected static String dfmTojwd(String objValue) {
		int indexOfD = objValue.indexOf('°');
		if(-1==indexOfD) throw new RuntimeException("无效的数据格式...");
		String duValue = objValue.substring(0, indexOfD);
		int indexOfF = 0;
		if(objValue.indexOf("′")>0){
			indexOfF = objValue.indexOf("′");
		}else { 
			indexOfF = objValue.indexOf('\'');
		}
		if(-1==indexOfF) throw new RuntimeException("无效的数据格式...");
		String fenValue = objValue.substring(indexOfD+1, indexOfF);;
		if(indexOfF==objValue.length()-1){//没有秒
			return String.valueOf(Integer.parseInt(duValue)+Double.parseDouble(fenValue)/60);
		}else{
			String miaoValue = objValue.substring(indexOfF+1);
			return String.valueOf(Integer.parseInt(duValue)+Double.parseDouble(fenValue)/60+Double.parseDouble(miaoValue)/3600);
		}
	}


}
