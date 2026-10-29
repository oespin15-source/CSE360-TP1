package guiListAllLessons;

import javafx.scene.control.Alert;

/*******
 * <p> Title: ControllerContributorHome Class. </p>
 * 
 * <p> Description: The Java/FX-based Contributor Home Page. This class provides the controller
 * actions basic on the user's use of the JavaFX GUI widgets defined by the View class. This class 
 * prints the appropriate visibility status based on whether the experience information should be 
 * visible. It also sets and monitors a filter to help the list be populated based on the 
 * user's input. 
 * 
 * The class has been written assuming that the View or the Model are the only class methods that
 * can invoke these methods.  This is why each has been declared at "protected".  Do not change any
 * of these methods to public.</p>
 * 
 * <p> Copyright: Noah Lesniewski © 2026 </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 */

public class ControllerListAllLessons {
	
	private static String lessonFilter = "";
	
	/**
	 * Default constructor is not used.
	 */
	public ControllerListAllLessons() {
	}
	
	
	/**********
	 * <p> Method: getVisibilityStatus(boolean visibility) </p>
	 * 
	 * <p> Description: The method returns the word "Visible" if the lesson experience
	 * information is visible or it returns "Invisible" if it is invisible. </p>
	 * 
	 * @param visibility is the visibility associated with the lesson being checked
	 * 
	 * @return visibilityStatus specifies the visibility status of the current lesson
	 * 
	 */
	protected static String getVisibilityStatus (boolean visibility) {
		String visibilityStatus;
		if (visibility == true) {
			visibilityStatus = "Visible"; 
		}
		else {
			visibilityStatus = "Invisible";
		}
		return visibilityStatus;
	}
	
	
	/**********
	 * <p> Method: setLessonFilter() </p>
	 * 
	 * <p> Description: This method is called when the user types in the filter text field. 
	 * A private local copy of the filter is kept here.</p>
	 * 
	 */
	protected static void setLessonFilter() {
		lessonFilter = ViewListAllLessons.text_filter.getText();
	}
	
	
	/**********
	 * <p> Method: getLessonFilter() </p>
	 * 
	 * <p> Description: This method is called when the user presses the filter button. 
	 * It returns the lesson filter value.</p>
	 * 
	 */
	protected static String getLessonFilter() {
		return lessonFilter;
	}
	
	
	/**********
	 * <p> Method: evaluateFilterValidity() </p>
	 * 
	 * <p> Description: This method is called when the contributor presses the filter button. It ensures
	 * the filter cannot be more than 64 characters.</p>
	 * 
	 */
	protected static boolean evaluateFilterValidity() {
		String theLessonFilter = ViewListAllLessons.text_filter.getText();
		Alert alert = new Alert(Alert.AlertType.ERROR);
		if (theLessonFilter.length() > 64) {
			alert.setTitle("Invalid Filter");
	        alert.setHeaderText("The filter is invalid.");
	        alert.setContentText("The filter must be fewer than 64 characters");
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
	 * <p> Description: This method returns the user to the home page of the role they are 
	 * currently using. </p>
	 * 
	 */
	protected static void performReturn() {
		if (ViewListAllLessons.theUser.getActiveRole().compareTo("Contributor") == 0) {
			guiContributor.ViewContributorHome.displayContributorHome(ViewListAllLessons.theStage, 
					ViewListAllLessons.theUser);
		}
		else if (ViewListAllLessons.theUser.getActiveRole().compareTo("Role1") == 0) {
			guiRole1.ViewRole1Home.displayRole1Home(ViewListAllLessons.theStage, 
				ViewListAllLessons.theUser);
		}
		else if (ViewListAllLessons.theUser.getActiveRole().compareTo("Role2") == 0) {
			guiRole2.ViewRole2Home.displayRole2Home(ViewListAllLessons.theStage, 
					ViewListAllLessons.theUser);
		}
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewListAllLessons.theStage);
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
	
	
	/**********
	 * <p> Method: performCreateLesson() </p>
	 * 
	 * <p> Description: This method directs the user to the Create Lesson Page so the user can create
	 * a new lesson. </p>
	 * 
	 */
	protected static void performCreateLesson () {
		guiCreateLesson.ViewCreateLesson.displayCreateLesson(ViewListAllLessons.theStage , ViewListAllLessons.theUser);
	}	
}