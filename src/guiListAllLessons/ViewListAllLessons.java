package guiListAllLessons;

import database.Database;
import entityClasses.LessonsLearned;
import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*******
 * <p> Title: ViewListAllLessons Class. </p>
 * 
 * <p> Description: The Java/FX-based System Startup Page. This class provides 
 * the JavaFX GUI widgets that enable a user to view a list of the lessons learned and 
 * examine the lessons for more details. The Lesson will display different information 
 * and enable different features depending on the role currently being used by the user.
 * Other roles can set visibility on experience information so contributors may not see them. </p>
 * 
 * <p> Copyright: Noah Lesniewski © 2026 </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 *  
 */
public class ViewListAllLessons {
	
	/* application values required by the user interface */
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

	/* widget attributes for the GUI Areas */
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// Area 2: This is used to input text into the lesson text fields
	protected static TextField text_filter = new TextField();
	protected static ListView<LessonsLearned> list_LessonList = new ListView<>();
	protected static Button button_Filter = new Button("Filter");

	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator4 = new Line(20, 525, width-20,525);
	
	/* widget attributes for the GUI Areas */
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");

	private static ViewListAllLessons theView;		
	
	/* Reference for the in-memory database so this package has access */
	private static Database theDatabase = applicationMain.FoundationsMain.database;

	/* The Stage that JavaFX has established for us */
	protected static Stage theStage;			
	/* The Pane that holds all the GUI widgets */
	protected static Pane theRootPane;			
	/* The current logged in User */
	protected static User theUser;				

	/* The shared Scene each invocation populates */
	private static Scene theViewListAllLessonsScene;	

	/**********
	 * <p> Method: displayListAllLessons(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the List Lessons page to be displayed.
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
	public static void displayListAllLessons(Stage ps, User user) {
		
		theStage = ps;
		theUser = user;
		
		if (theView == null) theView = new ViewListAllLessons();		
				
		list_LessonList.getItems().clear();
		list_LessonList.setItems(entityClasses.LessonsLearnedList.listLessonsLearned(ControllerListAllLessons.getLessonFilter(), theUser));
		
		theDatabase.getUserAccountDetails(user.getUserName());		
		label_UserDetails.setText("User: " + theUser.getUserName());
		
		// Place all of the widget items into the Root Pane's list of children
		theRootPane.getChildren().clear();
        theRootPane.getChildren().addAll(
			label_PageTitle, label_UserDetails, line_Separator1,
	        line_Separator4, button_Return, button_Logout, button_Quit, list_LessonList, 
	        text_filter, button_Filter);
				
		theStage.setTitle("CSE 360 Foundations: List Lessons Page");
		theStage.setScene(theViewListAllLessonsScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewListAllLessons() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object.</p>
	 * 
	 * This is a singleton and is only performed once.  Subsequent uses fill in the changeable
	 * fields using the displayListAllLessons method.</p>
	 * 
	 */
	private ViewListAllLessons() {

		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theViewListAllLessonsScene = new Scene(theRootPane, width, height);	// Create the scene
		
		// GUI Area 1
		label_PageTitle.setText("List Lessons Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
		
		// GUI Area 2
		setupTextUI(text_filter, "Arial", 20, 550, Pos.BASELINE_LEFT, 20, 100, true);
		text_filter.textProperty().addListener((_, _, _) 
				-> {ControllerListAllLessons.setLessonFilter(); });
		
		setupButtonUI(button_Filter, "Dialog", 18, 200, Pos.CENTER, 580, 100);
		button_Filter.setOnAction((_) -> {
			if (ControllerListAllLessons.evaluateFilterValidity() == true) {
				ViewListAllLessons.displayListAllLessons(theStage, theUser); } });
		
		setupListUI(list_LessonList, "Dialog", 12, width, 375, 0, 145);
		
		// Create a custom display for every lesson list cell
		list_LessonList.setCellFactory((_) -> new ListCell<LessonsLearned>() {
			// Buttons GUI
			private final Button examineButton = new Button("Examine");
			private final Button visibilityButton = new Button("Visibility");
			// Lesson Header and Body GUI
			private final Label label_LessonTitle = new Label("Lesson");
			private final Label label_HeaderTitle = new Label("Subject");
			private final Label header = new Label();
			private final Label label_BodyTitle = new Label("Core Information");
			private final Label body = new Label();
			// Experience Info GUI
			private final Label label_ExpInfoTitle = new Label("Experience Information");
			private final Label label_WhatTitle = new Label("What was done");
			private final Label what = new Label();
			private final Label label_HowTitle = new Label("How it was done");
			private final Label how = new Label();
			private final Label label_TimeTitle = new Label("Time Spent");
			private final Label time = new Label();
			private final Label label_EffortTitle = new Label("Effort Used");
			private final Label effort = new Label();
			private final Label label_Visibility = new Label();
			// Grouping GUI elements
			private final HBox lessonButtons = new HBox(5, examineButton); 
			private final HBox expInfoButtons = new HBox(5, label_Visibility, visibilityButton); 
			private final VBox lessonText = new VBox(5, label_LessonTitle, label_HeaderTitle, 
				    header, label_BodyTitle, body);
			private final VBox expInfoText = new VBox(5, label_ExpInfoTitle, label_WhatTitle,
				    what, label_HowTitle, how, label_TimeTitle, time, label_EffortTitle, effort);
				    
			private final BorderPane pane = new BorderPane();
			// Initializer block to configure the buttons and layout of every cell
			{
				label_LessonTitle.setStyle("-fx-font-weight: bold;");
				label_LessonTitle.setStyle("-fx-font-size: 16 px");
				label_HeaderTitle.setStyle("-fx-font-weight: bold;");
				label_BodyTitle.setStyle("-fx-font-weight: bold;");
				label_ExpInfoTitle.setStyle("-fx-font-weight: bold;");
				label_ExpInfoTitle.setStyle("-fx-font-size: 16 px");
				label_WhatTitle.setStyle("-fx-font-weight: bold;");
				label_HowTitle.setStyle("-fx-font-weight: bold;");
				label_TimeTitle.setStyle("-fx-font-weight: bold;");
				label_EffortTitle.setStyle("-fx-font-weight: bold;");
				label_Visibility.setStyle("-fx-font-weight: bold;");
				label_Visibility.setStyle("-fx-font-size: 16 px");
				lessonText.setMaxWidth(width-20);
				expInfoText.setMaxWidth(width-70);
				header.setWrapText(true);
				body.setWrapText(true);
				what.setWrapText(true);
				how.setWrapText(true);
				time.setWrapText(true);
				effort.setWrapText(true);

				// Visibility button configuration
				visibilityButton.setOnAction((_) -> {
					LessonsLearned lesson = getItem();		// gets the lesson on the current row
				    lesson.setInfoVisibility(!lesson.getInfoVisibility());
				    theDatabase.setExpInfoVisibility(lesson.getID(), lesson.getInfoVisibility());
				    label_Visibility.setText(ControllerListAllLessons.getVisibilityStatus(lesson.getInfoVisibility()));
				});
				
				// Examine button configuration
				examineButton.setOnAction((_) -> {
					LessonsLearned lesson = getItem();	// gets the lesson on the current row
					entityClasses.LessonsLearnedList.establishLessonDetails(lesson);
					guiExamineLesson.ViewExamineLesson.displayExamineLesson(theStage, theUser, lesson);
				});
			} // The end of the initializer block

			// For every cell JavaFX automatically displays a particular lesson by 
			// updating the cell's header, body, buttons, layout, and whether it should be visible.
			@Override
			protected void updateItem(LessonsLearned lesson, boolean empty) {
				super.updateItem(lesson, empty);	// calls the original ListCell implementation as well as these additions
				
				if (empty || lesson == null) { 		// check for an empty cell
					setGraphic(null);				// doesn't display the custom display for empty cells
				    setText(null);
				    return;
				}
				// if the row isn't for experience information
				else if (lesson.getWhatWasDone() == null || lesson.getWhatWasDone().isEmpty()) {
					pane.setCenter(lessonText);
					pane.setRight(lessonButtons);
				    header.setText(lesson.getLessonHeader());
				    body.setText(lesson.getLessonBody());
				} 
				// if the row is for experience information
				else {
					pane.setCenter(expInfoText);
					// other roles can change the visibility of experience information 
					if (theUser.getActiveRole().compareTo("Contributor") != 0) {
						pane.setRight(expInfoButtons);
					}
				    else {
				        pane.setRight(null);
				    }
				    what.setText(lesson.getWhatWasDone());
				    how.setText(lesson.getHowItWasDone());
				    time.setText(lesson.getTimeSpent());
				    effort.setText(lesson.getEffortUsed());
				    label_Visibility.setText(ControllerListAllLessons.getVisibilityStatus(lesson.getInfoVisibility()));
				}
				// Set the custom display if not empty
				setGraphic(pane);
			} 
		});	// The end of the cell factory
		
		// GUI Area 3
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerListAllLessons.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerListAllLessons.performLogout(); });
				    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerListAllLessons.performQuit(); });

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
	 * Private local method to initialize the standard fields for a text input field
	 * 
	 * @param b		The TextField object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the Button
	 * @param p		The alignment (e.g. left, centered, or right)
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 * @param e		Is this TextField user editable?
	 */
	private void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));
		t.setMinWidth(w);
		t.setMaxWidth(w);
		t.setAlignment(p);
		t.setLayoutX(x);
		t.setLayoutY(y);		
		t.setEditable(e);
	}
	
	
	/**********
	 * Private local method to initialize the standard fields for a list
	 * 
	 * @param l		The list object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the list
	 * @param h		The height of the list
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupListUI(ListView<LessonsLearned> l, String ff, double f, double w, double h, double x, double y) {
		l.setStyle("-fx-font-family: " + ff);
		l.setStyle("-fx-font-size: " + f + "px");
	    l.setMinWidth(w);
	    l.setMinHeight(h);
	    l.setMaxHeight(h);
	    l.setLayoutX(x);
	    l.setLayoutY(y);
	}
}
