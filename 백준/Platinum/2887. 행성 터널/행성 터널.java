import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    static int N;
    static class Point{
        int num;
        int x;
        int y;
        int z;

        public Point(int num, int x, int y, int z) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static class Edge{
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
    static ArrayList<Edge> edges;
    static Point[] points;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 100000

        points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            points[i] = new Point(i, x, y, z);
        }

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.x, o2.x);
            }
        });

        edges = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            int sub = points[i + 1].x - points[i].x;

            edges.add(new Edge(points[i].num, points[i + 1].num, sub));
        }

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.y, o2.y);
            }
        });

        for (int i = 0; i < N - 1; i++) {
            int sub = points[i + 1].y - points[i].y;

            edges.add(new Edge(points[i].num, points[i + 1].num, sub));
        }

        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return Integer.compare(o1.z, o2.z);
            }
        });

        for (int i = 0; i < N - 1; i++) {
            int sub = points[i + 1].z - points[i].z;

            edges.add(new Edge(points[i].num, points[i + 1].num, sub));
        }

        // x, y, z 간선 대입 끝

        // 크루스칼 시작

        Collections.sort(edges, new Comparator<Edge>() { // 무게 순 오름차순 정렬
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });

        int min = 0;

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (Edge edge : edges) {
            if (find(edge.s) != find(edge.e)) {
                min += edge.w;
                union(edge.s, edge.e);
            }
        }

        System.out.println(min);



        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int s, int e) {
        s = find(s);
        e = find(e);

        if (s < e) {
            parent[e] = s;
        }
        else{
            parent[s] = e;
        }
    }

    static int[] parent;

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}