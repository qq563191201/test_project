package sql.sum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sql_sum {
	public String url = "jdbc:mysql://222.174.144.22:34006/lesogo_sdrztqt?characterEncoding\\=UTF-8";
	public String user = "lesogo_mobile";
	public String password = "100200";

	Connection con;
	public sql_sum(){
		try {
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
	 *@title
	 *@author Anleilei
	 *@date 下午03:42:51
	 *@version 1.0
	 *@Description  添加实况
	 *
	 *
	 *
	 */
	public void tsum(){
		int station[] = {160,162};
		String date = "2015-03-";
		for(int j=0;j<station.length;j++){
			for (int i = 1; i < 5; i++) {
				PreparedStatement pre;
				//54945、54940、54936
				try {
					String sql = "select sum(data_rain) sum from tabtime_data where " +
					" data_city_code ='"+station[j]+"' and (data_obstime between '"+date
					+i+" 00:00' and '"+date+i+" 23:00')";
					pre = this.con.prepareStatement(sql);
					ResultSet rs = pre.executeQuery();
					Float rowcount = 0f;
					if(rs.next()){
						rowcount = rs.getFloat("sum");
					}
					sql = "insert into rainfall(rain_fall,rain_station,rain_date)values("+rowcount+","+station[j]+",'"+date+i+"')";
					pre.execute(sql);
					System.out.println("station:"+station[j]+">>>time:"+date+i+"<<<"+rowcount);
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("出错了。。。。");
				}
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
	 * 更新经纬度
	 * @param args
	 * @throws SQLException
	 */
	public void update(){
		PreparedStatement pre;
		try {
			pre = this.con.prepareStatement("select * from pub_stations");
			
			ResultSet result = pre.executeQuery();
			//获取父节点
			while(result.next()) {
				String id = result.getString("station_id");
				String latitude = result.getString("station_latitude");
				String longitude = result.getString("station_longitude");
				if(!latitude.contains(".") && latitude.length() == 6){
					latitude = Float.valueOf(latitude)/10000+"";
				}
				if(!longitude.contains(".") && longitude.length() == 7){
					longitude = Float.valueOf(longitude)/10000+"";
				}
				String sql = "update pub_stations set station_latitude='"+latitude
				+"',station_longitude='"+longitude+"',province='四川省',area_state='1' where station_id="+id;
				PreparedStatement pre1 = this.con.prepareStatement(sql);
				pre1.execute(sql);
				pre1.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) throws SQLException {
		new sql_sum().tsum();
	}
}
