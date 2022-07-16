public abstract class Composant {
    private String nom;
    public Composant(String nom) { this.nom = nom; }
    public String getNom() { return this.nom; }
    public abstract void affiche(int tabulations);
    public void ajouter(Composant c) {}
}