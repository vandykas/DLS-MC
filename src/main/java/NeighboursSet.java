public class NeighboursSet {
    private int setSize;
    private int[] neigboursSet;
    private int[] neigboursKey;

    public NeighboursSet(int setSize) {
        this.setSize = setSize;
        neigboursSet = new int[setSize];
        neigboursKey = new int[setSize];
    }

    public int getSetSize() {
        return setSize;
    }

    private boolean isValidVertex(int vertex) {
        return vertex >= 0 && vertex < setSize;
    }

    public boolean setKey(int vertexNum, int newKey) {
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

    public boolean addVertex(int vertexNum) {
        if (isValidVertex(vertexNum)) {
            neigboursSet[setSize] = vertexNum;
            setSize++;
        }
        return false;
    }

    public int removeVertex(int vertexNum) {
        if (isValidVertex(vertexNum)) {
            int vertexKey = neigboursKey[vertexNum];
            neigboursSet[vertexKey] = neigboursSet[setSize - 1];
            setSize--;
        }
        return -1;
    }
}
