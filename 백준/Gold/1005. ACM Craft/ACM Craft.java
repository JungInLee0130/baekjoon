import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int T, N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 1 ~ N : 100이하
            K = Integer.parseInt(st.nextToken()); // 10만이하

            st = new StringTokenizer(br.readLine()); // 소요시간

            int[] times = new int[N + 1]; // 1 ~ N : 10만이하
            for (int i = 1; i <= N; i++) {
                times[i] = Integer.parseInt(st.nextToken());
            }

            graph = new ArrayList<>();
            graph.add(new ArrayList<>());
            for (int i = 1; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            indegree = new int[N + 1]; // 1 ~ N
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                graph.get(start).add(end);
                indegree[end]++; // 진입차수
            }

            int target = Integer.parseInt(br.readLine());

            sumTimes = new int[N + 1];

            int n = topologySort(target, times);

            if (n == -1) {
                System.out.println(-1);
            }
            else{
                System.out.println(sumTimes[target]);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> graph;
    static int[] indegree;
    static int[] sumTimes;

    private static int topologySort(int target, int[] times) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                sumTimes[i] += times[i]; // 누적 초기화
            }
        }


        for (int i = 0; i < N; i++) {
            if (queue.isEmpty()) {
                System.out.println("사이클 발생");
                return -1;
            }

            Integer poll = queue.poll();

            if (poll == target) {
                return 1;
            }


            for (Integer e :graph.get(poll)) {
                // 미리 저장되있는 거냐 더해서 큰거냐
                sumTimes[e] = Math.max(sumTimes[e], sumTimes[poll] + times[e]);
                
                indegree[e]--;
                if (indegree[e] == 0) {
                    queue.add(e);
                }
            }
        }
        return 1;
    }
}