import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            graph.get(parent).add(child);
            graph.get(child).add(parent);
        }

        visited = new boolean[N + 1];

        dfs(a, b, 0);

        System.out.println(cnt);
    }

    static boolean[] visited;
    static int cnt = -1;
    private static int dfs(int start, int b, int count) {
        if (start == b) {
            cnt = count;
            return count;
        }

        visited[start] = true;

        for (Integer end :graph.get(start)) {
            if (!visited[end]) {
                dfs(end, b, count + 1);
            }
        }

        return count;
    }
}