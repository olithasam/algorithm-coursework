import java.util.*;

public class MaxFlowAlgorithm {
    public static int fordFulkerson(Graph g, int s, int t) {
        int maxFlow = 0;
        int[] parent = new int[g.n];
        Edge[] parentEdge = new Edge[g.n];

        List<List<Integer>> augmentingPaths = new ArrayList<>();
        List<Integer> pathFlows = new ArrayList<>();

        while (true) {
            boolean[] visited = new boolean[g.n];
            if (!dfs(g, s, t, visited, parent, parentEdge)) break;

            // Find bottleneck capacity (minimum residual capacity along the path)
            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                pathFlow = Math.min(pathFlow, parentEdge[v].capacity - parentEdge[v].flow);
            }

            // Store this augmenting path
            List<Integer> path = new ArrayList<>();
            for (int v = t; v != s; v = parent[v]) {
                path.add(v);
            }
            path.add(s);
            Collections.reverse(path);
            augmentingPaths.add(path);
            pathFlows.add(pathFlow);

            // Update flow along the path
            for (int v = t; v != s; v = parent[v]) {
                Edge e = parentEdge[v];
                e.flow += pathFlow;
                g.adj[e.to].get(e.rev).flow -= pathFlow;
            }

            maxFlow += pathFlow;
        }

        // Print augmenting paths and flows
        System.out.println("\nFinal Augmenting Paths and Flow Calculations:");
        for (int i = 0; i < augmentingPaths.size(); i++) {
            List<Integer> path = augmentingPaths.get(i);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < path.size(); j++) {
                sb.append(path.get(j));
                if (j != path.size() - 1) {
                    sb.append(" -> ");
                }
            }
            System.out.println("Path " + (i + 1) + ": " + sb + " with flow = " + pathFlows.get(i));
        }

        return maxFlow;
    }

    private static boolean dfs(Graph g, int s, int t, boolean[] visited, int[] parent, Edge[] parentEdge) {
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        visited[s] = true;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            for (Edge e : g.adj[u]) {
                if (!visited[e.to] && e.flow < e.capacity) {
                    visited[e.to] = true;
                    parent[e.to] = u;
                    parentEdge[e.to] = e;
                    stack.push(e.to);
                    if (e.to == t) return true;
                }
            }
        }
        return false;
    }
}
