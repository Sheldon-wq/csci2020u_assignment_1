package questions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Arrays;

public class Question_1 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();

        //Pick random cards (without repeating cards)
        int[] selections = new int[3];
        for(int i = 0; i < selections.length; i++) {
            int num;
            boolean dup;
            do {
                dup = false;
                num = (int)(Math.random()*54+1);
                for(int j:selections) {
                    if(j==num) {
                        dup = true;
                        break;
                    }
                }
            }while(dup == true);
            selections[i] = num;
        }

        //Display the cards
        ImageView card1 = new ImageView(new Image("image\\cards\\" + selections[0] +".png"));
        ImageView card2 = new ImageView(new Image("image\\cards\\" + selections[1] +".png"));
        ImageView card3 = new ImageView(new Image("image\\cards\\" + selections[2] +".png"));
        pane.add(card1,0,0);
        pane.add(card2,1,0);
        pane.add(card3,2,0);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_1");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
