package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    // Initialisation des structures de données
    private Map<Noeud<E>, Double> couts = new HashMap<>();
    private Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
    private PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialiser les coûts avec l'infini sauf pour le nœud de départ
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            this.couts.put(noeud, Double.POSITIVE_INFINITY);
            this.predecesseurs.put(noeud, null);
        }
        this.couts.put(depart, 0.0); // Coût du départ à zéro

        // Exploration des nœuds
        this.filePriorite.offer(depart);
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break; // Arrêt si le nœud d'arrivée est atteint
            }
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double coutTotal = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (coutTotal < couts.get(voisin)) {
                    this.couts.put(voisin, coutTotal);
                    this.predecesseurs.put(voisin, noeudActuel);
                    this.filePriorite.offer(voisin);
                }
            }
        }

        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudActuel = arrivee;
        while (noeudActuel != null) {
            chemin.add(noeudActuel);
            noeudActuel = this.predecesseurs.get(noeudActuel);
        }
        Collections.reverse(chemin);

        return chemin;
    }
}
