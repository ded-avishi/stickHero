module com.example.stickherofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.stickherofx to javafx.fxml;
    exports com.example.stickherofx;
}