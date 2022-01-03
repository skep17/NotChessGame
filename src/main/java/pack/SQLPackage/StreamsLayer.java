package pack.SQLPackage;

import java.sql.ResultSet;
import java.sql.SQLException;

import pack.DAO.User;

public class StreamsLayer {
	private SqlQueries dbLayer;
	
	public StreamsLayer() {
		dbLayer = new SqlQueries();
	}
	
	public User getUserInfo() throws SQLException {
		ResultSet allUsers =  dbLayer.getAllUsers();
	
		
		return null;
	}
}
