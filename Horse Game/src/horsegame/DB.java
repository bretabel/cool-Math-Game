package horsegame;

import java.sql.*;


public class DB {
	private String url;
	private String dbName;
	private String dbPassword;
	private String driver;
	private Connection conn;
	public DB() {
		url = "jdbc:mysql://localhost:8080/HorseGameDB";
		dbName = "admin";
		dbPassword = "HorseGame123";
		driver = "com.mysql.cj.jdbc.Driver";
		connectDB();
	}
	
	public void connectDB(){
		try{
			Class.forName(driver);
			//conn = DriverManager.getConnection(url,dbName, dbPassword);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HorseGameDB?user=admin&password=HorseGame123");
			//conn = DriverManager.getConnection(url);
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HorseGameDB", "admin", "HorseGame123");
			System.out.print("Please Work");
		}catch(Exception e){
			System.out.println(e);			
		}
		
	}
	public String checkLogin(String name, String password) throws SQLException{
		String res="";
		Statement stat = conn.createStatement();
		String sql="select * from login where usrname ='"+name+"' and password='"+password+"'";
		ResultSet rs = stat.executeQuery(sql);
		if(rs.next()) {
			res=rs.getString("usrname");
		}
		conn.close();
		return res;	
	}

	/*
	public static void main(String[] args) throws SQLException {
		System.out.println("Hello");
		DB newdb = new DB();
		System.out.println("Hello");
		System.out.println(newdb.checkLogin("will", "123456"));
		
	}*/
}
