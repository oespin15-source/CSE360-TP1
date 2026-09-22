package guiDeleteUser;

import java.util.Optional;

import database.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ControllerDeleteUser {

    private static Database theDatabase =
            applicationMain.FoundationsMain.database;

    protected static void deleteUser() {

        String username =
                ViewDeleteUser.combobox_User.getValue();

        // Make sure a user was selected
        if (username == null ||
                username.equals("<Select a User>")) {

            ViewDeleteUser.label_Status.setText(
                    "Please select a user.");
            return;
        }

        // Admin cannot delete their own account
        if (username.equals(
                ViewDeleteUser.theUser.getUserName())) {

            ViewDeleteUser.label_Status.setText(
                    "You cannot delete your own account.");
            return;
        }

        // Ask for confirmation
        Alert confirmation =
                new Alert(AlertType.CONFIRMATION);

        confirmation.setTitle("Delete User");
        confirmation.setHeaderText("Are you sure?");
        confirmation.setContentText(
                "Delete user: " + username + "?");

        Optional<ButtonType> result =
                confirmation.showAndWait();

        if (result.isPresent() &&
                result.get() == ButtonType.OK) {

            boolean deleted =
                    theDatabase.deleteUser(username);

            if (deleted) {
                ViewDeleteUser.label_Status.setText(
                        "User deleted successfully.");

                ViewDeleteUser.combobox_User.setItems(
                        javafx.collections.FXCollections
                        .observableArrayList(
                                theDatabase.getUserList()));

                ViewDeleteUser.combobox_User
                        .getSelectionModel().select(0);

            } else {
                ViewDeleteUser.label_Status.setText(
                        "Unable to delete user.");
            }
        }
    }
}