package Project.Controllers;

import Project.Entities.Produit;
import Project.Services.ProduitService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MesProduitsController {

    @FXML
    private TableView<Produit> produitsTable;
    @FXML
    private TableColumn<Produit, String> ColumnId;
    @FXML
    private TableColumn<Produit, String> ColumnNom;
    @FXML
    private TableColumn<Produit, String> ColumnStatut;
    @FXML
    private TableColumn<Produit, String> ColumnQuantite;
    @FXML
    private TableColumn<Produit, String> ColumnDate;
    @FXML
    private Button SupprimerMesProduitsButton;
    @FXML
    private Button PerimesButton;
    @FXML
    private Button AbimesButton;
    @FXML
    private Button TriIdButton;
    @FXML
    private Button TriNomButton;
    @FXML
    private Button RechercherButton;
    @FXML
    private TextField RechercheTextField;
    @FXML
    private DatePicker DatePickerProduits;
    @FXML
    private Button OkButton;
    @FXML
    private Button StatistiquesMesProduitsButton;
    @FXML
    private Button ActualiserButton;
    @FXML
    private Button ModifierButton;


    private final ProduitService produitService = new ProduitService();
    private ObservableList<Produit> produitsObservableList;

    @FXML
    public void initialize() {
        // Initialize the columns
        ColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        ColumnStatut.setCellValueFactory(new PropertyValueFactory<>("status"));
        ColumnQuantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        ColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Load data from Firebase
        List<Produit> produits = produitService.getAllProduits();
        produitsObservableList = FXCollections.observableArrayList(produits);
        produitsTable.setItems(produitsObservableList);
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
    private void handleAjouterProduitButtonAction(javafx.event.ActionEvent event) {
        try {
            // Load the AjouterProduit FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterProduit.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and close it
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Create a new stage and show the AjouterProduit interface
            Stage stage = new Stage();
            stage.setTitle("Ajouter un produit");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading AjouterProduit.fxml!");
        }
    }

    @FXML
    private void handleSupprimerProduitButtonAction(javafx.event.ActionEvent event) {
        Produit selectedProduit = produitsTable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            produitService.supprimerProduit(selectedProduit.getId());
            produitsTable.getItems().remove(selectedProduit);
        } else {
            System.out.println("❌ Aucun produit sélectionné !");
        }
    }

    @FXML
    private void handlePerimesButtonAction(javafx.event.ActionEvent event) {
        List<Produit> perimes = produitsObservableList.stream()
                .filter(produit -> "Périmé".equals(produit.getStatus()))
                .collect(Collectors.toList());
        produitsTable.setItems(FXCollections.observableArrayList(perimes));
    }

    @FXML
    private void handleAbimesButtonAction(javafx.event.ActionEvent event) {
        List<Produit> abimes = produitsObservableList.stream()
                .filter(produit -> "Abîmé".equals(produit.getStatus()))
                .collect(Collectors.toList());
        produitsTable.setItems(FXCollections.observableArrayList(abimes));
    }
    @FXML
    private void handleTriIdButtonAction(javafx.event.ActionEvent event) {
        SortedList<Produit> sortedList = new SortedList<>(produitsObservableList);
        sortedList.setComparator(Comparator.comparingInt(Produit::getId));
        produitsTable.setItems(sortedList);
    }

    @FXML
    private void handleTriNomButtonAction(javafx.event.ActionEvent event) {
        SortedList<Produit> sortedList = new SortedList<>(produitsObservableList);
        sortedList.setComparator(Comparator.comparing(Produit::getNom));
        produitsTable.setItems(sortedList);
    }

    @FXML
    private void handleRechercherButtonAction(javafx.event.ActionEvent event) {
        String searchText = RechercheTextField.getText().toLowerCase();
        List<Produit> filteredList = produitsObservableList.stream()
                .filter(produit -> produit.getNom().toLowerCase().contains(searchText))
                .collect(Collectors.toList());
        produitsTable.setItems(FXCollections.observableArrayList(filteredList));
    }
    @FXML
    private void handleOkButtonAction(javafx.event.ActionEvent event) {
        LocalDate selectedDate = DatePickerProduits.getValue();
        if (selectedDate != null) {
            String dateString = selectedDate.toString();
            List<Produit> filteredList = produitsObservableList.stream()
                    .filter(produit -> dateString.equals(produit.getDate()))
                    .collect(Collectors.toList());
            produitsTable.setItems(FXCollections.observableArrayList(filteredList));
        }
    }
    @FXML
    private void handleStatistiquesButtonAction(javafx.event.ActionEvent event) {
        try {
            // Load the Statistiques FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Statistiques.fxml"));
            Parent root = loader.load();

            // Create a new stage and show the Statistiques interface
            Stage stage = new Stage();
            stage.setTitle("Statistiques des Produits");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Statistiques.fxml!");
        }
    }
    @FXML
    private void handleActualiserButtonAction(javafx.event.ActionEvent event) {
        // Reload data from Firebase
        List<Produit> produits = produitService.getAllProduits();
        produitsObservableList = FXCollections.observableArrayList(produits);
        produitsTable.setItems(produitsObservableList);
    }
    @FXML
    private void handleModifierButtonAction(javafx.event.ActionEvent event) {
        Produit selectedProduit = produitsTable.getSelectionModel().getSelectedItem();
        if (selectedProduit != null) {
            try {
                // Load the Modifier FXML file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierProduit.fxml"));
                Parent root = loader.load();

                // Get the controller of the Modifier interface
                ModifierProduitController modifierController = loader.getController();
                // Pass the selected product to the controller
                modifierController.setProduit(selectedProduit);

                // Get the current stage (window)
                Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

                // Set the new scene to the current stage
                currentStage.setScene(new Scene(root));
                currentStage.setTitle("Modifier Produit");
                currentStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error loading ModifierProduit.fxml!");
            }
        } else {
            System.out.println("❌ Aucun produit sélectionné !");
        }
    }
}