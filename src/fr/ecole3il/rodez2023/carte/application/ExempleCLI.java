package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 * @author p.roquart
 * genre on peut tester le code
 * c'est agaçant tous ces tests
 * 
 * de toute façon quand ça compile c'est bon
 */
public class ExempleCLI {

	public static void main(String[] args) {
		GenerateurCarte generateur = new GenerateurCarte();
		Carte test = generateur.genererCarte(100,100);
		AlgorithmeChemin algoChemin = new AlgorithmeDijkstra();
		Chemin chemin = algoChemin.trouverChemin(test, 0, 0, 50, 50);
		chemin.afficherChemin();
		chemin = algoChemin.trouverChemin(test, 0, 0, 50, 50);
		chemin.afficherChemin();
	}
	
}
