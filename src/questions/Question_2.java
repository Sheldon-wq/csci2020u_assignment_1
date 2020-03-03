package questions;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Question_2 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Panes
        GridPane compPane = new GridPane();
        compPane.setPadding(new Insets(10,10,10,10));

        FlowPane buttonPane = new FlowPane();
        buttonPane.setPadding(new Insets(0,10,10,10));
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        VBox pane = new VBox();
        pane.setPrefWidth(277);

        //Labels
        Label investmentAmount = new Label("Investment Amount");
        Label years = new Label("Years");
        Label interest = new Label("Annual Interest Rate");
        Label futureVal = new Label("Future Value");

        //Text fields
        TextField investmentAmountT = new TextField();
        investmentAmountT.setAlignment(Pos.CENTER_RIGHT);
        TextField yearsT = new TextField();
        yearsT.setAlignment(Pos.CENTER_RIGHT);
        TextField interestT = new TextField();
        interestT.setAlignment(Pos.CENTER_RIGHT);
        TextField futureValT = new TextField();
        futureValT.setAlignment(Pos.CENTER_RIGHT);
        futureValT.setDisable(true);

        //Calculate button
        Button calculate = new Button("Calculate");
        calculate.setOnAction(actionEvent -> {
            //Calculate future value
            float monthlyInterestRate = Float.parseFloat(interestT.getText())/100/12;
            float futureValue = (float)(Float.parseFloat(investmentAmountT.getText())*
                                (Math.pow(1+monthlyInterestRate, Float.parseFloat(yearsT.getText())*12)));

            //Format output to display 2 decimal places rounded up
            DecimalFormat df = new DecimalFormat("0.00");
            df.setRoundingMode(RoundingMode.UP);
            futureValT.setText(df.format(futureValue));
        });

        //Add components to panes
        compPane.add(investmentAmount,0,0);
        compPane.add(years,0,1);
        compPane.add(interest,0,2);
        compPane.add(futureVal,0,3);

        compPane.add(investmentAmountT,1,0);
        compPane.add(yearsT,1,1);
        compPane.add(interestT,1,2);
        compPane.add(futureValT,1,3);

        buttonPane.getChildren().add(calculate);

        pane.getChildren().add(compPane);
        pane.getChildren().add(buttonPane);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question_2");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
