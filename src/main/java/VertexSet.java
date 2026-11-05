public class VertexSet {
    private final int numOfVertex;
    private int currentSize;
    private final int[] set;
    private final int[] key;

    public VertexSet(int numOfVertex) {
        this.currentSize = 0;
        this.numOfVertex = numOfVertex;
        this.set = new int[numOfVertex];
        this.key = new int[numOfVertex];
    }

    public int getSize() {
        return currentSize;
    }

    private boolean isValidVertex(int vertex) {
        return vertex >= 0 && vertex < this.numOfVertex;
    }

    private boolean isValidKey(int key) {
        return key >= 0 && key < currentSize;
    }

    private boolean setKey(int vertexNum, int newKey) {
        if (isValidVertex(vertexNum)) {
            key[vertexNum] = newKey;
            return true;
        }
        return false;
    }

    public int key(int vertexNum) {
        if (isValidVertex(vertexNum)) {
            return key[vertexNum];
        }
        return -1;
    }

    public int vertex(int key) {
        if (isValidKey(key)) {
            return set[key];
        }
        return -1;
    }

    public boolean addVertex(int vertexNum) {
        if (isValidVertex(vertexNum) && currentSize < numOfVertex && key[vertexNum] == -1) {
            set[currentSize] = vertexNum;
            currentSize++;
            return true;
        }
        return false;
    }

    public int removeVertex(int removedVertex) {
        if (isValidVertex(removedVertex) && key[removedVertex] != -1) {
            int removedVertexKey = key(removedVertex);
            int addedVertex = set[currentSize - 1];

            set[removedVertexKey] = addedVertex;

            setKey(addedVertex, removedVertexKey);
            setKey(removedVertex, -1);

            currentSize--;
        }
        return -1;
    }

    public void clear() {
        for (int i = 0; i < numOfVertex; i++) {
            key[i] = -1;
        }
        currentSize = 0;
    }
}
