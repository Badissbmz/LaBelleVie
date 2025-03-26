package Project.Controllers;

import Project.Entities.Produit;
import Project.Services.ProduitService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AjouterProduitController {

    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private ComboBox<String> statusComboBox;
    @FXML
    private TextField quantiteField;

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

            Produit produit = new Produit(id, nom, status, quantite);
            produitService.ajouterProduit(produit);

            idField.clear();
            nomField.clear();
            statusComboBox.getSelectionModel().clearSelection();
            quantiteField.clear();

            showAlert("Succès", "Produit ajouté avec succès !");
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
}