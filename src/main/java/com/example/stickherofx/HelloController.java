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
    private Stick stickBeingUsed;
    private Pillar pillar1;
    private Pillar pillar2;
    private Pillar pillar3;
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
        this.pillar1 = (Pillar) firstPillar;
        groot.getChildren().add(firstPillar);

        // pillar 2
        Rectangle secondPillar;
        secondPillar = pillarGenerator.newPillar();
        secondPillar.setHeight(500);
        secondPillar.setWidth(60 + (Math.random()*130));
        secondPillar.setX(195+(30 + (Math.random()*200))); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
        secondPillar.setY(357);
        secondPillar.setFill(Color.BLACK);
        this.pillar2 = (Pillar) secondPillar;
        groot.getChildren().add(secondPillar);

        // pillar 3 (out of scene initially)
        Rectangle thirdPillar = pillarGenerator.newPillar();
        thirdPillar.setHeight(500);
        thirdPillar.setWidth(60 + (Math.random()*130));
        thirdPillar.setX(1400); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
        thirdPillar.setY(357);
        thirdPillar.setFill(Color.BLACK);
        this.pillar3 = (Pillar) thirdPillar;
        groot.getChildren().add(thirdPillar);
        //----------------------------------------------



//  for multiple views just make more imageViews with the same image object

        root.requestFocus(); // moves focus from button to node root
        stage.setTitle("GamePlay Window");
        StickGenerator stickGenerator = StickGenerator.getInstance();
        // spawn and init stick ------------------------------------------------
        Rectangle stick = stickGenerator.newStick();
        stick.setX(160);
        stick.setY(361);
        stick.setHeight(0);
        stick.setWidth(5);
        stick.setFill(Color.BLACK);
        stickBeingUsed = (Stick) stick;
        groot.getChildren().add(stickBeingUsed);

//        // arraylist of sticks
//        ArrayList<Stick> sticks = new ArrayList<>();

        // ---------------------------------------------------------------------

        // key press detection==================================================
        boolean KeyState;
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                                  @Override
                                  public void handle(KeyEvent keyEvent) {
                                      switch (keyEvent.getCode()){
                                          case SPACE -> {
                                              stickBeingUsed.setHeight(stickBeingUsed.getHeight()+5);
                                              stickBeingUsed.setY(stickBeingUsed.getY() - 5); // Move the rectangle upwards
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
                        stickBeingUsed.getTransforms().add(new Rotate(90,160,356));
                        // Applying translational motion to the player
                        TranslateTransition move = new TranslateTransition(Duration.seconds(1), player.getImageView());
                        move.setByX(stickBeingUsed.getHeight()); // Adjust this value based on how far you want the player to move
                        move.play();


                        move.setOnFinished(event -> {
                            // check if character falls
                            double stickEndX = stickBeingUsed.getX() + stickBeingUsed.getHeight();

                            if ((stickEndX > pillar2.getX() + pillar2.getWidth()) || (stickEndX < pillar2.getX())){
                                // player falls down
                                TranslateTransition fall = new TranslateTransition(Duration.seconds(0.25), player.getImageView());
                                fall.setByY(500); // Adjust this value based on how far you want the player to fall
                                fall.play();
                                // stick moves another 90 degrees
                                stickBeingUsed.getTransforms().add(new Rotate(90,163,359));


                                // pillar 2 becomes pillar 1
                                pillar1 = pillar2;

                                // pillar 3 becomes pillar 2
                                pillar2 = pillar3;

                                // pillar 3 (out of scene initially)
                                Rectangle newPillar = pillarGenerator.newPillar();
                                newPillar.setHeight(500);
                                newPillar.setWidth(60 + (Math.random()*130));
                                newPillar.setX(1400); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
                                newPillar.setY(357);
                                newPillar.setFill(Color.BLACK);
                                pillar3 = (Pillar) newPillar;
                                groot.getChildren().add(newPillar);


                                fall.setOnFinished(event2 -> {
                                    // switch to game over screen (replay + home + show highScore)
                                });

                            } else {
                                // moves the character some extra
                                TranslateTransition move2 = new TranslateTransition(Duration.seconds(0.58),player.getImageView());
                                move2.setByX((pillar2.getX()+pillar2.getWidth()-stickBeingUsed.getHeight()-stickBeingUsed.getX()-player.getImageView().getFitWidth()-10));
                                move2.play();

                                move2.setOnFinished(event1 -> {
                                    // character, pillar and pillar1 move to the left
                                    System.out.println(pillar1.getX() + " " + pillar2.getX() + " " + pillar3.getX());
                                    TranslateTransition move1 = new TranslateTransition(Duration.seconds(0.4),stickBeingUsed);
                                    move1.setByX(-pillar2.getX());
                                    move1.play();
                                    TranslateTransition move3 = new TranslateTransition(Duration.seconds(0.4), player.getImageView());
                                    move3.setByX(-pillar2.getX());
                                    move3.play();
                                    TranslateTransition move4 =  new TranslateTransition(Duration.seconds(0.4), pillar2);
                                    move4.setByX(-pillar2.getX());
                                    move4.play();
                                    TranslateTransition move6 = new TranslateTransition(Duration.seconds(0.4),pillar1);
                                    move6.setByX(-pillar2.getX());
                                    move6.play();
                                    // at the same pt in time, pillar2 moves in from the left
                                    TranslateTransition move5  = new TranslateTransition(Duration.seconds(0.4), pillar3);
                                    move5.setByX(-pillar3.getX()+(195+(15 + (Math.random()*200))));
                                    move5.play();

                                    move5.setOnFinished(event2 -> {
                                        // generate new stick
                                        Rectangle stick = stickGenerator.newStick();
                                        stick.setX(secondPillar.getLayoutX()+secondPillar.getWidth());
                                        stick.setY(361);
                                        stick.setHeight(0);
                                        stick.setWidth(5);
                                        stick.setFill(Color.BLACK);
                                        stickBeingUsed = (Stick) stick;
                                        groot.getChildren().add(stickBeingUsed);

                                        // pillar 4 (out of scene initially)
                                        Rectangle newPillar = pillarGenerator.newPillar();
                                        newPillar.setHeight(500);
                                        newPillar.setWidth(60 + (Math.random()*130));
                                        newPillar.setX(1400); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
                                        newPillar.setY(357);
                                        newPillar.setFill(Color.BLACK);
//                                        pillar3 = (Pillar) newPillar;
                                        groot.getChildren().add(newPillar);
                                        pillar2 = pillar3;

                                        // pillar 2 becomes pillar 1
                                        pillar1 = pillar2;

                                        // pillar 3 becomes pillar 2

                                        pillar3 = (Pillar) newPillar;
                                    });





                                });
                            }



                        });
                    }
                }
            }
        });

        // Add the various nodes to the Group
        groot.getChildren().add(player.getImageView());

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
