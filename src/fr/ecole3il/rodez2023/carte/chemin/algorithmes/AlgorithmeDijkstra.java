package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

/**
 * Cette classe implémente l'algorithme de Dijkstra pour trouver le plus court chemin entre deux nœuds dans un graphe.
 * @param <E> le type de données stockées dans les nœuds du graphe
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve le plus court chemin entre deux nœuds dans un graphe en utilisant l'algorithme de Dijkstra.
     *
     * @param graphe   le graphe dans lequel trouver le chemin
     * @param depart   le nœud de départ du chemin
     * @param arrivee  le nœud d'arrivée du chemin
     * @return une liste de nœuds représentant le plus court chemin trouvé
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des coûts et des prédécesseurs
        Map<Noeud<E>, Double> cout = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            cout.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        cout.put(depart, 0.0);

        // File de priorité pour stocker les nœuds à explorer, triés par coût
        PriorityQueue<Noeud<E>> filePrioritaire = new PriorityQueue<>((n1, n2) -> (int) (cout.get(n1) - cout.get(n2)));
        filePrioritaire.add(depart);

        // Boucle principale de l'algorithme de Dijkstra
        while (!filePrioritaire.isEmpty()) {
            Noeud<E> noeud = filePrioritaire.poll();
            if (noeud.equals(arrivee)) break; // Arrête si le nœud d'arrivée est atteint

            // Parcours des voisins du nœud actuel
            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double coutActuel = cout.get(noeud) + graphe.getCoutArete(noeud, voisin);
                if (coutActuel < cout.get(voisin)) {
                    // Mise à jour des informations si un meilleur chemin est trouvé vers un voisin
                    cout.put(voisin, coutActuel);
                    predecesseurs.put(voisin, noeud);
                    filePrioritaire.add(voisin);
                }
            }
        }

        // Reconstruction du plus court chemin à partir des prédécesseurs
        List<Noeud<E>> plusCourtChemin = new LinkedList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            plusCourtChemin.add(0, noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(plusCourtChemin);

        return plusCourtChemin;
    }
}
