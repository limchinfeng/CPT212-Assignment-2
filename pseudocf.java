import java.util.*;

class pseudocf {
    private LinkedList<Integer> adjLists[];
    private int num[];
    private List<Integer> edges;
    private int i;

    // Graph creation
    pseudocf(int vertices) {
        adjLists = new LinkedList[vertices];
        num = new int[vertices];
        edges = new ArrayList<>();
        i = 0;

        for (int j = 0; j < vertices; j++)
            adjLists[j] = new LinkedList<Integer>();
    }

    // Add edges
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    // Find path using DFS
    void findPathDFS(int vertex, int destination) {
        for (int j = 0; j < adjLists.length; j++)
            num[j] = 0;
        edges = new ArrayList<>();
        i = 0;

        DFS(vertex, destination);

        if (num[destination] != 0) {
            System.out.println("Shortest path to the destination vertex:");
            for (int j = 0; j < edges.size(); j += 2) {
                int src = edges.get(j);
                int dest = edges.get(j + 1);
                System.out.println(src + " -> " + dest);
            }
        } else {
            System.out.println("No path found to the destination vertex.");
        }
    }

    // DFS algorithm
    void DFS(int vertex, int destination) {
        num[vertex] = i++;

        Iterator<Integer> ite = adjLists[vertex].listIterator();

        while (ite.hasNext()) {
            int adj = ite.next();
            if (num[adj] == 0) {
                edges.add(vertex);
                edges.add(adj);
                if (adj == destination) {
                    num[destination] = i++; // Update destination vertex's num value
                    return;
                }
                DFS(adj, destination);
            }
        }
    }

    public static void main(String args[]) {
        pseudocf g = new pseudocf(9);

        g.addEdge(0, 1);
        // g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 6);
        g.addEdge(2, 3);
        g.addEdge(6, 7);
        g.addEdge(7, 8);

        System.out.println("Following is Depth First Traversal");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter destination vertex: ");
        int destination = scanner.nextInt();

        g.findPathDFS(0, destination);
    }
}
