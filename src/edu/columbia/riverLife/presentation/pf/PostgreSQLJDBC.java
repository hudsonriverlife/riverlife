package edu.columbia.riverLife.presentation.pf;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PostgreSQLJDBC {
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
       Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://localhost:5432/hudson",
            "xxx", "xxxxxx");
       
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
        // ResultSet rs = stmt.executeQuery( "SELECT site_sampling_id FROM riverlife.site_sampling;" );
         ResultSet rs = stmt.executeQuery( "select student_salinity_id as site_sampling_id from riverlife.student_salinity;" );
         
         while ( rs.next() ) {
            int id = rs.getInt("site_sampling_id");
           
            System.out.println( "ID = " + id );
           
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
       System.out.println("Operation done successfully");
     }
}
