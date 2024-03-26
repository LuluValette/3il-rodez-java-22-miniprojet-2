package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Noeud représente un nœud dans une structure de graphe.
 *
 * @param <E> Le type de valeur associé à ce nœud.
 */
public class Noeud<E> {
    private final E valeur; // La valeur associée à ce nœud
    private final List<Noeud<E>> voisins; // La liste des nœuds voisins (connectés) à ce nœud

    /**
     * Construit un nouveau nœud avec la valeur spécifiée.
     *
     * @param valeur La valeur associée à ce nœud.
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur associée à ce nœud.
     *
     * @return La valeur associée à ce nœud.
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Récupère la liste des nœuds voisins (connectés) à ce nœud.
     *
     * @return La liste des nœuds voisins.
     */
    public List<Noeud<E>> getVoisins() {
        return this.voisins;
    }

    /**
     * Ajoute un nœud voisin à ce nœud.
     *
     * @param voisin Le nœud voisin à ajouter.
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
