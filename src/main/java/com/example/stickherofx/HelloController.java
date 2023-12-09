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
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HelloController {
    private Stick stickBeingUsed;
    private double minHeightStick;
    private double maxHeightStick;
    private Pillar pillar1;
    private Pillar pillar2;
    private Pillar pillar3;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Text highscore;

    public void setStage(Stage stg){
        this.stage=stg;
    }

    public void whenFail(Stage stg) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ifFail.fxml")));
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
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

        String musicFile = "src/main/resources/Desert Theme.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

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
//        boolean KeyState;
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
                                          case K -> {
                                              player.flip();

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
                        minHeightStick = pillar2.getX() - pillar1.getWidth() - stickBeingUsed.getHeight();
                        maxHeightStick = pillar2.getX() + pillar2.getWidth() - pillar1.getWidth() - stickBeingUsed.getHeight();
                        System.out.println(pillar2.getX()+" "+pillar2.getWidth()+" "+pillar1.getWidth()+" "+stickBeingUsed.getHeight()+" "+stickBeingUsed.getWidth());

                        stickBeingUsed.getTransforms().add(new Rotate(90,stickBeingUsed.getX(),stickBeingUsed.getY()+stickBeingUsed.getHeight()-5));

                        // Applying translational motion to the player
                        TranslateTransition move = new TranslateTransition(Duration.seconds(1), player.getImageView());
                        move.setByX(stickBeingUsed.getHeight()); // Adjust this value based on how far you want the player to move
                        move.play();


                        move.setOnFinished(event -> {
                            // check if character falls
                            if ((0 < minHeightStick || (0 > maxHeightStick))){
                                // player falls down
                                TranslateTransition fall = new TranslateTransition(Duration.seconds(0.25), player.getImageView());
                                fall.setByY(500); // Adjust this value based on how far you want the player to fall
                                fall.play();
                                // stick disappears
                                stickBeingUsed.setHeight(0);
                                stickBeingUsed.setWidth(0);


                                fall.setOnFinished(event2 -> {
                                    try {
                                        whenFail(stage);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });

                            } else {
                                groot.getChildren().remove(highscore);
                                player.setScore(player.getScore()+1);
                                String temp=Integer.toString(player.getScore());
                                Text text=new Text(temp);
                                text.setFont(Font.font("Impact",40));
                                text.setY(130);
                                text.setX(431);
                                text.setFill(Color.BLACK);
                                text.toFront();
                                highscore=text;
                                groot.getChildren().add(highscore);

//                                minHeightStick = pillar3.getX() - pillar2.getWidth() - stickBeingUsed.getHeight();
//                                maxHeightStick = pillar3.getX() + pillar3.getWidth() - pillar2.getWidth() - stickBeingUsed.getHeight();

                                // moves the character some extra
                                TranslateTransition move2 = new TranslateTransition(Duration.seconds(0.58),player.getImageView());
                                move2.setByX((pillar2.getX()+pillar2.getWidth()-stickBeingUsed.getHeight()-stickBeingUsed.getX()-player.getImageView().getFitWidth()-10));
                                move2.play();

//                                minHeightStick = pillar2.getX() - pillar1.getWidth();
//                                maxHeightStick = pillar2.getX() + pillar2.getWidth() - pillar1.getWidth();

                                move2.setOnFinished(event1 -> {
                                    // character, pillar and pillar1 move to the left
                                    System.out.println(pillar1.getX() + " " + pillar2.getX() + " " + pillar3.getX());
                                    TranslateTransition move1 = new TranslateTransition(Duration.seconds(0.4),stickBeingUsed);
                                    move1.setByX(-pillar2.getX()-pillar2.getWidth()+pillar1.getWidth());
                                    move1.play();
                                    TranslateTransition move3 = new TranslateTransition(Duration.seconds(0.4), player.getImageView());
                                    move3.setToX(22);
                                    move3.play();
//                                    TranslateTransition move7 = new TranslateTransition(Duration.seconds(0.4), pillar3);
//                                    move7.setByX(-1400+195+30 + (Math.random()*200));
//                                    move7.play();
                                    TranslateTransition move4 =  new TranslateTransition(Duration.seconds(0.4), pillar2);
                                    move4.setByX(-pillar2.getX()-pillar2.getWidth()+pillar1.getWidth());
                                    move4.play();

//                                    boolean fall = (pillar3.getWidth());
                                    // at the same pt in time, pillar3 moves in from the left
                                    move4.setOnFinished(event2 -> {

                                        // generate new stick
                                        Rectangle stick = stickGenerator.newStick();
                                        stick.setX(160);
                                        stick.setY(361);
                                        stick.setHeight(0);
                                        stick.setWidth(5);
                                        stick.setFill(Color.BLACK);
                                        stickBeingUsed = (Stick) stick;
                                        groot.getChildren().add(stickBeingUsed);

                                        // pillar 3
                                        Rectangle newPillar = pillarGenerator.newPillar();
                                        newPillar.setHeight(500);
                                        newPillar.setWidth(60 + (Math.random()*130));
                                        newPillar.setX(195+(30 + (Math.random()*200))); // 15px = minDist bw 2 pillars; 215px = maxDist bw 2 pillars
                                        newPillar.setY(357);
                                        newPillar.setFill(Color.BLACK);
                                        pillar3 = (Pillar) newPillar;
                                        groot.getChildren().add(newPillar);
                                        pillar1 = pillar2;
                                        pillar2 = pillar3;
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
