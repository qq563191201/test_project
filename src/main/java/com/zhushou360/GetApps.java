package com.zhushou360;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONException;

//import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.util.Util;

public class GetApps {
	
//	private static Logger logger = Logger.getLogger(GetApps.class);
	
	private static String infoId;
	
	private String path = "D:/glm/work/apache-tomcat-8.0.21/webapps/marketDoc/";
	
//	private String path = "/usr/local/tomcat/webapps/marketDoc/";
	
//	public String url = "jdbc:mysql://183.57.151.49:3306/www-market?characterEncoding\\=UTF-8";
//	public String user = "admin_data";
//	public String password = "bsrt!@#data";
	
	public String url = "jdbc:mysql://localhost:3306/www-market?characterEncoding\\=UTF-8";
	public String user = "root";
	public String password = "";
	
	Connection con;
	
	public GetApps(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updatePicture(){
		String sql = "select id,md5 from tb_apps_detail where id>11354";
		PreparedStatement pre = null;
		Long id = null;
		ResultSet result = null;
		try{
			pre = this.con.prepareStatement(sql);
			result = pre.executeQuery();
			while(result.next()){
				Long s1 = System.currentTimeMillis();
				id = result.getLong("id");
				String md5 = result.getString("md5");
				String sql1 = "update tb_apps_detail_pictures set app_detail_id="+id
						+" where md5='"+md5+"'";
				PreparedStatement pre1 = null;
				try{
					pre1 = this.con.prepareStatement(sql1);
					pre1.execute();
					
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(pre1 != null){
						try {
							pre1.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				Long s2 = System.currentTimeMillis();
//				logger.info((s2-s1)+"ms,id="+id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				pre.close();
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		new GetApps().updatefilter();
    }
	
	public void updatefilter(){
		String sql = "select * from tb_filter_app where type=4";
		PreparedStatement pre = null;
		ResultSet result = null;
		try{
			pre = this.con.prepareStatement(sql);
			result = pre.executeQuery();
			while(result.next()){
				String fId = result.getString("id");
				String typesId = result.getString("types_id");
				sql = "select * from tb_apps_detail where typesId="+typesId;
				PreparedStatement pre1 = null;
				ResultSet result1 = null;
				try{
					pre1 = this.con.prepareStatement(sql);
					result1 = pre1.executeQuery();
					while(result1.next()){
						String id = result1.getString("id");
						String mtypeCode = result1.getString("mtypeCode");
						String typeCode = result1.getString("typeCode");
						sql = "update tb_filter_app set mtypeCode="+mtypeCode+",typeCode="+typeCode+
								",app_detail_id="+id+" where id="+fId;
						PreparedStatement pre2 = null;
						try{
							pre2 = this.con.prepareStatement(sql);
							pre2.executeUpdate();
						}catch(Exception e){
							e.printStackTrace();
						}finally{
							if(pre2!=null){
								try {
									pre2.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(pre1!=null){
						try {
							pre1.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(result1!=null){
						try {
							result1.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pre!=null){
				try {
					pre.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(result!=null){
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * @Title: 获取app信息
	 * @Package com.zhushou360
	 * @Description:
	 * @author Xuxiaobing
	 * @date 2015年6月13日 上午11:56:55
	 * @version V1.0
	 */
	@SuppressWarnings("deprecation")
	public void getAppsInformation(){
		String mySql = "select * from tb_category where mtypeCode=2";
		try{
			PreparedStatement preCategory = this.con.prepareStatement(mySql);
			ResultSet result1 = preCategory.executeQuery();
			while(result1.next()){
				String typeName = URLEncoder.encode(result1.getString("typeName"),"utf-8");
				String mtypeCode = result1.getString("mtypeCode");
				String typeCode = result1.getString("id");
				for(int y=1;y<5;y++){
					String strUrl = "http://123.125.82.206/app/list/cid/1/format/webview?tag="+typeName+"&order=weekpure&tag2="+typeName+"&needtag=1&os=16&webp=1&page="+y+"&fm=cati_cid1_tag"+typeName+"&m=100a181920b5f9a08e23f919b8edc3c6&m2=e545e741cda1395e93b1d509ca7bcc40&v=3.2.23&re=1&ch=475235&model=MI+2A&sn=4.47798919578295&cu=qct+msm8960+cdp&ppi=720x1280&startCount=23&snt=-1";
//					logger.info("page="+y+",typeCode="+typeCode);
					JSONObject json = readJsonFromUrl(strUrl);
					if(json!=null){
						String data = json.getString("data");
						JSONArray jsonarray = new JSONArray(data);
						for (int i = 0; i < jsonarray.length()-1; i++) {
							JSONObject jsonobj = jsonarray.getJSONObject(i);
							String id = jsonobj.getString("id");
							infoId = id;
							String strSql = "select * from tb_apps_detail where typesId=?";
							boolean isNull = false;
							PreparedStatement preSearch = null;
							try{
								preSearch = this.con.prepareStatement(strSql);
								preSearch.setString(1, id);
								ResultSet result = preSearch.executeQuery();
								if(result.next()){
									isNull = true;
								}
							}catch(Exception e){
								e.printStackTrace();
							}finally{
								preSearch.close();
							}
							if(!isNull){
						    	String name = jsonobj.getString("name");
						    	String apkId = jsonobj.getString("apkid");
						    	String down_url = jsonobj.getString("down_url");
						    	String logo_url = jsonobj.getString("logo_url")!=null?jsonobj.getString("logo_url"):"";
						    	long thisTime = System.currentTimeMillis();
						    	String iconPath = "app/"+Util.formats1.format(new Date())+"/"+thisTime+"/";
						    	String apkPath = path+iconPath;
						    	String sql = "";
						    	String rating = jsonobj.getString("rating");//评分
						    	String size = jsonobj.getString("size");
						    	String version_code = jsonobj.getString("version_code");//314
						    	String version_name = jsonobj.getString("version_name");//3.1.4
						    	String apk_md5 = jsonobj.getString("apk_md5");
						    	String signature_md5 = jsonobj.getString("signature_md5");
						    	//应用详情页http://123.125.82.206/mintf/getAppInfoByIds?isdes=1&webp=1&sort=1&pname=com.qihoo.antivirus&market_id=360market&si=1670805&ppi=720_1280&pos=0&ad_code=0&fm=cati_cid1_tag%E7%B3%BB%E7%BB%9F%E5%AE%89%E5%85%A8_3&m=100a181920b5f9a08e23f919b8edc3c6&m2=e545e741cda1395e93b1d509ca7bcc40&v=3.2.23&re=1&ch=475235&os=16&model=MI+2A&sn=4.47798919578295&cu=qct+msm8960+cdp&startCount=24&snt=-1
						    	strUrl = "http://123.125.82.206/mintf/getAppInfoByIds?isdes=1&webp=1&sort=1&pname="+apkId+"&market_id=360market&si="+id+"&ppi=720_1280&pos=0&ad_code=0&fm=cati_cid1_tag%E7%B3%BB%E7%BB%9F%E5%AE%89%E5%85%A8_3&m=100a181920b5f9a08e23f919b8edc3c6&m2=e545e741cda1395e93b1d509ca7bcc40&v=3.2.23&re=1&ch=475235&os=16&model=MI+2A&sn=4.47798919578295&cu=qct+msm8960+cdp&startCount=24&snt=-1";
						    	JSONObject json1 = readJsonFromUrl(strUrl);
						    	data = json1.getString("data");
						    	JSONArray jsonarray1 = new JSONArray(data);
						    	String list_tag = "";
						    	if(jsonarray1.length()>0){
						    		JSONObject jsonobj1 = jsonarray1.getJSONObject(0);
						    		String brief = jsonobj1.getString("brief");
						    		String corp = jsonobj1.getString("corp");
						    		String os_version = jsonobj1.getString("os_version");//sdkVersion
						    		String is_safe = jsonobj1.getString("is_safe");
						    		String uses_permission = jsonobj1.getString("uses_permission");//应用权限
						    		String update_time = jsonobj1.getString("update_time");//更新时间
						    		Timestamp updateTime = Timestamp.valueOf(update_time);
						    		String update_info = jsonobj1.getString("update_info");//版本更新信息
						    		String logo_url_160 = jsonobj1.getString("logo_url_160");
						    		String iconLargeName = "";
						    		String picPath = "mu/"+Util.year.format(new Date())+"/"+Util.moth.format(new Date())
							    			+"/"+Util.day.format(new Date())+"/";
						    		String thumbPath = path+picPath;
							    	String thrumb_small = "";
							    	String trumb = "";
							    	if(!logo_url_160.equals("") || !logo_url.equals("")){
							    		try{
								    		trumb = jsonobj1.getString("trumb");
								    	}catch(JSONException e){
								    		e.printStackTrace();
								    	}
								    	if(!trumb.equals("")){
									    	String[] thumbArray = trumb.split("\\|");
									    	for(String str: thumbArray){
									    		String thumName = downImage(str, thumbPath+"l/");
									    		if(!thumName.equals("")){
											    	webpToPng(thumbPath+"l/"+thumName, thumbPath+"l/"+thumName.substring(0,thumName.lastIndexOf(".")-1)+".png");
											    	File file2 = new File(thumbPath+"l/"+thumName);
													if(file2.exists()){
														file2.delete();
													}
									    		}
									    	}
								    	}
								    	try{
								    		thrumb_small = jsonobj1.getString("thrumb_small");
								    	}catch(JSONException e){
								    		e.printStackTrace();
								    	}
								    	if(!thrumb_small.equals("")){
								    	String[] thumbSmallArray = thrumb_small.split("\\|");
									    	for(String str: thumbSmallArray){
									    		String thumName = downImage(str, thumbPath+"s/");
									    		if(!thumName.equals("")){
											    	webpToPng(thumbPath+"s/"+thumName, thumbPath+"s/"+thumName.substring(0,thumName.lastIndexOf(".")-1)+".png");
											    	File file2 = new File(thumbPath+"s/"+thumName);
													if(file2.exists()){
														file2.delete();
													}
													sql = "insert into tb_apps_detail_pictures(md5,pic_name,pic_small,state)values(?,?,?,?)";
													PreparedStatement pre1 = null;
													try{
														pre1 = this.con.prepareStatement(sql);
														pre1.setString(1, apk_md5);
														if(!trumb.equals("")){
															pre1.setString(2, "l/"+thumName.substring(0,thumName.lastIndexOf(".")-1)+".png");
														}
														pre1.setString(3, "s/"+thumName.substring(0,thumName.lastIndexOf(".")-1)+".png");
														pre1.setInt(4, 1);
														pre1.executeUpdate();
													}catch(Exception e){
														e.printStackTrace();
													}finally{
														pre1.close();
													}
									    		}
									    	}
								    	}
								    	if(thrumb_small.equals("")&&trumb.equals("")){
								    		continue;
								    	}
						    		
						    			if(!logo_url_160.equals("")){
								    		iconLargeName = downImage(logo_url_160, apkPath+"/icon_n/", "icon_l");
									    	webpToPng(apkPath+"/icon_n/"+iconLargeName, apkPath+"/icon_n/"+"icon_l.png");
									    	File file1 = new File(apkPath+"/icon_n/"+iconLargeName);
											if(file1.exists()){
												file1.delete();
											}
						    			}
						    			
								    	list_tag = jsonobj1.getString("list_tag");
								    	String[] tagArray = list_tag.split(" ");
								    	String searchTag = "";
								    	for(String str: tagArray){
								    		searchTag += str+",";
								    	}
								    	
								    	PreparedStatement pre = null;
								    	try{
								    		sql = "insert into tb_apps_detail(name,typesId,mtypeCode,typeCode,appUpType,size,apkPath,"
									    			+ "iconPath,picPath,status,versionCode,safeType,uses_permission,update_info,sdkVersion,"
									    			+ "searchTag,discription,version,smallIcon,mediumIcon,apkId,starNum,createTime,updateTime,"
									    			+ "largeIcon,md5,md5Cert,webFrom)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
									    	pre = this.con.prepareStatement(sql);
									    	searchTag = searchTag.substring(0, searchTag.lastIndexOf(","));
									    	pre.setString(1, name);
									    	pre.setString(2, id);
									    	pre.setString(3, mtypeCode);
									    	pre.setString(4, typeCode);
									    	pre.setInt(5, 0);
									    	pre.setString(6, size);
									    	String apkName = downImage(down_url, apkPath);
									    	pre.setString(7, iconPath+apkName);
									    	pre.setString(8, iconPath+"icon_n/");
									    	pre.setString(9, picPath);
									    	pre.setString(10, "5");
									    	pre.setString(11, version_code);
									    	pre.setString(12, is_safe);
									    	pre.setString(13, uses_permission);
									    	pre.setString(14, update_info);
									    	pre.setString(15, os_version);
									    	pre.setString(16, searchTag);
									    	pre.setString(17, brief);
									    	pre.setString(18, version_name);
									    	pre.setString(19, "icon_m.png");
									    	pre.setString(20, "icon_m.png");
									    	pre.setString(21, apkId);
									    	pre.setString(22, rating);
									    	pre.setTimestamp(23, updateTime);
									    	pre.setTimestamp(24, updateTime);
									    	if(iconLargeName.equals("")){
									    		pre.setString(25, "icon_m.png");
									    	}else{
									    		pre.setString(25, "icon_l.png");
									    	}
									    	pre.setString(26, apk_md5);
									    	pre.setString(27, signature_md5);
									    	pre.setString(28, corp);
									    	
									    	if(!logo_url.equals("")){
										    	//图标下载
										    	String iconMiddleName = downImage(logo_url, apkPath+"/icon_n/", "icon_m");
										    	webpToPng(apkPath+"/icon_n/"+iconMiddleName, apkPath+"/icon_n/"+"icon_m.png");
										    	File file = new File(apkPath+"/icon_n/"+iconMiddleName);
												if(file.exists()){
													file.delete();
												}
									    	}
									    	
									    	pre.executeUpdate(); // insert remaining records
								    	}catch(SQLException e){
								    		e.printStackTrace();
								    	}finally{
								    		pre.close();
								    	}
										
						    		}
						    	}
							}
						}
					}
//					logger.info("page="+y+" is download");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @Title: 获取分类
	 * @Package com.zhushou360
	 * @Description:
	 * @author Xuxiaobing
	 * @date 2015年6月12日 下午5:41:24
	 * @version V1.0
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public void getCollection(){
		String strUrl = "http://123.125.82.206/app/getCollectionTags?os=16&webp=1&fm=cati&m=100a181920b5f9a08e23f919b8edc3c6&m2=e545e741cda1395e93b1d509ca7bcc40&v=3.2.23&re=1&ch=475235&os=16&model=MI+2A&sn=4.47798919578295&cu=qct+msm8960+cdp&ppi=720x1280&startCount=23";
		try{
			JSONObject json = readJsonFromUrl(strUrl);
			if(json!=null){
				String data = json.getString("data");
				JSONArray jsonarray = new JSONArray("["+data+"]");
				for (int i = 0; i < jsonarray.length(); i++) {
					JSONObject jsonobj = jsonarray.getJSONObject(i);
			    	String softTags = jsonobj.getString("softTags");
			    	JSONArray arrayData = new JSONArray(softTags);
			    	String sql = "insert into tb_category(typeId,typeName,mtypeCode,picturePath,createTime,updateTime)values";
			    	PreparedStatement pre = this.con.prepareStatement(sql+"(?,?,?,?,?,?)");
			    	for(int j=0;j < arrayData.length(); j++){
			    		JSONObject jsonData = arrayData.getJSONObject(j);
			    		String logo = jsonData.getString("logo");
			    		Long sysTime = System.currentTimeMillis();
			    		String year = Util.year.format(new Date());
			    		String month = Util.moth.format(new Date());
			    		if(month.subSequence(0, 1).equals("0")){
			    			month = month.substring(1,2);
			    		}
			    		String day = Util.day.format(new Date());
			    		if(day.subSequence(0, 1).equals("0")){
			    			day = day.substring(1,2);
			    		}
			    		String imagePath = "mu/category/"+year+"/"+month+"/"+day+"/"+sysTime+"/";
			    		String imageName = imagePath+downImage(logo, path+imagePath);
			    		String title = jsonData.getString("title");
			    		String cid = jsonData.getString("cid");
			    		String qcmsint1 = jsonData.getString("qcmsint1");
			    		pre.setString(1, qcmsint1);
			    		pre.setString(2, title);
			    		pre.setString(3, cid);
			    		pre.setString(4, imageName);
			    		pre.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			    		pre.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			    		pre.addBatch();
			    	}
			    	String gameTags = jsonobj.getString("gameTags");
			    	JSONArray arrayGames = new JSONArray(gameTags);
			    	for(int j=0;j < arrayGames.length(); j++){
			    		JSONObject jsonData = arrayGames.getJSONObject(j);
			    		String logo = jsonData.getString("logo");
			    		Long sysTime = System.currentTimeMillis();
			    		String year = Util.year.format(new Date());
			    		String month = Util.moth.format(new Date());
			    		if(month.subSequence(0, 1).equals("0")){
			    			month = month.substring(1,2);
			    		}
			    		String day = Util.day.format(new Date());
			    		if(day.subSequence(0, 1).equals("0")){
			    			day = day.substring(1,2);
			    		}
			    		String imagePath = "mu/category/"+year+"/"+month+"/"+day+"/"+sysTime+"/";
			    		String imageName = imagePath+downImage(logo, path+imagePath);
			    		String title = jsonData.getString("title");
			    		String cid = jsonData.getString("cid");
			    		pre.setString(1, "");
			    		pre.setString(2, title);
			    		pre.setString(3, cid);
			    		pre.setString(4, imageName);
			    		pre.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			    		pre.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			    		pre.addBatch();
			    	}
			    	pre.executeBatch(); // insert remaining records
			    	pre.clearBatch();
			    	pre.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: 下载
	 * @Package com.zhushou360
	 * @Description:
	 * @param url 下载地址
	 * @param filePath 路径
	 * @return
	 * @author Xuxiaobing
	 * @date 2015年6月15日 上午10:11:20
	 * @version V1.0
	 */
	public static synchronized String downImage(String url, String filePath){
		String name = "";
		try{
			// 创建流  
	        BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());  
	        // 生成文件名
	        int index = url.lastIndexOf("/");  
	        String sName = url.substring(index+1, url.length());
	        // 存放地址  
	        File img = new File(filePath);
	        img.mkdirs();
	        // 生成文件
	        OutputStream  out = new FileOutputStream(filePath+sName);  
	        byte[] buf = new byte[2048];  
	        int length = in.read(buf);  
	        while (length != -1) {  
	            out.write(buf, 0, length);  
	            length = in.read(buf);  
	        }  
	        in.close();  
	        out.close(); 
	        name = sName;
		}catch(Exception e){
//			logger.info("infoId="+infoId+"url="+url+",filePath="+filePath+",name="+name);
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 
	 * @Title: 下载
	 * @Package com.zhushou360
	 * @Description:
	 * @param url 下载地址
	 * @param filePath 路径
	 * @return
	 * @author Xuxiaobing
	 * @date 2015年6月15日 上午10:11:20
	 * @version V1.0
	 */
	public static synchronized String downImage(String url, String filePath, String name){
		String str = "";
		try{
			// 创建流  
	        BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());  
	        // 生成文件名
	        int index = url.lastIndexOf(".");  
	        String sName = url.substring(index, url.length());
	        // 存放地址  
	        File img = new File(filePath);
	        img.mkdirs();
	        // 生成文件
	        OutputStream  out = new FileOutputStream(filePath+name+sName);  
	        byte[] buf = new byte[2048];  
	        int length = in.read(buf);  
	        while (length != -1) {  
	            out.write(buf, 0, length);  
	            length = in.read(buf);  
	        }  
	        in.close();  
	        out.close(); 
	        str = name+sName;
		}catch(Exception e){
//			logger.info("infoId="+infoId+",url="+url+",filePath="+filePath+",name="+name);
			e.printStackTrace();
		}
		return str;
	}
	
	private static String readAll(Reader rd) throws IOException {  
		StringBuilder sb = new StringBuilder();  
	    int cp;  
	    while ((cp = rd.read()) != -1) {  
	    	sb.append((char) cp);  
	    }  
	    return sb.toString();  
	}  
	  
	public static JSONObject readJsonFromUrl(String url) throws IOException, ParseException {  
		InputStream is = new URL(url).openStream();  
	    try {  
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));  
	    	String jsonText = readAll(rd);  
	    	JSONObject json = new JSONObject(jsonText);  
	    	return json;  
	    } finally {  
	    	is.close();  
	    	// System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");  
	    }  
	} 
	
	public void getJson(){
		// 首先用一个字符串 来装载网页链接
        String strUrl = "http://m.app.haosou.com/category/cat_request?page=1&requestType=ajax&_t=1434072338862&cid=3&csid=11&order=download";
        try {
            // 创建一个url对象来指向 该网站链接 括号里()装载的是该网站链接的路径
            // 更多可以看看 http://wenku.baidu.com/view/8186caf4f61fb7360b4c6547.html
            URL url = new URL(strUrl);
            // InputStreamReader 是一个输入流读取器 用于将读取的字节转换成字符
            // 更多可以看看 http://blog.sina.com.cn/s/blog_44a05959010004il.html
            InputStreamReader isr = new InputStreamReader(url.openStream(), "utf-8"); // 统一使用utf-8 编码模式
            // 使用 BufferedReader 来读取 InputStreamReader 转换成的字符
            BufferedReader br = new BufferedReader(isr);
            String json = "";
            // 开始读取数据 如果读到的数据不为空 则往里面读
            while (br.readLine() != null) {
            	// 则打印出来 这里打印出来的结果 应该是整个网站的
            	json += br.readLine();
            }
            // 当读完数据后 记得关闭 BufferReader
            br.close();
            try{
            	JSONArray jsonarray = new JSONArray(json);
            	for (int i = 0; i < jsonarray.length(); i++) {   
    		    	JSONObject jsonobj = jsonarray.getJSONObject(i);
    		    	String apkid = jsonobj.getString("apkid");
    		    	String down_url = jsonobj.getString("down_url");
    		    	String id = jsonobj.getString("id");
    		    	String logo_url = jsonobj.getString("logo_url");
//    		    	String logo_url_160 = jsonobj.getString("logo_url_160");
    		    	String rating = jsonobj.getString("rating");//评分
    		    	String size = jsonobj.getString("size");//大小
    		    	String apk_md5 = jsonobj.getString("apk_md5");
    		    	String version_code = jsonobj.getString("version_code");
    		    	String version_name = jsonobj.getString("version_name");
    		    	String signature_md5 = jsonobj.getString("signature_md5");
    		    	String is_ad = jsonobj.getString("is_ad");
    		    	strUrl = "http://m.app.haosou.com/detail/index?pname=com.qihoo360.mobilesafe&id="+id;
    		    	Document docHtml =  Jsoup.connect(strUrl).get();
    		        String images = docHtml.getElementsByClass("software-form").html();
    		        Document docMoblie = Jsoup.parse(images);
    		        Elements elementsMobile = docMoblie.getElementsByAttribute("src");
    		        //取出截图
    		        for(Element e :elementsMobile)
    		        {
    		        	String screenUrl = e.attr("src");
    		        }
    		        //获取更新时间
    		        String regTime = "(\\d{4}\\-\\d{1,2}\\-\\d{1,2})"; 
    		        String update = regularGroup(regTime, images);
    		        //获取作者
    		        regTime = "作者：([^\"|[\u4e00-\u9fa5]]+)</li>";
    		        String author = regularGroup(regTime, images);
    		        //获取描述
    		        String desc = docHtml.getElementById("fullDesc").html();
    		        if(desc.contains("<p>")){
    		        	desc = desc.substring(0, desc.indexOf("<p>"));
    		        }
            	}
            }catch(Exception e){
            	e.printStackTrace();
            }
        } catch (IOException e) {
            // 如果出错 抛出异常
            e.printStackTrace();
        }
	}
	
	// 传入2个字符串参数 一个是pattern(我们使用的正则) 另一个matcher是html源代码
    public static String regularGroup(String pattern, String matcher) {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(matcher);
        if (m.find()) { // 如果读到
            return m.group(1);// 返回捕获的数据
        } else {
            return ""; // 否则返回一个空字符串
        }
    }
    
    /**
     * 
     * @Title: webp转png
     * @Package com.zhushou360
     * @Description:
     * @param filePath
     * @param fileName
     * @author Xuxiaobing
     * @date 2015年6月15日 上午9:49:09
     * @version V1.0
     */
    public void webpToPng(String filePath, String fileName){
    	String cwebp="";
    	String os = System.getProperty("os.name");
    	if(os.toLowerCase().startsWith("win")){
    		//判断当前jdk的版本位数
    		if(System.getProperty("sun.arch.data.model").equals("64")){
    			cwebp = System.getProperty("user.dir")+"/libwebp-0.4.2-windows-x64/bin/dwebp.exe";
    		}else{
    		    cwebp = System.getProperty("user.dir")+"/libwebp-0.4.2-windows-x86/bin/dwebp.exe";
    		}
    	}else{
    		//判断当前jdk的版本位数
    		if(System.getProperty("sun.arch.data.model").equals("64")){
    			cwebp = System.getProperty("user.dir")+"/libwebp-0.4.2-linux-x86-64/bin/dwebp";
    		}else{
    		    cwebp = System.getProperty("user.dir")+"/libwebp-0.4.2-linux-x86-32/bin/dwebp";
    		}
    	}
		
		String[] arg = new String[]{cwebp, filePath, "-o", fileName};
		try{
			Runtime.getRuntime().exec(arg);
			Thread.sleep(100);
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
