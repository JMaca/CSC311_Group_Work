module com.example.csc311_group_work {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc311_group_work to javafx.fxml;
    exports com.example.csc311_group_work;
}