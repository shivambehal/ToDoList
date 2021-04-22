package com.toDoList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class itemDbUtil {
	
	private Connection getConnection() throws ClassNotFoundException, SQLException{
		
		String dbDriver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(dbDriver);
		String dbName = "JDBCConnection";
		String dbUserName = "Sys as SYSDBA";
		String dbPassword = "root";
		String dbUrl = "jdbc:oracle:thin:@Gursharan_Singh";
		Connection conn = DriverManager.getConnection(dbUrl, 
                dbUserName,dbPassword); 
		return conn;
		
	}
	
	private void closeConnection(Connection conn, Statement stmt, ResultSet rst){
		
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rst != null){
			try {
				rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public String checkCredentials(String email, String password){
		
		String areValid = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		
		try{
			
			conn = getConnection();
			
			if(!isAlreadyRegistered(email, conn)){
				areValid = "not registered";
			}
			else{
				String sqlStatement = "select * from users where email =  '" + email + "' and password =  '" + password + "'";
				
				stmt = conn.createStatement();
				rst = stmt.executeQuery(sqlStatement);
				if(rst.next()){
					areValid = "valid";
				}
				else{
					areValid = "inValid";
				}

			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeConnection(conn, stmt, rst);
		}
		
		return areValid;
		
	}
	
	public void addItem(item theItem, String email){
		
		Connection conn = null;
		String tableName = getTableName(email);
		try {
			conn = getConnection();
		
			String label1 = theItem.getLabel();
			String date1 = theItem.getDate();
			String time1 = theItem.getTime();
			
			String sqlStatement = "insert into " + tableName
					+ " (label1, date1, time1)"
					+ "values (?, ?, ?)";
			
			PreparedStatement stmt = conn.prepareStatement(sqlStatement);
			
			stmt.setString(1, label1);
			stmt.setString(2, date1);
			stmt.setString(3, time1);
			
			stmt.execute();
			
			closeConnection(conn, stmt, null);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
public void deleteItem(String label, String email){
		
		Connection conn = null;
		String tableName = getTableName(email);
		try {
			conn = getConnection();
			
			String sqlStatement = "delete from " + tableName
					+ " WHERE label1=?";
			
			PreparedStatement stmt = conn.prepareStatement(sqlStatement);
			
			stmt.setString(1, label);
			
			stmt.execute();
			
			closeConnection(conn, stmt, null);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public String forEmail(String email, String label) 
    {
    	Connection conn = null;
		String tableName = getTableName(email);
		try {
			conn = getConnection();
			
			String sqlStatement = "select * from " + tableName
					+ " WHERE label1=?";
			
			PreparedStatement stmt = conn.prepareStatement(sqlStatement);
			
			stmt.setString(1, label);
			ResultSet rs = stmt.executeQuery();
			rs.next();			
			String one = rs.getNString("label1");
			String two =rs.getNString("date1");
			String three = rs.getNString("time1");
			String c = String.format("%-" + 15 + "s", "Label") + String.format("%" + 15 + "s", "Date") + String.format("%"+ 30 + "s","Time");
			String mail = "ToDo List\n\n"+c+"\n----------------------------------------------------------------------\n"+one+ "\t\t"+two+"\t\t"+three+"\n\n\nProject Name:TodoList\nGroup Name:Team Collaboration";
			closeConnection(conn, stmt, null);
			return mail;
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
    	
    }
	
	public List<item> getItems(String email){
		
		List<item> items = new ArrayList<>();
		
		String tableName = getTableName(email);
		
		Connection conn = null;
		try {
			conn = getConnection();
			
			String sqlStatement = "select * from " + tableName;
			Statement stmt = conn.createStatement();
			
			ResultSet rst = stmt.executeQuery(sqlStatement);
			
			while(rst.next()){
				
				// retrieve data from result set row
				int id = rst.getInt("id");
				String label1 = rst.getString("label1");
				String date1 = rst.getString("date1");
				String time1 = rst.getString("time1");
				
				System.out.println(label1);
				System.out.println(date1);
				System.out.println(time1);
				
				item theItem = new item(id, label1, date1, time1);
				
				items.add(theItem);
			}
			
			closeConnection(conn, stmt, rst);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return items;
		
	}
	
	private String getTableName(String email){
		
		int pos1 = email.indexOf("@");
		
		int pos2 = email.indexOf(".",pos1);
		
		String tableName = email.substring(0, pos1) + "_" + email.substring(pos1+1, pos2);
		
		return tableName;
	}
	
	private void createTable(Connection conn, String email){
		
		Statement smt = null;
		
		String tableName = getTableName(email);
		
		try {
			smt = conn.createStatement();
			String sql = "CREATE TABLE " + tableName + " " +
	                   "(id number(20) GENERATED BY DEFAULT ON NULL AS IDENTITY, " +
	                   " label1 VARCHAR2(50), " + 
	                   " date1 VARCHAR2(12), " + 
	                   " time1 VARCHAR2(10), " + 
	                   " PRIMARY KEY ( id ))"; 

			smt.executeUpdate(sql);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
	}
	
	private boolean isAlreadyRegistered(String email, Connection conn){
		
		String sqlStatement = "select * from users where email ='" + email + "'";
		
		Statement stmt = null;
		
		ResultSet rst = null;
		
		Boolean isFound = false;
		
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sqlStatement);
			if(rst.next()){
				isFound = true;
			}
			else{
				isFound = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isFound;
		
	}

	public String addUser(String email, String password) {
		
		Connection conn = null;
		
		String userAdded = null;
		
		try {
			conn = getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isAlreadyRegistered(email, conn)){
			userAdded =  "already registered";
		}
		
		else{
			
			try {
				
				String sqlStatement = "insert into users "
						+ "(email, password)"
						+ "values (?, ?)";
				
				PreparedStatement stmt = conn.prepareStatement(sqlStatement);
				
				stmt.setString(1, email);
				stmt.setString(2, password);
				
				stmt.execute();
				
				createTable(conn, email);
			      
			  	userAdded = "user registered";
				
				closeConnection(conn, stmt, null);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return userAdded;
		
	}



}
