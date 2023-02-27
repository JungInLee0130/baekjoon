import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Point[] star;
    static class Point{
        double x;
        double y;
        int num;

        public Point(double x, double y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    static class Edge{
        Point start;
        Point end;
        double cost;

        public Edge(Point start, Point end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    static ArrayList<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        star = new Point[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            star[i] = new Point(x, y, i);
        }

        edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                Point start = star[i];
                Point end = star[j];
                double cost = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));

                edges.add(new Edge(start, end, cost));
            }
        }

        // 가까운 순으로 정렬
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.cost, o2.cost);
            }
        });

        parent = new int[N]; // 0 ~ N

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        totalCost = 0.0;

        union();


        System.out.printf("%.2f\n", totalCost);




        bw.flush();
        br.close();
        bw.close();
    }

    static double totalCost;
    private static void union() {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            int from = edge.start.num;
            int to = edge.end.num;
            double cost = edge.cost;

            int fromParent = find(from);
            int toParent = find(to);

            if (fromParent == toParent) continue;

            int min = Math.min(fromParent, toParent);
            int max = Math.max(fromParent, toParent);

            totalCost += edge.cost;
            parent[max] = min;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    static int[] parent;
}

/* 별자리를 만드는 최소 비용
* 1000이하
* */