package entityClasses;

import java.sql.SQLException;
import java.util.Optional;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/*******
 * <p> Title: LessonsLearnedList Class. </p>
 * 
 * <p> Description: This class manages the creation, retrieval, updating, and deletion of lessons learned. 
 * This class also includes functionality for adding experience information to existing lessons and establishing 
 * lesson details. The functionality of this class interacts with the database to retrieve and persist lesson information. </p>
 * 
 * @author Noah Lesniewski
 * 
 * @version 1.00		2026-10-28 Initial version
 * 
 */
public class LessonsLearnedList {
	
	// Reference for the in-memory database so this package has access
	private static Database theDatabase = applicationMain.FoundationsMain.database;
	
	/**
	 * Default constructor is not used.
	 */
	public LessonsLearnedList() {
	}
	
	
	/**********
	 * <p> Method: void createLessonLearned(String lessonHeader, String lessonBody) </p>
	 * 
	 * <p> Description: This method is used to create lessons and add them to the database. New lessons only needs the subject and 
	 * core information to be provided. the lesson's group id is determined by checking whether the lesson to be created is the first
	 * one in the database or by finding the highest group id in the database and assigning a higher id. A unique group id allows 
	 * related lessons with different experience information to be easily identifiable and grouped together. The username is 
	 * based on the currently logged in user, so lessons are identifiable by who made them. </p>
	 * 
	 * @param lessonHeader specifies the lesson header of the lesson to be created
	 * 
	 * @param lessonBody specifies the lesson body of the lesson to be created
	 * 
	 */
	public static void createLessonLearned(String lessonHeader, String lessonBody) {
		int newLessonGroupID = 1; // if the database is empty start at 1
		if (theDatabase.isLessonDatabaseEmpty() == false) {
			newLessonGroupID = theDatabase.getHighestLessonGroupID() + 1; // when a new lesson is created, increment the highest group id 
		}
		LessonsLearned lesson = new LessonsLearned(newLessonGroupID, theDatabase.getCurrentUsername(), lessonHeader, lessonBody);
    	try {
        	theDatabase.register(lesson);
        }
        catch (SQLException e) {
            System.err.println("*** ERROR *** Database error trying to register a lesson: " + 
            e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
	}
	
	
	/**********
	 * <p> Method: void updateLessonsLearned(String lessonHeader, String lessonBody) </p>
	 * 
	 * <p> Description: This method updates the old header and body information with the new header and body information
	 * provided by the user. It utilizes the database to query through the lessonDB table based on the currently examined lesson's
	 * id. The current lesson ID is established when a lesson is examined. Other roles beside the contributor that can examine 
	 * the lesson can also update the information. </p>
	 * 
	 * @param lessonHeader specifies the new lesson header of the lesson
	 * 
	 * @param lessonBody specifies the new lesson body of the lesson
	 * 
	 */
	public static void updateLessonsLearned(String lessonHeader, String lessonBody) {
		theDatabase.updateLessonHeader(theDatabase.getCurrentLessonId(), lessonHeader);
		theDatabase.updateLessonBody(theDatabase.getCurrentLessonId(), lessonBody);
		theDatabase.setCurrentLessonHeader(lessonHeader);
		theDatabase.setCurrentLessonBody(lessonBody);
	}
	
	
	/**********
	 * <p> Method: void deleteLessonsLearned(LessonsLearned lesson) </p>
	 * 
	 * <p> Description: This method will delete a lesson learned based on the group ID, so
	 * that all lessons with the same group ID will also be deleted together.
	 * This ensures all experience information attached to a lesson will be deleted alongside
	 * it. This method will only delete a lesson if the header and core information
	 * are unlocked, and it receives confirmation from the user. </p>
	 * 
	 * @param lesson specifies the lesson to be deleted
	 * 
	 */
	public static void deleteLessonsLearned(LessonsLearned lesson) {
		Alert userMessage = new Alert(AlertType.CONFIRMATION); 
        Optional<ButtonType> result;
        // The user can't delete a lesson if it has any locks on it
        if (lesson.getLessonHeaderLock() == true || lesson.getLessonBodyLock() == true) {
        	userMessage.setTitle("Delete Lesson");		
    		userMessage.setHeaderText("Delete Unsuccessful");
    		userMessage.setContentText("Unable to delete lessons with locked fields");
    		result = userMessage.showAndWait();
    	} 
        else {
    		userMessage.setTitle("Delete Lesson");		// check for user validation
    		userMessage.setHeaderText("Are you sure?");
    		userMessage.setContentText("Delete lesson: " + lesson.getLessonHeader() + "?");
    		result = userMessage.showAndWait();
    		
    		if (result.isPresent() && result.get() == ButtonType.OK) {
            	theDatabase.deleteLesson(lesson.getLessonGroupID());
    		}
    	return;
        }
	}
	
	
	/**********
	 * <p> Method: ObservableList<LessonsLearned> listLessonsLearned(String lessonFilter, User theUser) </p>
	 * 
	 * <p> Description: This method creates an ObservableList of lessons learned. It uses the database
	 * to fill the list with all lessons that have been created by the currently logged in user, so 
	 * the user will only be shown their own contributions. The theUser parameter is to identify which 
	 * role the current user is using. If other roles have set experience information to be invisible,
	 * contributors cannot see them, while other roles can. </p>
	 * 
	 * @param lessonFilter specifies the filter of the list to be populated
	 * 
	 * @param theUser specifies the currently logged in user
	 * 
	 */
	public static ObservableList<LessonsLearned> listLessonsLearned(String lessonFilter, User theUser) {
		ObservableList<LessonsLearned> lessonsList = FXCollections.observableArrayList();
		lessonsList.clear();
		theDatabase.fillLessonList(lessonsList, theDatabase.getCurrentUsername(), theUser);
		return lessonsList;
	}
	
	/**********
	 * <p> Method: addExperienceInformation(String whatWasDone, String howItWasDone, String timeSpent, String effortUsed) </p>
	 * 
	 * <p> Description: This method adds experience information to already existing lessons by reusing the relevant
	 * lesson's header and core information. It adds new experience information provided by the user. The new experience 
	 * information is stored in the database as a separate lesson record with the same group ID as the original lesson, 
	 * so they are easily grouped together.</p>
	 * 
	 * @param whatWasDone specifies experience information of what was done to be added to the lesson
	 * 
	 * @param howItWasDone specifies experience information of how it was done to be added to the lesson
	 * 
	 * @param timeSpent specifies experience information of time spent to be added to the lesson
	 * 
	 * @param effortUsed specifies experience information of effort used to be added to the lesson
	 * 
	 */
	public static void addExperienceInformation(String whatWasDone, String howItWasDone, String timeSpent, String effortUsed) {
	// Add experience information to an existing lesson
		LessonsLearned lesson = new LessonsLearned(theDatabase.getCurrentLessonId(), 
				theDatabase.getCurrentLessonGroupID(), theDatabase.getCurrentUsername(), 
				theDatabase.getCurrentLessonHeader(), theDatabase.getCurrentLessonBody(),
				theDatabase.getCurrentLessonHeaderLock(), theDatabase.getCurrentLessonBodyLock(), 
				whatWasDone, howItWasDone, timeSpent, effortUsed, true);
		try {
			theDatabase.register(lesson);
		    }
		catch (SQLException e) {
		    System.err.println("*** ERROR *** Database error trying to register a lesson: " + 
		    e.getMessage());
		    e.printStackTrace();
		    System.exit(0);
		}
	}
	
	
	/**********
	 * <p> Method: establishLessonDetails(LessonsLearned lesson) </p>
	 * 
	 * <p> Description: This method stores the lesson header, lesson body, ID, group ID, header 
	 * lock, and body lock in the database, so these details can be used by other methods 
	 * for updating or adding experience information to the examined lesson.</p>
	 * 
	 * @param lesson specifies the currently relevant lesson's details
	 * 
	 */
	public static void establishLessonDetails (LessonsLearned lesson) {
		theDatabase.setCurrentLessonHeader(lesson.getLessonHeader());
		theDatabase.setCurrentLessonBody(lesson.getLessonBody());
		theDatabase.setCurrentLessonId(lesson.getID());
		theDatabase.setCurrentLessonHeaderLock(lesson.getLessonHeaderLock());
		theDatabase.setCurrentLessonBodyLock(lesson.getLessonBodyLock());
		theDatabase.setCurrentLessonGroupID(lesson.getLessonGroupID());
	}
	
}
