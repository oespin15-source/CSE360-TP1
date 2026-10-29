package guiAddExperienceInformation;

import database.Database;
import entityClasses.LessonsLearned;
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
 * <p> Title: ViewAddExperienceInformation Class. </p>
 * 
 * <p> Description: The Java/FX-based Add Experience Information Page. This class provides 
 * the JavaFX GUI widgets that enable a user to input experience information, what
 * was done to leverage a lesson learned, how it was done, how much time it took, and the 
 * effort from team members, and add it to an existing lesson. </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 *  
 */
public class ViewAddExperienceInformation {
	
	/* application values required by the user interface */
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

	/* widget attributes for the GUI Areas */
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// GUI Area 2
	protected static Label label_WhatWasDone = new Label("What was done?");
	protected static TextArea text_WhatWasDone = new TextArea();
	protected static Label label_HowItWasDone = new Label("How was it done?");
	protected static TextArea text_HowItWasDone = new TextArea();
	protected static Label label_TimeSpent = new Label("How long did it take?");
	protected static TextArea text_TimeSpent = new TextArea();
	protected static Label label_EffortUsed = new Label("How much effort from team members");
	protected static TextArea text_EffortUsed = new TextArea();
	protected static Button button_AddInfo = new Button("Add");

	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator4 = new Line(20, 525, width-20,525);
	
	/* widget attributes for the GUI Areas */
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");
	
	private static ViewAddExperienceInformation theView;		
	
	/* Reference for the in-memory database so this package has access */
	private static Database theDatabase = applicationMain.FoundationsMain.database;
	/* The Stage that JavaFX has established for us */
	protected static Stage theStage;			
	/* The Pane that holds all the GUI widgets */
	protected static Pane theRootPane;			
	/* The current logged in User */
	protected static User theUser;
	/* The lesson to be added on to */
	protected static LessonsLearned theLesson;
	
	/* The shared Scene each invocation populates */
	private static Scene theViewAddExperienceInformationScene;	
	

	/**********
	 * <p> Method: displayCreateLesson(Stage ps, User user, LessonsLearned lesson) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the Add Experience Information page to be displayed.
	 * 
	 * It first sets up every shared attributes so we don't have to pass parameters.
	 * 
	 * It then checks to see if the page has been setup.  If not, it instantiates the class, 
	 * initializes all the static aspects of the GUI widgets (e.g., location on the page, font,
	 * size, and any methods to be performed).
	 * 
	 * After the instantiation, the code then populates the elements that change based on the user
	 * and the system's current state.  It then sets the Scene onto the stage, and makes it visible
	 * to the user.
	 * 
	 * Lastly, sets the title for the window, displays the page, and waits for the user to do something.
	 * 
	 * @param ps specifies the JavaFX Stage to be used for this GUI and it's methods
	 * 
	 * @param user specifies the User for this GUI and its methods
	 * 
	 * @param lesson specifies the LessonLearned for this GUI and its methods
	 * 
	 */
	public static void displayAddExperienceInformation(Stage ps, User user, LessonsLearned lesson) {
		
		theStage = ps;
		theUser = user;
		theLesson = lesson;
		
		if (theView == null) theView = new ViewAddExperienceInformation();		
		
		theDatabase.getUserAccountDetails(user.getUserName());
		label_UserDetails.setText("User: " + theUser.getUserName());
		
		text_WhatWasDone.setText("");
		text_HowItWasDone.setText("");
		text_TimeSpent.setText("");
		text_EffortUsed.setText("");
		
		// Place all of the widget items into the Root Pane's list of children
		theRootPane.getChildren().clear();
        theRootPane.getChildren().addAll(
			label_PageTitle, 
	        line_Separator4, button_Logout, button_Quit, button_Return,
	        label_WhatWasDone, text_WhatWasDone, label_HowItWasDone, text_HowItWasDone,
	        label_TimeSpent, text_TimeSpent, label_EffortUsed, text_EffortUsed,
	        button_AddInfo);
				
		theStage.setTitle("CSE 360 Foundations: Add Experience Information Page");
		theStage.setScene(theViewAddExperienceInformationScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewCreateLesson() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object.</p>
	 * 
	 * This is a singleton and is only performed once.  Subsequent uses fill in the changeable
	 * fields using the displayRole2Home method.</p>
	 * 
	 */
	private ViewAddExperienceInformation() {

		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theViewAddExperienceInformationScene = new Scene(theRootPane, width, height);	// Create the scene
		
		// GUI Area 1
		label_PageTitle.setText("Add Experience Information Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
		
		// GUI Area 2
		// label and text area for WhatWasDone
		setupLabelUI(label_WhatWasDone, "Arial", 20, 30, Pos.CENTER, 20, 50);	
		setupTextAreaUI(text_WhatWasDone, "Arial", FontWeight.NORMAL, 20, 760, 60, 20, 80, true, true);
		
		// label and text area for HowItWasDone
		setupLabelUI(label_HowItWasDone, "Arial", 20, 30, Pos.CENTER, 20, 150);
		setupTextAreaUI(text_HowItWasDone, "Arial", FontWeight.NORMAL, 20, 760, 60, 20, 180, true, true);
		
		// label and text area for TimeSpent
		setupLabelUI(label_TimeSpent, "Arial", 20, 30, Pos.CENTER, 20, 250);
		setupTextAreaUI(text_TimeSpent, "Arial", FontWeight.NORMAL, 20, 760, 60, 20, 280, true, true);
		
		// label and text area for EffortUsed
		setupLabelUI(label_EffortUsed, "Arial", 20, 30, Pos.CENTER, 20, 350);
		setupTextAreaUI(text_EffortUsed, "Arial", FontWeight.NORMAL, 20, 760, 60, 20, 380, true, true);
				
		// Button to post the lesson
		setupButtonUI(button_AddInfo, "Dialog", 18, 210, Pos.CENTER, 570, 475);
		button_AddInfo.setOnAction((_) -> {
			if (ControllerAddExperienceInformation.evaluateExpInfoValidity() == true) {
				ControllerAddExperienceInformation.addExperienceInformation(); } });
		
		// GUI Area 3
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerAddExperienceInformation.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerAddExperienceInformation.performLogout(); });
		    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerAddExperienceInformation.performQuit(); });
		
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
	private static void setupTextAreaUI(TextArea t, String ff, FontWeight fw, double f, double w, double h, double x, double y, boolean e, boolean r){
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