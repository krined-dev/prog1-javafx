package sample;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



            HBox pane = new HBox(5);
            Circle circle = new Circle(50, 200, 200);
            pane.getChildren().addAll(circle);

            circle.setCenterX(100);
            circle.setCenterY(100);
            circle.setRadius(50);
            pane.getChildren().addAll(circle);

            // Create a scene and place it in the stage
            Scene scene = new Scene(pane);
            primaryStage.setTitle("Test"); // Set the stage title
            primaryStage.setScene(scene); // Place the scene in the stage
            primaryStage.show(); // Display the stage
        }


    public static void main(String[] args) {
        launch(args);
    }
}
