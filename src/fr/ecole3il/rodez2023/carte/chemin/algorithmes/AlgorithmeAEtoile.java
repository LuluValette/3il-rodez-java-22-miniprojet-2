package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

/**
 * Cette classe implémente l'algorithme A* pour trouver le chemin le plus court entre deux nœuds dans un graphe.
 * @param <E> le type de données stockées dans les nœuds du graphe
 */
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve le chemin le plus court entre deux nœuds dans un graphe en utilisant l'algorithme A*.
     *
     * @param graphe   le graphe dans lequel trouver le chemin
     * @param depart   le nœud de départ du chemin
     * @param arrivee  le nœud d'arrivée du chemin
     * @return une liste de nœuds représentant le chemin le plus court trouvé
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des coûts estimés et réels ainsi que des nœuds précédents
        Map<Noeud<E>, Double> coutEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutActuel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutActuel.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        coutEstime.put(depart, 0.0);
        coutActuel.put(depart, 0.0);

        // File de priorité pour stocker les nœuds à explorer, triés par coût estimé
        PriorityQueue<Noeud<E>> FilePrioritaire = new PriorityQueue<>((n1, n2) -> (int) (coutEstime.get(n1) - coutEstime.get(n2)));
        FilePrioritaire.add(depart);

        // Boucle principale de l'algorithme A*
        while (!FilePrioritaire.isEmpty()) {
            Noeud<E> noeud = FilePrioritaire.poll();
            if (noeud.equals(arrivee)) break; // Arrête si le nœud d'arrivée est atteint

            // Parcours des voisins du nœud actuel
            for (Noeud<E> voisins : graphe.getVoisins(noeud)) {
                double cout = coutActuel.get(noeud) + graphe.getCoutArete(noeud, voisins);
                if (cout < coutActuel.get(voisins)) {
                    // Mise à jour des informations si un meilleur chemin est trouvé vers un voisin
                    predecesseurs.put(voisins, noeud);
                    coutActuel.put(voisins, cout);
                    coutEstime.put(voisins, cout);
                    FilePrioritaire.add(voisins);
                }
            }
        }

        // Reconstruction du chemin à partir des nœuds précédents
        List<Noeud<E>> chemin = new LinkedList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin);

        return chemin;
    }
}
