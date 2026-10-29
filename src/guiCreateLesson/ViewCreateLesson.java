package guiCreateLesson;

import database.Database;
import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/*******
 * <p> Title: ViewContributorHome Class. </p>
 * 
 * <p> Description: The Java/FX-based Create Lesson Page. This class provides 
 * the JavaFX GUI widgets that enable a user to add new lessons learned to the 
 * system, specifying core information. A new lesson is comprised of a lesson
 * header and lesson body to contain the core information. </p>
 * 
 * <p> Copyright: Noah Lesniewski © 2026 </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 *  
 */
public class ViewCreateLesson {
	
	/* application values required by the user interface */
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

	/* widget attributes for the GUI Areas */
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// Area 2: This is used to input text into the lesson text fields
	protected static Label label_LessonHeader = new Label("Subject Header:");
	protected static TextArea text_LessonHeader = new TextArea();
	protected static Label label_LessonBody = new Label("Core Information:");
	protected static TextArea text_LessonBody = new TextArea();
	protected static Button button_SubmitLesson = new Button("Create");

	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator4 = new Line(20, 525, width-20,525);
	
	/* widget attributes for the GUI Areas */
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");
	
	private static ViewCreateLesson theView;		
	
	/* Reference for the in-memory database so this package has access */
	private static Database theDatabase = applicationMain.FoundationsMain.database;

	/* The Stage that JavaFX has established for us */
	protected static Stage theStage;			
	/* The Pane that holds all the GUI widgets */
	protected static Pane theRootPane;			
	/* The current logged in User */
	protected static User theUser;				
	
	/* The shared Scene each invocation populates */
	private static Scene theViewCreateLessonScene;	
	

	/**********
	 * <p> Method: displayCreateLesson(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the Create Lesson page to be displayed.
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
	public static void displayCreateLesson(Stage ps, User user) {
		
		theStage = ps;
		theUser = user;
		
		if (theView == null) theView = new ViewCreateLesson();		
		
		theDatabase.getUserAccountDetails(user.getUserName());
		label_UserDetails.setText("User: " + theUser.getUserName());
		
		text_LessonHeader.setText("");
		text_LessonBody.setText("");
		
		// Place all of the widget items into the Root Pane's list of children
		theRootPane.getChildren().clear();
        theRootPane.getChildren().addAll(
			label_PageTitle, label_UserDetails, line_Separator1,
	        line_Separator4, button_Logout, button_Quit, button_Return,
	        label_LessonHeader, text_LessonHeader, label_LessonBody, text_LessonBody,
	        button_SubmitLesson);
				
		theStage.setTitle("CSE 360 Foundations: Create Lesson Page");
		theStage.setScene(theViewCreateLessonScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewCreateLesson() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object.</p>
	 * 
	 * This is a singleton and is only performed once. Subsequent uses fill in the changeable
	 * fields using the displayCreateLesson method.</p>
	 * 
	 */
	private ViewCreateLesson() {

		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theViewCreateLessonScene = new Scene(theRootPane, width, height);	// Create the scene
		
		// GUI Area 1
		label_PageTitle.setText("Create Lesson Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
		
		// GUI Area 2
		setupLabelUI(label_LessonHeader, "Arial", 20, 30, Pos.CENTER, 20, 120);
				
		// Establish the text input operand field for lesson header
		setupTextAreaUI(text_LessonHeader, "Arial", FontWeight.BOLD, 20, 760, 35, 20, 150, true, true);
		
		setupLabelUI(label_LessonBody, "Arial", 20, 30, Pos.CENTER, 20, 200);
		
		// Establish the text input operand field for lesson body
		setupTextAreaUI(text_LessonBody, "Arial", FontWeight.NORMAL, 20, 760, 230, 20, 230, true, true);
				
		// Button to post the lesson
		setupButtonUI(button_SubmitLesson, "Dialog", 18, 210, Pos.CENTER, 570, 475);
		button_SubmitLesson.setOnAction((_) -> {
			if (ControllerCreateLesson.evaluateLessonValidity() == true) {
				ControllerCreateLesson.createLesson(); } });
		
		// GUI Area 3
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerCreateLesson.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerCreateLesson.performLogout(); });
		    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerCreateLesson.performQuit(); });
		
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
	
	
	/**********
	 * Private local method to initialize the standard fields for a text input area
	 * 
	 * @param b		The TextField object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param fw	The font weight to be used
	 * @param w		The width of the Button
	 * @param w		The height of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 * @param e		Is this TextArea user editable?
	 * @param r		Does this TextArea have wrap text?
	 */
	private void setupTextAreaUI(TextArea t, String ff, FontWeight fw, double f, double w, double h, double x, double y, boolean e, boolean r){
		t.setFont(Font.font(ff, fw, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setMinHeight(h);
		t.setMaxHeight(h);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
		t.setWrapText(r);
	}
}