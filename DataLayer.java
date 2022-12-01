/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

import java.io.Console;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {
    private Connection conn;
    private Console console = System.console();

    /**
     * Connect to the database
     * 
     * @return true if the connection is successful, else false
     */
    public boolean connect(String username, String password) {
        // get a connection

        /*
         * url line below at the end identifies the database name
         * /* Define Data Source
         */
        String url = "jdbc:mysql://localhost/studentfaculty";
        url = url + "?serverTimezone=UTC"; // added 9/12

        // 2) Create a connection
        try {
            // This statement below returns connections to the URL.
            // SQLException will be thrown, if database access occurs or url is null.

            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("There was a connection error");
            System.exit(1);
            return false;
        }
        return true;
    }

    /**
     * Close the database connection
     * 
     * @return true if successful, false otherwise
     */
    public boolean close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    // student search Entries Function
    // - Takes in the interestID  from input
    // System will display entries from faculty members with that interest

    public int searchEntries(int interestid){
        int result=0;
        try{
        PreparedStatement stmt;
        stmt =conn.prepareStatement("SELECT entries.topic AS “email” ,interestID From userinterests JOIN entries USING(userID) WHERE entries.userID = userinterests.userID AND interestID = ? GROUP BY entries.userID;");
        stmt.setInt(1,interestid);
        result = stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("There was an error in the insert");
            System.out.println("Error = " + e);
            e.printStackTrace();      
          }
         return result;
        }

    // student search faculty Function
    // - Takes in the interestID  from input
    // System will display the email address from faculty members with that interest

    public int searchFaculty(int interestid){
    int result=0;
    try{
    PreparedStatement stmt;
    stmt =conn.prepareStatement("SELECT faculty.email AS 'email', faculty.officeNumber as 'Office Number'From faculty JOIN userinterests USING(userID) WHERE faculty.userID = userID AND interestID = ? GROUP BY faculty.userID;");
    stmt.setInt(1,interestid);
    result = stmt.executeUpdate();
    }catch(SQLException e){
        System.out.println("There was an error in the insert");
        System.out.println("Error = " + e);
        e.printStackTrace();      
      }
     return result;
    }


    // Faculty Add Function
    // - Takes in the userID from sign in and topic from input
    // - User will input the topic and other entry data into GUI,
    // System already knows their userID, entryID is assigned automatically.
    

    
    
    public int addEntry(int userid, String topic){
        int result = 0;
        try {
        
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO entries(userID, topic) VALUES (?, ?);");
            stmt.setInt(1,userid);
            stmt.setString(2,topic);
         
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the insert");
            System.out.println("Error = " + e);
            e.printStackTrace();      
        }
        return (result);
    }
    
    
    // Faculty Delete Function
    // - Takes in the entryid from GUI
    // User will choose which entry to delete (to be implemented later). Deletes 
    // the unique entryID 
    
    public int deleteEntry(int entryid){
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM entries WHERE entryID = ?;");
            stmt.setInt(1,entryid);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the delete");
            e.printStackTrace();
            System.exit(1);
            return 0; 
        }
    }
    
    

     // Faculty Update Function
     // - update the entry topic based on the entryid
     // - Will take in the entryid from searching method, and user
     // inputs whatever edits they make to topic. Method will
     // update the entry when user is done
     
    public int updateEntry(int entryid, String topic){
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE entries SET topic = ? WHERE entryId = ?;");
            stmt.setInt(1,entryid);
            stmt.setString(2,topic);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the update");
            e.printStackTrace();
            System.exit(1);
            return 0; 
        }
    }
    

    
    /**
     * Takes in username
     * @return an array of formatted entries that match user's id
     */
    public String[] selectUpdateEntry(int userID) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT topic FROM Entries WHERE userID = " + Integer.toString(userID));
            List<String> topics = new ArrayList<>();
            while (rs.next()) {
                topics.add(rs.getString("topic"));
            }
            return topics.toArray(new String[0]);
        } catch (SQLException e) {
            System.out.println("There was an error in selecting entries");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    public String[] appLoginDL(){
        String[] result = new String[0];
        return result;
    }
}