package questions;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
        points[0].setCenterX(100); //Point A
        points[0].setCenterY(200);
        points[1].setCenterX(200); //Point B
        points[1].setCenterY(100);
        points[2].setCenterX(200); //Point C
        points[2].setCenterY(300);

        //Lines connecting the points
        Line a = new Line(points[1].getCenterX(),points[1].getCenterY(),points[2].getCenterX(),points[2].getCenterY());
        Line b = new Line(points[2].getCenterX(),points[2].getCenterY(),points[0].getCenterX(),points[0].getCenterY());
        Line c = new Line(points[0].getCenterX(),points[0].getCenterY(),points[1].getCenterX(),points[1].getCenterY());

        //Bind lines to points
        a.startXProperty().bind(points[1].centerXProperty());
        a.startYProperty().bind(points[1].centerYProperty());
        a.endXProperty().bind(points[2].centerXProperty());
        a.endYProperty().bind(points[2].centerYProperty());

        b.startXProperty().bind(points[2].centerXProperty());
        b.startYProperty().bind(points[2].centerYProperty());
        b.endXProperty().bind(points[0].centerXProperty());
        b.endYProperty().bind(points[0].centerYProperty());

        c.startXProperty().bind(points[0].centerXProperty());
        c.startYProperty().bind(points[0].centerYProperty());
        c.endXProperty().bind(points[1].centerXProperty());
        c.endYProperty().bind(points[1].centerYProperty());

        //The circle
        Circle perimeter = new Circle();
        perimeter.setCenterX(200);
        perimeter.setCenterY(200);
        perimeter.setRadius(100);
        perimeter.setFill(Color.WHITE);
        perimeter.setStroke(Color.BLACK);

        //Allow moving the points along the perimeter circle by dragging the mouse
        for(int i = 0; i < 3; i++) {
            Circle temp = points[i];
            temp.setOnMouseDragged(e->{
                //Determine point on the perimeter closest to the mouse position
                //Determine angle
                double theta = (Math.atan((e.getY()-200)/(e.getX()-200)));

                //Position the point
                if(e.getX() < 200) {
                    temp.setCenterX(-(100*Math.cos(theta))+200);
                    temp.setCenterY(-(100*Math.sin(theta))+200);
                }
                else {
                    temp.setCenterX(100 * Math.cos(theta) + 200);
                    temp.setCenterY(100 * Math.sin(theta) + 200);
                }
            });
        }

        //Add all the shapes to the group
        Group root = new Group();
        root.getChildren().add(perimeter);
        root.getChildren().add(a);
        root.getChildren().add(b);
        root.getChildren().add(c);
        root.getChildren().addAll(points);

        Scene scene = new Scene(root,400,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_3");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
