package guiDeleteUser;

import java.util.List;

import database.Database;
import entityClasses.User;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/*******
 * <p> Title: ViewDeleteUser Class. </p>
 *
 * <p> Description: Provides the user interface for deleting a user
 * account. The view displays the available users, allows an administrator
 * to select a user for deletion, and provides controls to delete the
 * selected user or return to the Admin Home page. </p>
 *
 * @author Obed Espinoza
 *
 * @version 1.00 2026-09-23 
 */

public class ViewDeleteUser {

    private static double width = applicationMain.FoundationsMain.WINDOW_WIDTH;
    private static double height = applicationMain.FoundationsMain.WINDOW_HEIGHT;

    protected static Stage theStage;
    protected static User theUser;

    private static Database theDatabase =
            applicationMain.FoundationsMain.database;

    private static Pane theRootPane;
    private static Scene theScene;

    protected static ComboBox<String> combobox_User =
            new ComboBox<String>();

    protected static Label label_Status = new Label();

    private static Button button_Delete =
            new Button("Delete User");

    private static Button button_Return =
            new Button("Return");

    public static void displayDeleteUser(Stage stage, User user) {

        theStage = stage;
        theUser = user;

        theRootPane = new Pane();
        theScene = new Scene(theRootPane, width, height);

        Label title = new Label("Delete User Account");
        title.setFont(Font.font("Arial", 28));
        title.setMinWidth(width);
        title.setAlignment(Pos.CENTER);
        title.setLayoutY(30);

        Label selectLabel = new Label("Select User:");
        selectLabel.setFont(Font.font("Arial", 18));
        selectLabel.setLayoutX(200);
        selectLabel.setLayoutY(130);

        List<String> users = theDatabase.getUserList();

        combobox_User.setItems(
                FXCollections.observableArrayList(users));
        combobox_User.getSelectionModel().select(0);
        combobox_User.setLayoutX(320);
        combobox_User.setLayoutY(125);
        combobox_User.setMinWidth(250);

        button_Delete.setLayoutX(320);
        button_Delete.setLayoutY(190);
        button_Delete.setMinWidth(150);

        button_Return.setLayoutX(320);
        button_Return.setLayoutY(240);
        button_Return.setMinWidth(150);

        label_Status.setLayoutX(200);
        label_Status.setLayoutY(300);
        label_Status.setMinWidth(500);
        label_Status.setAlignment(Pos.CENTER);

        button_Delete.setOnAction((_) ->
                ControllerDeleteUser.deleteUser());

        button_Return.setOnAction((_) ->
                guiAdminHome.ViewAdminHome.displayAdminHome(
                        theStage, theUser));

        theRootPane.getChildren().addAll(
                title,
                selectLabel,
                combobox_User,
                button_Delete,
                button_Return,
                label_Status);

        theStage.setTitle("Delete User Account");
        theStage.setScene(theScene);
        theStage.show();
    }
}
