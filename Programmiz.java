import java.util.*;

class Programmiz {
    private LinkedList<Integer> adjLists[];
    private boolean visited[];
    private boolean found; // Declare the found variable

    // Graph creation
    Programmiz(int vertices) {
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
    void DFS(int vertex, int destination) {
        if (vertex == destination) {
            System.out.println(vertex);
            found = true;
            return;
        }
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> ite = adjLists[vertex].listIterator();
        while (ite.hasNext() && !found) {
            int adj = ite.next();
            if (!visited[adj])
                DFS(adj, destination);
        }
    }

    public static void main(String args[]) {
        Programmiz g = new Programmiz(9);

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

        g.DFS(0, destination);
    }
}