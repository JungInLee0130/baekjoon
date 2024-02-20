import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Vertex>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Vertex(end, cost));
            //graph.get(end).add(new Vertex(start, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        int answer = dijk(s, e);

        System.out.println(answer);
    }
    static class Vertex implements Comparable<Vertex>{
        int num;
        int cost;

        public Vertex(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex v) {
            return Integer.compare(this.cost, v.cost);
        }
    }

    private static int dijk(int start, int end) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1]; // 최단거리를 저장할 배열 : start에 대한 // 1 ~ N
        Arrays.fill(dist, 100000000);
        dist[start] = 0;
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex poll = pq.poll();
            int cur = poll.num;

            if (visited[cur]) continue;
            visited[cur] = true;

            for (Vertex v: graph.get(cur)) {
                int ev = v.num;
                int cost = poll.cost + v.cost;

                if (cost < dist[ev]) {
                    dist[ev] = cost;
                    //System.out.println(cur + " : " + ev + ":" + cost);
                    pq.add(new Vertex(ev, cost));
                }
            }
        }

        /*for (Integer e:dist) {
            System.out.print(e + " ");
        }
        System.out.println();*/

        return dist[end];
    }
}