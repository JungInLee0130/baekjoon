import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] percent;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 14이하
        percent = new int[4];
        for (int i = 0; i < 4; i++) {
            percent[i] = Integer.parseInt(st.nextToken()); // 자연수, 0이상 100이하
        }

        visited = new boolean[2 * N + 1][2 * N + 1]; // 좌표 0 ~ 2N까지
        visited[N][N] = true;

        dfs(N, N, 0, 1.0);

        bw.write(String.valueOf(totalPercent));

        bw.flush();
        br.close();
        bw.close();
    }
    private static double totalPercent = 0.0;
    static boolean[][] visited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    private static void dfs(int r, int c, int cnt, double per) {
        if (cnt == N) {
            // 총 퍼센티지 모두 구함.
            totalPercent += per;
            return;
        }
        for (int i = 0; i < 4; i++) {
            // 동서남북
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 방문했던점이면
            if (visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, cnt + 1, per * (percent[i] / 100.0));
            visited[nr][nc] = false;
        }
    }
}