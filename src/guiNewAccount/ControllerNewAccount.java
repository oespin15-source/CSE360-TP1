package guiNewAccount;

import java.sql.SQLException;
import database.Database;
import entityClasses.User;
import inputValidation.UserNameRecognizer;
import inputValidation.PasswordEvaluator;
/*******
 * <p> Title: ControllerNewAccount Class. </p>
 * 
 * <p> Description: The Java/FX-based New Account Page.  This class provides the controller actions
 * to allow the user to establish a new account after responding to an invitation and the use of a
 * one time code.
 * 
 * The controller deals with the user pressing the "User Step" button widget being click.  If also
 * supports the user click on the "Quit" button widget.
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

public class ControllerNewAccount {
	
	/**
	 * Default constructor is not used.
	 */
	public ControllerNewAccount() {
	}
	
	
	/* called by the View and used in this class to instantiate the Database */
	private static Database theDatabase = applicationMain.FoundationsMain.database;
	
	/**********
	 * <p> Method: public doCreateUser() </p>
	 * 
	 * <p> Description: This method is called when the user has clicked on the User Setup
	 * button.  This method checks the input fields to see that they are valid.  If so, it then
	 * creates the account by adding information to the database.
	 * 
	 * The method reaches batch to the view page and to fetch the information needed rather than
	 * passing that information as parameters.
	 * 
	 * After the input is entirely validated, user is created with known email and known role.
	 * If successful, Database worked, invitation is removed from system.
	 * 
	 * 
	 */	
	protected static void doCreateUser() {
		
		String username = ViewNewAccount.text_Username.getText();
		String password = ViewNewAccount.text_Password1.getText();
		
		String usernameErrorMessage =
				UserNameRecognizer.checkForValidUserName(username);

		if (!usernameErrorMessage.isEmpty()) {
			ViewNewAccount.alertUsernameError.setContentText(usernameErrorMessage);
			ViewNewAccount.alertUsernameError.showAndWait();
			return;
		}
		
		String passwordErrorMessage =
		        PasswordEvaluator.evaluatePassword(password);

		if (!passwordErrorMessage.isEmpty()) {
			ViewNewAccount.alertPasswordError.setContentText(passwordErrorMessage);
			ViewNewAccount.alertPasswordError.showAndWait();
		    return;
		}
		
		System.out.println("** Account for Username: " + username + "; theInvitationCode: "+
				ViewNewAccount.theInvitationCode + "; email address: " + 
				ViewNewAccount.emailAddress + "; Role: " + ViewNewAccount.theRole);
		
		int roleCode = 0;
		User user = null;
	
		
		if (ViewNewAccount.text_Password1.getText().
				compareTo(ViewNewAccount.text_Password2.getText()) == 0) { 
			if (ViewNewAccount.theRole.compareTo("Admin") == 0) {
				roleCode = 1;
				user = new User(username, password, "", "", "", "", "", true, false, false);
			} else if (ViewNewAccount.theRole.compareTo("Role1") == 0) {
				roleCode = 2;
				user = new User(username, password, "", "", "", "", "", false, true, false);
			} else if (ViewNewAccount.theRole.compareTo("Role2") == 0) {
				roleCode = 3;
				user = new User(username, password, "", "", "", "", "", false, false, true);
			} else {
				System.out.println(
						"**** Trying to create a New Account for a role that does not exist!");
				System.exit(0);
			}
			
        	user.setEmailAddress(ViewNewAccount.emailAddress);

			applicationMain.FoundationsMain.activeHomePage = roleCode;
			
            try {
            	theDatabase.register(user);
            } catch (SQLException e) {
                System.err.println("*** ERROR *** Database error: " + e.getMessage());
                e.printStackTrace();
                System.exit(0);
            }
            System.out.print(false);
            theDatabase.removeInvitationAfterUse(
            		ViewNewAccount.text_Invitation.getText());
            
            theDatabase.getUserAccountDetails(username);

            guiUserUpdate.ViewUserUpdate.displayUserUpdate(ViewNewAccount.theStage, user);
		}
		else {
			ViewNewAccount.text_Password1.setText("");
			ViewNewAccount.text_Password2.setText("");
			ViewNewAccount.alertUsernamePasswordError.showAndWait();
		}
	}
	
	/**********
	 * <p> Method: protected validatePassword() </p>
	 * 
	 * <p> Description: This method is called for the password validation of a new user.
	 * The password validation implemented from HW1 is used.
	 * 
	 * While the user types the password, immediate feedback is displayed.
	 * </p>
	 * 
	 */	
	protected static void validatePassword() {
	    String password = ViewNewAccount.text_Password1.getText();

	    if (password.isEmpty()) {
	        ViewNewAccount.label_PasswordRequirements.setText("");
	        return;
	    }

	    String passwordErrorMessage =
	            PasswordEvaluator.evaluatePassword(password);

	    if (passwordErrorMessage.isEmpty()) {
	        ViewNewAccount.label_PasswordRequirements.setText(
	                "Password satisfies all requirements.");
	    } else {
	        ViewNewAccount.label_PasswordRequirements.setText(
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
		String password = ViewNewAccount.text_Password1.getText();
		double strengthScore = password.length();
        
        ViewNewAccount.Bar_passwordStrength.setProgress(strengthScore);
        
        // Update the colors and text of the label
        if (strengthScore <= 8) {
        	ViewNewAccount.Bar_passwordStrength.setStyle("-fx-accent: red;");
        	ViewNewAccount.label_StrengthLabel.setText("Strength: Weak");
        } else if ((strengthScore > 8) && (strengthScore <= 16)) {
        	ViewNewAccount.Bar_passwordStrength.setStyle("-fx-accent: orange;");
        	ViewNewAccount.label_StrengthLabel.setText("Strength: Medium");
        } else {
        	ViewNewAccount.Bar_passwordStrength.setStyle("-fx-accent: green;");
        	ViewNewAccount.label_StrengthLabel.setText("Strength: Strong");
        }
    }

	
	/**********
	 * <p> Method: public performQuit() </p>
	 * 
	 * <p> Description: This method is called when the user has clicked on the Quit button.  Doing
	 * this terminates the execution of the application.  All important data must be stored in the
	 * database, so there is no cleanup required.  (This is important so we can minimize the impact
	 * of crashed.)
	 * 
	 */	
	protected static void performQuit() {
		System.out.println("Perform Quit");
		System.exit(0);
	}	
}
