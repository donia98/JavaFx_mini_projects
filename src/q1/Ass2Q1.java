package q1;

import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Ass2Q1 extends Application {

    private Button Copy;
    private ListView<String> listViewNames;
    private ListView<String> listViewCopiedNames;

    @Override
    public void start(Stage primaryStage) throws Exception {

        listViewNames = new ListView<>();
        listViewCopiedNames = new ListView<>();
        Copy = new Button("Copy Text");
        HBox hBox = new HBox(20, listViewNames, Copy, listViewCopiedNames);
        hBox.setPadding(new Insets(40));
        hBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(hBox, 800, 400);
        ObservableList<String> list = FXCollections.observableArrayList();

        listViewNames.setItems(list);

        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item2");
        list.add("item3");
        list.add("item1");
        list.add("item2");
        list.add("item3");

        listViewNames.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Alert warningM = new Alert(AlertType.NONE);
        Copy.setOnAction(event -> {

            if (!listViewNames.getSelectionModel().isEmpty()) {
                ObservableList<String> selectedItems = listViewNames.getSelectionModel().getSelectedItems();
                
                listViewCopiedNames.getItems().addAll(selectedItems);
                listViewCopiedNames.getItems().setAll(
                        listViewCopiedNames.getItems().stream()
                                .sorted()
                                .collect(Collectors.toList())
                );
            } else {

                warningM.setAlertType(AlertType.WARNING);
                warningM.setContentText("No item selections :( ");
                warningM.show();
            }
        });

        primaryStage.setTitle("Multiple Selection lists (Donia Hassona 220160783)");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
