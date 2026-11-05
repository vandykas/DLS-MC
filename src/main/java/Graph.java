import java.util.ArrayList;

public class Graph {
    private final int capacity;
    private final int[][] matrix;
    private final ArrayList<Integer>[] list;

    public Graph(int capacity) {
        this.capacity = capacity;
        this.matrix = new int[capacity][capacity];
        this.list = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            this.list[i] = new ArrayList<>(); 
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Integer> getNeighbours(int u) {
        return list[u];
    }

    public void addEdge(int u, int v) {
        if (!isAdjacent(u, v)) {
            matrix[u][v] = 1;
            matrix[v][u] = 1;

            list[u].add(v);
            list[v].add(u);
        }
    }

    public boolean isAdjacent(int u, int v) {
        return matrix[u][v] == 1;
    }
}
