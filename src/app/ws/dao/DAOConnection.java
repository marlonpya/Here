package app.ws.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOConnection {

	static Connection con = null;

	public void connect() {
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.jdbc.Driver");
			String database = "AlphaBussines2016";
			String user = "root";
			String password = "mysql";			
			//String testConnStr = "jdbc:sqlserver://localhost\\SQLEXPRESS2012;databaseName=AlphaBussines2016;user=sa;password=sql";
			//con = DriverManager.getConnection("jdbc:sqlserver://localhost; databaseName=" + database + "; user=" + user + "; password=" + password);
			con = DriverManager.getConnection("jdbc:mysql://localhost/"+database, user, password);
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver: " + ex.getMessage());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ResultSet getQuery(String query) {
		try {
			PreparedStatement pst = con.prepareStatement(query);
			return pst.executeQuery();
		} catch (SQLException ex) {
			System.out.println("GetQuery: " + "[" + query + "] - " + ex.getMessage());
		}
		return null;
	}
	
	public void setQuery(String query) {
		try {
			connect();
			PreparedStatement cst = con.prepareStatement(query);
			cst.executeUpdate();
			close();
		} catch (SQLException ex) {
			System.out.println("SetQuery: " + "[" + query + "] - " + ex.getMessage());
		}
	}

	public ResultSet getProc(String query) {
		try {
			CallableStatement cst = con.prepareCall(query);
			return cst.executeQuery();
		} catch (SQLException ex) {
			System.out.println("GetProc: " + "[" + query + "] - " + ex.getMessage());
		}
		return null;
	}

	public void setProc(String query) {
		try {
			connect();
			CallableStatement cst = con.prepareCall(query);
			cst.executeUpdate();
			close();
		} catch (SQLException ex) {
			System.out.println("SetProc: " + "[" + query + "] - " + ex.getMessage());
		}
	}
	
	public Object getFunction(int type, String query) {
		try {
			CallableStatement cst = con.prepareCall("{" + query + "}");
			cst.registerOutParameter(1, type);
			cst.execute();
			return cst.getInt(1);
		} catch (SQLException ex) {
			System.out.println("GetFunction: " + "[" + query + "] - " + ex.getMessage() + "\n");
			return null;
		}
	}

	public void close() {
		try {
			con.close();
		} catch (SQLException ex) {
			System.out.println("Close: " + ex.getMessage());
		}
	}
	
}
