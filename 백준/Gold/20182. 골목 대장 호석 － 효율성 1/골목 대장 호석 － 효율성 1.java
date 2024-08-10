import java.io.*;
import java.util.*;

public class Main {
    static int N, M, C;
    static int A, B;
    static List<List<Point>> graph;
    static class Point implements Comparable<Point>{
        int p;
        int v;
        int shy;

        public Point(int p, int v) {
            this.p = p;
            this.v = v;
        }

        public Point(int p, int v, int shy) {
            this.p = p;
            this.v = v;
            this.shy = shy;
        }


        @Override
        public int compareTo(Point o) {
            return this.shy - o.shy;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Point(e, v));
            graph.get(e).add(new Point(s, v));
        }
        
        System.out.println(dijk());

        bw.flush();
        br.close();
        bw.close();
    }
    private static final int INF = Integer.MAX_VALUE;
    private static int[] shy;
    private static int dijk() {
        PriorityQueue<Point> pq = new PriorityQueue<>(); // 비용기준으로 내림차순
        pq.add(new Point(A, 0, 0)); // 출발지, 비용, 수치심
        shy = new int[N + 1];
        Arrays.fill(shy, INF);
        shy[A] = 0; // 초기화

        while (!pq.isEmpty()) {
            Point poll = pq.poll();

            int start = poll.p;
            int curCost = poll.v;
            int curShy = poll.shy;

            if (start == B) {
                return shy[B];
            }

            // 현재 수치심이 더 큼
            if (curShy > shy[start]) continue;

            for (Point endPoint :graph.get(start)) {
                int end = endPoint.p;
                int cost = endPoint.v;

                // 비용 : 더한 값이 C 초과인경우 : 걸러냄
                if (curCost + cost > C) continue;

                // 수치심 : end 수치심이 더 낮을 경우
                if (shy[end] <= Math.max(shy[start], cost)) continue;

                // 더 클경우 : 갱신
                shy[end] = Math.max(shy[start], cost);
                pq.add(new Point(end, curCost + cost, shy[end]));
            }
        }

        return -1;
    }
}