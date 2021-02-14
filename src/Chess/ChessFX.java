package Chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        pane.setPrefSize(160,160);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rute = new Rectangle();
                rute.widthProperty().bind(pane.widthProperty().divide(8));
                rute.heightProperty().bind(pane.heightProperty().divide(8));
                //rute.relocate(i * rute * 1.0, j * rute.getWidth());
               

                if ((i + j) % 2 == 0) {
                    rute.setFill(Color.WHITE);
                } else {
                    rute.setFill(Color.BLACK);
                }
                pane.getChildren().addAll(rute);


            }
        }



        Scene scene = new Scene(pane);
        stage.setTitle("Sjakkbrett");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
}


