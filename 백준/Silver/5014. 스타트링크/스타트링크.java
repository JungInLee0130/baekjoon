import java.io.*;
import java.util.*;

public class Main {
    static int[] map;
    static int F, S, G, U, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 1 ~ F

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[F]; // 0 ~ F - 1

        int ans = bfs(S - 1);

        if (ans == -1) System.out.println("use the stairs");
        else System.out.println(ans);

        bw.flush();
        br.close();
        bw.close();
    }

    // U만큼 이동, D만큼 이동해서 도달하는 최소횟수 출력
    static Queue<int[]> queue;
    static boolean[] visited;
    private static int bfs(int S) {
        queue = new LinkedList<>();
        visited = new boolean[F];
        int time = 0;
        int[] move = new int[]{U, -D};

        queue.add(new int[]{S, 0}); // S, times
        visited[S] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if (poll[0] == G - 1) return poll[1];

            for (int i = 0; i < 2; i++) {
                int nm = poll[0] + move[i];

                // 범위 벗어나면
                if (0 > nm || nm > F - 1) continue;
                // 방문한곳이면
                if (visited[nm]) continue;

                visited[nm] = true;
                queue.add(new int[]{nm, poll[1] + 1});
            }
        }

        return -1;
    }
}