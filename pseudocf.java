import java.util.*;

class pseudocf {
    private LinkedList<Integer> adjLists[];
    private int num[];
    private List<Integer> path;

    // Graph creation
    pseudocf(int vertices) {
        adjLists = new LinkedList[vertices];
        num = new int[vertices];
        path = new ArrayList<>();

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
        path = new ArrayList<>();

        DFS(vertex, destination);

        if (!path.isEmpty()) {
            System.out.println("Shortest path to the destination vertex:");
            for (int j = 0; j < path.size(); j++) {
                System.out.print(path.get(j));
                if (j != path.size() - 1)
                    System.out.print(" -> ");
            }
            System.out.println();
        } else {
            System.out.println("No path found to the destination vertex.");
        }
    }

    // DFS algorithm
    boolean DFS(int vertex, int destination) {
        num[vertex] = 1;

        if (vertex == destination) {
            path.add(vertex);
            return true;
        }

        Iterator<Integer> ite = adjLists[vertex].listIterator();

        while (ite.hasNext()) {
            int adj = ite.next();
            if (num[adj] == 0) {
                if (DFS(adj, destination)) {
                    path.add(0, vertex);
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String args[]) {
        pseudocf g = new pseudocf(11);

        g.addEdge(0, 1);
        // g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 6);
        g.addEdge(2, 3);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(2, 9);
        g.addEdge(8, 9);
        g.addEdge(9, 10);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        

        System.out.println("Following is Depth First Traversal");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter destination vertex: ");
        int destination = scanner.nextInt();

        g.findPathDFS(0, destination);
    }
}
