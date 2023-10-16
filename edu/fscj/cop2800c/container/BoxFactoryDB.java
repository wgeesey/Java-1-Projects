// BoxFactoryDB.java
// D. Singletary
// 3/24/23
// Class for Box DB operations

package edu.fscj.cop2800c.container;

import java.sql.*;

public class BoxFactoryDB
{      
    public static void createDB(Box[] boxes)
    {
       final String DB_NAME = "BoxFactory";
       final String USER_NAME = DB_NAME;
       final String USER_PW = DB_NAME;

       // class name for driver
       // Apache Derby
       // final String CLASS_NAME = "org.apache.derby.jdbc.ClientDriver";
       // SQL Server
       final String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
       
       // derby connection url
       //final String CONN_URL = "jdbc:derby://localhost:1527/" + DB_NAME;
       
       // SQL Server connection url, use integratedSecurity=true for
       // Windows (vs. SQL) authentication
       final String CONN_URL = "jdbc:sqlserver://localhost:1433;" +
                               "integratedSecurity=true;";

       //System.out.println("Current JVM version - " + System.getProperty("java.version"));

       final String SQL_DROP_TABLE = "DROP TABLE Box";
       
       // Declare the JDBC objects.
       Connection con = null;
       Statement stmt = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       // Many things can go wrong here that in the real world would
       // need to be dealt with gracefully. To keep it simple, however,
       // we are just using a monolithic try/catch
       try {
           
           // load the driver         
           Class.forName(CLASS_NAME);

           // get a connection
           con = DriverManager.getConnection(CONN_URL, USER_NAME, USER_PW);

           // create a statement container
           stmt = con.createStatement(); // this can be reused
           
           // try to create the DB, skip if already there (i.e. if previous drop failed)
           try {
               stmt.executeUpdate("CREATE DATABASE " + DB_NAME + ";");
               System.out.println("DB created");
           } catch (SQLException e) { 
               System.out.println("could not create DB, already exists");
           }
           
           // declare the SQL statement to create the table
           String createQuery = "USE " + DB_NAME + ";" +
                      "CREATE TABLE Box " +
                      "(ID smallint  PRIMARY KEY NOT NULL," +
                       "SIZE varchar(8) NOT NULL," +
                       "COLOR varchar(12) NOT NULL," +
                       "LENGTH float NOT NULL," +
                       "WIDTH float NOT NULL," +
                       "HEIGHT float NOT NULL)";
                       
            // create the table
            stmt.executeUpdate(createQuery);
            System.out.println("Table created");
            
            // use the PreparedStatement to insert the data
            String insertQuery = "INSERT INTO Box(ID, SIZE, COLOR, LENGTH, WIDTH, HEIGHT) " +
                                 "VALUES(?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertQuery);
            for (Box box : boxes) {
                pstmt.setInt(1, box.getId());
                pstmt.setString(2, box.getSize().toString());
                pstmt.setString(3, box.getColor().toString());
                pstmt.setDouble(4, box.getLength());
                pstmt.setDouble(5, box.getWidth());
                pstmt.setDouble(6, box.getHeight());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        
            // select the data from the table, save to ResultSet
            System.out.println("Reading from DB");
            String selectQuery = "SELECT * FROM Box;";
            rs = stmt.executeQuery(selectQuery);
            
            // show the data using the next() iterator
            while (rs.next()) {
                 int id = rs.getInt("ID");
                 String size = rs.getString("SIZE");
                 String color = rs.getString("COLOR");
                 double length = rs.getFloat("LENGTH");
                 double width = rs.getFloat("WIDTH");
                 double height = rs.getFloat("HEIGHT");
                 System.out.println(id + "," +
                                    size + "," +
                                    color + "," +
                                    length + "," +
                                    width + "," +
                                    height);                
            }
          
           // drop the table and DB
           stmt.executeUpdate(SQL_DROP_TABLE);
           System.out.println("Box table dropped");
           
           // try to drop the DB, frequently fails due to "in use"
           try {
               stmt.executeUpdate("DROP DATABASE " + DB_NAME + ";");
               System.out.println("DB dropped");
           } catch (SQLException e) { 
               System.out.println("could not drop DB, in use");
           }
       } catch (Exception e) { // Handle any errors that may have occurred.
           e.printStackTrace();
       }
       
       // clean up whether we catch an exception or not
       finally {
           // close can also throw an exception, we want to continue
           // to close other objects if it does so we'll do a 
           // try/catch for each close operation
           if (rs != null) try { rs.close(); } catch(Exception e) {}
           if (stmt != null) try { stmt.close(); } catch(Exception e) {}
           if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
           if (con != null) try { con.close(); } catch(Exception e) {}
       }
    }
}
