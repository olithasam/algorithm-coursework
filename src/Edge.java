public class Edge {
    int to, rev;
    int capacity, flow;

    public Edge(int to, int rev, int capacity) {
        this.to = to;
        this.rev = rev;
        this.capacity = capacity;
        this.flow = 0;
    }
}
