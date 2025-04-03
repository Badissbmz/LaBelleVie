package Project.Controllers;
import Project.Services.ProduitService;
import Project.Entities.Produit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;
import java.io.IOException;

public class ModifierProduitController {

    @FXML
    private TextField ModifierIdField;
    @FXML
    private TextField ModifierNomField;
    @FXML
    private ComboBox<String> ModifierStatusComboBox;
    @FXML
    private TextField ModifierQuantiteField;
    @FXML
    private DatePicker ModifierDatePicker;

    private final ProduitService produitService = new ProduitService();

    @FXML
    public void initialize() {
        ModifierStatusComboBox.getItems().addAll("Abîmé", "Périmé");
    }
    public void setProduit(Produit produit) {
        ModifierIdField.setText(String.valueOf(produit.getId()));
        ModifierNomField.setText(produit.getNom());
        ModifierStatusComboBox.setValue(produit.getStatus());
        ModifierQuantiteField.setText(String.valueOf(produit.getQuantite()));
        ModifierDatePicker.setValue(LocalDate.parse(produit.getDate()));
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
    private void handleModifierButtonAction() {
        try {
            int id = Integer.parseInt(ModifierIdField.getText());
            String nom = ModifierNomField.getText();
            String status = ModifierStatusComboBox.getValue();
            int quantite = Integer.parseInt(ModifierQuantiteField.getText());
            LocalDate date = ModifierDatePicker.getValue();

            Produit produit = new Produit(id, nom, status, quantite, date.toString());
            produitService.modifierProduit(produit);

            showAlert("Succès", "Produit modifié avec succès !");
        } catch (NumberFormatException e) {
            showAlert("Erreur", "Veuillez entrer des valeurs valides !");
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
    private void handleConsulterButtonAction2(javafx.event.ActionEvent event) {
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
    private void handleAnnulerButtonAction(javafx.event.ActionEvent event) {
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



}
