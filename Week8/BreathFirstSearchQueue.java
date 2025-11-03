package Week8;

public class BreathFirstSearchQueue {
    public java.util.List<Integer> bfs(java.util.List<Integer>[] graph, int start) {
        if (graph == null || graph.length == 0) return new java.util.ArrayList<>();
        if (start < 0 || start >= graph.length) throw new IllegalArgumentException("start out of bounds");

        boolean[] visited = new boolean[graph.length];
        java.util.List<Integer> order = new java.util.ArrayList<>();
        java.util.Queue<Integer> queue = new java.util.ArrayDeque<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.remove();
            order.add(u);
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return order;
    }

    public static java.util.List<Integer>[] buildUndirectedGraph(int n, int[][] edges) {
        @SuppressWarnings("unchecked")
        java.util.List<Integer>[] g = (java.util.List<Integer>[]) new java.util.ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new java.util.ArrayList<>();
        for (int[] e : edges) {
            if (e.length >= 2) {
                int a = e[0], b = e[1];
                g[a].add(b);
                g[b].add(a);
            }
        }
        return g;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0,1},{0,2},{1,3},{2,4},{2,5}};
        java.util.List<Integer>[] graph = buildUndirectedGraph(n, edges);
        BreathFirstSearchQueue bfsInstance = new BreathFirstSearchQueue();
        java.util.List<Integer> order = bfsInstance.bfs(graph, 0);
        System.out.println("BFS order: " + order);
    }
}
