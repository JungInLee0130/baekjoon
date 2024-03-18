import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Vertex>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Vertex(end, 1));
            graph.get(end).add(new Vertex(start, 1));
        }

        // 다익스트라
        dist = new int[N + 1][N + 1]; // 1 ~ N
        int[] kevin = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dijk(i);
        }

        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                kevin[i] += dist[i][j];
            }
            if (min > kevin[i]) {
                min = kevin[i];
                index = i;
            }
        }

        System.out.println(index);
    }

    static int[][] dist;
    static class Vertex implements Comparable<Vertex>{
        int num;
        int weight;

        public Vertex(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }


        @Override
        public int compareTo(Vertex v) {
            return Integer.compare(this.weight, v.weight); // 사실 필요없긴함.
        }
    }
    private static void dijk(int start) {
        dist[start][start] = 0;
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));
        // 아 다익스트라 visited 체크 쥰ㄴㄴㄴ내 헷갈리네

        while (!pq.isEmpty()) {
            Vertex poll = pq.poll();

            for (Vertex v: graph.get(poll.num)) {
                int endNum = v.num;
                int endWeight = v.weight;
                if (dist[start][endNum] > dist[start][poll.num] + endWeight) {
                    dist[start][endNum] = dist[start][poll.num] + endWeight;
                    pq.add(new Vertex(endNum, dist[start][endWeight]));
                }
            }
        }
    }
}