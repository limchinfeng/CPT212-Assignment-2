import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Charpseudofile {
    private LinkedList<Character> adjLists[];
    private char num[];
    private List<Character> path;

    // Graph creation
    Charpseudofile(int vertices) {
        adjLists = new LinkedList[vertices];
        num = new char[vertices];
        path = new ArrayList<>();

        for (int j = 0; j < vertices; j++)
            adjLists[j] = new LinkedList<Character>();
    }

    // Add edges
    void addEdge(char src, char dest) {
        adjLists[src - 'A'].add(dest);
    }

    // Show all vertices
    void showVertices() {
        System.out.println("Vertices with edges:");

        Set<Character> verticesWithEdges = new HashSet<>();

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            if (!adjLists[vertex].isEmpty()) {
                verticesWithEdges.add((char) ('A' + vertex));
                for (char neighbor : adjLists[vertex]) {
                    verticesWithEdges.add(neighbor);
                }
            }
        }

        for (char vertex : verticesWithEdges) {
            System.out.print(vertex + " ");
        }
        System.out.println("");
    }

    // Show all edges
    void showEdges() {
        System.out.println("All edges:");

        for (int vertex = 0; vertex < adjLists.length; vertex++) {
            for (char neighbor : adjLists[vertex]) {
                System.out.println((char) ('A' + vertex) + " -> " + neighbor);
            }
        }
    }

    // Find path using DFS
    void findPathDFS(char vertex, char destination) {
        for (int j = 0; j < adjLists.length; j++)
            num[j] = '\0';
        path = new ArrayList<>();

        DFS(vertex - 'A', destination - 'A');

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
        num[vertex] = '1';

        if (vertex == destination) {
            path.add((char) ('A' + vertex));
            return true;
        }

        Iterator<Character> ite = adjLists[vertex].listIterator();

        while (ite.hasNext()) {
            char adj = ite.next();
            if (num[adj - 'A'] == '\0') {
                if (DFS(adj - 'A', destination)) {
                    path.add(0, (char) ('A' + vertex));
                    return true;
                }
            }
        }

        return false;
    }

    static int ActionOption(Scanner scanner, Charpseudofile g) {
        int option = 0;

        System.out.println("Select an option:");
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

    public static void printAvailableVertices(Charpseudofile g) {
        g.showVertices();
        System.out.println();
    }

    public static void printAllEdges(Charpseudofile g) {
        g.showEdges();
        System.out.println();
    }

    public static void addEdge(Scanner scanner, Charpseudofile g) {
        System.out.print("Enter source vertex: ");
        char src = scanner.next().charAt(0);
        System.out.print("Enter destination vertex: ");
        char dest = scanner.next().charAt(0);
        g.addEdge(src, dest);
        System.out.println("Edge added successfully.");
        System.out.println();
    }

    public static void printShortestPath(Scanner scanner, Charpseudofile g) {

        g.showVertices();

        System.out.print("Enter starting point: ");
        char start = scanner.next().charAt(0);
        System.out.print("Enter destination point: ");
        char destination = scanner.next().charAt(0);
        if (start >= 'A' && start <= 'Z' && destination >= 'A' && destination <= 'Z') {
            g.findPathDFS(start, destination);
        } else {
            System.out.println("Invalid starting point or destination point.");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Charpseudofile g = null;

        try {
            File file = new File("CharVertex.txt");
            Scanner fileScanner = new Scanner(file);

            int numVertices = fileScanner.nextInt();
            g = new Charpseudofile(numVertices);

            while (fileScanner.hasNext()) {
                char src = fileScanner.next().charAt(0);
                char dest = fileScanner.next().charAt(0);
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
