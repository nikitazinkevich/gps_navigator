package tools;

public class Edge  {

    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public Edge(Vertex source, Vertex destination, int weight) {

        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    Vertex getDestination() {
        return destination;
    }

    Vertex getSource() {
        return source;
    }
    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }


}