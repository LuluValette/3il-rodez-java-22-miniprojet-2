package fr.ecole3il.rodez2023.carte.elements;

import java.util.ArrayList;
import java.util.List;

public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;

    public Noeud(E valeur){
        this.valeur=valeur;
        this.voisins = new ArrayList();
    }

    public E getValeur() {
        return valeur;
    }
    public List<Noeud<E>> getVoisins(){
        return this.voisins;
    }
    public void ajouterVoisin(Noeud<E> voisin){
        this.voisins.add(voisin);
    }
}
