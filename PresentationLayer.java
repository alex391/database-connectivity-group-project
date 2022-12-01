/*
 * ISTE 330 Jim Habermas
 * Alex Leute, Evan Reighter, Michael McIntosh, Teo Luciani, Adrian Marquez
 * 11/15/22
 * Group Project 01 StudentFaculty Project DataLayer
 */

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PresentationLayer {

	DataLayer dl = new DataLayer();
	private int columns;
	private int rows;

	public static Font myFontForOutput = new Font("Courier", Font.BOLD, 20);
   
	public PresentationLayer() {
		System.out.println("Connecting to the database . . .");

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
				"Default password is \"student\" - This logs into system studentfaculty DB", JOptionPane.QUESTION_MESSAGE);

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
      
      //loop 1 login
         //case Student v Faculty
            //loop Student/guest
               //Who is logged in somewhere
               //exit button
               //options to
                  //edit interests
                  //search by interests
                  //search by userID
                  //or browse entries
            //loop faculty
               //Who is logged in somewhere
               //exit button
               //options to
                  //edit interests
                  //search by interests
                  //search by userID
                  //or browse entries
                  //entries
                     //add
                     //update
                     //delete
            

		//Closing all connections to database
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
		System.exit(0);
	} // End of main method
} // End of Class