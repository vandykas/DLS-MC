public class VertexSet {
    private int numOfVertex;
    private int setSize;
    private int[] neigboursSet;
    private int[] neigboursKey;

    public VertexSet(int numOfVertex) {
        this.setSize = 0;
        this.numOfVertex = numOfVertex;
        this.neigboursSet = new int[numOfVertex];
        this.neigboursKey = new int[numOfVertex];
    }

    public int getSetSize() {
        return setSize;
    }

    private boolean isValidVertex(int vertex) {
        return vertex >= 0 && vertex < this.numOfVertex;
    }

    private boolean isValidKey(int key) {
        return key >= 0 && key < setSize;
    }

    private boolean setKey(int vertexNum, int newKey) {
        if (isValidVertex(vertexNum)) {
            neigboursKey[vertexNum] = newKey;
            return true;
        }
        return false;
    }

    public int key(int vertexNum) {
        if (isValidVertex(vertexNum)) {
            return neigboursKey[vertexNum];
        }
        return -1;
    }

    public int vertex(int key) {
        if (isValidKey(key)) {
            return neigboursSet[key];
        }
        return -1;
    }

    public boolean addVertex(int vertexNum) {
        if (isValidVertex(vertexNum) && setSize < numOfVertex && neigboursKey[vertexNum] == -1) {
            neigboursSet[setSize] = vertexNum;
            setSize++;
            return true;
        }
        return false;
    }

    public int removeVertex(int removedVertex) {
        if (isValidVertex(removedVertex) && neigboursKey[removedVertex] != -1) {
            int removedVertexKey = key(removedVertex);
            int addedVertex = neigboursSet[setSize - 1];

            neigboursSet[removedVertexKey] = addedVertex;

            setKey(addedVertex, removedVertexKey);
            setKey(removedVertex, -1);

            setSize--;
        }
        return -1;
    }

    public void clear() {
        setSize = 0;
    }
}
