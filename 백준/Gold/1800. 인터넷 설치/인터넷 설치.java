import java.awt.*;
import java.io.*;
import java.rmi.dgc.VMID;
import java.util.*;
import java.util.List;

public class Main {
    static int N, P, K;
    static List<List<Point>> graph;
    static class Point implements Comparable<Point>{
        int e;
        int v;

        public Point(int e, int v) {
            this.e = e;
            this.v = v;
        }

        @Override
        public int compareTo(Point o) {
            return this.v - o.v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            // K개는 공짜로 연결해줌
            graph.get(s).add(new Point(e, v));
            graph.get(e).add(new Point(s, v));
        }

        // 1번부터 N번까지 갈수있는 모든 경우의 수
        // 모든 합중에 작은거 vs K개만큼 공짜후 작은거 -> 이거는 찾을수가 없음
        // 그냥 최대 비용을 정하고, 이분탐색을 통해 비용을 먼저 선택
        // 해당 비용안에 1부터 N에 도달할수있는지 검사
        binarySearch();

        if (answer == 1_000_001){
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }


        bw.flush();
        br.close();
        bw.close();
    }

    private static int answer = -1;
    private static void binarySearch() {
        int left = 0;
        int right = 1_000_000;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (dijk(mid)) {
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        answer = Math.max(left, right);
    }

    static int[] check;
    static final int INF = Integer.MAX_VALUE;
    private static boolean dijk(int cost) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(1, 0)); // v = 횟수?
        check = new int[N + 1];
        Arrays.fill(check, INF);
        check[1] = 0;
        while (!pq.isEmpty()) {
            Point poll = pq.poll();

            int curNode = poll.e;
            int curTime = poll.v; // 사용횟수

            if (check[curNode] < curTime) continue; // 사용횟수 다씀
            for (Point next : graph.get(curNode)) {
                int times = curTime; // 사용횟수

                // cost 비교
                if (next.v > cost) { // cost보다 크면
                    times++; // 사용횟수 + 1
                }
                // 사용횟수 안썻으면
                if (check[next.e] > times) { //
                    check[next.e] = times;
                    pq.add(new Point(next.e, times)); // 다음점과 사용현황
                }
            }
        }
        return check[N] <= K;
    }
}