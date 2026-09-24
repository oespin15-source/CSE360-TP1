package guiResetPassword;

import database.Database;
import guiUserLogin.ViewUserLogin;
import inputValidation.PasswordEvaluator;

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
	
	/**
	 * Default constructor is not used
	 */
	public ControllerResetPassword() {
	}
	
	/* called to perform validation on password */
	private static String resetPassword1 = "";
	/* called to perform validation on password */
	private static String resetPassword2 = "";
	/* correct username is passed later */
	private static String correctUsername = "";
	/* called by the View and used in this class to instantiate the Database */
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
	 * use password. 
	 * 
	 * As always, helpful error messages are displayed where needed.</p>
	 * 
	 */
	protected static void performPasswordReset() {
		String passwordErrorMessage =
		        PasswordEvaluator.evaluatePassword(resetPassword1);
		if (resetPassword1.compareTo(resetPassword2) != 0) {
			ViewResetPassword.text_NewPassword1.setText("");
			ViewResetPassword.text_NewPassword2.setText("");
			ViewResetPassword.alertPasswordError.setContentText("The passwords are not identical.");
			ViewResetPassword.alertPasswordError.showAndWait();
			return;
		}
		if (!passwordErrorMessage.isEmpty()) {
			ViewResetPassword.alertPasswordError.setContentText(passwordErrorMessage);
			ViewResetPassword.alertPasswordError.showAndWait();
			return;
		}
		else {
			theDatabase.updatePassword(correctUsername, resetPassword1);
			ViewResetPassword.alertNewPasswordSuccess.showAndWait();
			guiUserLogin.ViewUserLogin.displayUserLogin(ViewUserLogin.theStage);
			guiSetOneTimePassword.ControllerSetOneTimePassword.ClearOneTimePassword();
		}
	}
	
	
	protected static void validatePassword() {
	    String password = ViewResetPassword.text_NewPassword1.getText();

	    if (password.isEmpty()) {
	    	ViewResetPassword.label_PasswordRequirements.setText("");
	        return;
	    }

	    String passwordErrorMessage =
	            PasswordEvaluator.evaluatePassword(password);

	    if (passwordErrorMessage.isEmpty()) {
	    	ViewResetPassword.label_PasswordRequirements.setText(
	                "Password satisfies all requirements.");
	    } else {
	    	ViewResetPassword.label_PasswordRequirements.setText(
	                passwordErrorMessage);
	    }
	}


	/**********
	 * <p> Method: public updateStrength() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the first 
	 * password text box. It updates the progress bar's strength score.
	 * 
	 * Bar strength is mainly worried about length, character validation is nevertheless performed.
	 * 
	 * The bar is updated immediately as characters are typed or erased.
	 * Bar strengths: 
	 * less than 8: weak
	 * between 8 and 16: medium
	 * more than 16: strong
	 */	
	protected static void updateStrength() {
		String password = ViewResetPassword.text_NewPassword1.getText();
		double strengthScore = password.length();
        
		ViewResetPassword.Bar_passwordStrength.setProgress(strengthScore);
        
        if (strengthScore <= 8) {
        	ViewResetPassword.Bar_passwordStrength.setStyle("-fx-accent: red;");
        	ViewResetPassword.label_StrengthLabel.setText("Strength: Weak");
        } else if ((strengthScore > 8) && (strengthScore <= 16)) {
        	ViewResetPassword.Bar_passwordStrength.setStyle("-fx-accent: orange;");
        	ViewResetPassword.label_StrengthLabel.setText("Strength: Medium");
        } else {
        	ViewResetPassword.Bar_passwordStrength.setStyle("-fx-accent: green;");
        	ViewResetPassword.label_StrengthLabel.setText("Strength: Strong");
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
