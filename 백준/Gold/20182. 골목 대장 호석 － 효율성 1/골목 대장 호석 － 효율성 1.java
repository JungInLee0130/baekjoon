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

        // 비용정함
        //System.out.println(binarySearch());
        System.out.println(dijk());

        bw.flush();
        br.close();
        bw.close();
    }

    private static int minValue = Integer.MAX_VALUE;
    private static int binarySearch() {
        int left = 1;
        int right = 2_000_000;
        int answer = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            // 이동가능 : 비용 줄임
            /*if (dijk(mid)) {
                right = mid - 1;
            }
            else{ // 이동 불가 : 비용 늘림
                left = mid + 1;
            }*/
        }

        if (minValue > 2_000_000) {
            return answer;
        }
        return minValue;
    }

    private static final int INF = Integer.MAX_VALUE;
    private static int[] cost;
    private static int dijk() {
        PriorityQueue<Point> pq = new PriorityQueue<>(); // 비용기준으로 내림차순
        pq.add(new Point(A, 0, 0)); // 출발지, 비용, 수치심
        cost = new int[N + 1];
        Arrays.fill(cost, INF);
        cost[A] = 0; // 초기화

        while (!pq.isEmpty()) {
            Point poll = pq.poll();

            int start = poll.p;

            if (start == B) {
                minValue = cost[B];
                return minValue;
                //return true;
            }

            if (poll.shy > cost[poll.p]) continue;

            for (Point endPoint :graph.get(poll.p)) {
                int end = endPoint.p;
                int value = endPoint.v;

                // 더한 값이 mid 초과인경우 : 걸러냄
                if (cost[start] + value > C) continue;

                // end 수치심이 더 낮을 경우
                if (cost[end] <= Math.max(cost[start], value)) continue;

                cost[end] = Math.max(cost[start], value);
                pq.add(new Point(end, cost[end], cost[end]));
            }
        }

        return -1;
        //return false;
    }
}