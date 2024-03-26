package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E>{
    private HashMap<Noeud<E>, Double> ListeNoeuds;
    private HashMap<Noeud<E>, Double> ListeNoeudsExplores;

    public AlgorithmeAEtoile(){
        ListeNoeuds = new HashMap<>();
        ListeNoeudsExplores = new HashMap<>();
    }
    @Override
    public List<Noeud> trouverChemin(Graphe graphe, Noeud depart, Noeud arrivee) {
        return null;
    }
}