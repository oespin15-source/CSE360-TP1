package guiResetPassword;

import entityClasses.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*******
 * <p> Title: ViewResetPassword Class. </p>
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

public class ViewResetPassword {
	
	/*-*******************************************************************************************

	Attributes
	
	*/
	
	// These are the application values required by the user interface
	
	private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
	private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;
		
	// These are the widget attributes for the GUI. 
	private static Label label_ApplicationTitle = new Label("Foundation Application Password Reset Page");
	protected static Label label_ResetPasswordLine = new Label("Please enter a new password.");
	protected static TextField text_NewPassword1 = new TextField();
	protected static TextField text_NewPassword2 = new TextField();
	protected static Button button_PasswordReset = new Button("Reset Password");
	protected static Label label_PasswordsDoNotMatch = new Label();
	protected static Label label_PasswordRequirements = new Label();
	protected static ProgressBar Bar_passwordStrength = new ProgressBar();
    protected static Label label_StrengthLabel = new Label();
	
	// This alert is used should the user enter an invalid password
	protected static Alert alertPasswordError = new Alert(AlertType.INFORMATION);
	
	// This alert is used when the password is successfully reset
	protected static Alert alertNewPasswordSuccess = new Alert(AlertType.INFORMATION);
	
	// This is used for quitting the application and returning to the previous page
	protected static Button button_Return = new Button("Return");
	protected static Button button_Quit = new Button("Quit");
	
	// This is the end of the GUI objects for the page.
	
	// These attributes are used to configure the page and populate it with this user's information
	protected static Stage theStage;			// The Stage that JavaFX has established for us
	protected static Pane theRootPane;			// The Pane that holds all the GUI widgets 
	public static Scene theResetPasswordScene = null;	// The Scene each invocation populates
	private static ViewResetPassword theView;	// Used to determine if instantiation of the class
	
	
	/*-*******************************************************************************************

	Constructors
	
	*/
	
	/**********
	 * <p> Method: displayResetPassword(Stage ps) </p>
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
	public static void displayResetPassword (Stage ps, User user) {
		
		// Establish the reference to the GUI
		theStage = ps;
		
		// If not yet established, populate the static aspects of the GUI by creating the 
		// singleton instance of this class
		if (theView == null) theView = new ViewResetPassword();
		
		text_NewPassword1.setText(""); // Clear the input fields so previously entered values do not
		text_NewPassword2.setText(""); // appear for a new user
		
		// Place all of the established GUI elements into the pane
    	theRootPane.getChildren().clear();
    	theRootPane.getChildren().addAll(label_ApplicationTitle, label_ResetPasswordLine,
    			button_PasswordReset, text_NewPassword1, text_NewPassword2, button_Return, button_Quit,
    			label_PasswordRequirements, Bar_passwordStrength, label_StrengthLabel);
    	
		// Set the title for the window, display the page, and wait for the Admin to do something
		theStage.setTitle("CSE 360 Foundation Code: Reset Password");	
		theStage.setScene(theResetPasswordScene);
		theStage.show();
	}
	
	/**********
	 * <p> Method: ViewResetPassword() </p>
	 * 
	 * <p> Description: This method initializes all the elements of the graphical user interface.
	 * This method determines the location, size, font, color, and change and event handlers for
	 * each GUI object. </p>
	 * 
	 * This is a singleton, so this is performed just once.  Subsequent uses fill in the changeable
	 * fields using the displayResetPassword method.</p>
	 * 
	 */
	public ViewResetPassword() {
		
		// Create the Pane for the list of widgets and the Scene for the window
		theRootPane = new Pane();
		theResetPasswordScene = new Scene(theRootPane, width, height);
				
		// Label the Panel with the name of the startup screen, centered at the top of the pane
		setupLabelUI(label_ApplicationTitle, "Arial", 28, width, Pos.CENTER, 0, 5);
		
		// Label to display the message for the user
    	setupLabelUI(label_ResetPasswordLine, "Arial", 24, width, Pos.CENTER, 0, 70);
		
		// Establish the text input operand field for the password 1
		setupTextUI(text_NewPassword1, "Arial", 20, 300, Pos.CENTER, 250, 260, true);
		text_NewPassword1.setPromptText("Enter New Password");
		text_NewPassword1.textProperty().addListener((_, _, _) 
				-> {ControllerResetPassword.ResetThePassword1(); 
					ControllerResetPassword.validatePassword();
				    ControllerResetPassword.updateStrength();}); 
		
		// Establish the text input operand field for the password 2
		setupTextUI(text_NewPassword2, "Arial", 20, 300, Pos.CENTER, 250, 310, true);
		text_NewPassword2.setPromptText("Enter New Password Again");
		text_NewPassword2.textProperty().addListener((_, _, _) 
				-> {ControllerResetPassword.ResetThePassword2(); });
		
		// Button to perform the password reset
        setupButtonUI(button_PasswordReset, "Dialog", 18, 200, Pos.CENTER, 300, 360);
        button_PasswordReset.setOnAction((_) -> {ControllerResetPassword.performPasswordReset(); });
        
        // Label to display password requirements
        setupLabelUI(label_PasswordRequirements, "Arial", 16, width, Pos.CENTER, 0, 410);

		// Progress bar for password strength
     	setupProgressBarUI(Bar_passwordStrength, 300, 15, 250, 460);
     	setupLabelUI(label_StrengthLabel, "Arial", 16, 100, Pos.CENTER, 350, 480);
		
        // If the password is invalid, this alert dialog will tell the user
     	alertPasswordError.setTitle("Invalid Password");
     	alertPasswordError.setHeaderText("The password does not meet the requirements.");
		
		// If the passwords do match, this alert dialog will tell the user
		alertNewPasswordSuccess.setTitle("Password Change Was Successfull");
		alertNewPasswordSuccess.setHeaderText("The password has now been changed.");
		alertNewPasswordSuccess.setContentText("Please use the new password to login.");
		
		// Quit and Return buttons		
		setupButtonUI(button_Return, "Dialog", 18, 210, Pos.CENTER, 20, 540);
		button_Return.setOnAction((_) -> {ControllerResetPassword.performReturn(); });
		    
		setupButtonUI(button_Quit, "Dialog", 18, 210, Pos.CENTER, 570, 540);
		button_Quit.setOnAction((_) -> {ControllerResetPassword.performQuit(); });
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


	/**********
	 * Private local method to initialize the standard fields for a progress bar
	 * 
	 * @param p		The progress bar object to be initialized
	 * @param w		The width of the progress bar
	 * @param h		The height of the progress bar
	 * @param x		The location from the left edge (x axis)
	 * @param y		The location from the top (y axis)
	 */
	private void setupProgressBarUI(ProgressBar p, double w, double h, double x, double y){
		p.setPrefWidth(w);
		p.setPrefHeight(h);
		p.setLayoutX(x);
		p.setLayoutY(y);		
	}

}
