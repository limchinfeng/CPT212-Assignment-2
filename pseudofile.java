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

    // Get all vertices
    List<Integer> getVertices() {
        List<Integer> vertices = new ArrayList<>();

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            if (!adjLists[vertex].isEmpty()) {
                vertices.add(vertex);
            }
        }

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            for (int i = 0; i < adjLists.length; i++) {
                if (adjLists[i].contains(vertex)) {
                    vertices.add(vertex);
                }
            }
        }
        return vertices;
    }

    // Show all vertex
    void showVertices() {
        System.out.println("Vertices with edges:");

        Set<Integer> verticesWithEdges = new HashSet<>();

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            if (!adjLists[vertex].isEmpty()) {
                verticesWithEdges.add(vertex);
            }
        }

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            for (int i = 0; i < adjLists.length; i++) {
                if (adjLists[i].contains(vertex)) {
                    verticesWithEdges.add(vertex);
                }
            }
        }

        for (int vertex : verticesWithEdges) {
            System.out.print(vertex + " ");
        }
        System.out.println("");
    }

    // Show all edges
    void showEdges() {
        System.out.println("All edges:");

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            for (int neighbor : adjLists[vertex]) {
                System.out.println(vertex + " -> " + neighbor);
            }
        }
    }

    // Find path using DFS
    void findPathDFS(int vertex, int destination) {
        for (int j = 0; j < adjLists.length; j++)
            num[j] = 0;
        path = new ArrayList<>();

        DFS(vertex, destination);

        if (!path.isEmpty()) {
            System.out.println("\nShortest path to the destination vertex:");
            for (int j = 0; j < path.size(); j++) {
                System.out.print(path.get(j));
                if (j != path.size() - 1)
                    System.out.print(" -> ");
            }
            System.out.println();
        } else {
            System.out.println("\nNo path found to the destination vertex.");
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

    static int ActionOption(Scanner scanner, pseudofile g) {
        int option = 0;

        System.out.println("\nSelect an option:");
        System.out.println("1. Print available vertices");
        System.out.println("2. Print all edges");
        System.out.println("3. Add edge");
        System.out.println("4. Print shortest path from starting point to destination point");
        System.out.println("5. Exit\n");
        System.out.print("Option: ");

        option = scanner.nextInt();

        while (option < 1 && option > 5) {
            System.out.println("Invalid option. Please enter the valid input.");
            System.out.println("Option: ");
            option = scanner.nextInt();
        }

        return option;
    }

    public static void printAvailableVertices(pseudofile g) {
        g.showVertices();
        System.out.println();
    }

    public static void printAllEdges(pseudofile g) {
        g.showEdges();
        System.out.println();
    }

    public static void addEdge(Scanner scanner, pseudofile g) {
        System.out.print("Enter source vertex: ");
        int src = scanner.nextInt();
        System.out.print("Enter destination vertex: ");
        int dest = scanner.nextInt();
        g.addEdge(src, dest);
        System.out.println("Edge added successfully.");
        System.out.println();
    }

    public static void printShortestPath(Scanner scanner, pseudofile g) {

        g.showVertices();

        List<Integer> vertices = g.getVertices();
        int start, destination;

        do {
            System.out.print("Enter starting vertex: ");
            start = scanner.nextInt();
            if (!vertices.contains(start)) {
                System.out.println("\nInvalid starting vertex. Please enter the valid vertex.");
                System.out.print("Starting Vertex: ");
                start = scanner.nextInt();
            }
        } while (!vertices.contains(start));

        do {
            System.out.print("Enter destination vertex: ");
            destination = scanner.nextInt();
            if (!vertices.contains(destination)) {
                System.out.println("\nInvalid destination vertex. Please enter the valid vertex.");
                System.out.print("Destination Vertex: ");
                destination = scanner.nextInt();
            }
        } while (!vertices.contains(destination));

        g.findPathDFS(start, destination);
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        pseudofile g = null;

        try {
            File file = new File("vertex.txt");
            Scanner fileScanner = new Scanner(file);

            int numVertices = fileScanner.nextInt();
            g = new pseudofile(numVertices);

            while (fileScanner.hasNextInt()) {
                int src = fileScanner.nextInt();
                int dest = fileScanner.nextInt();
                g.addEdge(src, dest);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }

        while (!exit) {

            int option = ActionOption(scanner, g);
            System.out.println("---");

            switch (option) {
                case 1:
                    printAvailableVertices(g);
                    break;
                case 2:
                    printAllEdges(g);
                    break;
                case 3:
                    addEdge(scanner, g);
                    break;
                case 4:
                    printShortestPath(scanner, g);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
                    break;
            }
        }
    }
}
