package fr.ecole3il.rodez2023.carte.elements;
import java.util.ArrayList;
import java.util.List;

public class Graphe<E> {
    private List<Noeud<E>> noeuds;
    private List<List<Double>> matriceAdjacence;

    public Graphe() {
        this.noeuds = new ArrayList<>();
        this.matriceAdjacence = new ArrayList<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            // Ajouter une nouvelle ligne à la matrice d'adjacence
            List<Double> nouvelleLigne = new ArrayList<>();
            for (int i = 0; i < matriceAdjacence.size(); i++) {
                matriceAdjacence.get(i).add(null); // Ajouter une colonne vide pour le nouveau nœud
                nouvelleLigne.add(null); // Ajouter une colonne vide à la nouvelle ligne
            }
            matriceAdjacence.add(nouvelleLigne);
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        int indexDepart = noeuds.indexOf(depart);
        int indexArrivee = noeuds.indexOf(arrivee);
        matriceAdjacence.get(indexDepart).set(indexArrivee, cout);
        // Si le graphe est non orienté, décommentez la ligne suivante pour rendre symétrique la matrice d'adjacence
        // matriceAdjacence.get(indexArrivee).set(indexDepart, cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        int indexDepart = noeuds.indexOf(depart);
        int indexArrivee = noeuds.indexOf(arrivee);
        return matriceAdjacence.get(indexDepart).get(indexArrivee);
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(noeuds);
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        List<Noeud<E>> voisins = new ArrayList<>();
        int indexNoeud = noeuds.indexOf(noeud);
        if (indexNoeud != -1) {
            List<Double> ligne = matriceAdjacence.get(indexNoeud);
            for (int i = 0; i < ligne.size(); i++) {
                if (ligne.get(i) != null) {
                    voisins.add(noeuds.get(i));
                }
            }
        }
        return voisins;
    }
}
