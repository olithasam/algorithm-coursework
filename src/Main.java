import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("***********************************");
        System.out.println("      Network Flow Algorithm       ");
        System.out.println("***********************************");
        System.out.println(" ");

        Scanner inputScanner = new Scanner(System.in);
        Scanner fileScanner = null;
        String filePath = "";

        while (true) {
            System.out.print("Enter the benchmark file name (eg-: ladder_3): ");
            String fileName = inputScanner.nextLine();
            filePath = "./benchmarks/" + fileName + ".txt";

            try {
                fileScanner = new Scanner(new File(filePath));
                break;
            } catch (IOException e) {
                System.out.println("Invalid file name. Please enter a valid one.");
            }
        }

        int n = fileScanner.nextInt();
        Graph g = new Graph(n);

        while (fileScanner.hasNext()) {
            int from = fileScanner.nextInt();
            int to = fileScanner.nextInt();
            int cap = fileScanner.nextInt();
            g.addEdge(from, to, cap);
        }

        int maxFlow = MaxFlowAlgorithm.fordFulkerson(g, 0, n - 1);
        System.out.println(" ");
        System.out.println("Maximum Flow: " + maxFlow);

        inputScanner.close();
        fileScanner.close();
    }
}
