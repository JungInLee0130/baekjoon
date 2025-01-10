import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N;
    static int[][] S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 능력치 차이 최소.
        // N = 20
        N = Integer.parseInt(br.readLine());

        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        visited = new boolean[N];
        dfs(0);

        System.out.println(min);

        br.close();
    }

    static boolean[] visited;
    private static void dfs(int cnt) {
        if (cnt == N) {
            sum();
            return;
        }

        visited[cnt] = true;
        dfs(cnt + 1);
        visited[cnt] = false;
        dfs(cnt + 1);
    }

    private static void sum() {

        startSum = 0;
        linkSum = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    startSum += S[i][j] + S[j][i];
                } else if (!visited[i] && !visited[j]) {
                    linkSum += S[i][j] + S[j][i];
                }
            }
        }

        int sub = Math.abs(startSum - linkSum);

        min = Math.min(min, sub);
    }

    static int min = Integer.MAX_VALUE;
    static int startSum;
    static int linkSum;
}