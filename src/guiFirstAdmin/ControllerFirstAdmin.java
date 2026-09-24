package guiFirstAdmin;

import java.sql.SQLException;

import database.Database;
import entityClasses.User;
import javafx.stage.Stage;
import inputValidation.UserNameRecognizer;
import inputValidation.PasswordEvaluator;

/*******
 * <p> Title: ControllerFirstAdmin Class. </p>
 * 
 * <p> Description: ControllerFirstAdmin class provides the controller actions based on the user's
 *  use of the JavaFX GUI widgets defined by the View class.
 * 
 * This page contains a number of buttons that have not yet been implemented.  WHhen those buttons
 * are pressed, an alert pops up to tell the user that the function associated with the button has
 * not been implemented. Also, be aware that What has been implemented may not work the way the
 * final product requires and there maybe defects in this code.
 * 
 * The class has been written assuming that the View or the Model are the only class methods that
 * can invoke these methods.  This is why each has been declared at "protected".  Do not change any
 * of these methods to public.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-08-17 Initial version
 *  
 */

public class ControllerFirstAdmin {
	
	/* called by the View to perform validation on admin Username */	
	private static String adminUsername = "";
	/* called by the View to perform validation on admin Password */
	private static String adminPassword1 = "";
	/* called by the View to perform validation on admin Password Re-Entering */
	private static String adminPassword2 = "";
	/* called by the View and used in this class to instantiate the Database */
	protected static Database theDatabase = applicationMain.FoundationsMain.database;		

	/**
	 * Default constructor is not used.
	 */
	public ControllerFirstAdmin() {
	}

	/**********
	 * <p> Method: setAdminUsername() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the username field in the
	 * View.  A private local copy of what was last entered is kept here.</p>
	 * 
	 */
	protected static void setAdminUsername() {
		adminUsername = ViewFirstAdmin.text_AdminUsername.getText();
		ViewFirstAdmin.label_UsernameError.setText("");
	}
	
	/**********
	 * <p> Method: setAdminPassword1() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the password 1 field in
	 * the View.  A private local copy of what was last entered is kept here.
	 * 
	 * After password is entered, PasswordEvaluator class evaluates it. Immediate feedback is displayed 
	 * at all times for the user to know when the password is satisfying. 
	 * 
	 * Only when satisfied, the Admin password is showing a satisfaction message.
	 * </p>
	 * 
	 */
	protected static void setAdminPassword1() {
	    adminPassword1 = ViewFirstAdmin.text_AdminPassword1.getText();

	    if (adminPassword1.isEmpty()) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText("");
	        return;
	    }

	    String passwordErrorMessage =
	            PasswordEvaluator.evaluatePassword(adminPassword1);

	    if (passwordErrorMessage.isEmpty()) {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText(
	                "Password satisfies all requirements.");
	    } else {
	        ViewFirstAdmin.label_PasswordsDoNotMatch.setText(
	                passwordErrorMessage);
	    }
	}
	
	
	/**********
	 * <p> Method: setAdminPassword2() </p>
	 * 
	 * <p> Description: This method is called when the user adds text to the password 2 field in
	 * the View.  A private local copy of what was last entered is kept here.
	 * 
	 * Keeps track of currently typed second password and deletes the immediate feedback from the previous method.
	 * </p>
	 * 
	 */
	protected static void setAdminPassword2() {
		adminPassword2 = ViewFirstAdmin.text_AdminPassword2.getText();		
		ViewFirstAdmin.label_PasswordsDoNotMatch.setText("");
	}
	
	
	/**********
	 * <p> Method: doSetupAdmin() </p>
	 * 
	 * <p> Description: This method is called when the user presses the button to set up the Admin
	 * account.  It start by trying to establish a new user and placing that user into the
	 * database.  If that is successful, we proceed to the UserUpdate page.
	 * 
	 * Using UserNameRecognizer class, the Username is validated.
	 * If successful, sequentially the password 1 is validated. 
	 * Once password 1 is satisfying, a final check of equality between password 1 and password 2 is done.
	 * 
	 * After validating these 3 inputs, only then the user is officially created, if successful.
	 * 
	 * Finally, if all is successful, the user is navigated to the User Update Page.
	 * 
	 * However, if password 2 is not equivalent, display error message accordingly, and correction is expected. 
	 * </p>
	 * 
	 */
	protected static void doSetupAdmin(Stage ps, int r) {
		
		String usernameErrorMessage =
				UserNameRecognizer.checkForValidUserName(adminUsername);
		
		if (!usernameErrorMessage.isEmpty()) {
			ViewFirstAdmin.label_UsernameError.setText(usernameErrorMessage);
			return;
		}
		
		String passwordErrorMessage =
		        PasswordEvaluator.evaluatePassword(adminPassword1);

		if (!passwordErrorMessage.isEmpty()) {
		    ViewFirstAdmin.label_PasswordsDoNotMatch.setText(passwordErrorMessage);
		    return;
		}
		
		if (adminPassword1.compareTo(adminPassword2) == 0) {
        	User user = new User(adminUsername, adminPassword1, "", "", "", "", "", true, false, 
        			false);
            try {
            	theDatabase.register(user);
            	}
            catch (SQLException e) {
                System.err.println("*** ERROR *** Database error trying to register a user: " + 
                		e.getMessage());
                e.printStackTrace();
                System.exit(0);
            }
            
        	guiUserUpdate.ViewUserUpdate.displayUserUpdate(ViewFirstAdmin.theStage, user);
		}
		else {
			ViewFirstAdmin.text_AdminPassword1.setText("");
			ViewFirstAdmin.text_AdminPassword2.setText("");
			ViewFirstAdmin.label_PasswordsDoNotMatch.setText(
					"The two passwords must match. Please try again!");
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
		String password = ViewFirstAdmin.text_AdminPassword1.getText();
		double strengthScore = password.length();
        
		ViewFirstAdmin.Bar_passwordStrength.setProgress(strengthScore);
        
        if (strengthScore <= 8) {
        	ViewFirstAdmin.Bar_passwordStrength.setStyle("-fx-accent: red;");
        	ViewFirstAdmin.label_StrengthLabel.setText("Strength: Weak");
        } else if ((strengthScore > 8) && (strengthScore <= 16)) {
        	ViewFirstAdmin.Bar_passwordStrength.setStyle("-fx-accent: orange;");
        	ViewFirstAdmin.label_StrengthLabel.setText("Strength: Medium");
        } else {
        	ViewFirstAdmin.Bar_passwordStrength.setStyle("-fx-accent: green;");
        	ViewFirstAdmin.label_StrengthLabel.setText("Strength: Strong");
        }
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
		System.out.println("Perform Quit");
		System.exit(0);
	}	
}
