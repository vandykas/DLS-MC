import java.util.ArrayList;

public class DLS {
    private int improvingNeighboursSize;
    private int[] improvingNeighbours;
    private int[] improvingNeighboursKey;


    private int levelNeighboursSize;
    private int[] levelNeighbours;
    private int[] levelNeighboursKey;

    private int cliqueSize;
    private int[] clique;
    private int[] cliqueKey;

    private ArrayList<Integer>[] adjList;
    private boolean[][] adjMatrix;

    private void dls() {

    }

    private void expand() {
        while (improvingNeighboursSize > 0) {
            int picked = selectMinPenalty();
            clique[cliqueSize++] = picked;

            int lastKey = improvingNeighboursSize - 1;
            for (int i = improvingNeighboursSize - 1; i >= 0; i--) {
                int currentVertex = improvingNeighbours[i];
                if (!adjMatrix[currentVertex][picked]) {
                    int addedVertex = improvingNeighbours[lastKey];
                    improvingNeighbours[i] = addedVertex;

                    improvingNeighboursKey[addedVertex] = i;
                    improvingNeighboursKey[currentVertex] = -1;

                    lastKey--;
                }
            }
            improvingNeighboursSize = lastKey + 1;
        }
    }

    private void plateuSearch() {
        int initialCliqueSize = cliqueSize;
        while (improvingNeighboursSize == 0 && levelNeighboursSize > 0 && initialCliqueSize > 0) {
            int picked = selectMinPenalty();
            clique[cliqueSize++] = picked;
        }
    }

    private int selectMinPenalty() {
        return -1;
    }
}
