module com.example.prj1_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prj1_2 to javafx.fxml;
    exports com.example.prj1_2;
}