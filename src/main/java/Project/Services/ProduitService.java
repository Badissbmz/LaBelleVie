package Project.Services;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import Project.Entities.Produit;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;
import java.util.List;

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
        produitData.put("date", produit.getDate()); // Add the date field

        try {
            WriteResult result = docRef.set(produitData).get();
            System.out.println("✅ Produit ajouté à Firestore à : " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de l'ajout du produit !");
        }
    }


    // 🔥 Récupérer tous les produits de Firestore
    public List<Produit> getAllProduits() {
        List<Produit> produits = new ArrayList<>();
        ApiFuture<QuerySnapshot> future = db.collection("Produits").get();
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                Produit produit = document.toObject(Produit.class);
                produits.add(produit);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de la récupération des produits !");
        }
        return produits;
    }
    // ProduitService.java
    public void supprimerProduit(int id) {
        DocumentReference docRef = db.collection("Produits").document(String.valueOf(id));
        try {
            ApiFuture<WriteResult> writeResult = docRef.delete();
            System.out.println("✅ Produit supprimé à : " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de la suppression du produit !");
        }
    }
    // 🔥 Modify a product in Firestore
    public void modifierProduit(Produit produit) {
        DocumentReference docRef = db.collection("Produits").document(String.valueOf(produit.getId()));

        // Create a map of the updated product data
        Map<String, Object> produitData = new HashMap<>();
        produitData.put("id", produit.getId());
        produitData.put("nom", produit.getNom());
        produitData.put("status", produit.getStatus());
        produitData.put("quantite", produit.getQuantite());
        produitData.put("date", produit.getDate());

        try {
            WriteResult result = docRef.set(produitData).get();
            System.out.println("✅ Produit modifié à Firestore à : " + result.getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("❌ Erreur lors de la modification du produit !");
        }
    }
}