package app;

import tools.*;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class NavigatorApp {
    private static List<Vertex> vertexes = new ArrayList<>();
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {

        try {
            final GpsNavigator navigator = new StubNavigator();

            navigator.readData("input_data");  //Введите сюда путь к файлу


            final Path path = navigator.findPath("A", "B"); // А сюда  начальную и конечную точки
            System.out.println(path);

        } catch (Exception e) {System.out.println("exception");}

    }



    private static class StubNavigator implements GpsNavigator {

        @Override
        public void readData(String filePath) throws IOException {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                while (reader.ready()) {
                    String[] s = reader.readLine().split(" ");
                    vertexes.add(new Vertex(s[0], s[0]));
                    vertexes.add(new Vertex((s[1]), s[1]));
                    edges.add(new Edge(new Vertex(s[0], s[0]), new Vertex(s[1], s[1]),
                            Integer.parseInt(s[2]) * Integer.parseInt(s[3])));

                }

            }

        }

        @Override
        public Path findPath(String pointA, String pointB) {
            Vertex source = null;
            Vertex target = null;
            Graph graph = new Graph(vertexes, edges);
            Algorithm algorithm = new Algorithm(graph);
            for (Vertex vertex : graph.getVertexes()) {
                if (vertex.getName().equals(pointA)) {
                    source = vertex;
                }
                if (vertex.getName().equals(pointB)) {
                    target = vertex;
                }
            }
            algorithm.execute(source);
            return new Path(algorithm.getPath(target), Algorithm.getTotalDistance());


        }
    }

}
