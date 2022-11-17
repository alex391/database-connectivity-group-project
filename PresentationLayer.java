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
		JLabel lblDatabase = new JLabel("Database ->");
		JTextField tfDatabase = new JTextField("");

		Inputbox.add(lblUser);
		Inputbox.add(tfUser);
		Inputbox.add(lblPassword);
		Inputbox.add(tfPassword);
		Inputbox.add(lblDatabase);
		Inputbox.add(tfDatabase);

		lblUser.setFont(myFontForOutput);
		tfUser.setFont(myFontForOutput);
		tfUser.setForeground(Color.BLUE);
		lblPassword.setFont(myFontForOutput);
		tfPassword.setFont(myFontForOutput);
		tfPassword.setForeground(Color.BLUE);
		lblDatabase.setFont(myFontForOutput);
		tfDatabase.setFont(myFontForOutput);
		tfDatabase.setForeground(Color.BLUE);

		JOptionPane.showMessageDialog(null, Inputbox,
				"Input    Default password is \"student\"", JOptionPane.QUESTION_MESSAGE);

		String userName = tfUser.getText();
		String database = tfDatabase.getText();

		String password = new String();
		String passwordInput = new String();

		passwordInput = tfPassword.getText();

		// set the default password to "student"
		if (passwordInput.equalsIgnoreCase("")) {
			password = "student"; // CHANGE TO STUDENT
		} else {
			password = passwordInput;
		}

		dl.connect(); // Call DataLayer

		// System.out.println("You have connected to the database!");
		// System.out.println("\nGoing to fetch the result set...\n Attempting to gather metadata...");

		// columns = dl.getResultSet(); // getColumnCount() is what this method performs
		// System.out.println("\nThe number of columns returned from the sql statment is -->  " + columns);

		// rows = dl.geNumberOfRows_passenger_table();
		// System.out.println("\nThe number of rows returned from the sql statment is ----->  " + rows);

		// System.out.print("\nPlease enter new zip code -> ");
		// String zipcode = GetInput.readLine();

		// System.out.print("Please enter city ---------> ");
		// String city = GetInput.readLine();

		// System.out.print("Please enter state --------> ");
		// String state = GetInput.readLine();

		// int result = dl.addZipCode(zipcode, city, state);

		// System.out.println("\nThe result of adding a zip code is -> " + result + " <-");

		// //Adding a Passenger.
		// System.out.println("\n   * * *    Adding a Passenger    * * *");
		// System.out.print("\nPlease enter passenger ID -------> ");
		// int passengerID = GetInput.readLineInt();

		// System.out.print("Please enter first name ---------> ");
		// String fname = GetInput.readLine();

		// System.out.print("Please enter last name ----------> ");
		// String lname = GetInput.readLine();

		// System.out.print("Please enter street -------------> ");
		// String street = GetInput.readLine();

		// System.out.print("Please enter Zip Code -----------> ");
		// String zipcode = GetInput.readLine();

		// int result = dl.addPassenger(passengerID, fname, lname, street, zipcode);
		// System.out.println("\nThe result of adding a passenger is -> " + result + " <-");

		// //Deleting a Passenger
		// System.out.println("\n   * * *    Deleting a Passenger    * * *");
		// System.out.print("\nPlease enter passenger ID -------> ");
		// passengerID = GetInput.readLineInt();

		// result = dl.deletePassenger(passengerID);
		// System.out.println("\nThe result of deleting a passenger is -> " + result + " <-");

		// //Updating a Passenger
		// System.out.println("\n   * * *    Updating a Passenger    * * *");
		// System.out.print("\nPlease enter passenger ID -------> ");
		// passengerID = GetInput.readLineInt();

		// System.out.print("Please enter street -------------> ");
		// street = GetInput.readLine();

		// result = dl.updatePassenger(passengerID, street);
		// System.out.println("\nThe result of updating a passenger is -> " + result + " <-");

		// String msg = dl.retrievePassengers();
		// JOptionPane.showMessageDialog(null, msg,
		// 		"Passengers In Travel", JOptionPane.PLAIN_MESSAGE);

		//Closing all connections to database
		System.out.println("\nClosing all connections to database...\n");
		dl.close();

		// End Of Job data - EOJ routines
		java.util.Date today = new java.util.Date();
		System.out.println("\nProgram terminated @ " + today);
	} // End of Constructor

	public static void main(String[] args) {
		System.out.println("McIntosh, Michael");
		new PresentationLayer(); // Create a new object. An Instantiation
		System.out.println("EOJ");
		System.exit(0);
	} // End of main method
} // End of Class