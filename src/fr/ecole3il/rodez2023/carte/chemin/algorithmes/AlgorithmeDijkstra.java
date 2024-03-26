package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    /*
            Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0);

        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> (int) (couts.get(n1) - couts.get(n2)));
        filePriorite.add(depart);
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>,Double> couts = new HashMap<>();
        Map<Noeud<E>,Noeud<E>> predecesseurs = new HashMap<>();
        for (Noeud<E> noeud : graphe.getNoeuds()){
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud,null);
        }
        couts.put(depart,0.0);
        PriorityQueue<Noeud<E>> aExplorer = new PriorityQueue<>((n1, n2) -> (int) (couts.get(n1) - couts.get(n2)));
        aExplorer.add(depart);
        while (!aExplorer.isEmpty()) {
            Noeud<E> noeudCourant = aExplorer.poll();

            if (noeudCourant.equals(arrivee)) {
                // Reconstruction du chemin
                return reconstruireChemin(predecesseurs, arrivee);
            }

            for (Noeud<E> voisin : graphe.getVoisins(noeudCourant)) {
                double nouveauCout = couts.get(noeudCourant) + graphe.getCoutArete(noeudCourant, voisin);

                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout);
                    predecesseurs.put(voisin, noeudCourant);
                    aExplorer.add(voisin);
                }
            }
        }

        // Aucun chemin trouvé
        return null;
    }

    private List<Noeud<E>> reconstruireChemin(Map<Noeud<E>, Noeud<E>> predecesseurs, Noeud<E> arrivee) {
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeudCourant = arrivee;

        while (predecesseurs.containsKey(noeudCourant)) {
            chemin.add(0, noeudCourant);
            noeudCourant = predecesseurs.get(noeudCourant);
        }
        chemin.add(0, noeudCourant);

        return chemin;
    }
}
