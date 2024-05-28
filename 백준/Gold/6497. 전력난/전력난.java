import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int M, N;
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }


        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    static PriorityQueue<Edge> edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken()); // 0 ~ N - 1
            N = Integer.parseInt(st.nextToken());

            if (M == 0 && N == 0) {
                break;
            }

            edges = new PriorityQueue<>();

            // MST : prim, union find
            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            int answer = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges.add(new Edge(start, end, weight));
                answer += weight;
            }

            min = 0;
            while (!edges.isEmpty()) {
                Edge poll = edges.poll();
                kruskal(poll);
            }

            System.out.println(answer - min);
        }



        bw.flush();
        bw.close();
        br.close();
    }

    static int min;
    private static void kruskal(Edge edge) {
        int start = edge.start;
        int end = edge.end;
        if (find(start) != find(end)) {
            min += edge.weight;
            union(start, end);
        }
    }

    private static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start <= end) {
            parent[end] = start;
        }
        else{
            parent[start] = end;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static int[] parent;
}