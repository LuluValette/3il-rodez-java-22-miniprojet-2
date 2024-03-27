package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.List;

/**
 * Interface définissant un algorithme pour trouver un chemin dans un graphe.
 * @param <E> le type de données stockées dans les nœuds du graphe
 */
public interface AlgorithmeChemin<E> {

    /**
     * Trouve le chemin entre deux nœuds dans un graphe.
     *
     * @param graphe   le graphe dans lequel trouver le chemin
     * @param depart   le nœud de départ du chemin
     * @param arrivee  le nœud d'arrivée du chemin
     * @return une liste de nœuds représentant le chemin trouvé
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
