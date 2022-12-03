
/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

//import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PresentationLayer {
    String userName = "";
    String userType = "G";
    boolean loggedIn = false;
    DataLayer dl = new DataLayer();

    public static Font myFontForOutput = new Font("Courier", Font.BOLD, 20);
      
      
      /// GUEST PORTAL GUI ///
      
     
     public void GuestBox() {
        // Student Box Frame Setup
        JFrame f = new JFrame("Guest Portal");
        f.setSize(400, 400);

      // Label
      JLabel label = new JLabel(" Guest Features");
      label.setBounds(120,50,150,30);
      f.add(label);



      // Search by Interests, returns Faculty that match current users interests
      JButton searchIntButton = new JButton("Search by Interests");
      searchIntButton.setBounds(90,80,175,50);
      f.add(searchIntButton);
            //  Search Interests Button Listening
            searchIntButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               
                  // Box with textarea of interest option + textbox for user input. Search button to search by what user inputs.
                     JFrame sframe = new JFrame("Search for Faculty Name by Interest ID");
                     sframe.setSize(400, 300);
                     
                     // Add Label, box, button
                     JLabel newLabel = new JLabel("Enter Interest ID to Search: ");
                     sframe.add(newLabel);
                     newLabel.setBounds(185,30,185,30);
                     
                     JTextField t = new JTextField(16);
                     sframe.add(t);
                     t.setBounds(185,60,180,30);

                     JButton b = new JButton("Search");
                     sframe.add(b);
                     b.setBounds(220,100,100,50);
                     
                        // Add Listener for search button
                        b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
               
                              // Action for search uses the textfield as a parameter
                              
                              // Result box will show the faculty with matching interest as user input

                                String output = dl.searchFaculty(Integer.parseInt(t.getText()));
                                ResultBox(output);
                        }
                        });

                        
                     // List the interests        
                     JLabel intLabel = new JLabel("Interest and ID's: ");
                     sframe.add(intLabel);
                     intLabel.setBounds(10,0,150,30);
                                 
                     JTextArea listInterests = new JTextArea("Java = 1\nAnthropology = 2\nEthics in Computing = 3\nCalculus = 4\nMobile Design = 5\nPython = 6\nBiochemical Engineering = 7\nBiology = 8\nArt = 9\nFilm/Animation = 10\n");
                     listInterests.setEditable(false);
                     listInterests.setBounds(10,30,160,250);
                     sframe.add(listInterests);
                     
                     
                     sframe.setLayout(null); // using no layout managers
                     sframe.setVisible(true); // making the frame visible
                     
                                         
               
               }
            });

      // Browse
      JButton browseButton = new JButton("Browse Entries");
      browseButton.setBounds(100,130,150,50);
      f.add(browseButton);
         //  Search UserID Button Listening
            browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               String result = dl.allEntries();
               ResultBox(result);
               
               }
            });
            
      // Exit Button
      JButton exitButton = new JButton("Exit");
      exitButton.setBounds(100,180,150,50);
      f.add(exitButton);
            //Exit Button Listening
            exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // Closing all connections to database
               System.out.println("\nClosing all connections to database...\n");
               dl.close();
               System.exit(0);
               }
            });

      f.setLayout(null); // using no layout managers
      f.setVisible(true); // making the frame visible
   }

    
    /// STUDENT PORTAL GUI ///
    
    
    public void StudentBox() {
        // Student Box Frame Setup
        JFrame f = new JFrame("Student Portal");
        f.setSize(400, 400);

      // Label
      JLabel label = new JLabel(" Student Features");
      label.setBounds(100,50,150,30);
      f.add(label);
      
      // Edit interests
      JButton editButton = new JButton("Edit Interests");
      editButton.setBounds(90,80,150,50);
      f.add(editButton);
            //  Edit Interest Listening
             editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               
                  // Box with textarea of interest option + textbox for user input. Search button to search by what user inputs.
                     JFrame sframe = new JFrame("Edit your Interest");
                     sframe.setSize(400, 300);
                     
                     // Add Label, box, button
                     JLabel newLabel = new JLabel("Enter your new Interest: ");
                     sframe.add(newLabel);
                     newLabel.setBounds(185,30,185,30);
                     
                     JTextField t = new JTextField(16);
                     sframe.add(t);
                     t.setBounds(185,60,180,30);

                     JButton b = new JButton("Update");
                     sframe.add(b);
                     b.setBounds(220,100,100,50);
                     
                        // Add Listener for search button
                        b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
               
                              // Action for search uses the textfield as a parameter
                              
                              // Result box will show the faculty with matching interest as user input

                                String output = dl.searchFaculty(Integer.parseInt(t.getText()));
                                ResultBox(output);
                        }
                        });

                        
                     // List the interests        
                     JLabel intLabel = new JLabel("Interest and ID's: ");
                     sframe.add(intLabel);
                     intLabel.setBounds(10,0,150,30);
                                 
                     JTextArea listInterests = new JTextArea("Java = 1\nAnthropology = 2\nEthics in Computing = 3\nCalculus = 4\nMobile Design = 5\nPython = 6\nBiochemical Engineering = 7\nBiology = 8\nArt = 9\nFilm/Animation = 10\n");
                     listInterests.setEditable(false);
                     listInterests.setBounds(10,30,160,250);
                     sframe.add(listInterests);
                     
                     
                     sframe.setLayout(null); // using no layout managers
                     sframe.setVisible(true); // making the frame visible
                     
                                         
               
               }
            });



      // Search by Interests, returns Faculty that match current users interests
      JButton searchIntButton = new JButton("Search by Interests");
      searchIntButton.setBounds(100,130,175,50);
      f.add(searchIntButton);
            //  Search Interests Button Listening
            searchIntButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               
                  // Box with textarea of interest option + textbox for user input. Search button to search by what user inputs.
                     JFrame sframe = new JFrame("Search for Faculty Name by Interest ID");
                     sframe.setSize(400, 300);
                     
                     // Add Label, box, button
                     JLabel newLabel = new JLabel("Enter Interest ID to Search: ");
                     sframe.add(newLabel);
                     newLabel.setBounds(185,30,185,30);
                     
                     JTextField t = new JTextField(16);
                     sframe.add(t);
                     t.setBounds(185,60,180,30);

                     JButton b = new JButton("Search");
                     sframe.add(b);
                     b.setBounds(220,100,100,50);
                     
                        // Add Listener for search button
                        b.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
               
                              // Action for search uses the textfield as a parameter
                              
                              // Result box will show the faculty with matching interest as user input

                                String output = dl.searchFaculty(Integer.parseInt(t.getText()));
                                ResultBox(output);
                        }
                        });

                        
                     // List the interests        
                     JLabel intLabel = new JLabel("Interest and ID's: ");
                     sframe.add(intLabel);
                     intLabel.setBounds(10,0,150,30);
                                 
                     JTextArea listInterests = new JTextArea("Java = 1\nAnthropology = 2\nEthics in Computing = 3\nCalculus = 4\nMobile Design = 5\nPython = 6\nBiochemical Engineering = 7\nBiology = 8\nArt = 9\nFilm/Animation = 10\n");
                     listInterests.setEditable(false);
                     listInterests.setBounds(10,30,160,250);
                     sframe.add(listInterests);
                     
                     
                     sframe.setLayout(null); // using no layout managers
                     sframe.setVisible(true); // making the frame visible
                     
                                         
               
               }
            });

      // Search Common Interest
      JButton searchCommonButton = new JButton("Common Interests");
      searchCommonButton.setBounds(100,180,150,50);
      f.add(searchCommonButton);
         //  Search UserID Button Listening
            searchCommonButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
                 
               //String result = dl.Method();
               //ResultBox(result);
               
               }
            });

      // Browse
      JButton browseButton = new JButton("Browse Entries");
      browseButton.setBounds(100,230,150,50);
      f.add(browseButton);
         //  Search UserID Button Listening
            browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               String result = dl.allEntries();
               ResultBox(result);
               
               }
            });
            
      // Exit Button
      JButton exitButton = new JButton("Exit");
      exitButton.setBounds(100,280,150,50);
      f.add(exitButton);
            //Exit Button Listening
            exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // Closing all connections to database
               System.out.println("\nClosing all connections to database...\n");
               dl.close();
               System.exit(0);
               }
            });

      f.setLayout(null); // using no layout managers
      f.setVisible(true); // making the frame visible
   }

    public void FacultyBox() {
        // Faculty Box Frame Setup
        JFrame f = new JFrame("Faculty Portal");
        f.setSize(600,400);

        // Label
        JLabel label = new JLabel(" Student/Guest Features");
        label.setBounds(125,50,150,30);
        f.add(label);
        // Edit interests
        JButton editButton = new JButton("Edit Interests");
        editButton.setBounds(125,80,150,50);
        f.add(editButton);
        // Search Interests
        JButton searchIntButton = new JButton("Search Interests");
        searchIntButton.setBounds(125,130,150,50);
        f.add(searchIntButton);
        // Search UserID
        JButton searchUserButton = new JButton("Search User ID");
        searchUserButton.setBounds(125,180,150,50);
        f.add(searchUserButton);
        // Browse
        JButton browseButton = new JButton("Browse Entries");
        browseButton.setBounds(125,230,150,50);
        f.add(browseButton);
        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(125,280,150,50);
        f.add(exitButton);


        // Faculty Buttons

        // Label
        JLabel label2 = new JLabel("Faculty Features");
        label2.setBounds(320,100,150,30);
        f.add(label2);
         
        // Add Entry Button
        JButton addEntry = new JButton("Add Entry");
        addEntry.setBounds(300,130,150,50);
        f.add(addEntry);
        
            addEntry.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               
               }
            });
            
        
        //  Update Entry Button
        JButton update = new JButton("Update Entry");
        update.setBounds(300,180,150,50);
        f.add(update);
        
            
            update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               
               }
            });
            
        
        // Delete Entry Button
        JButton delete = new JButton("Delete Entry");
        delete.setBounds(300,230,150,50);
        f.add(delete);
        
         
            delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
               // Action
               
               }
            });


        f.setLayout(null); // using no layout managers
        f.setVisible(true); // making the frame visible
    }
   
   
   

      // Results Box, preset object that show whatever comes out a query for any methods/button later on.
      public void ResultBox(String in) {
   
      String resultTest = new String(in);
      // Frame Creation
      JFrame f = new JFrame();
      f.setSize(600,600);
      // Text Area Creation
      JTextArea result = new JTextArea(resultTest);
      result.setEditable(false);
      result.setBounds(10,10,500,500);
      f.add(result);
      
      
      
   
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
        JTextField tfUser = new JTextField("Guest");
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
        return dl.checkPassword(userName, password);
    }

    /**
     * Constructor
     */
    public PresentationLayer() {
        System.out.println("Connecting to the database . . .");

        // // BOX TESTS
        // StudentBox();
        // FacultyBox();
        // ResultBox("hello");

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
        loggedIn = appLogin();
        if (loggedIn) {
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
                
                case "G":
                   
                    GuestBox();
                    break;
                default:
                    System.err.println("Invalid user type!");
                    break;
            }
        }
    } // End of Constructor

    public static void main(String[] args) {
        System.out.println("Group 4");

        new PresentationLayer(); // Create a new object. An Instantiation
    } // End of main method
} // End of Class