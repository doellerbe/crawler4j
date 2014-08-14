package edu.uci.ics.crawler4j.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CrawlerMySqlConn {
	final String user;
	final String pass;
	final String dbname;
	static String connectionUrl;
	
	static Connection conn;
	
	public CrawlerMySqlConn(String connectionUrl, String user, String pass, String dbname){
		CrawlerMySqlConn.connectionUrl = connectionUrl;
		this.user = user;
		this.pass = pass;
		this.dbname = dbname;
	}
	
	public static Connection getConnection() throws SQLException{
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(CrawlerMySqlConn.connectionUrl);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Got connection to mysql : " + conn);
		
		return conn;
	}
	
	public void insertData() {
		
	}
	
	public void deleteData() {
		
	}
	
	public void updateData() {
		
	}
	
	public void getData() {
		
	}
	
	public static void main(String[] args) throws SQLException{
		String connectionUrl = "jdbc:mysql://127.0.0.1:3306/";
		String username = "root";
		String password = "";
		String database = "pagebot";
		
		connectionUrl = connectionUrl + database + "?";
		
		if(!username.isEmpty()){
			connectionUrl = connectionUrl + "user=" + username;
		}
		
		if(!password.isEmpty()){
			connectionUrl = connectionUrl + "&password=" + password;
		}
		
		System.out.println("ConnectionUrl : " + connectionUrl);
		
		CrawlerMySqlConn newConn = new CrawlerMySqlConn(connectionUrl, username, password, database);
		newConn.getConnection();
	}
}
