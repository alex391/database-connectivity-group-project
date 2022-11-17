
/*
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Project 01 StudentFaculty project DataLayer
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Console;

public class DataLayer {
    private Connection conn;
    private Console console = System.console();

    /**
     * Connect to the database
     * 
     * @return true if the connection is successful, else false
     */
    public boolean connect() {
        // get a connection

        /*
         * url line below at the end identifies the database name
         * /* Define Data Source
         */
        String url = "jdbc:mysql://localhost/studentfaculty";
        url = url + "?serverTimezone=UTC"; // added 9/12

        String user = new String();
        String password;
        System.out.print("MySQL username (default root): ");
        user = console.readLine();
        if (user.equalsIgnoreCase("")) {
            user = "root";
        }
        password = new String(console.readPassword("Password (Default is \"student\") (typing hidden):"));

        // set the default password to "student"
        if (password.equalsIgnoreCase("")) {
            password = "student";
        }

        // 2) Create a connection
        try {
            // This statement below returns connections to the URL.
            // SQLException will be thrown, if database access occurs or url is null.

            conn = DriverManager.getConnection(url, user, password);
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
            System.out.println("There was an error in the delete");
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    

    /*
     * public string searchUsers(interest String, userType int){
     * usertype is 1=student, 2=faculty, 3=both
     * 
     * Query FName, LName,(where interest like interest) interests of person,
     * topictitle,
     * send result set to string]
     * 
     * return string
     * }
     */
}
