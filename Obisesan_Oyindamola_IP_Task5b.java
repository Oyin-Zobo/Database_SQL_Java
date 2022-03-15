import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Project1 {

    // Database credentials
    final static String HOSTNAME = "obis000-sql-server.database.windows.net";
    final static String DBNAME = "cs-dsa-4513-sql-db";
    final static String USERNAME = "obis0000";
    final static String PASSWORD = "Anec3030";
    
 // Database connection string
    final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
            HOSTNAME, DBNAME, USERNAME, PASSWORD);

    // Query templates
    final static String QUERY_TEMPLATE_1 = "EXEC query1 @cust_name = ?, @addr = ?, @category = ?;";
    final static String QUERY_TEMPLATE_2 = "EXEC query2 @dept_num = ?, @dep_data = ?;";
    final static String QUERY_TEMPLATE_3 = "EXEC query3 @pid = ?, @process_data = ?, @dept_num = ?, @type_process = ?, @cut_type = ?, @machine_type = ?, @fit_type = ?, @paint_type = ?, @paint_method = ?;";
    //final static String QUERY_TEMPLATE_3 = "EXEC query3 @pid = ?, @process_data = ?, @dept_num = ?, @type_process = ?, @cut_type = ?, machine_type = ?, @fit_type = ?, @paint_type = ?, @paint_method = ?;";
    final static String QUERY_TEMPLATE_4 = "EXEC query4 @assm_id = ?, @pid = ?, @cust_name = ?, @dat_assm = ?, @detail = ?, @job_id = ?, @dep_num = ?;";
    final static String QUERY_TEMPLATE_5 = "EXEC query5 @acct_num = ?, @pid = ?, @assm_id = ?, @date_est = ?, @dept_num = ?, @type_acct = ?, @cost_assm = ?, @cost_dept = ?, @cost_pro = ?;";
    final static String QUERY_TEMPLATE_6 = "EXEC query6 @job_id = ?, @date_start = ?, @dept_num = ?, @pid = ?, @assm_id = ?, @type_job = ?;"; 
    final static String QUERY_TEMPLATE_7 = "EXEC query7 @date_end = ?, @labor_time_cut = ?, @labor_time_fit = ?, @labor_time_paint = ?, @job_id = ?, @type_job = ?, @type_of_mac = ?, @amt_time = ?, @mat_used = ?, @volume = ?, @color = ?;"; 
    final static String QUERY_TEMPLATE_8 = "EXEC query8 @trans_id = ?, @sub_cost = ?, @acct_type = ?, @assm_id = ?, @acct_num = ?, @date_est = ?;"; 
    final static String QUERY_TEMPLATE_9 = "EXEC query9 @assm_id = ?;";
    final static String QUERY_TEMPLATE_10 = "EXEC query10 @dept_num = ?, @date_end = ?;";
    final static String QUERY_TEMPLATE_11 = "EXEC query11 @assm_id = ?;"; 
    final static String QUERY_TEMPLATE_12 = "EXEC query12 @job_type = ?, @date_end = ?, @dept_num = ?;"; 
    final static String QUERY_TEMPLATE_13 = "EXEC query13 @cat_1 = ?, @cat_2 = ?;"; 
    final static String QUERY_TEMPLATE_14 = "EXEC query14 @job_id_1 = ?, @job_id_2 = ?;"; 
    final static String QUERY_TEMPLATE_15 = "EXEC query15 @job_id = ?, @color = ?;"; 
    final static String QUERY_TEMPLATE_16 = "EXEC query16 @cust_name = ?, @addr = ?, @category = ?;";  
    final static String QUERY_TEMPLATE_17 = "EXEC query17";
  
    
    // User input prompt//
    final static String PROMPT = 
            "\nPlease select one of the options below: \n" +
            "1) Insert new customer; \n" + 
            "2) Insert new department; \n" +
            "3) Insert new process; \n" +
            "4) Insert new assembly; \n" +
            "5) Insert new account; \n" +
            "6) Insert new job; \n" +
            "7) Insert new job_end; \n" +
            "8) Insert update acct; \n" +
            "9) retrieve total labor time; \n" +
            "10)retreive total_labor_time; \n" +
            "11)retreive assm_id; \n" +
            "12)retrieve job; \n" +
            "13)retrieve category range; \n" +
            "14)delete job; \n" +
            "15)update paint; \n" +
            "16)import; \n" +
            "17)export; \n" +
            "18) Exit!";
    
    public static void main(String[] args) throws SQLException {

        System.out.println("Welcome to the sample application!");

        final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
        String option = ""; // Initialize user option selection as nothing
        while (!option.equals("3")) { // As user for options until option 3 is selected
            System.out.println(PROMPT); // Print the available options
            option = sc.next(); // Read in the user option selection
            
            switch (option) { // Switch between different options
            case "1": // Insert a new CUSTOMER option
                // Collect the new customer data from the user
                System.out.println("Please enter Customer name:");
                sc.nextLine();
                final String cust_name = sc.nextLine(); // Read in the user input of customer name 

                System.out.println("Please enter address:");
                // Preceding nextInt, nextFloar, etc. do not consume new line characters from the user input.
                // We call nextLine to consume that newline character, so that subsequent nextLine doesn't return nothing.
                //sc.nextLine();
                final String addr = sc.nextLine(); // Read in user input of address (white-spaces allowed).

              
                System.out.println("Please enter category");
                //sc.nextLine();
                final int category = sc.nextInt(); // Read in user input of category

            
              
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_1)) {
                        // Populate the query template with the data collected from the user
                        statement.setString(1, cust_name);
                        statement.setString(2, addr);
                        //statement.setInt(3, y_of_e);
                        statement.setInt(3, category);
                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;
            
            case "2": // Insert a new student option
                // Collect the new department data from the user
                System.out.println("Please enter integer  department:");
                final int dept_num = sc.nextInt(); // Read in the user input of dept_num 

                System.out.println("Please enter department details:");
                // Preceding nextInt, nextFloar, etc. do not consume new line characters from the user input.
                // We call nextLine to consume that newline character, so that subsequent nextLine doesn't return nothing.
                sc.nextLine();
                final String dep_data = sc.nextLine(); // Read in user input of student department number 

               

                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2)) {
                        // Populate the query template with the data collected from the user
                        statement.setInt(1, dept_num);
                        statement.setString(2, dep_data);
                        


                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;    
                
            case "3": // Insert a new process option
                // Collect the new process data from the user
               System.out.println("Please enter process id:");
               sc.nextLine();
               final String pid3 = sc.nextLine(); // Read in the user input of process id 
               
               System.out.println("Please enter process data:");
               //sc.nextLine();
               final String process_data = sc.nextLine(); // Read in the user input of process data 
               
               System.out.println("Please enter department num:");
               final int dept_num3 = sc.nextInt(); // Read in the user input of department number 
               
               System.out.println("Please enter type_process:");
               sc.nextLine();
               final String type_process = sc.nextLine(); // Read in the user input of process type
               
               System.out.println("Please enter cut type:");
               //sc.nextLine();
               final String cut_type = sc.nextLine(); // Read in the user input of cut type 
               
               System.out.println("Please enter machine type:");
               //sc.nextLine();
               final String machine_type = sc.nextLine(); // Read in the user input of machine type 
               
               System.out.println("Please enter fit type:");
               //sc.nextLine();
               final String fit_type = sc.nextLine(); // Read in the user input of fit type
               
               System.out.println("Please enter paint type:");
               //sc.nextLine();
               final String paint_type = sc.nextLine(); // Read in the user input of paint type

                System.out.println("Please enter paint method:");
                // Preceding nextInt, nextFloar, etc. do not consume new line characters from the user input.
                // We call nextLine to consume that newline character, so that subsequent nextLine doesn't return nothing.
                //sc.nextLine();
                final String paint_method = sc.nextLine(); // Read in user input of student First Name (white-spaces allowed).
               
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_3)) {
                        // Populate the query template with the data collected from the user
                        
                    	statement.setString(1, pid3);
                    	statement.setString(2, process_data);
                    	statement.setInt(3, dept_num3);
                    	statement.setString(4, type_process);
                        statement.setString(5, cut_type);
                        statement.setString(6, machine_type);
                        statement.setString(7, fit_type);
                        statement.setString(8, paint_type);
                        statement.setString(9, paint_method);


                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;    
                
                
            case "4": // Insert a new assembly option
                // Collect the new student data from the user
            	System.out.println("Please enter assembly ID:");
            	sc.nextLine();
                final String assm_id = sc.nextLine(); 
                
                System.out.println("Please enter process id:");
                final int pid = sc.nextInt(); 
                
                System.out.println("Please enter customer name:");
            	sc.nextLine();
                final String cust_name1 = sc.nextLine(); 
                
                System.out.println("Please enter assembly date:");
            	//sc.nextLine();
                final String dat_assm = sc.nextLine(); 
                
                System.out.println("Please enter assembly detail:");
            	//sc.nextLine();
                final String detail = sc.nextLine(); 
            	
            	System.out.println("Please enter integer  department:");
                final int dep_num = sc.nextInt(); 

                System.out.println("Please enter JOB ID:");
                // Preceding nextInt, nextFloar, etc. do not consume new line characters from the user input.
                // We call nextLine to consume that newline character, so that subsequent nextLine doesn't return nothing.
                //sc.nextLine();
                final int job_id = sc.nextInt(); 


                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_4)) {
                        // Populate the query template with the data collected from the user
                        statement.setString(1, assm_id);
                        statement.setInt(2, pid);
                        statement.setString(3,  cust_name1);
                        statement.setString(4, dat_assm);
                        statement.setString(5,  detail);
                    	statement.setInt(6, dep_num);
                        statement.setInt(7, job_id);


                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;    
                
            
            
            case "5": // Insert a new student option
                // Collect the new student data from the user
            	System.out.println("Please enter account number:");
            	//sc.nextLine();
                final int acct_num5 = sc.nextInt(); 
                
                System.out.println("Please enter process id:");
                sc.nextLine();
                final String pid5 = sc.nextLine(); 
                
                System.out.println("Please enter assembly id:");
                //sc.nextLine();
                final String assm_id5 = sc.nextLine(); 
                
                System.out.println("Please enter date est:");
            	//sc.nextLine();
                final String date_est = sc.nextLine(); 
                
                System.out.println("Please enter department number:");
            	//sc.nextLine();
                final int dept_num5 = sc.nextInt(); 
                
                System.out.println("Please enter type:");
            	sc.nextLine();
                final String type_acct = sc.nextLine(); 
                
                System.out.println("Please enter cost of assem:");
                //sc.nextLine();
                final float cost_assm = sc.nextFloat(); 
            	
                System.out.println("Please enter cost of dept:");
                //sc.nextLine();
                final float cost_dept = sc.nextFloat(); 
                
                System.out.println("Please enter cost of process:");
                //sc.nextLine();
                final float cost_pro = sc.nextFloat(); 
                

                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_5)) {
                        // Populate the query template with the data collected from the user
                        statement.setInt(1,acct_num5);
                        statement.setString(2,  pid5);
                        statement.setString(3, assm_id5);
                        statement.setString(4, date_est);
                        statement.setInt(5, dept_num5);
                        statement.setString(6, type_acct);
                        statement.setFloat(7,  cost_assm);
                        statement.setFloat(8,  cost_dept);
                        statement.setFloat(9, cost_pro);


                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;   
                
            case "6": // Insert a new job option
                // Collect the new job data from the user
            	
            	System.out.println("Please enter job id:");
                final int job_id6 = sc.nextInt(); 
                

                System.out.println("Please enter date start:");
                sc.nextLine();
                final String date_start = sc.nextLine(); 
                
                System.out.println("Please enter integer  department:");
                final int dept_num6 = sc.nextInt(); 
               
                
                
            	System.out.println("Please enter process id:");
                final int pid6 = sc.nextInt(); 

                
            	System.out.println("Please enter assembly ID:");
            	sc.nextLine();
                final String assm_id6 = sc.nextLine(); 
                
                System.out.println("Please enter job_type:");
            	//sc.nextLine();
                final String type_job6 = sc.nextLine(); 


                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_6)) {
                        // Populate the query template with the data collected from the user
                    	statement.setInt(1, job_id6);
                    	statement.setString(2, date_start);
                    	statement.setInt(3, dept_num6);
                    	statement.setInt(4, pid6);
                    	statement.setString(5, assm_id6);
                    	statement.setString(6, type_job6);
                    	
                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;  
            
            case "7": // Insert a new job option
                // Collect the new job data from the user
            	
            	 System.out.println("Please enter date end:");
                 sc.nextLine();
                 final String date_end = sc.nextLine(); 
            	
            	System.out.println("Please enter  labor time cut:");
                final float labor_time_cut = sc.nextFloat(); 
                
                System.out.println("Please enter  labor time fit:");
                final float labor_time_fit = sc.nextFloat(); 
                
                System.out.println("Please enter  labor time paint:");
                final float labor_time_paint = sc.nextFloat(); 
               
                
                System.out.println("Please enter job id:");
                final int job_id7 = sc.nextInt(); 

                
            	System.out.println("Please enter job type:");
            	sc.nextLine();
                final String type_job = sc.nextLine(); 
                
                
                System.out.println("Please enter type_of_mac:");
            	//sc.nextLine();
                final String type_mac = sc.nextLine(); 
                
                System.out.println("Please enter amt time:");
                final float amt_time = sc.nextFloat(); 
                

                System.out.println("Please enter mat used:");
            	sc.nextLine();
                final String mat_used = sc.nextLine(); 
                
                
                System.out.println("Please enter volume");
                final float volume = sc.nextFloat(); 
                
                System.out.println("Please enter color:");
            	sc.nextLine();
                final String color = sc.nextLine(); 
                


                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_7)) {
                        // Populate the query template with the data collected from the user
                    	statement.setString(1, date_end);
                    	statement.setFloat(2, labor_time_cut);
                    	statement.setFloat(3, labor_time_fit);
                    	statement.setFloat(4, labor_time_paint);
                    	
                    	statement.setInt(5, job_id7);
                    	
                    	statement.setString(6, type_job);
                    	statement.setString(7, type_mac);
                    	statement.setFloat(8, amt_time);
                    	statement.setString(9, mat_used);
                    	statement.setFloat(10, volume);
                    	statement.setString(11, color);

                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break;      
                
            case "8": // Insert a new transaction option
                // Collect the new transaction data from the user
            	
            	System.out.println("Please enter trans id:");
            	sc.nextLine();
                final String trans_id = sc.nextLine(); 
                
                System.out.println("Please enter subcost:");
            	//sc.nextLine();
                final float sub_cost = sc.nextFloat(); 
                
                System.out.println("Please enter acct type:");
            	sc.nextLine();
                final String acct_type = sc.nextLine(); 
                
                System.out.println("Please enter assembly ID:");
            	//sc.nextLine();
                final String assm_id8 = sc.nextLine(); 

                System.out.println("Please enter acct num:");
                //sc.nextLine();
                final int acct_num = sc.nextInt(); 
                
                System.out.println("Please enter date est:");
                sc.nextLine();
                final String date_est8 = sc.nextLine(); 
               
                
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_8)) {
                        // Populate the query template with the data collected from the user
                    	statement.setString(1, trans_id);
                    	statement.setFloat(2, sub_cost);
                    	statement.setString(3, acct_type);
                    	statement.setString(4, assm_id8);
                    	statement.setInt(5, acct_num);
                    	statement.setString(6, date_est8);
                       
                    	
                        
                        //statement.setFloat(3, age_);
                        //statement.setInt(4,did);


                        System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                    }
                }
                
                break; 
                
                
            case "9": 
            
                System.out.println("Please enter assembly ID:");
            	sc.nextLine();
                final String assm_id9 = sc.nextLine(); 
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_9)) {
                        // Populate the query template with the data collected from the user
                    	
                    	statement.setString(1, assm_id9);
                    	
                    	try(
                                final ResultSet resultSet = statement.executeQuery()){
                                
                                while (resultSet.next()) {
                                System.out.println(String.format("\ttotal_cost: %s",
                                    resultSet.getString(1)));
                                System.out.println("\n"); 
                                
                                }
                    		}
                    }
                }
                
                break; 
                
             
            case "10":
               
            	
            
                System.out.println("Please department num:");
            	
                final int dept_num10 = sc.nextInt();
                //final int total_labor_time = sc.nextInt();
                System.out.println("Please date completed:");
                
                final String date_end10 = sc.nextLine();       
                sc.nextLine();
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_10)) {
                        // Populate the query template with the data collected from the user
                    	
                    	statement.setInt(1, dept_num10);
                    	statement.setString(2, date_end10);
                    	
                    	try(
                                final ResultSet resultSet = statement.executeQuery()){
                                
                                while (resultSet.next()) {
                                System.out.println(String.format("\ttotal_labor_time: %s",
                                    resultSet.getString(1)));
                                System.out.println("\n"); 
                                
                                }
                    		}
                    }
                }
                
                break; 
                
            case "11": 
             
            	
            
                System.out.println("Please enter assembly ID:");
            	sc.nextLine();
                final String assm_id11 = sc.nextLine();
                //final int total_labor_time = sc.nextInt();
                //final String date_end10 = sc.nextLine();                
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_11)) {
                        // Populate the query template with the data collected from the user
                    	
                    	statement.setString(1, assm_id11);
                    	//statement.setString(2, date_end10);
                    	
                    	try(
                                final ResultSet resultSet = statement.executeQuery()){
                                
                                while (resultSet.next()) {
                                System.out.println(String.format("%s | %s",
                                    resultSet.getString(1), resultSet.getString(2)));
                                System.out.println("\n"); 
                                
                                }
                    		}
                    }
                }
                
                break; 
            
            case "12": 
                
            	
            
                System.out.println("Please enter job type:");
            	sc.nextLine();
                final String job_type = sc.nextLine();
                
                System.out.println("Please enter date ended:");
            	//sc.nextLine();
                final String date_end12 = sc.nextLine();
                
                System.out.println("Please enter department number:");
            	//sc.nextLine();
                final int dept_num12 = sc.nextInt();
                
                //final int total_labor_time = sc.nextInt();
                //final String date_end10 = sc.nextLine();                
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_12)) {
                        // Populate the query template with the data collected from the user
                    	
                    	statement.setString(1, job_type);
                    	statement.setString(2, date_end12);
                    	statement.setInt(3, dept_num12);
                    	
                    	try(
                                final ResultSet resultSet = statement.executeQuery()){
                                
                                while (resultSet.next()) {
                                System.out.println(String.format("%s | %s | %s | %s | %s | %s | %s",
                                    resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
                                System.out.println("\n"); 
                                
                                }
                    		}
                    }
                }
                
                break; 
            
            case "13": 
   
                System.out.println("Please enter category 1:");
            	//sc.nextLine();
                final int cat_1 = sc.nextInt();
                
                System.out.println("Please enter category 2:");
            	//sc.nextLine();
                final int cat_2 = sc.nextInt();
                
                //final int total_labor_time = sc.nextInt();
                //final String date_end10 = sc.nextLine();                
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_13)) {
                        // Populate the query template with the data collected from the user
                    	
                    	
                    	
                    	statement.setInt(1, cat_1);
                    	statement.setInt(2, cat_2);
                    	
                    	try(
                                final ResultSet resultSet = statement.executeQuery()){
                                
                                while (resultSet.next()) {
                                System.out.println(String.format("\tcust_name: %s",
                                    resultSet.getString(1)));
                                System.out.println("\n"); 
                                
                                }
                    		}
                    }
                }
                
                break; 
           
            case "14":
                
                System.out.println("Please enter job_id 1:");
            	//sc.nextLine();
                final int job_id_1 = sc.nextInt();
                
                System.out.println("Please enter job_id 2:");
            	//sc.nextLine();
                final int job_id_2 = sc.nextInt();
  
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_14)) {
                        // Populate the query template with the data collected from the user
                    	
                    	//statement.setString(1, job_type);
                    	//statement.setString(2, date_end12);
                    	
                    	statement.setInt(1, job_id_1);
                    	statement.setInt(2, job_id_2);
                    	
                    	
                    	System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows updated.", rows_inserted));
                        
                    	
                    }
                }
                
                break;      
                
            case "15": 
                
                System.out.println("Please enter job_id:");
            	//sc.nextLine();
                final int job_id15 = sc.nextInt();
                
                System.out.println("Please enter color:");
            	sc.nextLine();
                final String color15 = sc.nextLine();
                
                //final int total_labor_time = sc.nextInt();
                //final String date_end10 = sc.nextLine();                
   
                System.out.println("Connecting to the database...");
                // Get a database connection and prepare a query statement
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    try (
                        final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_15)) {
                        // Populate the query template with the data collected from the user
                    	
                    	//statement.setString(1, job_type);
                    	//statement.setString(2, date_end12);
                    	
                    	statement.setInt(1, job_id15);
                    	statement.setString(2, color15);
                    	
                    	
                    	System.out.println("Dispatching the query...");
                        // Actually execute the populated query
                        final int rows_inserted = statement.executeUpdate();
                        System.out.println(String.format("Done. %d rows updated.", rows_inserted));
                        
                    	
                    }
                }
                
                break;        
              
                
            case "16": // Import: enter new customer from a data file until the file is empty.
                
                sc.nextLine();
               
                System.out.println("Please enter the input filename:");
                final String input_file = sc.nextLine();
                
                try {
                      File myObj = new File(input_file+".txt");
                      
                      Scanner myReader = new Scanner(myObj);
                      
                      while (myReader.hasNextLine()) {
                          for(int k=0; k<1; k++) {
                            String data = myReader.nextLine();
                              System.out.println(data);
                              
                            try (final Connection connection = DriverManager.getConnection(URL)) {
                              try (
                                  final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_16)) {
                                  
                                  
                            	  //System.out.println("5");
                                  // Populate the query template with the data collected from the user
                                  statement.setString(1, data);
                                  //System.out.println("5");
                                  data = myReader.nextLine();
                                  statement.setString(2, data);
                                  data = myReader.nextLine();
                                  statement.setString(3, data);

 

                                  System.out.println("Dispatching the query...");
                                  // Actually execute the populated query
                                  final int rows_inserted = statement.executeUpdate();
                                  System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
                              }
                          }
                              
                          } 
                      }
                      myReader.close();
                    } catch (FileNotFoundException e) {
                      System.out.println("An error occurred.");
                      e.printStackTrace();
                    }                
                    break;  
           
               
                
            case "17": // Retrieve
                
            	 //final int cat_117 = sc.nextInt();
                 
                 //System.out.println("Please enter category 2:");
             	//sc.nextLine();
                 //final int cat_217 = sc.nextInt();
            	
            	sc.nextLine();
                System.out.println("Please enter the output filename ");
                final String outputFile = sc.nextLine();
                System.out.println("Connecting to the database...");
                // Get the database connection, execute procedure, as no user input need be collected
                try (final Connection connection = DriverManager.getConnection(URL)) {
                    System.out.println("Dispatching the query...");
                    try (
                        final Statement statement = connection.createStatement();
  
                    	final ResultSet resultSet = statement.executeQuery(QUERY_TEMPLATE_17)) 
                    {
                    	
                        try {
                            FileWriter myWriter = new FileWriter(outputFile+".txt");
                            
                          myWriter.write("Contents of the QUERY: \n");
                          myWriter.write("customer names\n");
                        //myWriter.write(resultSet.getString(1));
                        while (resultSet.next()) {
                         
                          
                          myWriter.write(resultSet.getString(1)+"\n");
                      }
                          
                            myWriter.close();
                            System.out.println("Successfully wrote to the file.");
                          } catch (IOException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                          }                           
                    }
                }
            
                break;
                
            case "18": // Do nothing, the while loop will terminate upon the next iteration
                System.out.println("Exiting! Good-Bye!");
                break;
            default: // Unrecognized option, re-prompt the user for the correct one
                System.out.println(String.format(
                    "Unrecognized option: %s\n" + 
                    "Please try again!", 
                    option));
                break;
        }
    }

    sc.close(); // Close the scanner before exiting the application
}
}
                
                