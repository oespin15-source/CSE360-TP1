package guiListAllUsers;

import java.util.List;
import database.Database;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/*******
 * <p> Title: ControllerListAllUsers Class. </p>
 * 
 * <p> Description: The Java/FX-based List All Users Page.  This class provides the controller actions
 * to allow an admin to properly list the username, name, email, and roles of every user. 
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-08-17 Initial version
 *  
 */

public class ControllerListAllUsers {
	
	/*-********************************************************************************************

	The controller attributes for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	*/
	
	/**
	 * Default constructor is not used
	 */
	public ControllerListAllUsers() {
	}
	
	// Access to database
	private static Database theDatabase = applicationMain.FoundationsMain.database;
	
	
	/**********
	 * <p> Method: setCellValue() </p>
	 * 
	 * <p> Description: This method is called in the displayListAllUsers method to display specific values 
	 * to a particular column in the table. The method takes a column and an index as parameters and tells 
	 * the table to display the String within the specified index in every row.</p>
	 * 
	 */
	protected static void setCellValue(TableColumn<String[], String> columnName, int index) {
		columnName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[index]) );
	}
	
	
	/**********
	 * <p> Method: setupTableItems() </p>
	 * 
	 * <p> Description: This method is called in the displayListAllUsers method to populate the table 
	 * with user information. It retrieves the list of usernames to obtain each user's name, email, and
	 * roles. The information is stored in a String array as a row in the table.</p>
	 * 
	 */
	protected static ObservableList<String[]> setupTableItems(ObservableList<String[]> users) {		
		// Retrieve usernames
		List<String> usernameList = theDatabase.getUserList();
		
		// Save the currently logged in user since GetRoles changes this
		String loggedInUsername = theDatabase.getCurrentUsername();
		
		for (int i=1; i<=theDatabase.getNumberOfUsers(); ++i) {
			String username = usernameList.get(i);
			String usersName = theDatabase.getFirstName(username) + " " + theDatabase.getLastName(username);
			String userEmailAddress = theDatabase.getEmailAddress(username);
			String userRoles = ControllerListAllUsers.getRoles(username);
			
			// adds all Strings of user data to an Array element
			users.add(new String[] {username, usersName, userEmailAddress, userRoles});
		}
		// Restore current user information as the user logged in
		theDatabase.getUserAccountDetails(loggedInUsername);
		
		return users;
	}
	
	
	/**********
	 * <p> Method: getRoles() </p>
	 * 
	 * <p> Description: This method creates a String of roles that the user currently has with 
	 * a comma between words. </p>
	 * 
	 */
	protected static String getRoles(String username) {
		boolean notTheFirst = false;
		String theCurrentRoles = "";
		theDatabase.getUserAccountDetails(username);
			
		// Admin role - It can only be at the head of a list
		if (theDatabase.getCurrentAdminRole()) {
			theCurrentRoles += "Admin";
			notTheFirst = true;
		}
			
		// Roles 1 - It could be at the head of the list or later in the list
		if (theDatabase.getCurrentNewRole1()) {
			if (notTheFirst)
				theCurrentRoles += ", Role1"; 
			else {
				theCurrentRoles += "Role1";
				notTheFirst = true;
			}
		}

		// Roles 2 - It could be at the head of the list or later in the list
		if (theDatabase.getCurrentNewRole2()) {
			if (notTheFirst)
				theCurrentRoles += ", Role2"; 
			else {
				theCurrentRoles += "Role2";
				notTheFirst = true;
			}
		}
		
		return theCurrentRoles;
	}

	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user (who must be an Admin as only admins are the
	 * only users who have access to this page) to the Admin Home page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiAdminHome.ViewAdminHome.displayAdminHome(ViewListAllUsers.theStage,
				ViewListAllUsers.theUser);
	}
	
	
	/**********
	 * <p> Method: performLogout() </p>
	 * 
	 * <p> Description: This method logs out the current user and proceeds to the normal login
	 * page where existing users can log in or potential new users with a invitation code can
	 * start the process of setting up an account. </p>
	 * 
	 */
	protected static void performLogout() {
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewListAllUsers.theStage);
	}
	
	
	/**********
	 * <p> Method: performQuit() </p>
	 * 
	 * <p> Description: This method terminates the execution of the program.  It leaves the
	 * database in a state where the normal login page will be displayed when the application is
	 * restarted.</p>
	 * 
	 */
	protected static void performQuit() {
		System.exit(0);
	}

}
