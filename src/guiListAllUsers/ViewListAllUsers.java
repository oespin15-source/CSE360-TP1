package guiListAllUsers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * <p> Title: ViewListAllUsers Class. </p>
 * 
 * <p> Description: The Java/FX-based System Startup Page.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-04-20 Initial version
 * @param <JFrame>
 *  
 */

public class ViewListAllUsers {
	
	/* application values required by the user interface */	
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;
	
	/* widget attributes for the GUI */	
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	protected static Button button_UpdateThisUser = new Button("Account Update");
		
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
		
	/* Area 2: This is a table that displays all the users information */	
	protected static TableView<String[]> table_userTable = new TableView<>();
	protected static TableColumn<String[], String> usernameColumn = new TableColumn<>("Username");
	protected static TableColumn<String[], String> nameColumn = new TableColumn<>("Name");
	protected static TableColumn<String[], String> emailColumn = new TableColumn<>("Email");
	protected static TableColumn<String[], String> roleColumn = new TableColumn<>("Role");
	
	/* separator and it is used to partition the GUI for various tasks */
	protected static Line line_Separator3 = new Line(20, 525, width-20,525);
		
	/* widget attributes for the GUI Area 3 */
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");
		
	/* The Stage that JavaFX has established for us */
	protected static Stage theStage;			
	/* The Pane that holds all the GUI widgets */
	protected static Pane theRootPane;			 
	/* The Scene each invocation populates */
	public static Scene theViewSetOneTimePasswordScene = null;
	/* Used to determine if instantiation of the class */
	private static ViewListAllUsers theView;	
	/* The current user of the application */
	 protected static User theUser;				
	
	
	/**********
	 * <p> Method: displayListAllUsers(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the ListAllUsers page to be displayed.
	 * 
	 * It first sets up very shared attributes so we don't have to pass parameters.
	 * 
	 * It then checks to see if the page has been setup.  If not, it instantiates the class, 
	 * initializes all the static aspects of the GUI widgets (e.g., location on the page, font,
	 * size, and any methods to be performed).
	 * 
	 * After the instantiation, the code then populates the elements that change based on the user
	 * and the system's current state.  It then sets the Scene onto the stage, and makes it visible
	 * to the user.
	 * 
	 * Lastly, set the title for the window, display the page, and wait for the Admin to do something.
	 * 
	 * @param ps specifies the JavaFX Stage to be used for this GUI and it's methods
	 *
	 */
	public static void displayListAllUsers (Stage ps, User user) {
		
		theStage = ps;
		theUser = user;
		
		if (theView == null) theView = new ViewListAllUsers();
		
		ControllerListAllUsers.setCellValue(usernameColumn, 0);
		ControllerListAllUsers.setCellValue(nameColumn, 1);
		ControllerListAllUsers.setCellValue(emailColumn, 2);
		ControllerListAllUsers.setCellValue(roleColumn, 3);
		
		ObservableList<String[]> users = FXCollections.observableArrayList();
		
		ControllerListAllUsers.setupTableItems(users);
		table_userTable.setItems(users);
    	
    	theRootPane.getChildren().clear();
		theRootPane.getChildren().addAll(
				label_PageTitle, label_UserDetails, button_UpdateThisUser, line_Separator1,
				line_Separator3, button_Return, button_Logout, button_Quit, table_userTable);
		
		
		theStage.setTitle("CSE 360 Foundation Code: Admin Operations Page");	
		theStage.setScene(theViewSetOneTimePasswordScene);
		theStage.show();
	}

   
	/**********
	 * <p> Method: ViewListAllUsers() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object. </p>
	 * 
	 * This is a singleton, so this is performed just once.  Subsequent uses fill in the changeable
	 * fields using the displayListAllUsers method.
	 * 
	 * Three GUI areas are set and populated.</p>
	 * 
	 */
	public ViewListAllUsers() {
		
		theRootPane = new Pane();
		theViewSetOneTimePasswordScene = new Scene(theRootPane, width, height);
		
		label_PageTitle.setText("List All Users Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
				
		setupButtonUI(button_UpdateThisUser, "Dialog", 18, 170, Pos.CENTER, 610, 45);
		button_UpdateThisUser.setOnAction((_) -> 
			{guiUserUpdate.ViewUserUpdate.displayUserUpdate(theStage, theUser); });
		
		setupTableUI(table_userTable, "Dialog", 12, width, 400, 0, 110);
		
    	table_userTable.getColumns().addAll(usernameColumn, nameColumn, emailColumn, roleColumn);
    	
    	setupTableColumnUI(usernameColumn, "Dialog", 12, width/4, Pos.CENTER);
    	setupTableColumnUI(nameColumn, "Dialog", 12, width/4, Pos.CENTER);
    	setupTableColumnUI(emailColumn, "Dialog", 12, width/4, Pos.CENTER);
    	setupTableColumnUI(roleColumn, "Dialog", 12, width/4, Pos.CENTER);

		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerListAllUsers.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerListAllUsers.performLogout(); });
		    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerListAllUsers.performQuit(); });
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
	private void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
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
	private void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));
		b.setMinWidth(w);
		b.setAlignment(p);
		b.setLayoutX(x);
		b.setLayoutY(y);		
	}
	
	
	/**********
	 * Private local method to initialize the standard fields for a table
	 * 
	 * @param t		The table object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the table
	 * @param h		The height of the table
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupTableUI(TableView<String[]> t, String ff, double f, double w, double h, double x, double y) {
		t.setStyle("-fx-font-family: " + ff);
		t.setStyle("-fx-font-size: " + f + "px");
	    t.setMinWidth(w);
	    t.setMinHeight(h);
	    t.setLayoutX(x);
	    t.setLayoutY(y);
	}
	
	
	/**********
	 * Private local method to initialize the standard fields for a table column
	 * 
	 * @param c		The table column object to be initialized
	 * @param ff	The font to be used
	 * @param f		The size of the font to be used
	 * @param w		The width of the table column
	 * @param p		The alignment (e.g. left, centered, or right)
	 */
	private void setupTableColumnUI(TableColumn<String[], String> c, String ff, double f, double w, Pos p) {
		c.setStyle("-fx-font-family: " + ff);
		c.setStyle("-fx-font-size: " + f + "px");
		c.setMinWidth(w);
		c.setStyle("-fx-alignment: " + p);
	}

}