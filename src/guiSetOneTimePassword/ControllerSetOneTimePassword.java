package guiSetOneTimePassword;

/*******
 * <p> Title: ControllerSetOneTimePassword Class. </p>
 * 
 * <p> Description: The Java/FX-based Reset Password Page.  This class provides the controller actions
 * to allow an admin to set a one time use password for another user to use to change their password. 
 * 
 * The controller sets the single use password. Once the password is used it is cleared.  
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-08-17 Initial version
 *  
 */

public class ControllerSetOneTimePassword {
	
	/*-********************************************************************************************

	The controller attributes for this page
	
	This controller is not a class that gets instantiated.  Rather, it is a collection of protected
	static methods that can be called by the View (which is a singleton instantiated object) and 
	the Model is often just a stub, or will be a singleton instantiated object.
	
	*/
	
	/**
	 * Default constructor is not used
	 */
	public ControllerSetOneTimePassword() {
	}
	
	private static String oneTimePassword = "";
	
	
	/**********
	 * <p> Method: SetTheOneTimePassword() </p>
	 * 
	 * <p> Description: This method is called when the admin presses the set password button. A private 
	 * local copy of the password is kept here.</p>
	 * 
	 */
	protected static void SetTheOneTimePassword() {
		oneTimePassword = ViewSetOneTimePassword.text_SetOneTimePassword.getText();
		System.out.print(oneTimePassword);
	}
	
	
	/**********
	 * <p> Method: GetTheOneTimePassword() </p>
	 * 
	 * <p> Description: This method is called when the user tries to login. The system checks the single
	 * use password against the input password in case it matches. </p>
	 * 
	 */
	public static String GetTheOneTimePassword() {
		return oneTimePassword;
	}
	
	
	/**********
	 * <p> Method: ClearOneTimePassword() </p>
	 * 
	 * <p> Description: This method is called when a users password is updated to a new password. It clears
	 * the single use password after being used.</p>
	 * 
	 */
	public static void ClearOneTimePassword() {
		oneTimePassword = "";
		ViewSetOneTimePassword.text_SetOneTimePassword.setText("");
	}
	
	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user (who must be an Admin as only admins are the
	 * only users who have access to this page) to the Admin Home page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiAdminHome.ViewAdminHome.displayAdminHome(ViewSetOneTimePassword.theStage,
				ViewSetOneTimePassword.theUser);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewSetOneTimePassword.theStage);
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
