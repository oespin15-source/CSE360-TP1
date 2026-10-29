package guiContributor;

import database.Database;
import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*******
 * <p> Title: ViewContributorHome Class. </p>
 * 
 * <p> Description: The Java/FX-based System Startup Page. This class provides 
 * the JavaFX GUI widgets that enable a contributor to create new lessons and 
 * view all of their contributions.</p>
 * 
 * <p> Copyright: Noah Lesniewski © 2026 </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 *  
 */
public class ViewContributorHome {
	
	/* application values required by the user interface */
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

	/* widget attributes for the GUI Areas */
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	protected static Button button_UpdateThisUser = new Button("Account Update");
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// Area 2
	protected static Button button_CreateLesson = new Button("Create Lesson");
	protected static Button button_ListLesson = new Button("List Lesson");

	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator4 = new Line(20, 525, width-20,525);
	
	/* widget attributes for the GUI Areas */
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");

	private static ViewContributorHome theView;		
	
	/* Reference for the in-memory database so this package has access */
	private static Database theDatabase = applicationMain.FoundationsMain.database;

	/* The Stage that JavaFX has established for us */
	protected static Stage theStage;			
	/* The Pane that holds all the GUI widgets */
	protected static Pane theRootPane;			
	/* The current logged in User */
	protected static User theUser;				

	/* The shared Scene each invocation populates */
	private static Scene theViewContributorHomeScene;	
	/* Admin: 1; Role1: 2; Role2: 3 Contributor: 4 */
	protected static final int theRole = 4;		
    

	/**********
	 * <p> Method: displayContributorHome(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the Contributor Home page to be displayed.
	 * 
	 * It first sets up every shared attributes so we don't have to pass parameters.
	 * 
	 * It then checks to see if the page has been setup.  If not, it instantiates the class, 
	 * initializes all the static aspects of the GIUI widgets (e.g., location on the page, font,
	 * size, and any methods to be performed).
	 * 
	 * After the instantiation, the code then populates the elements that change based on the user
	 * and the system's current state.  It then sets the Scene onto the stage, and makes it visible
	 * to the user.
	 * 
	 * Lastly, sets the title for the window, displays the page, and waits for the Admin to do something.
	 * 
	 * @param ps specifies the JavaFX Stage to be used for this GUI and it's methods
	 * 
	 * @param user specifies the User for this GUI and it's methods
	 * 
	 */
	public static void displayContributorHome(Stage ps, User user) {
		
		theStage = ps;
		theUser = user;
		
		if (theView == null) theView = new ViewContributorHome();		
		
		theDatabase.getUserAccountDetails(user.getUserName());
		applicationMain.FoundationsMain.activeHomePage = theRole;
		
		label_UserDetails.setText("User: " + theUser.getUserName());
		
		// Place all of the widget items into the Root Pane's list of children
		theRootPane.getChildren().clear();
        theRootPane.getChildren().addAll(
			label_PageTitle, label_UserDetails, button_UpdateThisUser, line_Separator1,
	        line_Separator4, button_Logout, button_Quit, button_CreateLesson,
	        button_ListLesson);
				
		theStage.setTitle("CSE 360 Foundations: Contributor Home Page");
		theStage.setScene(theViewContributorHomeScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewContributorHome() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object.</p>
	 * 
	 * This is a singleton and is only performed once. Subsequent uses fill in the changeable
	 * fields using the displayContributorHome method.</p>
	 * 
	 */
	private ViewContributorHome() {

		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theViewContributorHomeScene = new Scene(theRootPane, width, height);	// Create the scene
		
		// GUI Area 1
		label_PageTitle.setText("Contributor Home Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
		
		setupButtonUI(button_UpdateThisUser, "Dialog", 18, 170, Pos.CENTER, 610, 45);
		button_UpdateThisUser.setOnAction((_) -> {ControllerContributorHome.performUpdate(); });
		
		// GUI Area 2
		setupButtonUI(button_CreateLesson, "Dialog", 18, 200, Pos.CENTER, 300, 200);
        button_CreateLesson.setOnAction((_) -> {ControllerContributorHome.performCreateLesson(); });
        
        setupButtonUI(button_ListLesson, "Dialog", 18, 200, Pos.CENTER, 300, 250);
        button_ListLesson.setOnAction((_) -> {ControllerContributorHome.performListLessons(); });
		
		// GUI Area 3
        setupButtonUI(button_Logout, "Dialog", 18, 250, Pos.CENTER, 20, 540);
        button_Logout.setOnAction((_) -> {ControllerContributorHome.performLogout(); });
        
        setupButtonUI(button_Quit, "Dialog", 18, 250, Pos.CENTER, 300, 540);
        button_Quit.setOnAction((_) -> {ControllerContributorHome.performQuit(); });

		// This is the end of the GUI initialization code
	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 * 
	 * @param l		The Label object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private static void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, 
			double y){
		l.setFont(Font.font(ff, f));
		l.setMinWidth(w);
		l.setAlignment(p);
		l.setLayoutX(x);
		l.setLayoutY(y);		
	}
	
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 * 
	 * @param b		The Button object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private static void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, 
			double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
}