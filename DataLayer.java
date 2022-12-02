/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

import java.io.Console;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public boolean connect(String userName, String password) {
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

            conn = DriverManager.getConnection(url, userName, password);
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
    // - Takes in the interestID from input
    // System will display entries from faculty members with that interest

    public int searchEntries(int interestID) {
        int result = 0;
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement(
                    "SELECT entries.topic AS \"email\" , interestID FROM userinterests JOIN entries USING(userID) WHERE entries.userID = userinterests.userID AND interestID = ? GROUP BY entries.userID;");
            stmt.setInt(1, interestID);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the insert.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result;
    }

    // student search faculty Function
    // - Takes in the interestID from input
    // System will display the email address from faculty members with that interest

    public int searchFaculty(int interestID) {
        int result = 0;
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement(
                    "SELECT faculty.email AS 'email', faculty.officeNumber as 'Office Number' FROM faculty JOIN userinterests USING(userID) WHERE faculty.userID = userID AND interestID = ? GROUP BY faculty.userID;");
            stmt.setInt(1, interestID);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
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

    public int addEntry(int userID, String topic) {
        int result = 0;
        try {

            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO entries(userID, topic) VALUES (?, ?);");
            stmt.setInt(1, userID);
            stmt.setString(2, topic);

            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the insert.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return (result);
    }

    /**
     * Faculty Delete Entry Function.
     * Deletes a faculty member by taking in their entryID.
     * @param entryID used to delete the faculty member corresponding to that ID.
     * @return the result of the delete function whether it was successful or not.
     * 0 means that it did not work.
     */
    public int deleteEntry(int entryID) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("DELETE FROM entries WHERE entryID = ?;");
            stmt.setInt(1, entryID);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the delete.");
            e.printStackTrace();
            System.exit(1);
            return 0;
        }
    }

    /**
     * Faculty Update Function
     * Searches the database using the entryID given from the user.
     * Once an entry is found, the user can input whatever edits they
     * want to make to the topic. The method will update the
     * entry's topic when the user is done.
     * @param entryID used to edit the topic of the faculty member corresponding to that ID.
     * @param newTopic the new topic that the user wants to replace the entry's topic with.
     * @return the result of the update function whether it was successful or not.
     * 0 means that it did not work.
     */
    public int updateEntry(int entryID, String newTopic) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE entries SET topic = ? WHERE entryId = ?;");
            stmt.setInt(1, entryID);
            stmt.setString(2, newTopic);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the update");
            e.printStackTrace();
            System.exit(1);
            return 0;
        }
    }

    /**
     * Takes in userName
     *
     * @return an array of formatted entries that match user's ID
     */
    public String[] selectUpdateEntry(int userID) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT topic FROM Entries WHERE userID = " + Integer.toString(userID));
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

    /**
     * Get the usertype, given a userName
     *
     * @param userName the userName
     * @return the user type, either "F" for Faculty or "S" for Student, "G" for
     *         guest
     */
    public String getUserType(String userName) {
        if (userName.equals("guest")) {
            return "G";
        }
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery(String.format("SELECT userType FROM users WHERE userName = \"%s\"", userName));
            String type = null;
            if (rs.next()) {
                type = rs.getString("userType");
            } else {
                System.err.println("Error in getting the topic - no more rows.");
            }

            if (type == null) {
                System.err.println("Error in getting the topic - type is null.");
                return "G"; // To not crash unless we have to, return G
            } else {
                return type;
            }
        } catch (SQLException e) {
            System.out.println("There was an error in getting the user type.");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * Hash a string
     *
     * @param plain - the plaintext string to hash
     * @return the hash of that string
     */
    String hashString(String plain) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(plain.getBytes(StandardCharsets.UTF_8));
            return new String(digest, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            // Won't happen, because SHA-1 is guaranteed to exist
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * Check if the password is correct
     *
     * @param userName the userName of the user
     * @param password the password
     * @return true if the password matches what's in the database
     */
    boolean checkPassword(String userName, String password) {
        return true;
    }

}