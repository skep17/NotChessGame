package pack.SQLPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlQueries {
	private String selectAllUsers =  "select * from users";
	private String particulatSelectByID = "select * from products where productid = ? ";
	private Connection con;
	
	public SqlQueries() {
		this.con =SqlConnection.getDbContext();
	}
	public ResultSet getAllUsers() throws SQLException {
		PreparedStatement statement = con.prepareStatement(selectAllUsers);
		ResultSet results = statement.executeQuery();
		return results;
	}
	
	public ResultSet particulaSelection(String item) throws SQLException {
		PreparedStatement statement = con.prepareStatement(particulatSelectByID);
		statement.setString(1, item);
		return statement.executeQuery();
	}

	public void killConnection() throws SQLException {
		SqlConnection.closeConnection();
	}
	
}