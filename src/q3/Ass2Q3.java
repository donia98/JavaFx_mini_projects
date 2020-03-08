/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author nadoo
 */
public class Ass2Q3 extends Application {
    
    private Menu file;
    private Menu edit;
    private TextArea textArea;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        file = new Menu("File");
        edit = new Menu("Edit");
        textArea = new TextArea();
        MenuBar mb = new MenuBar();
        mb.getMenus().add(file);
        mb.getMenus().add(edit);
        
        MenuItem open = new MenuItem("Open");
        MenuItem close = new MenuItem("Close");
        MenuItem exit = new MenuItem("Exit");
        
        file.getItems().add(open);
        file.getItems().add(close);
        file.getItems().add(exit);
        
        MenuItem font = new MenuItem("Font");
        MenuItem color = new MenuItem("Color");
        
        edit.getItems().add(font);
        edit.getItems().add(color);
        
        VBox vBox = new VBox(20, mb, textArea);
        vBox.setPadding(new Insets(10));
        
        Scene scene = new Scene(vBox, 400, 300);

        // Register an event handler for the exit item.
        exit.setOnAction(event
                -> {
            primaryStage.close();
        });
        // Register an event handler for clear textarea
        close.setOnAction(event
                -> {
            textArea.clear();
            textArea.setEditable(false);
        });

        // Register an event handler for open file and display it's content in the textarea
        open.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();

                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);

                //Show save file dialog
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    try {
                        textArea.setText(readFile(file));
                        textArea.setEditable(true);
                    } catch (IOException ex) {
                        Logger.getLogger(Ass2Q3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        });

//        int fonts[] = {12,14,16,18,20,24};
        List<Integer> fonts = new ArrayList<>();
        fonts.add(12);
        fonts.add(14);
        fonts.add(16);
        fonts.add(20);
        // create a choice dialog 
        ChoiceDialog d1 = new ChoiceDialog<>(fonts.get(0), fonts);
        
        font.setOnAction(event
                -> {
            d1.setHeaderText("Select the font size from the list");            
            d1.setContentText("Available sizes");            
            d1.showAndWait();            
            textArea.setStyle("-fx-font-size: " + d1.getSelectedItem() + ";");
        });
        
        List<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("yellow");
        colors.add("pink");
        // create a choice dialog
        ChoiceDialog<String> d2 = new ChoiceDialog<>(colors.get(0), colors);
        
        color.setOnAction(event
                -> {
            d2.setHeaderText("Select the color from the list");            
            d2.setContentText("Available colors");            
            Optional<String> result = d2.showAndWait();
            if (result.isPresent()) {
                textArea.setStyle("-fx-text-fill: " + d2.getSelectedItem() + ";");
            }
            
        });
        
        primaryStage.setTitle("File View ( Donia Hassona 220160783 )");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private String readFile(File file) throws IOException {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;
        
        try {
            
            bufferedReader = new BufferedReader(new FileReader(file));
            
            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ass2Q3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ass2Q3.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Ass2Q3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return stringBuffer.toString();
    }
}
