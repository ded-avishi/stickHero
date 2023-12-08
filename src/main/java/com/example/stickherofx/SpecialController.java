package com.example.stickherofx;

import javafx.event.ActionEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class SpecialController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane pane;

    public void initialize() {
        addRectangle();
    }

    private void addRectangle() {
        Rectangle rectangle = new Rectangle(100, 50, Color.BLUE);
        pane.getChildren().add(rectangle);
    }

}
