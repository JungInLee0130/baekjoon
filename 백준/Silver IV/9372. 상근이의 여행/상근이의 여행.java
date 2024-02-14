import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
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

                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            min = Integer.MAX_VALUE;
            visited = new boolean[N + 1];
            visited[2] = true;
            cnt = 0;
            dfs(2, 0);

            System.out.println(min);
        }
    }

    static int min = Integer.MAX_VALUE;
    static boolean[] visited;
    static boolean isOk;
    static int cnt;
    private static void dfs(int start, int depth) {
        if (check()) {
            min = Math.min(min, cnt);
            return;
        }

        for (Integer end : graph.get(start)) {
            if (!visited[end] && !isOk) {
                visited[end] = true;
                cnt++;
                dfs(end, depth + 1);
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}