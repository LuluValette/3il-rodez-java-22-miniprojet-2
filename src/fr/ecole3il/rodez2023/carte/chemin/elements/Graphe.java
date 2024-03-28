package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.elements.Case;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Représente un graphe pondéré non orienté.
 * @param <E> le type de données stockées dans les nœuds du graphe
 */
public class Graphe<E> {
    private Map<Noeud<E>, Map<Noeud<E>, Double>> graphe;

    /**
     * Constructeur par défaut initialisant le graphe.
     */
    public Graphe() {
        this.graphe = new HashMap<>();
    }

    /**
     * Ajoute un nœud au graphe s'il n'existe pas déjà.
     * @param noeud le nœud à ajouter
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        this.graphe.putIfAbsent(noeud, new HashMap<>());
    }

    /**
     * Ajoute une arête pondérée entre deux nœuds.
     * @param depart le nœud de départ de l'arête
     * @param arrivee le nœud d'arrivée de l'arête
     * @param cout le poids de l'arête (coût)
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        this.graphe.get(depart).put(arrivee, cout);
    }

    /**
     * Obtient le coût de l'arête entre deux nœuds.
     * @param depart le nœud de départ de l'arête
     * @param arrivee le nœud d'arrivée de l'arête
     * @return le coût de l'arête s'il existe, sinon -1.0
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return this.graphe.getOrDefault(depart, new HashMap<>()).getOrDefault(arrivee, -1.0);
    }

    /**
     * Obtient une liste de tous les nœuds du graphe.
     * @return une liste de tous les nœuds du graphe
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.graphe.keySet());
    }

    /**
     * Obtient une liste des nœuds voisins d'un nœud donné.
     * @param noeud le nœud pour lequel obtenir les voisins
     * @return une liste des nœuds voisins
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!this.graphe.containsKey(noeud))
            return new ArrayList<>();
        return new ArrayList<>(this.graphe.get(noeud).keySet());
    }

    /**
     * Obtient le nœud correspondant à une position donnée dans le graphe.
     * @param x la coordonnée x de la position
     * @param y la coordonnée y de la position
     * @return le nœud correspondant à la position spécifiée, ou null si aucun nœud ne correspond
     */
    public Noeud<E> getNoeud(int x, int y) {
        for (Noeud<E> noeud : this.getNoeuds()) {
            Case caseActuelle = (Case) noeud.getValeur();
            if (caseActuelle.getX() == x && caseActuelle.getY() == y) {
                return noeud;
            }
        }
        return null;
    }
}
