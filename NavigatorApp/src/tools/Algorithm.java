package tools;

import java.util.*;

public class Algorithm {
    private final List<Edge> edges;
    private Set<Vertex> visitedVertices;
    private Set<Vertex> unVisitedVertices;
    private Map<Vertex, Vertex> descendants;
    private Map<Vertex, Integer> distance;
    private static int totalDistance;

    public Algorithm(Graph graph) {
        this.edges = new ArrayList<>(graph.getEdges());

    }

    public void execute(Vertex source) {
        visitedVertices = new HashSet<>();
        unVisitedVertices = new HashSet<>();
        distance = new HashMap<>();
        descendants = new HashMap<>();

        distance.put(source, 0);
        unVisitedVertices.add(source);
        while (unVisitedVertices.size() > 0) {
            Vertex node = getMinimum(unVisitedVertices);
            visitedVertices.add(node);
            unVisitedVertices.remove(node);
            findMinDistance(node);
        }

    }

    private void findMinDistance(Vertex node) {
            List<Vertex> adjacentNodes = getNeighbors(node);
            for (Vertex target : adjacentNodes) {
                if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                    distance.put(target, getShortestDistance(node)
                            + getDistance(node, target));
                    descendants.put(target, node);
                    unVisitedVertices.add(target);
                }

            }

    }

    private int getDistance(Vertex node, Vertex target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("exception");
    }

    private boolean isSettled(Vertex vertex) {
        return visitedVertices.contains(vertex);
    }

    private List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private Vertex getMinimum(Set<Vertex> unVisitedVertices) {
        Vertex minimum = null;
        for (Vertex vertex : unVisitedVertices) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return  minimum;
    }

    private int getShortestDistance(Vertex destination) {
        Integer dist = distance.get(destination);
        return Objects.requireNonNullElse(dist, Integer.MAX_VALUE);
        
    }

    public LinkedList<String> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<>();
        Vertex step = target;
        Vertex prevStep;
        if (descendants.get(step) == null) {
            return null;
        }
        path.add(step);
        while (descendants.get(step) != null) {
            prevStep = step;
            step = descendants.get(step);
            for(Edge e: edges)
                if (e.getDestination().equals(prevStep) && e.getSource().equals(step)) {

                    totalDistance += e.getWeight();
                }
            path.add(step);
        }
        Collections.reverse(path);
        LinkedList <String> paths = new LinkedList<>();
        for(Vertex v: path){
            paths.add(v.getName());
        }
        return paths;
    }

    public static int getTotalDistance() {
        return totalDistance;
    }

}
