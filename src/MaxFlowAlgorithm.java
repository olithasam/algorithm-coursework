import java.util.Stack;

public class MaxFlowAlgorithm {

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

    public static int fordFulkerson(Graph g, int s, int t) {
        int maxFlow = 0;
        int[] parent = new int[g.n];
        Edge[] parentEdge = new Edge[g.n];

        while (true) {
            boolean[] visited = new boolean[g.n];
            if (!dfs(g, s, t, visited, parent, parentEdge)) break;

            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != s; v = parent[v]) {
                pathFlow = Math.min(pathFlow, parentEdge[v].capacity - parentEdge[v].flow);
            }

            for (int v = t; v != s; v = parent[v]) {
                Edge e = parentEdge[v];
                e.flow += pathFlow;
                g.adj[e.to].get(e.rev).flow -= pathFlow;
            }

            maxFlow += pathFlow;
            System.out.println("Augmented path flow: " + pathFlow);
        }

        return maxFlow;
    }
}
