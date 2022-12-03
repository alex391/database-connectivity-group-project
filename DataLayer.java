/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {
    private Connection conn;
    /**
     * Connect to the database and create a connection.
     * 
     * @return True if the connection is successful.
     * Return false if otherwise.
     */
    public boolean connect(String userName, String password) {
        // 1) Get a connection
        /*
         * Url line below at the end identifies the database name
         * Define Data Source
         */
        String url = "jdbc:mysql://localhost/studentfaculty";
        url = url + "?serverTimezone=UTC";

        // 2) Create a connection
        try {
            // This statement below returns connections to the URL.
            // SQLException will be thrown, if database access occurs or url is null.
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("There was a connection error.");
            System.exit(1);
            return false;
        }
        return true;
    }

    /**
     * Close the database connection.
     * 
     * @return True if closing the connection is successful.
     * Return false if otherwise.
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

    /**
     * This function searches the entries for a shared interest using an interestID that matches it.
     * The function will display all entries from the faculty member that shares that interest.
     * @param interestID The interestID of the desired interest to search for.
     * @return all the entries of the faculty members that have that interest.
     */
    public int searchEntries(int interestID) {
        int result = 0;
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement(
                    "SELECT entries.topic AS \"email\" ,interestID From userinterests JOIN entries USING(userID) WHERE entries.userID = userinterests.userID AND interestID = ? GROUP BY entries.userID;");
            stmt.setInt(1, interestID);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result;
    }

    public String allEntries() {
        StringBuilder result= new StringBuilder();
        try {
            Statement topicStatement = conn.createStatement();
            ResultSet topicResult = topicStatement.executeQuery("SELECT topic, userID  From entries GROUP BY topic;");
            while (topicResult.next()) {
                // topic - firstName lastName, firstName2 lastName2, ...
                result.append(topicResult.getString("topic")).append(" - ");
                Statement usersStatement = conn.createStatement();
                ResultSet usersResult = usersStatement.executeQuery(
                    "SELECT firstName, lastName FROM users WHERE userID = " + topicResult.getInt("userID")
                );
                while(usersResult.next()) {
                    result.append(usersResult.getString("firstName")).append(" ");
                    result.append(usersResult.getString("lastName"));
                    if (!usersResult.isLast()) {
                        result.append(", ");
                    }
                }
                result.append("\n");
            }
        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result.toString();
    }

    public String StudentInterests(int UserID) {
        String result="";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT interestID FROM UserInterests WHERE USERID= \"%d\" ;",UserID));   
            while (rs.next()) {
            result += rs.getString("email") + "\n";
            
            }
        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function searches the faculty for a shared interest using an interestID that matches it.
     * The function will return the emails of every faculty member that shares that interest.
     * @param interestID The interestID of the desired interest to search for.
     * @return all the emails of every faculty member that shares that interest.
     */

    public String searchFaculty(int interestID) {
        String result="";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT faculty.email AS 'email',CONCAT_WS(',',Users.lastName,Users.firstName) AS name , faculty.officeNumber,faculty.buildNumber AS 'Office Number' FROM faculty JOIN userinterests USING(userID) JOIN Users USING (userID)WHERE faculty.userID = userID AND interestID =  \"%d\" GROUP BY faculty.userID;",interestID));   
            while (rs.next()) {
            result += rs.getString("email") + "\n";
            
            }
        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result;
    }



    /**
     * This function adds an entry into the database containing userID and a topic.
     * The system already knows the userID due to the sign in.
     * The entryID is assigned automatically.
     * @param userID The userID to use for the new entry in the database.
     * @param topic The topic to use for the new entry in the database.
     * @return the result of the add function whether it was successful or not.
     */
    public int addEntry(int userID, String topic, int interestID) {
        int result = 0;
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO entries(userID, topic, interestID) VALUES (?, ?, ?);");
            stmt.setInt(1, userID);
            stmt.setString(2, topic);
            stmt.setInt(3, interestID);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the insert.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return (result);
    }

    /**
     * This function deletes a faculty member by taking in their entryID.
     * @param entryID used to delete the faculty member corresponding to that ID.
     * @return the result of the delete function whether it was successful or not.
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
     * This function searches the database using the entryID given from the user.
     * Once an entry is found, the user can input whatever edits they
     * want to make to the topic. 
     * The method will update the entry's topic when the user is done.
     * @param entryID used to edit the topic of the faculty member corresponding to that ID.
     * @param newTopic the new topic that the user wants to replace the entry's topic with.
     * @return the result of the update function whether it was successful or not.
     */
    public int updateEntry(int entryID, String newTopic) {
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement("UPDATE entries SET topic = ? WHERE entryId = ?;");
            stmt.setString(1, newTopic);
            stmt.setInt(2, entryID);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the update.");
            e.printStackTrace();
            System.exit(1);
            return 0;
        }
    }

    /**
     * This function selects all the topics from 
     * an entry where the userID matches.
     * @return an array of formatted topics that match user's ID
     */
    public String[] selectTopicsFromEntry(int userID) {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery("SELECT topic FROM Entries WHERE userID = " + userID);
            List<String> arrayTopics = new ArrayList<>();
            while (rs.next()) {
                arrayTopics.add(rs.getString("topic"));
            }
            return arrayTopics.toArray(new String[0]);
        } catch (SQLException e) {
            System.out.println("There was an error in selecting topic(s) from the entryID given.");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * This function gets the userType using the given userName.
     * @param userName the userName to be checked for a userType.
     * @return the userType, either "F" for 
     * Faculty, "S" for Student, or "G" for Guest.
     */
    public String getUserType(String userName) {
        if (userName.equalsIgnoreCase("guest")) {
            return "G";
        }
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery(String.format("SELECT userType FROM users WHERE userName = \"%s\";", userName));
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
            System.err.println("There was an error in getting/selecting the user type.");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }



    public int getUserID(String userName) {
        if (userName.equals("guest")) {
            return 0;
        }
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery(String.format("SELECT userID FROM users WHERE userName = \"%s\";", userName));
            int type = 0;
            if (rs.next()) {
                type = rs.getInt("userID");
            } else {
                System.err.println("Error in getting the topic - no more rows.");
            }

            if (type == 0) {
                System.err.println("Error in getting the topic - type is null.");
                return 0; // To not crash unless we have to, return G
            } else {
                return type;
            }
        } catch (SQLException e) {
            System.err.println("There was an error in getting/selecting the user type.");
            e.printStackTrace();
            System.exit(1);
            return 0;
        }
    }

    /**
     * This function hashes a string and converts it using a SHA-1 hash.
     *
     * @param plainText The plaintext string that is going to be converted to hash.
     * @return the hash of that string.
     */
    String hashString(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(plainText.getBytes(StandardCharsets.UTF_8));
            BigInteger bi = new BigInteger(1, bytes); // Convert to a hex string. Thanks to https://stackoverflow.com/a/943963/12203444
            return String.format("%0" + (bytes.length << 1) + "x", bi);
        } catch (NoSuchAlgorithmException e) {
            // Won't happen, because SHA-1 is guaranteed to exist.
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * This function checks if the password is correct
     * and matches what's in the database.
     *
     * @param userName the userName of the user.
     * @param password the password of the user.
     * @return true if the password matches what's in the database.
     * False means that it did not match.
     */
    boolean checkPassword(String userName, String password) {
        password = hashString(password);
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery(String.format("SELECT password FROM users WHERE userName = \"%s\";", userName));
            String correctPassword = null;
            if (rs.next()) {
                correctPassword = rs.getString("password");
            } else {
                System.err.println("Error in checking the password - no more rows.");
            }

            if (correctPassword == null) {
                System.err.println("Error in checking the password - type is null.");
                return false;
            } else {
                return password.equals(correctPassword);
            }
        } catch (SQLException e) {
            System.out.println("There was an error in checking the password.");
            e.printStackTrace();
            System.exit(1);
            return false;
        }
    }
}