/**
 * <p> Title: FoundationsF26 Module. </p>
 * 
 * <p> Description: opens applicationMain to javafx.graphics, javafx.fxml.</p>
 * 
 */
module FoundationsF26 {
	requires javafx.controls;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	
	opens applicationMain to javafx.graphics, javafx.fxml;
}
