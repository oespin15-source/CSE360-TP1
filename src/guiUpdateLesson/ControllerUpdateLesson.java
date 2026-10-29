package guiUpdateLesson;

import javafx.scene.control.Alert;

/*******
 * <p> Title: ControllerUpdateLesson Class. </p>
 * 
 * <p> Description: The Java/FX-based Update Lesson Page.  This class provides the controller
 * actions basic on the user's use of the JavaFX GUI widgets defined by the View class. After 
 * validating the user input, this class will use the new lesson subject header and core information
 * to update the current lesson.
 * 
 * The class has been written assuming that the View or the Model are the only class methods that
 * can invoke these methods.  This is why each has been declared at "protected".  Do not change any
 * of these methods to public.</p>
 * 
 * <p> Copyright: Noah Lesniewski © 2026 </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-02 Initial version
 */
public class ControllerUpdateLesson {
	
	private static String lessonHeader = "";
	private static String lessonBody = "";
	
	/**
	 * Default constructor is not used.
	 */
	public ControllerUpdateLesson() {
	}
	
	
	/**********
	 * <p> Method: updateLesson() </p>
	 * 
	 * <p> Description: This method is called when the contributor presses the update button. It updates 
	 * the lesson information in the database.</p>
	 * 
	 */
	protected static void updateLesson() {
		lessonHeader = ViewUpdateLesson.text_LessonHeader.getText();
		lessonBody = ViewUpdateLesson.text_LessonBody.getText();
		entityClasses.LessonsLearnedList.updateLessonsLearned(lessonHeader, lessonBody);
		guiExamineLesson.ViewExamineLesson.displayExamineLesson(ViewUpdateLesson.theStage, 
				ViewUpdateLesson.theUser, ViewUpdateLesson.theLesson);
	}
	
	
	/**********
	 * <p> Method: evaluateLessonValidity() </p>
	 * 
	 * <p> Description: This method is called when the contributor presses the update button. It checks
	 * to ensure the header and body are valid before allowing the lesson to be updated.</p>
	 * 
	 */
	protected static boolean evaluateLessonValidity() {
		lessonHeader = ViewUpdateLesson.text_LessonHeader.getText();
		lessonBody = ViewUpdateLesson.text_LessonBody.getText();
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
	 * <p> Description: This method returns the user to the Examine Lesson Page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiExamineLesson.ViewExamineLesson.displayExamineLesson(ViewUpdateLesson.theStage, 
				ViewUpdateLesson.theUser, ViewUpdateLesson.theLesson);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewUpdateLesson.theStage);
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