package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Set<Noeud<E>> explores = new HashSet<>();
        Map<Noeud<E>, Double> coutTotalEstime = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        // Initialisation des coûts
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutTotalEstime.put(noeud, Double.POSITIVE_INFINITY);
        }

        coutTotalEstime.put(depart, 0.0);
        coutTotalEstime.put(depart, 0.0);
        PriorityQueue<Noeud<E>> aExplorer = new PriorityQueue<>((n1, n2) -> (int) (coutTotalEstime.get(n1) - coutTotalEstime.get(n2)));

        // Boucle principale
        aExplorer.add(depart);
        while (!aExplorer.isEmpty()) {
            Noeud<E> noeudCourant = aExplorer.poll();

            if (noeudCourant.equals(arrivee)) {
                // Reconstruction du chemin
                return reconstruireChemin(predecesseurs, arrivee);
            }

            explores.add(noeudCourant);

            for (Noeud<E> voisin : graphe.getVoisins(noeudCourant)) {
                if (explores.contains(voisin)) {
                    continue;
                }

                double nouveauCout = coutTotalEstime.get(noeudCourant) + graphe.getCoutArete(noeudCourant, voisin);

                if (nouveauCout < coutTotalEstime.get(voisin)) {
                    coutTotalEstime.put(voisin, nouveauCout);
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