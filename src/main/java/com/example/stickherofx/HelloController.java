package com.example.stickherofx;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane pane;

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene2(ActionEvent event) throws IOException {
        Image backgroundImage = new Image("C:\\Users\\NAMIT\\IdeaProjects\\StickHeroFX\\src\\main\\resources\\playbg.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1539);
        backgroundImageView.setFitHeight(1058);
        Group root = new Group();
        backgroundImageView.setX(-354);
        backgroundImageView.setY(-247);
        root.setPickOnBounds(false);

        Image sheep=new Image("C:\\Users\\NAMIT\\IdeaProjects\\StickHeroFX\\src\\main\\resources\\Asset 1sheep.png");
        ImageView charview=new ImageView(sheep);
        charview.setFitWidth(102);
        charview.setFitHeight(56);
        charview.setX(75);
        charview.setY(303);
        root.setPickOnBounds(false);

        Text text=new Text();
        text.setText("Hold your finger on screen to stretch out the stick");
        text.setX(250);
        text.setY(172);
        text.setFont(Font.font("Agency FB",20));
        String hexColor = "#e7e7e7";
        Color fillColor = Color.web(hexColor);
        text.setFill(fillColor);

        Label labelText = new Label("Hold your finger on the screen to stretch out the stick");
        labelText.setTextFill(fillColor);
        labelText.setFont(Font.font("Agency FB", 20));
        labelText.setLayoutX(282);
        labelText.setLayoutY(192);

        Label score = new Label("0");
        score.setTextFill(Color.web("#0b0b0b"));
        score.setFont(Font.font("Impact", 40));
        score.setLayoutX(431);
        score.setLayoutY(94);

        Rectangle rectangle=new Rectangle();
        rectangle.setWidth(175);
        rectangle.setHeight(125);
        rectangle.setY(358);
        rectangle.setX(-5);
        rectangle.setFill(Color.web("#0b0b0b"));

        Rectangle rectangle2=new Rectangle();
        rectangle2.setWidth(70);
        rectangle2.setHeight(125);
        rectangle2.setY(358);
        rectangle2.setX(241);
        rectangle2.setFill(Color.web("#0b0b0b"));

        Button pauseButton = new Button("PAUSE");
        pauseButton.setFont(Font.font("Impact", 20));
        pauseButton.setTextFill(Color.web("#e7e7e7"));
        pauseButton.setStyle("-fx-background-color: #cc3300; " +"-fx-background-radius: 100; ");
        pauseButton.setLayoutX(19);
        pauseButton.setLayoutY(13);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    switchToPauseScrn(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        root.getChildren().addAll(backgroundImageView,rectangle,labelText,score,rectangle2,charview,pauseButton);

        Scene scene=new Scene(root,856,482,Color.BLACK);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("StickHero");
        stage.setScene(scene);
        stage.show();
    }


    /*public void switchToScene2(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }*/


    public void switchToChooseChar(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("choosechar.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToPauseScrn(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("pausescrn.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
