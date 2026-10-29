package guiExamineLesson;



/*******
 * <p> Title: ControllerExamineLesson Class. </p>
 * 
 * <p> Description: The Java/FX-based Create Lesson Home Page.  This class provides the controller
 * actions basic on the user's use of the JavaFX GUI widgets defined by the View class. This class 
 * prints the appropriate locked status based on whether the lesson header or body text fields have 
 * been locked. 
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
public class ControllerExamineLesson {
	
	/**
	 * Default constructor is not used.
	 */
	public ControllerExamineLesson() {
	}
	
	
	/**********
	 * <p> Method: getLockStatus(boolean lock) </p>
	 * 
	 * <p> Description: The method returns the word "Locked" if the text field is locked or it
	 * returns "Unlocked" if it is unlocked. </p>
	 * 
	 * @param lock is the lock associated with the text field being checked
	 * 
	 * @return lockStatus specifies the locked status of the current lock
	 * 
	 */
	protected static String getLockStatus (boolean lock) {
		String lockStatus;
		if (lock == true) {
			lockStatus = "Locked"; 
		}
		else {
			lockStatus = "Unlocked";
		}
		return lockStatus;
	}
	
	
	/**********
	 * <p> Method: performReturn() </p>
	 * 
	 * <p> Description: This method returns the user to the List Lessons Page. </p>
	 * 
	 */
	protected static void performReturn() {
		guiListAllLessons.ViewListAllLessons.displayListAllLessons(ViewExamineLesson.theStage, 
				ViewExamineLesson.theUser);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewExamineLesson.theStage);
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
