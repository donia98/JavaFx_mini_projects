package q4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Donia Hassona
 * @id 220160783
 */
public class Ass2Q4 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User ");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome");
        scenetitle.setId("welcome-text");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String password = pwBox.getText();
                MessageDigest md = null;
                try {
                    md = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Ass2Q4.class.getName()).log(Level.SEVERE, null, ex);
                }
                md.update(password.getBytes());
                byte[] digest = md.digest();
                String myHash = DatatypeConverter.printHexBinary(digest);
//            System.out.println(myHash);

                try {
                    File file = new File("users.txt");    //creates a new file instance  
                    FileReader fr = new FileReader(file);   //reads the file  
                    BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream  
                    StringBuilder sb = new StringBuilder();    //constructs a string buffer with no characters  
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);      //appends line to string buffer  
                        sb.append("\n");     //line feed
                        if (line == userTextField.getText() + myHash) {
                            System.out.println("Found");
                            fr.close();
                        } else {
                            System.out.println("not Found");
                        }
                    }
                    fr.close();    //closes the stream and release the resources  
//                    System.out.println(sb.toString());   //returns a string that textually represents the object  
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ass2Q4.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Ass2Q4.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
//        scene.getStylesheets().add(Ass2Q4.class.getResource("login.css").toExternalForm());
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
