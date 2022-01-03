package pack.SQLPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlConnection {
    private final static String connectionString = "jdbc:mysql://localhost:3306/boardgamedb";
    private final static String userId = "root";
    private final static String lock = "julia";
    private static Connection dbContext;
    
    
    static  {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            dbContext =  DriverManager.getConnection(connectionString,userId,lock);
        } catch (Exception ex) { System.out.println(ex.getMessage()); }
    }
    public static void closeConnection(){
        try {
            dbContext.close();
        } catch (SQLException ex){ System.out.println(ex.getMessage()); }
    }
    public static Connection getDbContext(){
        return dbContext;
    }

}