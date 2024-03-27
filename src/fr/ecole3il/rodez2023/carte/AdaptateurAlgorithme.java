package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.*;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AdaptateurAlgorithme {

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        Graphe<Case> graphe = creerGraphe(carte);

        Tuile tuileDepart = carte.getTuile(xDepart, yDepart);
        Case depart = new Case(tuileDepart, xDepart, yDepart);

        Tuile tuileArrivee = carte.getTuile(xArrivee, yArrivee);
        Case arrivee = new Case(tuileArrivee, xArrivee, yArrivee);

        List<Noeud<Case>> chemin = algorithme.trouverChemin(graphe, new Noeud<>(depart), new Noeud<>(arrivee));

        return afficherChemin(chemin);
    }

    static Graphe<Case> creerGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for(int x = 0; x < largeur; x++){
            for(int y = 0; y < hauteur; y++){
                Tuile tuileCourante = carte.getTuile(x, y);
                Case caseCourante = new Case(tuileCourante, x, y);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));
                ajouterAretesVoisines(graphe, caseCourante, x, y, largeur, hauteur);
            }
        }
        return graphe;
    }

    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){
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

    static double calculerCout(Case from, Case to){
        if(from == null || to == null)
            return Double.POSITIVE_INFINITY;
        return 1;
    }
    static Chemin afficherChemin(List<Noeud<Case>> chemin){
        if (chemin.isEmpty()) {
            System.out.println("No path found!");
            return new Chemin(new ArrayList<>());
        }

        System.out.print("Path: ");
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : chemin){
            Case caseNode = noeud.getValeur();
            cheminCases.add(caseNode);
            System.out.print(" -> " + caseNode.toString());
        }
        System.out.println();

        return new Chemin(cheminCases);
    }
}
