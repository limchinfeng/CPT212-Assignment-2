import java.util.*;

class Graph {
    private LinkedList<Integer> adjLists[];
    private int num[];
    private List<Integer> edges;

    // Graph creation
    Graph(int vertices) {
        adjLists = new LinkedList[vertices];
        num = new int[vertices];
        edges = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjLists[i] = new LinkedList<>();
            num[i] = 0;
        }
    }

    // Add edges
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    // DFS algorithm
    void DFS(int vertex, int destination) {
        num[vertex] = edges.size() + 1;
        for (int adj : adjLists[vertex]) {
            if (num[adj] == 0 && vertex != destination) {
                edges.add(adj);
                DFS(adj, destination);
            }
        }
    }

    void FindPathDFS(int source, int destination) {
        for (int v = 0; v < adjLists.length; v++) {
            num[v] = 0;
        }
        edges.clear();
        int i = 0;

        while (source != destination || Arrays.stream(num).anyMatch(num -> num == 0)) {
            DFS(source, destination);
        }

        if (source == destination) {
            System.out.println("Path from " + source + " to " + destination + ": " + edges);
        } else {
            System.out.println("No path from " + source + " to " + destination);
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 0);
        g.addEdge(1, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 3);
        g.addEdge(5, 3);

        System.out.println("Following is Depth First Traversal:");
        g.FindPathDFS(0, 5);
    }
}
