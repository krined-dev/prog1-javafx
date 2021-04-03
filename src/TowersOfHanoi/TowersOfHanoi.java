package TowersOfHanoi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TowersOfHanoi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Defining the layout
        stage.setTitle("Towers Of Hanoi");
        StackPane pane = new StackPane();
        pane.setPadding(new Insets(10));
        Button findMoves = new Button("Find moves");
        TextField amountOfDisks = new TextField();
        amountOfDisks.setPromptText("Enter number of disks");
        TextArea outputText = new TextArea();
        outputText.setPrefSize(800,400);
        outputText.setMaxHeight(800);
        outputText.setMaxWidth(400);

        HBox topBox = new HBox(5);
        topBox.getChildren().addAll(amountOfDisks, findMoves);
        topBox.setAlignment(Pos.CENTER);
        VBox layout = new VBox(5);
        layout.getChildren().addAll(topBox, outputText);

       // Defining behaviour

        findMoves.setOnAction(event -> {
            try {
                int nOfDisks = Integer.parseInt(amountOfDisks.getText());
                outputText.setText(moveDisks(nOfDisks));
            } catch(Exception e) {
                outputText.setText("Invalid input");
            }
        });

        pane.getChildren().addAll(layout);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        amountOfDisks.getParent().requestFocus();

        layout.setAlignment(Pos.CENTER);
        stage.show();

    }


    /** The method for finding the solution to move n disks
     from fromTower to toTower with auxTower */


    public static String moveDisks(int n, char fromTower, // recursive method
                                   char toTower, char auxTower, StringBuilder s, Counter calls) {

        if (n == 1) {// Stopping condition
                 s.append("Move number ").append(calls.getCount() + 1).append(": ").append("Move disk ").append(n)
                         .append(" from ").append(fromTower).append(" to ").append(toTower).append("\n");
                 calls.incrementCount();
        } else {
            moveDisks(n - 1, fromTower, auxTower, toTower, s, calls);
                s.append("Move number ").append(calls.getCount() + 1).append(": ").append("Move disk ")
                        .append(n).append(" from ").append(fromTower).append(" to ").append(toTower).append("\n");
            calls.incrementCount();
            moveDisks(n - 1, auxTower, toTower, fromTower, s, calls);
        }

        return s.toString();
    }

    public static String moveDisks(int nOfDisks){ // Formatting method
        char[] towers = {'A', 'B', 'C'};
        StringBuilder output = new StringBuilder();
        Counter count =  new Counter();

        String moves = "Moves are:\n" + moveDisks(nOfDisks, towers[0], towers[1], towers[2], output, count) +
                "\nNumber of calls to the method is: " + (count.getCount());
        count.resetCount();

        return moves;

    }
}

class Counter {
    static int count = 0;

    public void incrementCount() {
        count ++;
    }

    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }
}
