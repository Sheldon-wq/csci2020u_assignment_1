package questions;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class Question_4 extends Application {
    int[] counts = new int[26];
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane mainPane = new GridPane();
        mainPane.setMaxSize(430,215);

        //Bar chart components
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> histogram = new BarChart<String,Number>(xAxis,yAxis);
        histogram.setLegendVisible(false);
        /*
        XYChart.Series countSeries = new XYChart.Series();
        for(int i = 0; i < 26; i++) {
            countSeries.getData().add(new XYChart.Data(String.valueOf((char)(i+65)),counts[i]));
        }
        histogram.getData().add(countSeries);
        */
        updateData(histogram);

        //User input components
        HBox textPane = new HBox();
        Label filename = new Label("Filename");
        TextField textfield = new TextField();
        textfield.setMinWidth(340);
        Button viewButton = new Button("View");

        viewButton.setOnAction(actionEvent -> {
            //Open the specified file
            try {
                File file = new File(textfield.getText());
                Scanner input = new Scanner(file);
                while(input.hasNextLine()) {
                    String line = input.nextLine();
                    for(int i = 0; i < line.length(); i++) { //Check each character in the line
                        char letter = line.charAt(i);
                        int ascii = (int)letter;
                        if((ascii < 92) && (ascii > 64)) { //Count upper case letters
                            ascii -= 65;
                            counts[ascii]++;
                        }
                        else if((ascii > 96) && (ascii < 123)) { //Count lower case letters
                            ascii -= 97;
                            counts[ascii]++;
                        }
                    }
                }
                updateData(histogram);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        });

        textPane.getChildren().addAll(filename,textfield,viewButton);
        mainPane.add(histogram,0,0);
        mainPane.add(textPane,0,1);


        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_4");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    //Updates the data displayed on the bar chart
    public void updateData(BarChart chart) {
        chart.getData().clear();
        XYChart.Series countSeries = new XYChart.Series();
        for(int i = 0; i < 26; i++) {
            countSeries.getData().add(new XYChart.Data(String.valueOf((char)(i+65)),counts[i]));
            System.out.print(counts[i]+" "); //delete
        }
        System.out.print("\n"); //delete
        chart.getData().add(countSeries);

        for(int i = 0; i < 26; i++) { //reset counts of each character after plotting
            counts[i] = 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
