import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v, weight;

    Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class EstradasEscuras {
    static int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    static void unite(int[] parent, int[] rank, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            if (m == 0 && n == 0) break;

            List<Edge> edges = new ArrayList<>();
            int totalWeight = 0;

            for (int i = 0; i < n; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int weight = scanner.nextInt();
                edges.add(new Edge(u, v, weight));
                totalWeight += weight;
            }

            Collections.sort(edges);

            int[] parent = new int[m];
            int[] rank = new int[m];
            for (int i = 0; i < m; i++) {
                parent[i] = i;
                rank[i] = 0;
            }

            int mstWeight = 0;
            for (Edge edge : edges) {
                if (find(parent, edge.u) != find(parent, edge.v)) {
                    unite(parent, rank, edge.u, edge.v);
                    mstWeight += edge.weight;
                }
            }

            System.out.println(totalWeight - mstWeight);
        }

        scanner.close();
    }
}
