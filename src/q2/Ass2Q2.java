/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q2;


import java.text.DecimalFormat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Donia Hassona 220160783
 */
public class Ass2Q2 extends Application {

    private TextField textFieldTemp;
    private Label label1;
    private RadioButton fahrenheitBtn;
    private RadioButton kelvinBtn;
    private Label label2;

    @Override
    public void start(Stage primaryStage) {
        textFieldTemp = new TextField();
        label1 = new Label("Enter Celsius temperature");
        fahrenheitBtn = new RadioButton("Fahrenheit");
        kelvinBtn = new RadioButton("Kelvin");
        label2 = new Label();

        HBox hBox = new HBox(20, fahrenheitBtn, kelvinBtn);
        hBox.setPadding(new Insets(20));

        VBox vBox = new VBox(20, label1, textFieldTemp, hBox, label2);
        vBox.setPadding(new Insets(20));

        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox, 400, 250);

        ToggleGroup radioGroup = new ToggleGroup();

        fahrenheitBtn.setToggleGroup(radioGroup);
        kelvinBtn.setToggleGroup(radioGroup);

        fahrenheitBtn.setOnAction(event -> {
            double toNum = Double.parseDouble(textFieldTemp.getText());
            if (fahrenheitBtn.isSelected()) {
                double celsiusResult = (toNum * 5 / 9) + 32;
                label2.setText("New Temperature is: " + new DecimalFormat("##.##").format(celsiusResult) + " F");
            } else {
                double fahrenheitResult = toNum + 273.15;
                label2.setText("New Temperature is: " + new DecimalFormat("##.##").format(fahrenheitResult) + " K");
            }
        });

        kelvinBtn.setOnAction(event -> {
            double toNum = Double.parseDouble(textFieldTemp.getText());
            if (fahrenheitBtn.isSelected()) {
                double celsiusResult = (toNum * 5 / 9) + 32;
                label2.setText("New Temperature is: " + new DecimalFormat("##.##").format(celsiusResult) + " F");
            } else {
                double fahrenheitResult = toNum + 273.15;
                label2.setText("New Temperature is: " + new DecimalFormat("##.##").format(fahrenheitResult) + " K");
            }
        });
        
        primaryStage.setTitle("Temperature Converter ( Donia Hassona 220160783 ) ");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
