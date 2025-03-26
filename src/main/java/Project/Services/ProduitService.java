package Project.Services;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import Project.Entities.Produit;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ProduitService {

    private final Firestore db;

    public ProduitService() {
        this.db = FirestoreClient.getFirestore();
    }

    // 🔥 Ajouter un produit à Firestore
    public void ajouterProduit(Produit produit) {
        DocumentReference docRef = db.collection("Produits").document(String.valueOf(produit.getId()));

        // Création de la structure de données
        Map<String, Object> produitData = new HashMap<>();
        produitData.put("id", produit.getId());
        produitData.put("nom", produit.getNom());
        produitData.put("status", produit.getStatus());
        produitData.put("quantite", produit.getQuantite());

        try {
            WriteResult result = docRef.set(produitData).get();
            System.out.println("✅ Produit ajouté à Firestore à : " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de l'ajout du produit !");
        }
    }
}
