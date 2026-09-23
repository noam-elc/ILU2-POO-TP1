package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	private class Marche {
		private Etal[] etals;

		public Marche(int nbEtalsMax) {
			etals = new Etal[nbEtalsMax];
			for (int i = 0; i < nbEtalsMax; i++)
				etals[i] = new Etal();
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (etals[indiceEtal].isEtalOccupe()) {
				System.out.println("Cet étal est déjà occupé par un autre villageois !");
			} else {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
		}

		public int trouverEtalLibre() {
			int resultat = -1;
			for (int i = 0; i < etals.length; i++) {
				if (!etals[i].isEtalOccupe())
					resultat = i;
			}
			return resultat;
		}

		public Etal[] trouverEtals(String produits) {
			int nb_etals_vendant_produits = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produits))
					nb_etals_vendant_produits++;
			}
			Etal[] etals_vendant_produits = new Etal[nb_etals_vendant_produits];
			int indice_parcours_tableau = 0;
			for (int j = 0; j < etals.length; j++) {
				if (etals[j].contientProduit(produits))
					etals_vendant_produits[indice_parcours_tableau] = etals[j];
			}
			return etals_vendant_produits;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			Etal trouver_gaulois_vendeur = null;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois)
					trouver_gaulois_vendeur = etals[i];
			}
			return trouver_gaulois_vendeur;
		}

		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nb_etals_libres = 0;
			if (etals.length == 0) {
				chaine.append("Il n'y a aucun étal dans le marché.\n");
			} else {
				chaine.append("Dans le village, il y a :\n");
				for (int i = 0; i < etals.length; i++) {
					if (etals[i].isEtalOccupe()) {
						chaine.append(etals[i].afficherEtal());
					} else {
						nb_etals_libres++;
					}
				}

			}
			chaine.append("Il reste " + nb_etals_libres + " étals non utilisés dans le marché.\n");
			return chaine.toString();
		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}