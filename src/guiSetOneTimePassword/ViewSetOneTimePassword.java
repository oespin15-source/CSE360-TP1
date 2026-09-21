package guiSetOneTimePassword;

import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*******
 * <p> Title: ViewSetOneTimePassword Class. </p>
 * 
 * <p> Description: The Java/FX-based System Startup Page.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2025 </p>
 * 
 * @author Lynn Robert Carter
 * 
 * @version 1.00		2025-04-20 Initial version
 *  
 */

public class ViewSetOneTimePassword {
	
	/*-*******************************************************************************************

	Attributes
	
	*/
	
	// These are the application values required by the user interface
	
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;
	
	// These are the widget attributes for the GUI. There are 3 areas for this GUI.
	
	// GUI Area 1: It informs the user about the purpose of this page, whose account is being used,
	// and a button to allow this user to update the account settings.
	protected static Label label_PageTitle = new Label();
	protected static Label label_UserDetails = new Label();
	protected static Button button_UpdateThisUser = new Button("Account Update");
	
	// This is a separator and it is used to partition the GUI for various tasks
	protected static Line line_Separator1 = new Line(20, 95, width-20, 95);
	
	// Area 2: This allows the admin to set a password for another user who has forgotten their password.
	// Once a user has logged in with the one-time password, it is cleared so it can’t be used again.
	protected static Label label_SetAPassword = new Label("Set a Password");
	protected static TextField text_SetOneTimePassword = new TextField();
	protected static Button button_SetPassword = new Button("Set Password");
	
	// This is a separator and it is used to partition the GUI for various tasks
	protected static Line line_Separator3 = new Line(20, 525, width-20,525);
	
	// GUI Area 3: This is last of the GUI areas.  It is used for quitting the application, logging
	// out, and on other pages a return is provided so the user can return to a previous page when
	// the actions on that page are complete.  Be advised that in most cases in this code, the 
	// return is to a fixed page as opposed to the actual page that invoked the pages.
	protected static Button button_Return = new Button("Return");
	protected static Button button_Logout = new Button("Logout");
	protected static Button button_Quit = new Button("Quit");
	
	// This is the end of the GUI objects for the page.
	
	// These attributes are used to configure the page and populate it with this user's information
	protected static Stage theStage;			// The Stage that JavaFX has established for us
	protected static Pane theRootPane;			// The Pane that holds all the GUI widgets 
	public static Scene theViewSetOneTimePasswordScene = null;	// The Scene each invocation populates
	private static ViewSetOneTimePassword theView;	// Used to determine if instantiation of the class
	protected static User theUser;				// The current user of the application
	
	
	/*-*******************************************************************************************

	Constructors
	
	*/
	
	/**********
	 * <p> Method: displaySetOneTimePassword(Stage ps, User user) </p>
	 * 
	 * <p> Description: This method is the single entry point from outside this package to cause
	 * the ResetPassword page to be displayed.
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
	 * @param ps specifies the JavaFX Stage to be used for this GUI and it's methods
	 *
	 */
	public static void displaySetOneTimePassword (Stage ps, User user) {
		
		// Establish the references to the GUI and the current user
		theStage = ps;
		theUser = user;
		
		// If not yet established, populate the static aspects of the GUI by creating the 
		// singleton instance of this class
		if (theView == null) theView = new ViewSetOneTimePassword();
		
		// Place all of the established GUI elements into the pane
    	theRootPane.getChildren().clear();
		theRootPane.getChildren().addAll(
				label_PageTitle, label_UserDetails, button_UpdateThisUser, line_Separator1,
				label_SetAPassword, text_SetOneTimePassword, button_SetPassword,
				line_Separator3, button_Return, button_Logout, button_Quit);
		
		// Set the title for the window, display the page, and wait for the Admin to do something
		theStage.setTitle("CSE 360 Foundation Code: Admin Operations Page");	
		theStage.setScene(theViewSetOneTimePasswordScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewSetOneTimePassword() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object. </p>
	 * 
	 * This is a singleton, so this is performed just once.  Subsequent uses fill in the changeable
	 * fields using the displayResetPassword method.</p>
	 * 
	 */
	public ViewSetOneTimePassword() {
		
		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theViewSetOneTimePasswordScene = new Scene(theRootPane, width, height);
		
		// GUI Area 1
		label_PageTitle.setText("Set a One-Time Password Page");
		setupLabelUI(label_PageTitle, "Arial", 28, width, Pos.CENTER, 0, 5);

		label_UserDetails.setText("User: " + theUser.getUserName());
		setupLabelUI(label_UserDetails, "Arial", 20, width, Pos.BASELINE_LEFT, 20, 55);
				
		setupButtonUI(button_UpdateThisUser, "Dialog", 18, 170, Pos.CENTER, 610, 45);
		button_UpdateThisUser.setOnAction((_) -> 
			{guiUserUpdate.ViewUserUpdate.displayUserUpdate(theStage, theUser); });
		
		// GUI Area 2
		setupLabelUI(label_SetAPassword, "Arial", 20, 300, Pos.CENTER, 250, 210);
		
		// Establish the text input operand field for the password
		setupTextUI(text_SetOneTimePassword, "Arial", 20, 300, Pos.CENTER, 250, 260, true);
		text_SetOneTimePassword.setPromptText("Enter New Password");
		
		// Button to set the password
		setupButtonUI(button_SetPassword, "Dialog", 18, 200, Pos.CENTER, 300, 310);
		button_SetPassword.setOnAction((_) -> {ControllerSetOneTimePassword.SetTheOneTimePassword(); });
		
		// GUI Area 3		
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerSetOneTimePassword.performReturn(); });

		setupButtonUI(button_Logout, "Dialog", 18, 210, Pos.CENTER, 300, 540);
		button_Logout.setOnAction((_) -> {ControllerSetOneTimePassword.performLogout(); });
		    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerSetOneTimePassword.performQuit(); });
	}
	
	/*-*******************************************************************************************

	Helper methods used to minimizes the number of lines of code needed above
	
	*/

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

}
