package tools;


import java.io.IOException;

public interface GpsNavigator {

    void readData(String filePath) throws IOException;

    Path findPath(String pointA, String pointB);
}
