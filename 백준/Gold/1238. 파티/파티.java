import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static class Vertex implements Comparable<Vertex>{
        int end;
        int weight;

        public Vertex(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }


        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    static ArrayList<ArrayList<Vertex>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Vertex(end, t));
        }

        // 다익스트라일꺼같은데
        dist = new int[N + 1][N + 1]; // 1 ~ N
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = INF;
            }
        }

        // 시작점부터 다 최단거리 찾기
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            dijk(i);
        }

        int[] answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            answer[i] = dist[i][X] + dist[X][i];
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            max = Math.max(max, answer[i]);
        }

        System.out.println(max);
    }

    static int[][] dist; // 시작점, 끝점
    static final int INF = Integer.MAX_VALUE;
    static boolean[] visited;
    private static void dijk(int start) { // 시작점부터 모든 지점까지의 최단거리
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex(start, 0));
        //visited[start] = true;
        dist[start][start] = 0;

        while (!priorityQueue.isEmpty()) {
            Vertex poll = priorityQueue.poll();

            for (Vertex ends: graph.get(poll.end)) {
                int end = ends.end;
                int weight = ends.weight;

                //!visited[end] &&
                if (dist[start][end] > weight + poll.weight) {
                    visited[end] = true;
                    dist[start][end] = weight + poll.weight;
                    priorityQueue.add(new Vertex(end, dist[start][end]));
                }
            }
        }
    }
}