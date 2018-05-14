package sql.不同数据库连接;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 
 * @title SqlServer2000 JDBC连接
 * @description SqlServer2000 JDBC连接
 * @author Xuxiaobing
 * @date 2015-1-6 下午02:07:06 
 * @version v1.0
 */
public class SqlServerJDBC {
	
	private static Logger logger = Logger.getLogger(SqlServerJDBC.class);
	
	public static void main(String[] args) { 
//		SqlServerJDBC jdbc = new SqlServerJDBC();
		Connection con = SqlServerJDBC.connectSqlServer2000();
		try{
			String sql = "select * from t_ItemDetailV ";
//			PreparedStatement pre = con.prepareStatement(sql);
//			ResultSet rs = pre.executeQuery();
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery("select * from t_ItemDetailV;");
			if(!rs.next()){
				System.out.println("空");
			}
			while(rs.next()){
				System.out.println("FDetailID="+rs.getString("FDetailID"));
			}
			sta.close();
			rs.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	
	public static Connection connectSqlServer2000(){
		String driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";  
		String dbURL = "jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=dbo";
		String userName = "sa"; 
		String userPwd = "123456";
		Connection dbConn = null;  
		try { 
			Class.forName(driverName);  
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			logger.info("sqlserver2000 jdbc驱动连接成功");
		}catch(Exception e) {  
			logger.info("没找到sqlserver2000 jdbc驱动");
			e.printStackTrace();
		}
		return dbConn;
	}
}
