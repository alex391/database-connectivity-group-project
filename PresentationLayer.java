
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
   int UserID;
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
      label.setBounds(120, 50, 150, 30);
      f.add(label);

      // Search by Interests, returns Faculty that match current users interests
      JButton searchIntButton = new JButton("Search Faculty by Interests");
      searchIntButton.setBounds(77, 80, 200, 50);
      f.add(searchIntButton);
      // Search Interests Button Listening
      searchIntButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Search for Faculty Name by Interest ID");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter The Number of Your Interest ID to Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Search");
            sframe.add(b);
            b.setBounds(220, 100, 100, 50);

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
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
         }
      });

      // Browse
      JButton browseButton = new JButton("Browse Entries");
      browseButton.setBounds(100, 130, 150, 50);
      f.add(browseButton);
      // Search UserID Button Listening
      browseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action
            String result = dl.allEntries();
            ResultBox(result);

         }
      });

      // Exit Button
      JButton exitButton = new JButton("Exit");
      exitButton.setBounds(100, 180, 150, 50);
      f.add(exitButton);
      // Exit Button Listening
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
      label.setBounds(122, 50, 150, 30);
      f.add(label);

      // Edit interests
      JButton editButton = new JButton("Edit Your Interests");
      editButton.setBounds(100, 80, 175, 50);
      f.add(editButton);
      // Edit Interest Listening
      editButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Edit your Interest");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter your new Interest: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Update");
            sframe.add(b);
            b.setBounds(220, 100, 100, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for search uses the textfield as a parameter

                  // Result box will show the faculty with matching interest as user input

                  dl.addInterest(dl.getUserID(userName), Integer.parseInt(t.getText()));
                  ResultBox("Interest Added");
               }
            });

            // List the interests
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
         }
      });

      // Search by Interests, returns Faculty that match current users interests
      JButton searchIntButton = new JButton("Search Faculty by Interests");
      searchIntButton.setBounds(90, 130, 200, 50);
      f.add(searchIntButton);
      // Search Interests Button Listening
      searchIntButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Search for Faculty Name by Interest ID");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter The Number of Your Interest ID to Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Search");
            sframe.add(b);
            b.setBounds(220, 100, 100, 50);

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
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
         }
      });

      // Search Common Interest
      JButton searchCommonButton = new JButton("Common Interests with Faculty");
      searchCommonButton.setBounds(80, 180, 220, 50);
      f.add(searchCommonButton);
      // Search UserID Button Listening
      searchCommonButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Common Interests");
            sframe.setSize(400, 300);

            StringBuilder resultBuilder = new StringBuilder();
            for (String user : dl.getCommonInterests(dl.getUserID(userName))) {
               resultBuilder.append(resultBuilder);
               resultBuilder.append(" ");
            }
            String result = resultBuilder.toString() ; //name and email of these users, and what intrest they had in common
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible

         }
      });

      // Browse
      JButton browseButton = new JButton("Browse Entries");
      browseButton.setBounds(110, 230, 150, 50);
      f.add(browseButton);
      // Search UserID Button Listening
      browseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action
            String result = dl.allEntries();
            ResultBox(result);

         }
      });

      // Exit Button
      JButton exitButton = new JButton("Exit");
      exitButton.setBounds(110, 280, 150, 50);
      f.add(exitButton);
      // Exit Button Listening
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
      f.setSize(600, 400);

      // Label
      JLabel label = new JLabel(" Student Features");
      label.setBounds(92, 50, 170, 30);
      f.add(label);

      // Edit interests
      JButton editButton = new JButton("Edit Your Interests");
      editButton.setBounds(80, 80, 150, 50);
      f.add(editButton);

      // Edit Interest Listening
      editButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Edit your Interest");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter your new Interest: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Update");
            sframe.add(b);
            b.setBounds(220, 100, 100, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for search uses the textfield as a parameter

                  // Result box will show the faculty with matching interest as user input

                  dl.addInterest(dl.getUserID(userName), Integer.parseInt(t.getText()));
                  ResultBox("Interest Added");
               }
            });

            // List the interests
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
         }
      });

      // Search Interests
      JButton searchIntButton = new JButton("Search Faculty by Interests");
      searchIntButton.setBounds(50, 130, 210, 50);
      f.add(searchIntButton);

      // Search Interests Button Listening
      searchIntButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Search for Faculty Name by Interest ID");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter The Number of Your Interest ID to Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Search");
            sframe.add(b);
            b.setBounds(220, 100, 100, 50);

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
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
         }
      });

      // Search Commonalities
      JButton searchCommonButton = new JButton("Faculty with Common Interests");
      searchCommonButton.setBounds(40, 180, 230, 50);
      f.add(searchCommonButton);

      searchCommonButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // String result = dl.Method();
            // ResultBox(result);

         }
      });

      // Browse
      JButton browseButton = new JButton("Browse Entries");
      browseButton.setBounds(80, 230, 150, 50);
      f.add(browseButton);

      // Search UserID Button Listening
      browseButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action
            String result = dl.allEntries();
            ResultBox(result);

         }
      });

      // Exit Button
      JButton exitButton = new JButton("Exit");
      exitButton.setBounds(80, 280, 150, 50);
      f.add(exitButton);

      // Exit Button Listening
      exitButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Closing all connections to database
            System.out.println("\nClosing all connections to database...\n");
            dl.close();
            System.exit(0);
         }
      });

      // Faculty Buttons

      // Label
      JLabel label2 = new JLabel("Faculty Features");
      label2.setBounds(380, 100, 150, 30);
      f.add(label2);

      // Add Entry Button
      JButton addEntry = new JButton("Add Entry");
      addEntry.setBounds(360, 130, 150, 50);
      f.add(addEntry);

      addEntry.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Action
            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("New Entry");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Paste Abstract:");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JLabel newLabel2 = new JLabel("Interest ID:");
            sframe.add(newLabel2);
            newLabel2.setBounds(185, 100, 185, 30);

            JTextField interest = new JTextField(16);
            sframe.add(interest);
            interest.setBounds(185, 140, 180, 30);

            JButton b = new JButton("Add Entry");
            sframe.add(b);
            b.setBounds(220, 180, 180, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for new entry uses the textfield as a parameter

                  // Result box will show success message if added

                  dl.addEntry(dl.getUserID(userName), t.getText(), Integer.parseInt(interest.getText()));
                  ResultBox("Entry Added");

               }
            });
            sframe.setLayout(null); // using no layout managers
            sframe.setVisible(true); // making the frame visible
            
            // List the interests
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible


         }
      });

      // Update Entry Button
      JButton update = new JButton("Update Entry");
      update.setBounds(360, 180, 150, 50);
      f.add(update);

      update.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action
            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Update Entry");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("New Topic:");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JLabel newLabel2 = new JLabel("Entry ID:");
            sframe.add(newLabel2);
            newLabel2.setBounds(185, 100, 185, 30);

            JTextField entryID = new JTextField(16);
            sframe.add(entryID);
            entryID.setBounds(185, 140, 180, 30);

            JButton b = new JButton("Update Entry");
            sframe.add(b);
            b.setBounds(220, 180, 180, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for new entry uses the textfield as a parameter

                  // Result box will show success message if added

                  dl.updateEntry(Integer.parseInt(entryID.getText()), t.getText());
                  ResultBox("Entry Updated");

               }
            });
            sframe.setLayout(null); // using no layout managers
            sframe.setVisible(true); // making the frame visible
            
            

         }
         
         
      });

      // Delete Entry Button
      JButton delete = new JButton("Delete Entry");
      delete.setBounds(360, 230, 150, 50);
      f.add(delete);

      delete.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action
            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Delete Entry");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Entry ID:");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Delete Entry");
            sframe.add(b);
            b.setBounds(220, 180, 180, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for new entry uses the textfield as a parameter

                  // Result box will show success message if added

                  dl.deleteEntry(Integer.parseInt(t.getText()));
                  ResultBox("Entry Deleted");

               }
            });
            sframe.setLayout(null); // using no layout managers
            sframe.setVisible(true); // making the frame visible
         }
      });

      f.setLayout(null); // using no layout managers
      f.setVisible(true); // making the frame visible
      
      
      
      // EDIT THIS METHOD to search for "s"
      
       // Search for Students Interests
      JButton searchStudButton = new JButton("Search Students by Interests");
      searchStudButton.setBounds(330, 280, 210, 50);
      f.add(searchStudButton);

      // Search Interests Button Listening
      searchStudButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Search for Student by Interest ID");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter The Number of the Interest ID to Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 185, 30);

            JTextField t = new JTextField(16);
            sframe.add(t);
            t.setBounds(185, 60, 180, 30);

            JButton b = new JButton("Search");
            sframe.add(b);
            b.setBounds(220, 100, 100, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {


                                
                  String output = dl.searchStudent(Integer.parseInt(t.getText()));
                  ResultBox(output);
               }
            });

            // List the interests
            JLabel intLabel = new JLabel("Interests and ID's: ");
            sframe.add(intLabel);
            intLabel.setBounds(10, 0, 150, 30);

            String result = dl.allInterests();
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 170, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
         }
      });

   }

   // Results Box, preset object that show whatever comes out a query for any
   // methods/button later on.
   public void ResultBox(String in) {

      String resultTest = new String(in);
      // Frame Creation
      JFrame f = new JFrame();
      f.setSize(600, 600);
      // Text Area Creation
      JTextArea result = new JTextArea(resultTest);
      result.setEditable(false);
      result.setBounds(10, 10, 500, 500);

      JScrollPane scroll; 
      scroll = new JScrollPane(result);

      f.getContentPane().add(scroll);
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
      JTextField tfUser = new JTextField("");
      JTextField tfPassword = new JPasswordField("");
      JButton b = new JButton("Guest");// Guest Button
  

      Inputbox.add(lblUser);
      Inputbox.add(tfUser);
      Inputbox.add(lblPassword);
      Inputbox.add(tfPassword);
      Inputbox.add(b); // Guest Button
      
      // Guest Listen
      b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  
                  tfUser.setText("Guest");
                  tfPassword.setText("guest");

               }
            });

      lblUser.setFont(myFontForOutput);
      tfUser.setFont(myFontForOutput);
      tfUser.setForeground(Color.BLUE);
      lblPassword.setFont(myFontForOutput);
      tfPassword.setFont(myFontForOutput);
      tfPassword.setForeground(Color.BLUE);

      JOptionPane.showMessageDialog(null, Inputbox,
            "Sign in as Faculty/Student, or use Guest Button",
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