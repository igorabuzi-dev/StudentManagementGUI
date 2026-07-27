module com.studentmanagement.studentmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.studentmanagement.studentmanagementapp;
    opens com.studentmanagement.studentmanagementapp to javafx.fxml, javafx.graphics;
}