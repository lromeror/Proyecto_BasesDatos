module com.mycompany.yourminifactory {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.yourminifactory to javafx.fxml;
    exports com.mycompany.yourminifactory;
}
