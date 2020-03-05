package questions;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
        double aLen= lineLength(a);
        double bLen = lineLength(b);
        double cLen = lineLength(c);

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

        //Labels for the angles
        Label A = new Label(String.valueOf(toDegrees((Math.acos((aLen*aLen-bLen*bLen-cLen*cLen)/(-2*bLen*cLen))))));
        A.translateXProperty().bind(points[0].centerXProperty());
        A.translateYProperty().bind(points[0].centerYProperty());

        Label B = new Label(String.valueOf(toDegrees((Math.acos((bLen*bLen-aLen*aLen-cLen*cLen)/(-2*aLen*cLen))))));
        B.translateXProperty().bind(points[1].centerXProperty());
        B.translateYProperty().bind(points[1].centerYProperty());

        Label C = new Label(String.valueOf(toDegrees((Math.acos((cLen*cLen-bLen*bLen-aLen*aLen)/(-2*aLen*bLen))))));
        C.translateXProperty().bind(points[2].centerXProperty());
        C.translateYProperty().bind(points[2].centerYProperty());

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
                //Determine angle
                double theta = (Math.atan((e.getY()-200)/(e.getX()-200)));

                //Position the point on the circumference of the circle
                if(e.getX() < 200) {
                    temp.setCenterX(-(100*Math.cos(theta))+200);
                    temp.setCenterY(-(100*Math.sin(theta))+200);
                }
                else {
                    temp.setCenterX(100*Math.cos(theta)+200);
                    temp.setCenterY(100*Math.sin(theta)+200);
                }

                //Calculate the angles of the triangle using provided cosine law
                double lenA = lineLength(a);
                double lenB = lineLength(b);
                double lenC = lineLength(c);

                A.setText(String.valueOf(toDegrees((Math.acos((lenA*lenA-lenB*lenB-lenC*lenC)/(-2*lenB*lenC))))));
                B.setText(String.valueOf(toDegrees((Math.acos((lenB*lenB-lenA*lenA-lenC*lenC)/(-2*lenA*lenC))))));
                C.setText(String.valueOf(toDegrees((Math.acos((lenC*lenC-lenB*lenB-lenA*lenA)/(-2*lenA*lenB))))));
            });
        }

        //Add all the shapes to the group
        Group root = new Group();
        root.getChildren().add(perimeter);
        root.getChildren().addAll(a,b,c);
        root.getChildren().addAll(points);
        root.getChildren().addAll(A,B,C);

        Scene scene = new Scene(root,400,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_3");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Converts an angle measurement from radians to degrees, rounding to the nearest degree
     * @param radians: the angle measurement in radians
     * @return the angle measurement in degrees, rounded to the nearest degree
     */
    public int toDegrees(double radians) {
        return (int)(radians*(180/Math.PI));
    }

    /**
     * Calculates the length of a line segment
     * @param line: the line whose length is being calculated
     * @return the length of the line segment
     */
    public double lineLength(Line line) {
        return Math.sqrt(Math.pow(line.getEndX()-line.getStartX(),2)+Math.pow(line.getEndY()-line.getStartY(),2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}