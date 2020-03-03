package questions;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Question_3 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //3 points of the triangle
        Circle[] points = new Circle[3];
        for(int i = 0; i < 3; i++) {
            points[i] = new Circle();
            points[i].setRadius(5);
            points[i].setFill(Color.RED);
        }
        points[0].setCenterX(170);
        points[0].setCenterY(150);
        points[1].setCenterX(225);
        points[1].setCenterY(150);
        points[2].setCenterX(200);
        points[2].setCenterY(200);

        //Allow moving the points using the mouse
        for(int i = 0; i < 3; i++) {
            Circle temp = points[i];
            temp.setOnMouseDragged(e->{
               temp.setCenterX(e.getX());
               temp.setCenterY(e.getY());
            });
        }


        //Lines connecting the points

        //The circle

        Group root = new Group(points);
        Scene scene = new Scene(root,400,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_3");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
