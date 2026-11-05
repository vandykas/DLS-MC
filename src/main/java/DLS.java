import java.util.Random;

public class DLS {
    private final VertexSet improvingNeighbours;
    private final VertexSet levelNeighbours;
    private final VertexSet clique;

    private int[] cliqueDegree;
    private final int[] penalty;

    private final Random rand;
    private final Graph graph;

    private int numSteps;
    private final int maxSteps;
    private final int pd;

    public DLS(int maxSteps, int pd, Graph graph) {
        this.numSteps = 0;
        this.maxSteps = maxSteps;
        this.pd = pd;

        this.penalty = new int[graph.getNumOfVertex()];

        this.rand = new Random();
        this.graph = graph;
        this.improvingNeighbours = new VertexSet(graph.getNumOfVertex());
        this.levelNeighbours = new VertexSet(graph.getNumOfVertex());
        this.clique = new VertexSet(graph.getNumOfVertex());
    }

    private VertexSet run(int targetCliqueSize) {
        VertexSet bestClique = new VertexSet(graph.getNumOfVertex());
        int firstVertex = rand.nextInt(graph.getNumOfVertex());
        clique.addVertex(firstVertex);

        while (numSteps < maxSteps) {
            int v = expand();
            if (clique.getSize() >= targetCliqueSize) {
                return clique;
            }
            else if (clique.getSize() > bestClique.getSize()) {
                bestClique = clique;
            }

            while (improvingNeighbours.getSize() > 0) {
                v = expand();
                bestClique = clique;
                if (clique.getSize() >= targetCliqueSize) {
                    return clique;
                }
                else if (clique.getSize() > bestClique.getSize()) {
                    bestClique = clique;
                }
                v = plateauSearch();
            }

            updatePenalty();
            if (pd > 1) {
                clique.clear();
                clique.addVertex(v);
            }
            else {
                v = rand.nextInt(graph.getNumOfVertex());
                for (int i = clique.getSize() - 1; i >= 0; i--) {
                    int u = clique.vertex(i);
                    if (!graph.isAdjacent(u, v)) {
                        clique.removeVertex(u);
                    }
                }
            }
        }
        return bestClique;
    }

    private int expand() {
        int picked = -1;
        while (improvingNeighbours.getSize() > 0) {
            picked = selectMinPenalty(improvingNeighbours);
            clique.addVertex(picked);

            for (int i = improvingNeighbours.getSize() - 1; i >= 0; i--) {
                int currentVertex = improvingNeighbours.vertex(i);
                if (!graph.isAdjacent(currentVertex, picked)) {
                    improvingNeighbours.removeVertex(currentVertex);
                }
            }
            numSteps++;
        }
        return picked;
    }

    private int plateauSearch() {
        int initialCliqueSize = clique.getSize();
        int picked = -1;
        while (improvingNeighbours.getSize() == 0 && levelNeighbours.getSize() > 0 && initialCliqueSize > 0) {
            picked = selectMinPenalty(levelNeighbours);
            clique.addVertex(picked);

            // TODO: Make efficient data structure to store vertex outside of clique that is not adjacent to v in level neighbors set
            int deleted = 0;
            clique.removeVertex(deleted);

            int currentCliqueSize = clique.getSize();
            for (int neighbour : graph.getAdjList(deleted)) {
                cliqueDegree[neighbour]--;
                if (cliqueDegree[neighbour] < currentCliqueSize - 1) {
                    levelNeighbours.removeVertex(neighbour);
                }
            }
            for (int neighbour : graph.getAdjList(picked)) {
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
    private int selectMinPenalty(VertexSet neighboursSet) {
        return -1;
    }

    private void updatePenalty() {
        for (int i = 0; i < clique.getSize(); i++) {
            penalty[clique.vertex(i)]++;
        }
    }
}
