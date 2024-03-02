package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));

        // Initialiser les coûts avec l'infini sauf pour le nœud de départ
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0); // Coût du départ à zéro

        // Exploration des nœuds
        filePriorite.offer(depart);
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break; // Arrêt si le nœud d'arrivée est atteint
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double coutTotal = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (coutTotal < couts.get(voisin)) {
                    couts.put(voisin, coutTotal);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.offer(voisin);
                }
            }
        }

        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudActuel = arrivee;
        while (noeudActuel != null) {
            chemin.add(noeudActuel);
            noeudActuel = predecesseurs.get(noeudActuel);
        }
        Collections.reverse(chemin);

        return chemin;
    }
}
