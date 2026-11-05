import java.util.ArrayList;

public class Graph {
    private final int numOfVertex;
    private final int[][] matrix;
    private ArrayList<Integer>[] list;

    public Graph(int numOfVertex) {
        this.numOfVertex = numOfVertex;
        matrix = new int[numOfVertex][numOfVertex];
        list = new ArrayList[numOfVertex];
    }

    public int getNumOfVertex() {
        return numOfVertex;
    }

    public ArrayList<Integer> getAdjList(int u) {
        return this.list[u];
    }

    public void addEdge(int u, int v) {
        matrix[u][v] = 1;
        matrix[v][u] = 1;

        list[u].add(v);
        list[v].add(u);
    }

    public boolean isAdjacent(int u, int v) {
        return matrix[u][v] == 1;
    }
}
