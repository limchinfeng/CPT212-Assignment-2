import java.util.*;

class shortestpath {
    private LinkedList<Integer> adjLists[];
    private boolean visited[];
    private boolean found; // Declare the found variable

    // Graph creation
    shortestpath(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];
        found = false; // Initialize the found variable to false

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    // Add edges
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    // DFS algorithm
    List<Integer> DFS(int vertex, int destination) {
        if (vertex == destination) {
            List<Integer> path = new ArrayList<>();
            path.add(vertex);
            return path; // Reached the destination, return path with only the current vertex
        }
        visited[vertex] = true;

        Iterator<Integer> ite = adjLists[vertex].listIterator();

        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj]) {
                List<Integer> path = DFS(adj, destination);
                if (path != null) {
                    path.add(0, vertex); // Add the current vertex to the beginning of the path
                    return path;
                }
            }
        }

        visited[vertex] = false; // Reset visited flag for backtracking

        return null; // No path found from current vertex to the destination
    }

    public static void main(String args[]) {
        shortestpath g = new shortestpath(9);

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

        List<Integer> shortestPath = g.DFS(0, destination);

        if (shortestPath == null) {
            System.out.println("No path found to the destination vertex.");
        } else {
            System.out.println("Shortest path to the destination vertex:");
            for (int vertex : shortestPath) {
                System.out.print(vertex + " ");
            }
        }
    }

}