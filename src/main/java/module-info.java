module com.example.stickherofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;


    opens com.example.stickherofx to javafx.fxml;
    exports com.example.stickherofx;
}