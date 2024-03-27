package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> estimatedCost = new HashMap<>();
        Map<Noeud<E>, Double> actualCost = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> previous = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            estimatedCost.put(noeud, Double.POSITIVE_INFINITY);
            actualCost.put(noeud, Double.POSITIVE_INFINITY);
            previous.put(noeud, null);
        }
        estimatedCost.put(depart, 0.0);
        actualCost.put(depart, 0.0);

        PriorityQueue<Noeud<E>> priorityQueue = new PriorityQueue<>((n1, n2) -> (int) (estimatedCost.get(n1) - estimatedCost.get(n2)));
        priorityQueue.add(depart);

        while (!priorityQueue.isEmpty()) {
            Noeud<E> noeud = priorityQueue.poll();
            if (noeud.equals(arrivee)) break;

            for (Noeud<E> neighbor : graphe.getVoisins(noeud)) {
                double cost = actualCost.get(noeud) + graphe.getCoutArete(noeud, neighbor);
                if (cost < actualCost.get(neighbor)) {
                    previous.put(neighbor, noeud);
                    actualCost.put(neighbor, cost);
                    estimatedCost.put(neighbor, cost);
                    priorityQueue.add(neighbor);
                }
            }
        }

        List<Noeud<E>> path = new LinkedList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            path.add(noeud);
            noeud = previous.get(noeud);
        }
        Collections.reverse(path);

        return path;
    }
}