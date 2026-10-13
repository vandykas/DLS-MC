import java.util.ArrayList;

public class DLS {
    private VertexSet improvingNeighbours;
    private VertexSet levelNeighbours;

    private int cliqueSize;
    private int[] clique;
    private int[] cliqueKey;

    private ArrayList<Integer>[] adjList;
    private boolean[][] adjMatrix;

    private void dls() {

    }

    private void expand() {
        while (improvingNeighbours.getSetSize() > 0) {
            int picked = selectMinPenalty();
            clique[cliqueSize++] = picked;

            for (int i = improvingNeighbours.getSetSize() - 1; i >= 0; i--) {
                int currentVertex = improvingNeighbours.vertex(i);
                if (!adjMatrix[currentVertex][picked]) {
                    improvingNeighbours.removeVertex(currentVertex);
                }
            }
        }
    }

    private void plateauSearch() {
        int initialCliqueSize = cliqueSize;
        while (improvingNeighbours.getSetSize() == 0 && levelNeighbours.getSetSize() > 0 && initialCliqueSize > 0) {
            int picked = selectMinPenalty();
            clique[cliqueSize++] = picked;

            int deleted = 0;
            for (int i = levelNeighbours.getSetSize() - 1; i >= 0; i--) {
                int currentVertex = levelNeighbours.vertex(i);
                if (adjMatrix[currentVertex][deleted] && !adjMatrix[currentVertex][picked]) {
                    levelNeighbours.removeVertex(currentVertex);
                }
                else if (!adjMatrix[currentVertex][deleted] && adjMatrix[currentVertex][picked]) {
                    improvingNeighbours.addVertex(currentVertex);
                }
            }
            initialCliqueSize--;
        }
    }

    private int selectMinPenalty() {
        return -1;
    }
}
