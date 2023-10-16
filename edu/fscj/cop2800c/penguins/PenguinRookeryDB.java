// PenguinRookeryDB.java
// W. Geesey
// 3/27/23
// Class for Penguin DB operations

package edu.fscj.cop2800c.penguins;

import java.sql.*;

public class PenguinRookeryDB
{      
    public static void createDB(Penguin[] penguins)
    {
       final String DB_NAME = "PenguinRookery";
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

       final String SQL_DROP_TABLE = "DROP TABLE Penguin";
       
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
                      "CREATE TABLE Penguin " +
                      "(SAMPLENUMBER smallint  PRIMARY KEY NOT NULL," +
                       "SPECIES varchar(12) NOT NULL," +
                       "CULMENLENGTH decimal(18,2) NOT NULL," +
                       "CULMENDEPTH decimal(18,2) NOT NULL," +
                       "FLIPPERLENGTH float NOT NULL," +
                       "BODYMASS float NOT NULL," +
                       "SEX varchar(8) NOT NULL)";
                       
            // create the table
            stmt.executeUpdate(createQuery);
            System.out.println("Table created");
            
            // use the PreparedStatement to insert the data
            String insertQuery = "INSERT INTO PENGUIN(SAMPLENUMBER, SPECIES, CULMENLENGTH, CULMENDEPTH, FLIPPERLENGTH, BODYMASS, SEX) " +
                                 "VALUES(?, ?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(insertQuery);
            for (Penguin penguin : penguins) {
                pstmt.setInt(1, penguin.getSampleNum());
                pstmt.setString(2, penguin.getSpecies().toString());
                pstmt.setDouble(3, penguin.getCulmenLen());
                pstmt.setDouble(4, penguin.getCulmenDepth());
                pstmt.setDouble(5, penguin.getFlipperLen());
                pstmt.setDouble(6, penguin.getBodyMass());
                pstmt.setString(7, penguin.getSex().toString());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
        
            // select the data from the table, save to ResultSet
            System.out.println("Reading from DB");
            String selectQuery = "SELECT * FROM Penguin;";
            rs = stmt.executeQuery(selectQuery);
            
            // show the data using the next() iterator
            while (rs.next()) {
                 int sampleNum = rs.getInt("SAMPLENUMBER");
                 String species = rs.getString("SPECIES");
                 double culmenLen = rs.getBigDecimal("CULMENLENGTH").doubleValue();
                 double culmenDep = rs.getBigDecimal("CULMENDEPTH").doubleValue();
                 double flipperLen = rs.getDouble("FLIPPERLENGTH");
                 double bodyMass = rs.getDouble("BODYMASS");
                 String sex = rs.getString("SEX");
                 System.out.println(sampleNum + "," +
                                    species + "," +
                                    culmenLen + "," +
                                    culmenDep + "," +
                                    flipperLen + "," +
                                    bodyMass + "," +
                                    sex);                
            }
          
           // drop the table and DB
//            stmt.executeUpdate(SQL_DROP_TABLE);
//            System.out.println("Penguin table dropped");
           
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
