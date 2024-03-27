package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> costs = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> previous = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            costs.put(noeud, Double.POSITIVE_INFINITY);
            previous.put(noeud, null);
        }
        costs.put(depart, 0.0);

        PriorityQueue<Noeud<E>> priorityQueue = new PriorityQueue<>((n1, n2) -> (int) (costs.get(n1) - costs.get(n2)));
        priorityQueue.add(depart);

        while (!priorityQueue.isEmpty()) {
            Noeud<E> noeud = priorityQueue.poll();
            if (noeud.equals(arrivee)) break;

            for (Noeud<E> neighbor : graphe.getVoisins(noeud)) {
                double cost = costs.get(noeud) + graphe.getCoutArete(noeud, neighbor);
                if (cost < costs.get(neighbor)) {
                    costs.put(neighbor, cost);
                    previous.put(neighbor, noeud);
                    priorityQueue.add(neighbor);
                }
            }
        }

        List<Noeud<E>> shortestPath = new LinkedList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            shortestPath.add(0, noeud);
            noeud = previous.get(noeud);
        }
        Collections.reverse(shortestPath);

        return shortestPath;
    }
}
