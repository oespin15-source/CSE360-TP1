package guiContributor;

/*******
 * <p> Title: ControllerContributorHome Class. </p>
 * 
 * <p> Description: The Java/FX-based Contributor Home Page.  This class provides the controller
 * actions basic on the user's use of the JavaFX GUI widgets defined by the View class. The
 * contributor can create new lessons and view all of their contributions. 
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
public class ControllerContributorHome {
	
	
	/**
	 * Default constructor is not used.
	 */
	public ControllerContributorHome() {
	}
	
	
	/**********
	 * <p> Method: performCreateLesson() </p>
	 * 
	 * <p> Description: This method directs the user to the Create Lesson Page so the user can create
	 * a new lesson. </p>
	 * 
	 */
	protected static void performCreateLesson () {
		guiCreateLesson.ViewCreateLesson.displayCreateLesson(ViewContributorHome.theStage, 
				ViewContributorHome.theUser);
	}
	
	
	/**********
	 * <p> 
	 * 
	 * Title: performListLessons () Method. </p>
	 * 
	 * <p> Description: This method directs the user to the List Lessons Page so the user can view
	 * the lessons they have contributed.</p>
	 */
	protected static void performListLessons () {
		guiListAllLessons.ViewListAllLessons.displayListAllLessons(ViewContributorHome.theStage, 
				ViewContributorHome.theUser);
	}
	
	
	/**********
	 * <p> Method: performUpdate() </p>
	 * 
	 * <p> Description: This method directs the user to the User Update Page so the user can change
	 * the user account attributes. </p>
	 * 
	 */
	protected static void performUpdate () {
		guiUserUpdate.ViewUserUpdate.displayUserUpdate(ViewContributorHome.theStage, 
				ViewContributorHome.theUser);
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
		guiUserLogin.ViewUserLogin.displayUserLogin(ViewContributorHome.theStage);
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