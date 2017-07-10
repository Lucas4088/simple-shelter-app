package my;

import java.sql.*;

public class DatabaseManaging {
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/shelter?autoReconnect=true&useSSL=false";
	
	private static final String USER = "root";
	private static final String PASS = "admin";
	
	private Connection conn;
	private Statement stmt;
	
	DatabaseManaging(){
		conn = null;
		stmt = null;
	}
	
	public void insertInto(String databaseName, String[] values){
		String sql = "INSERT INTO "+databaseName+" VALUES (";
		sql+=values[0]+", "+values[1]+", "+values[2];
		sql+=")";
		try {
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			//Open Connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//Execute query
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			new ErrorDialog(e.getMessage());
		}catch(SQLException sqle){
			new ErrorDialog(sqle.getMessage());
		}finally {
			try{
				if(stmt != null)
					conn.close();
			}catch (SQLException sqlee) {
				
			}
			try{
				if(conn != null)
					conn.close();
			}catch(SQLException sqleee){
				new ErrorDialog(sqleee.getMessage());
			}
		}
	}
	
	public void remove(String databaseName, Integer ID){
		String sql = "DELETE FROM "+databaseName+" WHERE ID = "+ID;
		
		try{
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			new ErrorDialog(e.getMessage());
		}catch(SQLException sqle){
			new ErrorDialog(sqle.getMessage());
		}finally {
			try{
				if(stmt != null)
					conn.close();
			}catch (SQLException sqlee) {
				
			}
			try{
				if(conn != null)
					conn.close();
			}catch(SQLException sqleee){
				new ErrorDialog(sqleee.getMessage());
			}
		}
	}
}
