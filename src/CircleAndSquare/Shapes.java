package CircleAndSquare;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

import java.util.Random;

public class Shapes extends Application {

    static int BOX_SIZE = 800; // Størrelsen på boksen firkantene og sirklene skal være i
    static int OBJECT_SIZE = 30; // Størrelse på firkantene og sirklene.

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Firkant og Sirkel");
        BorderPane pane = new BorderPane();
        javafx.scene.control.Button square = new Button("Firkant");
        Button circle = new Button("Sirkel");
        Button red = new Button("Rød");
        Button blue = new Button("Blå");
        Button rotate = new Button("Roter");

        Pane centerPane = new Pane();
        centerPane.setPrefSize(BOX_SIZE, BOX_SIZE);
        centerPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        HBox topRight = new HBox(5);
        HBox topLeft = new HBox(5);

        VBox lBox = new VBox(5);
        VBox rBox = new VBox(5);
        HBox bBox = new HBox(5);
        HBox tBox = new HBox(5);

        lBox.getChildren().addAll(red, blue);
        rBox.getChildren().addAll(rotate);
        bBox.getChildren().addAll(square, circle);
        tBox.getChildren().addAll(topLeft, topRight);
        bBox.setAlignment(Pos.BOTTOM_CENTER);
        rBox.setAlignment(Pos.CENTER_RIGHT);
        lBox.setAlignment(Pos.CENTER_LEFT);
        tBox.setAlignment(Pos.CENTER);
        pane.setCenter(centerPane);
        pane.setLeft(lBox);
        pane.setRight(rBox);
        pane.setBottom(bBox);
        pane.setTop(tBox);

        square.setOnAction(event -> {
            Color currentColor = Color.BLACK;
            if (getCurrent(centerPane) != null) {
                currentColor = (Color) ((Shape) getCurrent(centerPane)).getFill();
            }
            Rektangel rek = new Rektangel(OBJECT_SIZE, currentColor);
            try {
                centerPane.getChildren().removeIf(node -> node instanceof Rektangel);
                centerPane.getChildren().stream()
                        .filter(e -> e instanceof Sirkel)
                        .map(e -> (Sirkel) e)
                        .forEach(Sirkel::setCurrentFalse);

            } catch (Exception ignored) { }
            rek.setCurrent(true);
            rek.setOnMouseClicked(e -> {
                Color cColor = (Color) ((Shape) getCurrent(centerPane)).getFill();
                removeCurrentStatus(centerPane);
                rek.setCurrentTrue(cColor);
            });
            topLeft.getChildren().clear();
            topLeft.getChildren().add(new TextField(rek.toString()));
            topLeft.setAlignment(Pos.CENTER_LEFT);
            centerPane.getChildren().add(rek);
        });

        circle.setOnAction(event -> {
            Color currentColor = Color.BLACK;
            if (getCurrent(centerPane) != null) {
                currentColor = (Color) ((Shape) getCurrent(centerPane)).getFill();
            }
            Sirkel sirkel = new Sirkel(OBJECT_SIZE, currentColor);
            try {
                centerPane.getChildren().removeIf(node -> node instanceof Sirkel);
                centerPane.getChildren().stream()
                        .filter(e -> e instanceof Rektangel)
                        .map(e -> (Rektangel) e)
                        .forEach(Rektangel::setCurrentFalse);
            } catch (Exception ignored) { }
            sirkel.setCurrent(true);  // Intellij snakker om duplikat kode, men uten denne fungere det ikke
            sirkel.setOnMouseClicked(e -> {
                Color cColor = (Color) ((Shape) getCurrent(centerPane)).getFill();
                removeCurrentStatus(centerPane);
                sirkel.setCurrentTrue(cColor);
            });
            topRight.getChildren().clear();
            topRight.getChildren().add(new TextField(sirkel.toString()));
            topRight.setAlignment(Pos.CENTER_RIGHT);
            centerPane.getChildren().add(sirkel);
        });


        red.setOnAction(event -> {
            setCurrentColor(Color.RED, centerPane);
        });

        blue.setOnAction(event -> {
            setCurrentColor(Color.BLUE, centerPane);
        });

        rotate.setOnAction(event -> {
            rotateRek(centerPane);
        });




        // Sett scene og start stage.
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    public void rotateRek(Pane p){
        for (Node n : p.getChildren()) {
            if (n instanceof Rektangel) {
                Rotate rotate = new Rotate();
                rotate.setPivotX(OBJECT_SIZE / 2.0);
                rotate.setPivotY(OBJECT_SIZE / 2.0);
                rotate.setAngle(45);
                n.getTransforms().add(rotate);
            }
        }
    }

    public void removeCurrentStatus(Pane centerPane) {
        for (Node n : centerPane.getChildren()) {
            if (n instanceof Sirkel) {
                ((Sirkel) n).setCurrentFalse();
            }
            if (n instanceof Rektangel) {
                ((Rektangel) n).setCurrentFalse();
            }
        }
    }


    private static void setCurrentColor(Color c, Pane p) {
        if (getCurrent(p) != null) {
            if (getCurrent(p) instanceof Shape) {
                ((Shape) getCurrent(p)).setFill(c);
            }
        }
    }

    private static Node getCurrent(Pane p) {
        for (Node n: p.getChildren()) {
            if (n instanceof Rektangel) {
                if (((Rektangel) n).isCurrent()) {
                    return n;
                }
            }

            if (n instanceof Sirkel) {
                if (((Sirkel) n).isCurrent()) {
                    return n;
                }
            }
        }
        return null;
    }

    static class Rektangel extends Rectangle {
        boolean current;


        Random random =  new Random();
        Rektangel(int size, Color c) {
            super.setHeight(size);
            super.setWidth(size);
            super.setFill(c);

            int randomX = random.nextInt((BOX_SIZE-size)-1);
            int randomY = random.nextInt((BOX_SIZE-(size*2))-1);
            super.relocate(randomX, randomY);
        }

        public boolean isCurrent() {
            return current;
        }

        public void setCurrentFalse() {
            current = false;
            this.setFill(Color.BLACK);
        }

        public void setCurrent(Boolean b) {
            current = b;
        }
        @Override
        public String toString() {
           return "Firkant: x = " + getLayoutX() + ". y = " + getLayoutY();
        }

        public void setCurrentTrue(Color c) {
            current = true;
            this.setFill(c);
        }
    }


    public static class Sirkel extends Circle {
        boolean current;
        Random random =  new Random();

        Sirkel(int size, Color c) {
            super.setRadius(size / 2.0);
            super.setFill(c);


            int randomX = random.nextInt((BOX_SIZE-size)-1);
            int randomY = random.nextInt((BOX_SIZE-(size*2))-1);
            super.relocate(randomX, randomY);
        }

        public void setCurrent(boolean b) {
            current = b;
        }

        public void setCurrentTrue(Color c) {
            current = true;
            this.setFill(c);
        }

        public void setCurrentFalse() {
            current = false;
            this.setFill(Color.BLACK);
        }

        public boolean isCurrent() {
            return current;
        }
        @Override
        public String toString() {
            return "Sirkel: x = " + getLayoutX() + ". y = " + getLayoutY();
        }
    }



}
