package questions;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
        mainPane.setMaxSize(450,215);

        //Bar chart components
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> histogram = new BarChart<String,Number>(xAxis,yAxis);
        histogram.setLegendVisible(false);
        updateData(histogram);

        //User input components
        HBox textPane = new HBox();
        Label filename = new Label("Filename");

        TextField textfield = new TextField();
        textfield.setMinWidth(340);

        //Allow user to press the enter key to open and read from the file
        textfield.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                //Open specified file
                openFile(histogram,textfield);
            }
        });

        //Set button to count the letters in the specified file when pressed
        Button viewButton = new Button("View");
        viewButton.setOnAction(actionEvent -> {
            //Open the specified file
            openFile(histogram,textfield);
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

    /**
     * Updates the data displayed on the bar chart
     * @param chart: the chart whose data is being updated
     */
    public void updateData(BarChart chart) {
        chart.getData().clear();
        XYChart.Series countSeries = new XYChart.Series();
        for(int i = 0; i < 26; i++) {
            countSeries.getData().add(new XYChart.Data(String.valueOf((char)(i+65)),counts[i]));
        }
        chart.getData().add(countSeries);

        for(int i = 0; i < 26; i++) { //reset counts of each character after plotting
            counts[i] = 0;
        }
    }

    /**
     * Opens a file and counts the number of times each letter in the alphabet appears in the file
     * @param chart: the chart being used to display letter counts
     * @param text: text field containing the absolute path to the file
     */
    public void openFile(BarChart chart, TextField text) {
        try {
            File file = new File(text.getText());
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
            updateData(chart);
        }
        catch(Exception e) {
            System.out.println("Error: file not found");
        }
    }

    public static void main(String[] args) { launch(args); }
}
