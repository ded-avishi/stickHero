package com.example.stickherofx;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);

        // player spawn -----------------------------
        Image image = new Image("Asset 1sheep.png");
        ImageView imageView = new ImageView(image);
        imageView.setX(22);
        imageView.setY(280);
        imageView.setPreserveRatio(true); // or false idk
        // Scale the ImageView by 0.5x
        imageView.setScaleX(0.25); // Scale along the x-axis
        imageView.setScaleY(0.25); // Scale along the y-axis
        Group groot = new Group();
        // player object with the above imageview
        Player player = new Player(imageView);
        // -------------------------------------------
        // pillars spawn -----------------------------
        PillarGenerator pillarGenerator = PillarGenerator.getInstance();
        Rectangle firstPillar;
        firstPillar = pillarGenerator.newPillar();
        firstPillar.setHeight(500);
        firstPillar.setWidth(190);
        firstPillar.setX(0);
        firstPillar.setY(357);
        firstPillar.setFill(Color.BLACK);
        groot.getChildren().add(firstPillar);

        // pillar 2
        Rectangle secondPillar;
        secondPillar = pillarGenerator.newPillar();
        secondPillar.setHeight(500);
        secondPillar.setWidth(60 + (Math.random()*130));
        secondPillar.setX(195+(30 + (Math.random()*200))); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
        secondPillar.setY(357);
        secondPillar.setFill(Color.BLACK);
        groot.getChildren().add(secondPillar);
        //----------------------------------------------



//  for multiple views just make more imageViews with the same image object

        root.requestFocus(); // moves focus from button to node root
        stage.setTitle("GamePlay Window");

        // arraylist of sticks
        ArrayList<Stick> sticks = new ArrayList<>();

        // spawn and init stick ------------------------------------------------
        StickGenerator stickGenerator = StickGenerator.getInstance();
        Rectangle stick = stickGenerator.newStick();
        stick.setX(160);
        stick.setY(361);
        stick.setHeight(0);
        stick.setWidth(5);
        stick.setFill(Color.BLACK);

        // ---------------------------------------------------------------------

        // key press detection==================================================
        boolean KeyState;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent keyEvent) {
                                      switch (keyEvent.getCode()){
                                          case SPACE -> {
                                              stick.setHeight(stick.getHeight()+5);
                                              stick.setY(stick.getY() - 5); // Move the rectangle upwards
                                          }
                                          case ESCAPE -> {
                                              // pausebutton implementation
                                          }
                                      }
                                  }
                              }
        );
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()){
                    case SPACE -> {
                        // stick falls
                        stick.getTransforms().add(new Rotate(90,160,356));
                        // Applying translational motion to the player
                        TranslateTransition move = new TranslateTransition(Duration.seconds(1), player.getImageView());
                        move.setByX(stick.getHeight()); // Adjust this value based on how far you want the player to move
                        move.play();

                        // pillar 3 (out of scene initially)
                        Rectangle thirdPillar = pillarGenerator.newPillar();
                        thirdPillar.setHeight(500);
                        thirdPillar.setWidth(60 + (Math.random()*130));
                        thirdPillar.setX(1400); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
                        thirdPillar.setY(357);
                        thirdPillar.setFill(Color.BLACK);
                        groot.getChildren().add(thirdPillar);

                        move.setOnFinished(event -> {
                            // check if character falls
                            if (((stick.getHeight()+stick.getX()) >= (secondPillar.getWidth()+secondPillar.getX())) || ((stick.getHeight()+stick.getX()) <= (secondPillar.getX()))){
                                // player falls down
                                TranslateTransition fall = new TranslateTransition(Duration.seconds(0.25), player.getImageView());
                                fall.setByY(500); // Adjust this value based on how far you want the player to fall
                                fall.play();
                                // stick moves another 90 degrees
                                stick.getTransforms().add(new Rotate(90,163,359));

                                fall.setOnFinished(event2 -> {
                                    // switch to game over screen (replay + home + show highScore)
                                });

                            } else {
                                // moves the character some extra
                                TranslateTransition move2 = new TranslateTransition(Duration.seconds(0.58),player.getImageView());
                                move2.setByX((secondPillar.getX()+secondPillar.getWidth()-stick.getHeight()-stick.getX()-player.getImageView().getFitWidth()-10));
                                move2.play();

                                move2.setOnFinished(event1 -> {
                                    // character, pillar and pillar1 move to the left
                                    TranslateTransition move1 = new TranslateTransition(Duration.seconds(0.4),stick);
                                    move1.setByX(-secondPillar.getX());
                                    move1.play();
                                    TranslateTransition move3 = new TranslateTransition(Duration.seconds(0.4), player.getImageView());
                                    move3.setByX(-secondPillar.getX());
                                    move3.play();
                                    TranslateTransition move4 =  new TranslateTransition(Duration.seconds(0.4), secondPillar);
                                    move4.setByX(-secondPillar.getX());
                                    move4.play();
                                    TranslateTransition move6 = new TranslateTransition(Duration.seconds(0.4),firstPillar);
                                    move6.setByX(-secondPillar.getX());
                                    move6.play();
                                    // at the same pt in time, pillar2 moves in from the left
                                    TranslateTransition move5  = new TranslateTransition(Duration.seconds(0.4), thirdPillar);
                                    move5.setByX(-thirdPillar.getX()+(195+(15 + (Math.random()*200))));
                                    move5.play();

                                    Rectangle newStick = stickGenerator.newStick();
                                    newStick.setX(160);
                                    newStick.setY(361);
                                    newStick.setHeight(0);
                                    newStick.setWidth(5);
                                    newStick.setFill(Color.BLACK);
                                    // Add the new stick to the list and the group
                                    sticks.add((Stick) newStick);
                                    groot.getChildren().add(newStick);

                                    // Update the stick reference
                                    Rectangle stick = newStick;

                                    // stick back to initial position and zero height
//                                    stick.setX(160);
//                                    stick.setY(361);
//                                    stick.setHeight(0);
//                                    stick.setWidth(5);
//                                    stick.setFill(Color.BLACK);
//                                    stick.getTransforms().add(new Rotate(-90,160.5,361.5));

                                });
                            }



                        });
                    }
                }
            }
        });

        // Add the various nodes to the Group
        groot.getChildren().add(player.getImageView());
        groot.getChildren().add(stick);

        // Add the Group to the root of the Scene
        ((Pane) root).getChildren().add(groot);

        // Add the Scene to the Stage
        stage.setScene(scene);
        stage.show();
    }
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
