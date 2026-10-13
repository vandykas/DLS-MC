import java.util.ArrayList;
import java.util.Random;

public class DLS {
    private final VertexSet improvingNeighbours;
    private final VertexSet levelNeighbours;
    private final VertexSet clique;

    private int[] cliqueDegree;

    private Random rand = new Random();
    private int numOfVertex;
    private ArrayList<Integer>[] adjList;
    private boolean[][] adjMatrix;

    private int numSteps;
    private final int maxSteps;
    private final int penaltyDelay;

    public DLS(int numOfVertex, int maxSteps, int penaltyDelay) {
        this.numSteps = 0;
        this.maxSteps = maxSteps;
        this.penaltyDelay = penaltyDelay;

        this.rand = new Random();
        this.improvingNeighbours = new VertexSet(numOfVertex);
        this.levelNeighbours = new VertexSet(numOfVertex);
        this.clique = new VertexSet(numOfVertex);
    }

    private void dls(int targetCliqueSize) {
        int firstVertex = rand.nextInt(numOfVertex);
        clique.addVertex(firstVertex);

        // TODO: Vertex penalty initialization

        while (numSteps < maxSteps) {
            int lastPicked = expand();
            if (clique.getSetSize() >= targetCliqueSize) {
                // TODO: Return clique
            }
            while (improvingNeighbours.getSetSize() > 0) {
                lastPicked = expand();
                if (clique.getSetSize() >= targetCliqueSize) {
                    // TODO: Return clique
                }
                lastPicked = plateauSearch();
            }

            // TODO: Vertex penalty update

            if (penaltyDelay > 1) {
                clique.clear();
                firstVertex = rand.nextInt(numOfVertex);
                clique.addVertex(firstVertex);
            }
            else {
                int picked = rand.nextInt(numOfVertex);
                for (int i = clique.getSetSize() - 1; i >= 0; i--) {
                    int currentVertex = clique.vertex(i);
                    if (!adjMatrix[currentVertex][picked]) {
                        clique.removeVertex(currentVertex);
                    }
                }
            }
        }
    }

    private int expand() {
        int picked = -1;
        while (improvingNeighbours.getSetSize() > 0) {
            picked = selectMinPenalty();
            clique.addVertex(picked);

            for (int i = improvingNeighbours.getSetSize() - 1; i >= 0; i--) {
                int currentVertex = improvingNeighbours.vertex(i);
                if (!adjMatrix[currentVertex][picked]) {
                    improvingNeighbours.removeVertex(currentVertex);
                }
            }
            numSteps++;
        }
        return picked;
    }

    private int plateauSearch() {
        int initialCliqueSize = clique.getSetSize();
        int picked = -1;
        while (improvingNeighbours.getSetSize() == 0 && levelNeighbours.getSetSize() > 0 && initialCliqueSize > 0) {
            picked = selectMinPenalty();
            clique.addVertex(picked);

            // TODO: Make efficient data structure to store vertex outside of clique that is not adjacent to v in level neighbors set
            int deleted = 0;
            clique.removeVertex(deleted);

            int currentCliqueSize = clique.getSetSize();
            for (int neighbour : adjList[deleted]) {
                cliqueDegree[neighbour]--;
                if (cliqueDegree[neighbour] < currentCliqueSize - 1) {
                    levelNeighbours.removeVertex(neighbour);
                }
            }
            for (int neighbour : adjList[picked]) {
                cliqueDegree[neighbour]++;
                if (cliqueDegree[neighbour] == currentCliqueSize - 1) {
                    levelNeighbours.addVertex(neighbour);
                }
                else if (cliqueDegree[neighbour] == currentCliqueSize) {
                    improvingNeighbours.addVertex(neighbour);
                }
            }
            initialCliqueSize--;
            numSteps++;
        }
        return picked;
    }

    // TODO: Make efficient algorithm to pick vertex with minimum penalty uniformly at random
    private int selectMinPenalty() {
        return -1;
    }
}
