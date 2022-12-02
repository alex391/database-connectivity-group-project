
/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

//import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class PresentationLayer {
    String userName = "";
    String userType = "G";
    boolean loggedIn = false;
    DataLayer dl = new DataLayer();

    public static Font myFontForOutput = new Font("Courier", Font.BOLD, 20);

    public void StudentBox() {
        // Student Box Frame Setup
        JFrame f = new JFrame();
        f.setSize(400, 400);

        // Label
        JLabel label = new JLabel(" Student/Guest Features");
        label.setBounds(100, 50, 150, 30);
        f.add(label);
        // Edit interests
        JButton editButton = new JButton("Edit Interests");
        editButton.setBounds(100, 80, 150, 50);
        f.add(editButton);
        // Search Interests
        JButton searchIntButton = new JButton("Search Interests");
        searchIntButton.setBounds(100, 130, 150, 50);
        f.add(searchIntButton);
        // Search UserID
        JButton searchUserButton = new JButton("Search User ID");
        searchUserButton.setBounds(100, 180, 150, 50);
        f.add(searchUserButton);
        // Browse
        JButton browseButton = new JButton("Browse Entries");
        browseButton.setBounds(100, 230, 150, 50);
        f.add(browseButton);
        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(100, 280, 150, 50);
        f.add(exitButton);

        f.setLayout(null); // using no layout managers
        f.setVisible(true); // making the frame visible
    }

    public void FacultyBox() {
        // Faculty Box Frame Setup
        JFrame f = new JFrame();
        f.setSize(600, 400);

        // Label
        JLabel label = new JLabel(" Student/Guest Features");
        label.setBounds(125, 50, 150, 30);
        f.add(label);
        // Edit interests
        JButton editButton = new JButton("Edit Interests");
        editButton.setBounds(125, 80, 150, 50);
        f.add(editButton);
        // Search Interests
        JButton searchIntButton = new JButton("Search Interests");
        searchIntButton.setBounds(125, 130, 150, 50);
        f.add(searchIntButton);
        // Search UserID
        JButton searchUserButton = new JButton("Search User ID");
        searchUserButton.setBounds(125, 180, 150, 50);
        f.add(searchUserButton);
        // Browse
        JButton browseButton = new JButton("Browse Entries");
        browseButton.setBounds(125, 230, 150, 50);
        f.add(browseButton);
        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(125, 280, 150, 50);
        f.add(exitButton);

        // Faculty Buttons

        // Label
        JLabel label2 = new JLabel(" Faculty Features");
        label2.setBounds(320, 100, 150, 30);
        f.add(label2);
        JButton addEntry = new JButton("Add Entry");
        addEntry.setBounds(300, 130, 150, 50);
        f.add(addEntry);
        JButton update = new JButton("Update Entry");
        update.setBounds(300, 180, 150, 50);
        f.add(update);
        JButton delete = new JButton("Delete Entry");
        delete.setBounds(300, 230, 150, 50);
        f.add(delete);
        f.setLayout(null); // using no layout managers
        f.setVisible(true); // making the frame visible
    }

    /**
     * User Login
     * Changes global userType and userName attributes
     * 
     * @return true when log in matches credentials provided
     */
    public boolean appLogin() {
        JPanel Inputbox = new JPanel(new GridLayout(3, 2));
        JLabel lblUser = new JLabel("Username -> ");
        JLabel lblPassword = new JLabel("Password -> ");
        JTextField tfUser = new JTextField("guest");
        JTextField tfPassword = new JPasswordField("");

        Inputbox.add(lblUser);
        Inputbox.add(tfUser);
        Inputbox.add(lblPassword);
        Inputbox.add(tfPassword);

        lblUser.setFont(myFontForOutput);
        tfUser.setFont(myFontForOutput);
        tfUser.setForeground(Color.BLUE);
        lblPassword.setFont(myFontForOutput);
        tfPassword.setFont(myFontForOutput);
        tfPassword.setForeground(Color.BLUE);

        JOptionPane.showMessageDialog(null, Inputbox,
                "Default password is \"guest\" - This logs into interest search application",
                JOptionPane.QUESTION_MESSAGE);

        userName = tfUser.getText();
        userType = dl.getUserType(userName);
        String password = new String();
        String passwordInput = new String();
        passwordInput = tfPassword.getText();

        // set the default password to "student"
        if (passwordInput.equalsIgnoreCase("")) {
            password = "guest"; // CHANGE TO guest
        } else {
            password = passwordInput;
        }

        // returns true false
        loggedIn = dl.checkPassword(userName, password);
        return loggedIn;
    }

    /**
     * Constructor
     */
    public PresentationLayer() {
        System.out.println("Connecting to the database . . .");

        // // BOX TESTS
        // StudentBox();
        // FacultyBox();

        JPanel Inputbox = new JPanel(new GridLayout(3, 2));
        JLabel lblUser = new JLabel("Username -> ");
        JLabel lblPassword = new JLabel("Password -> ");
        JTextField tfUser = new JTextField("root");
        // JTextField tfPassword = new JTextField("");
        JTextField tfPassword = new JPasswordField("");

        Inputbox.add(lblUser);
        Inputbox.add(tfUser);
        Inputbox.add(lblPassword);
        Inputbox.add(tfPassword);

        lblUser.setFont(myFontForOutput);
        tfUser.setFont(myFontForOutput);
        tfUser.setForeground(Color.BLUE);
        lblPassword.setFont(myFontForOutput);
        tfPassword.setFont(myFontForOutput);
        tfPassword.setForeground(Color.BLUE);

        JOptionPane.showMessageDialog(null, Inputbox,
                "Default password is \"student\" - This logs into system studentfaculty DB",
                JOptionPane.QUESTION_MESSAGE);

        String userName = tfUser.getText();
        String password = new String();
        String passwordInput = new String();
        passwordInput = tfPassword.getText();

        // set the default password to "student"
        if (passwordInput.equalsIgnoreCase("")) {
            password = "student"; // CHANGE TO STUDENT
        } else {
            password = passwordInput;
        }

        dl.connect(userName, password); // Call DataLayer

        // case Student v Faculty
        appLogin();
        while (loggedIn) {
            System.out.println("before switch case");
            switch (userType) {
                case "F": // Faculty
                    // loop faculty
                    // Who is logged in somewhere
                    // exit button
                    // options to
                    // edit interests
                    // search by interests
                    // search by userID
                    // or browse entries
                    // entries
                    // add
                    // update
                    // delete
                    FacultyBox();
                    break;

                case "S": // Student
                    StudentBox();
                    break;
                case "G": // Guest - currently both students and guests are the same.
                    // loop Student/guest
                    // Who is logged in somewhere
                    // exit button
                    // options to
                    // edit interests
                    // search by interests
                    // search by userID
                    // or browse entries
                    StudentBox();
                    break;
                default:
                    System.err.println("Invalid user type!");
                    break;
            }
        }
        // Closing all connections to database
        System.out.println("\nClosing all connections to database...\n");
        dl.close();

        // End Of Job data - EOJ routines
        java.util.Date today = new java.util.Date();
        System.out.println("\nProgram terminated @ " + today);
    } // End of Constructor

    public static void main(String[] args) {
        System.out.println("Group 4");

        new PresentationLayer(); // Create a new object. An Instantiation
        System.out.println("EOJ");
    } // End of main method
} // End of Class