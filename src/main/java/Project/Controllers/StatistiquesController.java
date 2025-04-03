package Project.Controllers;

import Project.Entities.Produit;
import Project.Services.ProduitService;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatistiquesController {

    @FXML
    private BarChart<String, Number> barChart;

    private final ProduitService produitService = new ProduitService();

    @FXML
    public void initialize() {
        // Load data from Firebase
        List<Produit> produits = produitService.getAllProduits();

        // Calculate the number of products for each status
        Map<String, Long> statusCount = produits.stream()
                .collect(Collectors.groupingBy(Produit::getStatus, Collectors.counting()));

        // Create a series for the bar chart
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Product Statistics");

        // Add data to the series
        series.getData().add(new XYChart.Data<>("Abîmé", statusCount.getOrDefault("Abîmé", 0L)));
        series.getData().add(new XYChart.Data<>("Périmé", statusCount.getOrDefault("Périmé", 0L)));

        // Add the series to the bar chart
        barChart.getData().add(series);
    }
}