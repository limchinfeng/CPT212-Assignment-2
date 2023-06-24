import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class pseudofile {
    private LinkedList<Integer> adjLists[];
    private int num[];
    private List<Integer> path;

    // Graph creation
    pseudofile(int vertices) {
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
        Scanner scanner = new Scanner(System.in);

        try {
            File file = new File("vertex.txt");
            Scanner fileScanner = new Scanner(file);

            int numVertices = fileScanner.nextInt();
            pseudofile g = new pseudofile(numVertices);

            while (fileScanner.hasNextInt()) {
                int src = fileScanner.nextInt();
                int dest = fileScanner.nextInt();
                g.addEdge(src, dest);
            }

            System.out.println("Following is Depth First Traversal");

            System.out.print("Enter destination vertex: ");
            int destination = scanner.nextInt();

            g.findPathDFS(0, destination);

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}