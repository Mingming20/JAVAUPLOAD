/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataBase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author yosoresje_sd2081
 */
public class CRUD {
    private ResultSet conn;
    public boolean addData(String sqlString) {
        try {
            // create a mysql database connection
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/demo";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");

            Statement st = conn.createStatement();

            // note that i'm leaving "date_created" out of this insert statement
            st.executeUpdate(sqlString);
            conn.close();
            return true;
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return false;
        }

    }

    public ResultSet getData(String sqlQuery) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost/demo";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "");
            
            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sqlQuery);
            while(rs.next()){
                System.out.println(rs.getString("fname") + rs.getString("mname")+rs.getString("lname")
                +rs.getString("username") + rs.getString("password")+ rs.getString("email"));
            }    
            conn.close();
            return rs;
           
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            return conn;
        }
       
    }
   
}