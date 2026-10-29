package guiAddExperienceInformation;

import javafx.scene.control.Alert;

/*******
 * <p> Title: ControllerAddExperienceInformation Class. </p>
 * 
 * <p> Description: The Java/FX-based Add Experience Information Page. This class provides 
 * the controller actions basic on the user's use of the JavaFX GUI widgets defined by the 
 * View class. After validating the user input, this class will use the experience information
 * to create a new Lesson Learned object and insert it into the database. 
 * 
 * The class has been written assuming that the View or the Model are the only class methods that
 * can invoke these methods.  This is why each has been declared at "protected".  Do not change any
 * of these methods to public.</p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 */
public class ControllerAddExperienceInformation {
	
	private static String whatWasDone = "";
	private static String howItWasDone = "";
	private static String timeSpent = "";
	private static String effortRequired = "";

	
	/**
	 * Default constructor is not used.
	 */
	public ControllerAddExperienceInformation() {
	}
	
	
	/**********
	 * <p> Method: addExperienceInformation() </p>
	 * 
	 * <p> Description: This method is called when the user presses the add button in the View
	 * class. It registers the experience information of what was done, how it was done, 
	 * how long it took, and the effort used by the various team members to the lesson database. It
	 * uses the information of an existing lesson. The addExperienceInformation method in the
	 * LessonsLearned entity class uses the current lesson's details, which were set by examining
	 * the lesson being added on to by this method. </p>
	 * 
	 */
	protected static void addExperienceInformation() {
		whatWasDone = ViewAddExperienceInformation.text_WhatWasDone.getText();
		howItWasDone = ViewAddExperienceInformation.text_HowItWasDone.getText();
		timeSpent = ViewAddExperienceInformation.text_TimeSpent.getText();
		effortRequired = ViewAddExperienceInformation.text_EffortUsed.getText();
		entityClasses.LessonsLearnedList.addExperienceInformation(whatWasDone, howItWasDone, 
				timeSpent, effortRequired);
    	guiContributor.ViewContributorHome.displayContributorHome(ViewAddExperienceInformation.theStage, 
    			ViewAddExperienceInformation.theUser);
	}
	
	
	/**********
	 * <p> Method: evaluateLessonValidity() </p>
	 * 
	 * <p> Description: This method is called when the contributor presses the add button. It checks
	 * to ensure the text fields contain valid inputs before allowing the lesson to be added to the 
	 * database. Text fields are invalid if they are empty or greater than 255 characters.</p>
	 * 
	 */
	protected static boolean evaluateExpInfoValidity() {
		whatWasDone = ViewAddExperienceInformation.text_WhatWasDone.getText();
		howItWasDone = ViewAddExperienceInformation.text_HowItWasDone.getText();
		timeSpent = ViewAddExperienceInformation.text_TimeSpent.getText();
		effortRequired = ViewAddExperienceInformation.text_EffortUsed.getText();
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Invalid Information");
		if (whatWasDone.length() == 0 || whatWasDone.length() > 255) {
	        alert.setHeaderText("The experience information must include what was done");
	        alert.setContentText("What was done must be greater than 0 characters and fewer than 225 characters");
	        alert.showAndWait();
		}
		else if (howItWasDone.length() == 0 || howItWasDone.length() > 255) {
	        alert.setHeaderText("The experience information must include how it was done");
	        alert.setContentText("How it was done must be greater than 0 characters and fewer than 225 characters");
	        alert.showAndWait();
		}
		else if (timeSpent.length() == 0 || timeSpent.length() > 255) {
	        alert.setHeaderText("The experience information must include the time spent");
	        alert.setContentText("the time spent must be greater than 0 characters and fewer than 225 characters");
	        alert.showAndWait();
		}
		else if (effortRequired.length() == 0 || effortRequired.length() > 255) {
	        alert.setHeaderText("The experience information must include effort used by team members");
	        alert.setContentText("The effort used by team members must be greater than 0 characters and fewer than 225 characters");
	        alert.showAndWait();
		}
		else {
			return true;
		}
		return false;
	}
	
	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user to the List Lessons page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiListAllLessons.ViewListAllLessons.displayListAllLessons(ViewAddExperienceInformation.theStage, 
				ViewAddExperienceInformation.theUser);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewAddExperienceInformation.theStage);
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