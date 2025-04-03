package Project.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField NomUtilisateurTextField;

    @FXML
    private TextField MotdepasseTextField;

    @FXML
    private Button SeConnecterButton;

    @FXML
    private Label LabelLogin;

    @FXML
    private void handleSeConnecterButtonAction(ActionEvent event) {
        String username = NomUtilisateurTextField.getText();
        String password = MotdepasseTextField.getText();
        if (isValidCredentials(username, password)) {
            openMenuInterface();
        } else {
            LabelLogin.setText("Nom d'utilisateur ou mot de passe incorrect");
            LabelLogin.setStyle("-fx-text-fill: red;");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        return "adminlabellevie".equals(username) && "adminlabellevie2025".equals(password);
    }

    private void openMenuInterface() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
            Stage stage = (Stage) SeConnecterButton.getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}