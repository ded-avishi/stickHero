module com.example.stickherofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.stickherofx to javafx.fxml;
    exports com.example.stickherofx;
}