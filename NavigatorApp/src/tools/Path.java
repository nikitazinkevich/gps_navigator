package tools;

import java.util.List;

public class Path {


    /**
     * All points of the path in the order we need to visit it.
     */
    private final List<String> path;

    /**
     * Total cost of the path.
     */
    private final int cost;

    public Path(List<String> path, int cost) {
        this.path = path;
        this.cost = cost;
    }

    @Override
    public String toString() {
        String TO_STRING_PATTERN = "tools.Path: %s; of cost %d";
        return String.format(TO_STRING_PATTERN, String.join(" ", path), cost);
    }
}
