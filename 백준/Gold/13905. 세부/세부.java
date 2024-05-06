import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> priorityQueue;
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
            return Integer.compare(o.weight, this.weight); // 내림차순
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 10만이하
        M = Integer.parseInt(st.nextToken()); // 30만이하

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken()); // 100만 이하

            priorityQueue.offer(new Edge(start, end, weight));
        }

        max = 0;
        kruscal();

        System.out.println(max);

        bw.flush();
        bw.close();
        br.close();
    }

    static int max;
    static int s, e;
    private static void kruscal() {
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();

            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);

                if (find(s) == find(e)) {
                    max = edge.weight;
                    break;
                }
            }
        }
    }

    private static void union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start != end) {
            parent[end] = start;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }
}