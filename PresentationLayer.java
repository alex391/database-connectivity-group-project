
/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PresentationLayer {
   String userName = "";
   String userType = "G";
   boolean loggedIn;
   DataLayer dl = new DataLayer();

   public static Font myFontForOutput = new Font("Courier", Font.BOLD, 20);

   /**
    * GUEST PORTAL GUI
    * This function is responsible for the Guest Portal GUI creation and
    * instantiation.
    * This method is called whenever the user logs in as a Guest.
    */
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
            JLabel newLabel = new JLabel("Enter The Number Of The Interest ID To Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 400, 30);

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
            listInterests(sframe);
         }
      });

      // Browse
      JButton browseButton = new JButton("Browse Entries");
      browseButton.setBounds(100, 130, 150, 50);
      f.add(browseButton);
      // Browse Button Listening
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
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   /**
    * STUDENT PORTAL GUI
    * This function is responsible for the Student Portal GUI creation and
    * instantiation.
    * This method is called whenever the user logs in as a Student.
    */
   public void StudentBox() {
      // Student Box Frame Setup
      JFrame f = new JFrame("Student Portal");
      f.setSize(400, 400);

      // Label
      JLabel label = new JLabel(" Student Features");
      label.setBounds(92, 50, 170, 30);
      f.add(label);

      // Edit Interests
      JButton editButton = new JButton("Edit Your Interests");
      editButton.setBounds(62, 80, 175, 50);
      f.add(editButton);
      // Edit Interest Listening
      editButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Edit Your Interests");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel enterInterestLabel = new JLabel("Enter Your New Interest: ");
            sframe.add(enterInterestLabel);
            enterInterestLabel.setBounds(185, 30, 185, 30);

            JTextField enterInterestText = new JTextField(16);
            sframe.add(enterInterestText);
            enterInterestText.setBounds(185, 60, 180, 30);

            JButton enterInterestButton = new JButton("Add");
            sframe.add(enterInterestButton);
            enterInterestButton.setBounds(220, 100, 100, 50);

            // Add Listener for search button
            enterInterestButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for search uses the textfield as a parameter

                  // Result box will show the faculty with matching interest as user input

                  dl.addInterest(dl.getUserID(userName), Integer.parseInt(enterInterestText.getText()));
                  ResultBox("Interest Added");
               }
            });

            // Add Label, box, button
            JLabel removeInterestLabel = new JLabel("Enter Your Unwanted Interest: ");
            sframe.add(removeInterestLabel);
            removeInterestLabel.setBounds(400, 30, 185, 30);

            JTextField removeInterestText = new JTextField(16);
            sframe.add(removeInterestText);
            removeInterestText.setBounds(400, 60, 180, 30);

            JButton removeInterestButton = new JButton("Remove");
            sframe.add(removeInterestButton);
            removeInterestButton.setBounds(440, 100, 100, 50);

            // Add Listener for search button
            removeInterestButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for search uses the textfield as a parameter

                  // Result box will show the faculty with matching interest as user input

                  dl.deleteInterest(dl.getUserID(userName), Integer.parseInt(removeInterestText.getText()));
                  ResultBox("Interest Removed");
               }
            });

            // List the interests
            listInterests(sframe);
            listUserInterests(sframe);
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
            JLabel newLabel = new JLabel("Enter The Number Of The Interest ID To Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 400, 30);

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
            listInterests(sframe);
         }
      });

      // Search Commonalities
      JButton searchCommonButton = new JButton("Common Interests");
      searchCommonButton.setBounds(40, 180, 230, 50);
      f.add(searchCommonButton);

      searchCommonButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Common Interests");
            sframe.setSize(400, 300);

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Full Name - Interest In Common - Occupation\n\n");
            for (String user : dl.getCommonInterests(dl.getUserID(userName))) {
               resultBuilder.append(user);
               resultBuilder.append("\n");
            }
            String result = resultBuilder.toString(); // name of the users, what interest they had in common, and what
                                                      // user/occupation they are.
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 600, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
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

      f.setLayout(null); // using no layout managers
      f.setVisible(true); // making the frame visible
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   /**
    * FACULTY PORTAL GUI
    * This function is responsible for the Faculty Portal GUI creation and
    * instantiation.
    * This method is called whenever the user logs in as a Faculty.
    */
   public void FacultyBox() {
      // Faculty Box Frame Setup
      JFrame f = new JFrame("Faculty Portal");
      f.setSize(600, 400);

      // Label
      JLabel label = new JLabel(" Student Features");
      label.setBounds(92, 50, 170, 30);
      f.add(label);

      // Edit Interests
      JButton editButton = new JButton("Edit Your Interests");
      editButton.setBounds(62, 80, 175, 50);
      f.add(editButton);
      // Edit Interest Listening
      editButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Edit Your Interests");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel enterInterestLabel = new JLabel("Enter Your New Interest: ");
            sframe.add(enterInterestLabel);
            enterInterestLabel.setBounds(185, 30, 185, 30);

            JTextField enterInterestText = new JTextField(16);
            sframe.add(enterInterestText);
            enterInterestText.setBounds(185, 60, 180, 30);

            JButton enterInterestButton = new JButton("Add");
            sframe.add(enterInterestButton);
            enterInterestButton.setBounds(220, 100, 100, 50);

            // Add Listener for search button
            enterInterestButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for search uses the textfield as a parameter

                  // Result box will show the faculty with matching interest as user input

                  dl.addInterest(dl.getUserID(userName), Integer.parseInt(enterInterestText.getText()));
                  ResultBox("Interest Added");
               }
            });

            // Add Label, box, button
            JLabel removeInterestLabel = new JLabel("Enter Your Unwanted Interest: ");
            sframe.add(removeInterestLabel);
            removeInterestLabel.setBounds(400, 30, 185, 30);

            JTextField removeInterestText = new JTextField(16);
            sframe.add(removeInterestText);
            removeInterestText.setBounds(400, 60, 180, 30);

            JButton removeInterestButton = new JButton("Remove");
            sframe.add(removeInterestButton);
            removeInterestButton.setBounds(440, 100, 100, 50);

            // Add Listener for search button
            removeInterestButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for search uses the textfield as a parameter

                  // Result box will show the faculty with matching interest as user input

                  dl.deleteInterest(dl.getUserID(userName), Integer.parseInt(removeInterestText.getText()));
                  ResultBox("Interest Removed");
               }
            });

            // List the interests
            listInterests(sframe);
            listUserInterests(sframe);
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
            JLabel newLabel = new JLabel("Enter The Number Of The Interest ID To Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 400, 30);

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
            listInterests(sframe);
         }
      });

      // Search Commonalities
      JButton searchCommonButton = new JButton("Common Interests");
      searchCommonButton.setBounds(40, 180, 230, 50);
      f.add(searchCommonButton);

      searchCommonButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Common Interests");
            sframe.setSize(400, 300);

            StringBuilder resultBuilder = new StringBuilder();
            resultBuilder.append("Full Name - Interest In Common - Occupation\n\n");
            for (String user : dl.getCommonInterests(dl.getUserID(userName))) {
               resultBuilder.append(user);
               resultBuilder.append("\n");
            }
            String result = resultBuilder.toString(); // name of the users, what interest they had in common, and what
                                                      // user/occupation they are.
            JTextArea listInterests = new JTextArea(result);
            listInterests.setEditable(false);
            listInterests.setBounds(10, 30, 600, 400);
            sframe.add(listInterests);

            sframe.setLayout(null); // using no layout managers
            sframe.setSize(800, 800);
            sframe.setVisible(true); // making the frame visible
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
            b.setBounds(190, 180, 180, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for new entry uses the textfield as a parameter

                  // Result box will show success message if added

                  dl.addEntry(dl.getUserID(userName), t.getText(), Integer.parseInt(interest.getText()));
                  ResultBox("Entry Added.");

               }
            });
            sframe.setLayout(null); // using no layout managers
            sframe.setVisible(true); // making the frame visible

            // List the interests
            listInterests(sframe);
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
            b.setBounds(180, 180, 180, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for new entry uses the textfield as a parameter

                  // Result box will show success message if added
                  if (dl.checkOwnership(dl.getUserID(userName), Integer.parseInt(entryID.getText()))) {
                     dl.updateEntry(Integer.parseInt(entryID.getText()), t.getText());
                     ResultBox("Entry Updated.");
                  } else {
                     ResultBox("You cannot edit/delete entries you do not own.");
                  }
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
            b.setBounds(180, 180, 180, 50);

            // Add Listener for search button
            b.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {

                  // Action for new entry uses the textfield as a parameter

                  // Result box will show success message if added
                  if (dl.checkOwnership(dl.getUserID(userName), Integer.parseInt(t.getText()))) {
                     dl.deleteEntry(Integer.parseInt(t.getText()));
                     ResultBox("Entry Deleted.");
                  } else {
                     ResultBox("You cannot edit/delete entries you do not own.");
                  }
               }
            });
            sframe.setLayout(null); // using no layout managers
            sframe.setVisible(true); // making the frame visible
         }
      });

      f.setLayout(null); // using no layout managers
      f.setVisible(true); // making the frame visible
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // EDIT THIS METHOD to search for "s"

      // Search for Students Interests
      JButton searchStudButton = new JButton("Search Students By Interests");
      searchStudButton.setBounds(330, 280, 210, 50);
      f.add(searchStudButton);

      // Search Interests Button Listening
      searchStudButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {

            // Action

            // Box with textarea of interest option + textbox for user input. Search button
            // to search by what user inputs.
            JFrame sframe = new JFrame("Search for Student By Interest ID");
            sframe.setSize(400, 300);

            // Add Label, box, button
            JLabel newLabel = new JLabel("Enter The Number Of The Interest ID To Search: ");
            sframe.add(newLabel);
            newLabel.setBounds(185, 30, 400, 30);

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
            listInterests(sframe);
         }
      });
   }

   /**
    * This function lists all the interests of the StudentFaculty database.
    *
    * @param sframe The sframe to add the interests to.
    */
   private void listInterests(JFrame sframe) {
      JLabel intLabel = new JLabel("Interests And ID's: ");
      sframe.add(intLabel);
      intLabel.setBounds(10, 0, 170, 30);

      String result = dl.allInterests();
      JTextArea listInterests = new JTextArea(result);
      listInterests.setEditable(false);
      listInterests.setBounds(10, 30, 170, 420); //nice
      sframe.add(listInterests);
      
      sframe.setLayout(null); // using no layout managers
      sframe.setSize(800, 800);
      sframe.setVisible(true); // making the frame visible
   }

   /**
    * List all the user's interests
    *
    * @param sframe the sframe to add the interests to.
    */
   private void listUserInterests(JFrame sframe) {
      JLabel intLabel = new JLabel("Your Current Interests And ID's: ");
      sframe.add(intLabel);
      intLabel.setBounds(600, 0, 190, 30);

      String result = dl.allUserInterests(Integer.toString(dl.getUserID(userName)));
      JTextArea listUserInterests = new JTextArea(result);
      listUserInterests.setEditable(false);
      listUserInterests.setBounds(600, 30, 170, 420);
      
      sframe.add(listUserInterests);

      sframe.setLayout(null); // using no layout managers
      sframe.setSize(800, 800);
      sframe.setVisible(true); // making the frame visible
   }

   /**
    * This function is a box constructor used to create a JFrame containing a
    * JTextArea and JScrollPane scrollbar.
    * Used to show whatever comes out of a given query from a method or button.
    * 
    * @param title The name at the top of the JFrame.
    */
   public void ResultBox(String title) {

      String resultTest = new String(title);
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
      f.setVisible(true); // making the frame visible.
   }

   /**
    * This function is the User Login and how the user accesses the database.
    * 
    * Checks the login information against the database to ensure access is limited
    * to the correct users.
    * Changes global userType and userName attributes for the program.
    * 
    * @return True when login matches credentials provided. False if otherwise.
    */
   public boolean appLogin() {
      JFrame loginFrame = new JFrame("Faculty Portal");
      loginFrame.setSize(300, 200);

      JPanel Inputbox = new JPanel(new GridLayout(3, 2));
      JLabel lblUser = new JLabel("Username -> ");
      JLabel lblPassword = new JLabel("Password -> ");
      JTextField tfUser = new JTextField("");
      JTextField tfPassword = new JPasswordField("");
      JButton b = new JButton("Guest"); // Guest Button

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
            "Sign in as Faculty/Student, or use Guest Button.",
            JOptionPane.QUESTION_MESSAGE);

      userName = tfUser.getText();
      userType = dl.getUserType(userName);
      String password = new String();
      String passwordInput = new String();
      passwordInput = tfPassword.getText();

      // Set the default password to "student".
      if (passwordInput.equalsIgnoreCase("")) {
         password = "guest"; // CHANGE TO guest

      } else {
         password = passwordInput;
      }
      // Returns true or false.
      return dl.checkPassword(userName, password);
   }

   /**
    * This function is the PresentationLayer Constructor.
    * Constructs a fully functional PresentationLayer.
    */
   public PresentationLayer() {
      System.out.println("Connecting to the database . . .");

      JPanel Inputbox = new JPanel(new GridLayout(3, 2));

      JLabel lblUser = new JLabel("Username -> ");
      JLabel lblPassword = new JLabel("Password -> ");
      JTextField tfUser = new JTextField("root");
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
            "Default password is \"student\" - This logs into system studentfaculty DB.",
            JOptionPane.QUESTION_MESSAGE);

      String userName = tfUser.getText();
      String password = new String();
      String passwordInput = new String();
      passwordInput = tfPassword.getText();

      // Set the default password to "student".
      if (passwordInput.equalsIgnoreCase("")) {
         password = "student"; // CHANGE TO STUDENT.
      } else {
         password = passwordInput;
      }

      dl.connect(userName, password); // Call DataLayer.

      //bool for loop
      Boolean notLoggedIn = true;
      //while loop returns user to login until sucessful attempt
      while(notLoggedIn){
         loggedIn = appLogin();
         if (loggedIn){
            notLoggedIn = false;
            System.out.println("Login success!");
         }
         System.out.println("Login failed :(");
      }
      
      if (loggedIn) {
         switch (userType) {
            case "F": // Faculty.
               /*
                * Loop Faculty who is logged in.
                * 
                * Edit Button.
                * - Add Your Interests.
                * - Delete Your Interests.
                * 
                * Search Faculty By Interests Button.
                * - Search all Faculty with the same interest using interestID.
                * 
                * Common Interests Button.
                * - Returns all common interests on click.
                * 
                * Browse Entries Button.
                * - Displays all entries on click.
                * 
                * Exit Button.
                * - Exits program.
                * 
                * ONLY FACULTY CAN SEE OR DO THE BELOW FUNCTIONS.
                * Add Entry Button.
                * - Paste/Enter Abstract.
                * - Enter interestID.
                * 
                * Update Entry Button.
                * - Enter topic's new name.
                * - Enter entryID.
                * 
                * Delete Entry Button.
                * - Enter entryID.
                * 
                * Search Students By Interests Button.
                * - Enter interestID.
                */
               FacultyBox();
               break;

            case "S": // Student.
               /*
                * Loop Student who is logged in.
                * 
                * Edit Button.
                * - Add Your Interests.
                * - Delete Your Interests.
                * 
                * Search Faculty By Interests Button.
                * - Search all Faculty with the same interest using interestID.
                * 
                * Common Interests Button.
                * - Returns all common interests on click.
                * 
                * Browse Entries Button.
                * - Displays all entries on click.
                * 
                * Exit Button.
                * - Exits program.
                */
               StudentBox();
               break;

            case "G": // Guest.
               /*
                * Loop Guest who is logged in.
                * 
                * Search Faculty By Interests Button.
                * - Search all Faculty with the same interest using interestID.
                * 
                * Browse Entries Button.
                * - Displays all entries on click.
                * 
                * Exit Button.
                * - Exits program.
                */
               GuestBox();
               break;
            default:
               System.err.println("Invalid user type!");
               break;
         }
      }
   } // End of Constructor.

   /**
    * Simple main function to run the project. Prints out our Group #, names, and
    * starts PresentationLayer.
    * 
    * @param args Default arguments.
    */
   public static void main(String[] args) {
      System.out.println("Group 4: Adrian Marquez, Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani.");

      new PresentationLayer(); // Create a new object. An Instantiation.
   } // End of main method.
} // End of Class.