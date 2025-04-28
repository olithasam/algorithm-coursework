import java.util.ArrayList;
import java.util.List;

// Class to represent the flow network as an adjacency list
public class Graph {
    int n;
    List<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int n) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
    }
    // Method to add an edge with a corresponding reverse edge
    public void addEdge(int from, int to, int capacity) {
        Edge e1 = new Edge(to, adj[to].size(), capacity);
        Edge e2 = new Edge(from, adj[from].size(), 0); // reverse edge
        adj[from].add(e1);
        adj[to].add(e2);
    }
}
