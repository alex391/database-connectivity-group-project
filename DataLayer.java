/*
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
import java.util.LinkedList;
import java.util.List;

public class DataLayer {
    private Connection conn;

    /**
     * Connect to the database and create a connection.
     * 
     * @return True if the connection is successful.
     *         Return false if otherwise.
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
     *         Return false if otherwise.
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
     * This function searches the entries for a shared interest using an interestID
     * that matches it.
     * The function will display all entries from the faculty member that shares
     * that interest.
     * 
     * @param interestID The interestID of the desired interest to search for.
     * @return All the entries of the faculty members that have that interest.
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
            System.out.println("There was an error in the searchEntries select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function returns all the Entries stored in the Entries database.
     * 
     * @return All the Entries stored in the database.
     */
    public String allEntries() {
        StringBuilder result = new StringBuilder();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT topic, GROUP_CONCAT(Users.lastName, ', ', Users.firstName SEPARATOR ' | ') AS 'name', GROUP_CONCAT(Faculty.email SEPARATOR ' | ') AS 'email' FROM entries JOIN Users USING(userID) JOIN Faculty USING(userID) GROUP BY topic;");
            result.append("All Entries:\n\n");
            while (rs.next()) {
                result.append("Topic:       " + rs.getString("topic") + "\n");
                result.append("Author:     " + rs.getString("name") + "\n");
                result.append("Contact:   " + rs.getString("email") + "\n\n");
            }
        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * This function returns all the Interests stored in the Interests database.
     * 
     * @return All the Interests stored in the database.
     */
    public String allInterests() {
        StringBuilder result = new StringBuilder();
        try {
            Statement interestStatement = conn.createStatement();
            ResultSet interestResult = interestStatement.executeQuery("SELECT interestID FROM interests;");
            while (interestResult.next()) {
                // interestID - interest...
                result.append(interestResult.getString("interestID")).append(" - ");
                Statement usersStatement = conn.createStatement();
                ResultSet usersResult = usersStatement.executeQuery(
                        "SELECT interest FROM interests WHERE interestID = " + interestResult.getInt("interestID"));
                while (usersResult.next()) {
                    result.append(usersResult.getString("interest"));
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

    /**
     * This function returns all the Interests stored in the Interests database that
     * matches the current user
     * 
     * @return All the Interests stored in the database.
     */
    public String allUserInterests(String user) {
        StringBuilder result = new StringBuilder();
        try {
            Statement interestStatement = conn.createStatement();
            ResultSet interestResult = interestStatement.executeQuery(
                    "SELECT interests.interestID, interests.interest FROM userinterests JOIN interests USING(interestID) WHERE userID = "
                            + user + " ORDER BY interestID;");
            while (interestResult.next()) {
                // interestID - interest...
                result.append(interestResult.getString("interestID")).append(" - ")
                        .append(interestResult.getString("interest"));
                result.append("\n");
            }

        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * This function returns the email(s) of the faculty member(s) being searched.
     * 
     * @param userID The userID of the faculty member or members.
     * @return The email(s) of the faculty member(s) being searched.
     */
    public String StudentInterests(int userID) {
        String result = "";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt
                    .executeQuery(String.format("SELECT interestID FROM UserInterests WHERE USERID= \"%d\" ;", userID));
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
     * This function searches the faculty for a shared interest using an interestID
     * that matches it.
     * The function will return the emails of every faculty member that shares that
     * interest.
     * 
     * @param interestID The interestID of the desired interest to search for.
     * @return All the emails of every faculty member that shares that interest.
     */

    public String searchFaculty(int interestID) {
        String result = "";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT faculty.email AS 'email', CONCAT_WS(', ', Users.lastName, Users.firstName) AS name, CONCAT_WS('-', faculty.buildNumber, faculty.officeNumber) AS 'officeLoc' FROM faculty JOIN userinterests USING(userID) JOIN Users USING (userID) WHERE interestID =  \"%d\";",
                    interestID));
            result += "Faculty with the specified interest:\n\n";
            while (rs.next()) {
                result += "Name:   " + rs.getString("name") + "\n";
                result += "Email:    " + rs.getString("email") + "\n";
                result += "Office#:  " + rs.getString("officeLoc") + "\n\n";
            }
        } catch (SQLException e) {
            System.out.println("There was an error in the select.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This function searches the students for a shared interest using an interestID
     * that matches it.
     * The function will return the info of every student that shares that
     * interest.
     * 
     * @param interestID The interestID of the desired interest to search for.
     * @return All the emails of every student of that interest.
     */

    public String searchStudent(int interestID) {
        String result = "";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String.format(
                    "SELECT CONCAT_WS(', ', Users.lastName, Users.firstName) AS name FROM Users JOIN userinterests USING(userID) WHERE userType = 'S' AND interestID =  \"%d\";",
                    interestID));
            result += "Students With Specified Interest:\n\n";
            while (rs.next()) {
                result += "Name:  " + rs.getString("name") + "\n";
                result += "Email:  Our database doesn't have anywhere to store non-faculty emails and we realized too late. :(\n\n";
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
     * 
     * @param userID The userID to use for the new entry in the database.
     * @param topic  The topic to use for the new entry in the database.
     * @return The result of the add function whether it was successful or not.
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
     * This function allows the user to add their interests.
     * 
     * @param userID     The userID that will have interests added.
     * @param interestID The interestID to be added.
     * @return The result of the add function whether it was successful or not.
     */
    public int addInterest(int userID, int interestID) {
        int result = 0;
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO userinterests(userID, interestID) VALUES (?, ?);");
            stmt.setInt(1, userID);
            stmt.setInt(2, interestID);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the insert.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return (result);
    }

    /**
     * This function allows the user to delete their interests.
     * 
     * @param userID     The userID that will have interests deleted.
     * @param interestID The interestID to be deleted.
     * @return The result of the delete function whether it was successful or not.
     */
    public int deleteInterest(int userID, int interestID) {
        int result = 0;
        try {
            PreparedStatement stmt;
            stmt = conn.prepareStatement("DELETE FROM userinterests WHERE userID = ? AND interestID = ?;");
            stmt.setInt(1, userID);
            stmt.setInt(2, interestID);
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("There was an error in the delete.");
            System.out.println("Error = " + e);
            e.printStackTrace();
        }
        return (result);
    }

    /**
     * This function deletes a faculty member by taking in their entryID.
     * 
     * @param entryID Used to delete the faculty member corresponding to that ID.
     * @return The result of the delete function whether it was successful or not.
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
     * 
     * @param entryID  Used to edit the topic of the faculty member corresponding to
     *                 that ID.
     * @param newTopic The new topic that the user wants to replace the entry's
     *                 topic with.
     * @return The result of the update function whether it was successful or not.
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
     * 
     * @return An array of formatted topics that match user's ID.
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
     * 
     * @param userName The userName to be checked for a userType.
     * @return The userType, either "F" for
     *         Faculty, "S" for Student, or "G" for Guest.
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
                System.err.println("Error in getting the user type - no more rows.");
            }

            if (type == null) {
                System.err.println("Error in getting the user type - type is null.");
                return "G"; // To not crash unless we have to, return G.
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

    /**
     * This function returns the userID of the userName entered. Useful for when you
     * know a username, but not their userID.
     * 
     * @param userName The userName to get the userID of.
     * @return The userID of that belongs to the userName.
     */
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
                return 0; // To not crash unless we have to, return 0.
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
     * This function returns every other user that shares the same interests.
     * Get all the other users that have the same interests.
     *
     * @param userID The userID of the person who is currently searching.
     * @return A list of the other users.
     */
    public List<String> getCommonInterests(int userID) {
        try {

            // First, find out what interests this user has.
            Statement statement = conn.createStatement();
            ResultSet rs = statement
                    .executeQuery(String.format("SELECT interestID FROM userinterests WHERE userID = \"%d\";", userID));
            List<Integer> usersInterests = new LinkedList<>();
            while (rs.next()) {
                usersInterests.add(rs.getInt("interestID"));
            }
            List<String> commonUsers = new LinkedList<>();

            // Secondly, find the other users that have that same interest.
            for (int interestID : usersInterests) {
                Statement interestStatement = conn.createStatement();
                ResultSet intrestsResultSet = interestStatement
                        .executeQuery(String.format(
                                "SELECT firstName, lastName, userType, interest FROM users JOIN userinterests u on users.userID = u.userID JOIN interests i on i.interestID = u.interestID WHERE i.interestID = \"%d\";",
                                interestID));
                while (intrestsResultSet.next()) {
                    String userInfo = intrestsResultSet.getString("firstName") +
                            " " +
                            intrestsResultSet.getString("lastName") +
                            " - " +
                            intrestsResultSet.getString("interest") +
                            " - " +
                            longUserType(intrestsResultSet.getString("userType"));
                    commonUsers.add(userInfo);
                }
            }
            return commonUsers;
        } catch (SQLException e) {
            System.out.println("There was an error in checking the interests.");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * This function converts the userType CHAR into a verbose String name instead.
     * Convert the user type code to a longer more human-readable string.
     *
     * @param shortUserType "F", "S", or "G".
     * @return Faculty, Student, or Guest.
     */
    private String longUserType(String shortUserType) {
        switch (shortUserType) {
            case "F":
                return "Faculty";
            case "S":
                return "Student";
            case "G":
                return "Guest";
            default:
                return "Unknown user type";
        }
    }

    /**
     * This function hashes a string with SHA-1.
     *
     * @param plainText The plaintext string that is going to be converted to hash.
     * @return The hash of that string.
     */
    String hashString(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(plainText.getBytes(StandardCharsets.UTF_8));
            BigInteger bi = new BigInteger(1, bytes); // Convert to a hex string. Thanks to
                                                      // https://stackoverflow.com/a/943963/12203444
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
     * @param userName The userName of the user.
     * @param password The password of the user.
     * @return True if the password matches what's in the database.
     *         False means that it did not match.
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

    /**
     * This function returns true or false based on if the given user does (true) or
     * does not (false) own the entry.
     * 
     * Used for confirmation so a user cannot delete another user's entry.
     * 
     * @param userID  Of current user.
     * @param entryID Of entry in question.
     * @return True or False whether the user owns or does not own the entry.
     */
    boolean checkOwnership(int userID, int entryID) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(String
                    .format("SELECT * FROM entries WHERE userID = \"%d\" AND entryID = \"%d\";", userID, entryID));

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("There was an error in validating ownership.");
            e.printStackTrace();
            System.exit(1);
            return false;
        }
    }
}