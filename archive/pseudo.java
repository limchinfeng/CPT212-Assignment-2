import java.util.*;

class pseudo {
    private LinkedList<Integer> adjLists[];
    private boolean visited[];
    private boolean found;

    pseudo(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];
        found = false;

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    List<Integer> DFS(int vertex, int destination) {
        if (vertex == destination) {
            List<Integer> path = new ArrayList<>();
            path.add(vertex);
            return path;
        }
        visited[vertex] = true;

        Iterator<Integer> ite = adjLists[vertex].listIterator();

        while (ite.hasNext()) {
            int adj = ite.next();
            if (!visited[adj]) {
                List<Integer> path = DFS(adj, destination);
                if (path != null) {
                    path.add(0, vertex);
                    return path;
                }
            }
        }

        visited[vertex] = false;

        return null;
    }

    boolean hasUnnumberedVertex(int[] num) {
        for (int n : num) {
            if (n == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        pseudo g = new pseudo(9);

        g.addEdge(0, 1);
        // g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 7);
        g.addEdge(2, 3);
        g.addEdge(6, 7);
        g.addEdge(7, 8);

        System.out.println("Following is Depth First Traversal");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter destination vertex: ");
        int destination = scanner.nextInt();

        int numVertices = g.adjLists.length;
        int[] num = new int[numVertices];
        List<Integer> edges = new ArrayList<>();
        int i = 0;

        while (!g.found || g.hasUnnumberedVertex(num)) {
            g.DFSHelper(0, destination, num, edges, i);
        }

        if (g.found) {
            System.out.println("Shortest path to the destination vertex:");
            for (int vertex : edges) {
                System.out.print(vertex + " ");
            }
        } else {
            System.out.println("No path found to the destination vertex.");
        }
    }

    void DFSHelper(int v, int d, int[] num, List<Integer> edges, int i) {
        num[v] = i++;

        for (int u : adjLists[v]) {
            if (num[u] == 0 && v != d) {
                edges.add(u);
                DFSHelper(u, d, num, edges, i);
            }
        }

        if (v == d) {
            found = true;
        }
    }
}
