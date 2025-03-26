package Project.Controllers;  // Ensure this is your actual package name

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Project.Utils.FirebaseConfig;

import java.io.IOException;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FirebaseConfig.initializeFirebase(); // Initialize Firebase
        Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        primaryStage.setTitle("Gestion du stock : LaBelleVie");
        primaryStage.setScene(new Scene(root, 875, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
