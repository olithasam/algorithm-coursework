import java.io.File;
import java.io.IOException;
import java.util.*;

// Main class to run the Network Flow Algorithm
public class Main {
    public static void main(String[] args) {

        System.out.println("***********************************");
        System.out.println("      Network Flow Algorithm       ");
        System.out.println("***********************************");
        System.out.println(" ");

        Scanner inputScanner = new Scanner(System.in);
        Scanner fileScanner = null;
        String filePath = "";

        // Ask user to input a valid benchmark file name
        while (true) {
            System.out.print("Enter the benchmark file name (eg-: ladder_3): ");
            String fileName = inputScanner.nextLine();
            filePath = "./benchmarks/" + fileName + ".txt";

            try {
                fileScanner = new Scanner(new File(filePath));
                break;  // File successfully opened
            } catch (IOException e) {
                System.out.println("Invalid file name. Please enter a valid one.");
            }
        }
        // Read number of nodes
        int n = fileScanner.nextInt();
        Graph g = new Graph(n);

        // Arrays to track in-degree and out-degree of each node
        int[] inDegree = new int[n];
        int[] outDegree = new int[n];

        // Read edges from the file and build the graph
        while (fileScanner.hasNext()) {
            int from = fileScanner.nextInt();
            int to = fileScanner.nextInt();
            int cap = fileScanner.nextInt();
            g.addEdge(from, to, cap);
            outDegree[from]++;
            inDegree[to]++;
        }

        // Source node has out-degree > 0 and in-degree == 0
        int source = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0 && outDegree[i] > 0) {
                source = i;
                break;
            }
        }

        // Set sink node as the last node (n - 1)
        int sink = n - 1;

        // Error handling if no valid source found
        if (source == -1) {
            System.out.println("Error: No valid source node found.");
            return;
        }
        // Display the detected source and sink nodes
        System.out.println("\nSource Node: " + source + ", Sink Node: " + sink);

        // Run Ford-Fulkerson
        int maxFlow = MaxFlowAlgorithm.fordFulkerson(g, source, sink, true);

        // Display the maximum flow result
        System.out.println(" ");
        System.out.println("Maximum Flow: " + maxFlow);

        inputScanner.close();
        fileScanner.close();
    }
}
