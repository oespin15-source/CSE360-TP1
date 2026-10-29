package guiCreateLesson;

import javafx.scene.control.Alert;

/*******
 * <p> Title: ControllerCreateLesson Class. </p>
 * 
 * <p> Description: The Java/FX-based Create Lesson Home Page.  This class provides the controller
 * actions basic on the user's use of the JavaFX GUI widgets defined by the View class. After 
 * validating the user input, this class will use the lesson subject header and core information
 * to create a new Lesson Learned object and insert it into the database. 
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
public class ControllerCreateLesson {
	
	private static String lessonHeader = "";
	private static String lessonBody = "";
	
	/**
	 * Default constructor is not used.
	 */
	public ControllerCreateLesson() {
	}
	
	
	/**********
	 * <p> Method: createLesson() </p>
	 * 
	 * <p> Description: This method is called when the contributor presses the submit button. 
	 * A contributor can specify the core information inside the lessonHeader and lessonBody 
	 * text fields. It registers the lesson information to the lesson database.</p>
	 * 
	 */
	protected static void createLesson() {
		lessonHeader = ViewCreateLesson.text_LessonHeader.getText();
		lessonBody = ViewCreateLesson.text_LessonBody.getText();
		entityClasses.LessonsLearnedList.createLessonLearned(lessonHeader, lessonBody);
    	guiContributor.ViewContributorHome.displayContributorHome(ViewCreateLesson.theStage, 
    			ViewCreateLesson.theUser);
	}
	
	
	/**********
	 * <p> Method: evaluateLessonValidity() </p>
	 * 
	 * <p> Description: This method is called when the contributor presses the submit button. It checks
	 * to ensure the header and body are valid before allowing the lesson to be added to the database.</p>
	 * 
	 */
	protected static boolean evaluateLessonValidity() {
		lessonHeader = ViewCreateLesson.text_LessonHeader.getText();
		lessonBody = ViewCreateLesson.text_LessonBody.getText();
		Alert alert = new Alert(Alert.AlertType.ERROR);
		if (lessonHeader.length() == 0) {
	        alert.setTitle("Invalid Header");
	        alert.setHeaderText("The header is invalid.");
	        alert.setContentText("The header must be greater than 0 characters");
	        alert.showAndWait();
		}
		else if (lessonHeader.length() > 64) {
	        alert.setTitle("Invalid Header");
	        alert.setHeaderText("The header is invalid.");
	        alert.setContentText("The header must be fewer than 64 characters");
	        alert.showAndWait();
		}
		else if (lessonBody.length() > 255) {
	        alert.setTitle("Invalid Body");
	        alert.setHeaderText("The body is invalid.");
	        alert.setContentText("The body must be fewer than 225 characters");
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
	 * <p> Description: This method returns the user to the Contributor Home Page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiContributor.ViewContributorHome.displayContributorHome(ViewCreateLesson.theStage, 
				ViewCreateLesson.theUser);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewCreateLesson.theStage);
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