package Project.Controllers;

import Project.Entities.Produit;
import Project.Services.ProduitService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AjouterProduitController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextField quantiteField;
    @FXML
    private DatePicker datePicker; // Add this line


    private final ProduitService produitService = new ProduitService();

    @FXML
    public void initialize() {
        statusComboBox.getItems().addAll("Abîmé", "Périmé");
    }


    @FXML
    private void ajouterProduit() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nom = nomField.getText();
            String status = statusComboBox.getValue();
            int quantite = Integer.parseInt(quantiteField.getText());
            LocalDate selectedDate = datePicker.getValue(); // Retrieve the date

            // Convert the date to a string
            String dateString = selectedDate != null ? selectedDate.toString() : null;

            Produit produit = new Produit(id, nom, status, quantite, dateString); // Add date to the product
            produitService.ajouterProduit(produit);

            idField.clear();
            nomField.clear();
            statusComboBox.getSelectionModel().clearSelection();
            quantiteField.clear();
            datePicker.setValue(null); // Clear the date picker

            showAlert("Succès", "Produit ajouté avec succès !");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer des valeurs valides !");
        }
    }

    @FXML
    private void handleMenuButtonAction(javafx.event.ActionEvent event) {
        try {
            // Load the Menu FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and close it
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Create a new stage and show the Menu interface
            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Menu.fxml!");
        }
    }

    @FXML
    private void handleQuitterButtonAction(javafx.event.ActionEvent event) {
        try {
            // Load the Login FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and close it
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Create a new stage and show the Login interface
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Login.fxml!");
        }
    }
    @FXML
    private void handleConsulterButtonAction(javafx.event.ActionEvent event) {
        try {
            // Load the MesProduits FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MesProduits.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and close it
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Create a new stage and show the MesProduits interface
            Stage stage = new Stage();
            stage.setTitle("Mes Produits");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading MesProduits.fxml!");
        }
    }
    @FXML
    private void handleAnnulerButtonAction() {
        idField.clear();
        nomField.clear();
        statusComboBox.getSelectionModel().clearSelection();
        quantiteField.clear();
        datePicker.setValue(null);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}