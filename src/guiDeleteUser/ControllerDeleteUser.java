package guiDeleteUser;

import java.util.Optional;
import database.Database;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/*******
 * <p> Title: ControllerDeleteUser Class. </p>
 *
 * <p> Description: Handles the logic for deleting a user account.
 * The controller verifies that a user has been selected, prevents an
 * administrator from deleting their own account, asks for confirmation,
 * and deletes the selected user from the database. </p>
 *
 * @author Obed Espinoza
 *
 * @version 1.00 2026-09-23 Initial implementation for TP1
 */

public class ControllerDeleteUser {

    private static Database theDatabase =
            applicationMain.FoundationsMain.database;

    /*******
     * <p> Method: deleteUser() </p>
     * 
     * <p> Description: This method first ensures an admin is selected and
     * that they are not the current user. It asks for permission and waits
     * for confirmation before removing the user. 
     *
     * @author Obed Espinoza
     *
     * @version 1.00 2026-09-23 Initial implementation for TP1
     */
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
