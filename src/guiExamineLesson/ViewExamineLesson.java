package guiExamineLesson;

import database.Database;
import entityClasses.LessonsLearned;
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
 * <p> Title: ViewExamineLesson Class. </p>
 * 
 * <p> Description: The Java/FX-based Create Lesson Page. This class provides 
 * the JavaFX GUI widgets that enable a user to view more detailed about a particular
 * lesson. It allows the user to determine if the lesson header or lesson body fields 
 * are locked. It also allows the user to delete the lesson, add experience information
 * on to the lesson, and update the lesson header or lesson body if they are 
 * unlocked.</p>
 * 
 * <p> Copyright: Noah Lesniewski © 2026 </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 *  
 */
public class ViewExamineLesson {
	
	/* application values required by the user interface */
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

	/* widget attributes for the GUI Areas */
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// Area 2
	protected static Label label_LessonHeader = new Label("Subject Header:");
	protected static Label text_LessonHeader = new Label();
	protected static Label label_LessonBody = new Label("Core Information:");
	protected static Label text_LessonBody = new Label();
	protected static Button button_UpdateLesson = new Button("Update");
	protected static Label text_LessonHeaderLock = new Label();
	protected static Label text_LessonBodyLock = new Label();
	protected static Button button_LessonHeaderLock = new Button("Toggle Lock");
	protected static Button button_LessonBodyLock = new Button("Toggle Lock");
	protected static Button button_AddExpInfo = new Button("Add Exp Info");
	protected static Button button_deleteLesson = new Button("Delete");
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator4 = new Line(20, 525, width-20,525);
	
	/* widget attributes for the GUI Areas */
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");
	
	private static ViewExamineLesson theView;		
	
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
	private static Scene theViewExamineLessonScene;
	
	
	/**********
	 * <p> Method: displayExamineLesson(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the Examine Lesson page to be displayed.
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
	public static void displayExamineLesson(Stage ps, User user, LessonsLearned lesson) {
	
		theStage = ps;
		theUser = user;
		theLesson = lesson;
		
		if (theView == null) theView = new ViewExamineLesson();		
		
		theDatabase.getUserAccountDetails(user.getUserName());
		label_UserDetails.setText("User: " + theUser.getUserName());
		
		text_LessonHeader.setText(theDatabase.getCurrentLessonHeader());
		text_LessonBody.setText(theDatabase.getCurrentLessonBody());
		text_LessonHeaderLock.setText(ControllerExamineLesson.getLockStatus(theLesson.getLessonHeaderLock()));
		text_LessonBodyLock.setText(ControllerExamineLesson.getLockStatus((theLesson.getLessonBodyLock())));
		System.out.println(theLesson.getLessonHeaderLock());

		
		// Place all of the widget items into the Root Pane's list of children
		theRootPane.getChildren().clear();
        theRootPane.getChildren().addAll(
			label_PageTitle, label_UserDetails, line_Separator1,
	        line_Separator4, button_Logout, button_Quit, button_Return,
	        label_LessonHeader, text_LessonHeader, label_LessonBody, text_LessonBody,
	        button_UpdateLesson, text_LessonHeaderLock, text_LessonBodyLock,
	        button_AddExpInfo, button_deleteLesson);
        
        // Other roles may lock the lessonHeader or lessonBody text fields, so the contributor 
        // is unable to alter them
        if (theUser.getActiveRole().compareTo("Role1") == 0) {
        	theRootPane.getChildren().addAll(button_LessonHeaderLock, button_LessonBodyLock);
        }
				
		theStage.setTitle("CSE 360 Foundations: Examine Lesson Page");
		theStage.setScene(theViewExamineLessonScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewExamineLesson() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object.</p>
	 * 
	 * This is a singleton and is only performed once.  Subsequent uses fill in the changeable
	 * fields using the displayExamineLesson method.</p>
	 * 
	 */
	private ViewExamineLesson() {

		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theViewExamineLessonScene = new Scene(theRootPane, width, height);	// Create the scene
		
		// GUI Area 1
		label_PageTitle.setText("Examine Lesson Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
		
		// GUI Area 2
		setupLabelUI(label_LessonHeader, "Arial", 20, 30, Pos.CENTER, 20, 120);
						
		// Establish the text input operand field for lesson header
		setupLabelUI(text_LessonHeader, "Arial", 20, 30, Pos.CENTER, 20, 150);
				
		setupLabelUI(label_LessonBody, "Arial", 20, 30, Pos.CENTER, 20, 200);
				
		// Establish the text input operand field for lesson body
		setupLabelUI(text_LessonBody, "Arial", 20, 30, Pos.CENTER, 20, 230);
		
		// Establish the header lock		
		setupLabelUI(text_LessonHeaderLock, "Arial", 20, 30, Pos.CENTER, 200, 120);
		setupButtonUI(button_LessonHeaderLock, "Dialog", 18, 150, Pos.CENTER, width-180, 120);
		button_LessonHeaderLock.setOnAction((_) -> {
			theLesson.setLessonHeaderLock(!theLesson.getLessonHeaderLock());
			theDatabase.setHeaderLock(theLesson.getID(), theLesson.getLessonHeaderLock());
			text_LessonHeaderLock.setText(ControllerExamineLesson.getLockStatus((theLesson.getLessonHeaderLock())));
		});
		
		// Establish the body lock
		setupLabelUI(text_LessonBodyLock, "Arial", 20, 30, Pos.CENTER, 200, 200);
		setupButtonUI(button_LessonBodyLock, "Dialog", 18, 150, Pos.CENTER, width-180, 200);
		button_LessonBodyLock.setOnAction((_) -> {
			theLesson.setLessonBodyLock(!theLesson.getLessonBodyLock());
			theDatabase.setBodyLock(theLesson.getID(), theLesson.getLessonBodyLock());
			text_LessonBodyLock.setText(ControllerExamineLesson.getLockStatus((theLesson.getLessonBodyLock())));
		});
		
		// Button to post the lesson
		setupButtonUI(button_UpdateLesson, "Dialog", 18, 210, Pos.CENTER, 570, 475);
		button_UpdateLesson.setOnAction((_) -> {
			guiUpdateLesson.ViewUpdateLesson.displayUpdateLesson(theStage, theUser, theLesson); });
		
		setupButtonUI(button_AddExpInfo, "Dialog", 18, 210, Pos.CENTER, 300, 475);
		button_AddExpInfo.setOnAction((_) -> {
			guiAddExperienceInformation.ViewAddExperienceInformation.displayAddExperienceInformation(theStage, theUser, theLesson); });
		
		setupButtonUI(button_deleteLesson, "Dialog", 18, 210, Pos.CENTER, 20, 475);
		button_deleteLesson.setOnAction((_) -> {
			entityClasses.LessonsLearnedList.deleteLessonsLearned(theLesson);
			guiListAllLessons.ViewListAllLessons.displayListAllLessons(theStage, theUser); });
		
		// GUI Area 3
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerExamineLesson.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerExamineLesson.performLogout(); });
				    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerExamineLesson.performQuit(); });
				
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