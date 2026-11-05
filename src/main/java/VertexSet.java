public class VertexSet {
    private int size;
    private final int capacity;
    private final int[] set;
    private final int[] keys;
    private final int[] timestamps;
    private int currentTimestamp;

    public VertexSet(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        this.set = new int[capacity];
        this.keys = new int[capacity];

        this.timestamps = new int[capacity];
        this.currentTimestamp = 1;
    }

    public int getSize() {
        return size;
    }

    private void setKey(int vertex, int key) {
        keys[vertex] = key;
        timestamps[vertex] = currentTimestamp;
    }

    public int key(int vertex) {
        if (timestamps[vertex] == currentTimestamp) {
            return keys[vertex];
        }
        return -1;
    }

    public int vertex(int key) {
        if (key < size) {
            return set[key];
        }
        return -1;
    }

    public boolean addVertex(int vertex) {
        if (size < capacity && key(vertex) == -1) {
            set[size] = vertex;
            setKey(vertex, size);
            size++;

            return true;
        }
        return false;
    }

    public boolean removeVertex(int vertex) {
        int vertexKey = key(vertex);
        if (vertexKey != -1) {
            int lastVertex = vertex(size - 1);
            set[vertexKey] = lastVertex;

            setKey(lastVertex, vertexKey);
            setKey(vertex, -1);
            size--;

            return true;
        }
        return false;
    }

    public void clear() {
        currentTimestamp++;
        size = 0;
    }
}
