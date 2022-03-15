package project_azure;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataHandler {
	
	private Connection conn;

    // Azure SQL connection credentials
	final static String host = "obis000-sql-server.database.windows.net";
    final static String database = "cs-dsa-4513-sql-db";
    final static String username = "obis0000";
    final static String password = "Anec3030";

    // Resulting connection string
    final private String url =
            String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                    host, database, username, password);

    // Initialize and save the database connection
    private void getDBConnection() throws SQLException {
        if (conn != null) {
            return;
        }

        this.conn = DriverManager.getConnection(url);
    }

    // Return the result of selecting everything from the movie_night table 
    public ResultSet getAllcust() throws SQLException {
        getDBConnection();
        
        final String sqlQuery = "SELECT * FROM customer;";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        return stmt.executeQuery();
    }

    // Inserts a record into the movie_night table with the given attribute values
    public boolean addcustomer(
            String cust_name, String addr, int category) throws SQLException {

        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery =
                "INSERT INTO customer " + 
                    "(cust_name, addr, category) " + 
                "VALUES " + 
                "(?, ?, ?)";
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute values
        stmt.setString(1, cust_name);
        stmt.setString(2, addr);
        stmt.setInt(3, category);


        // Execute the query, if only one record is updated, then we indicate success by returning true
        return stmt.executeUpdate() == 1;
    }
    
    //public ResultSet somecustomer() throws SQLException {
    public ResultSet somecustomer(int category_1, int category_2) throws SQLException {
        getDBConnection(); // Prepare the database connection

        // Prepare the SQL statement
        final String sqlQuery = 
        		
        		"select cust_name " +
        	    "from customer " +
        	    "where category between 2 and 10 " +
        	    "order by cust_name asc ";
        
        System.out.println(category_1);
        System.out.println(category_2);
        
        final PreparedStatement stmt = conn.prepareStatement(sqlQuery);

        // Replace the '?' in the above statement with the given attribute values
       //stmt.setInt(1, category_1);
       //stmt.setInt(2, category_2);
        //stmt.setInt(3, category);


        // Execute the query, if only one record is updated, then we indicate success by returning true
        return stmt.executeQuery();
    }
    
}

