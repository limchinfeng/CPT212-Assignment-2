import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class pseudofile {
    private static LinkedList<Integer> adjLists[];
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
        System.out.println("Available Location:");

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
        System.out.println("\n");
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
            System.out.println("\nShortest path to the destination location:");
            for (int j = 0; j < path.size(); j++) {
                System.out.print(path.get(j));
                if (j != path.size() - 1)
                    System.out.print(" -> ");
            }
            System.out.println();
        } else {
            System.out.println(
                    "\nNo path found from starting location " + vertex + " to the destination location " + destination);
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

String[] bigLetters = {
            "  _____  .__              .__                         _________",
            " /  _  \\ |__|____________ |  | _____    ____   ____   \\_   ___ \\  ____   _____ ___________    ____ ___.__.",
            " /  /_\\  \\|  \\_  __ \\____ \\|  | \\__  \\  /    \\_/ __ \\  /    \\  \\/ /  _ \\ /     \\\\____ \\__  \\  /    <   |  |",
            "/    |    \\  ||  | \\/  |_> >  |__/ __ \\|   |  \\  ___/  \\     \\___(  <_> )  Y Y  \\  |_> > __ \\|   |  \\___  |",
            "\\____|__  /__||__|  |   __/|____(____  /___|  /\\___  >  \\______  /\\____/|__|_|  /   __(____  /___|  / ____|",
            "        \\/          |__|             \\/     \\/     \\/          \\/             \\/|__|       \\/     \\/\\/",
        };

        for (String line : bigLetters) {
            System.out.println(line);
        }

        System.out.println("\nSelect an option:");
        System.out.println("1. Print available location");
        System.out.println("2. Print all edges between each location");
        System.out.println("3. Add edge");
        System.out.println("4. Remove edge");
        System.out.println("5. Print shortest path from starting point to destination location");
        System.out.println("6. Exit\n");
        System.out.print("Option: ");

        option = scanner.nextInt();

        while (option < 1 && option > 6) {
            System.out.println("Invalid option. Please enter a valid input.");
            System.out.print("Option: ");
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
        System.out.print("Enter source location: ");
        int src = scanner.nextInt();
        System.out.print("Enter destination location: ");
        int dest = scanner.nextInt();
        g.addEdge(src, dest);
        System.out.println("Edge added successfully between two locations.");
        System.out.println();
    }

    public static void removeEdge(Scanner scanner, pseudofile g) {
        System.out.print("Enter source location: ");
        int src = scanner.nextInt();
        System.out.print("Enter destination location: ");
        int dest = scanner.nextInt();
        if (adjLists[src].contains(dest)) {
            adjLists[src].remove(Integer.valueOf(dest));
            System.out.println("Edge removed successfully.");
        } else {
            System.out.println("Edge does not exist between the given locations.");
        }
        System.out.println();
    }

    public static void printShortestPath(Scanner scanner, pseudofile g) {

        g.showVertices();

        List<Integer> vertices = g.getVertices();
        int start, destination;

        do {
            System.out.print("Enter starting location: ");
            start = scanner.nextInt();
            if (!vertices.contains(start)) {
                System.out.println("\nInvalid starting location. Please enter the valid location.");
                System.out.print("Starting Location: ");
                start = scanner.nextInt();
            }
        } while (!vertices.contains(start));

        do {
            System.out.print("Enter destination location: ");
            destination = scanner.nextInt();
            if (!vertices.contains(destination)) {
                System.out.println("\nInvalid destination location. Please enter the valid location.");
                System.out.print("Destination Location: ");
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
                    removeEdge(scanner, g);
                    break;
                case 5:
                    printShortestPath(scanner, g);
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
                    break;
            }

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }

        String[] ending = {
            "\t\t___________.__                   __     _____.___.             ",
            "\t\t\\__    ___/|  |__ _____    ____ |  | __ \\__  |   | ____  __ __ ",
            "\t\t  |    |   |  |  \\__  \\  /    \\|  |/ /  /   |   |/  _ \\|  |  \\",
            "\t\t  |    |   |   Y  \\/ __ \\|   |  \\    <   \\____   (  <_> )  |  /",
            "\t\t  |____|   |___|  (____  /___|  /__|_ \\  / ______|\\____/|____/ ",
            "\t\t                \\/     \\/     \\/     \\/  \\/                     ",
        };

        for (String line : ending) {
            System.out.println(line);
        }


    }
}
