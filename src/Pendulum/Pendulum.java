package Pendulum;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Pendulum extends Application {
    @Override
    public void start(Stage primaryStage) {
        PendulumPane pane = new PendulumPane(); // du må lage klassen PendulumPane

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setTitle("Pendulum"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(100), e -> {pane.next();}));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play(); // Start animation


        pane.setOnMouseClicked(e -> {
            if( animation.getStatus().toString().equals("RUNNING")) {
                animation.pause();
            } else {
                animation.play();
            }
        });

        pane.requestFocus();
        pane.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                animation.setRate(animation.getRate() + 1);
            }
            if (e.getCode() == KeyCode.DOWN) {
                animation.setRate(animation.getRate() - 1);
            }
        });
    }
}
class PendulumPane extends Pane {
    Circle anchor = new Circle();
    Circle ball = new Circle();
    Line thread = new Line();
    double angle = 90.0;
    double radius = 100;
    boolean increasing = true;



   PendulumPane() {
       anchor.centerXProperty().bind(this.widthProperty().divide(2));
       anchor.centerYProperty().bind(this.heightProperty().divide(4));
       anchor.setRadius(5);
       anchor.fillProperty().set(Color.BLACK);
       thread.startYProperty().bind(anchor.centerYProperty());
       thread.startXProperty().bind(anchor.centerXProperty());
       ball.setRadius(15);
       ball.centerXProperty().bind(anchor.centerXProperty().add(radius * Math.cos(Math.toRadians(angle))));
       ball.centerYProperty().bind(anchor.centerYProperty().add(radius * Math.sin(Math.toRadians(angle))));
       ball.fillProperty().set(Color.BLACK);
       thread.endXProperty().bind(ball.centerXProperty());
       thread.endYProperty().bind(ball.centerYProperty());

       this.getChildren().addAll(anchor, thread, ball);
   }
    public void next() { // move pendulum between 60 and 120 deg

        if (increasing) {
            angle += 1;
            if (angle == 121) {
                increasing = false;
            }
        }
        if (!increasing) {
            angle -= 1;
            if (angle == 59) {
                increasing = true;
            }
        }

        setXYBall(angle);
    }

    private void setXYBall(double angleSet) {
        ball.centerXProperty().bind(anchor.centerXProperty().add(radius * Math.cos(Math.toRadians(angleSet))));
        ball.centerYProperty().bind(anchor.centerYProperty().add(radius * Math.sin(Math.toRadians(angleSet))));

    }
}
