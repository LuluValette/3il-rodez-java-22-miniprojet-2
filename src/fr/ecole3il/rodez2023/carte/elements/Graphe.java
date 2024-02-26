package fr.ecole3il.rodez2023.carte.elements;

public class Graphe {
    public Graphe(){}
    public void ajouterNoeud(Noeud<E> noeud){
        
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout){}

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee){}

    public List<Noeud<E>> getNoeuds(){}

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {}
}