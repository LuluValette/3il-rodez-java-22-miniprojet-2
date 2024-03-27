package fr.ecole3il.rodez2023.carte.chemin.elements;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E> {
    private Map<Noeud<E>, Map<Noeud<E>, Double>> graphe;
    public Graphe() {
        this.graphe = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        this.graphe.putIfAbsent(noeud, new HashMap<>());
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        this.graphe.get(depart).put(arrivee,cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return this.graphe.getOrDefault(depart, new HashMap<>()).getOrDefault(arrivee, -1.0);
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.graphe.keySet());
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!this.graphe.containsKey(noeud))
            return new ArrayList<>();
        return new ArrayList<>(this.graphe.get(noeud).keySet());
    }
}
