import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static class Edge{
        int s;
        int e;
        int cost;

        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
    }
    static int N, M, W;
    static int INF = Integer.MAX_VALUE;
    static ArrayList<Edge> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int move = Integer.parseInt(st.nextToken()); // move

                // 무방향
                graph.add(new Edge(start, end, move));
                graph.add(new Edge(end, start, move));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int minusTime = Integer.parseInt(st.nextToken()); // reduce

                // 방향
                graph.add(new Edge(start, end, -1 * minusTime));
            }

            // 시간이 줄어들면서 출발위치로 돌아오는것이 가능한지 여부 판단.
            boolean isYes = false;
            isYes = BellmanFord(1);
            for (int i = 1; i <= N; i++) { // 500
                isYes = BellmanFord(i);

                if (isYes) {
                    System.out.println("YES");
                    break;
                }
            }

            if (!isYes) {
                System.out.println("NO");
            }
        }


        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean BellmanFord(int start) {
        int[] dist = new int[N + 1]; // 1 ~ N
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 1; i < N; i++) { // 정점 - 1 // 499
            boolean isUpdate = false;
            for (int j = 0; j < graph.size(); j++) { // 간선 // 2700
                Edge edge = graph.get(j);

                // 현재 들어오는 정점이
                // 스타트가 무한이 아니고
                // 스타트 + 간선 < 엔드지점
                // 갱신
                if (dist[edge.s] != INF && dist[edge.e] > dist[edge.s] + edge.cost) {
                    dist[edge.e] = dist[edge.s] + edge.cost;
                    isUpdate = true;
                }
            }

            if (!isUpdate) {
                break;
            }
        }

        // 음수 가중치 확인
        for (int i = 0; i < graph.size(); i++) {
            Edge edge = graph.get(i);

            // 한번 더 돌렸는데 더 작은 값이 생김 -> 음수 사이클 존재
            if (dist[edge.s] != INF && dist[edge.e] > dist[edge.s] + edge.cost) {
                return true;
            }
        }

        return false;
    }
}