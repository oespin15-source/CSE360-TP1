package guiResetPassword;

import database.Database;
import guiUserLogin.ViewUserLogin;

/*******
 * <p> Title: ControllerResetPassword Class. </p>
 * 
 * <p> Description: The Java/FX-based Reset Password Page.  This class provides the controller actions
 * to allow the user to change their password using a single use password set by an admin. 
 * 
 * The controller determines if the new password is valid.  If so replaces the current user's 
 * old password with a new password.  The user is then directed to log in with their new password. 
 * The single use password is cleared afterward. 
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-08-17 Initial version
 *  
 */

public class ControllerResetPassword {
	
	/*-********************************************************************************************

	The controller attributes for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	*/
	
	/**
	 * Default constructor is not used
	 */
	public ControllerResetPassword() {
	}
	
	private static String resetPassword1 = "";
	private static String resetPassword2 = "";
	private static String correctUsername = "";
	private static Database theDatabase = applicationMain.FoundationsMain.database;
	
	
	/**********
	 * <p> Method: ResetThePassword1() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the password field in
	 * the View.  A private local copy of what was last entered is kept here.</p>
	 * 
	 */
	protected static void ResetThePassword1() {
		resetPassword1 = ViewResetPassword.text_NewPassword1.getText();
	}
	
	
	/**********
	 * <p> Method: ResetThePassword2() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the password field in
	 * the View.  A private local copy of what was last entered is kept here.</p>
	 * 
	 */
	protected static void ResetThePassword2() {
		resetPassword2 = ViewResetPassword.text_NewPassword2.getText();
	}
	
	
	/**********
	 * <p> Method: SetUsername() </p>
	 * 
	 * <p> Description: This method is called when the user tries to login with their username and 
	 * the single use password set by an admin. It stores a private local copy of the users
	 * username.</p>
	 * 
	 */
	public static void SetUsername(String Username) {
		correctUsername = Username;
	}
	
	
	/**********
	 * <p> Method: performPasswordReset() </p>
	 * 
	 * <p> Description: This method is called when the user presses on the set password button. It 
	 * checks if both instances of the new password are identical. If so, it updates the users 
	 * password in the database. Then, it redirects to the login page and clears the single
	 * use password. </p>
	 * 
	 */
	protected static void performPasswordReset() {
		if (resetPassword1.compareTo(resetPassword2) != 0) {
			// The two passwords are NOT the same, so clear the passwords, explain the passwords
			// must be the same, and clear the message as soon as the first character is typed.
			ViewResetPassword.text_NewPassword1.setText("");
			ViewResetPassword.text_NewPassword2.setText("");
			ViewResetPassword.alertPasswordMatchError.showAndWait();
		}
		else {
			// Change the user's password
			theDatabase.updatePassword(correctUsername, resetPassword1);
			// Alert the user it was a success
			ViewResetPassword.alertNewPasswordSuccess.showAndWait();
			// Redirect back to the login page
			guiUserLogin.ViewUserLogin.displayUserLogin(ViewUserLogin.theStage);
			// Clear the one time password once used
			guiSetOneTimePassword.ControllerSetOneTimePassword.ClearOneTimePassword();
		}
	}
	
	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user (who must be an Admin as only admins are the
	 * only users who have access to this page) to the Admin Home page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewUserLogin.theStage);
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
