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
        pane.setPrefSize(400, 400);

        int size = 8;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Rectangle rute = new Rectangle();

                rute.heightProperty().bind(pane.heightProperty().divide(size));
                rute.widthProperty().bind(pane.widthProperty().divide(size));
                rute.xProperty().bind(pane.widthProperty().divide(size).multiply(i));
                rute.yProperty().bind(pane.heightProperty().divide(size).multiply(j));

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


