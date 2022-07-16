public class Fichier extends Composant{

        public Fichier(String nom) {
            super(nom);
            //TODO Auto-generated constructor stub
        }
        
        public void affiche(int tabulations){
            System.out.print(("├── " +getNom()).indent(tabulations));}
        
        }

