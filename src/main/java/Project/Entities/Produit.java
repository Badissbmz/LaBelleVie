package Project.Entities;

public class Produit {
    private int id;          // ID entier
    private String nom;      // Nom du produit
    private String statut;   // "Périmé" ou "Abîmé"
    private int quantite;    // Quantité en stock
    private String date;     // Date de péremption

    // 🔹 Constructeurs
    public Produit() {
        // Constructeur vide requis pour Firebase
    }

    public Produit(int id, String nom, String statut, int quantite, String date) {
        this.id = id;
        this.nom = nom;
        this.statut = statut;
        this.quantite = quantite;
        this.date = date;
    }

    // 🔹 Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getStatus() { return statut; }
    public void setStatus(String status) { this.statut = status; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }


    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }


    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", statut='" + statut + '\'' +
                ", quantite=" + quantite +
                ", date='" + date + '\'' +
                '}';
    }
}
