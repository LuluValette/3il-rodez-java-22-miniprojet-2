package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un nœud dans un graphe.
 * @param <E> le type de données stockées dans le nœud
 */
public class Noeud<E> {
    private final E valeur;
    private final List<Noeud<E>> voisins;

    /**
     * Crée un nouveau nœud avec une valeur spécifiée.
     * @param valeur la valeur associée au nœud
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur associée au nœud.
     * @return la valeur du nœud
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Récupère la liste des voisins du nœud.
     * @return la liste des voisins du nœud
     */
    public List<Noeud<E>> getVoisins() {
        return this.voisins;
    }

    /**
     * Ajoute un voisin au nœud.
     * @param voisin le nœud voisin à ajouter
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
