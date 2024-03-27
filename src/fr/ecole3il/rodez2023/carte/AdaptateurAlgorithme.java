package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.*;

import java.util.*;

/**
 * Cette classe fournit des méthodes utilitaires pour adapter un algorithme de recherche de chemin générique
 * à une carte spécifique.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux points sur une carte en utilisant un algorithme de recherche de chemin.
     *
     * @param algorithme l'algorithme de recherche de chemin à utiliser
     * @param carte      la carte sur laquelle trouver le chemin
     * @param xDepart    la coordonnée x du point de départ
     * @param yDepart    la coordonnée y du point de départ
     * @param xArrivee   la coordonnée x du point d'arrivée
     * @param yArrivee   la coordonnée y du point d'arrivée
     * @return un objet Chemin représentant le chemin trouvé
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);

        Tuile tuileDepart = carte.getTuile(xDepart, yDepart);
        Case depart = new Case(tuileDepart, xDepart, yDepart);

        Tuile tuileArrivee = carte.getTuile(xArrivee, yArrivee);
        Case arrivee = new Case(tuileArrivee, xArrivee, yArrivee);

        List<Noeud<Case>> chemin = algorithme.trouverChemin(graphe, new Noeud<>(depart), new Noeud<>(arrivee));

        return afficherChemin(chemin);
    }

    /**
     * Crée un graphe à partir d'une carte.
     *
     * @param carte la carte à partir de laquelle créer le graphe
     * @return le graphe créé
     */
    static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Tuile tuileCourante = carte.getTuile(x, y);
                Case caseCourante = new Case(tuileCourante, x, y);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));
                ajouterAretesVoisines(graphe, caseCourante, x, y, largeur, hauteur);
            }
        }
        return graphe;
    }

    /**
     * Ajoute les arêtes entre une case et ses voisins dans le graphe.
     *
     * @param graphe  le graphe dans lequel ajouter les arêtes
     * @param currentCase la case actuelle
     * @param x       la coordonnée x de la case actuelle
     * @param y       la coordonnée y de la case actuelle
     * @param largeur la largeur de la carte
     * @param hauteur la hauteur de la carte
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if ((dx != 0 || dy != 0) && (x + dx >= 0 && x + dx < largeur && y + dy >= 0 && y + dy < hauteur)) {
                    Case voisin = graphe.getNoeuds().get((x + dx) * hauteur + (y + dy)).getValeur();
                    double cout = calculerCout(currentCase, voisin);
                    graphe.ajouterArete(new Noeud<>(currentCase), new Noeud<>(voisin), cout);
                }
            }
        }
    }

    /**
     * Calcule le coût entre deux cases (arêtes non diagonales).
     *
     * @param from la case de départ
     * @param to   la case d'arrivée
     * @return le coût entre les deux cases
     */
    static double calculerCout(Case from, Case to) {
        if (from == null || to == null)
            return Double.POSITIVE_INFINITY;
        return 1;
    }

    /**
     * Affiche le chemin trouvé.
     *
     * @param chemin la liste des nœuds constituant le chemin
     * @return un objet Chemin représentant le chemin trouvé
     */
    static Chemin afficherChemin(List<Noeud<Case>> chemin) {
        if (chemin.isEmpty()) {
            System.out.println("No path found!");
            return new Chemin(new ArrayList<>());
        }

        System.out.print("Path: ");
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : chemin) {
            Case caseNode = noeud.getValeur();
            cheminCases.add(caseNode);
            System.out.print(" -> " + caseNode.toString());
        }
        System.out.println();

        return new Chemin(cheminCases);
    }
}
