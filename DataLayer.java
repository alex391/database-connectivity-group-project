/*
 * Alex Leute
 * 10/2/22
 * HW02
 */
import java.sql.*;

import java.io.Console;
public class DataLayer {
    private Connection conn;
    private Console console = System.console();
    /**
     * Connect to the database
     * @return true if the connection is successful, else false
     */
    public boolean connect(){
        // get a connection
                
        /* url line below at the end identifies the database name
        /* Define Data Source */
        String url = "jdbc:mysql://localhost/travel";
        url = url + "?serverTimezone=UTC"; //added 9/12
        
        String user = new String();
        String password;
        System.out.print("MySQL username (default root): ");
        user = console.readLine();
        if (user.equalsIgnoreCase("")) {
            user = "root";
        }
        password = new String(console.readPassword("Password (Default is \"student\") (typing hidden):"));
   
        // set the default password to   "student"
        if (password.equalsIgnoreCase("")) {
            password = "student";
        }


        // 2) Create a connection
        try {
            //This statement below returns connections to the URL.
            //SQLException will be thrown, if database access occurs or url is null.

            conn = DriverManager.getConnection(url,user,password);
        }
        catch(SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("There was a connection error");
            System.exit(1);
            return false;
        }
        return true;

    }
    /**
     * Close the database connection
     * @return true if successful, false otherwise
     */
    public boolean close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    /**
     * Add a passenger.
     * @param id
     * @param args FName, LName, Street, Zip
     * @return the number of records inserted
     */
    public int addPassenger(int id, String... agrs){
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("INSERT INTO passenger VALUES (?, ?, ?, ?, ?);");
            stmt.setInt(1,id);
            int index = 2;
            for (String string : agrs) {
                stmt.setString(index++, string);
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the insert");
            e.printStackTrace();
            System.exit(1);
            return 0; // Unreachable, but the compiler doesn't (can't) know that
        }
    }
    /**
     * Delete a passenger
     * @param id
     * @return the number of records deleted
     */
    public int deletePassenger(int id){
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM passenger WHERE PassengerId = ?;");
            stmt.setInt(1,id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the delete");
            e.printStackTrace();
            System.exit(1);
            return 0; // Unreachable, but the compiler doesn't (can't) know that
        }

    }
    public int updatePassenger(int id, String street){
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE passenger SET Street = ? WHERE PassengerId = ?;");
            stmt.setString(1,street);
            stmt.setInt(2,id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the update");
            e.printStackTrace();
            System.exit(1);
            return 0; // Unreachable, but the compiler doesn't (can't) know that
        }
    }
}