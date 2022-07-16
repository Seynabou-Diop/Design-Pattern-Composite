import java.util.ArrayList;

public class Dossier extends Composant
{
	private ArrayList<Composant> composants = new ArrayList<Composant>();

	public Dossier(String nom)
	{
		super(nom);
	}


	public void ajouter(Composant composant)
	{
		this.composants.add(composant);
	}

	public void affiche(int tabulations )
	{
		System.out.print(getNom().indent(tabulations) );
		for (Composant composant : composants) 
		{
			
			composant.affiche(tabulations+4);
		}
	}
}

